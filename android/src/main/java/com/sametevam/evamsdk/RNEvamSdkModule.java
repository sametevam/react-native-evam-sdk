package com.sametevam.evamsdk;

import com.evam.evamsdk.Evam;
import com.evam.evamsdk.EvamInstance;
import com.evam.evamsdk.EvamLogManager;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.UiThreadUtil;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RNEvamSdkModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  private static final String START = "start";
  private static final String EVENT = "event";

  public RNEvamSdkModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }


  @ReactMethod
  public void sendEvamEvent(final String eventName, final Promise promise) {
      ConnectivityManager connectivityManager = (ConnectivityManager)reactContext.getSystemService(Context.CONNECTIVITY_SERVICE);
      NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
      boolean isConnected = networkInfo != null && networkInfo.isConnected(); // Whether to network
      boolean isConnectWifi = networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI; // Whether to connect WiFi
      boolean isConnectedMobile = networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE;; // Whether to connect to a mobile cellular network

      HashMap<String, String> eventParameters = new HashMap<>();
      eventParameters.put("key11", UUID.randomUUID().toString());
      Application currentActivity = (Application) reactContext.getApplicationContext();
      //Activity currentActivity = getCurrentActivity();
      try{
        UiThreadUtil.runOnUiThread(() -> {
         EvamLogManager.Companion.getInstance(currentActivity)
                .logEvent(getCurrentActivity(), eventName, eventParameters, appCommunicationItemAction -> { return null;});
        });

      //  UiThreadUtil.runOnUiThread(() -> {
      //    EvamLogManager.Companion.getInstance(currentActivity)
      //            .logEvent(currentActivity, "e1", action -> {
       //             return null;
       //           });
       // });

        promise.resolve("Event has send");
      }
      catch(Exception e) {
        promise.reject("No current activity.");
        e.printStackTrace();
      }
  }

  @ReactMethod
  public void changeActorId(final String actorId, final Promise promise) {
      try{
          Evam evamInstance = Evam.Companion.getInstance();
          //UiThreadUtil.runOnUiThread(() -> {
            // evamInstance.updateActorId(reactContext, "samet");
              evamInstance.updateActorId(reactContext, actorId);
            // });
            promise.resolve("ActorId changed");
      }
      catch(Exception e) {
         // promise.reject("Error");
          e.printStackTrace();
      }
  }



  @ReactMethod
  public void sendNavigationLog(final String page, final Promise promise) {
      Application currentActivity = (Application) reactContext.getApplicationContext();
      HashMap<String, String> eventParameters = new HashMap<>();
      Context context = reactContext.getApplicationContext();
      String sa  =  getCurrentActivity().getLocalClassName();
      Log.e("app", "Activity nameE:" + sa);

      try{
          UiThreadUtil.runOnUiThread(() -> {
              EvamLogManager.Companion.getInstance(currentActivity)
                      .logEvent( getCurrentActivity(), page, eventParameters, appCommunicationItemAction -> { return null;});
          });
          promise.resolve("navigationLog is send");
      }
      catch(Exception e){
          e.printStackTrace();
      }
  }

  @Override
  public String getName() {
    return "RNEvamSdk";
  }

}
