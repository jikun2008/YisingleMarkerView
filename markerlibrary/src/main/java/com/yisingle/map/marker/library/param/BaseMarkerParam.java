package com.yisingle.map.marker.library.param;

import com.amap.api.maps.model.MarkerOptions;

/**
 * @author jikun
 * Created by jikun on 2018/5/21.
 */
public class BaseMarkerParam {


    private MarkerOptions options = new MarkerOptions();


    public MarkerOptions getOptions() {
        return options;
    }

    public void setOptions(MarkerOptions options) {
        this.options = options;
    }


}
