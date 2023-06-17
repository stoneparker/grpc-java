package tests;

import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class TestsServer {
    private static final Logger logger = Logger.getLogger(TestsServer.class.getName());

    private static io.grpc.Server server;

    private void start() throws IOException {
        int port = 50051;
        server = Grpc.newServerBuilderForPort(port, InsecureServerCredentials.create())
                .addService(new TestsImpl())
                .build()
                .start();
        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                try {
                    TestsServer.this.stop();
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
                System.err.println("*** server shut down");
            }
        });
    }

    private void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final TestsServer testsServer = new TestsServer();
        testsServer.start();
        testsServer.blockUntilShutdown();
    }

    static class TestsImpl extends TestsGrpc.TestsImplBase {
        public void emptyArgsEmptyReturn(Empty req, StreamObserver<Empty> responseObserver) {
            responseObserver.onNext(null);
            responseObserver.onCompleted();
        }

        public void longArgsLongReturn(Long req, StreamObserver<Long> responseObserver) {
            Long reply = Long.newBuilder().setNumber(req.getNumber()).build();

            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }

        public void multiLongArgsLongReturn(MultiLong req, StreamObserver<Long> responseObserver) {
            Long reply = Long.newBuilder().setNumber(req.getNumber(1)).build();

            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }

        public void stringArgsStringReturn(StringMessage req, StreamObserver<StringMessage> responseObserver) {
            StringMessage reply = StringMessage.newBuilder().setMessage(req.getMessage()).build();

            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }
}
