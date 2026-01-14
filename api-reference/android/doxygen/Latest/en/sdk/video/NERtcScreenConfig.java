/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk.video;

/**
 * @if English
 * Encoding parameters of screen sharing.
 * @endif
 * @if Chinese
 * 屏幕共享的编码参数。
 * @endif
 */
public class NERtcScreenConfig extends NERtcEncodeConfig{

    /**NERtcSubStreamContentPrefer */
    public enum NERtcSubStreamContentPrefer {
        /**
         * @if English
         * (Default) Motion-intensive content. 
         * - Choose this option if you prefer smoothness or when you are sharing a video clip, movie, or video game. 
         * - When users choose motion-intensive content, you can set the frame rate as users set.
         * @endif
         * @if Chinese
         * （默认）内容类型为动画。
         * - 当共享的内容是视频、电影或游戏时，推荐选择该内容类型。
         * - 当用户设置内容类型为动画时，按用户设置的帧率处理。
         * @endif
         */
        CONTENT_PREFER_MOTION,
        /**
         * @if English
         * Motionless content. 
         * - Choose this option if you prefer sharpness or when you are sharing a picture or text.
         * - When users choose motionless pictures, the frame rate is within 10.
         * @endif
         * @if Chinese
         * 内容类型为细节。
         * - 当共享的内容是图片或文字时，推荐选择该内容类型。
         * - 当用户设置内容类型为细节时，最高允许用户设置到 10 帧，设置超过 10 帧时，按 10 帧处理。
         * @endif
         */
        CONTENT_PREFER_DETAILS
    }

    /**
     * @if English
     * Encoding strategy preference of screen sharing.
     * @endif
     * @if Chinese
     * 屏幕共享功能的编码策略倾向。
     * @endif
     */
    public NERtcSubStreamContentPrefer contentPrefer = NERtcSubStreamContentPrefer.CONTENT_PREFER_MOTION;

    @Override
    /**
     * @if English
     * to be added
     * @endif
     * @if Chinese
     * to be added
     * @endif
     */
    public String toString() {
        return "videoProfile = " + videoProfile
                + " frameRate = " + frameRate
                + " minFramerate = " + minFramerate
                + " bitrate = " + bitrate
                + " minBitrate = " + minBitrate
                + " contentPrefer = " + contentPrefer;
    }
}
