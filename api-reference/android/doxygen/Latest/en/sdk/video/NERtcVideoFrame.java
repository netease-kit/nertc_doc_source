/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk.video;

/**
 * @if English
 * Video data format. 
 * @endif
 * @if Chinese
 * 视频数据格式。
 * @endif
 */
public class NERtcVideoFrame {

    /**Format */
    public enum Format {

        /**
        * @if English
        * I420 format.
        * @endif
        * @if Chinese
        * I420 格式。
        * @endif
        */
        I420,

        /**
        * @if English
        * NV21 format.
        * @endif
        * @if Chinese
        * NV21 格式。
        * @endif
        */
        NV21,

        /**
        * @if English
        * RGBA format.
        * @endif
        * @if Chinese
        * RGBA 格式。
        * @endif
        */
        RGBA,

        /**
        * @if English
        * TEXTURE_OES format.
        * @endif
        * @if Chinese
        * TEXTURE_OES 格式。
        * @endif
        */
        TEXTURE_OES,

        /**
        * @if English
        * TEXTURE_RGB format.
        * @endif
        * @if Chinese
        * TEXTURE_RGB 格式。
        * @endif
        */
        TEXTURE_RGB
    }

    /**
     * @if English
     * The video width is the video resolution of video frames shown on the vertical axis. 
     * @endif
     * @if Chinese
     * 视频宽，即视频帧在纵轴上的像素。
     * @endif
     */
    public int width;
    /**
     * @if English
     * The video height is the video resolution of video frames shown on the horizontal axis.
     * @endif
     * @if Chinese
     * 视频高，即视频帧在横轴上的像素。
     * @endif
     */
    public int height;
    /**
     * @if English
     * The clockwise rotation angle of the video frame. 
     * @endif
     * @if Chinese
     * 视频顺时针旋转角度。
     * @endif
     */
    public int rotation;
    /**
     * @if English
     * Video frame format.
     * @endif
     * @if Chinese
     * 视频帧格式。
     * @endif
     */
    public Format format;

    /**
     * @if English
     * Timestamp of the video frame (ms).
     * @endif
     * @if Chinese
     * 视频时间戳，单位为毫秒。
     * @endif
     */
    public long timeStamp;

    /**
     * @if English
     * Applies video data of I420 format to facial recognition in the filtering library.
     * <br>textureWithI420 Return occurs when setting it as true. Otherwise, null. 
     * @endif
     * @if Chinese
     * I420 格式的视频数据，可用于滤镜库的人脸识别。
     * <br>textureWithI420 设置为 true 时才会返回，否则为 null。
     * @endif
     */
    public byte[] data;
    /**
     * @if English
     * The camera captures OES texture ID When injecting external videos. The filtering library will update the texture ID.
     * @endif
     * @if Chinese
     * 外部视频输入时，相机采集的 OES 纹理 ID，滤镜库处理后会更新此纹理 ID。
     * @endif
     */
    public int textureId;

    /**
     * @if English
     * Texture matrixes shown when inputting external videos.
     * @endif
     * @if Chinese
     * 外部视频输入时的纹理矩阵。
     * @endif
     */
    public float[] transformMatrix;

}
