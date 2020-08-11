import 'dart:convert';
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:logger/logger.dart';

import './device.dart';

class Devices with ChangeNotifier {
  Devices(this.authToken, this.userId, this._deviceItems);

  List<Device> _deviceItems = [];
  var logger = Logger();

  final String authToken;
  final String userId;
  List<Device> get deviceItems {
    return [..._deviceItems];
  }

  List<Device> get favoriteItems {
    return _deviceItems.where((prodItem) => prodItem.isFavorite).toList();
  }

  Device findDeviceById(String id) {
    return _deviceItems.firstWhere((element) => element.deviceId == id);
  }

  Future<void> fetchAndSetDevices([bool filterByUser = false]) async {
    final url =
        'http://aadphomeautomation-env.eba-hyr5pkk4.ap-south-1.elasticbeanstalk.com/aadp-vend-ws/device/$userId/userDevice';
    try {
      final response = await http.get(
        url,
        headers: {
          "Content-Type": "application/json",
          "Accept": "application/json",
          "Authorization": authToken,
        },
      );
      final extractedData = json.decode(response.body) as List<dynamic>;
      final List<Device> loadedDevices = [];

      if (extractedData == null) {
        return;
      }
      extractedData.forEach((deviceData) {
        loadedDevices.add(Device(
          deviceId: deviceData['deviceId'],
          title: deviceData['title'],
          description: deviceData['description'],
          onOffState: deviceData['onOffState'],
          isFavorite: deviceData['favorite'],
          imageUrl: deviceData['imageUrl'],
        ));
      });
      _deviceItems = loadedDevices;
      notifyListeners();
    } catch (error) {
      throw (error);
    }
  }

  Future<void> addDevice(Device device) async {
    const url =
        'http://aadphomeautomation-env.eba-hyr5pkk4.ap-south-1.elasticbeanstalk.com/aadp-vend-ws/device/newdevice';
    try {
      final response = await http.post(
        url,
        headers: {
          "Content-Type": "application/json",
          "Accept": "application/json",
          "Authorization": authToken,
        },
        body: json.encode({
          'id': '',
          'deviceId': '',
          'userId': userId,
          'title': device.title,
          'description': device.description,
          'imageUrl': device.imageUrl,
          'price': device.onOffState,
          'isFavorite': device.isFavorite,
        }),
      );
      final newDevice = Device(
        title: device.title,
        description: device.description,
        onOffState: device.onOffState,
        imageUrl: device.imageUrl,
        deviceId: json.decode(response.body)['id'],
      );
      logger.d("http status code is ->>>" + response.statusCode.toString());
      if (response.statusCode == 200) {
        _deviceItems.add(newDevice);
        notifyListeners();
      } else if (response.statusCode >= 400) {
        throw HttpException('Could not save device.');
      }
      // _items.insert(0, newdevice); // at the start of the list
      notifyListeners();
    } catch (error) {
      print(error);
      throw HttpException('Could not save device.');
    }
  }

  Future<void> updateDevice(String id, Device newDevice) async {
    final prodIndex = _deviceItems.indexWhere((prod) => prod.deviceId == id);
    if (prodIndex >= 0) {
      final url =
          'http://aadphomeautomation-env.eba-hyr5pkk4.ap-south-1.elasticbeanstalk.com/aadp-vend-ws/device/$id';
      await http.patch(url,
          headers: {
            "Content-Type": "application/json",
            "Accept": "application/json",
            "Authorization": authToken,
          },
          body: json.encode({
            'title': newDevice.title,
            'description': newDevice.description,
            'imageUrl': newDevice.imageUrl,
            'onOffState': newDevice.onOffState
          }));
      _deviceItems[prodIndex] = newDevice;
      notifyListeners();
    } else {
      print('...');
    }
  }

  Future<void> deleteDevice(String id) async {
    final url =
        'http://aadphomeautomation-env.eba-hyr5pkk4.ap-south-1.elasticbeanstalk.com/aadp-vend-ws/device/$id';
    final existingDeviceIndex =
        _deviceItems.indexWhere((device) => device.deviceId == id);
    var existingDevice = _deviceItems[existingDeviceIndex];
    _deviceItems.removeAt(existingDeviceIndex);
    notifyListeners();
    final response = await http.delete(url, headers: {
      "Content-Type": "application/json",
      "Accept": "application/json",
      "Authorization": authToken
    });
    logger.d("Status code ->>>>>" + response.statusCode.toString());
    if (response.statusCode >= 400) {
      _deviceItems.insert(existingDeviceIndex, existingDevice);
      notifyListeners();
      logger.d("Status code 400 ->>>>> throwing");
      throw HttpException('Could not delete device.');
    }
    existingDevice = null;
  }
}
