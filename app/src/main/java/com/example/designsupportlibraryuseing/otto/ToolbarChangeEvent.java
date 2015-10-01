package com.example.designsupportlibraryuseing.otto;

/**
 * Created by 晋伟 on 2015/9/23 0023.
 */
public class ToolbarChangeEvent {
    private String mTitle = null;

    public ToolbarChangeEvent(String title) {
        this.mTitle = title;
    }

    @Override
    public String toString() {
        return mTitle;
    }

}
