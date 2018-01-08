package com.wedt.metric;

public abstract class SimilarityCalculator<T, K> {
    abstract public K calcSimilarity(T t1, T t2);
}
