package com.walker.pipeline;

import java.nio.ByteBuffer;

public class PassthroughJoint extends PipelineJoint {
    protected PassthroughJoint(PipelineDatatype.SuperType superType, PipelineDatatype type) {
        super(superType, superType, type, type);
    }

    @Override
    protected void consumeBuffer(ByteBuffer buffer) {
        pushBuffer(buffer);
    }

    @Override
    protected void flush() {
        // Passthrough will never need to be flushed
    }
}
