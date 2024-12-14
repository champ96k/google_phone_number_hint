import 'package:flutter/material.dart';
import 'package:google_phone_number_hint/google_phone_number_hint.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String? _mobileNumber;

  @override
  void initState() {
    super.initState();
    WidgetsBinding.instance.addPostFrameCallback((timeStamp) {
      _getGoogleMobileNumber();
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      theme: ThemeData(useMaterial3: false),
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Google Phone Number Hint Example'),
        ),
        body: Center(
          child: Text(_mobileNumber ?? 'Loading...'),
        ),
      ),
    );
  }

  void _getGoogleMobileNumber() {
    GooglePhoneNumberHint().getMobileNumber().then((number) {
      setState(() {
        _mobileNumber = number;
      });
    });
  }
}
