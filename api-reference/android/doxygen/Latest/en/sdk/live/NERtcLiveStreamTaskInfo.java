/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk.live;

import android.util.Log;

import com.netease.lava.api.Trace;
import com.netease.lava.base.util.FileUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @if English
 * Configuration items of pushing stream tasks in the live streaming.
 * @endif
 * @if Chinese
 * 直播推流任务的配置项。
 * @endif
 */
public class NERtcLiveStreamTaskInfo implements Serializable {

    /**
     * to be added
     */
    public enum NERtcLiveStreamLayoutMode {

        /**
         * to be added
         */
        layoutFloatingRightVertical,

        /**
         * to be added
         */
        layoutFloatingLeftVertical,

        /**
         * to be added
         */
        layoutSplitScreen,

        /**
         * to be added
         */    
        layoutSplitScreenScaling,

        /**
         * to be added
         */
        layoutCustom,

        /**
         * to be added
         */
        layoutAudioOnly,

        /**
         * to be added
         */
        layoutSubscribe,

    }


    /**
     * @if English
     * Live streaming mode.
     * @endif
     * @if Chinese
     * 推流类型
     * @endif
     */
    public enum NERtcLiveStreamMode {

        /**
         * @if English
         * Pushes video streams.
         * @endif
         * @if Chinese
         * 推流带视频。
         * @endif
         */
        kNERtcLsModeVideo,


        /**
         * @if English
         * Pushes voice-only streams.
         * @endif
         * @if Chinese
         * 推流纯音频。
         * @endif
         */
        kNERtcLsModeAudio,

    }

    /**
     * @if English
     * Task ID for the push stream task. Its format is a 64-bit string that consists of letters, numbers, and underscores. Ensure this ID is unique.
     * @endif
     * @if Chinese
     * 自定义的推流任务ID。字母、数字、下划线组成的 64 位以内的字符串。请保证此 ID 唯一。
     * @endif
     */
    public String taskId;

    /**
     * @if English
     * URLs for pushing streams tasks. Such as rtmp://test.url.
     * <br>You can set the URL for pushing stream tasks to the pushUrl response parameter returned when you call the server API to create a room.
     * @endif
     * @if Chinese
     * 推流地址，例如 rtmp://test.url。
     * <br>此处的推流地址可设置为网易云信直播产品中服务端 API创建房间的返回参数 pushUrl。
     * @endif
     */
    public String url;

    /**
     * @if English
     * Specifies whether to enable recording during live streaming in CDN streaming scenarios. (Default): disabled.
     * @endif
     * @if Chinese
     * 旁路推流是否需要进行直播录制。默认为关闭状态。
     * @endif
     */
    public boolean serverRecordEnabled = false;

    /**
     * @if English
     * Live streaming push stream mode.
     * @endif
     * @if Chinese
     * 直播推流模式。
     * @endif
     */
    public NERtcLiveStreamMode liveMode = NERtcLiveStreamMode.kNERtcLsModeVideo;

    /**
     * @if English
     * Sets picture layout for interactive live streaming.
     * @endif
     * @if Chinese
     * 设置互动直播的画面布局。
     * @endif
     */
    public NERtcLiveStreamLayout layout;


    /**
     * @if English
     * Configurations such as encodings and parameters of audio and video streams.
     * @endif
     * @if Chinese
     * 音视频流编码参数等设置。
     * @endif
     */
    public NERtcLiveConfig config;


    /**
     * @if English
     * Reserved parameters. User-defined information send the Supplemental Enhancement Information (SEI) to the CDN live client.
     * <br>Maximum length: 4096 Bytes.
     * @endif
     * @if Chinese
     * 预留参数，用户自定义的发送到旁路推流客户端的信息，用于填充视频中 SEI 帧内容。
     * <br>长度不能超过 4096 字节。
     * @endif
     */
    public String extraInfo;


    public static NERtcLiveStreamTaskInfo deepCopy(NERtcLiveStreamTaskInfo taskInfo) {
        if (taskInfo == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutStream = null;
        ObjectOutputStream objectOutStream = null;

        ByteArrayInputStream byteArrayInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            byteArrayOutStream = new ByteArrayOutputStream();
            objectOutStream = new ObjectOutputStream(byteArrayOutStream);
            objectOutStream.writeObject(taskInfo);
            objectOutStream.flush();

            byteArrayInputStream = new ByteArrayInputStream(byteArrayOutStream.toByteArray());
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return (NERtcLiveStreamTaskInfo) objectInputStream.readObject();
        } catch (Exception e) {
            Trace.e("NERtcLiveStreamTaskInfo", "deepCopy exception: " + Log.getStackTraceString(e));
        } finally {
            FileUtil.closeQuietly(objectOutStream);
            FileUtil.closeQuietly(byteArrayOutStream);
            FileUtil.closeQuietly(byteArrayInputStream);
            FileUtil.closeQuietly(objectInputStream);
        }
        return taskInfo;
    }
}
