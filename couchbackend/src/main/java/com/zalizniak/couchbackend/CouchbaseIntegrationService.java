package com.zalizniak.couchbackend;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * It is very important that cluster and bucket instances are created during startup and are then reused until the application shuts down.
 * Connection setup is expensive and the SDK is designed to be thread safe and can be efficiently used across all your application threads.
 */
@Service
public class CouchbaseIntegrationService {

    private String username = "db-user";
    private String password = "db-user-password";

    private String clusterAddress = "127.0.0.1";
    private String bucketName = "defaultBucket";

    private CouchbaseCluster cluster;
    private Bucket bucket;

    @PostConstruct
    public void init() {
        cluster = CouchbaseCluster.create(clusterAddress).authenticate(username, password);
        bucket = cluster.openBucket(bucketName);
    }

    @PreDestroy
    public void shutDown() {
        cluster.disconnect();
    }

    public void addNewRecord(String id) {
        JsonObject content = JsonObject.create().put("hello", "world");
        JsonDocument inserted = bucket.upsert(JsonDocument.create(id, content));
    }

    public void getRecord(String id) {
        JsonDocument found = bucket.get(id);
        System.out.println("Couchbase is the best database in the " + found.content().getString("hello"));
    }

    public void queryRecord() {
        N1qlQueryResult result = bucket.query(N1qlQuery.simple("select * from defaultBucket"));

        for (N1qlQueryRow row : result) {
            System.out.println(row.value());
        }
    }
}
