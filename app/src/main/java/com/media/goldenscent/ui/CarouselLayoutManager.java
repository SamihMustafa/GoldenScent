package com.media.goldenscent.ui;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
/**
 * Created by Samih on 17-Jun-18.
 */
public class CarouselLayoutManager extends LinearLayoutManager{


    private boolean isScrollEnabled = false;

    public CarouselLayoutManager(Context context) {
        super(context);
        super.setOrientation(LinearLayoutManager.HORIZONTAL);
        super.setReverseLayout(true);
    }

    @Override
    public boolean isAutoMeasureEnabled() {
        return false;
    }

    public void setScrollEnabled(boolean flag){
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollHorizontally() {
        return isScrollEnabled && super.canScrollHorizontally();
    }


}
