package com.wedt.metric;

public abstract class SimilarityCalculator<T, J> {

    abstract double calcSimilarity(T tom, J jerry);
}
