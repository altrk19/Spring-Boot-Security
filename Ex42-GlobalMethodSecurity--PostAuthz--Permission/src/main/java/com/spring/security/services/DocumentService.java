package com.spring.security.services;

import com.spring.security.model.Document;
import com.spring.security.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @PostAuthorize("hasPermission(returnObject, 'ROLE_admin')")
//    @PreAuthorize("hasPermission(#code, 'document', 'ROLE_admin')")
    public Document getDocument(String code) {
        return documentRepository.findDocument(code);
    }
}
