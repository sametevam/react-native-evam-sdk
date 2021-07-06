/* @flow */

import { NativeModules } from 'react-native';

const { RNEvamSdk } = NativeModules;

type EvamSdkObject = {
  start: ?string,
};

const EvamSdk: EvamSdkObject = {
  start: RNEvamSdk && RNEvamSdk.start,
};

export default EvamSdk;
