import 'dart:async';
import 'dart:io';

import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

class GooglePhoneNumberHint {
  static const MethodChannel _channel = MethodChannel('google_mobile_number');

  ///Call this function to initiate method channel
  Future<String?> getMobileNumber() async {
    try {
      if (Platform.isAndroid) {
        final String? number = await _channel.invokeMethod('getMobileNumber');
        return number;
      } else {
        throw 'This plugin is only supported on Android';
      }
    } catch (e) {
      debugPrint('Error occured in getMobileNumber: $e');
      rethrow;
    }
  }
}
