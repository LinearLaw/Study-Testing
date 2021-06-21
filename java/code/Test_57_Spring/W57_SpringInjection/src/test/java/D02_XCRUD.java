import com.linear.D02_CRUD.dao.IAccountDao;
import com.linear.D02_CRUD.dao.impl.XAccountDaoImpl;
import com.linear.D02_CRUD.domain.Account;
import com.linear.D02_CRUD.service.IAccountService;
import com.linear.D02_CRUD.service.impl.XAccountServiceImpl;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.beans.PropertyVetoException;
import java.util.List;

/**
 * 57.2、 XML + DBUtils -> CRUD
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:D02_XCRUD.xml")
public class D02_XCRUD {

    /*  原本的写法
    *   - 创建连接池，配置连接池的参数
    *   - 创建QueryRunner
    *   - 创建DAO
    *   - 创建Service
    * */
    private XAccountServiceImpl accountService;
    public void init(){
        try {
            ComboPooledDataSource dataSource = new ComboPooledDataSource();
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/w57_spring");
            dataSource.setUser("root");
            dataSource.setPassword("root");

            QueryRunner qr = new QueryRunner(dataSource);
            XAccountDaoImpl accountDao = new XAccountDaoImpl();
            accountDao.setRunner(qr);

            accountService = new XAccountServiceImpl();
            accountService.setAccountDao((IAccountDao)accountDao);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    /* 现在的写法
        @RunWith 指定重写的类
        @ContextConfiguration 指定依赖注入的配置文件
        @Autowired 声明注入成员
     */
    @Autowired
    private IAccountService as;

    /** 1、查询所有 */
    @Test
    public void testFindAll(){
        List<Account> list = as.findAll();
        for(Account a : list){
            System.out.println(a);
        }
    }

    /** 2、按id查找 */
    @Test
    public void testFindById(){
        Account ac = as.findById(2);
        System.out.println(ac);
    }

    /** 3、新增account */
    @Test
    public void testSaveAccount(){
        Account ac = new Account();
        ac.setMoney(1000f);
        ac.setName("ABAMA");

        as.saveAccount(ac);
    }

    /** 4、修改 */
    @Test
    public void testUpdateAccount(){
        Account ac = as.findById(2);
        ac.setName("biden");

        as.updateAccount(ac);
    }

    /** 5、删除 */
    @Test
    public void testDeleteAccount(){
        as.deleteById(2);
    }

}
