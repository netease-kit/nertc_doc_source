/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk.watermark;


/**
 * @if English
 * Configures canvas watermarks.
 * <br>When setting watermarks of text, timestamp and picture together, if different types of watermarks overlap, sequence picture, text and time stamp to arrange layers covering.
 * @endif
 * @if Chinese
 * 画布水印设置。
 * <br>同时设置文字、时间戳或图片水印时，如果不同类型的水印位置有重叠，会按照图片、文本、时间戳的顺序进行图层覆盖。
 * @endif
 */
public class NERtcCanvasWatermarkConfig {

    /**
     * @if English
     * Text watermark.
     * <br>Adds ten text watermarks at most. 
     * @endif
     * @if Chinese
     * 文字水印。
     * <br>最多可以添加 10 个文字水印。
     * @endif
     */
    public NERtcTextWatermarkConfig[] textWatermarks;

    /**
     * @if English
     * Time stamp watermark.
     * <br>Adds only one time stamp watermark.
     * @endif
     * @if Chinese
     * 时间戳水印。
     * <br>只能添加 1 个时间戳水印。
     * @endif
     */
    public NERtcTimestampWatermarkConfig timestampWatermark;

    /**
     * @if English
     * Image watermark. 
     * <br>Adds four image watermarks at most.
     * @endif
     * @if Chinese
     * 图片水印。
     * <br>最多可以添加 4 个图片水印。
     * @endif
     */
    public NERtcImageWatermarkConfig[] imageWatermarks;

    /**
     * toString
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        if(textWatermarks != null) {
            for (NERtcTextWatermarkConfig config : textWatermarks) {
                if(config != null) {
                    str.append(config.toString());
                } else {
                    str.append("TextWatermarkConfig:null,");
                }
            }
        } else {
            str.append("textWatermarks:null,");
        }

        if(timestampWatermark != null) {
            str.append(timestampWatermark.toString());
        } else {
            str.append("timestampWatermark:null,");
        }
        if(imageWatermarks != null) {
            for (NERtcImageWatermarkConfig config : imageWatermarks) {
                if(config != null) {
                    str.append(config.toString());
                } else {
                    str.append("ImageWatermarkConfig:null,");
                }
            }
        } else {
            str.append("NERtcImageWatermarkConfig:null");
        }

        return str.toString();
    }
}
