package top.ximimi.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ximimi.dao.TeacherDao;
import top.ximimi.dao.entity.Student;
import top.ximimi.dao.entity.Teacher;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    public List<Teacher> selectAllTeacher(){
        return teacherDao.queryAllTeacher();
    }
}
