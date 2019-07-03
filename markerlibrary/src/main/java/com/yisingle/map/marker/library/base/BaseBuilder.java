package com.yisingle.map.marker.library.base;

import android.content.Context;

import com.amap.api.maps.AMap;

/**
 * @author jikun
 * Created by jikun on 2018/5/15.
 */
public abstract class BaseBuilder {

    private Context context;
    private AMap map;


    public BaseBuilder(Context context, AMap map) {
        this.context = context;
        this.map = map;
    }


    public Context getContext() {
        return context;
    }


    public AMap getMap() {
        return map;
    }


}
