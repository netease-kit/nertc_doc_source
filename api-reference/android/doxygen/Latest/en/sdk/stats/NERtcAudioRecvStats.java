/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk.stats;

import java.util.ArrayList;

/**
 * @if English
 * Audio statistics for remote users.
 * @endif
 * @if Chinese
 * 远端用户的音频统计。
 * @endif
 */
public class NERtcAudioRecvStats {

    /**
     * @if English
     * The user ID indicates who owns the video stream.
     * @endif
     * @if Chinese
     * 用户 ID，指定是哪个用户的音频流。
     * @endif
     */
    public long uid;


    /**
     * @if English
     * Stats of downstream audio streams received by the current user ID.
     * @endif
     * @if Chinese
     * 当前 uid 每条音频流的接收下行统计信息。
     * @endif
     */
    public ArrayList<NERtcAudioLayerRecvStats> layers = new ArrayList<>();


    @Override
    public String toString() {
        return "NERtcAudioRecvStats{" +
                "uid=" + uid +
                ", layers=" + layers +
                '}';
    }
}
