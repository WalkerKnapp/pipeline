package com.walker.pipeline.longtype;

import com.walker.pipeline.PipelineDatatype;

public abstract class LongPipelineSource extends LongPipelineJoint {

    protected LongPipelineSource(PipelineDatatype.SuperType outputSuperType, PipelineDatatype outputType) {
        super(PipelineDatatype.SuperType.NOT_APPLICABLE, outputSuperType, PipelineDatatype.NOT_APPLICABLE, outputType);
    }

    @Override
    protected void consumeBuffer(long buffer) {
        // No implementation needed
    }

    @Override
    protected long provideZCBuffer() {
        // No implementation  needed
        return -1L;
    }

    @Override
    protected void consumeZC() {
        // No implementation needed
    }
}
