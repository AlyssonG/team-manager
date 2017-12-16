package servico;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceSingleton {
    private static ServiceSingleton instance = new ServiceSingleton();
    TimeAppService timeAppService;
    MembroAppService membroAppService;

    private ServiceSingleton() {
        ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");
        timeAppService = (TimeAppService) fabrica.getBean("timeAppService");
        membroAppService = (MembroAppService) fabrica.getBean("membroAppService");
    }

    public static ServiceSingleton getInstance() {
        return instance;
    }

    public TimeAppService getTimeService(){
        return timeAppService;
    }

    public MembroAppService getMembroService(){
        return membroAppService;
    }
}
