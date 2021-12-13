package com.javaweb.shopping;






import com.javaweb.shopping.entity.User;
import com.javaweb.shopping.mapper.UserMapper;
import com.javaweb.shopping.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;
import java.sql.SQLException;



@SpringBootTest(classes = ShoppingApplicationTests.class)
class ShoppingApplicationTests {




    @Test
    void contextLoads() throws SQLException {


    }

}
