package com.zalizniak.couchbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * -DCOUCHBASE_NODE_ADDR=localhost
 */
@SpringBootApplication
public class CouchBackendApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CouchBackendApplication.class, args);
    }

    @Autowired
    private CouchbaseHelloDao couchbaseHelloDao;

    @Override
    public void run(String... args) {
        System.out.println("Performing command.");
    }

    private final AtomicLong sentAmount = new AtomicLong();

    private static final int NEW_RECORDS_PER_SECOND = 10;

    @Scheduled(fixedDelay = 1000 / NEW_RECORDS_PER_SECOND)
    public void bsendBSchedule() {
        couchbaseHelloDao.addNewRecord("helloworld-" + UUID.randomUUID().toString());
        sentAmount.incrementAndGet();
    }

    @Scheduled(fixedDelay = 5 * 1000)
    public void reportBySchedule() {
        System.out.println("Sent: " + sentAmount.get() + " records (" + NEW_RECORDS_PER_SECOND + " per second)");
    }
}
