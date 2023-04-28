package yonam2023.pushServer.DTO;

import lombok.Data;

// /fcm 처리용 커맨드 객체
@Data
public class RequestCommand {
    private String targetToken;
    private String title;
    private String body;

}
