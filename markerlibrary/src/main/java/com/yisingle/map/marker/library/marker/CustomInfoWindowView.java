package com.yisingle.map.marker.library.marker;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.maps.model.Marker;
import com.yisingle.map.marker.library.R;
import com.yisingle.map.marker.library.utils.YiSingleDeBug;
import com.yisingle.map.marker.library.viewholder.MapInfoWindowViewHolder;

/**
 * @author jikun
 * Created by jikun on 2018/9/19.
 */
class CustomInfoWindowView {


    protected Marker infoMarker = null;


    /**
     * @author jikun
     * Created by jikun on 2018/5/30.
     */
    public static abstract class BaseInfoWindowView<W> {

        private W infoData;

        private View infoWindowView;

        private Context context;

        private @LayoutRes
        int layoutId;

        private MapInfoWindowViewHolder viewHolder;

        public BaseInfoWindowView(@LayoutRes int layoutId, W infoData) {
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
