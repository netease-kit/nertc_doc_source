/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk.stats;

import java.util.ArrayList;

/**
 * @if English
 * Local audio stream upload statistics.
 * @endif
 * @if Chinese
 * 本地音频流上传统计信息
 * @endif
 */
public class NERtcAudioSendStats {
    /**
     * 本地所有音频流统计信息。
     */
    public ArrayList<NERtcAudioLayerSendStats> audioLayers = new ArrayList<>();

    @Override
    public String toString() {
        return "NERtcAudioSendStats{" +
                "audioLayers=" + audioLayers +
                '}';
    }
}
