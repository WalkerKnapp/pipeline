package com.walker.pipeline;

public abstract class PipelineOutput<T> extends PipelineJoint<T> {
    protected PipelineOutput(PipelineDatatype.SuperType desiredSuperType, PipelineDatatype desiredType) {
        super(desiredSuperType, PipelineDatatype.SuperType.NOT_APPLICABLE, desiredType, PipelineDatatype.NOT_APPLICABLE);
    }
}
