package com.spring.security.repositories;

import com.spring.security.model.Document;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class DocumentRepository {

    private static final Map<String, Document> documents = new HashMap<>();

    static {
        documents.put("abc123", new Document("natalie"));
        documents.put("qwe123", new Document("natalie"));
        documents.put("asd555", new Document("emma"));
    }

    public Document findDocument(String code) {
        return documents.get(code);
    }
}
