package com.solstice.solsticechallenge.dagger.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

@Module
public class SectionsModule {

    @Provides
    @Singleton
    SectionedRecyclerViewAdapter provideSectionedRecyclerViewAdapter() {
        return new SectionedRecyclerViewAdapter();
    }
}
