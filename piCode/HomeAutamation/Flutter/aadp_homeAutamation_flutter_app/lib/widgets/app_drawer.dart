import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../providers/auth.dart';
import '../screens/device_overview_screen.dart';
import '../screens/user_devices_screen.dart';

class AppDrawer extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Drawer(
        child: Column(
      children: <Widget>[
        AppBar(
          title: Text('Hello Friend!'),
          automaticallyImplyLeading: false,
        ),
        Divider(),
        ListTile(
          leading: Icon(Icons.shop),
          title: Text('Device'),
          onTap: () {
            Navigator.of(context)
                .pushReplacementNamed(DeviceOverview.routeName);
          },
        ),
        Divider(),
        ListTile(
          leading: Icon(Icons.edit),
          title: Text('Manage Device'),
          onTap: () {
            Navigator.of(context)
                .pushReplacementNamed(UserProductsScreen.routeName);
          },
        ),
        Divider(),
        ListTile(
          leading: Icon(Icons.exit_to_app),
          title: Text('Logout'),
          onTap: () {
            Navigator.of(context).pop();
            Navigator.of(context).pushReplacementNamed('/');

            // Navigator.of(context)
            //     .pushReplacementNamed(UserProductsScreen.routeName);
            Provider.of<Auth>(context, listen: false).logout();
          },
        ),
      ],
    ));
  }
}
