import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../providers/auth.dart';
import '../providers/cart.dart';
import '../providers/product.dart';
import '../screens/product_detail_screen.dart';

class ProductsItem extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final product = Provider.of<Product>(context, listen: false);
    final cart = Provider.of<Cart>(context, listen: false);
    final authData = Provider.of<Auth>(context, listen: false);
    return ClipRRect(
      borderRadius: BorderRadius.circular(10),
      child: GridTile(
        child: GestureDetector(
          onTap: () {
            Navigator.of(context).pushNamed(
              ProductDetailScreen.routeName,
              arguments: product.productId,
            );
          },
          child: Image.network(
            product.imageUrl,
            fit: BoxFit.cover,
          ),
        ),
        footer: GridTileBar(
          backgroundColor: Colors.black87,
          leading: Consumer<Product>(
            builder: (ctx, product, _) => IconButton(
              icon: Icon(
                product.isFavorite ? Icons.favorite : Icons.favorite_border,
              ),
              color: Theme.of(context).accentColor,
              onPressed: () {
                product.toggleFavoriteStatus(authData.token);
              },
            ),
          ),
          title: Text(
            '\u20B9' + product.price.toString(),
            textAlign: TextAlign.center,
          ),
          trailing: IconButton(
            icon: Icon(
              Icons.shopping_cart,
            ),
            onPressed: () {
              var printText = '';
              cart
                  .checkAvailableQty(product.machineSlotId, product.productId,
                      cart.findQuantity(product.machineSlotId) + 1)
                  .then((_) {
                if (cart.productAvailStatus) {
                  cart.editQuantityBy(product.machineSlotId, 1);
                  printText = 'Added item to cart!';
                } else {
                  printText = 'Product out of stock';
                }
                if (cart.productAvailStatus) {
                  cart.addItem(product.productId, product.machineSlotId,
                      product.imageUrl, product.price, product.title);
                }
                Scaffold.of(context).hideCurrentSnackBar();
                Scaffold.of(context).showSnackBar(
                  SnackBar(
                    content: Text(
                      printText,
                    ),
                    duration: Duration(seconds: 1),
                    action: SnackBarAction(
                      label: 'UNDO',
                      onPressed: () {
                        cart.removeSingleItem(product.machineSlotId);
                      },
                    ),
                  ),
                );
              });
            },
            color: Theme.of(context).accentColor,
          ),
        ),
      ),
    );
  }
}
