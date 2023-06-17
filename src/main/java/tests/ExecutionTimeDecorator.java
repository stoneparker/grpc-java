package tests;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Date;

public class ExecutionTimeDecorator implements InvocationHandler {
    private Object target;

    ExecutionTimeDecorator(Object target) {
        this.target = target;
    }

    @Override
    @ExecutionTime
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(ExecutionTime.class)) {
            long startTime = System.nanoTime();

            try {
                Object result = method.invoke(target, args);
                return result;
            } finally {
                long endTime = System.nanoTime();
                long executionTime = endTime - startTime;

                System.out.println(new Date() + " - Execution time of " + method.getName() + ": " + executionTime + " nanoseconds");
            }
        }

        return null;
    }
}