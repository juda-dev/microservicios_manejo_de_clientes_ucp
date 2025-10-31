package agendia.manejo_clientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class ManejoClientesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManejoClientesApplication.class, args);
    }

}
