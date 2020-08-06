import 'dart:convert';

import 'package:flutter/foundation.dart';
import 'package:http/http.dart' as http;
import 'package:logger/logger.dart';

class PaymentInfo {
  final String receiverUpiAddress;
  final String receiverName;
  final String merchantCode;
  final String transactionNote;
  final String transactionRef;
  final String resultCallBackurl;

  PaymentInfo({
    @required this.receiverUpiAddress,
    @required this.receiverName,
    @required this.merchantCode,
    @required this.transactionNote,
    @required this.transactionRef,
    @required this.resultCallBackurl,
  });
}

class PaymentsInfo with ChangeNotifier {
  var logger = Logger();
  PaymentInfo _paymentInfo;

  PaymentInfo get paymentInfo {
    return _paymentInfo;
  }

  final String authToken;
  final String userId;
  PaymentsInfo(this.authToken, this.userId);

  Future<void> fetchAndSetPaymentInfo() async {
    final url =
        'http://aadpbackendapi-env.eba-5w95vzgc.ap-south-1.elasticbeanstalk.com/aadp-vend-ws/payments/$userId';
    logger.d("url is ----> " + url);
    final response = await http.get(url, headers: {
      "Content-Type": "application/json",
      "Accept": "application/json",
      "Authorization": authToken,
    });

    final extractedData = json.decode(response.body);
    if (extractedData == null) {
      return;
    }

    _paymentInfo = PaymentInfo(
      receiverUpiAddress: extractedData['receiverUpiAddress'],
      receiverName: extractedData['receiverName'],
      merchantCode: extractedData['merchantCode'],
      transactionNote: extractedData['transactionNote'],
      transactionRef: extractedData['transactionRef'],
      resultCallBackurl: extractedData['resultCallBackurl'],
    );
    notifyListeners();
  }
}
