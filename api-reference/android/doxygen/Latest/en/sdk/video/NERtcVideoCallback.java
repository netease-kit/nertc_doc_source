/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk.video;

/** NERtcVideoCallback*/
public interface NERtcVideoCallback {
    /**
     * @if English
     * Occurs when the system captures local video data.
     * @note Sets the method with NERtcVideoView.
     * @param videoFrame For details about video data format,see {@link video.NERtcVideoFrame}.
     * @endif
     * @if Chinese
     * 本地视频数据采集回调。
     * @note 该方法通过 NERtcVideoView 进行设置。
     * @param videoFrame 视频数据格式，详细信息请参考 {@link video.NERtcVideoFrame}。
     * @endif
     */
    boolean onVideoCallback(NERtcVideoFrame videoFrame);
}
