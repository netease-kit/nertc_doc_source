/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk.video;


/**
 * @if English 
 * The camera capturer configuration. 
 * @endif
 * @if Chinese
 * 摄像头采集配置。
 * @endif
 */
public class NERtcCameraCaptureConfig {





    /**
     * @if English 
     * The width (px) of the video image captured by the local camera.
     * <br>The video encoding resolution is expressed in width x height. It is used to set the video encoding resolution and measure the encoding quality. 
     * - captureWidth: the pixels of the video frame on the horizontal axis, that is, the custom width.
     * - captureHeight： the pixels of the video frame on the horizontal axis, that is, the custom height.
     * @note 
     * -
     * - In manual mode, if the specified collection size is smaller than the encoding size, the encoding parameters will be aligned down to the collection size range. 
     * @endif
     * @if Chinese
     * 本地采集的视频宽度，单位为 px。
     * <br>视频编码分辨率以宽 x 高表示，用于设置视频编码分辨率，以衡量编码质量。
     * - captureWidth 表示视频帧在横轴上的像素，即自定义宽。
     * - captureHeight 表示视频帧在横轴上的像素，即自定义高。
     * @note 
     * - 如果您不需要次功能，只需要将宽度设置为0
     * - 手动模式下，如果指定的采集宽高小于编码宽高，编码参数会被下调对齐到采集的尺寸范围内。
     * @endif
     */
    public int captureWidth = 0;


    /**
     * @if English 
     * The height (px) of the video image captured by the local camera.
     * <br>The video encoding resolution is expressed in width x height. It is used to set the video encoding resolution and measure the encoding quality. 
     * - captureWidth: the pixels of the video frame on the horizontal axis, that is, the custom width.
     * - captureHeight： the pixels of the video frame on the horizontal axis, that is, the custom height.
     * @note 
     * -
     * - In manual mode, if the specified collection size is smaller than the encoding size, the encoding parameters will be aligned down to the collection size range. 
     * @endif
     * @if Chinese
     * 本地采集的视频高度，单位为 px。
     * <br>视频编码分辨率以宽 x 高表示，用于设置视频编码分辨率，以衡量编码质量。
     * - captureWidth 表示视频帧在横轴上的像素，即自定义宽。
     * - captureHeight 表示视频帧在横轴上的像素，即自定义高。
     * @note 
     * - 如果您不需要次功能，只需要将高度设置为0
     * - 手动模式下，如果指定的采集宽高小于编码宽高，编码参数会被下调对齐到采集的尺寸范围内。
     * @endif
     */
    public int captureHeight = 0;

    /**
     * @if English
     * Extra rotation information of the video capture.
     * @endif
     * @if Chinese
     * 摄像头额外旋转信息。
     * @endif
     */
    public enum NERtcCaptureExtraRotation {


        /**
         * @if English
         * (default) No extra rotation, just using the system rotation parameters.
         * @endif
         * @if Chinese
         * （默认）没有额外的旋转信息，直接使用系统旋转参数处理。
         * @endif
         */
        CAPTURE_EXTRA_ROTATION_DEFAULT,


        /**
         * @if English
         * Rotate the captured image with 90 degrees clockwise, in addition to system rotation information.
         * @endif
         * @if Chinese
         * 在系统旋转信息的基础上，额外顺时针旋转90度。
         * @endif
         */
        CAPTURE_EXTRA_ROTATION_CLOCKWISE_90,


        /**
         * @if English
         * Rotate the captured image with 180 degrees, in addition to system rotation information.
         * @endif
         * @if Chinese
         * 在系统旋转信息的基础上，额外旋转180度。
         * @endif
         */
        CAPTURE_EXTRA_ROTATION_180,


        /**
         * @if English
         * Rotate the captured image with 90 degrees anti-clockwise, in addition to system rotation information.
         * @endif
         * @if Chinese
         * 在系统旋转信息的基础上，额外逆时针旋转90度。
         * @endif
         */
        CAPTURE_EXTRA_ROTATION_ANTICLOCKWISE_90,
    }



    /**
     * @if English
     * The camera capture's extra rotation information.
     * @endif
     * @if Chinese
     * 设置摄像头的额外旋转信息。
     * @endif
     */
    public NERtcCaptureExtraRotation extraRotation = NERtcCaptureExtraRotation.CAPTURE_EXTRA_ROTATION_DEFAULT;


    @Override
    
    /**
     * to be added
     */
    public String toString() {
        return "NERtcCameraCaptureConfig{" +
                ", capture_width=" + captureWidth +
                ", capture_height=" + captureHeight +
                ", extraRotation=" + this.extraRotation.ordinal()*90 +
                '}';
    }
}
