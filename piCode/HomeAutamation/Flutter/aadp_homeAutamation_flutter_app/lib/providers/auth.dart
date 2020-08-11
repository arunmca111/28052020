import 'dart:async';
import 'dart:convert';

import 'package:flutter/widgets.dart';
import 'package:http/http.dart' as http;
import 'package:logger/logger.dart';
import 'package:shared_preferences/shared_preferences.dart';

import '../models/http_exception.dart';

enum AuthMode { Signup, Login }

class Auth with ChangeNotifier {
  var logger = Logger();
  Timer _authTimer;
  String _token;
  DateTime _expiryDate;
  String _userId;

  bool get isAuth {
    return token != null;
  }

  String get userId {
    return _userId;
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
      _autoLogout();
      notifyListeners();
      final prefs = await SharedPreferences.getInstance();
      final userData = json.encode(
        {
          'token': _token,
          'userId': _userId,
          'expiryDate': _expiryDate.toIso8601String(),
        },
      );
      prefs.setString('userData', userData);
    } catch (error) {
      throw error;
    }
  }

  Future<void> signup(String email, String password, String firstName,
      String lastName, String phoneNumber) async {
    final url =
        'http://aadphomeautomation-env.eba-hyr5pkk4.ap-south-1.elasticbeanstalk.com/aadp-vend-ws/users/user';
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
    final url =
        'http://aadphomeautomation-env.eba-hyr5pkk4.ap-south-1.elasticbeanstalk.com/aadp-vend-ws/users/login';
    var bodyRequest = json.encode({
      'email': email,
      'password': password,
    });

    return _authenticate(url, bodyRequest, AuthMode.Login);
  }

  Future<bool> tryAutoLogin() async {
    final prefs = await SharedPreferences.getInstance();
    if (!prefs.containsKey('userData')) {
      return false;
    }
    final extractedUserData =
        json.decode(prefs.getString('userData')) as Map<String, Object>;
    final expiryDate = DateTime.parse(extractedUserData['expiryDate']);

    if (expiryDate.isBefore(DateTime.now())) {
      return false;
    }
    _token = extractedUserData['token'];
    _userId = extractedUserData['userId'];
    _expiryDate = expiryDate;
    notifyListeners();
    _autoLogout();
    return true;
  }

  Future<void> logout() async {
    _token = null;
    _userId = null;
    _expiryDate = null;
    if (_authTimer != null) {
      _authTimer.cancel();
      _authTimer = null;
    }
    notifyListeners();
    final prefs = await SharedPreferences.getInstance();
    // prefs.remove('userData');
    prefs.clear();
  }

  void _autoLogout() {
    if (_authTimer != null) {
      _authTimer.cancel();
    }
    final timeToExpiry = _expiryDate.difference(DateTime.now()).inSeconds;
    _authTimer = Timer(Duration(seconds: timeToExpiry), logout);
  }
}
