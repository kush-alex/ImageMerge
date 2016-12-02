package com.kushnarenko.service.fusion;

import com.kushnarenko.service.fusion.impl.MultiFocusFusion;
import com.kushnarenko.service.fusion.impl.MultiModalFusion;
import com.kushnarenko.service.fusion.impl.MultiTemporalFusion;

import java.util.HashMap;
import java.util.Map;

public class ImageFusionContainer {

    private final static Map<ImageFusionType, ImageFusion> container = new HashMap<>();

    static {
        container.put(ImageFusionType.MULTI_FOCUS1, new MultiFocusFusion());
        container.put(ImageFusionType.MULTI_MODAL, new MultiModalFusion());
        container.put(ImageFusionType.MULTI_TEMPORAL, new MultiTemporalFusion());
    }

    public static ImageFusion get(ImageFusionType imageFusion) {
        return container.get(imageFusion);
    }
}
