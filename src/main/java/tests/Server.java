package tests;

import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Server {
    private static final Logger logger = Logger.getLogger(Server.class.getName());

    private static io.grpc.Server server;

    private void start() throws IOException {
        int port = 50051;
        server = Grpc.newServerBuilderForPort(port, InsecureServerCredentials.create())
                .addService(new ServerImpl())
                .build()
                .start();
        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                try {
                    Server.this.stop();
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
        final Server server = new Server();
        server.start();
        server.blockUntilShutdown();
    }

    static class ServerImpl extends TestsGrpc.TestsImplBase {
        public void emptyArgsEmptyReturn(Empty req, StreamObserver<Empty> responseObserver) {
            System.out.println("Client called emptyArgsEmptyReturn");

            responseObserver.onNext(null);
            responseObserver.onCompleted();
        }

        public void longArgsLongReturn(Long req, StreamObserver<Long> responseObserver) {
            System.out.println("Client called longArgsLongReturn");

            Long reply = Long.newBuilder().setNumber(req.getNumber()).build();

            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }

        public void multiLongArgsLongReturn(MultiLong req, StreamObserver<Long> responseObserver) {
            System.out.println("Client called multiLongArgsLongReturn");

            Long reply = Long.newBuilder().setNumber(req.getNumber(1)).build();

            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }

        public void stringArgsStringReturn(StringMessage req, StreamObserver<StringMessage> responseObserver) {
            System.out.println("Client called stringArgsStringReturn");

            StringMessage reply = StringMessage.newBuilder().setMessage(req.getMessage()).build();

            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }
}
