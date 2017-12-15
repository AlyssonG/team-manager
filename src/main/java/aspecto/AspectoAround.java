package aspecto;

import Permissao.PermissaoManager;
import excecao.ExecucaoDeMetodoSemARespectivaPermissaoException;
import excecao.ViolacaoDeConstraintDesconhecidaException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.reflections.Reflections;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.*;

@Aspect
public class AspectoAround {
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
            checkPermission(method.getDeclaredAnnotations());
            return joinPoint.proceed();
        } catch (org.springframework.dao.DataAccessException e) {
            Throwable t = e;

            if (t instanceof DataIntegrityViolationException) {
                t = t.getCause();
                while (t != null && !(t instanceof SQLException)) {
                    t = t.getCause();
                }

                String msg = (t.getMessage() != null) ? t.getMessage() : "";

                for (String nomeDeConstraint : listaDeNomesDeConstraints) {
                    if (msg.indexOf(nomeDeConstraint) != -1) {
                        throw (Exception) map.get(nomeDeConstraint).newInstance();
                    }
                }
                throw new ViolacaoDeConstraintDesconhecidaException
                        ("A opera��o n�o foi realizada em fun��o da viola��o de uma restri��o no banco da dados.");
            } else {
                throw e;
            }
        }
    }

    private void checkPermission(Annotation[] annotations) throws Throwable{
        PermissaoManager permissaoManager = PermissaoManager.getInstance();
        for(Annotation annotation : annotations) {
            if (annotation.toString().contains("Role") && !permissaoManager.hasPermission(annotation.toString()))
                throw new ExecucaoDeMetodoSemARespectivaPermissaoException("Você não tem permissão para efetuar esta operação");
        }
    }
}