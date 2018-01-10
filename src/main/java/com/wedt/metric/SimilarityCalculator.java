package com.wedt.metric;

public abstract class SimilarityCalculator<T, K> {
    abstract public K calcSimilarity(T selected, T other);
}
