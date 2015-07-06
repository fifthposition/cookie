package cook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.pivotal.springcloud.ssl.CloudFoundryCertificateTruster;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        CloudFoundryCertificateTruster.trustApiCertificate();
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    void setEnvironment(Environment e) {
        System.out.println("Currently " + e.getProperty("cook.special"));
    }


}

@RestController
@RefreshScope
class RestaurantRestController {

  @Value("${cook.special}")
  String special;

  @RequestMapping("/")
  String special() {
    return "Today's special is: " + this.special;
  }
}
