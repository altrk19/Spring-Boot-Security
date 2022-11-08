package com.spring.security.services;

import com.spring.security.model.Employee;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {
    private static final Map<String, Employee> records = new HashMap<>();
    static {
        records.put("emma",new Employee("Emma Thompson", Collections.singletonList("Karamazov Brothers"), Arrays.asList("accountant", "reader")));
        records.put("natalie",new Employee("Natalie Parker", Collections.singletonList("Beautiful Paris"), Collections.singletonList("researcher")));
    }

    @PostAuthorize("returnObject.roles.contains('reader')")
    public Employee getBookDetails(String name) {
        return records.get(name);
    }
}
