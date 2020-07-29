import 'dart:convert';
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:logger/logger.dart';

import './product.dart';

class Products with ChangeNotifier {
  Products(this.authToken, this.userId, this._productItems);

  List<Product> _productItems = [];
  var logger = Logger();

  final String authToken;
  final String userId;

  List<Product> get productItems {
    return [..._productItems];
  }

  List<Product> get favoriteItems {
    return _productItems.where((prodItem) => prodItem.isFavorite).toList();
  }

  Product findProductById(String id) {
    return productItems.firstWhere((element) => element.id == id);
  }

  Future<void> fetchAndSetProducts([bool filterByUser = false]) async {
    final filterString = filterByUser ? "$userId/userProducts" : '';
    final url = 'http://10.0.2.2:8081/products/$filterString';
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
      final List<Product> loadedProducts = [];

      if (extractedData == null) {
        return;
      }
      extractedData.forEach((prodData) {
        loadedProducts.add(Product(
          id: prodData['id'],
          title: prodData['title'],
          description: prodData['description'],
          price: prodData['price'],
          isFavorite: prodData['favorite'],
          imageUrl: prodData['imageUrl'],
        ));
      });
      _productItems = loadedProducts;
      notifyListeners();
    } catch (error) {
      throw (error);
    }
  }

  Future<void> addProduct(Product product) async {
    const url = 'http://10.0.2.2:8081/products/product';
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
          'userId': userId,
          'title': product.title,
          'description': product.description,
          'imageUrl': product.imageUrl,
          'price': product.price,
          'isFavorite': product.isFavorite,
        }),
      );
      final newProduct = Product(
        title: product.title,
        description: product.description,
        price: product.price,
        imageUrl: product.imageUrl,
        id: json.decode(response.body)['id'],
      );
      logger.d("http status code is ->>>" + response.statusCode.toString());
      if (response.statusCode == 200) {
        _productItems.add(newProduct);
        notifyListeners();
      } else if (response.statusCode >= 400) {
        throw HttpException('Could not save product.');
      }
      // _items.insert(0, newProduct); // at the start of the list
      notifyListeners();
    } catch (error) {
      print(error);
      throw HttpException('Could not save product.');
    }
  }

  Future<void> updateProduct(String id, Product newProduct) async {
    final prodIndex = _productItems.indexWhere((prod) => prod.id == id);
    if (prodIndex >= 0) {
      final url = 'http://10.0.2.2:8081/products/$id';
      await http.patch(url,
          headers: {
            "Content-Type": "application/json",
            "Accept": "application/json",
            "Authorization": authToken,
          },
          body: json.encode({
            'title': newProduct.title,
            'description': newProduct.description,
            'imageUrl': newProduct.imageUrl,
            'price': newProduct.price
          }));
      _productItems[prodIndex] = newProduct;
      notifyListeners();
    } else {
      print('...');
    }
  }

  Future<void> deleteProduct(String id) async {
    final url = 'http://10.0.2.2:8081/products/$id';
    final existingProductIndex =
        _productItems.indexWhere((prod) => prod.id == id);
    var existingProduct = _productItems[existingProductIndex];
    _productItems.removeAt(existingProductIndex);
    notifyListeners();
    final response = await http.delete(url, headers: {
      "Content-Type": "application/json",
      "Accept": "application/json",
      "Authorization": authToken
    });
    logger.d("Status code ->>>>>" + response.statusCode.toString());
    if (response.statusCode >= 400) {
      _productItems.insert(existingProductIndex, existingProduct);
      notifyListeners();
      logger.d("Status code 400 ->>>>> throwing");
      throw HttpException('Could not delete product.');
    }
    existingProduct = null;
  }
}
