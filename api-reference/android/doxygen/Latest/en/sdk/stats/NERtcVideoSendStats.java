/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk.stats;

import java.util.ArrayList;

/**
 * @if English
 * Statistics of local video stream uploaded.
 * @endif
 * @if Chinese
 * 本地视频流上传统计信息
 * @endif
 */
public class NERtcVideoSendStats {

    /**
     * @if English
     * Statistics of each local video stream uploaded.
     * @endif
     * @if Chinese
     * 本地视频单条流上传统计信息。
     * @endif
     */
    public ArrayList<NERtcVideoLayerSendStats> videoLayers = new ArrayList<>();

    @Override
    /**
     * to be added
     */
    public String toString() {
        return "NERtcVideoSendStats{" +
                "videoLayers=" + videoLayers +
                '}';
    }
}
