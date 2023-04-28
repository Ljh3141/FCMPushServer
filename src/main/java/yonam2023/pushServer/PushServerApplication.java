package yonam2023.pushServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//firebase fcm 프로젝트 등록 필요.
//서버키 등 등록 필요.
// https://galid1.tistory.com/740 참조
@SpringBootApplication
public class PushServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PushServerApplication.class, args);
	}

}
