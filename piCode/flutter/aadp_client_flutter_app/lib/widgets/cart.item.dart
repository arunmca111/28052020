import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../providers/cart.dart';
import '../utils/CustomTextStyle.dart';
import '../utils/CustomUtils.dart';
import '../widgets/round_icon_button.dart';

class CartItem extends StatelessWidget {
  final String id;
  final String productId;
  final String machineSlotId;
  final String imageUrl;
  final double price;
  final int quantity;
  final String title;

  CartItem(this.id, this.productId, this.machineSlotId, this.imageUrl,
      this.price, this.quantity, this.title);

  @override
  Widget build(BuildContext context) {
    final scaffold = Scaffold.of(context);
    final cart = Provider.of<Cart>(context, listen: false);

    return Dismissible(
      key: ValueKey(id),
      background: Container(
        color: Theme.of(context).errorColor,
        child: Icon(
          Icons.delete,
          color: Colors.white,
          size: 40,
        ),
        alignment: Alignment.centerRight,
        padding: EdgeInsets.only(right: 20),
        margin: EdgeInsets.symmetric(
          horizontal: 15,
          vertical: 4,
        ),
      ),
      direction: DismissDirection.endToStart,
      confirmDismiss: (direction) {
        return _showDialog(context);
      },
      onDismissed: (direction) {
        Provider.of<Cart>(context, listen: false).removeItem(machineSlotId);
      },
      child: Stack(
        children: <Widget>[
          Container(
            margin: EdgeInsets.only(left: 16, right: 16, top: 16),
            decoration: BoxDecoration(
                color: Colors.white,
                borderRadius: BorderRadius.all(Radius.circular(16))),
            child: Row(
              children: <Widget>[
                Container(
                  margin: EdgeInsets.only(right: 8, left: 8, top: 8, bottom: 8),
                  width: 80,
                  height: 80,
                  decoration: BoxDecoration(
                      borderRadius: BorderRadius.all(Radius.circular(14)),
                      color: Colors.blue.shade200,
                      image: DecorationImage(
                          image: NetworkImage(imageUrl), fit: BoxFit.cover)),
                ),
                Expanded(
                  child: Container(
                    padding: const EdgeInsets.all(8.0),
                    child: Column(
                      mainAxisSize: MainAxisSize.max,
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: <Widget>[
                        Container(
                          padding: EdgeInsets.only(right: 8, top: 4),
                          child: Text(
                            '$machineSlotId - $title',
                            maxLines: 2,
                            softWrap: true,
                            style: CustomTextStyle.textFormFieldSemiBold
                                .copyWith(fontSize: 14),
                          ),
                        ),
                        Utils.getSizedBox(height: 6),
                        Text(
                          '\u20B9' + price.toStringAsFixed(2),
                          style: CustomTextStyle.textFormFieldSemiBold
                              .copyWith(fontSize: 14),
                        ),
                        Container(
                          child: Row(
                            children: <Widget>[
                              /*    RoundIconButton(
                                  icon: Icons.delete,
                                  fillColor: Colors.white,
                                  iconColor: Colors.red,
                                  onPressed: () {
                                    cart.removeItem(productId);
                                  }),*/
                              RoundIconButton(
                                  icon: Icons.remove,
                                  fillColor: Colors.grey.shade200,
                                  iconColor: Colors.black,
                                  onPressed: () async {
                                    if (quantity == 1) {
                                      var condtion = await _showDialog(context);
                                      if (condtion) {
                                        Provider.of<Cart>(context,
                                                listen: false)
                                            .removeItem(machineSlotId);
                                      }
                                    } else {
                                      cart.editQuantityBy(machineSlotId, -1);
                                      SnackBar(
                                        content: Text(
                                          'Product removed',
                                        ),
                                      );
                                    }
                                  }),
                              Container(
                                color: Colors.grey.shade200,
                                padding: const EdgeInsets.only(
                                    top: 2, bottom: 2, right: 12, left: 12),
                                child: Text(
                                  quantity.toString(),
                                  style: CustomTextStyle.textFormFieldSemiBold,
                                ),
                              ),
                              RoundIconButton(
                                  icon: Icons.add,
                                  fillColor: Colors.grey.shade200,
                                  iconColor: Colors.black,
                                  onPressed: () {
                                    var printText = '';
                                    cart
                                        .checkAvailableQty(machineSlotId,
                                            productId, quantity + 1)
                                        .then((_) {
                                      if (cart.productAvailStatus) {
                                        cart.editQuantityBy(machineSlotId, 1);
                                        printText = 'Product added';
                                      } else {
                                        printText = 'Product out of stock';
                                      }

                                      scaffold.showSnackBar(
                                        SnackBar(
                                          content: Text(printText),
                                        ),
                                      );
                                    });
                                  }),
                              Flexible(fit: FlexFit.tight, child: SizedBox()),
                              Padding(
                                padding: const EdgeInsets.all(8.0),
                                child: Row(
                                  mainAxisAlignment: MainAxisAlignment.center,
                                  crossAxisAlignment: CrossAxisAlignment.end,
                                  children: <Widget>[
                                    Text(
                                      '\u20B9${(price * quantity).toStringAsFixed(2)}',
                                      style: CustomTextStyle.textFormFieldBlack
                                          .copyWith(color: Colors.green),
                                    ),
                                  ],
                                ),
                              )
                            ],
                          ),
                        ),
                      ],
                    ),
                  ),
                  flex: 100,
                )
              ],
            ),
          ),
        ],
      ),
    );
  }

  Future<bool> _showDialog(BuildContext ctx) async {
    // flutter defined function
    return showDialog(
      context: ctx,
      builder: (BuildContext context) {
        // return object of type Dialog
        return AlertDialog(
          title: new Text("Are you sure?"),
          content: new Text("Do you want to remove the item from the cart?"),
          actions: <Widget>[
            FlatButton(
              child: Text('No'),
              onPressed: () {
                Navigator.of(ctx).pop(false);
              },
            ),
            FlatButton(
              child: Text('Yes'),
              onPressed: () {
                Navigator.of(ctx).pop(true);
              },
            ),
          ],
        );
      },
    );
  }
}
