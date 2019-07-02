package com.zalizniak.couchbackend;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("#{environment.COUCHBASE_NODE_ADDR}")
    private String hostName;

    private String bucketName = "defaultBucket";

    @Bean
    public Cluster couchbaseCluster() {
        System.out.println("COUCHBASE_NODE_ADDR: " + hostName);
        return CouchbaseCluster.create(hostName).authenticate(username, password);
    }

    @Bean
    public Bucket couchbaseBucket() {
        return couchbaseCluster().openBucket(bucketName);
    }
}
