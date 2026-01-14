/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk.stats;

import com.netease.lava.nertc.sdk.NERtcConstants;

/**
 * @if English
 * Statistics callback. 
 * @endif
 * @if Chinese
 * 统计信息回调。
 * @endif
 */
public interface NERtcStatsObserver {
    /**
     * @if English
     * Current audio statistics callback. 
     * <br>The SDK triggers this callback every two seconds to report audio statistics.
     * @param stats {@link stats.NERtcStats}
     * @endif
     * @if Chinese
     * 当前通话统计回调。
     * <br>SDK 定期向 App 报告当前通话的统计信息，每 2 秒触发一次。
     * @param stats {@link stats.NERtcStats}。
     * @endif
     */
    void onRtcStats(NERtcStats stats);

    /**
     * @if English
     * Local audio statistics callback. 
     * @param stats {@link stats.NERtcAudioSendStats}
     * @endif
     * @if Chinese
     * 本地音频流统计信息回调。
     * @param stats {@link stats.NERtcAudioSendStats}。
     * @endif
     */
    void onLocalAudioStats(NERtcAudioSendStats stats);

    /**
     * @if English
     * Statistics callback of remote audio streams array.
     * @param statsArray {@link stats.NERtcAudioRecvStats}
     * @endif
     * @if Chinese
     * 通话中远端音频流的统计信息回调数组。
     * @param statsArray {@link stats.NERtcAudioRecvStats}。
     * @endif
     */
    void onRemoteAudioStats(NERtcAudioRecvStats[] statsArray);

    /**
     * @if English
     * Local video stream statistics callback. 
     * @param stats {@link stats.NERtcVideoSendStats}
     * @endif
     * @if Chinese
     * 本地视频流统计信息回调。
     * @param stats {@link stats.NERtcVideoSendStats}。
     * @endif
     */
    void onLocalVideoStats(NERtcVideoSendStats stats);

    /**
     * @if English
     * Statistics callback of remote video streams array.
     * @param statsArray {@link stats.NERtcVideoRecvStats}
     * @endif
     * @if Chinese
     * 通话中远端视频流的统计信息回调数组。
     * @param statsArray {@link stats.NERtcVideoRecvStats}。
     * @endif
     */
    void onRemoteVideoStats(NERtcVideoRecvStats[] statsArray);


    /**
     * @if English
     * Internet quality callback of all users during the call. 
     * @param statsArray {@link stats.NERtcNetworkQualityInfo} {@link NERtcConstants.NetworkStatus}
     * @endif
     * @if Chinese
     * 通话中所有用户的网络状态回调。
     * @param statsArray {@link stats.NERtcNetworkQualityInfo} {@link NERtcConstants.NetworkStatus}。
     * @endif
     */
    void onNetworkQuality(NERtcNetworkQualityInfo[] statsArray);

}
