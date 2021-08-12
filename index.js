/* @flow */

import { NativeModules } from 'react-native';

const { RNEvamSdk } = NativeModules;

const EvamSdk = {};

// type EvamSdkObject = {
//   start: ?string,
//   event: ?string,
// };

// const EvamSdk: EvamSdkObject = {
//   start: RNEvamSdk && RNEvamSdk.start,
//   event: RNEvamSdk && RNEvamSdk.event,
  
// };

function startEvam(string): Promise<string> {
  return RNEvamSdk.startEvam(string);
}

function sendEvent(eventName): Promise<string> {
    return RNEvamSdk.sendEvamEvent(eventName);
}

function changeActorId(actorId): Promise<string> {
  return RNEvamSdk.changeActorId(actorId);
}

function sendNavigationLog(page): Promise<string> {
  return RNEvamSdk.sendNavigationLog(page);
}


EvamSdk.startEvam = startEvam;
EvamSdk.sendEvent = sendEvent;
EvamSdk.changeActorId = changeActorId;
EvamSdk.sendNavigationLog = sendNavigationLog;

export default EvamSdk;
