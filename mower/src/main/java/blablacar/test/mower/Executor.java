package blablacar.test.mower;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by fatma on 26/11/2018.
 */
public class Executor {

    public static void main(String... args) {
         new ClassPathXmlApplicationContext("file-poller-mower.xml");
    }
}
