package top.ximimi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ximimi.dao.StudentDao;
import top.ximimi.dao.entity.Student;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentDao studentDao;


     public List<Student> selectAllStudent(){
         return studentDao.queryAllStudents();
     }

}
