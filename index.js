/* @flow */

import { NativeModules } from 'react-native';

const { RNEvamSdk } = NativeModules;

type EvamSdkObject = {
  start: ?string,
  event: ?string,
};

const EvamSdk: EvamSdkObject = {
  start: RNEvamSdk && RNEvamSdk.start,
  event: RNEvamSdk && RNEvamSdk.event,
  
};

export default EvamSdk;
