package yonam2023.pushServer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import yonam2023.pushServer.DTO.RequestCommand;
import yonam2023.pushServer.firebase.FirebaseCloudMessageService;

@RestController
@RequiredArgsConstructor
public class messageController {

    @Autowired
    TokenData td;

    private final FirebaseCloudMessageService firebaseCloudMessageService;

    //메시지 전송 처리 메서드
    @PostMapping("/fcm")
    public ResponseEntity pushMessage(@RequestBody RequestCommand requestCommand) throws Exception {
        System.out.println("Send Message : "+td.getToken() + "/ title : "
                + requestCommand.getTitle() + "/ body : " + requestCommand.getBody());

        //메시지 전송 요청
        firebaseCloudMessageService.sendMessageTo(
                td.getToken(),
                requestCommand.getTitle(),
                requestCommand.getBody());

        //200 반환
        return ResponseEntity.ok().build();
    }
}