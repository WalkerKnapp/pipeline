package com.walker.pipeline;

public abstract class PipelineSource<T> extends PipelineJoint<T> {

    protected PipelineSource(PipelineDatatype.SuperType outputSuperType, PipelineDatatype outputType) {
        super(PipelineDatatype.SuperType.NOT_APPLICABLE, outputSuperType, PipelineDatatype.NOT_APPLICABLE, outputType);
    }

    @Override
    protected void consumeBuffer(T buffer) {
        // No implementation needed
    }

    @Override
    protected T provideZCBuffer() {
        // No implementation  needed
        return null;
    }

    @Override
    protected void consumeZC() {
        // No implementation needed
    }
}
