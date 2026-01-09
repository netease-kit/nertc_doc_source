/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk.stats;

/**
 * @if English
 * Statistics of RTCEngine.
 * @endif
 * @if Chinese
 * 通话相关的统计信息。
 * @endif
 */
public class NERtcStats {


    /**
     * @if English
     * Total number of bytes transmitted, represented by an aggregate value.
     * @endif
     * @if Chinese
     * 发送字节数（Byte），累计值。
     * @endif
     */
    public long txBytes;

    /**
     * @if English
     * Total number of bytes received, represented by an aggregate value.
     * @endif
     * @if Chinese
     * 接收字节数（Byte），累计值。
     * @endif
     */
    public long rxBytes;

    /**
     * @if English
     * CPU usage (%).
     * <br>No access to Android 8.0 or higher.<p/>
     * @endif
     * @if Chinese
     * 当前 App 的 CPU 使用率 (%)。
     * <br>Android 8.0及后续版本无法获取<p/>。
     * @endif
     */
    public int cpuAppUsage;

    /**
     * @if English
     * System CPU usage (%)
     * <br>No access to Android 8.0 or higher.<p/>
     * @endif
     * @if Chinese
     * 当前系统的 CPU 使用率 (%)。
     * <br>Android 8.0及后续版本无法获取<p/>。
     * @endif
     */
    public int cpuTotalUsage;

    /**
     * @if English
     * The memory usage ratio of the app (%) takes to its extreme that may be over 100 for the computation.
     * @endif
     * @if Chinese
     * 当前 App 的内存占比 (%) , 占最大可用内存，由于计算方式的原因可能会超过100。
     * @endif
     */
    public int memoryAppUsageRatio;

    /**
     * @if English
     * The memory usage ratio of the system (%).
     * @endif
     * @if Chinese
     * 当前系统的内存占比 (%)。
     * @endif
     */
    public int memoryTotalUsageRatio;

    /**
     * @if English
     * The memory usage of the app (KB).
     * @endif
     * @if Chinese
     * 当前 App 的内存大小 (KB)。
     * @endif
     */
    public long memoryAppUsageInKBytes;


    /**
     * @if English
     * Total duration since joining the room. Sets a new timing after rejoining(S).
     * @endif
     * @if Chinese
     * 自加入房间的通话时长 ， 退出后再加入重新计时 ( 单位：S)。
     * @endif
     */
    public long totalDuration;

    /**
     * @if English
     * Total number of voice bytes transmitted since joining the room(Byte).
     * @endif
     * @if Chinese
     * 自加入房间后累计的发送的音频字节数（Byte）。
     * @endif
     */
    public long txAudioBytes;


    /**
     * @if English
     * Total number of video bytes transmitted since joining the room(Byte).
     * @endif
     * @if Chinese
     * 自加入房间后累计的发送的视频字节数（Byte）。
     * @endif
     */
    public long txVideoBytes;


    /**
     * @if English
     * Total number of audio bytes transmitted since joining the room(Byte).
     * @endif
     * @if Chinese
     * 自加入房间后累计的接收的音频字节数（Byte）。
     * @endif
     */
    public long rxAudioBytes;


    /**
     * @if English
     * Total number of video bytes received (bytes) since joining the room.
     * @endif
     * @if Chinese
     * 自加入房间后累计的接收的视频字节数（Byte）。
     * @endif
     */
    public long rxVideoBytes;

    /**
     * @if English
     * The received audio bitrate (Kbps).
     * @endif
     * @if Chinese
     * 音频接收码率（kbps）。
     * @endif
     */
    public int rxAudioKBitRate;

    /**
     * @if English
     * The received bitrate (kbps) of video.
     * @endif
     * @if Chinese
     * 视频接收码率（kbps）。
     * @endif
     */
    public int rxVideoKBitRate;

    /**
     * @if English
     * The transmission audio bitrate (Kbps).
     * @endif
     * @if Chinese
     * 音频发送码率（kbps）。
     * @endif
     */
    public int txAudioKBitRate;

    /**
     * @if English
     * The transmission bitrate (kbps) of video.
     * @endif
     * @if Chinese
     * 视频发送码率（kbps）。
     * @endif
     */
    public int txVideoKBitRate;

    /**
     * @if English
     * Uplink average round-trip delay time (ms)
     * @endif
     * @if Chinese
     * 上行平均往返时延(ms)。
     * @endif
     */
    public long upRtt;

    /**
     * @if English
     * Uplink average round-trip delay time (ms)
     * @endif
     * @if Chinese
     * 下行平均往返时延(ms)。
     * @endif
     */
    public long downRtt;

    /**
     * @if English
     * Local uplink voice package loss rate (%).
     * @endif
     * @if Chinese
     * 本地上行音频丢包率(%)。
     * @endif
     */
    public int txAudioPacketLossRate;

    /**
     * @if English
     * Local uplink video package loss rate (%).
     * @endif
     * @if Chinese
     * 本地上行视频实际丢包率(%)。
     * @endif
     */
    public int txVideoPacketLossRate;

    /**
     * @if English
     * Local uplink voice package loss sum.
     * @endif
     * @if Chinese
     * 本地上行音频丢包数。
     * @endif
     */
    public int txAudioPacketLossSum;

    /**
     * @if English
     * Local uplink video package sum.
     * @endif
     * @if Chinese
     * 本地上行视频丢包数。
     * @endif
     */
    public int txVideoPacketLossSum;


    /**
     * @if English
     * Local uplink voice jitter (ms).
     * @endif
     * @if Chinese
     * 本地上行音频抖动 (ms)。
     * @endif
     */
    public int txAudioJitter;

    /**
     * @if English
     * Local uplink video jitter (ms).
     * @endif
     * @if Chinese
     * 本地上行视频抖动 (ms)。
     * @endif
     */
    public int txVideoJitter;

    /**
     * @if English
     * Local downlink voice package loss rate (%).
     * @endif
     * @if Chinese
     * 本地下行音频丢包率(%)。
     * @endif
     */
    public int rxAudioPacketLossRate;


    /**
     * @if English
     * Local downlink video package loss rate (%).
     * @endif
     * @if Chinese
     * 本地下行视频丢包率(%)。
     * @endif
     */
    public int rxVideoPacketLossRate;

    /**
     * @if English
     * Local downlink voice package loss sum.
     * @endif
     * @if Chinese
     * 本地下行音频丢包数。
     * @endif
     */
    public long rxAudioPacketLossSum;


    /**
     * @if English
     * Local downlink video package loss sum.
     * @endif
     * @if Chinese
     * 本地下行视频丢包数。
     * @endif
     */
    public int rxVideoPacketLossSum;

    /**
     * @if English
     * Local downlink video jitter (ms).
     * @endif
     * @if Chinese
     * 本地下行音频抖动 (ms)。
     * @endif
     */
    public int rxAudioJitter;

    /**
     * @if English
     * Local downlink video jitter (ms).
     * @endif
     * @if Chinese
     * 本地下行视频抖动 (ms)。
     * @endif
     */
    public int rxVideoJitter;


    @Override
    /**
     * to be added
     */
    public String toString() {
        return "NERtcStats{" +
                "txBytes=" + txBytes +
                ", rxBytes=" + rxBytes +
                ", cpuAppUsage=" + cpuAppUsage +
                ", cpuTotalUsage=" + cpuTotalUsage +
                ", memoryAppUsageRatio=" + memoryAppUsageRatio +
                ", memoryTotalUsageRatio=" + memoryTotalUsageRatio +
                ", memoryAppUsageInKBytes=" + memoryAppUsageInKBytes +
                ", totalDuration=" + totalDuration +
                ", txAudioBytes=" + txAudioBytes +
                ", txVideoBytes=" + txVideoBytes +
                ", rxAudioBytes=" + rxAudioBytes +
                ", rxVideoBytes=" + rxVideoBytes +
                ", rxAudioKBitRate=" + rxAudioKBitRate +
                ", rxVideoKBitRate=" + rxVideoKBitRate +
                ", txAudioKBitRate=" + txAudioKBitRate +
                ", txVideoKBitRate=" + txVideoKBitRate +
                ", upRtt=" + upRtt +
                ", downRtt=" + downRtt +
                ", txAudioPacketLossRate=" + txAudioPacketLossRate +
                ", txVideoPacketLossRate=" + txVideoPacketLossRate +
                ", txAudioPacketLossSum=" + txAudioPacketLossSum +
                ", txVideoPacketLossSum=" + txVideoPacketLossSum +
                ", txAudioJitter=" + txAudioJitter +
                ", txVideoJitter=" + txVideoJitter +
                ", rxAudioPacketLossRate=" + rxAudioPacketLossRate +
                ", rxVideoPacketLossRate=" + rxVideoPacketLossRate +
                ", rxAudioPacketLossSum=" + rxAudioPacketLossSum +
                ", rxVideoPacketLossSum=" + rxVideoPacketLossSum +
                ", rxAudioJitter=" + rxAudioJitter +
                ", rxVideoJitter=" + rxVideoJitter +
                '}';
    }
}
