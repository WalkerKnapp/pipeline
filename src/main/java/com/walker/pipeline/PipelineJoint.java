package com.walker.pipeline;

import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class PipelineJoint<T> {

    private PipelineDatatype.SuperType desiredSuperType;
    private PipelineDatatype.SuperType outputSuperType;
    private PipelineDatatype desiredType;
    private PipelineDatatype outputType;

    private Consumer<T> bufferConsumer;
    private Supplier<T> zcBufferSupplier;
    private Runnable zcBufferNotifier;

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

    protected void pushBuffer(T buffer) {
        bufferConsumer.accept(buffer);
    }

    protected T requestZCBuffer() {
        return zcBufferSupplier.get();
    }

    protected void pushZCBuffer() {
        zcBufferNotifier.run();
    }

    protected abstract void consumeBuffer(T buffer);

    protected abstract T provideZCBuffer();

    protected abstract void consumeZC();

    protected abstract void flush();

    public void ready(){
        // Implementation optional
    }

    void setBufferConsumer(Consumer<T> bufferConsumer){
        this.bufferConsumer = bufferConsumer;
    }

    void setZcBufferSupplier(Supplier<T> bufferSupplier){
        this.zcBufferSupplier = bufferSupplier;
    }

    void setZcBufferNotifier(Runnable bufferNotifyer){
        this.zcBufferNotifier = bufferNotifyer;
    }
}
