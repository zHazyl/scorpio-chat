// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  baseApiUrl: 'http://134.209.250.111:8080',
  websocketBaseUrl: 'ws://134.209.250.111:8080',
  authServiceResource: '/auth-service',
  friendServiceResource: '/friend-service',
  groupServiceResource: '/group-service',
  chatMessagesServiceResource: '/chat-messages-service',
  websocketMessageServiceResource: '/messages-websocket-service'
};

export const baseUrl = 'http://134.209.250.111:8080';
export const wsBaseUrl = 'ws://134.209.250.111:8080';

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
