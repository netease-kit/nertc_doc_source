package com.netease.lava.nertc.sdk.audio;

import android.support.annotation.NonNull;

import com.netease.lava.nertc.sdk.NERtcOption;

/** NERtcAudioFrameRequestFormat */
public class NERtcAudioFrameRequestFormat {
    /**
     * @if English
     * The number of audio channels (If the audio data is a stereo sound, the data of two channels are crossed) 1: mono sound. 2: stereo sound.
     * @endif
     * @if Chinese
     * 音频房间数量(如果是立体声，数据是交叉的)。单声道: 1；双声道 : 2。
     * @endif
     */
    private int channels;

    /**
     * @if English
     * Sample rate. 
     * @endif
     * @if Chinese
     * 采样率。
     * @endif
     */
    private int sampleRate;

    /**
     * @if English
     * For more information about read and write permissions, see NERtcAudioFrameOpMode.
     * @endif
     * @if Chinese
     * 读写权限。详细信息请参考 NERtcAudioFrameOpMode。
     * @endif
     */
    private  int opMode = NERtcAudioFrameOpMode.kNERtcAudioFrameOpModeReadWrite;


    /**
     * @if English
     * Gets the number of audio rooms. (If the audio data is a stereo sound, the data of two channels are crossed.)
     * @return channels The number of audio channels. 
     * - 1: mono sound.
     * - 2: stereo sound.
     * @endif
     * @if Chinese
     * 获取音频房间数量。如果是立体声，则数据是交叉的。
     * @return channels 音频房间数量。
     * - 1：单声道。
     * - 2：双声道。
     * @endif
     */
    public int getChannels() {
        return channels;
    }

    /**
     * @if English
     * Sets the expected number of audio channels. (If the audio data is a stereo sound, the data of two channels are crossed.)
     * @param channels The number of audio channels.
     * - 1: mono sound.
     * - 2: stereo sound.
     * @endif
     * @if Chinese
     * 期望的音频房间数量。如果是立体声，则数据是交叉的。
     * @param channels 音频房间数量。
     * - 1：单声道。
     * - 2：双声道。
     * @endif
     */
    public void setChannels(int channels) {
        this.channels = channels;
    }

    /**
     * @if English
     * Gets audio sample rate.
     * @return sampleRate Audio sample rate.
     * @endif
     * @if Chinese
     * 获取音频采样率。
     * @return sampleRate 音频采样率。
     * @endif
     */
    public int getSampleRate() {
        return sampleRate;
    }

    /**
     * @if English
     * Sets audio sample rate. 
     * @param sampleRate Audio sample rate.
     * @endif
     * @if Chinese
     * 设置音频采样率。
     * @param sampleRate 音频采样率。
     * @endif
     */
    public void setSampleRate(int sampleRate) {
        this.sampleRate = sampleRate;
    }

    /**
     * @if English
     * Sets read and write permissions of audio data. For more information, see NERtcAudioFrameOpMode.
     * @param opMode Read and write permissions.
     * @endif
     * @if Chinese
     * 设置音频数据读写权限。详细信息请参考 NERtcAudioFrameOpMode。
     * @param opMode 读写权限
     * @endif
     */
    public void setOpMode(int opMode) {
        this.opMode = opMode;
    }

    /**
     * @if English
     * Gets read and write permissions of audio data. For more information, see NERtcAudioFrameOpMode.
     * @return opMode Read and write permissions.
     * @endif
     * @if Chinese
     * 获取音频数据读写权限。详细信息请参考 NERtcAudioFrameOpMode。
     * @return opMode 读写权限
     * @endif
     */
    public int getOpMode() {
        return opMode;
    }

    @NonNull
    @Override
    /**
     * to be added
     */
    public String toString() {
        return "{channels: " + channels +
                ", sampleRate: " + sampleRate +
                "}";
    }
}
