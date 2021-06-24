import com.linear.D03_AnnoWithoutXML.config.MainSpringConfiguration;
import com.linear.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MainSpringConfiguration.class)
public class D03_test {
    @Autowired
    private IAccountService as;

    @Test
    public void testTransfer(){
        as.transfer("A","B",100f);
    }
}
