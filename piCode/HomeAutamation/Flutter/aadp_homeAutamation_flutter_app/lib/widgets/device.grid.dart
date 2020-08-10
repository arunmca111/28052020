import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../providers/devices.dart';
import '../widgets/device.item.dart';

class DevicesGrid extends StatelessWidget {
  final bool showFavs;

  DevicesGrid(this.showFavs);

  @override
  Widget build(BuildContext context) {
    final deviceData = Provider.of<Devices>(context);

    final devicesList =
        showFavs ? deviceData.favoriteItems : deviceData.deviceItems;
    return GridView.builder(
      padding: const EdgeInsets.all(10.0),
      itemCount: devicesList.length,
      itemBuilder: (ctx, i) => ChangeNotifierProvider.value(
        value: devicesList[i],
        child: DevicesItem(),
      ),
      gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
        crossAxisCount: 2,
        childAspectRatio: 3 / 2,
        crossAxisSpacing: 10,
        mainAxisSpacing: 10,
      ),
    );
  }
}
