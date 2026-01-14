package com.netease.lava.nertc.sdk.audio;

/**
 * @if English
 * Audio format.
 * @endif
 * @if Chinese
 * 音频格式
 * @endif
 */
public abstract class NERtcAudioFormat {

    /**
     * @if English
     * Only 16-bit PCM raw data is supported.
     * @endif
     * @if Chinese
     * 原始数据格式，目前仅支持 PCM 16。
     * @endif
     */
    abstract public NERtcAudioType getType();

     /**
     * @if English
     * The number of audio channels.
     * @endif
     * @if Chinese
     * 音频通道数。
     * @endif
     */
    abstract public int getChannels();

    /**
     * @if English
     * Audio sample rate.
     * @endif
     * @if Chinese
     * 音频采样率。
     * @endif
     */
    abstract public int getSampleRate();

    /**
     * @if English
     * The number of bytes per sample.
     * @endif
     * @if Chinese
     * 单个采样点的字节数。
     * @endif
     */
    abstract public int getBytesPerSample();

    /**
     * @if English
     * The number of samples per channel.
     * @endif
     * @if Chinese
     * 单通道采样点个数。
     * @endif
     */
    abstract public int getSamplesPerChannel();
}
