import 'package:flutter/material.dart';
import 'package:flutter_complete_guide/providers/machineInfo.dart';
import 'package:flutter_complete_guide/providers/paymentsInfo.dart';
import 'package:flutter_complete_guide/screens/machine_entry_screen.dart';
import 'package:flutter_complete_guide/screens/payment_screen.dart';
import 'package:flutter_complete_guide/screens/product_detail_screen.dart';
import 'package:flutter_complete_guide/screens/product_overview_screen.dart';
import 'package:provider/provider.dart';

import './providers/auth.dart';
import './providers/cart.dart';
import './providers/orders.dart';
import './providers/products.dart';
import './screens/auth_screen.dart';
import './screens/cart_screen.dart';
import './screens/edit_product_screen.dart';
import './screens/orders_screen.dart';
import './screens/user_products_screen.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    MaterialColor colorCustom = MaterialColor(0xFF4267B2, color);
    return MultiProvider(
      providers: [
        ChangeNotifierProvider.value(
          value: Auth(),
        ),
        ChangeNotifierProxyProvider<Auth, MachinesInfo>(
          update: (ctx, auth, previousOrders) => MachinesInfo(
            auth.token,
            auth.userId,
          ),
        ),
        ChangeNotifierProxyProvider2<Auth, MachinesInfo, Orders>(
          update: (ctx, auth, mac, previousOrders) => Orders(
            auth.token,
            auth.userId,
            mac.selectedMachine,
            previousOrders == null ? [] : previousOrders.orders,
          ),
        ),
        ChangeNotifierProxyProvider2<Auth, MachinesInfo, Products>(
          update: (ctx, auth, mac, previousProducts) => Products(
            auth.token,
            auth.userId,
            mac.selectedMachine,
            previousProducts == null ? [] : previousProducts.productItems,
          ),
        ),
        ChangeNotifierProxyProvider2<Auth, MachinesInfo, Cart>(
          update: (ctx, auth, mac, previousCarts) => Cart(
            auth.token,
            auth.userId,
            mac.selectedMachine,
            previousCarts == null ? {} : previousCarts.items,
          ),
        ),
        ChangeNotifierProxyProvider<Auth, PaymentsInfo>(
          update: (ctx, auth, previousPayment) => PaymentsInfo(
            auth.token,
            auth.userId,
          ),
        ),
      ],
      child: Consumer<Auth>(
        builder: (context, auth, _) => MaterialApp(
            title: 'AADPVendingApp',
            theme: ThemeData(
                primarySwatch: colorCustom,
                accentColor: Colors.deepOrange,
                fontFamily: 'Lato'),
            home: auth.isAuth ? MachineEntry() : AuthScreen(),
            routes: {
              MachineEntry.routeName: (ctx) => MachineEntry(),
              CartScreen.cartRouteName: (ctx) => CartScreen(),
              OrdersScreen.routeName: (ctx) => OrdersScreen(),
              UserProductsScreen.routeName: (ctx) => UserProductsScreen(),
              EditProductScreen.routeName: (ctx) => EditProductScreen(),
              PaymentScreen.paymentRouteName: (ctx) => PaymentScreen(),
              ProductOverview.routeName: (ctx) => ProductOverview(),
              ProductDetailScreen.routeName: (ctx) => ProductDetailScreen(),
            }),
      ),
    );
  }
}

Map<int, Color> color = {
  50: Color.fromRGBO(66, 103, 178, .1),
  100: Color.fromRGBO(66, 103, 178, .2),
  200: Color.fromRGBO(66, 103, 178, .3),
  300: Color.fromRGBO(66, 103, 178, .4),
  400: Color.fromRGBO(66, 103, 178, .5),
  500: Color.fromRGBO(66, 103, 178, .6),
  600: Color.fromRGBO(66, 103, 178, .7),
  700: Color.fromRGBO(66, 103, 178, .8),
  800: Color.fromRGBO(66, 103, 178, .9),
  900: Color.fromRGBO(66, 103, 178, 1),
};
