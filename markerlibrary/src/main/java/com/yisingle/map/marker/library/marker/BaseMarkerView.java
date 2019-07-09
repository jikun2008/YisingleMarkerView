package com.yisingle.map.marker.library.marker;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.autonavi.amap.mapcore.IPoint;
import com.yisingle.map.marker.library.R;
import com.yisingle.map.marker.library.base.IMarkerView;
import com.yisingle.map.marker.library.param.BaseMarkerParam;
import com.yisingle.map.marker.library.utils.YiSingleDeBug;
import com.yisingle.map.marker.library.viewholder.MapInfoWindowViewHolder;

import java.math.BigDecimal;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * @author jikun
 * @date 17/5/19
 */

public abstract class BaseMarkerView<P extends BaseMarkerParam, W> extends AbstractMarkerView<P> implements IMarkerView<W> {


    private CustomInfoWindowView customInfoWindowView;

    private W infoData;


    private BaseInfoWindowView infoWindowView;

    private final String TAG = BaseMarkerView.class.getSimpleName();

    private ThreadPoolExecutor threadPoolExecutor;


    protected BaseMarkerView(Context context, AMap amap, P param) {
        super(context, amap);
        this.param = param;
        //线程池  采用  线程池数为1 的
        threadPoolExecutor = new ThreadPoolExecutor(
                1,
                1,
                50,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(3), new ThreadPoolExecutor.DiscardOldestPolicy());

    }


    @Override
    public void addToMap() {
        if (null != getAmap()) {
            if (isRemove()) {
                marker = getAmap().addMarker(param.getOptions());
            }
        }
    }

    @Override
    public void removeFromMap() {
        if (null != marker) {
            marker.remove();
            marker = null;
        }
        if (null != infoMarker) {
            infoMarker.remove();
            infoMarker = null;
        }
    }

    @Override
    public void destory() {
        removeFromMap();

        infoData = null;
        if (null != infoWindowView) {
            infoWindowView.destory();
        }
        threadPoolExecutor.shutdownNow();
        super.destory();
    }

    @Override
    public boolean isRemove() {
        return marker == null;
    }


    @Override
    public void bindInfoWindowView(BaseInfoWindowView<W> infoWindowView) {
        this.infoWindowView = infoWindowView;
        if (null == infoWindowView) {
            Log.e("BaseMarkerView", "BaseMarkerView please do not setInfoWindowView(null)");
        } else {
            infoWindowView.init(getContext());
            this.infoData = infoWindowView.getInfoData();
        }
    }

    @Override
    public void showInfoWindow(W data) {
        infoData = data;
        setShowInfoWindow(true);
        if (null != infoWindowView) {
            infoWindowView.setInfoData(data);
        }


        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                if (null == marker || null == infoWindowView) {
                    return;
                }


                MarkerOptions infoWindowParam = getInfoWindowMarkerOptions(infoWindowView);


                if (null == infoMarker || infoMarker.isRemoved()) {
                    if (null != getAmap()) {
                        infoMarker = getAmap().addMarker(infoWindowParam);

                        infoMarker.setAnchor(infoWindowParam.getAnchorU(), 1f);

                        infoMarker.setVisible(true);
                    }

                } else {

                    if (null != infoMarker) {
                        if (!infoMarker.isVisible()) {
                            infoMarker.setVisible(true);
                        }
                    }
                    if (null != infoMarker) {
                        infoMarker.setPosition(infoWindowParam.getPosition());
                    }
                    if (null != infoMarker) {
                        infoMarker.setAnchor(infoWindowParam.getAnchorU(), 1f);
                    }

                    if (null != infoMarker) {
                        infoMarker.setIcon(infoWindowParam.getIcon());
                    }


                }
            }
        });
    }


    @Override
    public void hideInfoWindow() {
        setShowInfoWindow(false);
        if (null != infoMarker) {
            infoMarker.setVisible(false);
        }
    }

    public int getCameraPaddingLeft() {
        int left = 0;
        if (null != marker && !marker.isRemoved() && marker.isVisible() && null != marker.getOptions().getIcon()) {
            left = new BigDecimal(marker.getOptions().getIcon().getWidth()).multiply(new BigDecimal(marker.getAnchorU())).intValue();
        }
        return left;
    }

    public int getCameraPaddingRight() {
        int right = 0;
        if (null != marker && !marker.isRemoved() && marker.isVisible() && null != marker.getOptions().getIcon()) {
            right = new BigDecimal(marker.getOptions().getIcon().getWidth()).multiply(new BigDecimal(1 - marker.getAnchorU())).intValue();
        }
        return right;
    }


    public int getCameraPaddingTop() {
        int top = 0;
        if (null != marker && !marker.isRemoved() && marker.isVisible() && null != marker.getOptions().getIcon()) {
            top = new BigDecimal(marker.getOptions().getIcon().getHeight()).multiply(new BigDecimal(marker.getAnchorV())).intValue();
        }
        return top;
    }

    public int getCameraPaddingBottom() {
        int bottom = 0;
        if (null != marker && !marker.isRemoved() && marker.isVisible() && null != marker.getOptions().getIcon()) {
            bottom = new BigDecimal(marker.getOptions().getIcon().getHeight()).multiply(new BigDecimal(1 - marker.getAnchorV())).intValue();
        }
        return bottom;
    }


    public int getInfoWidth() {
        int width = 0;
        if (null != infoMarker && !infoMarker.isRemoved() && infoMarker.isVisible()) {
            width = width + infoMarker.getOptions().getIcon().getWidth();
        }
        return width;
    }

    public int getInfoHeight() {
        if (null != infoMarker && !infoMarker.isRemoved() && infoMarker.isVisible()) {
            return infoMarker.getOptions().getIcon().getHeight();
        } else {
            return 0;
        }

    }


    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public W getInfoData() {
        return infoData;
    }

    public void setInfoData(W infoData) {
        this.infoData = infoData;
    }


    @Override
    public void setPosition(LatLng latLng) {
        super.setPosition(latLng);
        if (null != marker) {
            if (null != infoWindowView && isShowInfoWindow()) {
                showInfoWindow(infoData);
            }
        }


    }

    @Override
    public void setGeoPoint(IPoint geoPoint) {
        super.setGeoPoint(geoPoint);
        if (null != marker) {
            if (null != infoWindowView && isShowInfoWindow()) {
                showInfoWindow(infoData);
            }
        }
    }

    /**
     * zoom - 缩放级别，[3-20]。
     *
     * @param zoom zoom - 缩放级别，[3-20]。 @IntRange(from = 3, to = 20)
     */
    public void moveCamera(int zoom) {
        if (null != getAmap() && null != getPosition()) {
            getAmap().moveCamera(CameraUpdateFactory.newLatLngZoom(getPosition(), zoom));
        } else {
            Log.e(TAG, TAG + ":Amap==null is" + (getAmap() == null) + "---getPosition==null" + (getPosition() == null));
        }

    }

    /**
     * @author jikun
     * Created by jikun on 2018/5/30.
     */
    public static abstract class BaseInfoWindowView<W> {

        private W infoData;

        private View infoWindowView;

        private Context context;

        private int layoutId;

        private MapInfoWindowViewHolder viewHolder;

        public BaseInfoWindowView(int layoutId, W infoData) {
            this.layoutId = layoutId;
            this.infoData = infoData;

        }

        protected void init(Context context) {
            this.context = context;

            infoWindowView = LayoutInflater.from(context).inflate(R.layout.lib_info_window, null);
            LinearLayout llRealLayout = infoWindowView.findViewById(R.id.ll_real_layout);
            View view = LayoutInflater.from(context).inflate(layoutId, null);
            llRealLayout.addView(view);
            viewHolder = new MapInfoWindowViewHolder(0, view);
        }


        /**
         * 绑定数据
         *
         * @param viewHolder MapInfoWindowViewHolder
         * @param data       W
         */
        public abstract void bindData(MapInfoWindowViewHolder viewHolder, W data);


        protected View getView(int width, int height) {

            TextView seatView = infoWindowView.findViewById(R.id.seatView);
            seatView.setWidth(width);
            seatView.setHeight(height);

            if (YiSingleDeBug.isdebug) {
                seatView.setVisibility(View.VISIBLE);
                seatView.setBackgroundResource(android.R.color.holo_green_dark);
                infoWindowView.setBackgroundResource(android.R.color.holo_blue_light);
            }

            bindData(viewHolder, infoData);

            return infoWindowView;

        }


        public void destory() {

        }

        public void setInfoData(W infoData) {
            this.infoData = infoData;
        }

        public W getInfoData() {
            return infoData;
        }
    }

}
