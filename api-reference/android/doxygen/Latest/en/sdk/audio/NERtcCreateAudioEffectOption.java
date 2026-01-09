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
 * 音效构造参数类。
 * @endif
 */
public class NERtcCreateAudioEffectOption {

    /**
     * @if English
     * File paths of sound effect files to play support the absolute path in the SD card or URL address.
     * - Specifies a file name with a file extension, such as “/sdcard/test.mp3”. 
     * - Supported audio formats: mp3, mp4, m4a, aac, 3gp, mkv, and wav.
     * @endif
     * @if Chinese
     * 待播放的音效文件路径，支持本地 SD 卡中的绝对路径或 URL 地址。
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
     * 音效循环播放的次数：
     * - 1：（默认）播放音效一次。
     * - ≤ 0：无限循环播放音效，直至调用 stopEffect 或 stopAllEffects 后停止。
     * @endif
     */
    public int loopCount;
    /**
     * @if English
     * Whether to send the sound effect to remote users. The default value is true. Remote users can receive the sound effect.
     * @endif
     * @if Chinese
     * 是否将音效发送远端。默认为 true，即远端用户可以听到该音效。
     * @endif
     */
    public boolean sendEnabled = true;
    /**
     * @if English
     * The parameter indicates the volume of sound effect files. The volume range is 0 to 200. The default value is 100, which is the initial volume of the file.
     * @note If you adjust the volume during a call, the new setting is applied by default when users call again during the current call.
     * @endif
     * @if Chinese
     * 音效文件的发送音量，取值范围为 0 ~ 200。默认为 100，表示使用文件的原始音量。
     * @note 若您在通话中途修改了音量设置，则当前通话中再次调用时默认沿用此设置。
     * @endif
     */
    public int sendVolume = 100;
    /**
     * @if English
     * Whether to play the sound effect locally. The default value is true. Local users can receive the sound effect.
     * @endif
     * @if Chinese
     * 是否本地播放该音效。默认为 true，即本地用户可以听到该音效。
     * @endif
     */
    public boolean playbackEnabled = true;
    /**
     * @if English
     * The movie volume of sound effect files. The volume range is 0 to 200. The default value is 100, which is the initial volume of the file.
     * @note If you adjust the volume during a call, the new setting is applied by default when users call again during the current call.
     * @endif
     * @if Chinese
     * 音效文件的播放音量，取值范围为 0 ~ 200。默认为 100，表示使用文件的原始音量。
     * @note 若您在通话中途修改了音量设置，则当前通话中再次调用时默认沿用此设置。
     * @endif
     */
    public int playbackVolume = 100;

    @NonNull
    @Override
    /**
     * to be added
     */
    public String toString() {
        return String.format(Locale.CHINA,"{ path:%s,loopCount:%d,sendEnabled:%b," +
                "sendVolume:%d,playbackEnabled:%b,playbackVolume:%d }",
                path,loopCount,sendEnabled,sendVolume,playbackEnabled,playbackVolume);
    }
}
