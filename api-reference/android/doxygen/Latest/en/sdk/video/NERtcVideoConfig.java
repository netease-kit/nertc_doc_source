/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk.video;

import com.netease.lava.nertc.sdk.NERtcConstants;


/**
 * @if English
 * Profile of video encoder configuration.
 * @endif
 * @if Chinese
 * 视频编码器配置的属性。
 * @endif
 */
public class NERtcVideoConfig extends NERtcEncodeConfig{


    /**
     * @if English
     * The video encoding degradation preference under limited bandwidth.
     * @endif
     * @if Chinese
     * 带宽受限时的视频编码降级偏好。
     * @endif
     */
    public enum NERtcDegradationPreference {

        /**
         * @if English
         * (Default) Adjusts adaptation preferences based on scenario patterns.
         * - In a communication scenario, select DEGRADATION_BALANCED model to balance encoding frame rate and video quality.
         * - In a living streaming scenario, select DEGRADATION_MAINTAIN_QUALITY mode to guarantee the video quality by lowering encoding frame rate.
         * @endif
         * @if Chinese
         * （默认）根据场景模式调整适应性偏好。
         *  - 通信场景中，选择DEGRADATION_BALANCED模式，在编码帧率和视频质量之间保持平衡。
         *  - 直播场景中，选择DEGRADATION_MAINTAIN_QUALITY模式，降低编码帧率以保证视频质量。
         * @endif
         */
        DEGRADATION_DEFAULT,
        /**
         * @if English
         * Smooth priority. Reduces the video quality to guarantee the encoding frame rate. In a poor network condition, reduces the video definition to guarantee video fluency. The behaviors will reduce picture quality and blur the picture. However, the video fluency is guaranteed. 
         * @endif
         * @if Chinese
         * 流畅优先，降低视频质量以保证编码帧率。在弱网环境下，降低视频清晰度以保证视频流畅，此时画质降低，画面会变得模糊，但可以保持视频流畅。
         * @endif
         */
        DEGRADATION_MAINTAIN_FRAMERATE,

        /**
         * @if English
         * Clarity priority. Reduces the video quality to guarantee the encoding frame rate. In a poor network condition, reduces the video frame rate to guarantee video fluency. At this time,  choppy videos may be occasional.
         * @endif
         * @if Chinese
         * 清晰优先，降低编码帧率以保证视频质量。在弱网环境下，降低视频帧率以保证视频清晰，此时可能会出现一定卡顿。
         * @endif
         */
        DEGRADATION_MAINTAIN_QUALITY,

        /**
         * @if English
         * Balances encoding frame rate and the video quality.
         * @endif
         * @if Chinese
         * 在编码帧率和视频质量之间保持平衡。
         * @endif
         */
        DEGRADATION_BALANCED
    }

    /**
     * @if English
     * Sets the mirror mode of the local video encoding. This mode refers to send video locally, which only affects the video picture seen by the remote user.
     * @endif
     * @if Chinese
     * 设置本地视频编码的镜像模式，即本地发送视频的镜像模式，只影响远端用户看到的视频画面。
     * @endif
     */
    public enum NERtcVideoMirrorMode {
        /**
         * @if English
         * (Default) The SDK determines the mirror mode.
         * @endif
         * @if Chinese
         * （默认）由 SDK 决定镜像模式。
         * @endif
         */
        VIDEO_MIRROR_MODE_AUTO,


        /**
         * @if English
         * Enables the mirror mode.
         * @endif
         * @if Chinese
         * 启用镜像模式。
         * @endif
         */
        VIDEO_MIRROR_MODE_ENABLED,


        /**
         * @if English
         * Disables the mirror mode.
         * @endif
         * @if Chinese
         * 关闭镜像模式。
         * @endif
         */
        VIDEO_MIRROR_MODE_DISABLED,
    }


    /**
     * @if English
     * Sets the orientation mode of local video encoding. This mode refers to send video locally, which only affects the video picture seen by remote users.
     * @endif
     * @if Chinese
     * 设置本地视频编码的方向模式，即本地发送视频的方向模式，同时影响本端用户的预览画面和远端用户看到的视频画面。
     * @endif
     */
    public enum NERtcVideoOutputOrientationMode {

        /**
         * @if English
         * (Default)The output video always follows the orientation of the captured video, because the receiver takes the rotational information passed on from the video encoder. Applies to situations where the receiving end can process the rotational information.
         * <br>The mode is applicable to scenarios where the receiver can adjust the video orientation.
         * - If the captured video is in landscape mode, the output video is in landscape mode.
         * - If the captured video is in portrait mode, the output video is in portrait mode.
         * @endif
         * @if Chinese
         * （默认）该模式下 SDK 输出的视频方向与采集到的视频方向一致。接收端会根据收到的视频旋转信息对视频进行旋转。
         * <br>该模式适用于接收端可以调整视频方向的场景。
         * - 如果采集的视频是横屏模式，则输出的视频也是横屏模式。
         * - 如果采集的视频是竖屏模式，则输出的视频也是竖屏模式。
         * @endif
         */
        VIDEO_OUTPUT_ORIENTATION_MODE_ADAPTATIVE,


        /**
         * @if English
         * The output video is always in landscape mode. If the captured video is in portrait mode, the video encoder crops it to fit the output.
         * <br>Applies to scenarios where the receiver cannot process the rotational information.Such as the CDN live streaming.
         * @endif
         * @if Chinese
         * 该模式下 SDK 固定输出横屏模式的视频。如果采集到的视频是竖屏模式，则视频编码器会对其进行裁剪。
         * <br>该模式适用于当接收端无法调整视频方向时，例如旁路推流场景。
         * @endif
         */
        
        VIDEO_OUTPUT_ORIENTATION_MODE_FIXED_LANDSCAPE,


        /**
         * @if English
         * The output video is always in landscape mode. If the captured video is in portrait mode, the video encoder crops it to fit the output.
         * <br>Applies to scenarios where the receiver cannot process the rotational information. Such as the CDN live streaming.
         * @endif
         * @if Chinese
         * 该模式下 SDK 固定输出竖屏模式的视频，如果采集到的视频是横屏模式，则视频编码器会对其进行裁剪。
         * <br>该模式适用于当接收端无法调整视频方向时，例如旁路推流场景。
         * @endif
         */
        VIDEO_OUTPUT_ORIENTATION_MODE_FIXED_PORTRAIT,
    }

    /**
     * @if English
     * The video encoding resolution, which measures encoding quality. The resolution equals to multiplying the width and the height. Selects one between the resolution and the maxProfile property. 
     * <br>The width indicates the pixels of the video frame on the horizontal axis. The width is self-defined. 
     * - If you set the parameter as a negative,  apply max_profile level.
     * - If you need to set the self-defined resolution, select the property. The maxProfile property is invalid.
     * <br>@note In self-defined video capture scenarios, the width and height settings are invalid. The system performs automatic scale according to maxProfile.
     * @endif
     * @if Chinese
     * 视频编码分辨率，衡量编码质量，以宽x高表示。与 maxProfile 属性二选一。推荐优先使用自定义宽高设置。
     * <br>width表示视频帧在横轴上的像素，即自定义宽。
     * - 设置为负数时表示采用 max_profile 档位。
     * - 如果需要自定义分辨率场景，则设置此属性，maxProfile 属性失效。
     * <br>@note 自定义视频采集场景下，width 和 height 设置无效，会自动根据 maxProfile 缩放。
     * @endif
     */
    public int width = 0;

    /**
     * @if English
     * The video encoding resolution, which measures encoding quality. The resolution equals to multiplying the width and the height. Selects one between the resolution and the maxProfile property. 
     * <br>The height indicates the pixels of the video frame on the vertical axis. The height is self-defined. 
     * - If you set the parameter as a negative,  apply max_profile level.
     * - If you need to set the self-defined resolution, select the property. The maxProfile property is invalid.
     * <br>@note In a self-defined video capture scenario, the width and height settings are invalid. The system performs automatic scale according to maxProfile.
     * @endif
     * @if Chinese
     * 视频编码分辨率，衡量编码质量，以宽x高表示。与 maxProfile 属性二选一。推荐优先使用自定义宽高设置。
     * <br>height表示视频帧在纵轴上的像素，即自定义高。
     * - 设置为负数时表示采用 maxProfile 档位。
     * - 如果需要自定义分辨率场景，则设置此属性，maxProfile 属性失效。
     * <br>@note 自定义视频采集场景下，width 和 height 设置无效，会自动根据 maxProfile 缩放。
     * @endif
     */
    public int height = 0;

    /**
     * @if English
     * Whether the camera position is the front camera. Default: true. The setting is the front camera.
     * @endif
     * @if Chinese
     * 摄像头位置是否为前置摄像头。默认为 true，即前置摄像头。
     * @endif
     */
    public boolean frontCamera = true;

    /**
     * @if English
     * The Video capture color space format. The format is Texture by default. For details, see NERtcConstants.VideoColorFormat.
     * @endif
     * @if Chinese
     * 视频采集颜色空间格式，默认为 Texture。详细信息请参考NERtcConstants.VideoColorFormat。
     * @endif
     */
    public int colorFormat = NERtcConstants.VideoColorFormat.TEXTURE;

    /**
     * @if English
     * The video encoding degradation preference under limited bandwidth.
     * @endif
     * @if Chinese
     * 带宽受限时的视频编码降级偏好。
     * @endif
     */
    public NERtcDegradationPreference degradationPrefer = NERtcDegradationPreference.DEGRADATION_DEFAULT;

    /**
     * @if English
     * Video crop mode, aspect ratio.
     * @see NERtcConstants.VideoCropMode#DEFAULT
     * @see NERtcConstants.VideoCropMode#CROP_16x9
     * @see NERtcConstants.VideoCropMode#CROP_4x3
     * @see NERtcConstants.VideoCropMode#CROP_1x1
     * @endif
     * @if Chinese
     * 视频裁剪模式，宽高比。
     * @see NERtcConstants.VideoCropMode#DEFAULT
     * @see NERtcConstants.VideoCropMode#CROP_16x9
     * @see NERtcConstants.VideoCropMode#CROP_4x3
     * @see NERtcConstants.VideoCropMode#CROP_1x1
     * @endif
     */
    public int videoCropMode = NERtcConstants.VideoCropMode.DEFAULT;


    /**
     * @if English
     * Sets the mirror mode of local video encoding. This mode refers to send video locally, which only affects the video picture seen by remote users.
     * @endif
     * @if Chinese
     * 设置本地视频编码的镜像模式，即本地发送视频的镜像模式，只影响远端用户看到的视频画面。
     * @endif
     */
    public NERtcVideoMirrorMode mirrorMode = NERtcVideoMirrorMode.VIDEO_MIRROR_MODE_AUTO;


    /**
     * @if English
     * Sets the orientation mode of local video encoding. This mode refers to send video locally, which only affects the video picture seen by remote users.
     * @endif
     * @if Chinese
     * 设置本地视频编码的方向模式，即本地发送视频的方向模式，同时影响本端用户的预览画面和远端用户看到的视频画面。
     * @endif
     */
    public NERtcVideoOutputOrientationMode orientationMode = NERtcVideoOutputOrientationMode.VIDEO_OUTPUT_ORIENTATION_MODE_ADAPTATIVE;

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
        return "NERtcVideoConfig{" +
                "width=" + width +
                ", height=" + height +
                ", profile=" + videoProfile +
                ", frontCamera=" + frontCamera +
                ", colorFormat=" + colorFormat +
                ", degradationPrefer=" + degradationPrefer.ordinal() +
                ", videoCropMode=" + videoCropMode +
                ", mirror=" + mirrorMode.ordinal() +
                ", orientation=" + orientationMode.ordinal() +
                '}';
    }
}
