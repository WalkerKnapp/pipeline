package com.walker.pipeline;

import java.util.ArrayList;

public class Pipeline {

    private ArrayList<PipelineJoint> joints;

    private Pipeline(ArrayList<PipelineJoint> joints){
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

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private ArrayList<PipelineJoint> joints = new ArrayList<>();

        public Builder joint(PipelineJoint joint){
            joints.add(joint);
            return this;
        }

        public Pipeline build(){
            return new Pipeline(joints);
        }
    }
}
