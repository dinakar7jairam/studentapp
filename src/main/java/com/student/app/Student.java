package com.student.app;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.core.annotation.Introspected;
import javax.validation.constraints.NotBlank;

@Introspected
public class Student {

    private String studentId;

    @NonNull
    @NotBlank
    private String name;

    @NonNull
    @NotBlank
    private Integer age;

    @NonNull
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(@NonNull String studentId) {
        this.studentId = studentId;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public Integer getAge() {
        return age;
    }

    public void setAge(@NonNull Integer age) {
        this.age = age;
    }
}
