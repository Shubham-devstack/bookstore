package com.bookstore.demo.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "user")
@Data
public class UserProperties {
    private Map<String, User> users = new HashMap<>();

    @Data
    public static class User {
        private String username;
        private String password;
        private List<String> roles = new ArrayList<>();
    }
}
