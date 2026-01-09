/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk.stats;

import com.netease.lava.nertc.sdk.NERtcConstants;

/**
 * @if English
 * Statistics of each local video stream.
 * @endif
 * @if Chinese
 * 本地视频单条流统计信息。
 * @endif
 */
public class NERtcVideoLayerSendStats {

    /**
     * @if English
     * The types of video channels. 1: mainstreams. 2: substreams.
     * @see NERtcConstants.VideoType#VIDEO_TYPE_MAIN
     * @see NERtcConstants.VideoType#VIDEO_TYPE_SUB
     * @endif
     * @if Chinese
     * 视频流通道类型。 1：主流；2：辅流。
     * @see NERtcConstants.VideoType#VIDEO_TYPE_MAIN。
     * @see NERtcConstants.VideoType#VIDEO_TYPE_SUB。
     * @endif
     */
    public int layerType;

    /**
     * @if English
     * The width (px) of the video image captured by the local camera.
     * @endif
     * @if Chinese 
     * 本地采集的视频宽度，单位为 px。
     * @endif
     */
    public int capWidth;

    /**
     * @if English 
     * The height (px) of the video image captured by the local camera.
     * @endif
     * @if Chinese
     * 本地采集的视频高度，单位为 px。
     * @endif
     */
    public int capHeight;

    /**
     * @if English 
     * The width of a remote video encoding (px).
     * @endif
     * @if Chinese
     * 视频编码宽度，单位为 px。
     * @endif
     */
    public int width;

    /**
     * @if English
     * The height of a remote video encoding (px).
     * @endif
     * @if Chinese
     * 视频编码高度，单位为 px。
     * @endif
     */
    public int height;

    /**
     * @if English
     * Bitrate (Kbps) sent in the reported interval, which does not include the bitrate of the re-transmission video after the packet loss.
     * @endif
     * @if Chinese
     * 实际发送码率，单位为 Kbps，不包含丢包后重传视频等的发送码率。
     * @endif
     */
    public int sendBitrate;

    /**
     * @if English
     * The encoding output frame rate. 
     * @endif
     * @if Chinese
     * 编码输出帧率。
     * @endif
     */
    public int encoderOutputFrameRate;


    /**
     * @if English
     * The capture frame rate (fps) of the video.
     * @endif
     * @if Chinese
     * 采集码率。
     * @endif
     */
    public int captureFrameRate;


    /**
     * @if English
     * The target bitrate (Kbps) of the current encoder. This value is estimated by the SDK based on the current network conditions.
     * @endif
     * @if Chinese
     * 当前编码器的目标编码码率，单位为 Kbps，该码率为 SDK 根据当前网络状况预估的一个值。
     * @endif
     */
    public int targetBitrate;

    /**
     * @if English
     * The Reported encoder bitrate (Kbps).
     * @endif
     * @if Chinese
     * 编码器实际编码码率，单位为 Kbps。
     * @endif
     */
    public int encoderBitrate;

    /**
     * @if English
     * The reported sent frame rate(fps), which does not include the bitrate of the re-transmission video after the packet loss.
     * @endif
     * @if Chinese
     * 实际发送帧率，单位为 fps，不包含丢包后重传视频等的发送帧率。
     * @endif
     */
    public int sentFrameRate;


    /**
     * @if English
     * Render frame rate(fps).
     * @endif
     * @if Chinese
     * 视频渲染帧率，单位为 fps。
     * @endif
     */
    public int renderFrameRate;


    /**
     * @if English
     * Video encoder name.
     * @endif
     * @if Chinese
     * 视频编码器名字。
     * @endif
     */
    public String encoderName;

    @Override
    /**
     * to be added
     */
    public String toString() {
        return "NERtcVideoLayerSendStats{" +
                "layerType=" + layerType +
                ", capWidth=" + capWidth +
                ", capHeight=" + capHeight +
                ", width=" + width +
                ", height=" + height +
                ", sendBitrate=" + sendBitrate +
                ", encoderOutputFrameRate=" + encoderOutputFrameRate +
                ", captureFrameRate=" + captureFrameRate +
                ", targetBitrate=" + targetBitrate +
                ", encoderBitrate=" + encoderBitrate +
                ", sentFrameRate=" + sentFrameRate +
                ", renderFrameRate=" + renderFrameRate +
                ", encoderName=" + encoderName +
                '}';
    }
}
