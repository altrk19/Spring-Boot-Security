package com.spring.security.security;

import com.spring.security.model.Document;
import com.spring.security.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;

@Configuration
public class DocumentsPermissionEvaluator implements PermissionEvaluator {
    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        Document document = (Document) targetDomainObject;
        String permissionRole =
                (String) permission;   //@PostAuthorize("hasPermission(returnObject, 'ROLE_admin')")    //ROLE_admin
        boolean admin = authentication.getAuthorities().stream()
                .anyMatch(authorities -> authorities.getAuthority().equals(permissionRole));
        return admin || document.getOwner().equals(authentication.getName());
//        return false;
    }
    // hasPermission() method allows external authz expression

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
//        String code = targetId.toString();
//        Document document = documentRepository.findDocument(code);
//        String permissionRole = (String) permission;
//
//        boolean admin =
//                authentication.getAuthorities()
//                        .stream()
//                        .anyMatch(a -> a.getAuthority().equals(permissionRole));
//
//        return admin || document.getOwner().equals(authentication.getName());
        return false;
    }
    //Assumes the permission evaluator receives an object ID, which it can use to retrieve the needed object
}
