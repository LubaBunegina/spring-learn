import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import ru.diasoft.spring.entity.TestLearn;
import ru.diasoft.spring.service.CsvLoaderImp;
import ru.diasoft.spring.service.TestService;
import java.io.IOException;
import java.util.List;
public class Main {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("/spring-context.xml");
        TestService service = context.getBean(TestService.class);

        CsvLoaderImp loader = context.getBean(CsvLoaderImp.class);
        Resource res = loader.getResource();

        List<TestLearn> testList = service.getAll(res);

        if(testList != null){
            for(int t=0; t < testList.size(); t++){
                TestLearn test = testList.get(t);
                test.printTest();
            }
        }
        context.close();
    }
}
