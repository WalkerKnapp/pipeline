package com.walker.pipeline;

public class PassthroughJoint<T> extends PipelineJoint<T> {
    protected PassthroughJoint(PipelineDatatype.SuperType superType, PipelineDatatype type) {
        super(superType, superType, type, type);
    }

    @Override
    protected void consumeBuffer(T buffer) {
        pushBuffer(buffer);
    }

    @Override
    protected T provideZCBuffer() {
        // TODO: Support Zero-Copy transfers
        return null;
    }

    @Override
    protected void consumeZC() {
        // TODO: Support Zero-Copy transfers
    }

    @Override
    protected void flush() {
        // Passthrough will never need to be flushed
    }
}
