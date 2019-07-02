package com.zalizniak.couchbackend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CouchBackendApplicationTests {

    @Autowired
    private CouchbaseHelloDao couchbaseHelloDao;

    @Test
    void shouldPerformBasicOperations() {
        couchbaseHelloDao.addNewRecord("helloworld");
        couchbaseHelloDao.getRecord("helloworld");
        couchbaseHelloDao.queryRecord();
    }

    /**
     * Locally Mac: Added: 100000 in 194668 ms:
     */
    @Test
    void createOperationPerformance() {
        long start = System.currentTimeMillis();
        long numberToAdd = 10_000;
        for (int i = 0; i < numberToAdd; i++) {
            couchbaseHelloDao.addNewRecord("helloworld-" + i);
        }

        System.out.println("Added: "  + numberToAdd + " in " + (System.currentTimeMillis() - start) + " ms.");
    }

}
