import 'dart:convert';

import 'package:flutter/widgets.dart';
import 'package:http/http.dart' as http;
import 'package:logger/logger.dart';

import '../models/http_exception.dart';

enum AuthMode { Signup, Login }

class Auth with ChangeNotifier {
  var logger = Logger();

  String _token;
  DateTime _expiryDate;
  String _userId;

  bool get isAuth {
    return token != null;
  }

  String get token {
    if (_expiryDate != null &&
        _expiryDate.isAfter(DateTime.now()) &&
        _token != null) {
      return _token;
    }
    return null;
  }

  Future<void> _authenticate(
      String url, String bodyRequest, AuthMode operation) async {
    try {
      final response = await http.post(
        url,
        headers: {
          "Content-Type": "application/json",
          "Accept": "application/json",
        },
        body: bodyRequest,
      );
      logger.d('@@status code->>> ' + response.statusCode.toString());

      if (response.statusCode >= 400) {
        if (json.decode(response.body) != null) {
          throw HttpException(json.decode(response.body)['message']);
        } else {
          throw HttpException('Some Error');
        }
      }
      if (operation == AuthMode.Login) {
        _token = response.headers['authorization'];
        _userId = response.headers['userid'];
        _expiryDate = DateTime.now().add(
          Duration(
            milliseconds: int.parse(
              response.headers['expiresin'],
            ),
          ),
        );
        logger.d('@@status code->>> ' + response.statusCode.toString());
        logger.d('@@_token code->>> ' + _token);
        logger.d('@@_userId code->>> ' + _userId);
        logger.d('@@_expiryDate code->>> ' + _expiryDate.toIso8601String());
      }
      notifyListeners();
    } catch (error) {
      throw error;
    }
  }

  Future<void> signup(String email, String password, String firstName,
      String lastName, String phoneNumber) async {
    final url = 'http://10.0.2.2:8081/users/human';
    var bodyRequest = json.encode({
      'email': email,
      'password': password,
      'firstName': firstName,
      'lastName': lastName,
      'phoneNumber': phoneNumber,
    });

    return _authenticate(url, bodyRequest, AuthMode.Signup);
  }

  Future<void> login(String email, String password) async {
    final url = 'http://10.0.2.2:8081/users/login';
    var bodyRequest = json.encode({
      'email': email,
      'password': password,
    });

    return _authenticate(url, bodyRequest, AuthMode.Login);
  }
}
