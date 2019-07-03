package com.yisingle.map.marker;


import android.os.Bundle;

import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.yisingle.map.marker.library.marker.BaseMarkerView;
import com.yisingle.map.marker.library.view.PointMarkerView;
import com.yisingle.map.marker.library.viewholder.MapInfoWindowViewHolder;

/**
 * @author jikun
 */
public class MainActivity extends BaseMapActivity {

    private PointMarkerView<String> startPointMarkerView;

    private PointMarkerView<String> endMarkerView;

    private LatLng startlatLng = new LatLng(30.65769, 104.062388);


    private LatLng endlatLng = new LatLng(30.657792, 104.064759);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapView = findViewById(R.id.textureMapView);
        initMapView(savedInstanceState, mapView);


        startPointMarkerView = new PointMarkerView.Builder(getApplicationContext(), getAmap())
                .setText("文本信息start")
                .setPosition(startlatLng)
                .setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.amap_start))
                .create();

        startPointMarkerView.bindInfoWindowView(new BaseMarkerView.BaseInfoWindowView<String>(R.layout.info_window, "start") {
            @Override
            public void bindData(MapInfoWindowViewHolder viewHolder, String data) {
                viewHolder.setText(R.id.tvInfoWindow, data);
            }
        });
        startPointMarkerView.addToMap();
        startPointMarkerView.showInfoWindow("开始");


        endMarkerView = new PointMarkerView.Builder(getApplicationContext(), getAmap())
                .setText("文本信息start")
                .setPosition(endlatLng)
                .setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.amap_end))
                .setAnchor(0.5f, 1)
                .create();


        endMarkerView.bindInfoWindowView(new BaseMarkerView.BaseInfoWindowView<String>(R.layout.info_window, "结束") {
            @Override
            public void bindData(MapInfoWindowViewHolder viewHolder, String data) {
                viewHolder.setText(R.id.tvInfoWindow, data);
            }
        });

        endMarkerView.addToMap();
        endMarkerView.showInfoWindow("结束");


        moveToCamera(startlatLng, endlatLng);

    }


    private void moveToCamera(LatLng start, LatLng end) {
        //设置缩放级别
        float zoom = 17;
        if (null != getAmap()) {
            //zoom - 缩放级别，[3-20]。

            LatLngBounds.Builder b = LatLngBounds.builder();

            b.include(start);
            b.include(end);
            LatLngBounds bounds = b.build();
            getAmap().animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));
        }

    }


}
