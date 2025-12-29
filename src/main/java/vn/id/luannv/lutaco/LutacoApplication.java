package vn.id.luannv.lutaco;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class LutacoApplication {

	public static void main(String[] args) {
        log.info("Log from @luannv | it's running!");
		SpringApplication.run(LutacoApplication.class, args);
	}

}
