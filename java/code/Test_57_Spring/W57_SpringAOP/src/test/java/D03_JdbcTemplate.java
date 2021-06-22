import com.linear.D03_JdbcTemplate.dao.impl.AccountDao;
import com.linear.D03_JdbcTemplate.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.security.AccessControlContext;
import java.util.List;

/**
 * 57.7、 JdbcTemplate CRUD
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:D03_JdbcTemplate.xml")
public class D03_JdbcTemplate {

    /***
     *  1、测试CRUD
     */
    @Autowired
    private AccountDao accountDao;
    @Test
    public void testJdbc(){
        Account account = accountDao.findById(1);
        System.out.println(account);
    }

    @Autowired
    private JdbcTemplate jtt;

    /***
     * 2、测试JDBC基本功能
     */
    @Test
    public void testJdbcBasic_1(){
        // 1、获取spring容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("D03_JdbcTemplate.xml");

        // 2、从容器中获取JdbcTemplate对象
        JdbcTemplate jt = ac.getBean("jdbcTemplate",JdbcTemplate.class);

        // 3、执行操作 execute
        jt.execute("insert into account(name,money) values('ddd',444)");


    }

    /** 4、update */
    @Test
    public void testJdbcBasic_2(){

        jtt.update(
                "insert into account(name,money) values(?,?)",
                "fff",600
        );
    }

    /** 5、queryForObject */
    @Test
    public void testJdbcBasic_3(){
        Long count = jtt.queryForObject(
                "select count(*) from account where money>?",
                Long.class,
                1000f
        );
        System.out.println("count : " + count);
    }

    /** 6、query */
    @Test
    public void testJdbcBasic_4(){
        List<Account> list = jtt.query(
                "select * from account",
                new BeanPropertyRowMapper<Account>(Account.class)
        );
        for(Account ac : list){
            System.out.println(ac);
        }
    }
}
