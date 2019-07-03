package com.yisingle.map.marker.library.base;

import android.content.Context;

import com.amap.api.maps.AMap;

/**
 * @author jikun
 * Created by jikun on 2018/5/15.
 */
public abstract class BaseView implements IView {


    private Context context;

    private AMap amap;


    public BaseView(Context context, AMap amap) {
        this.context = context;
        this.amap = amap;
    }


    public AMap getAmap() {
        return amap;
    }

    public void setAmap(AMap amap) {
        this.amap = amap;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


    @Override
    public void destory() {
        context = null;
        amap = null;
    }

}
