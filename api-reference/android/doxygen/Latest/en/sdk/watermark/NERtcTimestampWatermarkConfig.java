/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk.watermark;

import java.util.Locale;

/**
 * @if English
 * Time stamp watermark settings.
 * - Adds only one time stamp with the format as yyyy-MM-dd HH:mm:ss.
 * - The time of time tamp watermark follows with the real-time.
 * @endif
 * @if Chinese
 * 时间戳水印设置。
 * - 只能添加 1 个时间戳水印，格式为 yyyy-MM-dd HH:mm:ss。
 * - 时间戳水印的时间和当前时间相同，且实时变化。
 * @endif
 */
public class NERtcTimestampWatermarkConfig {

    /**
     * @if English
     * Font size. The default value is 10, equalling to 150 pounds in the 144 dpi device.
     * @endif
     * @if Chinese
     * 字体大小。默认值为 10，相当于 144 dpi 设备上的 10 x 15 磅。
     * @endif
     */
    public int fontSize = 10;

    /**
     * @if English
     * Font color. ARGB Format. The default value is 0xFFFFFFFF, which indicates the white color.
     * @endif
     * @if Chinese
     * 字体颜色。ARGB 格式。默认为 0xFFFFFFFF，即白色。
     * @endif
     */
    public int fontColor = 0xFFFFFFFF;


    /**
     * @if English
     * The horizontal distance between the top-left corner of the watermark image and the top-left corner of the video canvas (pixel) and the value is 0 by default.
     * @endif
     * @if Chinese
     * 水印左上角与视频画布左上角的水平距离。单位为像素（pixel）。默认为 0。
     * @endif
     */
    public int offsetX = 0;

    /**
     * @if English
     * The vertical distance between the top-left corner of the watermark image and the top-left corner of the video canvas (pixel) and the value is 0 by default.
     * @endif
     * @if Chinese
     * 水印左上角与视频画布左上角的垂直距离。单位为像素（pixel）。默认为 0。
     * @endif
     */
    public int offsetY = 0;

    /**
     * @if English
     * The background color of watermark box. Format: ARGB. The default value is 0x8888888888, which indicates the gray color. Supports transparency setting.
     * @endif
     * @if Chinese
     * 水印框内背景颜色。ARGB格式，默认为 0x88888888，即灰色。支持透明度设置。
     * @endif
     */
    public int wmColor = 0x88888888;

    /**
     * @if English
     * The width of the watermark frame (px). The value is 0 by default, representing no watermark box.
     * @endif
     * @if Chinese
     * 水印框的宽度。单位为像素（pixel），默认值为 0，表示没有水印框。
     * @endif
     */
    public int wmWidth = 0;

    /**
     * @if English
     * The height of the watermark frame (px). The default value is 0, which represents no watermark box. 
     * @endif
     * @if Chinese
     * 水印框的高度。单位为像素（pixel），默认值为 0，表示没有水印框。
     * @endif
     */
    public int wmHeight = 0;

    /**
     * toString
     */
    @Override
    public String toString() {
        return String.format(Locale.CHINA,"TimestampWatermarkConfig:{fontSize:%d,fontColor:%d," +
                "offsetX:%d,offsetY:%d,wmColor:%d,wmWidth:%d,wmHeight:%d}",fontSize,
                fontColor,offsetX,offsetY,wmColor,wmWidth,wmHeight);
    }
}
