package top.ximimi.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import top.ximimi.dao.entity.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class StudentDao {

    //@Autowired
   // @Qualifier("masterSqlSessionFactory")
    //SqlSessionFactory sqlSessionFactory;

    @Autowired
    @Qualifier("masterSqlSession")
    SqlSession sqlSession; //= sqlSessionFactory.openSession();

    public List<Student> queryAllStudents() {

        String sqlId = "selectStudents";
        return sqlSession.selectList(sqlId);

    }

}