package com.netease.lava.nertc.sdk.audio;

import java.nio.ByteBuffer;

/**
 * @if English
 * Audio data.
 * @endif
 * @if Chinese
 * 音频数据
 * @endif
 */
public abstract class NERtcAudioFrame {
    /**
     * @if English
     * Gets parameters of PCM data.
     * @return NERtcAudioFormat Parameters of PCM data. 
     * @endif
     * @if Chinese
     * 获取 PCM 数据各项参数。
     * @return NERtcAudioFormat PCM 数据各项参数。
     * @endif
     */
    public abstract NERtcAudioFormat getFormat();

    /**
     * @if English
     * Gets PCM data.
     * @return NERtcAudioFormat PCM data. 
     * @endif
     * @if Chinese
     * 获取 PCM 数据。
     * @return NERtcAudioFormat PCM 数据。
     * @endif
     */
    public abstract ByteBuffer getData();
}
