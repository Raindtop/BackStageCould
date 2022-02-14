package org.raindrop.lambdaekl;

import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import lombok.extern.slf4j.Slf4j;
import org.raindrop.entity.Person;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class BeanUtils {
    private static Map<Class, SerializedLambda> CLASS_LAMBDA_CACHE = new ConcurrentHashMap<>();

    /**
     * 转换方法引用为属性名
     *
     * @param sfn
     * @param <T>
     * @return
     */
    public static <T> String convertToFieldName(SFunction<T> sfn) {
        SerializedLambda lambda = getSerializedLambda(sfn);
        String methodName = lambda.getImplMethodName();
        String prefix = null;

        if (methodName.startsWith("get")){
            prefix = "get";
        }
        else if(methodName.startsWith("is")){
            prefix = "is";
        }

        if (prefix == null){
            log.info("Error Gett Method", methodName);
            return null;
        }

        return methodName.replace(prefix, "");
    }

    public static SerializedLambda getSerializedLambda(Serializable fn) {
        SerializedLambda lambda = null;
        try {
            Method method = fn.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(Boolean.TRUE);
            lambda = (SerializedLambda) method.invoke(fn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lambda;
    }



    public static void main(String[] args) {
        System.out.println(convertToFieldName(Person::getName));
    }
}
