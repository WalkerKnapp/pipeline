package com.walker.pipeline;

public enum PipelineDatatype {
    RAW_BGR24(SuperType.RAW_VIDEO),

    ENCODED_HUFFYUV(SuperType.ENCODED_VIDEO),

    NOT_APPLICABLE(SuperType.NOT_APPLICABLE);

    public enum SuperType {
        RAW_VIDEO, ENCODED_VIDEO, NOT_APPLICABLE;
    }

    private SuperType superType;

    PipelineDatatype(SuperType superType){
        this.superType = superType;
    }

    public SuperType getSuperType() {
        return superType;
    }
}
