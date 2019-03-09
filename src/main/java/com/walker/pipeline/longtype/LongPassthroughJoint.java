package com.walker.pipeline.longtype;

import com.walker.pipeline.PipelineDatatype;

public class LongPassthroughJoint extends LongPipelineJoint {
    protected LongPassthroughJoint(PipelineDatatype.SuperType superType, PipelineDatatype type) {
        super(superType, superType, type, type);
    }

    @Override
    protected void consumeBuffer(long buffer) {
        pushBuffer(buffer);
    }

    @Override
    protected long provideZCBuffer() {
        // TODO: Support Zero-Copy transfers
        return -1L;
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
