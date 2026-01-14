/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk.video;

import com.netease.lava.nertc.sdk.NERtcConstants;

/**NERtcEncodeConfig */
public class NERtcEncodeConfig {

    /**
     * @if English
     * Video encoding frame rate.
     * @endif
     * @if Chinese
     * 视频编码帧率。
     * @endif
     */
    public enum NERtcVideoFrameRate {
        /**
         * @if English
         * 0 fps
         * @endif
         * @if Chinese
         * 0 fps
         * @endif
         */
        FRAME_RATE_FPS_DEFAULT(0),
        /**
         * @if English
         * 7 fps
         * @endif
         * @if Chinese
         * 7 fps
         * @endif
         */
        FRAME_RATE_FPS_7(7),
        /**
         * @if English
         * 10 fps
         * @endif
         * @if Chinese
         * 10 fps
         * @endif
         */
        FRAME_RATE_FPS_10(10),
        /**
         * @if English
         * 15 fps
         * @endif
         * @if Chinese
         * 15 fps
         * @endif
         */
        FRAME_RATE_FPS_15(15),
        /**
         * @if English
         * 24 fps
         * @endif
         * @if Chinese
         * 24 fps
         * @endif
         */
        FRAME_RATE_FPS_24(24),
        /**
         * @if English
         * 30 fps
         * @endif
         * @if Chinese
         * 30 fps
         * @endif
         */
        FRAME_RATE_FPS_30(30);

        private int value;
        /**
         * @if English
         * to be added
         * @param value 
         * @endif
         * @if Chinese
         * @param value
         * @endif
         */
        NERtcVideoFrameRate(int value) {
            this.value = value;
        }
        /**
         * @if English
         * Gets the value.
         * @endif
         * @if Chinese
         * 获取值。
         * @endif
         */
        public int getValue() {
            return value;
        }
    }

    /**
     * @if English
     * Uses video encoding resolution to measure the video quality. 
     * @see  NERtcConstants.VideoProfile#Lowest
     * @see  NERtcConstants.VideoProfile#LOW
     * @see  NERtcConstants.VideoProfile#STANDARD
     * @see  NERtcConstants.VideoProfile#HD720P
     * @see  NERtcConstants.VideoProfile#HD1080p
     * @endif
     * @if Chinese
     * 视频编码的分辨率，用于衡量编码质量。推荐优先使用自定义宽高设置。
     * @see  NERtcConstants.VideoProfile#Lowest
     * @see  NERtcConstants.VideoProfile#LOW
     * @see  NERtcConstants.VideoProfile#STANDARD
     * @see  NERtcConstants.VideoProfile#HD720P
     * @see  NERtcConstants.VideoProfile#HD1080p
     * @endif
     */
    public int videoProfile = NERtcConstants.VideoProfile.STANDARD;

    /**
     * @if English
     * The frame rate. For more information about frame rate of video encoding, see NERtcEncodeConfig.NERtcVideoFrameRate.  
     * - When using mainstreams, {@link NERtcVideoFrameRate#FRAME_RATE_FPS_7} defines the frame rate by default。
     * - {@link NERtcConstants.VideoProfile} >= NERtcConstants.VideoProfile#STANDARD，frameRate = FRAME_RATE_FPS_30 。
     * - {@link NERtcConstants.VideoProfile} < NERtcConstants.VideoProfile#STANDARD，frameRate = FRAME_RATE_FPS_15 。
     * - When using substreams, {@link NERtcVideoFrameRate#FRAME_RATE_FPS_7} defines the frame rate by default.
     * @endif
     * @if Chinese
     * 视频编码的帧率。详细信息请参考 NERtcEncodeConfig.NERtcVideoFrameRate。
     * - 使用主流时，默认根据设置的 {@link NERtcConstants.VideoProfile} 决定帧率。
     * -     {@link NERtcConstants.VideoProfile} >= NERtcConstants.VideoProfile#STANDARD，frameRate = FRAME_RATE_FPS_30 。
     * -     {@link NERtcConstants.VideoProfile} < NERtcConstants.VideoProfile#STANDARD，frameRate = FRAME_RATE_FPS_15 。
     * - 使用辅流时，frameRate 默认为 {@link NERtcVideoFrameRate#FRAME_RATE_FPS_7} 。<
     * @endif
     */
    public NERtcVideoFrameRate frameRate = NERtcVideoFrameRate.FRAME_RATE_FPS_DEFAULT;

    /**
     * @if English
     * The minimum frame rate of video encoding. The default value is 0. The value indicates the minimum frame rate.
     * @endif
     * @if Chinese
     * 视频编码的最小帧率。默认为 0，表示使用默认最小帧率。
     * @endif
     */
    public int minFramerate = 0;

    /**
     * @if English
     * The bitrate (Kbps) of video encoding. You can set the bitrate according to specific scenarios.
     * - If the video bitrate is not reasonable,the SDK will adjust the bitrate.
     * - If the bitrate is 0, the SDK will adjust accordingly.
     * @endif
     * @if Chinese
     * 视频编码的码率，单位为 Kbps。
     * <br>您可以根据场景需要，手动设置想要的码率。
     * - 若设置的视频码率超出合理范围，SDK 会自动按照合理区间处理码率。
     * - 若设置为 0，SDK将会自行计算处理。
     * 
        |**分辨率**|**帧率（fps）**|**通信场景码率(kbps)**|**直播场景码率(kbps)**|
        |:--|:--|:--|:--|
        |90 x 90|30|49|73|
        |90 x 90|15|32|48|
        |120 x 90|30|61|91|
        |120 x 90|15|40|60|
        |120 x 120|30|75|113|
        |120 x 120|15|50|75|
        |160 x 90|30|75|113|
        |160 x 90|15|50|75|
        |160 x 120|30|94|141|
        |160 x 120|15|62|93|
        |180 x 180|30|139|208|
        |180 x 180|15|91|137|
        |240 x 180|30|172|259|
        |240 x 180|15|113|170|
        |240 x 240|30|214|321|
        |240 x 240|15|141|212|
        |320 x 180|30|214|321|
        |320 x 180|15|141|212|
        |320 x 240|30|259|389|
        |320 x 240|15|175|263|
        |360 x 360|30|393|590|
        |360 x 360|15|259|389|
        |424 x 240|15|217|325|
        |480 x 360|30|488|732|
        |480 x 360|15|322|483|
        |480 x 480|30|606|909|
        |480 x 480|15|400|600|
        |640 x 360|30|606|909|
        |640 x 360|15|400|600|
        |640 x 480|30|752|1128|
        |640 x 480|15|496|744|
        |720 x 720|30|1113|1670|
        |720 x 720|15|734|1102|
        |848 x 480|30|929|1394|
        |720 x 720|15|613|919|
        |960 x 720|30|1382|2073|
        |960 x 720|15|911|1367|
        |1080 x 1080|30  |2046|3069|
        |1080 x 1080|15|1350|2025|
        |1280 x 720|30|1714|2572|
        |1280 x 720|15|1131|1697|
        |1440 x 1080|30|2538|3808|
        |1440 x 1080|15  |1675|2512|
        |1920 x 1080|30|3150|4725|
        |1920 x 1080|15|2078|3117|
     * @endif
     */
    public int bitrate = 0;

    /**
     * @if English
     * The minimum bitrate (Kbps) of video encoding. You can set the bitrate according to specific scenarios. If the value is 0, the SDK computes it.
     * @endif
     * @if Chinese
     * 视频编码的最小码率，单位为 Kbps。您可以根据场景需要，手动设置想要的最小码率，若设置为 0，SDK 将会自行计算处理。
     * @endif
     */
    public int minBitrate = 0;
}
