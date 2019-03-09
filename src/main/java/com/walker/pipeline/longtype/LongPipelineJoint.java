package com.walker.pipeline.longtype;

import com.walker.pipeline.PipelineDatatype;
import com.walker.pipeline.PipelineException;

public abstract class LongPipelineJoint {

    private PipelineDatatype.SuperType desiredSuperType;
    private PipelineDatatype.SuperType outputSuperType;
    private PipelineDatatype desiredType;
    private PipelineDatatype outputType;

    private LongPipelineJoint nextJoint;

    protected LongPipelineJoint(PipelineDatatype.SuperType desiredSuperType, PipelineDatatype.SuperType outputSuperType,
                                PipelineDatatype desiredType, PipelineDatatype outputType){
        if(desiredSuperType == null || outputSuperType == null){
            throw new PipelineException("The desired super type and the output super type must both exist.");
        }
        this.desiredSuperType = desiredSuperType;
        this.outputSuperType = outputSuperType;
        this.desiredType = desiredType;
        this.outputType = outputType;
    }

    protected LongPipelineJoint(PipelineDatatype desiredType, PipelineDatatype outputType){
        if(desiredType == null || outputType == null){
            throw new PipelineException("The desired super type and the output super type must both exist.");
        }
        this.desiredSuperType = desiredType.getSuperType();
        this.outputSuperType = outputType.getSuperType();
        this.desiredType = desiredType;
        this.outputType = outputType;
    }

    public PipelineDatatype.SuperType getDesiredSuperType() {
        return desiredSuperType;
    }

    public PipelineDatatype.SuperType getOutputSuperType() {
        return outputSuperType;
    }

    public PipelineDatatype getDesiredType() {
        return desiredType;
    }

    public PipelineDatatype getOutputType() {
        return outputType;
    }

    protected void pushBuffer(long buffer) {
        nextJoint.consumeBuffer(buffer);
    }

    protected long requestZCBuffer() {
        return nextJoint.provideZCBuffer();
    }

    protected void pushZCBuffer() {
        nextJoint.consumeZC();
    }

    protected abstract void consumeBuffer(long buffer);

    protected abstract long provideZCBuffer();

    protected abstract void consumeZC();

    protected abstract void flush();

    public void ready(){
        // Implementation optional
    }

    void setNextJoint(LongPipelineJoint joint) {
        this.nextJoint = joint;
    }
}
