/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk.stats;

import java.util.ArrayList;

/**
 * @if English
 * Statistics of remote video streams. 
 * @endif
 * @if Chinese
 * 远端视频流的统计信息
 * @endif
 */
public class NERtcVideoRecvStats {

    /**
     * @if English
     * User ID indicates who send video streams.
     * @endif
     * @if Chinese
     * 用户 ID，指定是哪个用户的视频流。
     * @endif
     */
    public long uid;


    /**
     * @if English
     * Received statistics from the server under each uid stream. 
     * @endif
     * @if Chinese
     * 当前 uid 每条流的接收下行统计信息。
     * @endif
     */
    public ArrayList<NERtcVideoLayerRecvStats> layers = new ArrayList<>();

    @Override
    /**
     * to be added
     */
    public String toString() {
        return "NERtcVideoRecvStats{" +
                "uid=" + uid +
                ", layers=" + layers +
                '}';
    }
}
