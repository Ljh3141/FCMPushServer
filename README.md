# FCMPushServer
Spring boot를 이용한 Fcm push server 구현 예제

firebase cloud messaging을 이용하므로 회원가입 후 다음 파일들을 수정해야함.

FirebaseInit.class의 path//
hello.html의 doFunction()의 firebaseConfig//
firebase-messaging-sw.js의 firebaseApp 설정부분

주의 : firebase-messaging-sw.js는 반드시 static의 바로 아래 위치해야함.
      프로젝트 빌드시 root context는 반드시 "/"여야함.
