package com.walker.pipeline.longtype;

import com.walker.pipeline.PipelineException;

import java.util.ArrayList;

public class LongPipeline {

    private ArrayList<LongPipelineJoint> joints;

    private LongPipeline(ArrayList<LongPipelineJoint> joints){
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
            joints.get(i).setNextJoint(joints.get(i + 1));
        }

        // Notify joints in reverse order
        for(int i = joints.size() - 1; i >= 0; i--){
            joints.get(i).ready();
        }
    }

    public void flush(){
        for(LongPipelineJoint joint : joints){
            joint.flush();
        }
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private ArrayList<LongPipelineJoint> joints = new ArrayList<>();

        public Builder joint(LongPipelineJoint joint){
            joints.add(joint);
            return this;
        }

        public LongPipeline build(){
            return new LongPipeline(joints);
        }
    }
}
