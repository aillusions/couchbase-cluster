package com.zalizniak.couchbackend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CouchBackendApplicationTests {

    @Autowired
    private CouchbaseIntegrationService couchbaseIntegrationService;

    @Test
    void shouldPerformBasicOperations() {
        couchbaseIntegrationService.addNewRecord("helloworld");
        couchbaseIntegrationService.getRecord("helloworld");
        couchbaseIntegrationService.queryRecord();
    }

    /**
     * Locally Mac: Added: 100000 in 194668 ms:
     */
    @Test
    void createOperationPerformance() {
        long start = System.currentTimeMillis();
        long numberToAdd = 100_000;
        for (int i = 0; i < numberToAdd; i++) {
            couchbaseIntegrationService.addNewRecord("helloworld-" + i);
        }

        System.out.println("Added: "  + numberToAdd + " in " + (System.currentTimeMillis() - start) + " ms.");
    }

}
