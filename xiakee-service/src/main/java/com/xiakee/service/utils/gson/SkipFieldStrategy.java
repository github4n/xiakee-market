/**
 * ClassName: SkipFieldStrategy
 * CopyRight: OpenSoft
 * Date: 13-8-26
 * Version: 1.0
 */
package com.xiakee.service.utils.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

import java.util.List;

public class SkipFieldStrategy implements ExclusionStrategy {
    private List<String> skipFields;

    public SkipFieldStrategy(List<String> skipFields) {
        this.skipFields = skipFields;
    }

    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        return skipFields != null && skipFields.contains(f.getName());
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }

    public List<String> getSkipFields() {
        return skipFields;
    }

    public void setSkipFields(List<String> skipFields) {
        this.skipFields = skipFields;
    }
}
