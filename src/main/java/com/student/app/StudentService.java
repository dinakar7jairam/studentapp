package com.student.app;

import io.micronaut.core.annotation.Introspected;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Introspected
public class StudentService {

    private Map<String, Student> studentMap = new HashMap<>();

    public Student create(Student student) {
        student.setStudentId(UUID.randomUUID().toString());
        studentMap.put(student.getStudentId(), student);
        return student;
    }

    public Student retrieve(String studentId) {
        return studentMap.get(studentId);
    }

    public Map<String, Student> retrieveAll() {
        return Collections.unmodifiableMap(studentMap);
    }
}
