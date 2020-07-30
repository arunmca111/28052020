import 'package:flutter/material.dart';
import 'package:flutter_complete_guide/providers/input.dart';
import 'package:flutter_complete_guide/providers/machineInfo.dart';
import 'package:provider/provider.dart';
import 'package:searchable_dropdown/searchable_dropdown.dart';

class MachineEntry extends StatefulWidget {
  @override
  _MachineEntryState createState() => new _MachineEntryState();
}

class _MachineEntryState extends State<MachineEntry> {
  String selectedValue;
  String preselectedValue = "dolor sit";
  ExampleNumber selectedNumber;
  List<int> selectedItems = [];
  final List<DropdownMenuItem> items = [];
  List<MachineInfo> machineInfoList = [];

  static const String appTitle = "Select the Machine";

  @override
  void initState() {
    String wordPair = "";

    Future.delayed(Duration.zero).then((_) async {
      machineInfoList = await Provider.of<MachinesInfo>(context, listen: false)
          .fetchMachineInfo();
    });

    machineInfoList.forEach((element) {
      items.add(DropdownMenuItem(
        child: Text(element.machineCode),
        value: element.machineCode,
      ));
    });

    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    /*machineInfoList = Provider.of<MachinesInfo>(context).machineInfo;*/
    Map<String, Widget> widgets;
    widgets = {
      "Select Machine": SearchableDropdown.single(
        items: items,
        value: selectedValue,
        hint: "Select one",
        searchHint: "",
        onChanged: (value) {
          setState(() {
            selectedValue = value;
          });
        },
        closeButton: null,
        doneButton: "Select",
        displayItem: (item, selected) {
          return (Row(children: [
            selected
                ? Icon(
                    Icons.radio_button_checked,
                    color: Theme.of(context).primaryColor,
                  )
                : Icon(
                    Icons.radio_button_unchecked,
                    color: Colors.grey,
                  ),
            SizedBox(width: 7),
            Expanded(
              child: item,
            ),
          ]));
        },
        isExpanded: true,
      ),
    };

    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text(appTitle),
        ),
        body: SingleChildScrollView(
          scrollDirection: Axis.vertical,
          child: Column(
            children: widgets
                .map((k, v) {
                  return (MapEntry(
                      k,
                      Center(
                          child: Card(
                              shape: RoundedRectangleBorder(
                                borderRadius: BorderRadius.circular(10),
                                side: BorderSide(
                                  color: Colors.grey,
                                  width: 1.0,
                                ),
                              ),
                              margin: EdgeInsets.all(20),
                              child: Padding(
                                padding: const EdgeInsets.all(20.0),
                                child: Column(
                                  children: <Widget>[
                                    Text("$k:"),
                                    v,
                                  ],
                                ),
                              )))));
                })
                .values
                .toList(),
          ),
        ),
      ),
    );
  }
}
