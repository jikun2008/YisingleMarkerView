package com.yisingle.map.marker.library.base;


import com.yisingle.map.marker.library.marker.BaseMarkerView;


/**
 * @author jikun
 * Created by jikun on 2018/4/18.
 */
public interface IMarkerView<W> {


    /**
     * 绑定数据
     *
     * @param infoWindowView BaseInfoWindowView
     */
    void bindInfoWindowView(BaseMarkerView.BaseInfoWindowView<W> infoWindowView);


    /**
     * 显示InfoWindow
     *
     * @param data W
     */
    void showInfoWindow(W data);

    /**
     * 隐藏InfoWindow
     */
    void hideInfoWindow();


}
