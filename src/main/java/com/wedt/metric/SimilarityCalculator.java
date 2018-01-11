package com.wedt.metric;

public abstract class SimilarityCalculator<T, K> {
    protected RepresentationConfiguration rc;
    protected ClassificationConfiguration cc;

    public SimilarityCalculator(RepresentationConfiguration rc, ClassificationConfiguration cc) {
        this.rc = rc;
        this.cc = cc;
    }

    abstract public K calcSimilarity(T selected, T other);
}
