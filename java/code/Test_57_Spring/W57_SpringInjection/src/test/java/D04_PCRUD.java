import com.linear.D01_Injection.service.IAccountService;
import com.linear.D02_CRUD.config.D04_SpringConfiguration;
import com.linear.D02_CRUD.domain.Account;
import com.linear.D02_CRUD.service.impl.AAccountServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 57.4、纯注解方式 - CRUD
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = D04_SpringConfiguration.class)
public class D04_PCRUD {

    @Autowired
    private AAccountServiceImpl as = null;

    @Test
    public void testFindAll(){
        List<Account> list = as.findAll();
        for (Account a : list){
            System.out.println(a);
        }
    }

}
