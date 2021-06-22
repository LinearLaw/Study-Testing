import com.linear.D01_AOPXML.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 57.5、测试 AOP - XML
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:D01_AOPXML.xml")
public class D01_AOPXMLTest {
    /** 1、前置、后置、异常、最终通知 */
    @Autowired
    private IAccountService accountService;

    @Test
    public void testAOPXML(){
        accountService.saveAccount();
    }

    /** 2、环绕通知 */
    @Autowired
    private IAccountService accountServiceAround;
    @Test
    public void testAround(){
        accountServiceAround.saveAccount();
    }

}
