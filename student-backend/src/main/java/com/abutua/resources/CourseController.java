package com.abutua.resources;


import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abutua.model.Course;

@RestController
@CrossOrigin
public class CourseController {
    
    private List<Course> courses = Arrays.asList(new Course(1, "Java"),
                                                new Course(2, "Angular"),
                                                new Course(3, "Python"),
                                                new Course(4, "Kotlin"));
    
    @GetMapping("courses")                                                    
    public List<Course> getCourse(){
        return courses;
    }                                                    
}
