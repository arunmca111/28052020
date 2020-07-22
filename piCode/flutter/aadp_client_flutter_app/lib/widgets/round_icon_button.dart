import 'package:flutter/material.dart';

class RoundIconButton extends StatelessWidget {
  RoundIconButton(
      {@required this.icon,
      @required this.onPressed,
      @required this.fillColor,
      @required this.iconColor});

  final IconData icon;
  final Function onPressed;
  final Color fillColor;
  final Color iconColor;

  @override
  Widget build(BuildContext context) {
    return RawMaterialButton(
      elevation: 0.0,
      child: Icon(
        icon,
        color: iconColor,
      ),
      onPressed: onPressed,
      constraints: BoxConstraints.tightFor(
        width: 24.0,
        height: 24.0,
      ),
      shape: CircleBorder(),
      fillColor: fillColor,
    );
  }
}
