/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk.stats;

/**
 * @if English
 * Properties of the audio volume information. An array containing the user ID and volume information for each speaker.
 * @endif
 * @if Chinese
 * 声音音量信息。 一个数组，包含每个说话者的用户 ID 和音量信息。
 * @endif
 */
public class NERtcAudioVolumeInfo {

    /**
     * @if English
     * The user ID defines who owns the video stream. 
     * @endif
     * @if Chinese
     * 用户 ID，指定是哪个用户的音量。
     * @endif
     */
    public long uid;

    /**
     * @if English
     * The volume of each user ranges between 0 (the lowest volume) and 100 (the highest volume). If the user calls startAudioMixing, the volume is the volume of each user after audio mixing.
     * - If the volume is 0, the user is not speaking.
     * - If the array is empty, the remote user is not speaking.
     * @endif
     * @if Chinese
     * 用户的音量，取值范围为 [0,100]。如果用户调用了 startAudioMixing，则 volume 为用户混音后的音量。
     * - 如果 volume 为 0，表示该用户没有说话。
     * - 如果数组为空，则表示此时远端没有人说话。
     * @endif
     */
    public int volume;
}
