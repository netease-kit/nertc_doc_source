/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk.watermark;

import android.graphics.Bitmap;

import java.util.Locale;

/**
 * @if English
 * Image watermark configuration parameters. 
 * <br>Adds four image watermarks at most.
 * @endif
 * @if Chinese
 * 图片水印设置参数。
 * <br>最多可设置 4 个图片水印。
 * @endif
 */
public class NERtcImageWatermarkConfig {

    /**
     * @if English
     * Image watermark.
     * @endif
     * @if Chinese
     * 水印图片。
     * @endif
     */
    public Bitmap[] images;

    /**
     * @if English
     * The width of image(pixel). The default value is 0, which represents the initial width.
     * @endif
     * @if Chinese
     * 水印图片的宽度。单位为像素（pixel），默认值为 0 表示按原始图宽。
     * @endif
     */
    public int imageWidth = 0;

    /**
     * @if English
     * The height of water image(pixel). The default value is 0, which represents the initial height.
     * @endif
     * @if Chinese
     * 水印图片的高度。单位为像素（pixel），默认值为 0 表示按原始图高。
     * @endif
     */
    public int imageHeight = 0;

    /**
     * @if English
     * The horizontal distance between the top-left corner of the watermark image and the top-left corner of the video canvas (pixel) and the default value is 0.
     * @endif
     * @if Chinese
     * 水印图片左上角与视频画布左上角的水平距离。单位为像素（pixel），默认值为 0。
     * @endif
     */
    public int offsetX = 0;

    /**
     * @if English
     * The vertical distance between the top-left corner of the watermark image and the top-left corner of the video canvas (pixel) and the default value is 0.
     * @endif
     * @if Chinese
     * 水印图片左上角与视频画布左上角的垂直距离。单位为像素（pixel），默认值为 0。
     * @endif
     */
    public int offsetY = 0;

    /**
     * @if English
     * Plays the frame rate. Default: 0/sec. The system doesn't automatically switch the picture, which displays in single and static frame.
     * @endif
     * @if Chinese
     * 播放帧率。默认 0 帧/秒，即不自动切换图片，图片单帧静态显示。
     * @endif
     */
    public int fps = 0;

    /**
     * @if English
     * Whether to set the loop playback. Default: true. If the setting is false, loop playback stops after watermark arrays playback ends.
     * @endif
     * @if Chinese
     * 是否设置循环。默认循环，设置为 false 后水印数组播放完毕后消失。
     * @endif
     */
    public boolean loop = true;

    /**
     * toString
     */
    @Override
    public String toString() {
        return String.format(Locale.CHINA,"ImageWatermarkConfig:{images:%s,imageWidth:%d,imageHeight:%d" +
                        "offsetX:%d,offsetY:%d,fps:%d,loop:%b}",getImageStr(),imageWidth,imageHeight,
                offsetX,offsetY,fps,loop);
    }

    private String getImageStr() {
        StringBuilder str = new StringBuilder();
        if(images != null) {
            for (Bitmap bitmap : images) {
                if(bitmap == null) {
                    str.append("null,");
                } else {
                    str.append("bitmap[").append(bitmap.getWidth()).append("x").append(bitmap.getHeight()).append("],");
                }
            }
        }
        return str.toString();
    }

}
