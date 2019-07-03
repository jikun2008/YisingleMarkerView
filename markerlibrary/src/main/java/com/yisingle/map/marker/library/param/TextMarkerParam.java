package com.yisingle.map.marker.library.param;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.FloatRange;
import android.support.annotation.IntDef;
import android.support.annotation.IntRange;
import android.text.TextPaint;

import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.MarkerOptions;

import com.yisingle.map.marker.library.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author jikun
 * Created by jikun on 2018/5/16.
 */
public class TextMarkerParam extends BaseMarkerParam {
    private @TextAlign
    int align;

    private int maxTextLength = 6;

    private String text = "";

    private int paddingLeftOrRight;

    private float textRowSpaceMult;

    private int textRowSpaceAdd;

    /**
     * 是否只显示文字
     */
    private boolean isPointShow = true;

    /**
     * 文字描边默认值
     */
    private TextPaint strokeTextPaint = new TextPaint();

    private TextPaint textPaint = new TextPaint();


    private MarkerOptions textMarkerOptions = new MarkerOptions();


    public TextMarkerParam() {

        textRowSpaceMult = 1f;
        textRowSpaceAdd = 0;

        getOptions().anchor(0.5f, 0.5f);
        getOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap.yisingle_hot_point));
        getOptions().zIndex(1);


        //这里设置一些默认值
        textPaint.setColor(Color.parseColor("#00C3A6"));
        textPaint.setFakeBoldText(true);
        textPaint.setTextSize(24);
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL_AND_STROKE);


        // 描边宽度--------描边颜色
        strokeTextPaint.setTextSize(24);
        strokeTextPaint.setFakeBoldText(true);
        strokeTextPaint.setAntiAlias(true);
        strokeTextPaint.setStrokeWidth(6);
        strokeTextPaint.setStyle(Paint.Style.STROKE);
        strokeTextPaint.setColor(Color.parseColor("#FFFFFF"));


        paddingLeftOrRight = 10;
        maxTextLength = 6;
        align = TextAlign.LEFT;
    }

    public @TextAlign
    int getAlign() {
        return align;
    }

    public void setAlign(@TextAlign int align) {
        this.align = align;
    }

    public int getMaxTextLength() {
        return maxTextLength;
    }

    public void setMaxTextLength(int maxTextLength) {
        this.maxTextLength = maxTextLength;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPaddingLeftOrRight() {
        return paddingLeftOrRight;
    }

    public void setPaddingLeftOrRight(int paddingLeftOrRight) {
        this.paddingLeftOrRight = paddingLeftOrRight;
    }

    public TextPaint getTextPaint() {
        return textPaint;
    }

    public void setTextPaint(TextPaint textPaint) {
        this.textPaint = textPaint;
    }

    public TextPaint getStrokeTextPaint() {
        return strokeTextPaint;
    }

    public void setStrokeTextPaint(TextPaint strokeTextPaint) {
        this.strokeTextPaint = strokeTextPaint;
    }

    public MarkerOptions getTextMarkerOptions() {
        return textMarkerOptions;
    }

    public void setTextMarkerOptions(MarkerOptions textMarkerOptions) {
        this.textMarkerOptions = textMarkerOptions;
    }

    public float getTextRowSpaceMult() {
        return textRowSpaceMult;
    }

    public void setTextRowSpaceMult(@FloatRange(from = 1f) float textRowSpaceMult) {
        this.textRowSpaceMult = textRowSpaceMult;
    }

    public int getTextRowSpaceAdd() {
        return textRowSpaceAdd;
    }

    public void setTextRowSpaceAdd(@IntRange(from = 0) int textRowSpaceAdd) {
        this.textRowSpaceAdd = textRowSpaceAdd;
    }


    public boolean isPointShow() {
        return isPointShow;
    }

    public void setPointShow(boolean pointShow) {
        isPointShow = pointShow;
    }

    //添加支持注解的依赖到你的项目中，需要在build.gradle文件中的依赖块中添加：
    //dependencies { compile 'com.android.support:support-annotations:24.2.0' }
    @IntDef({TextAlign.LEFT, TextAlign.CENTER, TextAlign.RIGHT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TextAlign {

        int LEFT = 0;
        int CENTER = 1;
        int RIGHT = 2;
    }
}
