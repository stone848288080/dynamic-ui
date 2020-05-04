package top.ximimi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ximimi.dao.entity.Student;
import top.ximimi.service.StudentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @RequestMapping("/all")
    public List<Student> getStudent(){
        return studentService.selectAllStudent();
    }

}
