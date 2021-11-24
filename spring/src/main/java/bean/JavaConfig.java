package bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: siyan.liu
 * @Date: 2021/10/22
 */
@Configuration
public class JavaConfig {

    @Bean(name="stu004")
    public Student getStudent() {
        return new Student(4l,"stu004");
    }
}
