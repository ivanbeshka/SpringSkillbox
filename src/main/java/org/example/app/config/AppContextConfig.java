package org.example.app.config;

import org.example.app.services.IDProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.example.app")
public class AppContextConfig {

    @Bean
    public IDProvider idProvider(){
        return new IDProvider();
    }
}
