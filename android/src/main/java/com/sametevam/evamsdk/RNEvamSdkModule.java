package com.sametevam.evamsdk;

import com.evam.evamsdk.Evam;
import com.evam.evamsdk.EvamLogManager;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import android.app.Application;
import android.content.pm.ApplicationInfo;


import java.util.HashMap;
import java.util.Map;

public class RNEvamSdkModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  private static final String START = "start";
  private static final String EVENT = "getEvent";

  public RNEvamSdkModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @ReactMethod
    public String startEvam() {
      Application application = (Application) reactContext.getApplicationContext();
      Evam evamInstance = Evam.Companion.getInstance();

        evamInstance.initialize(
                application,
              "https://test.em.api-evam.com/sdk-api/in-app-communication-wrapper",
                "https://test.em.api-evam.com/sdk-listener/event",
                "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJldmFtIiwiZG9tYWluIjoiZXZhbS5jb20iLCJpYXQiOjE2MTQyODYyODZ9.sfhJmjaYjeVMFSdR7nJxg1SndWHqdJY0HgdICmnpBgg",
                "evam.com",
                "evam",
                "evam.com",
                false
        );

    System.out.println("getEvamFunc");

    evamInstance.updateActorId(reactContext, "samet");
    
    return Boolean.toString(evamInstance.isInitialized());
  }

  @ReactMethod
  public String sendEvamEvent() {

    System.out.println("çalıştı mı");
    HashMap<String, String> eventParameters = new HashMap<>();
    eventParameters.put("key1", "cem");
    eventParameters.put("key2", "samet");
    try{
      EvamLogManager.Companion.getInstance(reactContext)
              .logEvent(reactContext, "event_name", eventParameters);
    }
    catch(Exception e) {
      e.printStackTrace();
    }

    return "success";
  }

  @Override
  public String getName() {
    return "RNEvamSdk";
  }

   @Override
  public Map<String, Object> getConstants() {
    final Map<String, Object> constants = new HashMap<>();
    

    // try {
      //  com.evam.evamsdk.Evam evamInstance = com.evam.evamsdk.Evam.Companion.getInstance();
      //   Application thisApplication = getApplication();
      //   evamInstance.initialize(
      //           thisApplication,
      //         "https://test.em.api-evam.com/sdk-api/in-app-communication-wrapper",
      //           "https://test.em.api-evam.com/sdk-listener/event",
      //           "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJldmFtIiwiZG9tYWluIjoiZXZhbS5jb20iLCJpYXQiOjE2MTQyODYyODZ9.sfhJmjaYjeVMFSdR7nJxg1SndWHqdJY0HgdICmnpBgg",
      //           "evam.com",
      //           "evam",
      //           "evam.com",
      //           false
      //   );
        // evamInstance.setSplashActivity(MainActivity.class);

        // constants.put(START, "May the force be with you");

        constants.put(START, startEvam());
        constants.put(EVENT, sendEvamEvent());
    // } catch (NameNotFoundException e) {
      // e.printStackTrace();
      //  System.out.println("Message: " + e.getMessage());
    // }
    return constants;
  }

}
