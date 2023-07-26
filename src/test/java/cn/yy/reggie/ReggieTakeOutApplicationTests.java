package cn.yy.reggie;

import org.junit.jupiter.api.Test;
import org.springframework.util.AntPathMatcher;

//@SpringBootTest
class ReggieTakeOutApplicationTests {

    @Test
    void contextLoads() {
        AntPathMatcher matcher = new AntPathMatcher();
        System.out.println(matcher.match("/ww", "/ww"));
    }

}
