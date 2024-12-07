import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);

        HelloWorld bean1Hello =
                (HelloWorld) applicationContext.getBean("helloworld");
        System.out.println(bean1Hello.getMessage());
        HelloWorld bean2Hello =
                (HelloWorld) applicationContext.getBean("helloworld");

        Cat bean1Cat = (Cat) applicationContext.getBean("cat");
        Cat bean2Cat = (Cat) applicationContext.getBean("cat");

        System.out.println(bean1Hello.equals(bean2Hello));
        System.out.println(bean1Cat.equals(bean2Cat));

    }
}