import 'package:flutter/foundation.dart';
import 'package:logger/logger.dart';

class CartItem {
  final String id;
  final String prodId;
  final String imageUrl;
  final String title;
  final int quantity;
  final double price;

  CartItem({
    @required this.id,
    @required this.prodId,
    @required this.imageUrl,
    @required this.title,
    @required this.quantity,
    @required this.price,
  });
}

class Cart with ChangeNotifier {
  var logger = Logger();
  Map<String, CartItem> _items = {};

  Map<String, CartItem> get items {
    return {..._items};
  }

  int get itemCount {
    return _items.length;
  }

  int findQuantity(String productId) {
    if (_items.containsKey(productId)) {
      return _items[productId].quantity;
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

  void editQuantityBy(String productId, int editBy) {
    if (_items.containsKey(productId)) {
      // change quantity...
      _items.update(
        productId,
        (existingCartItem) => CartItem(
          id: existingCartItem.id,
          imageUrl: existingCartItem.imageUrl,
          title: existingCartItem.title,
          price: existingCartItem.price,
          quantity: existingCartItem.quantity + editBy,
        ),
      );
    }
    notifyListeners();
  }

  void addItem(
    String productId,
    String imageUrl,
    double price,
    String title,
  ) {
    if (_items.containsKey(productId)) {
      // change quantity...
      _items.update(
        productId,
        (existingCartItem) => CartItem(
          id: existingCartItem.id,
          prodId: existingCartItem.prodId,
          imageUrl: existingCartItem.imageUrl,
          title: existingCartItem.title,
          price: existingCartItem.price,
          quantity: existingCartItem.quantity + 1,
        ),
      );
    } else {
      _items.putIfAbsent(
        productId,
        () => CartItem(
          id: DateTime.now().toString(),
          prodId: productId,
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

  void removeItem(String productId) {
    _items.remove(productId);
    notifyListeners();
  }

  void clear() {
    _items = {};
    notifyListeners();
  }

  void removeSingleItem(String productId) {
    if (!_items.containsKey(productId)) {
      return;
    }
    if (_items[productId].quantity > 1) {
      _items.update(
          productId,
          (existingCartItem) => CartItem(
                id: existingCartItem.id,
                prodId: existingCartItem.prodId,
                title: existingCartItem.title,
                price: existingCartItem.price,
                imageUrl: existingCartItem.imageUrl,
                quantity: existingCartItem.quantity - 1,
              ));
    } else {
      _items.remove(productId);
    }
    logger.d('remove item called');
    notifyListeners();
  }
}
