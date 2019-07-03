package com.yisingle.map.marker.library.utils;

import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.MarkerOptions;

import java.math.BigDecimal;

/**
 * @author jikun
 * Created by jikun on 2018/5/31.
 */
public class InfoWindowUtils {

    /**
     * 计算infoWindow占位高度
     *
     * @param markerOptions MarkerOptions
     * @return 高度
     */
    public static int calueInfoWindowSpaceHeight(MarkerOptions markerOptions) {
        return new BigDecimal(markerOptions.getAnchorV()).multiply(new BigDecimal(markerOptions.getIcon().getHeight())).intValue();
    }

    /**
     * 计算infoWindow水平锚点值
     * <p>
     * 公式为  return 值=(0.5*bigWidth-0.5*littleWidth+锚点值*littleWidth)/bigWidth
     *
     * @return 锚点值
     */
    public static float calueHorizontalInfoAnchor(MarkerOptions markerOptions, BitmapDescriptor viewBitmapDescriptor) {

        int bigWidth = viewBitmapDescriptor != null ? viewBitmapDescriptor.getWidth() : 0;
        int littleWidth = markerOptions.getIcon().getWidth();

        float anchorU = markerOptions.getAnchorU();
        if (bigWidth > littleWidth) {
            //x 的 值
            BigDecimal x = new BigDecimal(bigWidth);
            BigDecimal y = new BigDecimal(littleWidth);
            BigDecimal u = new BigDecimal(anchorU);
            BigDecimal x2 = x.divide(new BigDecimal(2), 4, BigDecimal.ROUND_HALF_UP);
            BigDecimal y2 = y.divide(new BigDecimal(2), 4, BigDecimal.ROUND_HALF_UP);
            BigDecimal uY = y.multiply(u);
            BigDecimal subtractX2Y2 = x2.subtract(y2);
            float now = subtractX2Y2.add(uY).divide(x, 4, BigDecimal.ROUND_HALF_UP).floatValue();
            return now;

        } else {
            return markerOptions.getAnchorU();
        }

    }
}
