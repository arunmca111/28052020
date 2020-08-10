import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../providers/auth.dart';
import '../providers/device.dart';
import '../screens/device_detail_screen.dart';

class DevicesItem extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final device = Provider.of<Device>(context, listen: false);
    final authData = Provider.of<Auth>(context, listen: false);
    return ClipRRect(
      borderRadius: BorderRadius.circular(10),
      child: GridTile(
        child: GestureDetector(
          onTap: () {
            Navigator.of(context).pushNamed(
              ProductDetailScreen.routeName,
              arguments: device.deviceId,
            );
          },
          child: Image.network(
            device.imageUrl,
            fit: BoxFit.cover,
          ),
        ),
        footer: GridTileBar(
          backgroundColor: Colors.black87,
          leading: Consumer<Device>(
            builder: (ctx, device, _) => IconButton(
              icon: Icon(
                device.onOffState == 0 ? Icons.favorite : Icons.favorite_border,
              ),
              color: Theme.of(context).accentColor,
              onPressed: () {
                print('came 1');
                device.toggleFavoriteStatus(authData.token, device.onOffState);
              },
            ),
          ),
          title: Text(
            device.title,
            textAlign: TextAlign.center,
          ),
        ),
      ),
    );
  }
}
