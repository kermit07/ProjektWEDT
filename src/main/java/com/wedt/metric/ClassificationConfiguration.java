package com.wedt.metric;

public class ClassificationConfiguration {

    private boolean dictionaryEnabled;
    private boolean kindEnabled;

    public ClassificationConfiguration(boolean dictionaryEnabled, boolean kindEnabled) {
        this.dictionaryEnabled = dictionaryEnabled;
        this.kindEnabled = kindEnabled;
    }

    public boolean isDictionaryEnabled() {
        return dictionaryEnabled;
    }

    public boolean isKindEnabled() {
        return kindEnabled;
    }
}
