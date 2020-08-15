package com.ivan.MoviesRDFTransformer.entities;

import java.util.concurrent.atomic.AtomicInteger;

public interface Member {
    static final AtomicInteger count = new AtomicInteger(0);

    Long getId();

    String getName();

    String getUrl();
}