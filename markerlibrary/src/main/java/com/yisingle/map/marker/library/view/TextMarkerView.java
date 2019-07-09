package com.yisingle.map.marker.library.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.yisingle.map.marker.library.base.BaseMarkerBuilder;
import com.yisingle.map.marker.library.marker.BaseMarkerView;
import com.yisingle.map.marker.library.param.TextMarkerParam;
import com.yisingle.map.marker.library.utils.TextPaintUtils;
import com.yisingle.map.marker.library.utils.YiSingleDeBug;


import java.math.BigDecimal;

/**
 * @author jikun
 * Created by jikun on 2018/5/7.
 */
public class TextMarkerView<W> extends BaseMarkerView<TextMarkerParam, W> {

    private Marker textMarker;


    private TextMarkerView(Context context, AMap amap, TextMarkerParam param) {
        super(context, amap, param);
    }


    @Override
    public void addToMap() {
        if (isRemove()) {
            super.addToMap();
            getParam().getTextMarkerOptions().position(getParam().getOptions().getPosition());
            StaticLayout staticLayout = createStaticLayout(getParam().getTextPaint());
            //设置锚点参数
            float[] anchor = getanchorByStaticLayout(staticLayout);
            getParam().getTextMarkerOptions().anchor(anchor[0], anchor[1]);
            getParam().getTextMarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(getTextBitMap(staticLayout)));
            getParam().getTextMarkerOptions().zIndex(getParam().getOptions().getZIndex());
            textMarker = getAmap().addMarker(getParam().getTextMarkerOptions());

            getMarker().setVisible(getParam().getTextMarkerOptions().isVisible() && getParam().isPointShow());
            textMarker.setVisible(getParam().getTextMarkerOptions().isVisible());


        }

    }


    @Override
    public void removeFromMap() {
        super.removeFromMap();
        if (null != textMarker) {
            textMarker.remove();
            textMarker = null;
        }

    }


    public void changeUI(LatLng latLng, String text) {
        setPosition(latLng);
        setText(text);

    }

    public void setTextPointShow(boolean isshow) {
        getParam().setPointShow(isshow);
        if (null != getMarker() && null != textMarker) {
            getMarker().setVisible(getParam().getTextMarkerOptions().isVisible() && getParam().isPointShow());
            textMarker.setVisible(getParam().getTextMarkerOptions().isVisible());
        }
    }

    @Override
    public void setVisible(boolean isVisible) {
        getParam().getTextMarkerOptions().visible(isVisible);
        if (null != getMarker() && null != textMarker) {
            getMarker().setVisible(getParam().getTextMarkerOptions().isVisible() && getParam().isPointShow());
            textMarker.setVisible(getParam().getTextMarkerOptions().isVisible());
        }
    }

    public void draw(LatLng latLng, String text) {
        setPosition(latLng);
        setText(text);
    }

    @Override
    public void setPosition(LatLng latLng) {
        super.setPosition(latLng);
        if (null != textMarker) {
            textMarker.setPosition(latLng);
        }
        setText(getParam().getText());
    }

    public void setText(String text) {
        getParam().setText(text);
        if (isRemove()) {
            addToMap();
        } else {
            if (null != textMarker) {

                StaticLayout staticLayout = createStaticLayout(getParam().getTextPaint());
                BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(getTextBitMap(staticLayout));
                textMarker.getOptions().icon(bitmapDescriptor);
                textMarker.setIcon(bitmapDescriptor);

                //设置锚点参数
                float[] anchor = getanchorByStaticLayout(staticLayout);
                textMarker.setAnchor(anchor[0], anchor[1]);
            }

        }

    }

    public void setTextSize(float textsize) {
        getParam().getTextPaint().setTextSize(textsize);
        setText(getParam().getText());

    }


    public void setTextColor(int color) {
        getParam().getTextPaint().setColor(color);
        setText(getParam().getText());
    }

    @Override
    public void setZIndex(float z) {
        super.setZIndex(z);
        if (null != textMarker) {
            textMarker.setZIndex(z);
        }

    }


    @Override
    public boolean isVisible() {
        return textMarker.isVisible();
    }

    public String getText() {
        return getParam().getText();

    }


    @Override
    public void destory() {
        removeFromMap();
        super.destory();
    }

    @Override
    public boolean isRemove() {
        return textMarker == null;
    }


    private Bitmap getTextBitMap(StaticLayout staticLayout) {
        //获取最大MaxWidth


        Bitmap bitmap = Bitmap.createBitmap(staticLayout.getWidth() + getParam().getPaddingLeftOrRight(),
                staticLayout.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.save();
        translateCanvas(getParam().getAlign(), canvas);
        //测试背景的时候使用一般不使用
        if (YiSingleDeBug.isdebug) {
            testDrawBack(canvas, staticLayout);
        }

        drawTextStroke(canvas);
        staticLayout.draw(canvas);

        canvas.restore();
        return bitmap;
    }

    @Override
    public int getCameraPaddingBottom() {
        int paddingBottom = 0;
        if (null != textMarker && !textMarker.isRemoved() && textMarker.isVisible() && !TextUtils.isEmpty(getParam().getText())) {
            paddingBottom = new BigDecimal(textMarker.getOptions().getIcon().getHeight())
                    .intValue();
        }
        return paddingBottom;
    }

    @Override
    public int getCameraPaddingTop() {
        int paddingTop = 0;
        if (null != textMarker && !textMarker.isRemoved() && textMarker.isVisible() && !TextUtils.isEmpty(getParam().getText())) {
            paddingTop = new BigDecimal(textMarker.getOptions().getIcon().getHeight())
                    .multiply(new BigDecimal(textMarker.getAnchorV()))
                    .multiply(new BigDecimal(2))
                    .setScale(0, BigDecimal.ROUND_HALF_UP)
                    .intValue();
        }
        return paddingTop;
    }

    @Override
    public int getCameraPaddingLeft() {
        int paddingLeft = 0;
        switch (getParam().getAlign()) {
            case TextMarkerParam.TextAlign.LEFT:
                if (null != marker && !marker.isRemoved() && marker.isVisible()) {
                    paddingLeft = marker.getOptions().getIcon().getWidth() / 2;
                }
                break;
            case TextMarkerParam.TextAlign.CENTER:
                if (null != textMarker && !textMarker.isRemoved() && textMarker.isVisible() && !TextUtils.isEmpty(getParam().getText())) {
                    paddingLeft = textMarker.getOptions().getIcon().getWidth() / 2;
                }
                break;
            case TextMarkerParam.TextAlign.RIGHT:

                if (null != marker && !marker.isRemoved() && marker.isVisible()) {
                    paddingLeft = paddingLeft + marker.getOptions().getIcon().getWidth() / 2;
                }
                if (null != textMarker && !textMarker.isRemoved() && textMarker.isVisible() && !TextUtils.isEmpty(getParam().getText())) {
                    paddingLeft = paddingLeft + textMarker.getOptions().getIcon().getWidth();
                }
                break;
            default:
                break;
        }
        return paddingLeft;
    }

    @Override
    public int getCameraPaddingRight() {
        int paddingRight = 0;
        switch (getParam().getAlign()) {
            case TextMarkerParam.TextAlign.LEFT:

                if (null != marker && !marker.isRemoved() && marker.isVisible()) {
                    paddingRight = paddingRight + marker.getOptions().getIcon().getWidth() / 2;
                }
                if (null != textMarker && !textMarker.isRemoved() && textMarker.isVisible() && !TextUtils.isEmpty(getParam().getText())) {
                    paddingRight = paddingRight + textMarker.getOptions().getIcon().getWidth();
                }
                break;
            case TextMarkerParam.TextAlign.CENTER:
                if (null != textMarker && !textMarker.isRemoved() && textMarker.isVisible() && !TextUtils.isEmpty(getParam().getText())) {
                    paddingRight = textMarker.getOptions().getIcon().getWidth() / 2;
                }
                break;
            case TextMarkerParam.TextAlign.RIGHT:
                if (null != marker && !marker.isRemoved() && marker.isVisible()) {
                    paddingRight = marker.getOptions().getIcon().getWidth() / 2;
                }
                break;
            default:
                break;
        }
        return paddingRight;
    }


    /**
     * 画描边
     *
     * @param canvas Canvas
     */
    private void drawTextStroke(Canvas canvas) {
        TextPaint textPaint = getParam().getStrokeTextPaint();
        textPaint.setTextSize(getParam().getTextPaint().getTextSize());
        StaticLayout staticLayout = createStaticLayout(textPaint);
        staticLayout.draw(canvas);
    }


    private void testDrawBack(Canvas canvas, StaticLayout staticLayout) {
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#FF3030"));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0, 0, staticLayout.getWidth(), staticLayout.getHeight(), paint);
    }


    private void testDrawTextBack(Canvas canvas, StaticLayout staticLayout, Paint.FontMetrics fontMetrics) {
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#4876FF"));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0, fontMetrics.top, staticLayout.getWidth(), fontMetrics.bottom, paint);
    }

    /**
     * 根据staticLayout设置锚点参数
     *
     * @param staticLayout StaticLayout
     * @return float[]
     */
    private float[] getanchorByStaticLayout(StaticLayout staticLayout) {


        float[] anchor = new float[2];


        BigDecimal y = new BigDecimal(0);
        if (staticLayout.getLineCount() > 0) {

            Paint.FontMetrics fontMetrics = getParam().getTextPaint().getFontMetrics();

            BigDecimal textHeight = new BigDecimal(fontMetrics.descent - fontMetrics.ascent);


            y = textHeight.divide(new BigDecimal(staticLayout.getHeight()).multiply(new BigDecimal(2)), 2, BigDecimal.ROUND_HALF_UP);

        }
        switch (getParam().getAlign()) {
            case TextMarkerParam.TextAlign.LEFT:
                anchor[0] = 0;
                anchor[1] = y.floatValue();

                break;
            case TextMarkerParam.TextAlign.CENTER:
                anchor[0] = 0.5f;
                anchor[1] = 0.5f;
                break;
            case TextMarkerParam.TextAlign.RIGHT:
                anchor[0] = 1f;
                anchor[1] = y.floatValue();
                break;

            default:
                anchor[0] = 0.5f;
                anchor[1] = 0.5f;
                break;
        }
        return anchor;

    }


    private void translateCanvas(int align, Canvas canvas) {
        switch (align) {
            case TextMarkerParam.TextAlign.LEFT:
                canvas.translate(getParam().getPaddingLeftOrRight(), 0);

                break;
            case TextMarkerParam.TextAlign.CENTER:
                canvas.translate(0, 0);

                break;
            case TextMarkerParam.TextAlign.RIGHT:
                //这里为什么和left不同
                //因为StaticLayout.draw的时候是靠左画文字的，如果这个时候向左移动后 会发生移动文字显示不全的BUG
                //又因为靠着右边
                // 所以我们可以通过----Bitmap.createBitmap(width+padding,height, Bitmap.Config.ARGB_8888)-----
                //----------width加上padding添加画布的宽度来达到显示效果  所以是一样的
                canvas.translate(0, 0);
                break;
            default:
                break;
        }
    }


    private StaticLayout createStaticLayout(TextPaint textPaint) {


        //如果设置的文本长度小于最大文字长度。那么以设置的文本长度为length传给StaticLayout
        int length = getParam().getText().length() < getParam().getMaxTextLength() ? getParam().getText().length() : getParam().getMaxTextLength();

        float maxWidth = TextPaintUtils.getMaxTextWidth(getParam().getTextPaint(), length);
        StaticLayout staticLayout = new StaticLayout(getParam().getText(),
                textPaint,
                (int) maxWidth, Layout.Alignment.ALIGN_NORMAL,
                getParam().getTextRowSpaceMult(),
                getParam().getTextRowSpaceAdd(),
                false);
        return staticLayout;
    }


    public final static class Builder extends BaseMarkerBuilder<Builder, TextMarkerParam> {


        public Builder(Context context, AMap map) {
            super(context, map);
        }

        @Override
        protected TextMarkerParam returnDefaultParam() {
            TextMarkerParam textMarkerParam = new TextMarkerParam();
            return textMarkerParam;
        }

        @Override
        public <W> TextMarkerView<W> create() {
            TextMarkerView<W> textMarkerView = new TextMarkerView<W>(getContext(), getMap(), getParam());
            return textMarkerView;
        }


        @Override
        protected Builder getChild() {
            return this;
        }


        public Builder setTextPaint(TextPaint textPaint) {
            getParam().setTextPaint(textPaint);
            return this;
        }

        public Builder setText(String text) {
            getParam().setText(text);
            return this;
        }


        public Builder setTextVisible(boolean visible) {
            getParam().getTextMarkerOptions().visible(visible);
            return this;
        }

        @Override
        public Builder setVisible(boolean visible) {
            getParam().getTextMarkerOptions().visible(visible);
            return this;
        }

        public Builder setTextPaddingLeftOrRight(int padding) {
            getParam().setPaddingLeftOrRight(padding);
            return this;
        }

        /**
         * @param textSpaceMult @FloatRange(from = 1f)
         * @return Builder
         */
        public Builder setTextRowSpaceMult(float textSpaceMult) {
            getParam().setTextRowSpaceMult(textSpaceMult);
            return this;
        }


        /**
         * @param textSpaceAdd @IntRange(from = 0)
         * @return Builder
         */
        public Builder setTextRowSpaceAdd(int textSpaceAdd) {
            getParam().setTextRowSpaceAdd(textSpaceAdd);
            return this;
        }


        public Builder setTextMaxTextLength(int maxTextLength) {
            getParam().setMaxTextLength(maxTextLength);
            return this;
        }

        public Builder setTextPointShow(boolean isshow) {
            getParam().setPointShow(isshow);
            return this;
        }


        /**
         * 设置描边的范围
         *
         * @param width 范围
         * @return Builder
         */
        public Builder setTextStrokeWidth(float width) {
            getParam().getStrokeTextPaint().setStrokeWidth(width);
            return this;
        }

        /**
         * 设置描边的颜色值
         *
         * @param color color
         * @return Builder
         */
        public Builder setTextStrokeColor(int color) {
            getParam().getStrokeTextPaint().setColor(color);
            return this;
        }


        /**
         * @param algin @TextMarkerParam.TextAlign
         * @return Builder
         */
        public Builder setTextAlign(int algin) {
            getParam().setAlign(algin);

            return this;
        }


        public Builder setTextSize(float textSize) {
            getParam().getTextPaint().setTextSize(textSize);
            return this;
        }


        public Builder setTextColor(int color) {
            getParam().getTextPaint().setColor(color);
            return this;
        }


        public Builder setTextPointIcon(BitmapDescriptor bitmapDescriptor) {
            setIcon(bitmapDescriptor);
            return this;
        }


    }


}
