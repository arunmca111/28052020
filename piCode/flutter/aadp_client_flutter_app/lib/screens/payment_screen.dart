import 'dart:math';

import 'package:flutter/material.dart';
import 'package:flutter_complete_guide/providers/cart.dart';
import 'package:flutter_complete_guide/providers/paymentsInfo.dart';
import 'package:provider/provider.dart';
import 'package:upi_pay/upi_pay.dart';

class PaymentScreen extends StatefulWidget {
  static const paymentRouteName = '/payment-detail';

  @override
  _PaymentScreenState createState() => _PaymentScreenState();
}

class _PaymentScreenState extends State<PaymentScreen> {
  String _upiAddrError;
  PaymentInfo paymentData;
  TextEditingController _upiAddressController = TextEditingController();
  TextEditingController _amountController = TextEditingController();

  Future<List<ApplicationMeta>> _appsFuture;

  @override
  void initState() {
    super.initState();

    _appsFuture = UpiPay.getInstalledUpiApplications();
  }

  @override
  void dispose() {
    _upiAddressController.dispose();
    _amountController.dispose();
    super.dispose();
  }

  Future<void> _openUPIGateway(ApplicationMeta app) async {
    final err = _validateUpiAddress(_upiAddressController.text);
    if (err != null) {
      setState(() {
        _upiAddrError = err;
      });
      return;
    }
    setState(() {
      _upiAddrError = null;
    });

    final transactionRef = Random.secure().nextInt(1 << 32).toString();
    print("Starting transaction with id $transactionRef");

    final tranResult = await UpiPay.initiateTransaction(
      amount: _amountController.text,
      app: app.upiApplication,
      receiverName: paymentData.receiverName,
      receiverUpiAddress: _upiAddressController.text,
      transactionRef: paymentData.transactionRef,
      merchantCode: paymentData.merchantCode,
      transactionNote: paymentData.transactionNote,
    );
    Navigator.pop(context, tranResult);
    print(tranResult);
  }

  @override
  Widget build(BuildContext context) {
    final cart = Provider.of<Cart>(context);
    paymentData = Provider.of<PaymentsInfo>(context).paymentInfo;
    _amountController.text = '\u20B9' + cart.totalPrice.toStringAsFixed(2);
    return Scaffold(
      appBar: AppBar(title: Text('Payments')),
      body: SafeArea(
          child: Container(
        padding: EdgeInsets.symmetric(horizontal: 16),
        child: ListView(
          children: <Widget>[
            if (_upiAddrError != null)
              Container(
                margin: EdgeInsets.only(top: 4, left: 12),
                child: Text(
                  _upiAddrError,
                  style: TextStyle(color: Colors.red),
                ),
              ),
            Container(
              margin: EdgeInsets.only(top: 32),
              child: Row(
                children: <Widget>[
                  Expanded(
                    child: TextField(
                      controller: _amountController,
                      readOnly: true,
                      enabled: false,
                      decoration: InputDecoration(
                        border: OutlineInputBorder(),
                        labelText: 'Total Amount',
                      ),
                    ),
                  ),
                ],
              ),
            ),
            Container(
              margin: EdgeInsets.only(top: 128, bottom: 32),
              child: Column(
                mainAxisSize: MainAxisSize.min,
                children: <Widget>[
                  Container(
                    margin: EdgeInsets.only(bottom: 12),
                    child: Text(
                      'Pay Using Below UPI Apps' + paymentData.receiverName,
                      style: Theme.of(context).textTheme.caption,
                    ),
                  ),
                  FutureBuilder<List<ApplicationMeta>>(
                    future: _appsFuture,
                    builder: (context, snapshot) {
                      if (snapshot.connectionState != ConnectionState.done) {
                        return Container();
                      }

                      return GridView.count(
                        crossAxisCount: 2,
                        shrinkWrap: true,
                        mainAxisSpacing: 8,
                        crossAxisSpacing: 8,
                        childAspectRatio: 1.6,
                        physics: NeverScrollableScrollPhysics(),
                        children: snapshot.data
                            .map((i) => Material(
                                  key: ObjectKey(i.upiApplication),
                                  color: Colors.grey[200],
                                  child: InkWell(
                                    onTap: () => _openUPIGateway(i),
                                    child: Column(
                                      mainAxisSize: MainAxisSize.min,
                                      mainAxisAlignment:
                                          MainAxisAlignment.center,
                                      children: <Widget>[
                                        Image.memory(
                                          i.icon,
                                          width: 64,
                                          height: 64,
                                        ),
                                        Container(
                                          margin: EdgeInsets.only(top: 4),
                                          child: Text(
                                            i.upiApplication.getAppName(),
                                          ),
                                        ),
                                      ],
                                    ),
                                  ),
                                ))
                            .toList(),
                      );
                    },
                  ),
                ],
              ),
            )
          ],
        ),
      )),
    );
  }
}

String _validateUpiAddress(String value) {
  if (value.isEmpty) {
    return 'UPI Address is required.';
  }

  if (!UpiPay.checkIfUpiAddressIsValid(value)) {
    return 'UPI Address is invalid.';
  }

  return null;
}
