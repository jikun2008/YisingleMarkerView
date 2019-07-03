package com.yisingle.map.marker.library.base;

/**
 * @author jikun
 * Created by jikun on 2018/5/11.
 */
public interface IView {


    /**
     * 添加到地图上
     */
    void addToMap();

    /**
     * 从地图上移除
     */
    void removeFromMap();

    /**
     * 从地图上摧毁
     */
    void destory();

    /**
     * 是否移除
     * @return boolean
     */
    boolean isRemove();


}
