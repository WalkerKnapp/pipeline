package com.walker.pipeline;

import java.util.ArrayList;

public class Pipeline<T> {

    private ArrayList<PipelineJoint<T>> joints;

    private Pipeline(ArrayList<PipelineJoint<T>> joints){
        this.joints = joints;

        // Check link types
        for(int i = 0; i < joints.size(); i++){
            if(i != 0){
                if(joints.get(i).getDesiredSuperType() != joints.get(i - 1).getOutputSuperType()){
                    throw new PipelineException("The output supertype of " + joints.get(i - 1).getClass().getSimpleName() +
                            " does not match the desired supertype of " + joints.get(i).getClass().getSimpleName());
                }
                if(joints.get(i).getDesiredType() != null  && joints.get(i - 1).getOutputType() != null && joints.get(i).getDesiredType() != joints.get(i - 1).getOutputType()){
                    throw new PipelineException("The output type of " + joints.get(i - 1).getClass().getSimpleName() +
                            " does not match the desired type of " + joints.get(i).getClass().getSimpleName());
                }
            }
        }

        // Create direct links
        for(int i = 0; i < joints.size() - 1; i++){
            joints.get(i).setBufferConsumer(joints.get(i + 1)::consumeBuffer);
            joints.get(i).setZcBufferNotifier(joints.get(i + 1)::consumeZC);
            joints.get(i).setZcBufferSupplier(joints.get(i + 1)::provideZCBuffer);
        }

        // Notify joints in reverse order
        for(int i = joints.size() - 1; i >= 0; i--){
            joints.get(i).ready();
        }
    }

    public void flush(){
        for(PipelineJoint joint : joints){
            joint.flush();
        }
    }

    public static <T> Builder builder(){
        return new Builder<T>();
    }

    public static class Builder<T> {
        private ArrayList<PipelineJoint<T>> joints = new ArrayList<>();

        public Builder joint(PipelineJoint<T> joint){
            joints.add(joint);
            return this;
        }

        public Pipeline build(){
            return new Pipeline<T>(joints);
        }
    }
}
