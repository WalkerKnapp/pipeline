package com.walker.pipeline;

import java.nio.ByteBuffer;
import java.util.function.Consumer;

public abstract class PipelineSource extends PipelineJoint {

    protected PipelineSource(PipelineDatatype.SuperType outputSuperType, PipelineDatatype outputType) {
        super(PipelineDatatype.SuperType.NOT_APPLICABLE, outputSuperType, PipelineDatatype.NOT_APPLICABLE, outputType);
    }

    @Override
    protected void consumeBuffer(ByteBuffer buffer) {
        // No implementation needed
    }

}
