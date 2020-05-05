package top.ximimi.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import top.ximimi.dao.entity.Teacher;

import java.util.List;

@Repository
public class TeacherDao {

    @Autowired
    @Qualifier("secondSqlSession")
    SqlSession sqlSession;

    public List<Teacher> queryAllTeacher(){

        String sqlId = "selectTeachers";
        return sqlSession.selectList(sqlId);

    }
}
