package aspecto;

import excecao.ExecucaoDeMetodoSemARespectivaPermissaoException;
import excecao.ViolacaoDeConstraintDesconhecidaException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.reflections.Reflections;
import org.springframework.dao.DataIntegrityViolationException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.*;

@Aspect
public class AspectoPermissoes {
    private static Map<String, Class<?>> map = new HashMap<String, Class<?>>();
    private static List<String> listaDeNomesDeConstraints;

    static {
        Reflections reflections = new Reflections("excecao");

        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(anotacao.ConstraintViolada.class);

        for (Class<?> classe : annotated) {
            map.put(classe.getAnnotation(anotacao.ConstraintViolada.class).nome(), classe);
        }

        listaDeNomesDeConstraints = new ArrayList<String>(map.keySet());
    }

    @Pointcut("call(* servico.*.*(..))")
    public void traduzExcecaoAround() {
    }

    @Around("traduzExcecaoAround()")
    public Object traduzExcecaoAround(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            for(Annotation an : method.getDeclaredAnnotations()) {
                if(!an.toString().toLowerCase().contains(getUserRole())){
                    throw new ExecucaoDeMetodoSemARespectivaPermissaoException("Você não possui permissão");
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            return joinPoint.proceed();
        }
    }

    private String getUserRole() {
        return "roleuser1";
    }
}