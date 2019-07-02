package com.zalizniak.couchbackend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CouchBackendApplicationTests {

    @Autowired
    private CouchbaseIntegrationService couchbaseIntegrationService;

    @Test
    void contextLoads() {
        couchbaseIntegrationService.addNewRecord("helloworld");
        couchbaseIntegrationService.getRecord("helloworld");
        couchbaseIntegrationService.queryRecord();
    }

}
