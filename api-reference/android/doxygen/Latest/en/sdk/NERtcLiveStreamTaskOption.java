/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk;

/**
 * @if English
 * Channel push stream task parameters.
 * @endif
 * @if Chinese
 * 房间推流任务参数。
 * @endif
 */
public class NERtcLiveStreamTaskOption {

    /** NERtcLiveStreamLayoutMode */
    public enum NERtcLiveStreamLayoutMode {

        /** to be added */
        layoutFloatingRightVertical,
        /** to be added */
        layoutFloatingLeftVertical,
        /** to be added */
        layoutSplitScreen,
        /** to be added */
        layoutSplitScreenScaling,
        /** to be added */
        layoutCustom,
        /** to be added */
        layoutAudioOnly,

    }

    /**
     * @if English
     * Custom ID for the push stream task. Its format is a 64-bit string consisting of letters, numbers, and underscores. Ensure this ID is unique.
     * @endif
     * @if Chinese
     * 自定义的推流任务ID。字母、数字、下划线组成的 64 位以内的字符串。请保证此ID唯一。
     * @endif
     */
    public String taskId;

    /**
     * @if English
     * Push stream address. Such as rtmp://test.url.
     * <br>Sets the push address as the return parameter called pushUrl created by server API of NetEase CommsEase. 
     * @endif
     * @if Chinese
     * 推流地址，例如 rtmp://test.url。
     * <br>此处的推流地址可设置为网易云信直播产品中服务端 API 创建房间的返回参数 pushUrl。
     * @endif
     */
    public String url;

    /**
     * @if English
     * Whether to push streams to CDN requires live recording. Default: Disabled. 
     * @endif
     * @if Chinese
     * 旁路推流是否需要进行直播录制。默认为关闭状态。
     * @endif
     */
    public boolean serverRecordEnabled = false;

    /**
     * @if English
     * Mixing stream layout mode.
     * @endif
     * @if Chinese
     * 合流布局模式。
     * @endif
     */
    public NERtcLiveStreamLayoutMode layoutMode = NERtcLiveStreamLayoutMode.layoutFloatingRightVertical;

    //optional
    /**
     * @if English
     * Main picture ID.
     * @endif
     * @if Chinese
     * 主画面 ID。
     * @endif
     */
    public long mainPictureUid = 0;

    /**
     * @if English
     * Custom layout script is only valid when using layoutMode == layoutCustom || layoutMode == layoutAudioOnly method.
     * @endif
     * @if Chinese
     * 自定义布局脚本，只在 layoutMode == layoutCustom || layoutMode == layoutAudioOnly 时有效
     * @endif
     */
    public String layoutParamters;

}
