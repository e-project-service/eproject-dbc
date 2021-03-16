package com.github.xiaoyao9184.eproject.dbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;

/**
 * Created by xy on 2021/3/12.
 */
@SpringBootApplication
public class DBCBootApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(DBCBootApplication.class);
        app.setDefaultProperties(new HashMap<String, Object>() {{
            put("spring.profiles.default", "dev");
        }});
        app.run(args);
    }

}
