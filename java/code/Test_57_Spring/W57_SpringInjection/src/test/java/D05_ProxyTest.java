import com.linear.D02_CRUD.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 57.4、转账
 * */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:D04_Proxy.xml")
public class D05_ProxyTest {
    /** (1) 没有事务控制的service */
    @Autowired
    @Qualifier("paccountService")
    private IAccountService as;

    /** 无事务管理情况下 */
    @Test
    public void testPure() {
        as.transfer("A","B",100f);
    }

    // -------------------------------------------------

    /** (2) 有事务控制的service */
    @Autowired
    @Qualifier("accountServiceTsc")
    private IAccountService asTsc;
    /** 有事务管理情况下 */
    @Test
    public void testTransaction(){
        asTsc.transfer("A","B",100f);
    }

    // -------------------------------------------------

    /** (3) 代理过后的service */
    @Autowired
    @Qualifier("proxyService")
    private IAccountService asProxy;
    @Test
    public void testProxyTransaction(){
        asProxy.transfer("A","B",100f);
    }
}
