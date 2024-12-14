import 'package:google_phone_number_hint/google_phone_number_hint_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

abstract class GooglePhoneNumberHintPlatform extends PlatformInterface {
  /// Constructs a GooglePhoneNumberHintPlatform.
  GooglePhoneNumberHintPlatform() : super(token: _token);

  static final Object _token = Object();

  static GooglePhoneNumberHintPlatform _instance =
      MethodChannelGooglePhoneNumberHint();

  /// The default instance of [GooglePhoneNumberHintPlatform] to use.
  ///
  /// Defaults to [MethodChannelGooglePhoneNumberHint].
  static GooglePhoneNumberHintPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [GooglePhoneNumberHintPlatform] when
  /// they register themselves.
  static set instance(GooglePhoneNumberHintPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
