package com.zalizniak.couchbackend;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;
import org.springframework.stereotype.Service;

@Service
public class CouchbaseIntegrationService {

    private String username = "db-user";
    private String password = "db-user-password";

    private String clusterAddress = "127.0.0.1";
    private String bucketName = "defaultBucket";

    public void addNewRecord(String id) {
        CouchbaseCluster cluster = CouchbaseCluster.create(clusterAddress).authenticate(username, password);
        Bucket bucket = cluster.openBucket(bucketName);

        JsonObject content = JsonObject.create().put("hello", "world");
        JsonDocument inserted = bucket.upsert(JsonDocument.create(id, content));

        cluster.disconnect();
    }

    public void getRecord(String id) {
        CouchbaseCluster cluster = CouchbaseCluster.create(clusterAddress).authenticate(username, password);
        Bucket bucket = cluster.openBucket(bucketName);

        JsonDocument found = bucket.get(id);
        System.out.println("Couchbase is the best database in the " + found.content().getString("hello"));

        cluster.disconnect();
    }

    public void queryRecord() {
        CouchbaseCluster cluster = CouchbaseCluster.create(clusterAddress).authenticate(username, password);
        Bucket bucket = cluster.openBucket(bucketName);

        N1qlQueryResult result = bucket.query(N1qlQuery.simple("select * from defaultBucket"));

        for (N1qlQueryRow row : result) {
            System.out.println(row.value());
        }

        cluster.disconnect();
    }
}
