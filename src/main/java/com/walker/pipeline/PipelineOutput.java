package com.walker.pipeline;

public abstract class PipelineOutput extends PipelineJoint {
    protected PipelineOutput(PipelineDatatype.SuperType desiredSuperType, PipelineDatatype desiredType) {
        super(desiredSuperType, PipelineDatatype.SuperType.NOT_APPLICABLE, desiredType, PipelineDatatype.NOT_APPLICABLE);
    }
}
