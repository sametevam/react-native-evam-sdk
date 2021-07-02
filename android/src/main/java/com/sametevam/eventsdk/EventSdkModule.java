package com.sametevam.evamsdk;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageManager;

import java.util.HashMap;
import java.util.Map;

public class EvamSdkModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  private static final String START = "start";
 

  public EvamSdkModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String start() {
    return "start başla";
  }

}
