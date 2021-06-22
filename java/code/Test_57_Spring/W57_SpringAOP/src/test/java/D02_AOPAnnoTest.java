import com.linear.D02_AOPAnno.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 57.5、测试 AOP - XML
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:D02_AOPAnno.xml")
public class D02_AOPAnnoTest {
    /** 1、前置、后置、异常、最终通知 */
    @Autowired
    @Qualifier("accountServiceAnno")
    private IAccountService accountService;

    @Test
    public void testAOPXML(){
        accountService.saveAccount();
    }

    /** 2、环绕通知 */
    @Autowired
    @Qualifier("accountServiceAroundAnno")
    private IAccountService accountServiceAround;
    @Test
    public void testAround(){
        accountServiceAround.saveAccount();
    }
}
