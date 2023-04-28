importScripts('https://www.gstatic.com/firebasejs/4.8.1/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/4.8.1/firebase-messaging.js');

// Initialize Firebase
// 이 js파일은 반드시 static의 root에 있어야함. 마찬가지로 컨텍스트 경로도 /firebase-messaging-sw.js여야함.
// 반드시!!
/*
let config = {
    //fcm config 입력.
};
firebase.initializeApp(config);

const messaging = firebase.messaging();

 */
import { initializeApp } from "firebase/app";
import { getMessaging } from "firebase/messaging/sw";

// Initialize the Firebase app in the service worker by passing in
// your app's Firebase config object.
// https://firebase.google.com/docs/web/setup#config-object
const firebaseApp = initializeApp({
    //fcm config 입력.
    //firebase에 프로젝트 등록 후 프로젝트 설정에서 아래로 내려 앱을 추가한다.
    //이때 npm이든 CDN이든 const firebaseConfig = { ~ } 내부의 값을 가져오면 된다.
});

// Retrieve an instance of Firebase Messaging so that it can handle background
// messages.
const messaging = getMessaging(firebaseApp);