package com.walker.pipeline;

import java.nio.ByteBuffer;
import java.util.function.Consumer;

public abstract class PipelineJoint {

    private PipelineDatatype.SuperType desiredSuperType;
    private PipelineDatatype.SuperType outputSuperType;
    private PipelineDatatype desiredType;
    private PipelineDatatype outputType;

    private Consumer<ByteBuffer> bufferConsumer;

    protected PipelineJoint(PipelineDatatype.SuperType desiredSuperType, PipelineDatatype.SuperType outputSuperType,
                            PipelineDatatype desiredType, PipelineDatatype outputType){
        if(desiredSuperType == null || outputSuperType == null){
            throw new PipelineException("The desired super type and the output super type must both exist.");
        }
        this.desiredSuperType = desiredSuperType;
        this.outputSuperType = outputSuperType;
        this.desiredType = desiredType;
        this.outputType = outputType;
    }

    protected PipelineJoint(PipelineDatatype desiredType, PipelineDatatype outputType){
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

    protected void pushBuffer(ByteBuffer buffer) {
        bufferConsumer.accept(buffer);
    }

    protected abstract void consumeBuffer(ByteBuffer buffer);

    protected abstract void flush();

    public void ready(){
        // Implementation optional
    }

    void setBufferConsumer(Consumer<ByteBuffer> bufferConsumer){
        this.bufferConsumer = bufferConsumer;
    }
}
