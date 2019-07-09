package com.yisingle.map.marker.library.utils;

import android.graphics.ColorFilter;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.Shader;
import android.os.Build;
import android.text.TextPaint;

/**
 * @author jikun
 * Created by jikun on 2018/5/8.
 */
public class TextPaintUtils {

    private TextPaint textPaint = new TextPaint();

    public TextPaintUtils setTextAlpha(int alpha) {
        textPaint.setAlpha(alpha);
        return this;
    }

    public TextPaintUtils setTextAntiAlias(boolean isAntiAlias) {
        textPaint.setAntiAlias(isAntiAlias);
        return this;
    }


    public TextPaintUtils setTextColor(int color) {
        textPaint.setColor(color);
        return this;
    }

    public TextPaintUtils setTextColorFilter(ColorFilter colorFilter) {
        textPaint.setColorFilter(colorFilter);
        return this;
    }

    public TextPaintUtils setTextDither(boolean isDither) {
        textPaint.setDither(isDither);
        return this;
    }

//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    public TextPaintUtils setTextElegantTextHeight(boolean elegant) {
//        textPaint.setElegantTextHeight(elegant);
//        return this;
//    }

    public TextPaintUtils setTextFakeBoldText(boolean fakeBoldText) {
        textPaint.setFakeBoldText(fakeBoldText);
        return this;
    }

    public TextPaintUtils setTextFilterBitmap(boolean filter) {
        textPaint.setFilterBitmap(filter);
        return this;
    }

    public TextPaintUtils setTextFlags(int flags) {
        textPaint.setFlags(flags);
        return this;
    }

//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    public TextPaintUtils setTextFontFeatureSettings(String settings) {
//        textPaint.setFontFeatureSettings(settings);
//        return this;
//    }


//    @RequiresApi(api = Build.VERSION_CODES.O)
//    public TextPaintUtils setTextFontVariationSettings(String fontVariationSettings) {
//        textPaint.setFontVariationSettings(fontVariationSettings);
//        return this;
//    }


    /**
     * #Paint.HINTING_OFF or #Paint.HINTING_ON.
     *
     * @param mode
     * @return
     */
    public TextPaintUtils setTextHinting(int mode) {
        textPaint.setHinting(mode);
        return this;
    }

//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    public TextPaintUtils setTextLetterSpacing(float letterSpacing) {
//        textPaint.setLetterSpacing(letterSpacing);
//        return this;
//    }


    public TextPaintUtils setTextLinearText(boolean linearText) {
        textPaint.setLinearText(linearText);
        return this;
    }

    public TextPaintUtils setTextMaskFilter(MaskFilter maskFilter) {
        textPaint.setMaskFilter(maskFilter);
        return this;
    }


    public TextPaintUtils setTextPathEffect(PathEffect pathEffect) {
        textPaint.setPathEffect(pathEffect);
        return this;
    }


    public TextPaintUtils setTextShader(Shader shader) {
        textPaint.setShader(shader);
        return this;
    }


    public TextPaintUtils setTextShadowLayer(float radius, float dx, float dy, int shadowColor) {
        textPaint.setShadowLayer(radius, dx, dy, shadowColor);
        return this;
    }

    public TextPaintUtils setTextStrikeThruText(boolean isStrikeThruText) {
        textPaint.setStrikeThruText(isStrikeThruText);
        return this;
    }

    public TextPaintUtils setTextStrokeCap(Paint.Cap cap) {
        textPaint.setStrokeCap(cap);
        return this;
    }

    public TextPaintUtils setTextStrokeJoin(Paint.Join join) {
        textPaint.setStrokeJoin(join);
        return this;
    }

    /**
     * 最大文字长度
     *
     * @param maxTextLength
     * @return
     */
    public static float getMaxTextWidth(Paint paint, int maxTextLength) {
        StringBuilder measureText = new StringBuilder();
        for (int i = 0; i < maxTextLength; i++) {
            measureText.append("徐");
        }
        float width = paint.measureText(measureText.toString());
        return width;
    }
}
