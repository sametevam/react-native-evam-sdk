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

public class RNEvamSdkModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  private static final String START = "start";
 

  public RNEvamSdkModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNEvamSdk";
  }

   @Override
  public Map<String, Object> getConstants() {
    final Map<String, Object> constants = new HashMap<>();

    // try {
       com.evam.evamsdk.Evam evamInstance = com.evam.evamsdk.Evam.Companion.getInstance();
        Application thisApplication = getApplication();
        evamInstance.initialize(
                thisApplication,
              "https://test.em.api-evam.com/sdk-api/in-app-communication-wrapper",
                "https://test.em.api-evam.com/sdk-listener/event",
                "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJldmFtIiwiZG9tYWluIjoiZXZhbS5jb20iLCJpYXQiOjE2MTQyODYyODZ9.sfhJmjaYjeVMFSdR7nJxg1SndWHqdJY0HgdICmnpBgg",
                "evam.com",
                "evam",
                "evam.com",
                false
        );
        // evamInstance.setSplashActivity(MainActivity.class);

        constants.put(START, "May the force be with you");
    // } catch (NameNotFoundException e) {
      // e.printStackTrace();
      //  System.out.println("Message: " + e.getMessage());
    // }
    return constants;
  }

}
