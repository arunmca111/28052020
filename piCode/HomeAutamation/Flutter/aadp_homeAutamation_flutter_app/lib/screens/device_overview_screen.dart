import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../providers/devices.dart';
import '../widgets/app_drawer.dart';
import '../widgets/device.grid.dart';

enum FilterOptions {
  Favorites,
  All,
}

class DeviceOverview extends StatefulWidget {
  static const routeName = '/device-overview';
  @override
  _DeviceOverviewScreenState createState() => _DeviceOverviewScreenState();
}

class _DeviceOverviewScreenState extends State<DeviceOverview> {
  var _showOnlyFavorites = false;
  var _isInit = true;
  var _isLoading = false;

  @override
  void initState() {
    super.initState();
  }

  @override
  void didChangeDependencies() {
    if (_isInit) {
      setState(() {
        _isLoading = true;
      });
      Provider.of<Devices>(context).fetchAndSetDevices().then((_) {
        setState(() {
          _isLoading = false;
        });
      });
    }
    _isInit = false;
    super.didChangeDependencies();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Device Control"),
        actions: <Widget>[
          PopupMenuButton(
            onSelected: (FilterOptions selectedValue) {
              setState(() {
                if (selectedValue == FilterOptions.Favorites) {
                  _showOnlyFavorites = true;
                } else {
                  _showOnlyFavorites = false;
                }
              });
            },
            icon: Icon(Icons.more_vert),
            itemBuilder: (_) => [
              PopupMenuItem(
                child: Text('Only Favorites'),
                value: FilterOptions.Favorites,
              ),
              PopupMenuItem(
                child: Text('Show All'),
                value: FilterOptions.All,
              ),
            ],
          ),
        ],
      ),
      drawer: AppDrawer(),
      body: _isLoading
          ? Center(
              child: CircularProgressIndicator(),
            )
          : DevicesGrid(_showOnlyFavorites),
    );
  }
}
