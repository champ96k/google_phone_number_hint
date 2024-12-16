package com.example.google_phone_number_hint;


import static androidx.core.app.ActivityCompat.startIntentSenderForResult;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.util.Log;

import androidx.activity.result.IntentSenderRequest;
import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.identity.GetPhoneNumberHintIntentRequest;
import com.google.android.gms.auth.api.identity.Identity;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry;


/**
 * MobileNumberPickerPlugin
 */
public class GooglePhoneNumberHintPlugin implements FlutterPlugin, MethodCallHandler, ActivityAware, PluginRegistry.ActivityResultListener {
    private MethodChannel channel;
    private Result phoneNumberResult;
    private Activity activity;
    private final int RESOLVE_HINT = 5978;
    private Context context;


    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "google_mobile_number");
        channel.setMethodCallHandler(this);
        context = flutterPluginBinding.getApplicationContext();
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        if (call.method.equals("getMobileNumber")) {
            GetPhoneNumberHintIntentRequest request = GetPhoneNumberHintIntentRequest.builder().build();

            Identity.getSignInClient(activity)
                    .getPhoneNumberHintIntent(request)
                    .addOnSuccessListener(r -> {
                        try {
                            IntentSender intentSender = r.getIntentSender();
                            IntentSenderRequest req = new IntentSenderRequest.Builder(intentSender).build();

                            startIntentSenderForResult(activity, req.getIntentSender(), RESOLVE_HINT, null, 0, 0, 0, null);
                        } catch (Exception e) {
                            Log.i("Error launching", "error occurred in launching Activity result " + e);
                        }
                    })
                    .addOnFailureListener(e -> Log.i("Failure occurred", "Failure getting phone number" + e));

            phoneNumberResult = result;
        } else {
            result.notImplemented();
        }
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        channel.setMethodCallHandler(null);
    }

    @Override
    public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
        binding.addActivityResultListener(this);
        activity = binding.getActivity();
    }

    @Override
    public void onDetachedFromActivityForConfigChanges() {
        activity = null;
    }

    @Override
    public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {
        binding.addActivityResultListener(this);
        activity = binding.getActivity();
    }

    @Override
    public void onDetachedFromActivity() {
        activity = null;
    }

    @Override
    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESOLVE_HINT) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    try {
                        String phoneNumber = Identity.getSignInClient(context).getPhoneNumberFromIntent(data);
                        phoneNumberResult.success(phoneNumber);
                    } catch (Exception error) {
                        System.out.println(error);
                    }
                }
            } else {
                try {
                    phoneNumberResult.success(null);
                } catch (Exception error) {
                    System.out.println(error);
                }
            }
        }
        return false;
    }
}
