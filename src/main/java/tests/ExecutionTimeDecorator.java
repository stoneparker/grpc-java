package tests;

import com.google.protobuf.InvalidProtocolBufferException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ExecutionTimeDecorator implements InvocationHandler {
    private Object target;
    public static Map<String, String> map = new HashMap<String, String>();

    ExecutionTimeDecorator(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable, InvalidProtocolBufferException {
        if (method.isAnnotationPresent(ExecutionTime.class)) {
            long startTime = System.currentTimeMillis();

            try {
                Object result = method.invoke(target, args);

                return result;
            } finally {
                long endTime = System.currentTimeMillis();
                long executionTime = endTime - startTime;

                String methodRegisters = map.get(method.getName());

                String newMethodRegisters;
                if (methodRegisters == null) newMethodRegisters = executionTime + "";
                else newMethodRegisters = methodRegisters + "," + executionTime;

                map.put(method.getName(), newMethodRegisters);

                System.out.println(new Date() + " - Execution time of " + method.getName() + ": " + executionTime + " milliseconds");
            }
        }

        return null;
    }
}
