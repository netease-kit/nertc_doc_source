/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk.live;

import java.io.Serializable;

/**
 * @if English
 * Sets background image for pushing streams.
 * @endif
 * @if Chinese
 * 推流背景图片设置
 * @endif
 */
public class NERtcLiveStreamImageInfo implements Serializable {

    /**
     * @if English
     * String URL of placeholder images.
     * @endif
     * @if Chinese
     * 占位图片的URL。
     * @endif
     */
    public String url;

    /**
     * @if English
     * Uses X parameter to set coordinate values of a canvas in the horizontal axis.
     * <br>Defines a point (X,Y) in the coordinate axis of a canvas that fills the upper left corner of placeholder images. 
     * <br>The value range is 0 to 1920. The default value is 0. If the specified value is an odd number, the value is rounded down to the next even number.
     * @endif
     * @if Chinese
     * x 参数用于设置画布的横轴坐标值。
     * <br>通过 x 和 y 指定画布坐标中的一个点，该点将作为占位图片的左上角。
     * <br>取值范围为 0~1920，默认为 0。若设置为奇数值，会自动向下取偶。
     * @endif
     */
    public int x;

    /**
     * @if English
     * Uses Y parameter to set coordinate values of a canvas in the vertical axis.
     * <br>Defines a point (X,Y) in the coordinate axis of a canvas that fills the upper left corner of placeholder images.
     * <br>The value range is 0 to 1920. The default value is 0. If the specified value is an odd number, the value is rounded down to the next even number.
     * @endif
     * @if Chinese
     * y 参数用于设置画布的纵轴坐标值。
     * <br>通过 x 和 y 指定画布坐标中的一个点，该点将作为占位图片的左上角。
     * <br>取值范围为 0~1920，默认为 0。若设置为奇数值，会自动向下取偶。
     * @endif
     */
    public int y;

    /**
     * @if English
     * The width of placeholder image in a canvas. By default, it is main picture width.
     * <br>The value range is 0 to 1920. The default value is 0. If the specified value is an odd number, the value is rounded down to the next even number.
     * @endif
     * @if Chinese
     * 该占位图片在画布中的宽度。默认为主画面宽度。
     * <br>取值范围为 0~1920，若设置为奇数值，会自动向下取偶。
     * @endif
     */
    public int width = 0;

    /**
     * @if English
     * The height of placeholder image in a canvas. By default, it is main picture height.
     * <br>The value range is 0 to 1920. The default value is 0. If the specified value is an odd number, the value is rounded down to the next even number.
     * @endif
     * @if Chinese
     * 该占位图片在画布中的高度。默认为主画面高度。
     * <br>取值范围为 0~1920，若设置为奇数值，会自动向下取偶。
     * @endif
     */
    public int height = 0;


}
