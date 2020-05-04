package top.ximimi.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import top.ximimi.dao.entity.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class StudentDao {

    @Autowired
    SqlSession sqlSession;

    public List<Student> queryAllStudents(){

        String sqlId = "selectStudents";
        return sqlSession.selectList(sqlId);

    }



}
