package com.apogiatzis.cygazetteapp.models;

/**
 * Created by andre on 19/08/2016.
 */
public class Section {

    public static final int SECTIONID_TMHMA_A = 0;
    public static final int SECTIONID_TMHMA_B = 1;
    public static final int SECTIONID_TMHMA_C = 2;

    private int sectionId;

    public Section(int id){
        this.sectionId = id;
    }

    public int getId(){
        return this.sectionId;
    }
}
