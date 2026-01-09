/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */
package com.netease.lava.nertc.sdk.stats;

import com.netease.lava.nertc.sdk.audio.NERtcAudioStreamType;

/**
 * @if English
 * Stats of a local audio stream.
 * @see NERtcAudioStreamType
 * @endif
 * @if Chinese
 * 本地音频单条流统计信息
 * @see NERtcAudioStreamType
 * @endif
 */
public class NERtcAudioLayerSendStats {

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
     * The average bitrate (kbps) transmission.
     * @endif
     * @if Chinese
     * 本地音频发送码率的平均值，单位为 Kbps
     * @endif
     */
    public int kbps;

    /**
     * @if English
     * Video package loss rate (%) in the specific duration.
     * @endif
     * @if Chinese
     * 特定时间内的音频丢包率（%）
     * @endif
     */
    public int lossRate;

    /**
     * @if English
     * The average round-trip delay time (ms).
     * @endif
     * @if Chinese
     * 平均往返时延（RTT），单位为毫秒
     * @endif
     */
    public long rtt;

    /**
     * @if English
     * The volume range is 0 to 100.
     * @endif
     * @if Chinese
     * 音量，范围为 0~100
     * @endif
     */
    public int volume;


    /**
     * @if English
     * The number of captured local audio channels.
     * @endif
     * @if Chinese
     * 本地音频采集声道数
     * @endif
     */
    public int numChannels;

    /**
     * @if English
     * Local video sample rate (Hz)
     * @endif
     * @if Chinese
     * 本地音频采样率，单位为 Hz
     * @endif
     */
    public int sentSampleRate;


    /**
     * @if English 
     * The local user volume captured by the local device. The value ranges between 0 (lowest volume) and 100 (highest volume).
     * camera.
     * @endif
     * @if Chinese 
     * 本地用户的采集音量，取值范围为 [0,100]。
     * @endif
     */
    public int capVolume;
    
    @Override
    /**
     * to be added
     */
    public String toString() {
        return "NERtcAudioSendStats{" +
                "kbps=" + kbps +
                ", lossRate=" + lossRate +
                ", rtt=" + rtt +
                ", volume=" + volume +
                ", numChannels=" + numChannels +
                ", sentSampleRate=" + sentSampleRate +
                '}';
    }
}
