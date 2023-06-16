package tests;

import io.grpc.*;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

interface TargetInterface {
    @ExecutionTime
    void emptyArgsEmptyReturn();
    @ExecutionTime
    void longArgsLongReturn();
    @ExecutionTime
    void multiLongArgsLongReturn();
    @ExecutionTime
    void stringArgsStringReturn();
}

public class TestsClient extends ExecutionTimeDecorator implements TargetInterface {
    private static final Logger logger = Logger.getLogger(TestsClient.class.getName());

    private final tests.GreeterGrpc.GreeterBlockingStub blockingStub;

    public TestsClient(Channel channel) {
        super(TestsClient.class);
        blockingStub = GreeterGrpc.newBlockingStub(channel);
    }

    @ExecutionTime
    public void emptyArgsEmptyReturn() {
        Empty response = blockingStub.emptyArgsEmptyReturn(null);
        logger.info("emptyArgsStringReturn: " + response);
    }

    @ExecutionTime
    public void longArgsLongReturn() {
        Long request = Long.newBuilder().setNumber(1L).build();
        Long response = blockingStub.longArgsLongReturn(request);
        logger.info("longArgsLongReturn: " + response);
    }

    @ExecutionTime
    public void multiLongArgsLongReturn() {
        MultiLong request = MultiLong.newBuilder()
                .addAllNumber(Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L))
                .build();

        Long response = blockingStub.multiLongArgsLongReturn(request);
        logger.info("multiLongArgsLongReturn: " + response);
    }

    @ExecutionTime
    public void stringArgsStringReturn() {
        /*
        * @TODO: implementar diferentes tamanhos?
        * */
        StringMessage request = StringMessage.newBuilder().setMessage("oi").build();
        StringMessage response = blockingStub.stringArgsStringReturn(request);
        logger.info("stringArgsStringReturn: " + response);
    }

    public static void main(java.lang.String[] args) throws Exception {
        java.lang.String target = "localhost:50051";

        ManagedChannel channel = Grpc.newChannelBuilder(target, InsecureChannelCredentials.create()).build();

        try {
            TestsClient client = new TestsClient(channel);

            ExecutionTimeDecorator decorator = new ExecutionTimeDecorator(client);

            TargetInterface proxy = (TargetInterface) Proxy.newProxyInstance(
                TestsClient.class.getClassLoader(),
                new Class<?>[] { TargetInterface.class },
                decorator
            );

            proxy.emptyArgsEmptyReturn();
            proxy.longArgsLongReturn();
            proxy.multiLongArgsLongReturn();
            proxy.stringArgsStringReturn();
        } finally {
            channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        }
    }
}

class ExecutionTimeDecorator implements InvocationHandler {
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

                System.out.println(new Date() + " - Execution time: " + executionTime + " nanoseconds");
            }
        }

        return null;
    }
}