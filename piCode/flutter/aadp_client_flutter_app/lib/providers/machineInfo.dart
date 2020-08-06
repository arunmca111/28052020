import 'dart:convert';

import 'package:flutter/foundation.dart';
import 'package:http/http.dart' as http;
import 'package:logger/logger.dart';

class MachineInfo {
  final String machineCode;
  final String machineDescription;

  MachineInfo({
    @required this.machineCode,
    @required this.machineDescription,
  });
}

class MachinesInfo with ChangeNotifier {
  var logger = Logger();
  List<MachineInfo> _machineInfo = [];
  String _selectedMachine;

  List<MachineInfo> get machineInfo {
    return _machineInfo;
  }

  String get selectedMachine {
    return _selectedMachine;
  }

  set selectedMachine(String selectedMachineId) {
    _selectedMachine = selectedMachineId;
    logger.d("selected machine @@@@-> " + _selectedMachine);
  }

  final String authToken;
  final String userId;
  MachinesInfo(this.authToken, this.userId);

  Future<void> fetchMachineInfo() async {
    _machineInfo = [];
    final url = 'http://10.0.2.2:8081/machine/';
    logger.d("url is ----> " + url);
    final response = await http.get(url, headers: {
      "Content-Type": "application/json",
      "Accept": "application/json",
      "Authorization": authToken,
    });

    final extractedData = json.decode(response.body) as List<dynamic>;
    if (extractedData == null) {
      return null;
    }
    extractedData.forEach((machineData) {
      _machineInfo.add(MachineInfo(
          machineCode: machineData['machineCode'],
          machineDescription: machineData['machineDescription']));
    });
    notifyListeners();
  }
}
