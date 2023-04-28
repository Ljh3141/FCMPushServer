package yonam2023.pushServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class pushController {

    @Autowired
    TokenData td;

    @RequestMapping("/hi")
    public String pushConGet(){
        System.out.println("hi called");
        return "hello";
    }

    //토큰 저장 요청처리 메서드
    @PostMapping("/token")
    public @ResponseBody void tokenPost(@RequestBody HashMap<String, Object> map){
        System.out.println("save token called");
        System.out.println(map);//토큰 전송 확인
        td.addToken((String)map.get("userToken"));//토큰 저장
        System.out.println("now have "+ td.getCount() + " tokens" );//저장된 토큰 수 확인
    }
}
