package com.spring.security.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NameService {
    private static final Map<String, List<String>> secretNames = new HashMap<>();
    static {
        secretNames.put("natalie", Arrays.asList("Energico", "Perfecto"));
        secretNames.put("emma", Collections.singletonList("Fantastico"));
    }

    @PreAuthorize("hasAuthority('write')")  //PreAuthz-1
    public String getName() {
        return "Fantastico";
    }

    @PreAuthorize("#name == authentication.principal.username")   //PreAuthz-2
    public List<String> getSecretNames(String name) {
        return secretNames.get(name);
    }
}
