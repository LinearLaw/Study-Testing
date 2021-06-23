import com.linear.D01_TxBasic.AccountService;
import com.linear.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:D01_TxBasic.xml")
public class D01_test {
    @Autowired
    private IAccountService accountService;

    @Test
    public void testTransfer(){
        accountService.transfer("A","B",100f);
    }
}
