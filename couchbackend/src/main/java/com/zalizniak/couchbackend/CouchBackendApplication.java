package com.zalizniak.couchbackend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * -DCOUCHBASE_NODE_ADDR=localhost
 */
@SpringBootApplication
public class CouchBackendApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CouchBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Command performed.");
    }
}
