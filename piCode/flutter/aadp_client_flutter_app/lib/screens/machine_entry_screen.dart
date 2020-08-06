import 'package:flutter/material.dart';
import 'package:flutter_complete_guide/providers/input.dart';
import 'package:flutter_complete_guide/providers/machineInfo.dart';
import 'package:flutter_complete_guide/screens/product_overview_screen.dart';
import 'package:provider/provider.dart';
import 'package:searchable_dropdown/searchable_dropdown.dart';

class MachineEntry extends StatefulWidget {
  static const routeName = '/machine';

  @override
  _MachineEntryState createState() => new _MachineEntryState();
}

class _MachineEntryState extends State<MachineEntry> {
  final GlobalKey<ScaffoldState> _scaffoldKey = new GlobalKey<ScaffoldState>();
  String selectedValue;
  String preselectedValue = "dolor sit";
  ExampleNumber selectedNumber;
  List<int> selectedItems = [];
  final List<DropdownMenuItem> items = [];
  List<MachineInfo> machineInfoList = [];
  var _isInit = true;
  var _isLoading = false;

  static const String appTitle = "Select the Machine";

  @override
  void initState() {
    print("init called ->");
    super.initState();
  }

  void updateSeletedMachine() {
    Provider.of<MachinesInfo>(context, listen: false).selectedMachine =
        selectedValue;
  }

  void didChangeDependencies() {
    if (_isInit) {
      setState(() {
        _isLoading = true;
      });
      Provider.of<MachinesInfo>(context).fetchMachineInfo().then((_) {
        setState(() {
          _isLoading = false;
          machineInfoList =
              Provider.of<MachinesInfo>(context, listen: false).machineInfo;
          print("data list is ->" + machineInfoList.toString());
          machineInfoList.forEach((element) {
            items.add(DropdownMenuItem(
              child: Text(element.machineCode),
              value: element.machineCode,
            ));
          });
        });
      });
    }
    _isInit = false;
    super.didChangeDependencies();
  }

  void _showScaffold(String message) {
    _scaffoldKey.currentState.showSnackBar(SnackBar(
      content: Text(message),
    ));
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
        doneButton: "Done",
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
        key: _scaffoldKey,
        appBar: AppBar(
          title: const Text(appTitle),
        ),
        body: _isLoading
            ? Center(
                child: CircularProgressIndicator(),
              )
            : SingleChildScrollView(
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
                                          RaisedButton.icon(
                                            onPressed: () {
                                              if (selectedValue == null) {
                                                _showScaffold(
                                                    "Select a Machine");
                                                return;
                                              }
                                              updateSeletedMachine();
                                              Navigator.of(context).pushNamed(
                                                ProductOverview.routeName,
                                              );
                                            },
                                            shape: RoundedRectangleBorder(
                                                borderRadius: BorderRadius.all(
                                                    Radius.circular(10.0))),
                                            label: Text(
                                              'Next',
                                              style: TextStyle(
                                                  color: Colors.white),
                                            ),
                                            icon: Icon(
                                              Icons.arrow_right,
                                              color: Colors.white,
                                            ),
                                            textColor: Colors.white,
                                            splashColor: Colors.red,
                                            color:
                                                Theme.of(context).primaryColor,
                                          ),
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
