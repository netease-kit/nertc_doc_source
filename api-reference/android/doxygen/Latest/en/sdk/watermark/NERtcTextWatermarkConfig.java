/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk.watermark;


import java.util.Locale;

/**
 * @if English
 * Text watermark setting parameters.
 * <br>Adds 10 text watermark at most. 
 * @endif
 * @if Chinese
 * 文字水印设置参数。
 * <br>最多可添加 10 个文字水印。
 * @endif
 */
public class NERtcTextWatermarkConfig {

    /**
     * @if English
     * Text content. If the setting is empty, do not add text watermark.
     * - String length is not limited, which depends on the font size and watermark box size. Do not display the area beyond the watermark. 
     * - If watermark width is set, the system changes lines automatically when the length of text content is longer than the width of watermark box. If the length of text content is longer than the width of watermark box, do not display the area beyond the box. 
     * - If the width and height are not set, the system does not change lines and display the area beyond the watermark box.  
     * @endif
     * @if Chinese
     * 文字内容，设置为空时，表示不添加文字水印。
     * - 字符串长度无限制。最终显示受字体大小和水印框大小的影响。超出水印框的部分不显示。
     * - 如果设置了水印框宽度，当文字内容长度超过水印框宽度时，会自动换行，如果超出水印框高度，超出部分不显示。
     * - 未设置水印框宽度和高度时，文字不换行，超出水印框的部分不显示。
     * @endif
     */
    public String content;

    /**
     * @if English
     * Font size. The default value is 10, equal to 150 pounds in the 144 dpi device.
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
     * The background color of a watermark box. Format:ARGB. The format is 0x8888888888 by default, or gray. Supports transparency setting.
     * @endif
     * @if Chinese
     * 水印框内背景颜色。ARGB格式，默认为 0x88888888，即灰色。支持透明度设置。
     * @endif
     */
    public int wmColor = 0x88888888;

    /**
     * @if English
     * The width of a watermark box. Unit: px. The value is O by default, representing no watermark box.
     * @endif
     * @if Chinese
     * 水印框的宽度。单位为像素（pixel），默认值为 0，表示没有水印框。
     * @endif
     */
    public int wmWidth = 0;

    /**
     * @if English
     * The height of a watermark box. Unit: px. . The value is 0 by default, representing no watermark box.
     * @endif
     * @if Chinese
     * 水印框的高度。单位为像素（pixel），默认值为 0，表示没有水印框。
     * @endif
     */
    public int wmHeight = 0;

    @Override
    /**
     * toString
     */
    public String toString() {
        return String.format(Locale.CHINA,"TextWatermarkConfig:{content:%s,fontSize:%d,fontColor:%d," +
                        "offsetX:%d,offsetY:%d,wmColor:%d,wmWidth:%d,wmHeight:%d}, ",content,fontSize,
                fontColor,offsetX,offsetY,wmColor,wmWidth,wmHeight);
    }
}
