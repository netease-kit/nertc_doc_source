/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk.stats;

import com.netease.lava.api.model.RTCNetworkStatusType;

/** NERtcNetworkQualityInfo*/
public class NERtcNetworkQualityInfo {

    /**
     * @if English
     * The user ID indicates who owns the Internet quality statistics.
     * @endif
     * @if Chinese
     * 用户 ID，指定是哪个用户的网络质量统计。
     * @endif
     */
    public long userId;

    /**
     * @if English
     * Uplink network quality of the user.
     * @endif
     * @if Chinese
     * 该用户的上行网络质量。
     * @endif
     */
    public int upStatus = RTCNetworkStatusType.kRtcNetworkStatusUnknown;

    /**
     * @if English
     * Downlink network quality of the user. 
     * @endif
     * @if Chinese
     * 该用户的下行网络质量。
     * @endif
     */
    public int downStatus = RTCNetworkStatusType.kRtcNetworkStatusUnknown;


    @Override
    /**
     * to be added
     */
    public String toString() {
        return "NERtcNetworkQualityInfo{" +
                "userId=" + userId +
                ", upStatus=" + upStatus +
                ", downStatus=" + downStatus +
                '}';
    }
}
