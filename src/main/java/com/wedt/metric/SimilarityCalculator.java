package com.wedt.metric;

import java.util.Set;

public abstract class SimilarityCalculator<T, K> {
    protected RepresentationConfiguration rc;
    protected ClassificationConfiguration cc;
    protected Set<String> dictList;

    public SimilarityCalculator(RepresentationConfiguration rc, ClassificationConfiguration cc, Set<String> dictList) {
        this.rc = rc;
        this.cc = cc;
        this.dictList = dictList;
    }

    abstract public K calcSimilarity(T selected, T other);
}
