package pl.lstypka.springSecurityDistributedSystem.client2.config;
/**
 * Created by Łukasz on 2015-11-20.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import pl.lstypka.springSecurityDistributedSystem.config.config.SecurityConfig;

@SpringBootApplication
@ComponentScan({"pl.lstypka.springSecurityDistributedSystem.client2"})
@Import({SecurityConfig.class})
public class Application {

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(Application.class, args);
    }

}