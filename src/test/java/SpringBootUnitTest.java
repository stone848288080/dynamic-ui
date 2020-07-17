import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.ximimi.SpringBootDriver;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootDriver.class)
public class SpringBootUnitTest {

    @Test
    public void testDao(){
        System.out.println("Hello Unit Test");
    }
}
