package com.zalizniak.couchbackend;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * It is very important that cluster and bucket instances are created during startup and are then reused until the application shuts down.
 * Connection setup is expensive and the SDK is designed to be thread safe and can be efficiently used across all your application threads.
 */
@Configuration
public class Config {

    private String username = "db-user";
    private String password = "db-user-password";

    private String hostName = "127.0.0.1";
    private String bucketName = "defaultBucket";

    @Bean
    public Cluster couchbaseCluster() {
        return CouchbaseCluster.create(hostName).authenticate(username, password);
    }

    @Bean
    public Bucket couchbaseBucket() {
        return couchbaseCluster().openBucket(bucketName);
    }
}
