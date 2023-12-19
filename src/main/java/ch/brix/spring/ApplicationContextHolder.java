package ch.brix.spring;

import lombok.Getter;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextHolder {

    @Getter
    private static ApplicationContext applicationContext;

    public ApplicationContextHolder(ApplicationContext applicationContext) {
        ApplicationContextHolder.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }
}
