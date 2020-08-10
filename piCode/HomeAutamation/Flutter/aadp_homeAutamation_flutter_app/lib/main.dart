import 'package:flutter/material.dart';
import 'package:flutter_complete_guide/screens/splash_screen.dart';
import 'package:provider/provider.dart';

import './providers/auth.dart';
import './providers/devices.dart';
import './screens/auth_screen.dart';
import './screens/device_detail_screen.dart';
import './screens/device_overview_screen.dart';
import './screens/edit_device_screen.dart';
import './screens/user_devices_screen.dart';

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
        ChangeNotifierProxyProvider<Auth, Devices>(
          update: (ctx, auth, previousProducts) => Devices(
            auth.token,
            auth.userId,
            previousProducts == null ? [] : previousProducts.deviceItems,
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
            home: auth.isAuth
                ? DeviceOverview()
                : FutureBuilder(
                    future: auth.tryAutoLogin(),
                    builder: (ctx, authResultSnapshot) =>
                        authResultSnapshot.connectionState ==
                                ConnectionState.waiting
                            ? SplashScreen()
                            : AuthScreen(),
                  ),
            routes: {
              UserProductsScreen.routeName: (ctx) => UserProductsScreen(),
              EditDevicescreen.routeName: (ctx) => EditDevicescreen(),
              DeviceOverview.routeName: (ctx) => DeviceOverview(),
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
