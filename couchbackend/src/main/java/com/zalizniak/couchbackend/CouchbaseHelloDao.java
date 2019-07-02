package com.zalizniak.couchbackend;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CouchbaseHelloDao {

    @Autowired
    private Bucket couchbaseBucket;

    public void addNewRecord(String id) {
        JsonObject content = JsonObject.create().put("hello", "world");
        JsonDocument inserted = couchbaseBucket.upsert(JsonDocument.create(id, content));
    }

    public void getRecord(String id) {
        JsonDocument found = couchbaseBucket.get(id);
        System.out.println("Couchbase is the best database in the " + found.content().getString("hello"));
    }

    public void queryRecord() {
        N1qlQueryResult result = couchbaseBucket.query(N1qlQuery.simple("select * from defaultBucket limit 10"));

        for (N1qlQueryRow row : result) {
            System.out.println(row.value());
        }
    }
}
