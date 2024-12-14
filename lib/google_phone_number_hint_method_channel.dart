import 'package:flutter/services.dart';
import 'package:google_phone_number_hint/google_phone_number_hint_platform_interface.dart';

/// An implementation of [GooglePhoneNumberHintPlatform] that uses method channels.
class MethodChannelGooglePhoneNumberHint extends GooglePhoneNumberHintPlatform {
  /// The method channel used to interact with the native platform.
  // @visibleForTesting
  final methodChannel = const MethodChannel('google_phone_number_hint');

  @override
  Future<String?> getPlatformVersion() async {
    final version =
        await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }
}
