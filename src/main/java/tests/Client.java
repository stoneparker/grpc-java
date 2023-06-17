package tests;

import io.grpc.*;

import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

interface iClient {
    @ExecutionTime
    void emptyArgsEmptyReturn();
    @ExecutionTime
    void longArgsLongReturn();
    @ExecutionTime
    void multiLongArgsLongReturn();
    @ExecutionTime
    void stringArgsStringReturn();
}

public class Client extends ExecutionTimeDecorator implements iClient {
    private static final Logger logger = Logger.getLogger(Client.class.getName());

    private final tests.TestsGrpc.TestsBlockingStub blockingStub;

    public Client(Channel channel) {
        super(Client.class);
        blockingStub = TestsGrpc.newBlockingStub(channel);
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
            Client client = new Client(channel);
            ExecutionTimeDecorator decorator = new ExecutionTimeDecorator(client);

            iClient proxy = (iClient) Proxy.newProxyInstance(
                Client.class.getClassLoader(),
                new Class<?>[] { iClient.class },
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
