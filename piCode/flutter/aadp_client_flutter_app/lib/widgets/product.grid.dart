import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../providers/products.dart';
import '../widgets/products.item.dart';

class ProductsGrid extends StatelessWidget {
  final bool showFavs;

  ProductsGrid(this.showFavs);

  @override
  Widget build(BuildContext context) {
    final productsData = Provider.of<Products>(context);

    final productsList =
        showFavs ? productsData.favoriteItems : productsData.productItems;
    return GridView.builder(
      padding: const EdgeInsets.all(10.0),
      itemCount: productsList.length,
      itemBuilder: (ctx, i) => ChangeNotifierProvider.value(
        value: productsList[i],
        child: ProductsItem(),
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
