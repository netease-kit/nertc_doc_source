/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk.audio;

import android.support.annotation.NonNull;

import java.util.Locale;

/**
 * @if English
 * Audio effect parameter class.
 * @endif
 * @if Chinese
 * 伴音构造参数类。
 * @endif
 */
public class NERtcCreateAudioMixingOption {

    /**
     * @if English
     * File paths of sound effect files to play support the absolute path in the SD card or URL address.
     * - Specifies the file name with a file extension, such as “/sdcard/test.mp3”. 
     * - Supported audio formats: mp3, mp4, m4a, aac, 3gp, mkv, and wav.
     * @endif
     * @if Chinese
     * 待播放的音乐文件的绝对路径或 URL 地址，支持本地 SD 卡中的绝对路径或 URL 地址。
     * - 需精确到文件名及后缀，例如 “/sdcard/test.mp3”。
     * - 支持的音效文件类型包括 MP3、M4A、AAC、3GP、WMA 和 WAV 格式。
     * @endif
     */
    public String path;
    /**
     * @if English
     * Times of the audio effect loops:
     * - 1: (Default) plays the sound effect once. 
     * - ≤ 0: Plays the sound effect all the time, and stops the playback until the system calls stopEffect or stopAllEffects.
     * @endif
     * @if Chinese
     * 伴音循环播放的次数：
     * - 1：（默认）播放一次。
     * - ≤ 0：无限循环播放，直至调用 pauseAudioMixing 后暂停，或调用 stopAudioMixing 后停止。
     * @endif
     */
    public int loopCount = 1;
    /**
     * @if English
     * Whether to send audio mixing to remote users. The default value is true. Remote users can receive the audio mixing.
     * @endif
     * @if Chinese
     * 是否将伴音发送远端，默认为 true，即远端用户可以听到该伴音。
     * @endif
     */
    public boolean sendEnabled = true;
    /**
     * @if English
     * The parameter indicates the volume of sound effect files. The volume range is 0 to 200. The default value is 100, which is the initial volume of the file.
     * @note If you adjust the volume during a call, the new setting is applied by default when users call again during the current call.
     * @endif
     * @if Chinese
     * 音乐文件的发送音量，取值范围为 0~200。默认为 100，表示使用文件的原始音量。
     * @note 若您在通话中途修改了音量设置，则当前通话中再次调用时默认沿用此设置。
     * @endif
     */
    public int sendVolume = -1;
    /**
     * @if English
     * Whether to play audio mixing locally. The default value is true. Local users can receive the audio mixing.
     * @endif
     * @if Chinese
     * 是否本地播放伴音。默认为 true，即本地用户可以听到该伴音。
     * @endif
     */
    public boolean playbackEnabled = true;
    /**
     * @if English
     * The playback volume of sound effect files. The volume range is 0 to 200. The default value is 100, which is the initial volume of the file.
     * @note If you adjust the volume during a call, the new setting is applied by default when users call again during the current call.
     * @endif
     * @if Chinese
     * 音乐文件的播放音量，取值范围为 0~200。默认为 100，表示使用文件的原始音量。
     * @note 若您在通话中途修改了音量设置，则当前通话中再次调用时默认沿用此设置。
     * @endif
     */
    public int playbackVolume = -1;


    /**
     * @if English
     * The timestamp of a position at which a music file starts playing. UTC timestamp, the number of milliseconds that have elapsed since January 1, 1970. Default value 0 indicates immediate playback.
     * @endif
     * @if Chinese
     * 音乐文件开始播放的时间，UTC 时间戳，即从1970 年 1 月 1 日 0 点 0 分 0 秒开始到事件发生时的毫秒数。默认值为 0，表示立即播放。
     * @endif
     */
    public long startTimeStamp = 0;


    /**
     * @if English
     * Specifies if a mixing audio uses a mainstream or substream. The default value is mainstream.
     * @endif
     * @if Chinese
     * 伴音跟随音频主流还是辅流，默认跟随主流。
     * @endif
     */
    public NERtcAudioStreamType sendWithAudioType = NERtcAudioStreamType.kNERtcAudioStreamTypeMain;


    /**
     * to be added
     */
    @Override
    public String toString() {
        return "NERtcCreateAudioMixingOption{" +
                "path='" + path + '\'' +
                ", loopCount=" + loopCount +
                ", sendEnabled=" + sendEnabled +
                ", sendVolume=" + sendVolume +
                ", playbackEnabled=" + playbackEnabled +
                ", playbackVolume=" + playbackVolume +
                ", startTimeStamp=" + startTimeStamp +
                ", sendWithAudioType=" + sendWithAudioType +
                '}';
    }
}
