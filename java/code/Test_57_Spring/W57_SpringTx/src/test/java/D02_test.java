import com.linear.dao.IAccountDao;
import com.linear.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:D02_TxAnno.xml")
public class D02_test {

    @Autowired
    private IAccountService caccountService;

    @Test
    public void testTransfer(){
        caccountService.transfer("A","B",100f);
    }
}
