import 'package:flutter/material.dart';
import 'package:logger/logger.dart';
import 'package:provider/provider.dart';

import '../providers/device.dart';
import '../providers/devices.dart';

class EditDevicescreen extends StatefulWidget {
  static const routeName = '/edit-device';

  @override
  _EditDevicescreenState createState() => _EditDevicescreenState();
}

class _EditDevicescreenState extends State<EditDevicescreen> {
  var logger = Logger();
  final _onOffFocusNode = FocusNode();
  final _descriptionFocusNode = FocusNode();
  final _imageUrlController = TextEditingController();
  final _imageUrlFocusNode = FocusNode();
  final _form = GlobalKey<FormState>();
  var _editedDevice = Device(
    deviceId: null,
    title: '',
    onOffState: 0,
    description: '',
    imageUrl: '',
  );
  var _initValues = {
    'title': '',
    'description': '',
    'onOffState': '',
    'imageUrl': '',
  };
  var _isInit = true;
  var _isLoading = false;

  @override
  void initState() {
    _imageUrlFocusNode.addListener(_updateImageUrl);
    super.initState();
  }

  @override
  void didChangeDependencies() {
    if (_isInit) {
      final deviceId = ModalRoute.of(context).settings.arguments as String;
      if (deviceId != null) {
        _editedDevice = Provider.of<Devices>(context, listen: false)
            .findDeviceById(deviceId);
        _initValues = {
          'title': _editedDevice.title,
          'description': _editedDevice.description,
          'onOffState': _editedDevice.onOffState.toString(),
          // 'imageUrl': _editeddevice.imageUrl,
          'imageUrl': '',
        };
        _imageUrlController.text = _editedDevice.imageUrl;
      }
    }
    _isInit = false;
    super.didChangeDependencies();
  }

  @override
  void dispose() {
    _imageUrlFocusNode.removeListener(_updateImageUrl);
    _onOffFocusNode.dispose();
    _descriptionFocusNode.dispose();
    _imageUrlController.dispose();
    _imageUrlFocusNode.dispose();
    super.dispose();
  }

  void _updateImageUrl() {
    if (!_imageUrlFocusNode.hasFocus) {
      if ((!_imageUrlController.text.startsWith('http') &&
              !_imageUrlController.text.startsWith('https')) ||
          (!_imageUrlController.text.endsWith('.png') &&
              !_imageUrlController.text.endsWith('.jpg') &&
              !_imageUrlController.text.endsWith('.jpeg'))) {
        return;
      }
      setState(() {});
    }
  }

  Future<void> _saveForm() async {
    final isValid = _form.currentState.validate();
    if (!isValid) {
      return;
    }

    _form.currentState.save();
    setState(() {
      _isLoading = true;
    });
    if (_editedDevice.deviceId != null) {
      await Provider.of<Devices>(context, listen: false)
          .updateDevice(_editedDevice.deviceId, _editedDevice);
    } else {
      try {
        await Provider.of<Devices>(context, listen: false)
            .addDevice(_editedDevice);
      } catch (error) {
        logger.d('catch error ->>> ' + error.toString());
        await showDialog(
          context: context,
          builder: (ctx) => AlertDialog(
            title: Text('An error occurred!'),
            content: Text('Something went wrong.'),
            actions: <Widget>[
              FlatButton(
                child: Text('Okay'),
                onPressed: () {
                  Navigator.of(ctx).pop();
                },
              )
            ],
          ),
        );
      }
    }
    setState(() {
      _isLoading = false;
    });
    Navigator.of(context).pop();
    // Navigator.of(context).pop();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Edit Device'),
        actions: <Widget>[
          IconButton(
            icon: Icon(Icons.save),
            onPressed: _saveForm,
          ),
        ],
      ),
      body: _isLoading
          ? Center(
              child: CircularProgressIndicator(),
            )
          : Padding(
              padding: const EdgeInsets.all(16.0),
              child: Form(
                key: _form,
                child: ListView(
                  children: <Widget>[
                    TextFormField(
                      initialValue: _initValues['title'],
                      decoration: InputDecoration(labelText: 'Title'),
                      textInputAction: TextInputAction.next,
                      onFieldSubmitted: (_) {
                        FocusScope.of(context).requestFocus(_onOffFocusNode);
                      },
                      validator: (value) {
                        if (value.isEmpty) {
                          return 'Please provide a value.';
                        }
                        return null;
                      },
                      onSaved: (value) {
                        _editedDevice = Device(
                            title: value,
                            onOffState: _editedDevice.onOffState,
                            description: _editedDevice.description,
                            imageUrl: _editedDevice.imageUrl,
                            deviceId: _editedDevice.deviceId,
                            isFavorite: _editedDevice.isFavorite);
                      },
                    ),
                    TextFormField(
                      initialValue: _initValues['onOffState'],
                      decoration: InputDecoration(labelText: 'Current State'),
                      textInputAction: TextInputAction.next,
                      keyboardType: TextInputType.number,
                      focusNode: _onOffFocusNode,
                      onFieldSubmitted: (_) {
                        FocusScope.of(context)
                            .requestFocus(_descriptionFocusNode);
                      },
                      validator: (value) {
                        if (value.isEmpty) {
                          return 'Please enter a 0 or 1 for ON/Off.';
                        }
                        if (double.tryParse(value) == null) {
                          return 'Please enter a valid number.';
                        }
                        if (double.parse(value) < 0) {
                          return 'Please enter a number either 0 or 1.';
                        }
                        return null;
                      },
                      onSaved: (value) {
                        _editedDevice = Device(
                            title: _editedDevice.title,
                            onOffState: int.parse(value),
                            description: _editedDevice.description,
                            imageUrl: _editedDevice.imageUrl,
                            deviceId: _editedDevice.deviceId,
                            isFavorite: _editedDevice.isFavorite);
                      },
                    ),
                    TextFormField(
                      initialValue: _initValues['description'],
                      decoration: InputDecoration(labelText: 'Description'),
                      maxLines: 3,
                      keyboardType: TextInputType.multiline,
                      focusNode: _descriptionFocusNode,
                      validator: (value) {
                        if (value.isEmpty) {
                          return 'Please enter a description.';
                        }
                        if (value.length < 10) {
                          return 'Should be at least 10 characters long.';
                        }
                        return null;
                      },
                      onSaved: (value) {
                        _editedDevice = Device(
                          title: _editedDevice.title,
                          onOffState: _editedDevice.onOffState,
                          description: value,
                          imageUrl: _editedDevice.imageUrl,
                          deviceId: _editedDevice.deviceId,
                          isFavorite: _editedDevice.isFavorite,
                        );
                      },
                    ),
                    Row(
                      crossAxisAlignment: CrossAxisAlignment.end,
                      children: <Widget>[
                        Container(
                          width: 100,
                          height: 100,
                          margin: EdgeInsets.only(
                            top: 8,
                            right: 10,
                          ),
                          decoration: BoxDecoration(
                            border: Border.all(
                              width: 1,
                              color: Colors.grey,
                            ),
                          ),
                          child: _imageUrlController.text.isEmpty
                              ? Text('Enter a URL')
                              : FittedBox(
                                  child: Image.network(
                                    _imageUrlController.text,
                                    fit: BoxFit.cover,
                                  ),
                                ),
                        ),
                        Expanded(
                          child: TextFormField(
                            decoration: InputDecoration(labelText: 'Image URL'),
                            keyboardType: TextInputType.url,
                            textInputAction: TextInputAction.done,
                            controller: _imageUrlController,
                            focusNode: _imageUrlFocusNode,
                            onFieldSubmitted: (_) {
                              _saveForm();
                            },
                            validator: (value) {
                              if (value.isEmpty) {
                                return 'Please enter an image URL.';
                              }
                              if (!value.startsWith('http') &&
                                  !value.startsWith('https')) {
                                return 'Please enter a valid URL.';
                              }
                              if (!value.endsWith('.png') &&
                                  !value.endsWith('.jpg') &&
                                  !value.endsWith('.jpeg')) {
                                return 'Please enter a valid image URL.';
                              }
                              return null;
                            },
                            onSaved: (value) {
                              _editedDevice = Device(
                                title: _editedDevice.title,
                                onOffState: _editedDevice.onOffState,
                                description: _editedDevice.description,
                                imageUrl: value,
                                deviceId: _editedDevice.deviceId,
                                isFavorite: _editedDevice.isFavorite,
                              );
                            },
                          ),
                        ),
                      ],
                    ),
                  ],
                ),
              ),
            ),
    );
  }
}
