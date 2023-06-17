package tests;

import com.google.protobuf.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

class SimpleClass implements Message {
    String var = "oi";
    private static final SimpleClass INSTANCE = new SimpleClass();

    public static final Parser<SimpleClass> PARSER = new Parser<SimpleClass>() {
        @Override
        public SimpleClass parseFrom(CodedInputStream input) {
            SimpleClass instance = new SimpleClass();
            return instance;
        }

        @Override
        public SimpleClass parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return null;
        }

        @Override
        public SimpleClass parsePartialFrom(CodedInputStream input) throws InvalidProtocolBufferException {
            return null;
        }

        @Override
        public SimpleClass parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return null;
        }

        @Override
        public SimpleClass parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return null;
        }

        @Override
        public SimpleClass parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return null;
        }

        @Override
        public SimpleClass parseFrom(ByteString data) {
            SimpleClass instance = new SimpleClass();
            return instance;
        }

        @Override
        public SimpleClass parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return null;
        }

        @Override
        public SimpleClass parsePartialFrom(ByteString data) throws InvalidProtocolBufferException {
            return null;
        }

        @Override
        public SimpleClass parsePartialFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return null;
        }

        @Override
        public SimpleClass parseFrom(byte[] data, int off, int len) throws InvalidProtocolBufferException {
            return null;
        }

        @Override
        public SimpleClass parseFrom(byte[] data, int off, int len, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return null;
        }

        @Override
        public SimpleClass parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return null;
        }

        @Override
        public SimpleClass parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return null;
        }

        @Override
        public SimpleClass parsePartialFrom(byte[] data, int off, int len) throws InvalidProtocolBufferException {
            return null;
        }

        @Override
        public SimpleClass parsePartialFrom(byte[] data, int off, int len, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return null;
        }

        @Override
        public SimpleClass parsePartialFrom(byte[] data) throws InvalidProtocolBufferException {
            return null;
        }

        @Override
        public SimpleClass parsePartialFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return null;
        }

        @Override
        public SimpleClass parseFrom(InputStream input) throws InvalidProtocolBufferException {
            return null;
        }

        @Override
        public SimpleClass parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return null;
        }

        @Override
        public SimpleClass parsePartialFrom(InputStream input) throws InvalidProtocolBufferException {
            return null;
        }

        @Override
        public SimpleClass parsePartialFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return null;
        }

        @Override
        public SimpleClass parseDelimitedFrom(InputStream input) throws InvalidProtocolBufferException {
            return null;
        }

        @Override
        public SimpleClass parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return null;
        }

        @Override
        public SimpleClass parsePartialDelimitedFrom(InputStream input) throws InvalidProtocolBufferException {
            return null;
        }

        @Override
        public SimpleClass parsePartialDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return null;
        }
    };

    @Override
    public void writeTo(CodedOutputStream output) throws IOException {

    }

    @Override
    public int getSerializedSize() {
        return 0;
    }

    @Override
    public Parser<? extends Message> getParserForType() {
        return PARSER;
    }

    @Override
    public ByteString toByteString() {
        try {
            ByteString.Output byteOutput = ByteString.newOutput();
            CodedOutputStream codedOutput = CodedOutputStream.newInstance(byteOutput);
            byteOutput.writeTo(byteOutput);
            codedOutput.flush();
            return byteOutput.toByteString();
        } catch (IOException e) {
            throw new RuntimeException("Error serializing SimpleClass to ByteString", e);
        }
    }

    @Override
    public byte[] toByteArray() {
        return new byte[0];
    }

    @Override
    public void writeTo(OutputStream output) throws IOException {

    }

    @Override
    public void writeDelimitedTo(OutputStream output) throws IOException {

    }

    @Override
    public Builder newBuilderForType() {
        return (Builder) this;
    }

    @Override
    public Builder toBuilder() {
        return (Builder) this;
    }

    @Override
    public Message getDefaultInstanceForType() {
        return INSTANCE;
    }

    @Override
    public boolean isInitialized() {
        return false;
    }

    @Override
    public List<String> findInitializationErrors() {
        return null;
    }

    @Override
    public String getInitializationErrorString() {
        return null;
    }

    @Override
    public Descriptors.Descriptor getDescriptorForType() {
        return ObjectMessage.getDescriptor();
    }

    @Override
    public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
        return null;
    }

    @Override
    public boolean hasOneof(Descriptors.OneofDescriptor oneof) {
        return false;
    }

    @Override
    public Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor oneof) {
        return null;
    }

    @Override
    public boolean hasField(Descriptors.FieldDescriptor field) {
        return false;
    }

    @Override
    public java.lang.Object getField(Descriptors.FieldDescriptor field) {
        if (field.getNumber() == 1) {
            return var;
        }

        throw new IllegalArgumentException("Invalid field descriptor: " + field.getName());
    }

    @Override
    public int getRepeatedFieldCount(Descriptors.FieldDescriptor field) {
        return 0;
    }

    @Override
    public java.lang.Object getRepeatedField(Descriptors.FieldDescriptor field, int index) {
        return null;
    }

    @Override
    public UnknownFieldSet getUnknownFields() {
        return null;
    }
}
