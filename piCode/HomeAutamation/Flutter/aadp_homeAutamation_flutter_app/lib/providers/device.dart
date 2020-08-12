import 'dart:convert';

import 'package:flutter/foundation.dart';
import 'package:http/http.dart' as http;

class Device with ChangeNotifier {
  final String deviceId;

  final String title;
  final String description;
  int onOffState;
  final String imageUrl;
  bool isFavorite;

  Device(
      {@required this.deviceId,
      @required this.title,
      @required this.description,
      @required this.onOffState,
      @required this.imageUrl,
      this.isFavorite = false});

  void _setFavValue(bool newValue) {
    isFavorite = newValue;
    notifyListeners();
  }

  Future<void> toggleFavoriteStatus(
      String userId, String token, int deviceState) async {
    print('came 3 --->');
    final oldStatus = deviceState;
    onOffState = deviceState == 0 ? 1 : 0;
    notifyListeners();
    print('result ' + onOffState.toString());

    final url =
        'http://aadphomeautomation-env.eba-hyr5pkk4.ap-south-1.elasticbeanstalk.com/aadp-vend-ws/device/userId/$userId/deviceId/$deviceId';
    try {
      final response = await http.patch(
        url,
        headers: {
          "Content-Type": "application/json",
          "Accept": "application/json",
          "Authorization": token,
        },
        body: json.encode({
          'onOffState': onOffState,
        }),
      );
    } catch (error) {
      //_setFavValue(oldStatus);
    }
  }
}
