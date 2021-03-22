package com.student.app;
import io.micronaut.http.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.Map;

@Controller
public class StudentController {

    @Post
    public Student create(@Valid @Body Student student) {
        return new StudentService().create(student);
    }

    @Get
    public Student retrieve(@Valid @PathVariable String studentId) {
        return new StudentService().retrieve(studentId);
    }

    @Get
    public Map<String, Student> retrieveAll() {
        return new StudentService().retrieveAll();
    }
}
