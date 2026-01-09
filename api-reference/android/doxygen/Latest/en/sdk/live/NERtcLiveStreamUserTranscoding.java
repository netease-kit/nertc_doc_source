/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk.live;
import java.io.Serializable;
/** NERtcLiveStreamUserTranscoding*/
public class NERtcLiveStreamUserTranscoding implements Serializable {


    /**NERtcLiveStreamVideoScaleMode */
    public enum NERtcLiveStreamVideoScaleMode {

        /**
         * @if English
         * Uniformly scale the video until one of its dimension fits the boundary (zoomed to fit). Areas that are not filled due to disparity in the aspect ratio are filled with black.。
         * @endif
         * @if Chinese
         * 视频尺寸等比缩放。优先保证视频内容全部显示。因视频尺寸与显示视窗尺寸不一致造成的视窗未被填满的区域填充背景色。
         * @endif
         */
        kNERtcLsModeVideoScaleFit,

        /**
         * @if English
         * Uniformly scale the video until it fills the visible boundaries (cropped). One dimension of the video may have clipped contents.
         * @endif
         * @if Chinese
         * 视频尺寸等比缩放。优先保证视窗被填满。因视频尺寸与显示视窗尺寸不一致而多出的视频将被截掉。
         * @endif
         */
        kNERtcLsModeVideoScaleCropFill
    }


    /**
     * @if English
     * Pulls video streams of the corresponding user to the specific uid into the live streaming. If multiple users are added, the uid must be unique.
     * @endif
     * @if Chinese
     * 将指定 uid 对应用户的视频流拉入直播。如果添加多个 users，则 uid 不能重复。
     * @endif
     */
    public long uid = -1;// -1 ：user not set , 0 local uid


    /**
     * @if English
     * Whether to play the corresponding video stream of this user to the audience during the live streaming. Settings are as follows:
     * - true: (Default) Plays this user's video stream in the live streaming.
     * - false: Disables the video stream of the user in the live streaming. 
     * The setting is invalid if the push stream mode is {@link NERtcLiveStreamTaskInfo.NERtcLiveStreamMode#kNERtcLsModeAudio}.
     * @endif
     * @if Chinese
     * 是否在直播中向观看者播放该用户的对应视频流。可设置为：
     * - true：（默认）在直播中播放该用户的视频流。
     * - false：在直播中不播放该用户的视频流。
     * 推流模式为{@link NERtcLiveStreamTaskInfo.NERtcLiveStreamMode#kNERtcLsModeAudio} 时无效。
     * @endif
     */
    public boolean videoPush = true;


    /**
     * @if English
     * Whether to mix the corresponding audio stream of this user in the live streaming. Settings are as follows:
     * - (Default) true: Mixes the corresponding audio stream for this user in the live streaming.
     * - false: Sets this user to mute state in the live streaming.
     * @endif
     * @if Chinese
     * 是否在直播中混流该用户的对应音频流。可设置为：
     * - true：（默认）在直播中混流该用户的对应音频流。
     * - false：在直播中将该用户设置为静音。
     * @endif
     */
    public boolean audioPush = true;


    /**
     * @if English
     * Video stream crop mode.
     * @endif
     * @if Chinese
     * 视频流裁剪模式
     * @endif
     */
    public NERtcLiveStreamVideoScaleMode adaption = NERtcLiveStreamVideoScaleMode.kNERtcLsModeVideoScaleFit;

    /**
     * @if English
     * Uses X parameter to set coordinate values of a canvas in the horizontal axis. Defines a point(X, Y) in the coordinate axis of a canvas that fills the upper left corner of placeholder images.
     * <br>The value range is 0 to 1920. The default value is 0. If the specified value is an odd number, the value is rounded down to the next even number.
     * @endif
     * @if Chinese
     * x 参数用于设置画布的横轴坐标值。通过 x 和 y 指定画布坐标中的一个点，该点将作为用户图像的左上角。
     * <br>取值范围为 0~1920，若设置为奇数值，会自动向下取偶。
     * @endif
     */
    public int x;


    /**
     * @if English
     * Uses Y parameter to set coordinate values of a canvas in the vertical axis. Defines a point(X,Y) in the coordinate axis of a canvas that fills the upper left corner of placeholder images.
     * <br>The value range is 0 to 1920. The default value is 0. If the specified value is an odd number, the value is rounded down to the next even number.
     * @endif
     * @if Chinese
     * y参数用于设置画布的纵轴坐标值。通过 x 和 y 指定画布坐标中的一个点，该点将作为用户图像的左上角。
     * <br>取值范围为 0~1920，若设置为奇数值，会自动向下取偶。
     * @endif
     */
    public int y;


    /**
     * @if English
     * The width of this user image in a canvas.
     * <br>The value range is 0 to 1920. The default value is 0. If the specified value is an odd number, the value is rounded down to the next even number.
     * @endif
     * @if Chinese
     * 该用户图像在画布中的宽度。
     * <br>取值范围为 0~1920，默认为 0。若设置为奇数值，会自动向下取偶。
     * @endif
     */
    public int width;

    /**
     * @if English
     * The height of this user image in a canvas.
     * <br>The value range is 0 to 1920. The default value is 0. If the specified value is an odd number, the value is rounded down to the next even number.
     * @endif
     * @if Chinese
     * 该用户图像在画布中的高度。
     * <br>取值范围为 0~1920，默认为 0。若设置为奇数值，会自动向下取偶。
     * @endif
     */
    public int height;


    /**
     * @if English
     * The layer number of the user video frame in the live video. Uses the number to determine the rendering level.
     * <br>The value range is 0 to 100. The default value is 0.
     * - (Default)A minimum value is 0. The value indicates that the image region is at the bottommost.
     * - A maximum value is 100. The number shows that the image region is at the topmost.
     * <br>@note Render regions at the same level are rendered in order in the array, stacking upwards as index is incremented.
     * @endif
     * @if Chinese
     * 直播视频上用户视频帧的图层编号，用来决定渲染层级。
     * <br>取值范围为 0~100，默认为 0。
     * - 最小值为 0（默认值），表示该区域图像位于最底层。
     * - 最大值为 100，表示该区域图像位于最顶层。
     * <br><b>注意</b>：相同层级的渲染区域会按照数组中顺序进行渲染，随着 index 递增，依次往上叠加。
     * @endif
     */
    public int zOrder;


}
