/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */
package com.netease.lava.nertc.sdk.stats;

import com.netease.lava.nertc.sdk.audio.NERtcAudioStreamType;

/**
 * 远端单条音频流的统计信息
 */
public class NERtcAudioLayerRecvStats {

    /**
     * @if English
     * AUdio stream type.
     * @see NERtcAudioStreamType
     * @endif
     * @if Chinese
     * 音频流类型。
     * @see NERtcAudioStreamType
     * @endif
     */
    public NERtcAudioStreamType streamType;

    /**
     * @if English
     * The average bitrate (kbps) received during a statistical period.
     * @endif
     * @if Chinese
     * 统计周期内接收到的码率平均值，单位为 Kbps。
     * @endif
     */
    public int kbps;

    /**
     * @if English
     * Statistics of the frame loss rate of remote audio streams during a statistical period.
     * @endif
     * @if Chinese
     * 统计周期内的远端音频流的丢帧率。
     * @endif
     */
    public int lossRate;

    /**
     * @if English
     * The volume range is 0 to 100. 
     * @endif
     * @if Chinese
     * 音量，范围为 0 ~ 100。
     * @endif
     */
    public int volume;


    /**
     * @if English
     * The cumulative length of time (ms) that a remote user experiences an audio latency after joining a room. An audio delay is defined as an audio loss rate of 4% in a statistical period.
     * @endif
     * @if Chinese
     * 远端用户在加入房间后发生音频卡顿的累计时长 (ms)。一个统计周期内，音频丢帧率达到 4% 即记为一次音频卡顿。
     * @endif
     */
    public long totalFrozenTime;

    /**
     * @if English
     * Average audio latency of downlink audio for remote users. Its value is the cumulative duration of audio jams occurring for remote users after joining the room as a percentage of the total effective duration of audio.
     * @endif
     * @if Chinese
     * 远端用户下行音频平均卡顿率。其值为远端用户在加入房间后发生音频卡顿的累计时长占音频总有效时长的百分比。
     * @endif
     */
    public int frozenRate;


    @Override
    /**
     * to be added
     */
    public String toString() {
        return "NERtcAudioLayerRecvStats{" +
                "kbps=" + kbps +
                ", lossRate=" + lossRate +
                ", volume=" + volume +
                ", totalFrozenTime=" + totalFrozenTime +
                ", frozenRate=" + frozenRate +
                '}';
    }
}
