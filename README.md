# Google Phone Number Hint (Android)

A Flutter plugin to easily fetch the user's mobile number using the Google Play Services Phone Number Hint API on Android.

## Features

- Fetches the user's mobile number via the Google Phone Number Hint API.
- Simplifies the process of prompting users to select a phone number linked to their Google account.

## Demo

![Google Phone number hint](https://s7.gifyu.com/images/SJtmQ.gif)

## Supported Platforms

- **Android** (requires Google Play Services).

---

## Installation

Add the following to your `pubspec.yaml`:

```yaml
dependencies:
  google_phone_number_hint: ^1.0.0
```

Run the following command to install the package:

```bash
flutter pub get
```

### Android Setup

Add the following dependency in your `android/app/build.gradle` file:

```gradle
dependencies {
    implementation 'com.google.android.gms:play-services-auth:21.2.0'
}
```

Ensure your `minSdkVersion` is set to **21 or higher** in `android/app/build.gradle`:

```gradle
android {
    defaultConfig {
        minSdkVersion 21
    }
}
```

Make sure to enable AndroidX support in `gradle.properties`:

```properties
android.useAndroidX=true
android.enableJetifier=true
```

---

## Usage

Import the package in your Dart code:

```dart
import 'package:google_phone_number_hint/google_phone_number_hint.dart';
```

### Example

Use the `GooglePhoneNumberHint` plugin to get the user's mobile number:

```dart

import 'package:flutter/material.dart';
import 'package:google_phone_number_hint/google_phone_number_hint.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String? _mobileNumber;

  Future<void> _getMobileNumber() async {
    try {
      final number = await GooglePhoneNumberHint().getMobileNumber();
      setState(() {
        _mobileNumber = number;
      });
    } catch (e) {
      print('Error getting mobile number: $e');
    }
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: Text('Google Phone Number Hint Example')),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Text(_mobileNumber ?? 'No number selected'),
              SizedBox(height: 20),
              ElevatedButton(
                onPressed: _getMobileNumber,
                child: Text('Get Mobile Number'),
              ),
            ],
          ),
        ),
      ),
    );
  }
}

```

### Explanation

- **`GooglePhoneNumberHint().getMobileNumber()`**: Prompts the user to select a phone number from their Google account and returns it as a `String`.

---

## API Reference

### `GooglePhoneNumberHint`

- **`Future<String?> getMobileNumber()`**:
  - Opens the phone number hint prompt and returns the selected mobile number.
  - Returns `null` if no number is selected.

---

## Permissions

No additional permissions are required, as the plugin leverages Google Play Services to handle phone number selection securely.

---

## Known Issues

- This plugin only works on Android devices with Google Play Services installed.
- Ensure that your app's `minSdkVersion` is set to 21 or higher.

---

## License

MIT License. See the `LICENSE` file for details.

---

## Contributions

Contributions are welcome! Please open an issue or submit a pull request on GitHub.

---

## Support

For any questions or issues, please open a GitHub issue
