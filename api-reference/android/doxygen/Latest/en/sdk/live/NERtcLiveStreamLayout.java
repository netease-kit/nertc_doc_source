/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk.live;

import android.graphics.Color;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @if English
 * Live streaming layout.
 * @endif
 * @if Chinese
 * 直播布局。
 * @endif
 */
public class NERtcLiveStreamLayout implements Serializable {

    /**
     * @if English
     * The overall width of a canvas(px). The  value range is 0 to 1920. If the specified value is an odd number, the value is rounded down to the next even number.
     * @endif
     * @if Chinese
     * 整体画布的宽度，单位为 px。取值范围为 0~1920，若设置为奇数值，会自动向下取偶。
     * @endif
     */
    public int width;

    /**
     * @if English
     * The overall height of a canvas(px). The  value range is 0 to 1920. If the specified value is an odd number, the value is rounded down to the next even number.
     * @endif
     * @if Chinese
     * 整体画布的高度，单位为 px。取值范围为 0~1920，若设置为奇数值，会自动向下取偶。
     * @endif
     */
    public int height;


    /**
     * @if English
     * Sets the background color(RGB) of video push with Color.parseColor().
     * @endif
     * @if Chinese
     * 视频推流背景色 RGB ， 直接用Color.parseColor()生成即可。
     * @endif
     */
    public int backgroundColor = Color.BLACK;


    /**
     * @if English
     * The placeholder image.
     * @endif
     * @if Chinese
     * 占位图片。
     * @endif
     */
    public NERtcLiveStreamImageInfo backgroundImg;


    /**
     * @if English
     * Layout array for members.
     * @endif
     * @if Chinese
     * 成员布局数组
     * @endif
     */
    public ArrayList<NERtcLiveStreamUserTranscoding> userTranscodingList;


}
