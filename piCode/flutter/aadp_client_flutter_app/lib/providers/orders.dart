import 'dart:convert';
import 'dart:io';

import 'package:flutter/foundation.dart';
import 'package:http/http.dart' as http;
import 'package:logger/logger.dart';

import './cart.dart';

class OrderItem {
  final String id;
  final double amount;
  final List<CartItem> products;
  final DateTime dateTime;

  OrderItem({
    @required this.id,
    @required this.amount,
    @required this.products,
    @required this.dateTime,
  });
}

class Orders with ChangeNotifier {
  var logger = Logger();
  List<OrderItem> _orders = [];

  List<OrderItem> get orders {
    return [..._orders];
  }

  final String authToken;

  Orders(this.authToken, this._orders);

  Future<void> addOrder(List<CartItem> cartProducts, double total) async {
    const url = 'http://10.0.2.2:8081/orders/order';
    final timeStamp = DateTime.now();

    try {
      final response = await http.post(
        url,
        headers: {
          "Content-Type": "application/json",
          "Accept": "application/json",
          "Authorization": authToken,
        },
        body: json.encode({
          'ordersId': '',
          'amount': total,
          'dateTime': timeStamp.toIso8601String(),
          'cartItems': cartProducts
              .map((cp) => {
                    'cartId': '',
                    'title': cp.title,
                    'quantity': cp.quantity,
                    'price': cp.price,
                  })
              .toList(),
        }),
      );

      logger.d("http status code is ->>>" + response.statusCode.toString());
      if (response.statusCode == 200) {
      } else if (response.statusCode >= 400) {
        throw HttpException('Could not save product.');
      }
      // _items.insert(0, newProduct); // at the start of the list

      //---------------------------------------------------
      _orders.insert(
        0,
        OrderItem(
          id: json.decode(response.body)['ordersId'],
          amount: total,
          dateTime: timeStamp,
          products: cartProducts,
        ),
      );
      logger.d('order added');
      notifyListeners();
    } catch (error) {
      print(error);
      throw HttpException('Could not save product.');
    }
  }

  Future<void> fetchAndSetOrders() async {
    const url = 'http://10.0.2.2:8081/orders/';
    final response = await http.get(url, headers: {
      "Content-Type": "application/json",
      "Accept": "application/json",
      "Authorization": authToken,
    });
    final List<OrderItem> loadedOrders = [];
    final extractedData = json.decode(response.body) as List<dynamic>;
    if (extractedData == null) {
      return;
    }
    extractedData.forEach((orderData) {
      loadedOrders.add(
        OrderItem(
          id: orderData['ordersId'],
          amount: orderData['amount'],
          dateTime: DateTime.parse(orderData['dateTime']),
          products: (orderData['cartItems'] as List<dynamic>)
              .map(
                (item) => CartItem(
                  id: item['cartId'],
                  price: item['price'],
                  quantity: item['quantity'],
                  title: item['title'],
                ),
              )
              .toList(),
        ),
      );
    });
    _orders = loadedOrders.reversed.toList();
    notifyListeners();
  }
}
