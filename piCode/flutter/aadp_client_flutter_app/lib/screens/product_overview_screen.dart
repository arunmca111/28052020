import 'package:flutter/material.dart';

import '../widgets/product.grid.dart';

class ProductOverview extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Vend-'O'-Vend"),
      ),
      body: ProductsGrid(),
    );
  }
}
