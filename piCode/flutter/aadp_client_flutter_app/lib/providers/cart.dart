import 'dart:convert';

import 'package:flutter/foundation.dart';
import 'package:http/http.dart' as http;
import 'package:logger/logger.dart';

class CartItem {
  final String id;
  final String prodId;
  final String machineSlotId;
  final String imageUrl;
  final String title;
  final int quantity;
  final double price;

  CartItem({
    @required this.id,
    @required this.prodId,
    @required this.machineSlotId,
    @required this.imageUrl,
    @required this.title,
    @required this.quantity,
    @required this.price,
  });
}

class Cart with ChangeNotifier {
  var logger = Logger();
  bool _productAvailStatus = false;
  final String authToken;
  final String userId;
  final String selectedMachineCode;
  Cart(this.authToken, this.userId, this.selectedMachineCode, this._items);

  Map<String, CartItem> _items = {};

  Map<String, CartItem> get items {
    return {..._items};
  }

  int get itemCount {
    return _items.length;
  }

  bool get productAvailStatus {
    return _productAvailStatus;
  }

  int findQuantity(String machineSlotId) {
    if (_items.containsKey(machineSlotId)) {
      return _items[machineSlotId].quantity;
    } else {
      return 0;
    }
  }

  double get totalPrice {
    var total = 0.0;
    _items.forEach((key, cartValue) {
      total += cartValue.price * cartValue.quantity;
    });
    return total;
  }

  Future<void> checkAvailableQty(
      String machineSlotId, String productId, int itemCount) async {
    _productAvailStatus = false;
    final url =
        'http://aadpbackendapi-env.eba-5w95vzgc.ap-south-1.elasticbeanstalk.com/aadp-vend-ws/products/machinecode/$selectedMachineCode/machineSlotId/$machineSlotId/productId/$productId/itemCount/$itemCount';
    try {
      final response = await http.get(
        url,
        headers: {
          "Content-Type": "application/json",
          "Accept": "application/json",
          "Authorization": authToken,
        },
      );
      final extractedData = json.decode(response.body) as Map<String, dynamic>;

      if (extractedData == null) {
        return false;
      }
      extractedData.forEach((key, value) {
        _productAvailStatus = value;
      });
    } catch (error) {
      throw (error);
    }
  }

  void editQuantityBy(String machineSlotId, int editBy) async {
    if (_items.containsKey(machineSlotId)) {
      _items.update(
        machineSlotId,
        (existingCartItem) => CartItem(
          id: existingCartItem.id,
          prodId: existingCartItem.prodId,
          machineSlotId: existingCartItem.machineSlotId,
          imageUrl: existingCartItem.imageUrl,
          title: existingCartItem.title,
          price: existingCartItem.price,
          quantity: existingCartItem.quantity + editBy,
        ),
      );
      notifyListeners();
    }
  }

  void addItem(
    String productId,
    String machineSlotId,
    String imageUrl,
    double price,
    String title,
  ) {
    if (_items.containsKey(machineSlotId)) {
      // change quantity...
      _items.update(
        machineSlotId,
        (existingCartItem) => CartItem(
          id: existingCartItem.id,
          prodId: existingCartItem.prodId,
          machineSlotId: machineSlotId,
          imageUrl: existingCartItem.imageUrl,
          title: existingCartItem.title,
          price: existingCartItem.price,
          quantity: existingCartItem.quantity + 1,
        ),
      );
    } else {
      _items.putIfAbsent(
        machineSlotId,
        () => CartItem(
          id: DateTime.now().toString(),
          prodId: productId,
          machineSlotId: machineSlotId,
          imageUrl: imageUrl,
          title: title,
          price: price,
          quantity: 1,
        ),
      );
      logger.d('cart item added');
    }
    notifyListeners();
  }

  void removeItem(String machineSlotId) {
    _items.remove(machineSlotId);
    notifyListeners();
  }

  void clear() {
    _items = {};
    notifyListeners();
  }

  void removeSingleItem(String machineSlotId) {
    if (!_items.containsKey(machineSlotId)) {
      return;
    }
    if (_items[machineSlotId].quantity > 1) {
      _items.update(
          machineSlotId,
          (existingCartItem) => CartItem(
                id: existingCartItem.id,
                prodId: existingCartItem.prodId,
                title: existingCartItem.title,
                price: existingCartItem.price,
                imageUrl: existingCartItem.imageUrl,
                quantity: existingCartItem.quantity - 1,
              ));
    } else {
      _items.remove(machineSlotId);
    }
    logger.d('remove item called');
    notifyListeners();
  }
}
