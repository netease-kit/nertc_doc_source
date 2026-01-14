/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk.audio;

/**
 * @if English
 * External audio frames.
 * @endif
 * @if Chinese
 * 外部音频格式。
 * @endif
 */
public class NERtcAudioExternalFrame {

    /**
    * @if English
    * Audio data. 
    * @endif
    * @if Chinese
    * 音频数据。
    * @endif
    */
    public byte[] audioData;

    /**
    * @if English
    * Audio sample rate.
    * @endif
    * @if Chinese
    * 音频采样率。
    * @endif
    */
    public int sampleRate;

    /**
    * @if English
    * The number of audio channels.
    * @endif
    * @if Chinese
    * 音频通道数。
    * @endif
    */
    public int numberOfChannels;

    /**
    * @if English
    * The number of samples per channel.
    * @endif
    * @if Chinese
    * 单通道采样点个数。
    * @endif
    */
    public int samplesPerChannel;

   /**
    * @if English
    * Syncs the timestamps of the audio mainstream and substream. The method is applied when the mainstream and substream are used for external sources.
    * @endif
    @if Chinese
    * 同步音频主辅流的时间戳，一般只有在同时开启外部音频主流及辅流输入时用到。
    * @endif
    */
    public long syncTimestamp = -1;

}
