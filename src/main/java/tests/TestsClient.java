package tests;

import io.grpc.*;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class TestsClient {
    private static final Logger logger = Logger.getLogger(TestsClient.class.getName());

    private final tests.GreeterGrpc.GreeterBlockingStub blockingStub;

    public TestsClient(Channel channel) {
        blockingStub = GreeterGrpc.newBlockingStub(channel);
    }

    public void emptyArgsEmptyReturn() {
        Empty response = blockingStub.emptyArgsEmptyReturn(null);
        logger.info("stringArgsStringReturn: " + response);
    }

    public void longArgsLongReturn() {
        Long request = Long.newBuilder().setNumber(1).build();
        Long response = blockingStub.longArgsLongReturn(request);
        logger.info("longArgsLongReturn: " + response);
    }

    public void multiLongArgsLongReturn() {
        /*
         * @TODO: fazer funcionar :)
        MultiLong request = MultiLong.newBuilder()
                .setNumber1(1)
                .setNumber2(2)
                .setNumber3(3)
                .setNumber4(4)
                .setNumber5(5)
                .setNumber6(6)
                .setNumber7(7)
                .setNumber8(8)
                .build();
        Long response = blockingStub.multiLongArgsLongReturn(request);
        logger.info("longArgsLongReturn: " + response);
         */
    }

    public void stringArgsStringReturn() {
        /*
        * @TODO: implementar diferentes tamanhos
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
            client.emptyArgsEmptyReturn();
            client.longArgsLongReturn();
            client.multiLongArgsLongReturn();
            client.stringArgsStringReturn();
        } finally {
            channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        }
    }
}
