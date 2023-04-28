package yonam2023.pushServer.controller;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TokenData {

    //토큰 데이터를 저장하기위한 Map 객체. db로 대체해도 괜찮음.
    //필요에 따라 토큰 저장방식을 바꿀것.
    Map<String, String> hashMap = new HashMap();
    int count=0;

    public Map<String, String> getAllToken(){
        return hashMap;
    }

    public String getToken(){
        return hashMap.get("0");
    }//getToken이 반드시 0번째 토큰을 반환하도록 되어있음. 수정필요.

    public void addToken(String token){
        hashMap.put(Integer.toString(count++), token);
    }//토큰을 추가하고 count를 증가.

    public int getCount(){
        return count;
    }

    //토큰 제거 구현되어있지 않음.
}
