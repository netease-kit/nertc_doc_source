/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk;


import com.netease.lava.api.model.RTCAudioDeviceHWErrorCode;
import com.netease.lava.api.model.stats.RTCMediaPriority;
import com.netease.lava.api.model.stats.RTCStreamFallbackOption;
import com.netease.lava.nertc.impl.RtcCode;
import com.netease.lava.nertc.impl.RtcConnectionType;
import com.netease.lava.nertc.impl.RtcParameters;

/**
 * @if English
 * NERTC related constants definition.
 * @endif
 * @if Chinese
 * NERTC 相关常量定义。
 * @endif
 */
public interface NERtcConstants {

    /**
     * @if English
     * Error code definition.
     * @endif
     * @if Chinese
     * 错误码定义。
     * @endif
     */
    interface ErrorCode {

        /**
         * @if English
         * Success.
         * @endif
         * @if Chinese
         * 成功。
         * @endif
         */
        int OK = 0;

        /**
         * @if English
         * Parameter error.
         * @endif
         * @if Chinese
         * 参数错误。
         * @endif
         */
        int ILLEGAL_ARGUMENT = RtcCode.ILLEGAL_ARGUMENT;

//        /**

//         * @if English
//         * State failure.
//         * @endif
//         * @if Chinese
//         * 状态错误。
//         * @endif
//         */
//        int ILLEGAL_STATUS = RtcCode.ILLEGAL_STATUS;
//
//        /**
//         * @if English
//         * The constant is not initialized.
//         * @endif
//         * @if Chinese
//         * 未初始化。
//         * @endif
//         */
//        int ERR_UNINITIALIZED = RtcCode.ERR_UNINITIALIZED;
//
//        /**
//         * @if English
//         * Invalid operation.
//         * @endif
//         * @if Chinese
//         * 操作不支持。
//         * @endif
//         */
//        int ERR_INVALID_OPERATION = RtcCode.ERR_INVALID_OPERATION;
        /**
         * @if English
         * Request time-out.
         * @endif
         * @if Chinese
         * 请求超时。
         * @endif
         */
        int RESERVE_ERROR_TIME_OUT = 408;
        /**
         * @if English
         * Parameter error.
         * @endif
         * @if Chinese
         * 参数错误。
         * @endif
         */
        int RESERVE_ERROR_INVALID_PARAMETER = 414;
        /**
         * @if English
         * The system supports two users to use the same room. If the third user wants to use the same channel, assign channels.
         * @endif
         * @if Chinese
         * 只支持两个用户, 有第三个人试图使用相同的房间名分配房间。
         * @endif
         */
        int RESERVE_ERROR_MORE_THAN_TWO_USER = 600;

        /**
         * @if English
         * No permission. The following are reasons:
         * - The Audio and Video Call services are not enabled. Or, the service is beyond the trail period and the user does not renew the service timely.
         * - In safe mode, the Token is not set after users join the room.
         * - Other permission-related problems.
         * @endif
         * @if Chinese
         * 权限不足。原因包括：
         * - 未开通音视频通话 2.0 服务，或试用期已过、未及时续费等。
         * - 安全模式下加入房间时未设置 Token。
         * - 其他权限问题。
         * @endif
         */
        int RESERVE_ERROR_NO_PERMISSION = 403;

        /**
         * @if English
         * Error occurs in assigning servers of a specified room.
         * @endif
         * @if Chinese
         * 分配房间服务器出错。
         * @endif
         */
        int RESERVE_ERROR_RESERVE_SERVER_FAIL = 500;

        //engine error code
        /**
         * @if English
         * Kernel panic.
         * @endif
         * @if Chinese
         * 内部错误。
         * @endif
         */
        int ENGINE_ERROR_FATAL = 30001;
        /**
         * @if English
         * Memory overflow.
         * @endif
         * @if Chinese
         * 内存溢出。
         * @endif
         */
        int ENGINE_ERROR_OUT_OF_MEMORY = 30002;
        /**
         * @if English
         * Parameter error.
         * @endif
         * @if Chinese
         * 参数错误。
         * @endif
         */
        int ENGINE_ERROR_INVALID_PARAM = 30003;
        /**
         * @if English
         * No support.
         * @endif
         * @if Chinese
         * 不支持。
         * @endif
         */
        int ENGINE_ERROR_NOT_SUPPORTED = 30004;
        /**
         * @if English
         * State error.
         * @endif
         * @if Chinese
         * 状态错误。
         * @endif
         */
        int ENGINE_ERROR_INVALID_STATE = 30005;
        /**
         * @if English
         * Resources deficiency.
         * @endif
         * @if Chinese
         * 缺乏资源。
         * @endif
         */
        int ENGINE_ERROR_LACK_OF_RESOURCE = 30006;
        /**
         * @if English
         * Illegal serial number.
         * @endif
         * @if Chinese
         * 序号非法。
         * @endif
         */
        int ENGINE_ERROR_INVALID_INDEX = 30007;
        /**
         * @if English
         * The system does not find the device.
         * @endif
         * @if Chinese
         * 设备未找到。
         * @endif
         */
        int ENGINE_ERROR_DEVICE_NOT_FOUND = 30008;
        /**
         * @if English
         * Invalid ID device.
         * @endif
         * @if Chinese
         * 设备ID非法。
         * @endif
         */
        int ENGINE_ERROR_INVALID_DEVICE_SOURCEID = 30009;
        /**
         * @if English
         * Invalid video engine.
         * @endif
         * @if Chinese
         * 视频能力非法。
         * @endif
         */
        int ENGINE_ERROR_INVALID_VIDEO_PROFILE = 30010;
        /**
         * @if English
         * Device creation fails.
         * @endif
         * @if Chinese
         * 创建设备失败。
         * @endif
         */
        int ENGINE_ERROR_CREATE_DEVICE_SOURCE_FAIL = 30011;
        /**
         * @if English
         * Invalid canvas.
         * @endif
         * @if Chinese
         * 画布非法。
         * @endif
         */
        int ENGINE_ERROR_INVALID_RENDER = 30012;
        /**
         * @if English
         * Already enables the preview.
         * @endif
         * @if Chinese
         * 预览已打开。
         * @endif
         */
        int ENGINE_ERROR_DEVICE_PREVIEW_ALREADY_STARTED = 30013;
        /**
         * @if English
         * Suspension.
         * @endif
         * @if Chinese
         * 挂起。
         * @endif
         */
        int ENGINE_ERROR_TRANSMIT_PENDDING = 30014;
        /**
         * @if English
         * Connection fails.
         * @endif
         * @if Chinese
         * 连接失败。
         * @endif
         */
        int ENGINE_ERROR_CONNECT_FAIL = 30015;
        /**
         * @if English
         * Dump creation fails.
         * @endif
         * @if Chinese
         * 创建dump 失败。
         * @endif
         */
        int ENGINE_ERROR_CREATE_DUMP_FILE_FAIL = 300016;
        /**
         * @if English
         * Dump start fails.
         * @endif
         * @if Chinese
         * 开始dump失败。
         * @endif
         */
        int ENGINE_ERROR_START_DUMP_FAIL = 300017;

        /**
         * @if English
         * Already join the channel.
         * @endif
         * @if Chinese
         * 房间已加入。
         * @endif
         */
        int ENGINE_ERROR_ROOM_ALREADY_JOINED = 30100;
        /**
         * @if English
         * Not joining the room.
         * @endif
         * @if Chinese
         * 房间未加入。
         * @endif
         */
        int ENGINE_ERROR_ROOM_NOT_JOINED = 30101;
        /**
         * @if English
         * Repetitively exit the room.
         * @endif
         * @if Chinese
         * 重复离开房间。
         * @endif
         */
        int ENGINE_ERROR_ROOM_REPLEATEDLY_LEAVE = 30102;
        /**
         * @if English
         * Request for joining channel fails.
         * @endif
         * @if Chinese
         * 请求加入房间失败。
         * @endif
         */
        int ENGINE_ERROR_REQUEST_JOIN_ROOM_FAIL = 30103;
        /**
         * @if English
         * The session is not found.
         * @endif
         * @if Chinese
         * 会话未找到。
         * @endif
         */
        int ENGINE_ERROR_SESSION_NOT_FOUND = 30104;
        /**
         * @if English
         * The user is not found.
         * @endif
         * @if Chinese
         * 用户未找到。
         * @endif
         */
        int ENGINE_ERROR_USER_NOT_FOUND = 30105;
        /**
         * @if English
         * Invalid users.
         * @endif
         * @if Chinese
         * 非法用户。
         * @endif
         */
        int ENGINE_ERROR_INVALID_USERID = 30106;
        /**
         * @if English
         * The media session start fails.
         * @endif
         * @if Chinese
         * 媒体会话未建立。
         * @endif
         */
        int ENGINE_ERROR_MEDIA_NOT_STARTED = 30107;
        /**
         * @if English
         * The media source is not found.
         * @endif
         * @if Chinese
         * 媒体源未找到。
         * @endif
         */
        int ENGINE_ERROR_SOURCE_NOT_FOUND = 30108;

        /**
         * @if English
         * Absence occurs when switching rooms.
         * @endif
         * @if Chinese
         * 切换房间时不在会议中。
         * @endif
         */
        int SWITCH_CHANNEL_NOT_JOINED = 30109;

        /**
         * @if English
         * Invalid relay of media streams.
         * <br>The reason is the repetitive calls of startChannelMediaRelay method. After a successful method call, if you want to call this method again, ensure you call the stopChannelMediaRelay method to quit the current relay.
         * @endif
         * @if Chinese
         * 媒体流转发状态无效。
         * <br>原因通常为重复调用 startChannelMediaRelay。成功调用startChannelMediaRelay后，必须先调用 stopChannelMediaRelay 方法退出当前的转发状态，才能再次调用该方法。
         * @endif
         */
        int ENGINE_ERROR_CHANNEL_MEDIARELAY_STATE_INVALID = 30110;

        /**
         * @if English
         * No permission of relaying media stream.
         * <br>Reasons as follows:
         * - The type of source channel is dual channel(1v1 mode). Therefore, media streams cannot be relayed.
         * - Calls the startChannelMediaRelay method to enable the member roles of media stream relay as the audience role. Only the host can relay media stream.
         * @endif
         * @if Chinese
         * 媒体流转发权限不足。
         * <br>原因通常包括：
         * - 源房间的房间类型为双人房间（1V1模式）。此时无法转发媒体流。
         * - 调用 startChannelMediaRelay 开启媒体流转发的成员角色为观众角色，仅主播角色可以转发媒体流。
         * @endif
         */
        int ENGINE_ERROR_CHANNEL_MEDIARELAY_DENIED = 30111;

        /**
         * @if English
         * Quitting media stream relay fails.
         * <br>The reason is that the system fails to enable media stream relay. Checks whether you successfully call startChannelMediaRelay to enable media stream relay before you ensure to call stopChannelMediaRelay.
         * @endif
         * @if Chinese
         * 停止媒体流转发操作失败。
         * 原因通常为未开启媒体流转发。请确认调用 stopChannelMediaRelay 前，是否已成功调用 startChannelMediaRelay 开启媒体流转发。
         * @endif
         */
        int ENGINE_ERROR_CHANNEL_MEDIARELAY_STOP_FAILED = 30112;

        /**
         * @if English
         * Failed to join the room because the specified media stream encryption key is inconsistent with the key specified by one or more of the members who join the room.
         * <br>Reset the encryption key by calling enableEncryption.
         * @endif
         * @if Chinese
         * 设置的媒体流加密密钥与房间中其他成员不一致，加入房间失败。
         * 请通过 enableEncryption 重新设置加密密钥。
         * @endif
         */
        int ENGINE_ERROR_ENCRYPT_NOT_SUITABLE = 30113;

        /**
         * @if English
         * Connection is not found.
         * @endif
         * @if Chinese
         * 连接未找到。
         * @endif
         */
        int ENGINE_ERROR_CONNECTION_NOT_FOUND = 30200;
        /**
         * @if English
         * The media stream is not found.
         * @endif
         * @if Chinese
         * 媒体流未找到。
         * @endif
         */
        int ENGINE_ERROR_STREAM_NOT_FOUND = 30201;
        /**
         * @if English
         * Adds track fails.
         * @endif
         * @if Chinese
         * 添加Track失败。
         * @endif
         */
        int ENGINE_ERROR_ADD_TRACK_FAIL = 30202;
        /**
         * @if English
         * Track is not found.
         * @endif
         * @if Chinese
         * Track未找到。
         * @endif
         */
        int ENGINE_ERROR_TRACK_NOT_FOUND = 30203;
        /**
         * @if English
         * Media disconnection.
         * @endif
         * @if Chinese
         * 媒体连接已断开。
         * @endif
         */
        int ENGINE_ERROR_MEDIA_CONNECTION_DISCONNECTED = 30204;
        /**
         * @if English
         * Signal disconnection.
         * @endif
         * @if Chinese
         * 信令断开。
         * @endif
         */
        int ENGINE_ERROR_SIGNAL_DISCONNECTED = 30205;
        /**
         * @if English
         * Kicked out of the room by the administrator.
         * @endif
         * @if Chinese
         * 被管理员踢出房间。
         * @endif
         */
        int ENGINE_ERROR_SERVER_KICKED = 30206;
        /**
         * @if English
         * Room closed.
         * @endif
         * @if Chinese
         * 房间被关闭。
         * @endif
         */
        int ENGINE_ERROR_ROOM_CLOSED = 30207;


        /**
         * @if English
         * Leave the room for switching to the destination room.
         * @endif
         * @if Chinese
         * 因为切换房间而离开房间。
         * @endif
         */
        int LEAVE_CHANNEL_FOR_SWITCH = 30208;

        /**
         * @if English
         * Leave the room for login with duplicate user id.
         * @endif
         * @if Chinese
         * 因为重复 UID 登陆而离开房间。
         * @endif
         */
        int LEAVE_CHANNEL_FOR_DUPLICATE_UID = 30209;

        /**
         * @if English
         * Media banned by server.
         * @endif
         * @if Chinese
         * 被服务器禁言。
         * @endif
         */
        int ENGINE_ERROR_MEDIA_BANNED = 30403;

    }

    /** ChannelMediaRelayState*/
    interface ChannelMediaRelayState {
        /**
         * @if English
         * Initial state. After a successful call of stopChannelMediaRelay method to stopChannelMediaRelay, stop cross-room media streaming. Occurs when the onMediaRelayReceiveEvent method is called.
         * @endif
         * @if Chinese
         * 初始状态。在成功调用 stopChannelMediaRelay 停止跨房间媒体流转发后， onMediaRelayReceiveEvent 会回调该状态。
         * @endif
         */
        int MEDIARELAY_STATE_IDLE = 0;
        /**
         * @if English
         * The SDK tries to relay cross-room media streams.
         * @endif
         * @if Chinese
         * SDK 尝试跨房间转发媒体流。
         * @endif
         */
        int MEDIARELAY_STATE_CONNECTING = 1;
        /**
         * @if English
         * Sets the host role of source channel into the target room.
         * @endif
         * @if Chinese
         * 源房间主播角色成功加入目标房间。
         * @endif
         */
        int MEDIARELAY_STATE_RUNNING = 2;
        /**
         * @if English
         * Failure occurs. See the error messages prompted by error of onMediaRelayReceiveEvent.
         * @endif
         * @if Chinese
         * 发生异常，详见 onMediaRelayReceiveEvent 的error 中提示的错误信息。
         * @endif
         */
        int MEDIARELAY_STATE_FAILURE = 3;
    }

    /** ChannelMediaRelayEvent */
    interface ChannelMediaRelayEvent {
        /**
         * @if English
         * Stops relaying media streams.
         * @endif
         * @if Chinese
         * 媒体流转发停止。
         * @endif
         */
        int MEDIARELAY_EVENT_DISCONNECT = 0;
        /**
         * @if English
         * Connects the SDK to the server. Starts trying to relay media streams.
         * @endif
         * @if Chinese
         * SDK 正在连接服务器，开始尝试转发媒体流。
         * @endif
         */
        int MEDIARELAY_EVENT_CONNECTING = 1;
        /**
         * @if English
         * Server is connected
         * @endif
         * @if Chinese
         * 连接服务器成功。
         * @endif
         */
        int MEDIARELAY_EVENT_CONNECTED = 2;
        /**
         * @if English
         * Relays video media streams to the target channel.
         * @endif
         * @if Chinese
         * 视频媒体流成功转发到目标房间。
         * @endif
         */
        int MEDIARELAY_EVENT_VIDEOSENTSUCCESS = 3;
        /**
         * @if English
         * Relays audio media streams to the target channel.
         * @endif
         * @if Chinese
         * 音频媒体流成功转发到目标房间。
         * @endif
         */
        int MEDIARELAY_EVENT_AUDIOSENTSUCCESS = 4;
        /**
         * @if English
         * Relays screen sharing and other media streams to the target channel.
         * @endif
         * @if Chinese
         * 屏幕共享等其他媒体流成功转发到目标房间。
         * @endif
         */
        int MEDIARELAY_EVENT_OTHERSTREAMSENTSUCCESS = 5;
        /**
         * @if English
         * Media stream relay fails. Reasons are as follows:
         * - RESERVE_ERROR_INVALID_PARAMETER(414): Errors occur when requesting parameters.
         * - ENGINE_ERROR_CHANNEL_MEDIARELAY_STATE_INVALID(30110): Repetitive calls of the startChannelMediaRelay method.
         * - ENGINE_ERROR_CHANNEL_MEDIARELAY_DENIED(30111): No permissions of relaying media streams. For example, when calling the channel members of the startChannelMediaRelay as the host role or channel as dual call channels, media streams are not supported to be relayed.
         * - ENGINE_ERROR_CHANNEL_MEDIARELAY_STOP_FAILED(30112): Before calling the stopChannelMediaRelay method, the SDK doesn't call the stopChannelMediaRelay method.
         * @endif
         * @if Chinese
         * 媒体流转发失败。原因包括：
         * - RESERVE_ERROR_INVALID_PARAMETER(414)：请求参数错误。
         * - ENGINE_ERROR_CHANNEL_MEDIARELAY_STATE_INVALID(30110)：重复调用 startChannelMediaRelay。
         * - ENGINE_ERROR_CHANNEL_MEDIARELAY_DENIED(30111)：媒体流转发权限不足。例如调用 startChannelMediaRelay 的房间成员为主播角色、或房间为双人通话房间，不支持转发媒体流。
         * - ENGINE_ERROR_CHANNEL_MEDIARELAY_STOP_FAILED(30112)：调用 stopChannelMediaRelay 前，未调用 startChannelMediaRelay。
         * @endif
         */
        int MEDIARELAY_EVENT_FAILURE = 100;
    }

    /**
     * @if English
     * Cloud proxy type.
     * @endif
     * @if Chinese
     * 云代理模式。
     * @endif
     */
    interface TransportType {
        /**
         * @if English
         * 0: Do not use the cloud proxy.
         * @endif
         * @if Chinese
         * 0：关闭已设置的云代理。
         * @endif
         */
        int TRANSPORT_TYPE_NONE_PROXY = 0;
        /**
         * @if English
         * 1: Sets the cloud proxy for the UDP protocol.
         * @endif
         * @if Chinese
         * 1: 开启 UDP 协议的云代理。
         * @endif
         */
        int TRANSPORT_TYPE_UDP_PROXY = 1;
    }

    /**
     * @if English
     * Cloud proxy type.
     * @endif
     * @if Chinese
     * 云代理模式。
     * @endif
     */
    interface CameraPosition {
        /**
         * @if English
         * 0: .
         * @endif
         * @if Chinese
         * 0：后置摄像头。
         * @endif
         */
        int CAMERA_POSITION_BACK = 0;
        /**
         * @if English
         * 1: .
         * @endif
         * @if Chinese
         * 1: 前置摄像头。
         * @endif
         */
        int CAMERA_POSITION_FRONT = 1;
    }


    /**
     * @if English
     * Warning code definition.
     * @endif
     * @if Chinese
     * 警告码定义。
     * @endif
     */
    interface WarningCode {

        /**
         * @if English
         * The video Codec capability of the current client device does not match with the channel. For example, the device does not support VP8 and other encoding types. In this channel, the system may fail to implement video Codec. The local server may not be able to display some video images of the remote Server normally, and the remote Server may also not be able to display the local image.
         * @endif
         * @if Chinese
         * 当前客户端设备视频编解码能力与房间不匹配，例如设备不支持 VP8 等编码类型。在此房间中可能无法成功进行视频编解码，即本端可能无法正常显示某些远端的视频画面，同样远端也可能无法显示本端画面。
         * @endif
         */
        int ABILITY_NOT_MATCH = 406;


        /**
         * @if English
         * audio asl fallback
         * @endif
         * @if Chinese
         * 音频自动选路回退
         * @endif
         */
        int ASL_AUDIO_FALLBACK = 407;
    }

    /**
     * @if English
     * User roles can be the host or audience.
     * @endif
     * @if Chinese
     * 与会者角色。可设置为主播或观众。
     * @endif
     */
    interface UserRole {
        /**
         * @if English
         * (Default) Hosts in the live streaming mode who can operate audio and video devices such as cameras, publish streams, configure interactive live streaming push tasks. Joining and exiting channels are visible to other users in the channel.
         * @endif
         * @if Chinese
         * （默认）直播模式中的主播，可以操作摄像头等音视频设备、发布流、配置互动直播推流任务、上下线对房间内其他用户可见。
         * @endif
         */
        int CLIENT_ROLE_BROADCASTER = 0;

        /**
         * @if English
         * Audiences in live streaming mode who can only receive audio and video streams. Audiences can't operate audio and video devices, configure interactive live streaming push tasks. Joining and exiting channels are visible to other users in the channel.
         * @endif
         * @if Chinese
         * 直播模式中的观众，观众只能接收音视频流，不支持操作音视频设备、配置互动直播推流任务、上下线不通知其他用户。
         * @endif
         */
        int CLIENT_ROLE_AUDIENCE = 1;
    }

    /**
     * @if English
     * Video clarity.
     * @endif
     * @if Chinese
     * 视频清晰度。
     * @endif
     */
    interface VideoProfile {

        /**
         * @if English
         * Low definition.（160×120/90 @15fps）
         * @endif
         * @if Chinese
         * 普清，（160×120/90 @15fps）。
         * @endif
         */
        int Lowest = 0;

        /**
         * @if English
         * Standard definition.（320x180/240 @15fps）
         * @endif
         * @if Chinese
         * 标清,（320x180/240 @15fps）。
         * @endif
         */
        int LOW = 1;

        /**
         * @if English
         * High definition.(640x360/480 @30fps)
         * @endif
         * @if Chinese
         * 高清, (640x360/480 @30fps)。
         * @endif
         */
        int STANDARD = 2;

        /**
         * @if English
         * Super definition.(1280x720 @30fps)
         * @endif
         * @if Chinese
         * 超清, (1280x720 @30fps)。
         * @endif
         */
        int HD720P = 3;

        /**
         * @if English
         * 1080P, (1920x1080 @30fps)
         * @endif
         * @if Chinese
         * 1080P, (1920x1080 @30fps)。
         * @endif
         */
        int HD1080p = 4;
    }

    /**
     * @if English
     * Codec mode. The mode is mainly used to differ software codec from hardware codec.
     * @endif
     * @if Chinese
     * 编解码模式，主要用来区分软件编解码和硬件编解码。
     * @endif
     */
    interface MediaCodecMode {
        /**
         * @if English
         * The system automatically selects the codec.
         * @endif
         * @if Chinese
         * 系统自动选择编解码器。
         * @endif
         */
        String MEDIA_CODEC_DEFAULT = RtcParameters.MEDIA_CODEC_DEFAULT;


        /**
         * @if English
         * Preferential use of hardware codecs.
         * @endif
         * @if Chinese
         * 优先使用硬件编解码器。
         * @endif
         */
        String MEDIA_CODEC_HARDWARE = RtcParameters.MEDIA_CODEC_HARDWARE;


        /**
         * @if English
         * Preferential use of software codecs.
         * @endif
         * @if Chinese
         * 优先使用软件编解码器。
         * @endif
         */
        String MEDIA_CODEC_SOFTWARE = RtcParameters.MEDIA_CODEC_SOFTWARE;
    }


    /**
     * @if English
     * Video canvas scaling mode.
     * @endif
     * @if Chinese
     * 视频画布缩放方式。
     * @endif
     */
    interface VideoScalingType {
        /**
         * @if English
         * Uniformly scale the video until one of its dimension fits the boundary (zoomed to fit). Areas that are not filled due to disparity in the aspect ratio are filled with black.
         * @endif
         * @if Chinese
         * 适应视频，视频尺寸等比缩放。优先保证视频内容全部显示。若视频尺寸与显示视窗尺寸不一致，视窗未被填满的区域填充背景色。
         * @endif
         */
        int SCALE_ASPECT_FIT = 0;

        /**
         * @if English
         * Uniformly scale the video until it fills the visible boundaries (cropped). One dimension of the video may have clipped contents.
         * @endif
         * @if Chinese
         * 适应区域，视频尺寸等比缩放。保证所有区域被填满，视频超出部分会被裁剪。
         * @endif
         */
        int SCALE_ASPECT_FILL = 1;

        /**
         * @if English
         * A compromise solution that scales video size with non-equally. Ensure to fully display the video content and fill the viewport.
         * @endif
         * @if Chinese
         * 折中方案，视频尺寸非等比缩放。保证视频内容全部显示，且填满视窗。
         * @endif
         */
        int SCALE_ASPECT_BALANCED = 2;
    }

    /**
     * @if English
     * Network type definition.
     * @see NERtcCallbackEx#onConnectionTypeChanged(int)
     * @endif
     * @if Chinese
     * 网络类型定义。
     * @see NERtcCallbackEx#onConnectionTypeChanged(int)。
     * @endif
     */
    interface ConnectionType {

        /**
         * @if English
         * Unknown data connection.
         * @endif
         * @if Chinese 
         * 未知类型。
         * @endif
         */
        int CONNECTION_UNKNOWN = RtcConnectionType.CONNECTION_UNKNOWN;

        /**
         * @if English
         * The Ethernet data connection.
         * @endif
         * @if Chinese 
         * 以太网数据连接。
         * @endif
         */
        int CONNECTION_ETHERNET = RtcConnectionType.CONNECTION_ETHERNET;

        /**
         * @if English
         * The WIFI data connection.
         * @endif
         * @if Chinese
         * WIFI
         * @endif
         */
        int CONNECTION_WIFI = RtcConnectionType.CONNECTION_WIFI;

        /**
         * @if English
         * The Mobile(2G) data connection.
         * @endif
         * @if Chinese 
         * 2G 移动网络。
         * @endif
         */
        int CONNECTION_2G = RtcConnectionType.CONNECTION_2G;

        /**
         * @if English
         * The Mobile(3G) data connection.
         * @endif
         * @if Chinese 
         * 3G 移动网络。
         * @endif
         */
        int CONNECTION_3G = RtcConnectionType.CONNECTION_3G;

        /**
         * @if English
         * The Mobile(4G) data connection.
         * @endif
         * @if Chinese 
         * 4G 移动网络。
         * @endif
         */
        int CONNECTION_4G = RtcConnectionType.CONNECTION_4G;

        /**
         * @if English
         * The Bluetooth data connection.
         * @endif
         * @if Chinese 
         * 蓝牙连接。
         * @endif
         */
        int CONNECTION_BLUETOOTH = RtcConnectionType.CONNECTION_BLUETOOTH;

        /**
         * @if English
         * The Mobile(5G) data connection，support by Android 11.
         * @endif
         * @if Chinese 
         * 5G 移动网络。
         * @endif
         */
        int CONNECTION_5G = RtcConnectionType.CONNECTION_5G;

        /**
         * @if English
         * The absence of a connection type.
         * @endif
         * @if Chinese 
         * 网络连接类型缺失。
         * @endif
         */
        int CONNECTION_NONE = RtcConnectionType.CONNECTION_NONE;

    }

    /**
     * @if English
     * Channel connection state.
     * @endif
     * @if Chinese
     * 当前房间的连接状态。
     * @endif
     */
    interface ConnectionState {

        /**
         * @if English
         * Disconnection.
         * @endif
         * @if Chinese
         * 尚未加入房间。
         * <br>该状态表示当前处于：
         * - 调用 {@link NERtc#init()} 接口之后、调用 {@link NERtc#joinChannel()} 接口之前的阶段。
         * - 调用 {@link NERtc#leaveChannel()} 后离开房间的阶段。
         * @endif
         */
        int CONNECTION_STATE_DISCONNECTED = 1;

        /**
         * @if English
         * Connecting.
         * @endif
         * @if Chinese
         * 正在加入房间。
         * <br>该状态表示 SDK 处于调用 {@link NERtc#joinChannel()} 接口之后，正在建立房间连接的阶段。如果加入房间成功 App 会收到 {@link NERtcCallbackEx#onConnectionStateChanged()} 回调，当前状态变为 CONNECTION_STATE_CONNECTED。
         * @endif
         */
        int CONNECTION_STATE_CONNECTING = 2;

        /**
         * @if English
         * Connected.
         * @endif
         * @if Chinese
         * 加入房间成功。
         * <br>该状态表示用户已经加入房间，如果因网络断开或切换而导致 SDK 与房间的连接中断，SDK 会自动重连，此时 App 会收到 {@link NERtcCallbackEx#onConnectionStateChanged()} 回调 ，当前状态变为 CONNECTION_STATE_RECONNECTING。
         * @endif
         */
        int CONNECTION_STATE_CONNECTED = 3;

        /**
         * @if English
         * Reconnecting.
         * @endif
         * @if Chinese
         * 正在尝试重新加入房间。
         * <br>该状态表示 SDK 之前曾加入过房间，但因为网络原因中断了，此时 SDK 会自动尝试重新加入房间。如果重连还是没能加入房间会触发 {@link NERtcCallbackEx#onConnectionStateChanged()} 回调， 当前状态变为 CONNECTION_STATE_FAILED，SDK 停止尝试重连。
         * @endif
         */
        int CONNECTION_STATE_RECONNECTING = 4;

        /**
         * @if English
         * Connection failure.
         * @endif
         * @if Chinese
         * 加入房间失败。
         * <br>该状态表示 SDK 已经不再尝试重新加入房间。如果用户还想重新加入房间，则需要再次调用 {@link NERtc#joinChannel()}。
         * @endif
         */
        int CONNECTION_STATE_FAILED = 5;

    }

    /**
     * @if English
     * Connection state change reason.
     * @endif
     * @if Chinese
     * 房间连接状态变更原因。
     * @endif
     */
    interface ConnectionStateChangeReason {

        /**
         * @if English
         * Leave the channel.
         * @endif
         * @if Chinese
         * 离开房间。
         * @endif
         */
        int CONNECTION_CHANGED_LEAVE_CHANNEL = 1;

        /**
         * @if English
         * Close the room.
         * @endif
         * @if Chinese
         * 房间被关闭。
         * @endif
         */
        int CONNECTION_CHANGED_CHANNEL_CLOSED = 2;

        /**
         * @if English
         * Kick users out of channels.
         * @endif
         * @if Chinese
         * 用户被踢。
         * @endif
         */
        int CONNECTION_CHANGED_SERVER_KICKED = 3;

        /**
         * @if English
         * Leave for time-out.
         * @endif
         * @if Chinese
         * 超时离开。
         * @endif
         */
        int CONNECTION_CHANGED_TIME_OUT = 4;

        /**
         * @if English
         * Join a channel.
         * @endif
         * @if Chinese
         * 加入房间。
         * @endif
         */
        int CONNECTION_CHANGED_JOIN_CHANNEL = 5;

        /**
         * @if English
         * Successfully join a channel.
         * @endif
         * @if Chinese
         * 加入房间成功。
         * @endif
         */
        int CONNECTION_CHANGED_JOIN_SUCCEED = 6;

        /**
         * @if English
         * Rejoin the channel(Reconnection).
         * @endif
         * @if Chinese
         * 重新加入房间成功（重连）。
         * @endif
         */
        int CONNECTION_CHANGED_REJOIN_SUCCEED = 7;

        /**
         * @if English
         * Media disconnection.
         * @endif
         * @if Chinese
         * 媒体连接断开。
         * @endif
         */
        int CONNECTION_CHANGED_MEDIA_CONNECTION_DISCONNECTED = 8;

        /**
         * @if English
         * Signal disconnection.
         * @endif
         * @if Chinese
         * 信令连接断开。
         * @endif
         */
        int CONNECTION_CHANGED_SIGNAL_DISCONNECTED = 9;

        /**
         * @if English
         * Requests for the room fails.
         * @endif
         * @if Chinese
         * 请求房间失败。
         * @endif
         */
        int CONNECTION_CHANGED_REQUEST_CHANNEL_FAILED = 10;

        /**
         * @if English
         * Joining the room fails.
         * @endif
         * @if Chinese
         * 加入房间失败。
         * @endif
         */
        int CONNECTION_CHANGED_JOIN_CHANNEL_FAILED = 11;

        /**
         * @if English
         * Reassign a server IP address.
         * @endif
         * @if Chinese
         * 重新分配了服务端IP。
         * @endif
         */
        int CONNECTION_CHANGED_RE_DISPATCH = 12;

        /**
         * @if English
         * Start connecting to server using the cloud proxy.
         * @endif
         * @if Chinese
         * 开始使用云代理进行连接。
         * @endif
         */
        int CONNECTION_CHANGED_SETTING_PROXY_SERVER = 13;


    }

    /**
     * @if English
     * Audio device types.
     * @endif
     * @if Chinese
     * 语音设备类型。
     * @endif
     */
    interface AudioFocusMode {
        /**
         * @if English
         * No request for audio focus.
         * @endif
         * @if Chinese
         * 不请求音频焦点。
         * @endif
         */
        int AUDIOFOCUS_OFF = 0;
        /**
         * @if English
         * Gains the audio focus in a long period.
         * @endif
         * @if Chinese
         * 长时间获得焦点。
         * @endif
         */
        int AUDIOFOCUS_GAIN = 1;

        /**
         * @if English
         * Gains transient audio focus. Release the focus after losing it. Such as notification sounds.
         * @endif
         * @if Chinese
         * 短暂性获得焦点，用完应立即释放，比如notification sounds。
         * @endif
         */
        int AUDIOFOCUS_GAIN_TRANSIENT = 2;

        /**
         * @if English
         * Temporary requests for lowering the volume of other audio applications. Mixable playback is supportable.
         * @endif
         * @if Chinese
         * 临时请求, 降低其他音频应用的声音，可混音播放。
         * @endif
         */
        int AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK = 3;

        /**
         * @if English
         * Gains transient audio focus to enable recording or audio recognition.
         * @endif
         * @if Chinese
         * 短暂性获得焦点，录音或者语音识别。
         * @endif
         */
        int AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE = 4;
    }

    /**
     * @if English
     * Audio device types.
     * @endif
     * @if Chinese
     * 语音设备类型。
     * @endif
     */
    interface AudioDevice {

        /**
         * @if English
         * The speaker.
         * @endif
         * @if Chinese
         * 扬声器。
         * @endif
         */
        int SPEAKER_PHONE = 0;

        /**
         * @if English
         * Wired headset.
         * @endif
         * @if Chinese
         * 有线耳机。
         * @endif
         */
        int WIRED_HEADSET = 1;

        /**
         * @if English
         * Earpiece.
         * @endif
         * @if Chinese
         * 听筒。
         * @endif
         */
        int EARPIECE = 2;

        /**
         * @if English
         * Bluetooth headset.
         * @endif
         * @if Chinese
         * 蓝牙耳机。
         * @endif
         */
        int BLUETOOTH_HEADSET = 3;
    }

    /**
     * @if English
     * Video device types.
     * @endif
     * @if Chinese
     * 音频设备类型。
     * @endif
     */
    interface AudioDeviceType {

        /**
         * @if English
         * Audio capturing devices.
         * @endif
         * @if Chinese
         * 音频采集设备。
         * @endif
         */
        int RECORD = 1;

        /**
         * @if English
         * Audio player devices.
         * @endif
         * @if Chinese
         * 音频播放设备。
         * @endif
         */
        int PLAYOUT = 2;
    }

    /**
     * @if English
     * Audio device state.
     * @endif
     * @if Chinese
     * 音频设备状态。
     * @endif
     */
    interface AudioDeviceState {

        /**
         * @if English
         * Enabled.
         * @endif
         * @if Chinese
         * 打开成功。
         * @endif
         */
        int OPENED = 1;

        /**
         * @if English
         * Disabled.
         * @endif
         * @if Chinese
         * 已关闭。
         * @endif
         */
        int CLOSED = 2;

        /**
         * @if English
         * Initializing fails.
         * @endif
         * @if Chinese
         * 初始化失败。
         * @endif
         */
        int INIT_ERROR = 3;

        /**
         * @if English
         * Enabling fails.
         * @endif
         * @if Chinese
         * 开启失败。
         * @endif
         */
        int START_ERROR = 4;

        /**
         * @if English
         * Unknown errors.
         * @endif
         * @if Chinese
         * 未知错误。
         * @endif
         */
        int UNKNOWN_ERROR = 5;

    }

    /**
     * @if English
     * Video device state.
     * @endif
     * @if Chinese
     * 视频设备状态。
     * @endif
     */
    interface VideoDeviceState {

        /**
         * @if English
         * Enabled.
         * @endif
         * @if Chinese
         * 打开成功。
         * @endif
         */
        int OPENED = 1;

        /**
         * @if English
         * Disabled.
         * @endif
         * @if Chinese
         * 已关闭。
         * @endif
         */
        int CLOSED = 2;

        /**
         * @if English
         * Grabbed by other applications, the system disconnects the camera.
         * @endif
         * @if Chinese
         * 相机断开，可能被其他应用抢占。
         * @endif
         */
        int DISCONNECTED = 3;

        /**
         * @if English
         * Camera freezes.
         * @endif
         * @if Chinese
         * 相机冻结。
         * @endif
         */
        int FREEZED = 4;

        /**
         * @if English
         * Unknown error.
         * @endif
         * @if Chinese
         * 未知错误。
         * @endif
         */
        int UNKNOWNERROR = 5;
    }

    /**
     * @if English
     * Video crop mode.
     * @endif
     * @if Chinese
     * 视频裁剪模式。
     * @endif
     */
    interface VideoCropMode {

        /**
         * @if English
         * (Default)The proportion of camera.
         * @endif
         * @if Chinese
         * 相机原始比例。
         * @endif
         */
        int DEFAULT = 0;

        /**
         * @if English
         * Crops by the 16:9 aspect ratio.
         * @endif
         * @if Chinese
         * 16:9 裁剪。
         * @endif
         */
        int CROP_16x9 = 1;

        /**
         * @if English
         * Crops by the 4:3 aspect ratio.
         * @endif
         * @if Chinese
         * 4:3 裁剪。
         * @endif
         */
        int CROP_4x3 = 2;

        /**
         * @if English
         * Crops by the 1:1 aspect ratio.
         * @endif
         * @if Chinese
         * 1:1 裁剪。
         * @endif
         */
        int CROP_1x1 = 3;
    }

    /**
     * @if English
     * Video data color space format.
     * @endif
     * @if Chinese
     * 视频数据颜色空间格式。
     * @endif
     */
    interface VideoColorFormat {

        /**
         * @if English
         * TEXTURE format.
         * @endif
         * @if Chinese
         * TEXTURE 格式。
         * @endif
         */
        int TEXTURE = 0; //default

        /**
         * @if English
         * I420 format.
         * @endif
         * @if Chinese
         * I420 格式。
         * @endif
         */
        int I420 = 1;

        /**
         * @if English
         * NV21 format.
         * @endif
         * @if Chinese
         * NV21 格式。
         * @endif
         */
        int NV21 = 2;
    }

    /**
     * @if English
     * Camera type.
     * @endif
     * @if Chinese
     * 摄像头类型。
     * @endif
     */
    interface CameraType {

        /**
         * @if English
         * Camera1
         * @endif
         * @if Chinese
         * Camera1。
         * @endif
         */
        int Camera1 = 1; //default

        /**
         * @if English
         * Camera1
         * @endif
         * @if Chinese
         * Camera1。
         * @endif
         */
        int Camera2 = 2;
    }

    /**
     * @if English
     * Video publishing stream types.
     * @endif
     * @if Chinese
     * 视频发布流类型。
     * @endif
     */
    interface VideoSendMode {
        /**
         * @if English
         * Transmits streams with the subscription format to the server.
         * @endif
         * @if Chinese
         * 按对端订阅格式发流。
         * @endif
         */
        int SEND_ON_PUB_NONE = 0;

        /**
         * @if English
         * High streams transmission(Default).
         * @endif
         * @if Chinese
         * 初始发送大流。
         * @endif
         */
        int SEND_ON_PUB_HIGH = 1;

        /**
         * @if English
         * Low streams transmission(Default).
         * @endif
         * @if Chinese
         * 初始发布小流。
         * @endif
         */
        int SEND_ON_PUB_LOW = 2;

        /**
         * @if English
         * High and low streams transmission(Default).
         * @endif
         * @if Chinese
         * 初始大小流同时发送。
         * @endif
         */
        int SEND_ON_PUB_ALL = 3;
    }

    /**
     * @if English
     * Server recording mode.
     * @endif
     * @if Chinese
     * 服务器录制模式。
     * @endif
     */
    interface ServerRecordMode {

        /**
         * @if English
         * Mix and single stream record.
         * @endif
         * @if Chinese
         * 合流+单流录制。
         * @endif
         */
        int MIX_AND_SINGLE = 0;

        /**
         * @if English
         * Mix stream recording mode.
         * @endif
         * @if Chinese
         * 合流录制模式。
         * @endif
         */
        int MIX = 1;

        /**
         * @if English
         * Single stream recording mode.
         * @endif
         * @if Chinese
         * 单流录制模式。
         * @endif
         */
        int SINGLE = 2;

    }


    /**
     * @if English
     * System category.
     * @endif
     * @if Chinese
     * 系统分类。
     * @endif
     */
    interface OSCategory {
        /**
         * @if English
         * Android universal devices.
         * @endif
         * @if Chinese
         * Android 通用设备。
         * @endif
         */
        int PHONE = 1;


        /**
         * @if English
         * TV or set-top box devices.
         * @endif
         * @if Chinese
         * TV或者机顶盒设备。
         * @endif
         */
        int TV = 6;


        /**
         * @if English
         * Watch devices.
         * @endif
         * @if Chinese
         * 手表设备。
         * @endif
         */
        int WATCH = 256;
    }

    /**
     * @if English
     * Audio properties. Sets sample rates, bitrate, encoding mode and the number of channels.
     * @endif
     * @if Chinese
     * 音频属性。设置采样率，码率，编码模式和声道数。
     * @endif
     */
    interface AudioProfile {
        /**
         * @if English
         * (Default) STANDARD is for audio scenarios and HIGH_QUALITY for music ones.
         * @endif
         * @if Chinese
         * 默认设置。语音场景下为 STANDARD，音乐场景下为 HIGH_QUALITY。
         * @endif
         */
        int DEFAULT = 0;

        /**
         * @if English
         * Standard audio quality mode. The sample rate is 16 kHz. The maximum value of audio encoding, mono sound, and encoding bitrate is 20 Kbps.
         * @endif
         * @if Chinese
         * 标准音质模式。采样率为 16 kHz、语音编码、单声道、编码码率最大值为 20 Kbps。
         * @endif
         */
        int STANDARD = 1;

        /**
         * @if English
         * Standard extension mode. The sample rate is 16 kHz. The maximum value of audio encoding, mono sound, and encoding bitrate is 32 Kbps.
         * @endif
         * @if Chinese
         * 标准扩展模式。采样率为 16 kHz、语音编码、单声道、编码码率最大值为 32 Kbps。
         * @endif
         */
        int STANDARD_EXTEND = 2;

        /**
         * @if English
         * Medium audio quality mode. The sample rate is 48 kHz. The maximum value of audio encoding, mono sound, and encoding bitrate is 32 Kbps.
         * @endif
         * @if Chinese
         * 中等音质模式。采样率为 48 kHz、音乐编码、单声道、编码码率最大值为 64 Kbps。
         * @endif
         */
        int MIDDLE_QUALITY = 3;
        /**
         * @if English
         * Medium audio quality mode(stereo). The sample rate is 48 kHz. The maximum value of audio encoding, mono sound, and the encoding bitrate is 64 Kbps.
         * @endif
         * @if Chinese
         * 中等音质模式（立体音）。采样率为 48 kHz、音乐编码、双声道、编码码率最大值为 80 Kbps。
         * @endif
         */
        int MIDDLE_QUALITY_STEREO = 4;
        /**
         * @if English
         * High audio quality mode. The sample rate is 48 kHz. The maximum value of audio encoding, mono sound, and encoding bitrate is 64 Kbps.
         * @endif
         * @if Chinese
         * 高音质模式。采样率为 48 kHz、音乐编码、单声道、编码码率最大值为 96 Kbps。
         * @endif
         */
        int HIGH_QUALITY = 5;
        /**
         * @if English
         * High audio quality mode(stereo). The sample rate is 48 kHz. The maximum value of audio encoding, mono sound, and encoding bitrate is 128 Kbps.
         * @endif
         * @if Chinese
         * 高音质模式（立体音）。采样率为 48 kHz、音乐编码、双声道、编码码率最大值为 128 Kbps。
         * @endif
         */
        int HIGH_QUALITY_STEREO = 6;
    }

    /**
     * @if English
     * Audio application scenarios.
     * @endif
     * @if Chinese
     * 音频应用场景。
     * @endif
     */
    interface AudioScenario {
        /**
         * @if English
         * AudioScenario is set to `SPEECH` by default.
         * @endif
         * @if Chinese
         * 默认设置为Speech。
         * @endif
         */
        int DEFAULT = 0;
        /**
         * @if English
         * Speech scenario. We recommend you to set AudioProfile to MIDDLE_QUALITY and lower.
         * @endif
         * @if Chinese
         * 语音场景. AudioProfile 推荐使用 MIDDLE_QUALITY 及以下。
         * @endif
         */
        int SPEECH = 1;
        /**
         * @if English
         * Music scenario. We recommend you to set AudioProfile to MIDDLE_QUALITY_STEREO and higher.
         * @endif
         * @if Chinese
         * 音乐场景。AudioProfile 推荐使用 MIDDLE_QUALITY_STEREO 及以上。
         * @endif
         */
        int MUSIC = 2;
        /**
         * @if English
         * Chatroom scenario. We recommend you to set AudioProfile to HIGH_QUALITY and higher.
         * @endif
         * @if Chinese
         * 语聊房场景，AudioProfile推荐 HIGH_QUALITY及以上。
         * @endif
         */
        int CHATROOM = 3;

    }

    interface WarnCode {

    }

    /**
     * @if English
     * Log level.
     * @endif
     * @if Chinese
     * 日志级别。
     * @endif
     */
    interface LogLevel {

        /**
         * @if English
         * Fatal level log information.
         * @endif
         * @if Chinese
         * Fatal 级别日志信息。
         * @endif
         */
        int FATAL = 0;
        /**
         * @if English
         * Error level log information.
         * @endif
         * @if Chinese
         * Error 级别日志信息。
         * @endif
         */
        int ERROR = 1;
        /**
         * @if English
         * Warning level log information.
         * @endif
         * @if Chinese
         * Warning 级别日志信息。
         * @endif
         */
        int WARNING = 2;
        /**
         * @if English
         * Info level log information(Default).
         * @endif
         * @if Chinese
         * Info 级别日志信息。默认级别。
         * @endif
         */
        int INFO = 3;
        /**
         * @if English
         * Detail Info level log information.
         * @endif
         * @if Chinese
         * Detail Info 级别日志信息。
         * @endif
         */
        int DETAIL_INFO = 4;
        /**
         * @if English
         * Verbos level log information.
         * @endif
         * @if Chinese
         * Verbos 级别日志信息。
         * @endif
         */
        int VERBOS = 5;
        /**
         * @if English
         * Debug level log information. If you want to get the most complete logs, you can set the log level as this level.
         * @endif
         * @if Chinese
         * Debug 级别日志信息。如果你想获取最完整的日志，可以将日志级别设为该等级。
         * @endif
         */
        int DEBUG = 6;
    }

    /**
     * @if English
     * Error occurs at runtime.
     * @endif
     * @if Chinese
     * 运行时错误。
     * @endif
     */
    interface RuntimeError {

        /**
         * @if English
         * No audio permission.
         * @endif
         * @if Chinese
         * 没有音频权限。
         * @endif
         */
        int ADM_NO_AUTHORIZE = 50000;

        /**
         * @if English
         * The microphone is not initialized.
         * @endif
         * @if Chinese
         * 麦克风初始化失败。
         * @endif
         */
        int ADM_RECORD_INIT_ERROR = 50103;

        /**
         * @if English
         * The microphone is not enabled.
         * @endif
         * @if Chinese
         * 麦克风打开失败。
         * @endif
         */
        int ADM_RECORD_START_ERROR = 40632;

        /**
         * @if English
         * Error occurs when running the microphone.
         * @endif
         * @if Chinese
         * 麦克风运行错误。
         * @endif
         */
        int ADM_RECORD_UNKNOWN_ERROR = 50105;

        /**
         * @if English
         * The video player is not initialized.
         * @endif
         * @if Chinese
         * 音频播放设备初始化失败。
         * @endif
         */
        int ADM_PLAYOUT_INIT_ERROR = 50203;

        /**
         * @if English
         * The audio player device is not enabled.
         * @endif
         * @if Chinese
         * 音频播放设备打开失败。
         * @endif
         */
        int ADM_PLAYOUT_START_ERROR = 50204;

        /**
         * @if English
         * Error occurs in running audio player devices.
         * @endif
         * @if Chinese
         * 音频播放设备运行错误。
         * @endif
         */
        int ADM_PLAYOUT_UNKNOWN_ERROR = 50205;


        /**
         * @if English
         * No video permissions
         * @endif
         * @if Chinese
         * 没有视频权限
         * @endif
         */
        int VDM_NO_AUTHORIZE = 50001;

        /**
         * @if English
         * Camera is used by other applications.
         * @endif
         * @if Chinese
         * 相机被其他应用抢占。
         * @endif
         */
        int VDM_CAMERA_DISCONNECT_ERROR = 50303;

        /**
         * @if English
         * Camera freezes.
         * @endif
         * @if Chinese
         * 相机已冻结。
         * @endif
         */
        int VDM_CAMERA_FREEZED_ERROR = 50304;

        /**
         * @if English
         * Camera runtime error.
         * @endif
         * @if Chinese
         * 相机运行错误。
         * @endif
         */
        int VDM_CAMERA_UNKNOWN_ERROR = 50305;

    }

    /**
     * @if English
     * Network conditions.
     * @endif
     * @if Chinese
     * 网络状态
     * @endif
     */
    interface NetworkStatus {

        /**
         * @if English
         * Unknown.
         * @endif
         * @if Chinese
         * 未知
         * @endif
         */
        int UNKNOWN = 0;

        /**
         * @if English
         * Excellent.
         * @endif
         * @if Chinese
         * 非常好
         * @endif
         */
        int EXCELLENT = 1;

        /**
         * @if English
         * Good.
         * @endif
         * @if Chinese
         * 好
         * @endif
         */
        int GOOD = 2;

        /**
         * @if English
         * Poor.
         * @endif
         * @if Chinese
         * 不太好
         * @endif
         */
        int POOR = 3;

        /**
         * @if English
         * Bad.
         * @endif
         * @if Chinese
         * 差
         * @endif
         */
        int BAD = 4;

        /**
         * @if English
         * Very bad.
         * @endif
         * @if Chinese
         * 非常差
         * @endif
         */
        int VERYBAD = 5;

        /**
         * @if English
         * Internet disconnection.
         * @endif
         * @if Chinese
         * 无网络
         * @endif
         */
        int DOWN = 6;
    }

    /**
     * @if English
     * Live streaming push state.
     * @endif
     * @if Chinese
     * 直播推流状态。
     * @endif
     */
    interface LiveStreamState {

        /**
         * @if English
         * The system is pushing streams.
         * @endif
         * @if Chinese
         * 推流中。
         * @endif
         */
        int STATE_PUSHING = 505;

        /**
         * @if English
         * Failure occurs when pushing in the interactive live streaming.
         * @endif
         * @if Chinese
         * 互动直播推流失败。
         * @endif
         */
        int STATE_PUSH_FAIL = 506;

        /**
         * @if English
         * Stops pushing streams.
         * @endif
         * @if Chinese
         * 推流结束。
         * @endif
         */
        int STATE_PUSH_STOPPED = 511;

        /**
         * @if English
         * Error occurs when setting background images.
         * @endif
         * @if Chinese
         * 背景图片设置出错。
         * @endif
         */
        int STATE_IMAGE_ERROR = 512;
    }

    /**
     * @if English
     * The channel profile.
     * @endif
     * @if Chinese
     * 房间场景。
     * @endif
     */
    interface RTCChannelProfile {

        /**
         * @if English
         * (Default) The communication profile allows all users in the channel to publish and receive audio and video streams. The profile is applicable to scenarios such as audio call and video group chat.
         * @endif
         * @if Chinese
         * （默认）通信场景。该场景下，房间内所有用户都可以发布和接收音、视频流。适用于语音通话、视频群聊等应用场景。
         * @endif
         */
        int COMMUNICATION = 0;

        /**
         * @if English
         * In the live streaming profile where has two user roles of host and audience. With the setClientRole method, the host can publish and receive audio and video streams, and the audience directly receives streams. It is applicable to scenarios such as chat room, video live streaming, interactive lecture hall.
         * @endif
         * @if Chinese
         * 直播场景。该场景有主播和观众两种用户角色，可以通过setClientRole设置。主播可以发布和接收音视频流，观众直接接收流。适用于语聊房、视频直播、互动大班课等应用场景。
         * @endif
         */
        int LIVE_BROADCASTING = 1;
    }


    /**
     * @if English
     * Video stream types.
     * @endif
     * @if Chinese
     * 视频流类型。
     * @endif
     */
    interface VideoType {

        /**
         * @if English
         * mainstreams.
         * @endif
         * @if Chinese
         * 主流。
         * @endif
         */
        int VIDEO_TYPE_MAIN = 1;

        /**
         * @if English
         * substreams .
         * @endif
         * @if Chinese
         * 辅流。
         * @endif
         */
        int VIDEO_TYPE_SUB = 2;
    }

    /**
     * AudioHeadsetProfile
     */
    interface AudioHeadsetProfile {
        /** USE_NORMAL*/
        int USE_NORMAL = 0;
        /** USE_COMMUNICATION*/
        int USE_COMMUNICATION = 1;
    }

    /**
     * AudioBluetoothProfile
     */
    interface AudioBluetoothProfile {
        /** USE_A2DP*/
        int USE_A2DP = 0;
        /** USE_HFP*/
        int USE_HFP = 1;
    }

    /**
     * @if English
     * Audio mixing error.
     * @endif
     * @if Chinese
     * 伴音错误状态。
     * @endif
     */
    interface AudioMixingError {

        /**
         * @if English
         * Finishes audio mixing.
         * @endif
         * @if Chinese
         * 伴音正常结束。
         * @endif
         */
        int AUDIO_MIXING_FINISH = 0;

        /**
         * @if English
         * Audio encoding error.
         * @endif
         * @if Chinese
         * 音频解码错误。
         * @endif
         */
        int AUDIO_MIXING_ERROR_DECODE = 1;

        /**
         * @if English
         * Interruption code error.
         * @endif
         * @if Chinese
         * 操作中断码。
         * @endif
         */
        int AUDIO_MIXING_ERROR_INTERRUPT = 2;

        /**
         * @if English
         * Files corresponding to 404 http/https are not found.
         * @endif
         * @if Chinese
         * 404 http/https 对应的文件找不到。
         * @endif
         */
        int AUDIO_MIXING_ERROR_HTTP_NOT_FOUND = 3;

        /**
         * @if English
         * Enabling streams and files fails.
         * @endif
         * @if Chinese
         * 打开流/文件失败。
         * @endif
         */
        int AUDIO_MIXING_ERROR_OPEN = 4;

        /**
         * @if English
         * Failures or time-outs occur when accessing encoding information.
         * @endif
         * @if Chinese
         * 获取解码信息失败/超时。
         * @endif
         */
        int AUDIO_MIXING_ERROR_NINFO = 5;

        /**
         * @if English
         * No audio stream.
         * @endif
         * @if Chinese
         * 无音频流。
         * @endif
         */
        int AUDIO_MIXING_ERROR_NSTREAM = 6;

        /**
         * @if English
         * No decoder.
         * @endif
         * @if Chinese
         * 无解码器。
         * @endif
         */
        int AUDIO_MIXING_ERROR_NCODEC = 7;

        /**
         * @if English
         * No memory.
         * @endif
         * @if Chinese
         * 无内存。
         * @endif
         */
        int AUDIO_MIXING_ERROR_NMEM = 8;

        /**
         * @if English
         * Failures or time-outs occur when enabling encoders.
         * @endif
         * @if Chinese
         * 解码器打开失败/超时。
         * @endif
         */
        int AUDIO_MIXING_ERROR_CODEC_OPEN = 9;

        /**
         * @if English
         * Invalid audio parameters( audio channels and sample rate)
         * @endif
         * @if Chinese
         * 无效音频参数（声道、采样率）。
         * @endif
         */
        int AUDIO_MIXING_ERROR_INVALID_INFO = 10;

        /**
         * @if English
         * Time-outs occur when enabling streams and files.
         * @endif
         * @if Chinese
         * 打开流/文件超时。
         * @endif
         */
        int AUDIO_MIXING_ERROR_OPEN_TIMEOUT = 11;

        /**
         * @if English
         * Internet I0 time-outs.
         * @endif
         * @if Chinese
         * 网络IO 超时。
         * @endif
         */
        int AUDIO_MIXING_ERROR_IO_TIMEOUT = 12;

        /**
         * @if English
         * Internet IO error.
         * @endif
         * @if Chinese
         * 网络IO 错误。
         * @endif
         */
        int AUDIO_MIXING_ERROR_IO = 13;

    }

    /**
     * @if English
     * Media pub type.
     * @endif
     * @if Chinese
     * 媒体 pub 类型。
     * @endif
     */
    interface MediaPublishType {
            /**
             * @if English
             * Audio pub type.
             * @endif
             * @if Chinese
             * 音频 pub 类型。
             * @endif
             */
        int MEDIA_PUBLISH_AUDIO = 0;
    }

    /**
     * @if English
     * Media stream priority.
     * @endif
     * @if Chinese
     * 媒体流优先级。
     * @endif
     */
    interface MediaPriority {
        /**
         * @if English
         * High priority.
         * @endif
         * @if Chinese
         * 高优先级。
         * @endif
         */
        int MEDIA_PRIORITY_HIGH = RTCMediaPriority.MEDIA_PRIORITY_HIGH;

        /**
         * @if English
         * (Default) Normal priority.
         * @endif
         * @if Chinese
         * （默认）普通优先级。
         * @endif
         */
        int MEDIA_PRIORITY_NORMAL = RTCMediaPriority.MEDIA_PRIORITY_NORMAL;
    }

    /**
     * @if English
     * Recording quality.
     * @endif
     * @if Chinese
     * 录音音质。
     * @endif
     */
    interface AudioRecordingQuality {
        /**
         * @if English
         * Low audio quality.
         * @endif
         * @if Chinese
         * 低音质。
         * @endif
         */
        int AUDIO_RECORDING_QUALITY_LOW = 0;
        /**
         * @if English
         * (Default) Medium audio quality.
         * @endif
         * @if Chinese
         * （默认）中音质。
         * @endif
         */
        int AUDIO_RECORDING_QUALITY_MEDIUM = 1;

        /**
         * @if English
         * High audio quality.
         * @endif
         * @if Chinese
         * 高音质。
         * @endif
         */
        int AUDIO_RECORDING_QUALITY_HIGH = 2;
    }


    /**
     * @if English
     * Recording callback events Status Code.
     * @endif
     * @if Chinese
     * 录音回调事件状态码。
     * @endif
     */
    interface AudioRecordingCode {

        /**
         * @if English
         * Audio files format is not supported.
         * @endif
         * @if Chinese
         * 不支持的录音文件格式。
         * @endif
         */
        int AUDIO_RECORD_ERROR_SUFFIX = 1;

        /**
         * @if English
         * Setting recording files fails. Reasons are as follows:
         * - Applications have no writable permissions.
         * - No file path.
         * @endif
         * @if Chinese
         * 无法创建录音文件，原因通常包括：
         * - 应用没有磁盘写入权限。
         * - 文件路径不存在。
         * @endif
         */
        int AUDIO_RECORD_OPEN_FILE_FAILED = 2;

        /**
         * @if English
         * Starts audio recording.
         * @endif
         * @if Chinese
         * 开始录制。
         * @endif
         */
        int AUDIO_RECORD_START = 3;

        /**
         * @if English
         * Recording error usually occurs when the disk capacity is to the fullest.
         * @endif
         * @if Chinese
         * 录制错误。原因通常为磁盘空间已满，无法写入。
         * @endif
         */
        int AUDIO_RECORD_ERROR = 4;

        /**
         * @if English
         * Finish recording.
         * @endif
         * @if Chinese
         * 完成录制。
         * @endif
         */
        int AUDIO_RECORD_FINISH = 5;
    }

    /** StreamFallbackOption */
    interface StreamFallbackOption {

        /**
         * @if English
         * No fallback behavior for the locally published video stream when the uplink network condition is poor. The stream quality is not guaranteed.
         * @note The option is only applicable to the setLocalPublishFallbackOption method but the setRemoteSubscribeFallbackOption method.
         * @endif
         * @if Chinese
         * 上行网络较弱时，不对音视频流作回退处理，但不能保证音视频流的质量。
         * @note 该选项只对 setLocalPublishFallbackOption 方法有效，对 setRemoteSubscribeFallbackOption 方法无效。
         * @endif
         */
        int DISABLED = RTCStreamFallbackOption.DISABLED;

        /**
         * @if English
         * Under poor downlink network conditions, the SDK will only receive low-quality video streams with low resolution and low bitrate.
         * @note The option is only applicable to setRemoteSubscribeFallbackOption method but setRemoteSubscribeFallbackOption method.
         * @endif
         * @if Chinese
         * 在下行网络条件较差的情况下，SDK 将只接收视频小流，即低分辨率、低码率视频流。
         * @note 该选项只对 setRemoteSubscribeFallbackOption 方法有效，对 setLocalPublishFallbackOption 方法无效。 
         * @endif
         */
        int VIDEO_STREAM_LOW = RTCStreamFallbackOption.VIDEO_STREAM_LOW;

        /**
         * @if English
         * - When uplink networks are poor, publish audio streams only.
         * - When downlink networks are poor, first try to receive low-quality video streams with low resolution and bitrate. If network conditions don't display videos, return to receive audio-only streams only.
         * @endif
         * @if Chinese
         * - 上行网络较弱时，只发布音频流。
         * - 下行网络较弱时，先尝试只接收视频小流，即低分辨率、低码率视频流。如果网络环境无法显示视频，则再回退到只接收音频流。
         * @endif
         */
        int AUDIO_ONLY = RTCStreamFallbackOption.AUDIO_ONLY;
    }

    /**
     * 音频dump类型
     */
    interface AUDIO_DUMP_MODE {
        /**
         * 仅输出.dump文件（默认）
         */
        int AUDIO_DUMP_PCM = 0;
        /**
         * 输出.dump和.wav文件
         */
        int AUDIO_DUMP_ALL = 1;
        /**
         * 仅输出.wav文件
         */
        int AUDIO_DUMP_WAV = 2;
    }

    /**
     * @if English
     * Reason why the virtual background is failed or success confirmation.
     * @endif
     * @if Chinese
     * 虚拟背景未成功启用的原因或确认成功的信息。
     * @endif
     */
    interface NERtcVirtualBackgroundSourceStateReason {
        /**
         * @if English
         * 0: The virtual background is successfully enabled.
         * @endif
         * @if Chinese
         * 0:  虚拟背景开启成功。
         * @endif
         */
        int VBS_STATE_REASON_SUCCESS = 0;
        /**
         * @if English
         * 1: The custom background image does not exist. Please check the value of `source` in VirtualBackgroundSource.
         * @endif
         * @if Chinese
         * 1：自定义背景图片不存在。 请检查 VirtualBackgroundSource 中 `source` 的值。
         * @endif
         */
        int VBS_STATE_REASON_IMAGE_NOT_EXIST = 1;
        /**
         * @if English
         * 2: The image format of the custom background image is invalid. Please check the value of `source` in VirtualBackgroundSource.
         * @endif
         * @if Chinese
         * 2：自定义背景图片的图片格式无效。 请检查 VirtualBackgroundSource 中 `source` 的值。
         * @endif
         */
        int VBS_STATE_REASON_IMAGE_FORMAT_NOT_SUPPORTED = 2;
        /**
         * @if English
         * 3: The color format of the custom background image is invalid. Please check the value of `color` in VirtualBackgroundSource.
         * @endif
         * @if Chinese
         * 3：自定义背景图片的颜色格式无效。 请检查 VirtualBackgroundSource 中 `color` 的值。
         * @endif
         */
        int VBS_STATE_REASON_COLOR_FORMAT_NOT_SUPPORTED = 3;
        /**
         * @if English
         * 4: The device does not support using the virtual background.
         * @endif
         * @if Chinese
         * 4：该设备不支持使用虚拟背景。
         * @endif
         */
        int VBS_STATE_REASON_DEVICE_NOT_SUPPORTED = 4;
    }

    /**
     * @if English
     * Video watermark state.
     * @endif
     * @if Chinese
     * 视频水印状态。
     * @endif
     */
    interface NERtcLocalVideoWatermarkState {

        /**
         * @if English
         * The device is not supported.
         * @endif
         * @if Chinese
         * 设备不支持。
         * @endif
         */
        int LVW_STATE_DEVICE_NOT_SUPPORTED = 1;

        /**
         * @if English
         * The image format is not supported.
         * @endif
         * @if Chinese
         * 图片格式不支持。
         * @endif
         */
        int LVW_STATE_IMG_FORMAT_NOT_SUPPORTED = 2;

        /**
         * @if English
         * Image number error.
         * @endif
         * @if Chinese
         * 图片数量设置错误。
         * @endif
         */
        int LVW_STATE_IMG_NUM_ERR = 3;

        /**
         * @if English
         * Image size error.
         * @endif
         * @if Chinese
         * 图片尺寸设置错误。
         * @endif
         */
        int LVW_STATE_IMG_SIZE_ERR = 4;

        /**
         * @if English
         * Image resolution error.
         * @endif
         * @if Chinese
         * 图片分辨率设置错误。
         * @endif
         */
        int LVW_STATE_IMG_FPS_ERR = 5;

        /**
         * @if English
         * Font error.
         * @endif
         * @if Chinese
         * 字体设置错误。
         * @endif
         */
        int LVW_STATE_FONT_ERR = 6;

        /**
         * @if English
         * Watermark canceled.
         * @endif
         * @if Chinese
         * 取消水印。
         * @endif
         */
        int LVW__STATE_CANCEL = 20;
    }
}
