package yonam2023.pushServer.firebase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.messaging.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.json.JsonParseException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import yonam2023.pushServer.DTO.FcmMessage;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FirebaseCloudMessageService {

    public void sendMessageTo(String targetToken, String title, String body) throws Exception {
        //메시지전송
        Message message = Message.builder()
                .setNotification(Notification.builder()
                        .setTitle(title)
                        .setBody(body)
                        .build())
                .setToken(targetToken)
                .build();
        String response = FirebaseMessaging.getInstance().send(message);
        //메시지가 성공적으로 전달되는경우, message ID가 반환됨.
        System.out.println("Successfully sent message: " + response);
        //header 직접 제작
        /*
        Message message = Message.builder().setToken(targetToken)
                .setWebpushConfig(WebpushConfig.builder().putHeader("ttl", "300")
                        .setNotification(new WebpushNotification(title, body))
                .build())
            .build();
        String response = FirebaseMessaging.getInstance().sendAsync(message).get();
        System.out.println("Sent message : " + response);

         */
        //request 객체를 직접 제작
        /*

        private final String API_URL = "https://fcm.googleapis.com/v1/projects/프로젝트명입력/messages:send";

        private final ObjectMapper objectMapper;

        String message = makeMessage(targetToken, title, body);

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(message,
                MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(API_URL)
                .post(requestBody)
                .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken())
                .addHeader(HttpHeaders.CONTENT_TYPE, "application/json; UTF-8")
                .build();

        Response response = client.newCall(request).execute();

        System.out.println(response.body().string());

         */
    }

    //메시지 생성 메서드. 3번째 방법에서 이용함.
    /*
    private String makeMessage(String targetToken, String title, String body) throws JsonParseException, JsonProcessingException {
        FcmMessage fcmMessage = FcmMessage.builder()
                .message(FcmMessage.Message.builder()
                        .token(targetToken)
                        .notification(FcmMessage.Notification.builder()
                                .title(title)
                                .body(body)
                                .image(null)
                                .build()
                        ).build()).validateOnly(false).build();

        return objectMapper.writeValueAsString(fcmMessage);
    }
     */

    //Access 토큰을 얻기위한 함수. 없이도 작동하는지 여부는 확인하지 못함.
    private String getAccessToken() throws IOException {
        //서버설정 json파일은 반드시 resources 폴더 아래 적절한 위치에 있어야함.
        String firebaseConfigPath = "서버설정파일.json";

        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource(firebaseConfigPath).getInputStream())
                .createScoped(List.of("https://www.googleapis.com/auth/cloud-platform"));
        //인증은 cloud-platform 또는 firebase.messaging 어느쪽에서 받아도 동일하다.

        googleCredentials.refreshIfExpired();
        return googleCredentials.getAccessToken().getTokenValue();
    }
}