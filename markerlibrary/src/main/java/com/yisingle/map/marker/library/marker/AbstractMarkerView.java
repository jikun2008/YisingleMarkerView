package com.yisingle.map.marker.library.marker;

import android.content.Context;
import android.view.View;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.animation.Animation;
import com.autonavi.amap.mapcore.IPoint;
import com.yisingle.map.marker.library.base.BaseView;
import com.yisingle.map.marker.library.param.BaseMarkerParam;
import com.yisingle.map.marker.library.utils.InfoWindowUtils;
import com.yisingle.map.marker.library.utils.ViewToBitMapUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jikun
 * Created by jikun on 2018/4/20.
 */
public abstract class AbstractMarkerView<P extends BaseMarkerParam> extends BaseView {


    protected Marker marker = null;

    protected Marker infoMarker = null;


    protected P param;

    private boolean isShowInfoWindow;


    private List<LatLng> latLngList = new ArrayList<>();

    private OnMoveListener moveListener;


    public AbstractMarkerView(Context context, AMap amap) {
        super(context, amap);


    }


    public void setAlpha(float alpha) {
        if (null != param && null != param.getOptions()) {
            param.getOptions().alpha(alpha);
        }
        if (null != marker) {
            marker.setAlpha(alpha);
        }

    }

    public void setAnchor(float var1, float var2) {
        if (null != param && null != param.getOptions()) {
            param.getOptions().anchor(var1, var2);
        }
        if (null != marker) {
            marker.setAnchor(var1, var2);
        }
    }

    public void setAnimation(Animation animation) {

        if (null != marker) {
            marker.setAnimation(animation);
        }
    }

    public void setAnimationListener(Animation.AnimationListener listener) {

        if (null != marker) {
            marker.setAnimationListener(listener);
        }
    }

    public void setAutoOverturnInfoWindow(boolean isauto) {
        if (null != param && null != param.getOptions()) {
            param.getOptions().autoOverturnInfoWindow(isauto);
        }
        if (null != marker) {
            marker.setAutoOverturnInfoWindow(isauto);
        }
    }

    public void setBelowMaskLayer(boolean isblew) {
        if (null != param && null != param.getOptions()) {
            param.getOptions().belowMaskLayer(isblew);
        }
        if (null != marker) {
            marker.setBelowMaskLayer(isblew);
        }
    }

    public void setClickable(boolean clickable) {

        if (null != marker) {
            marker.setClickable(clickable);
        }
    }

    public void setDisplayLevel(int level) {
        if (null != param && null != param.getOptions()) {
            param.getOptions().displayLevel(level);
        }
        if (null != marker) {
            marker.setDisplayLevel(level);
        }
    }

    public void setDraggable(boolean draggable) {
        if (null != param && null != param.getOptions()) {
            param.getOptions().draggable(draggable);
        }
        if (null != marker) {
            marker.setDraggable(draggable);
        }
    }

    public void setFixingPointEnable(boolean enable) {

        if (null != marker) {
            marker.setFixingPointEnable(enable);
        }
    }

    public void setFlat(boolean flat) {
        if (null != param && null != param.getOptions()) {
            param.getOptions().setFlat(flat);
        }
        if (null != marker) {
            marker.setFlat(flat);
        }
    }

    public void setGeoPoint(IPoint geoPoint) {
        if (null != marker) {
            marker.setGeoPoint(geoPoint);
        }
        if (null != infoMarker) {
            infoMarker.setGeoPoint(geoPoint);
        }
    }

    public void setIcon(BitmapDescriptor icon) {
        if (null != param && null != param.getOptions()) {
            param.getOptions().icon(icon);
        }
        if (null != marker) {
            marker.setIcon(icon);
        }
    }

    public void setIcons(ArrayList<BitmapDescriptor> icons) {
        if (null != param && null != param.getOptions()) {
            param.getOptions().icons(icons);
        }
        if (null != marker) {
            marker.setIcons(icons);
        }
    }


    public void setObject(Object obj) {

        if (null != marker) {
            marker.setObject(obj);
        }
    }

    public void setPeriod(int period) {
        if (null != param && null != param.getOptions()) {
            param.getOptions().period(period);
        }
        if (null != marker) {
            marker.setPeriod(period);
        }
    }

    public void setPosition(LatLng latLng) {
        if (null != param && null != param.getOptions()) {
            param.getOptions().position(latLng);
        }
        if (null != marker) {
            marker.setPosition(latLng);
        }
        if (null != marker) {
            marker.setPosition(latLng);
        }
    }


    public void setPositionByPixels(int x, int y) {
        if (null != marker) {
            marker.setPositionByPixels(x, y);
        }
    }

    public void setPositionNotUpdate(LatLng latLng) {
        if (null != param && null != param.getOptions()) {
            param.getOptions().position(latLng);
        }
        if (null != marker) {
            marker.setPositionNotUpdate(latLng);
        }
    }

    public void setRotateAngle(float angle) {
        if (null != param && null != param.getOptions()) {
            param.getOptions().rotateAngle(angle);
        }
        if (null != marker) {
            marker.setRotateAngle(angle);
        }
    }

    public void setRotateAngleNotUpdate(float angle) {
        if (null != param && null != param.getOptions()) {
            param.getOptions().rotateAngle(angle);
        }
        if (null != marker) {
            marker.setRotateAngleNotUpdate(angle);
        }
    }

    public void setSnippet(String spippet) {
        if (null != param && null != param.getOptions()) {
            param.getOptions().snippet(spippet);
        }
        if (null != marker) {
            marker.setSnippet(spippet);
        }
    }

    public void setTitle(String title) {
        if (null != param && null != param.getOptions()) {
            param.getOptions().title(title);
        }
        if (null != marker) {
            marker.setTitle(title);
        }
    }

    public void setToTop() {
        if (null != marker) {
            marker.setToTop();
        }
    }

    public void setVisible(boolean isVisible) {
        if (null != param && null != param.getOptions()) {
            param.getOptions().visible(isVisible);
        }
        if (null != marker) {
            marker.setVisible(isVisible);
        }
    }

    public void setZIndex(float z) {
        if (null != param && null != param.getOptions()) {
            param.getOptions().zIndex(z);
        }
        if (null != marker) {
            marker.setZIndex(z);
        }
    }


    /**
     * @return 获取Marker覆盖物的透明度, 透明度范围[0, 1] 1为不透明,默认值为1
     */
    public float getAlpha() {
        return marker.getAlpha();
    }


    /**
     * 。
     *
     * @return 返回Marker动画帧的图标列表
     */
    public ArrayList<BitmapDescriptor> getIcons() {
        return marker.getIcons();
    }


    /**
     * @return 返回Marker 的Id，每个marker 的唯一标识，用来区分不同的Marker。
     */
    public String getId() {
        return marker.getId();
    }


    /**
     * @return 获取Marker覆盖物的附加信息对象，即自定义的Marker的属性。
     */
    public Object getObject() {
        return marker.getObject();

    }
//
//    public getOptions(){
//
//    }

    /**
     * @return 得到多少帧刷新一次图片资源。
     */
    public int getPeriod() {
        return marker.getPeriod();
    }


    /**
     * @return 获取 Marker 覆盖物的位置坐标。
     */
    public LatLng getPosition() {
        if (null != marker) {
            return marker.getPosition();
        }
        return null;

    }


    /**
     * @return 获取 Marker覆盖物的图片旋转角度，从正北开始，逆时针计算。
     */
    public float getRotateAngle() {
        return marker.getRotateAngle();

    }

    /**
     * @return 获取Marker 覆盖物的文字片段。
     */
    public String getSnippet() {
        return marker.getSnippet();
    }


    /**
     * @return 获取Marker 覆盖物的标题。
     */
    public String getTitle() {
        return marker.getTitle();
    }


    /**
     * @return 获取Marker覆盖物的z轴值。
     */
    public float getZIndex() {
        return marker.getZIndex();
    }


    /**
     * @return 获取Maker覆盖物的点击状态, 可以通过 Marker.setClickable(boolean) 设置是否可以点击
     */
    public boolean isClickable() {
        return marker.isClickable();
    }

    /**
     * @return 获得Marker覆盖物的拖拽状态。
     */
    public boolean isDraggable() {
        return marker.isDraggable();
    }

    /**
     * @return 返回Marker覆盖物是否是平贴在地图上。
     */
    public boolean isFlat() {
        return marker.isFlat();
    }


    public void setShowInfoWindow(boolean showInfoWindow) {
        isShowInfoWindow = showInfoWindow;
    }

    public boolean isShowInfoWindow() {
        return isShowInfoWindow;
    }


    public boolean isVisible() {
        if (null != marker) {
            return marker.isVisible();
        } else {
            return false;
        }
    }


    public P getParam() {
        return param;
    }

    public void setParam(P param) {
        this.param = param;
    }


    public OnMoveListener getMoveListener() {
        return moveListener;
    }

    public void setMoveListener(OnMoveListener moveListener) {
        this.moveListener = moveListener;
    }


    protected MarkerOptions getInfoWindowMarkerOptions(BaseMarkerView.BaseInfoWindowView infoWindowView) {

        //position 坐标
        MarkerOptions markerOptions = new MarkerOptions();
        if (null != marker) {
            param.getOptions().position(marker.getPosition());
        }
        markerOptions.position(param.getOptions().getPosition());
        //position 坐标
        //icon 图片

        int width = getParam().getOptions().getIcon().getWidth();
        int height = InfoWindowUtils.calueInfoWindowSpaceHeight(getParam().getOptions());
        View view = infoWindowView.getView(width, height);
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(ViewToBitMapUtil.convertBitmapFromXML(view));
        markerOptions.icon(bitmapDescriptor);
        //icon 图片
        //anchor 锚点
        float anchorH = InfoWindowUtils.calueHorizontalInfoAnchor(getParam().getOptions(), bitmapDescriptor);
        markerOptions.anchor(anchorH, 1f);


        return markerOptions;

    }

    public interface OnMoveListener {

        void onMove(LatLng latLng);

    }


}
