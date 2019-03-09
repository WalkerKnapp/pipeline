package com.walker.pipeline.longtype;

import com.walker.pipeline.PipelineDatatype;

public abstract class LongPipelineOutput extends LongPipelineJoint {
    protected LongPipelineOutput(PipelineDatatype.SuperType desiredSuperType, PipelineDatatype desiredType) {
        super(desiredSuperType, PipelineDatatype.SuperType.NOT_APPLICABLE, desiredType, PipelineDatatype.NOT_APPLICABLE);
    }
}
