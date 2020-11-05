package org.hepx.jgt.ssoclient;

import org.jasig.cas.client.boot.configuration.EnableCasClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableCasClient
public class JgtSsoClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(JgtSsoClientApplication.class, args);
    }

}
