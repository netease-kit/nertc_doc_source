/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk.live;

import java.io.Serializable;

/** NERtcLiveConfig */
public class NERtcLiveConfig implements Serializable {
    /**
     * @if English
     * The audio sample rate of publishing a stream in the live streaming.
     * @endif
     * @if Chinese
     * 直播推流音频采样率
     * @endif
     */
    public enum NERtcLiveStreamAudioSampleRate {

        /**
         * @if English
         * Sample rate: 32 kHz
         * @endif
         * @if Chinese
         * 采样率为 32 kHz。
         * @endif
         */
        NERtcLiveStreamAudioSampleRate32000(32000),

        /**
         * @if English
         * Sample rate: 44.1kHz
         * @endif
         * @if Chinese
         * 采样率为 44.1 kHz。
         * @endif
         */
        NERtcLiveStreamAudioSampleRate44100(44100),

        /**
         * @if English
         * (Default)Audio sample rate: 48 kHz 
         * @endif
         * @if Chinese
         * （默认）采样率为 48 kHz。
         * @endif
         */
        NERtcLiveStreamAudioSampleRate48000(48000);

        private final int sampleRate;
        /** NERtcLiveStreamAudioSampleRate*/
        NERtcLiveStreamAudioSampleRate(int sampleRate) {
            this.sampleRate = sampleRate;
        }

        /**
         * @if English
         * Sample rate
         * @endif
         * @if Chinese
         * 采样率为
         * @endif
         */
        public int sampleRate() {
            return sampleRate;
        }
    }

    /**
     * @if English
     * Audio encoding profile of publishing a stream in the live streaming.
     * @endif
     * @if Chinese
     * 直播推流音频编码规格
     * @endif
     */
    public enum NERtcLiveStreamAudioCodecProfile {

        /**
         * @if English
         * (Default) LC-AAC profile indicates basic audio encoding profile.
         * @endif
         * @if Chinese
         * （默认）LC-AAC 规格，表示基本音频编码规格。
         * @endif
         */
        NERtcLiveStreamAudioCodecProfileLCAAC(0),

        /**
         * @if English
         * HE-AAC profile represents high-efficiency audio encoding profile.
         * @endif
         * @if Chinese
         * HE-AAC 规格，表示高效音频编码规格。
         * @endif
         */
        NERtcLiveStreamAudioCodecProfileHEAAC(1);

        private final int codecProfile;

        /**
         * @if English
         * Audio encoding profile of publishing a stream in the live streaming.
         * @param codecProfile  Audio encoding profile
         * @endif
         * @if Chinese
         * 直播推流音频编码规格
         * @param codecProfile 编码规格
         * @endif
         */
        NERtcLiveStreamAudioCodecProfile(int codecProfile) {
            this.codecProfile = codecProfile;
        }
        /**codecProfile */
        public int codecProfile() {
            return codecProfile;
        }
    }

    /**
     * @if English
     * Enables or disables transparent transmission for single video stream. By default, the setting is disabled. 
     * <br>When the setting is enabled, if the room ingests one video stream, the video stream is not transcoded by following the transcoding layout. Instead, the video stream is directly pushed to CDN.
     * <br>If video streams from multiple room members are mixed into one video stream, the setting becomes invalid. The setting cannot be restored even if only one video stream is pushed into the room.
     * @endif
     * @if Chinese
     * 单路视频透传开关，默认为关闭状态。
     * <br>开启后，如果房间中只有一路视频流输入， 则不对输入视频流进行转码，不遵循转码布局，直接推流 CDN。
     * <br>如果有多个房间成员视频流混合为一路流，则该设置失效，并在恢复为一个成员画面（单路流）时也不会恢复
     * @endif
     */
    public boolean singleVideoPassThrough;


    /**
     * @if English
     * Audio push stream bitrate.
     * <br>Unit: kbps. Valid values: 10 to 192. 
     * <br>In voice scenarios, we recommend that you set the bitrate to 64 or higher. In music scenarios, we recommend that you sett the bitrate to 128 or higher.
     * @endif
     * @if Chinese
     * 音频推流码率。
     * <br>单位为 kbps，取值范围为 10~192。
     * <br>语音场景建议设置为 64 及以上码率，音乐场景建议设置为 128 及以上码率。
     * @endif
     */
    public int audioBitrate;

    /**
     * @if English
     * Audio push stream sample rate. By default, the value is NERtcLiveStreamAudioSampleRate48000 48K.
     * @param sampleRate Sample rate.
     * @endif
     * @if Chinese
     * 音频推流采样率。默认值为 NERtcLiveStreamAudioSampleRate48000 48K。
     * @param sampleRate 采样率
     * @endif
     */
    public NERtcLiveStreamAudioSampleRate sampleRate;

    /**
     * @if English
     * The number of audio push stream channels. 
     * - 1: mono sound. 
     * - 2: (Default) stereo sound.
     * @endif
     * @if Chinese
     * 音频推流声道数。
     * - 1：单声道。
     * - 2：（默认）双声道。
     * @endif
     */
    public int channels;

    /**
     * @if English
     * Audio encoding profile. By default, the value is NERtcLiveStreamAudioCodecProfile audioCodecProfile LCAAC.
     * @endif
     * @if Chinese
     * 音频编码规格。默认值为 NERtcLiveStreamAudioCodecProfileLCAAC 普通编码规格。
     * @endif
     */
    public NERtcLiveStreamAudioCodecProfile audioCodecProfile;
}
