/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk.stats;

import com.netease.lava.nertc.sdk.NERtcConstants;

/**
 * @if English
 * Statistics of each remote video streams.
 * @endif
 * @if Chinese
 * 远端每条视频流的统计信息
 * @endif
 */
public class NERtcVideoLayerRecvStats {

    /**
     * @if English
     * The types of video channels. 1: mainstreams. 2: substreams.
     * @see NERtcConstants.VideoType#VIDEO_TYPE_MAIN
     * @see NERtcConstants.VideoType#VIDEO_TYPE_SUB
     * @endif
     * @if Chinese
     * 视频流通道类型。 1：主流；2：辅流。
     * @see NERtcConstants.VideoType#VIDEO_TYPE_MAIN
     * @see NERtcConstants.VideoType#VIDEO_TYPE_SUB
     * @endif
     */
    public int layerType;

    /**
     * @if English
     * The width of a remote video encoding (px).
     * @endif
     * @if Chinese
     * 远端视频编码宽度，单位为 px。
     * @endif
     */
    public int width;

    /**
     * @if English
     * The height of a remote video encoding (px).
     * @endif
     * @if Chinese
     * 远端视频编码高度，单位为 px。
     * @endif
     */
    public int height;

    /**
     * @if English
     * Received bitrate (Kbps).
     * @endif
     * @if Chinese
     * 接收到的码率，单位为 Kbps。
     * @endif
     */
    public int receivedBitrate;

    /**
     * @if English
     * Received frame rate (fps).
     * @endif
     * @if Chinese
     * 接收到的帧率，单位为 fps。
     * @endif
     */
    public int fps;


    /**
     * @if English
     * Downlink package loss rate of remote video. 
     * @endif
     * @if Chinese
     * 远端视频下行丢包率。
     * @endif
     */
    public int packetLossRate;


    /**
     * @if English
     * Decoder output frame rate (fps).
     * @endif
     * @if Chinese
     * 解码器输出帧率，单位为 fps。
     * @endif
     */
    public int decoderOutputFrameRate;


    /**
     * @if English
     * Render frame rate (fps).
     * @endif
     * @if Chinese
     * 渲染帧率，单位为 fps。
     * @endif
     */
    public int rendererOutputFrameRate;


    /**
     * @if English
     * The total freeze time (ms) of the downlink video after receiving video stream from the remote user. 
     * @endif
     * @if Chinese
     * 收到源端用户的视频流起，其下行视频卡顿累计时长，单位为 ms。
     * @endif
     */
    public long totalFrozenTime;

    /**
     * @if English
     * The average freeze time (totalFrozenTime) of downlink video as a percentage (%) of the total active time of the remote video stream (totalActiveTime) since receiving remote streams.
     * @endif
     * @if Chinese
     * 收到源端用户的视频流起，其下行视频平均卡顿率，其值为视频卡顿的累计时长占视频总有效时长的百分比。
     * @endif
     */
    public int frozenRate;

    /**
     * @if English
     * Video decoder name.
     * @endif
     * @if Chinese
     * 视频编码器名字。
     * @endif
     */
    public String decoderName;


    @Override
    /**
     * to be added
     */
    public String toString() {
        return "NERtcVideoLayerRecvStats{" +
                "layerType=" + layerType +
                ", width=" + width +
                ", height=" + height +
                ", receivedBitrate=" + receivedBitrate +
                ", fps=" + fps +
                ", packetLossRate=" + packetLossRate +
                ", decoderOutputFrameRate=" + decoderOutputFrameRate +
                ", rendererOutputFrameRate=" + rendererOutputFrameRate +
                ", totalFrozenTime=" + totalFrozenTime +
                ", frozenRate=" + frozenRate +
                ", decoderName=" + decoderName +
                '}';
    }
}
