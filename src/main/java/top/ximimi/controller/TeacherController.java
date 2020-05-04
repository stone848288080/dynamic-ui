package top.ximimi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ximimi.dao.entity.Student;
import top.ximimi.dao.entity.Teacher;
import top.ximimi.service.StudentService;
import top.ximimi.service.TeacherService;

import java.util.List;


@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @RequestMapping("/all")
    public List<Teacher> getTeacher(){
        return teacherService.selectAllTeacher();
    }



}
