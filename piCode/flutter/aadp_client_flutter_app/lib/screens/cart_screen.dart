import 'package:flutter/material.dart';
import 'package:flutter_complete_guide/providers/orders.dart';
import 'package:flutter_complete_guide/providers/paymentsInfo.dart';
import 'package:flutter_complete_guide/screens/payment_screen.dart';
import 'package:provider/provider.dart';

import '../providers/cart.dart' show Cart;
import '../utils/CustomTextStyle.dart';
import '../utils/CustomUtils.dart';
import '../widgets/cart.item.dart';

class CartScreen extends StatelessWidget {
  static const cartRouteName = '/cart-detail';
  @override
  Widget build(BuildContext context) {
    final cart = Provider.of<Cart>(context);

    return Scaffold(
      backgroundColor: Colors.grey.shade100,
      appBar: AppBar(
        title: Text(
          'Your Cart',
        ),
      ),
      body: Column(
        mainAxisAlignment: MainAxisAlignment.end,
        children: <Widget>[
          Expanded(
            child: ListView.builder(
                itemCount: cart.itemCount,
                itemBuilder: (context, index) => CartItem(
                    cart.items.values.toList()[index].id,
                    cart.items.keys.toList()[index],
                    cart.items.values.toList()[index].imageUrl,
                    cart.items.values.toList()[index].price,
                    cart.items.values.toList()[index].quantity,
                    cart.items.values.toList()[index].title)),
          ),
          (cart.itemCount > 0)
              ? Text(" Swipe <- to 🗑 Delete Item")
              : Text("Cart is empty"),
          Card(
            margin: EdgeInsets.all(15),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.center,
              mainAxisAlignment: MainAxisAlignment.end,
              children: <Widget>[
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: <Widget>[
                    Padding(
                      padding: EdgeInsets.only(top: 15),
                      child: Container(
                        margin: EdgeInsets.only(left: 30),
                        child: Text(
                          "Total",
                          style: CustomTextStyle.textFormFieldMedium
                              .copyWith(color: Colors.grey, fontSize: 12),
                        ),
                      ),
                    ),
                    Container(
                      margin: EdgeInsets.only(top: 15, right: 16),
                      child: Text(
                        '\u20B9' + cart.totalPrice.toStringAsFixed(2),
                        style: CustomTextStyle.textFormFieldBlack
                            .copyWith(color: Colors.green),
                      ),
                    ),
                  ],
                ),
                Utils.getSizedBox(height: 8),
                OrderButton(cart: cart),
                Utils.getSizedBox(height: 8),
              ],
            ),
          )
        ],
      ),
    );
  }

  void navigate(BuildContext context) {
    Navigator.of(context).pushNamed(
      CartScreen.cartRouteName,
    );
  }
}

class OrderButton extends StatefulWidget {
  const OrderButton({
    Key key,
    @required this.cart,
  }) : super(key: key);

  final Cart cart;

  @override
  _OrderButtonState createState() => _OrderButtonState();
}

class _OrderButtonState extends State<OrderButton> {
  var _isloading = false;

  // A method that launches the SelectionScreen and awaits the
  // result from Navigator.pop.
  Future<bool> _navigateAndDisplaySelection(BuildContext context) async {
    /*final result = await Navigator.push(
      context,
      MaterialPageRoute(builder: (context) => PaymentScreen()),
    );*/

    final result = await Navigator.of(context).pushNamed(
      PaymentScreen.paymentRouteName,
    );

    if (result == null) {
      Scaffold.of(context)
        ..removeCurrentSnackBar()
        ..showSnackBar(SnackBar(content: Text("Payment Failed")));
      return false;
    } else {
      Scaffold.of(context)
        ..removeCurrentSnackBar()
        ..showSnackBar(SnackBar(content: Text("$result")));
      return true;
    }
  }

  @override
  Widget build(BuildContext context) {
    return RaisedButton(
      onPressed: (widget.cart.totalPrice <= 0 || _isloading)
          ? null
          : () async {
              setState(() {
                _isloading = true;
              });
              await Provider.of<PaymentsInfo>(context, listen: false)
                  .fetchAndSetPaymentInfo();
              var paymentResultStatus =
                  await _navigateAndDisplaySelection(context);
              if (paymentResultStatus) {
                await Provider.of<Orders>(context, listen: false).addOrder(
                  widget.cart.items.values.toList(),
                  widget.cart.totalPrice,
                );
                widget.cart.clear();
              } else {}

              setState(() {
                _isloading = false;
              });

              //Navigator.of(context).pop();
              /* Navigator.push(context,
            new MaterialPageRoute(builder: (context) => null));*/
            },
      color: Theme.of(context).primaryColor,
      padding: EdgeInsets.only(top: 12, left: 60, right: 60, bottom: 12),
      shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.all(Radius.circular(24))),
      child: _isloading
          ? CircularProgressIndicator()
          : Text(
              "Pay",
              style: CustomTextStyle.textFormFieldSemiBold
                  .copyWith(color: Colors.white),
            ),
    );
  }
}
