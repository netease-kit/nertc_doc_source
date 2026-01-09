/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk.channel;

import android.content.Intent;
import android.media.projection.MediaProjection;

import com.netease.lava.api.IVideoRender;
import com.netease.lava.api.model.RTCResult;
import com.netease.lava.nertc.sdk.LastmileProbeConfig;
import com.netease.lava.nertc.sdk.NERtcCallbackEx;
import com.netease.lava.nertc.sdk.NERtcConstants;
import com.netease.lava.nertc.sdk.NERtcEx;
import com.netease.lava.nertc.sdk.NERtcMediaRelayParam;
import com.netease.lava.nertc.sdk.live.AddLiveTaskCallback;
import com.netease.lava.nertc.sdk.live.DeleteLiveTaskCallback;
import com.netease.lava.nertc.sdk.live.NERtcLiveStreamTaskInfo;
import com.netease.lava.nertc.sdk.live.UpdateLiveTaskCallback;
import com.netease.lava.nertc.sdk.stats.NERtcStatsObserver;
import com.netease.lava.nertc.sdk.video.NERtcCameraCaptureConfig;
import com.netease.lava.nertc.sdk.video.NERtcRemoteVideoStreamType;
import com.netease.lava.nertc.sdk.video.NERtcScreenConfig;
import com.netease.lava.nertc.sdk.video.NERtcTakeSnapshotCallback;
import com.netease.lava.nertc.sdk.video.NERtcVideoConfig;
import com.netease.lava.nertc.sdk.video.NERtcVideoStreamType;
import com.netease.lava.nertc.sdk.video.NERtcVideoView;
import com.netease.lava.nertc.sdk.watermark.NERtcCanvasWatermarkConfig;
import com.netease.lava.nertc.sdk.watermark.NERtcVideoWatermarkConfig;

/**
 * @if English 
 * The NERtcChannel class provides methods that enable real-time communications in a specified channel. By creating multiple NERtcChannel instances, users can join multiple channels.
 * @since V4.5.0
 * @endif
 * @if Chinese
 * NERtcChannel 类在指定房间中实现实时音视频功能。通过创建多个 NERtcChannel 对象，用户可以同时加入多个房间。
 * @since V4.5.0
 * @endif
 */
public abstract class NERtcChannel {

    /**
     * @if English
     * Gets the current channel name.
     * @return The current channel name.
     * @endif
     * @if Chinese
     * 获取当前房间名称。
     * @return 当前房间名称。 失败返回null
     * @endif
     */
    public abstract String getChannelName();

    /**
     * @if English 
     * Sets the channel event callback.
     * <br>After setting the channel event callback, you can listen for channel events and receive the statistics of the corresponding RtcChannel instance.
     * @param channelCallback The event callback object. For more information, see {@link NERtcChannelCallback}.
     * @endif
     * @if Chinese
     * 设置NERtcChannel对象的事件回调。
    * <br>你可以通过设置的事件回调，监听当前设置NERtcChannel对象的事件回调对象对应房间的事件，并接收房间中用户视频信息等。<br/>
     * @param channelCallback 事件回调对象 {@link NERtcChannelCallback}
     * @endif
     */
    public abstract void setChannelCallback(NERtcChannelCallback channelCallback);

    /**
     * @if English 
     * Registers a stats observer.
     * @param statsObserver Stats observer. For more information, see {@link stats.NERtcStatsObserver}.
     * @endif
     * @if Chinese
     * 注册统计信息观测器，设置统计信息回调。
     * @param statsObserver 统计信息观测器。详细信息请参考 {@link stats.NERtcStatsObserver}。
     * @endif
     */
    public abstract void setStatsObserver(NERtcStatsObserver statsObserver);

    /**
     * @if English 
     * Joins an RTC room.
     * <br> If the specified room does not exist when you join the room, a room with the specified name is automatically created in the servers provided by CommsEase.
     * @note 
     * - After joining a room by calling methods supported by the SDK, users in the same room can make audio or video calls with each other. Multiple users that join the same room can start group chat. Apps that use different App Keys cannot communicate with each other.
     * - After you call the method, the onJoinChannel callback is triggered on the local client, and the onUserJoined callback is triggered on the remote client.
     * - If you join a room, the audio streams from other users in the same room are subscribed by default. In this case, the data usage is billed. To unsubscribe, you can call the mute method.
     * - In live streaming, audience can switch rooms by calling switchChannel.
     * @param token       The signature used in authentication (NERTC Token). Valid values:
     *                    - null. You can specify null in the debugging mode. This poses a security risk. We recommend that you change to the safe mode in the CommsEase console by using the authentication mode before your product is officially launched.
     *                    - The NERTC token that is obtained. In safe mode, the acquired token must be specified. If the specified token is invalid, users are unable to join a room. Safe mode is recommended.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 加入音视频房间。
     * <br>加入音视频房间时，如果指定房间尚未创建，云信服务器内部会自动创建一个同名房间。
     * @note
     * - SDK 加入房间后，同一个房间内的用户可以互相通话，多个用户加入同一个房间，可以群聊。使用不同 App Key 的 App 之间不能互通。
     * - 成功调用该方加入房间后，本地会触发 onJoinChannel 回调，远端会触发 onUserJoined 回调。
     * - 用户成功加入房间后，默认订阅房间内所有其他用户的音频流，可能会因此产生用量并影响计费。如果想取消订阅，可以通过调用相应的 mute 方法实现。
     * - 直播场景中，观众角色可以通过 switchChannel 切换房间。
     * @param token       安全认证签名（NERTC Token）。可设置为：
     * - null。调试模式下可设置为 null。安全性不高，建议在产品正式上线前转为安全模式。
     * - 已获取的NERTC Token。安全模式下必须设置为获取到的 Token 。若未传入正确的 Token 将无法进入房间。推荐使用安全模式。
     * - 字符串格式，长度为 1~64 字节。
     * - 支持以下89个字符：a-z, A-Z, 0-9, space, !#$%&()+-:;≤.,>? @[]^_{|}~”
     * @return {@code 0} 方法调用成功，其他失败
     * @see NERtcChannel#leaveChannel()
     * @endif
     */
    public abstract int joinChannel(String token);


    /**
     * @if English
     * Allows a user to leave a room, such as hanging up or exiting a call.
     * <br>After joining a room, the user must call the leaveChannel method to end the call before joining another room.
     * <br>After the method is called successfully, the onLeaveChannel callback is locally triggered, and the onUserLeave callback is remotely triggered.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @see NERtc#joinChannel(String, String, long)
     * @endif
     * @if Chinese
     * 离开房间，即挂断或退出通话。
     * <br>结束通话时，必须调用 leaveChannel 结束通话，否则无法开始下一次通话。
     * <br>成功调用该方法离开房间后，本地会触发 onLeaveChannel 回调，远端会触发 onUserLeave 回调。
     * @return {@code 0} 成功，其他失败.
     * @see NERtcChannel#joinChannel(String)
     * @endif
     */
    public abstract int leaveChannel();

    /**
     * @if English
     * Enables or disables local audio capture.
     * <br>When an app joins a room, the audio module is enabled by default.
     * <br>The method does not affect receiving or playing remote audio streams. The enableLocalAudio(false) method is suitable for scenarios where the user wants to receive remote audio streams without sending audio streams to other users in the room.
     * @note 
     * - The enableLocalAudio method is different from muteLocalAudioStream. The enableLocalAudio method is used to enable local audio capturing and processing whereas the muteLocalAudioStream method is used to stop or restart pushing local audio streams.
     * - The method enables the internal engine. The setting remains unchanged after the leaveChannel method is called.
     * - From V4.4.0, the operation to enable or disable the local audio capture does not affect playback of the music file. If you have called enableLocalAudio(NO), you can still play back the music file by calling startAudioMixing.
     * @param enable specifies whether to enable local audio.
     *               - true: enables local audio capture. This is the default value.
     *               - false: disables local audio capture.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 开启/关闭本地音频采集。
     * <br>当 App 加入房间时，语音功能默认为开启状态。
     * <br>该方法不影响接收或播放远端音频流，enableLocalAudio(false) 适用于只下行不上行音频流的场景。
     * <br>成功调用该方法后，房间内其他用户触发 onUserAudioStart 或 onUserAudioStop 回调。
     * @note
     * - 该方法与 muteLocalAudioStream 的区别在于，enableLocalAudio 用于开启本地语音采集及处理；muteLocalAudioStream 用于停止或继续发送本地音频流。
     * - 该方法设置内部引擎为启用状态，在 leaveChannel 后仍然有效。
     * @param enable 是否开启本地语音。
     * - true:（默认）重新开启本地语音，即开启本地语音采集。
     * - false: 关闭本地语音，即停止本地语音采集。
     * @return
     * - 0: 方法调用成功；
     * - 其他: 方法调用失败。
     * @endif
     */
    public abstract int enableLocalAudio(boolean enable);

    /**
     * @if English
     * Publishes or unpublishes the local audio stream.
     * <br>When a user joins a room, the feature is enabled by default.
     * <br>The method does not affect receiving or playback remote audio streams. The enableLocalAudio(false) method is suitable for scenarios where only downstream data is received without upstream data.
     * @note 
     * - The method controls only the main stream.
     * - The method can be called before or after a user joins a room.
     * @since V4.6.10
     * @param enable specifies whether to publish the local audio stream.
     * - YES(default): publishes the local audio stream. 
     * - NO: unpublishes the local audio stream.
     * @param mediaType media type. Only the audio is supported.
     * @return 
     * - 0: success.
     * - Others: failure
     * @endif
     * @if Chinese
     * 发布/停止本地音频。
     * <br>当用户加入房间时，此功能默认为开启状态。
     * <br>该方法不影响接收或播放远端音频流，enableLocalAudio(false) 适用于只下行不上行音频流的场景。
     * @note 
     * - 该方法只控制主流。
     * - 该方法在加入房间前后均可调用。
     * @since V4.6.10
     * @param enable 是否发布本地语音。
     * - true（默认）：发布本地语音，即音频上行。
     * - false：不发布本地语音，即停止本地音频上行。
     * @param mediaType  发布类型，暂时仅支持音频。
     * @return 
     * - 0：方法调用成功。
     * - 其他：方法调用失败。
     * @endif
     */
    public abstract int enableMediaPub(int mediaType, boolean enable);

    /**
     * @if English
     * Stops or resumes publishing the local audio stream.
     * <br>The method is used to stop or resume publishing the local audio stream.
     * @note 
     * - This method does not change the usage state of the audio-capturing device because the audio capture devices are not disabled.
     * - The muted state is reset to unmuted after the call ends.
     * @param mute specifies whether to enable sending the local audio stream.
     *             - true: mutes the local audio stream. This is the default value.
     *             - false: unmutes the local audio stream.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 开关本地音频发送。
     * <br>该方法用于允许或禁止上行本地音频流。
     * @note
     * - 该方法不影响音频采集状态，因为并没有禁用音频采集设备。
     * - 静音状态会在通话结束后被重置为非静音。
     * - 成功调用该方法后，房间内其他用户会收到 onUserAudioMute 回调。
     * @param mute 是否开启本地音频发送。
     * - true:（默认）静音本地音频。
     * - false: 取消静音本地音频。
     * @return
     * - 0: 方法调用成功；
     * - 其他: 方法调用失败。
     * @endif
     */
    public abstract int muteLocalAudioStream(boolean mute);

    /**
     * @if English
     * Subscribes or unsubscribes audio streams from specified remote users.
     * <br>After a user joins a room, audio streams from all remote users are subscribed by default. You can call this method to subscribe or unsubscribe audio streams from all remote users.
     * @param uid       indicates the user ID.
     * @param subscribe specifies whether to subscribe specified audio streams.
     *                  - true: subscribes audio steams. This is the default value.
     *                  - false: unsubscribes audio streams.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @note This method can be called only if a user joins a room.
     * @endif
     * @if Chinese
     * 取消或恢复订阅指定远端用户音频流。
     * <br>加入房间时，默认订阅所有远端用户的音频流，您可以通过此方法取消或恢复订阅指定远端用户的音频流。
     * @note 该方法需要在加入房间后调用。
     * @param uid 指定用户的 ID。
     * @param subscribe 是否订阅指定音频流。
     * - true:（默认）订阅音频流。
     * - false: 取消订阅音频流。
     * @return
     * - 0: 方法调用成功。
     * - 30005: 状态异常，可能是自动订阅打开，导致该接口无效。
     * - 其他: 方法调用失败。
     * @endif
     */
    public abstract int subscribeRemoteAudioStream(long uid, boolean subscribe);

    /**
     * @if English
     * Subscribes to or unsubscribes from audio streams from all remote users.
     * @note 
     * - After a user joins a room, audio streams from all remote users are subscribed by default. In this case, do not repeat subscribing audio streams from all remote users by calling the subscribeAllRemoteAudioStreams(true) method.
     * - This method can be called only if a user joins a room.
     * - This is applicable for subsequent users that join the room.
     * @param subscribe specifies whether to unsubscribe audio streams from all remote users.
     *                  - true: subscribes audio streams. This is the default value.
     *                  - false: unsubscribes audio streams.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 取消或恢复订阅所有远端用户音频流。
     * @note
     * - 加入房间时，默认订阅所有远端用户的音频，即 setParameters 接口的 KEY_AUTO_SUBSCRIBE_AUDIO 参数默认设置为 true，只有在该参数设置为 false 时，本接口的调用才生效。
     * - 该方法加入房间前后都可调用。设置 subscribeAllRemoteAudioStreams 的参数为 true 后，对后续加入的用户同样生效。
     * @param subscribe 是否取消订阅所有远端用户的音频流。
     * - true:（默认）订阅音频流。
     * - false: 取消订阅音频流。
     * @return {@code 0} 方法调用成功，其他失败
     * @endif
     */
    public abstract int subscribeAllRemoteAudioStreams(boolean subscribe);

    /**
     * @if English
     * Sets the camera capturer configuration.
     * <br>For a video call or live streaming, generally the SDK controls the camera output parameters. By default, the SDK matches the most appropriate resolution based on the user's setLocalVideoConfig configuration. When the default camera capture settings do not meet special requirements, we recommend using this method to set the camera capturer configuration:
     * - If you want better quality for the local video preview, we recommend setting config as CAPTURE_PREFERENCE_OUTPUT_QUALITY. The SDK sets the camera output parameters with higher picture quality.
     * - To customize the width and height of the video image captured by the local camera, set the camera capture configuration as CAPTURE_PREFERENCE_MANUAL.
     * <p>@note 
     * - Call this method before or after joining the channel. The setting takes effect immediately without restarting the camera.
     * - Higher collection parameters means higher performance consumption, such as CPU and memory usage, especially when video pre-processing is enabled. 
     * @param captureConfig The camera capturer configuration. For details, see {@link video.NERtcCameraCaptureConfig}。
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 设置本地摄像头的采集偏好等配置。
     * <br>在视频通话或直播中，SDK 自动控制摄像头的输出参数。默认情况下，SDK 根据用户的 setLocalVideoConfig 配置匹配最合适的分辨率进行采集。但是在部分业务场景中，如果采集画面质量无法满足实际需求，可以调用该接口调整摄像头的采集配置。
     * - 需要采集并预览高清画质时，可以将采集偏好设置为 CAPTURE_PREFERENCE_OUTPUT_QUALITY，此时 SDK 会自动设置较高的摄像头输出参数，本地采集与预览画面比编码参数更加清晰。
     * - 需要自定义设置摄像头采集的视频尺寸时，请通过参数 preference 将采集偏好设为 CAPTURE_PREFERENCE_MANUAL，并通过 NERtcCameraCaptureConfig 中的 captureWidth 和 captureHeight 自定义设置本地摄像头采集的视频宽高。
     * <p>@note 
     * - 该方法可以在加入房间前后动态调用，设置成功后，会自动重启采集模块。
     * - 设置更高的采集参数会导致更大的性能消耗，例如 CPU 和内存占用等，尤其是在开启视频前处理的场景下。
     * @param captureConfig 摄像头采集配置，详细信息请参考 {@link video.NERtcCameraCaptureConfig}。
     * @return {@code 0} 方法调用成功，其他调用失败
     * @endif
     */
    public abstract int setCameraCaptureConfig(NERtcCameraCaptureConfig captureConfig);

    /**
     * @if English 
     * Sets local video parameters.
     * @note 
     * - You can call this method before or after joining the room.
     * - Each profile has a set of video parameters, such as resolution, frame rate, and bitrate. All the specified values of the parameters are the maximum values in optimal conditions.
     * - This method is a full parameter configuration method. If this method is invoked repeatedly, the SDK refreshes all previous parameter configurations and uses the latest parameter.  Therefore, you need to set all parameters each time you modify the configuration, otherwise, unconfigured parameters will be set to the default value.
     * <br>If the video engine cannot use the maximum value of resolution, frame rate, or bitrate due to poor network performance, the value closest to the maximum value is taken.
     * @param videoConfig sets the video parameters. For more information, see {@link video.NERtcVideoConfig}.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 设置视频编码属性。
     * - 可以在加入房间前或加入房间后调用此接口。
     * - `NERtcVideoConfig` 中的 `videoProfile` 可以指定预设的 Profile 模式，但是预设模式往往无法满足实际场景需求，网易云信建议您通过 `width` 和 `height` 进行自定义设置。详细信息请参考[设置视频属性](https://doc.yunxin.163.com/docs/jcyOTA0ODM/jEzNTM2NDg)。
     * @note
     * - 该接口为全量参数配置接口，重复调用此接口时，SDK 会刷新此前的所有参数配置，以最新的传参为准。所以每次修改配置时都需要设置所有参数，未设置的参数将取默认值。
     * - 每个属性对应一套视频参数，例如分辨率、帧率、码率等。 所有设置的参数均为理想情况下的最大值。
     * <br>当视频引擎因网络环境等原因无法达到设置的分辨率、帧率或码率的最大值时，会取最接近最大值的那个值。
     * @param videoConfig 视频编码属性配置，详细信息请参考 {@link video.NERtcVideoConfig}。
     * @return {@code 0} 方法调用成功，其他调用失败
     * @endif
     */
    public abstract int setLocalVideoConfig(NERtcVideoConfig videoConfig);

    /**
     * @if English
     * Enables or disables dual streams.
     * <br>The method sets single-stream mode or dual-stream mode. If the dual-stream mode is enabled, the receiver can choose to receive the high-quality stream or low-quality stream video. The high-quality stream has a high resolution and high bitrate. The low-quality stream has a low resolution and low bitrate.
     * @note 
     * - The method applies to only camera data. Video streams from custom input and screen sharing are not affected.
     * - You can call this method before or after joining a room.
     * @param enable specifies whether to enable dual-stream mode.
     *               - true: enables the dual-stream mode. This is the default value.
     *               - false: disables the dual-stream mode.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 设置是否开启视频大小流模式。
     * <br>该方法设置单流或者双流模式。发送端开启双流模式后，接收端可以选择接收大流还是小流。其中，大流指高分辨率、高码率的视频流，小流指低分辨率、低码率的视频流。
     * @note
     * - 该方法只对摄像头数据生效，自定义输入、屏幕共享等视频流无效。
     * - 该方法在加入房间前后都能调用。
     * @param enable 指定是否开启双流模式。
     * - true：（默认）开启双流模式。
     * - false：关闭双流模式。
     * @return {@code 0} 方法调用成功，其他调用失败
     * @endif
     */
    public abstract int enableDualStreamMode(boolean enable);

    /**
     * @if English
     * Sets local views.
     * <br>This method is used to set the display information about the local video. The method is applicable only to local users. Remote users are not affected. Apps can call this API operation to associate with the view that plays local video streams.
     * <br>In the application development, you can call this method to set local views after an instance is initialized. Then, you can join a room.
     * @param render specifies the video canvas. For more information, see NERtcVideoView。
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @see NERtcVideoView
     * @endif
     * @if Chinese
     * 设置本地视图。
     * <br>该方法设置本地视频显示信息。只影响本地用户看到的视频画面，不影响远端。 App 通过调用此接口绑定本地视频流的显示视窗（view）。
     * <br>在 App 开发中，通常在初始化后调用该方法进行本地视频设置，然后再加入房间。
     * @note
     * - 该方法在加入频道前后均能调用。
     * - 如果您希望在通话中更新本地用户视图的渲染或镜像模式，请使用 {@link NERtcVideoView#setScalingType(int)} 方法。
     * @param render 视频画布。详细信息请参考 {@link video.NERtcVideoView}。
     * @return
     * - 0: 方法调用成功；
     * - 其他: 方法调用失败。
     * @endif
     */
    public abstract int setupLocalVideoCanvas(IVideoRender render);

    /**
     * @if English
     * Sets views for remote users.
     * <br>This method is used to associate remote users with display views and configure the rendering mode and mirroring mode for remote user views that displays locally. The method affects only the video displays viewed by local users.
     * @param render specifies the video canvas. For more information, see NERtcVideoView。
     * @param uid    indicates the ID of a remote user.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @note
     * - If the user ID is not retrieved, the App calls this method after the onUserJoined event is triggered.
     * - To disassociate a specified user from a view, you can leave the render parameter empty.
     * - After the user exit the room, the association between a remote user and the view is cleared.
     * @see NERtcVideoView
     * @endif
     * @if Chinese
     * 设置远端用户视图。
     * <br>该方法绑定远端用户和显示视图，并设置远端用户视图在本地显示时的渲染模式和镜像模式，只影响本地用户看到的视频画面。
     * <br><b>注意</b>：
     * - 如果 App 无法事先知道对方的用户 ID，可以在 APP 收到 onUserJoined 事件时设置。
     * - 解除某个用户的绑定视图可以把 render 设置为空。
     * - 退出房间后，SDK 会清除远程用户和视图的绑定关系。
     * - 如果您希望在通话中更新本地用户视图的渲染或镜像模式，请使用 {@link NERtcVideoView#setScalingType(int)} 方法。
     * @param render 视频画布。详细信息请参考 {@link video.NERtcVideoView}。
     * @param uid 远端用户 ID。
     * @return
     * - 0: 方法调用成功；
     * - 其他: 方法调用失败。
     * @endif
     */
    public abstract int setupRemoteVideoCanvas(IVideoRender render, long uid);

    /**
     * @if English
     * Specifies whether to enable local video capture.
     * @note 
     * - You can call this method before or after joining a room.
     * - After local video capture is successfully enabled or disabled,  the onUserVideoStop or onUserVideoStart callback is remotely triggered.
     * @param enable specifies whether to enable local video capture.
     *               - true: enables local video capture. This is the default value.
     *               - false: disables local cameras. After local video capture is disabled, remote users cannot receive video streams from local users. However, local users can still receive video streams from remote users.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 是否开启本地视频采集。
     * @note
     * - 该方法在加入房间前和加入房间后均可调用。
     * - 成功启用或禁用本地视频采集后，远端会触发 onUserVideoStop 或 onUserVideoStart  回调。
     * @param enable 是否启用本地视频采集：
     * - true：（默认）开启本地视频采集。
     * - false：关闭本地摄像头设备。关闭后，远端用户会接收不到本地用户的视频流；但本地用户依然可以接收远端用户的视频流。
     * @return {@code 0} 方法调用成功，其他调用失败
     * @endif
     */
    public abstract int enableLocalVideo(boolean enable);

    /**
     * @if English
     * Stops or resumes publishing the local video stream.
     * <br>If the method is called successfully, onUserVideoMute is triggered remotely.
     * @note 
     * - If you call this method to stop publishing the local video stream, the SDK no longer sends the local video stream.
     * - The method can be called before or after a user joins a room.
     * - If you stop publishing the local video stream by calling this method, the option is reset to the default state that allows the app to publish the local video stream.
     * - The method is different from enableLocalVideo(false).  The enableLocalVideo(false) method turns off local camera devices. The muteLocalVideoStreamvideo method does not affect local video capture, or disables cameras, and responds faster.
     * @param mute specifies whether to stop publishing the local video stream.
     *             - true: does not publish the local video stream.
     *             - false: publish the local video stream. This is the default value.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 取消或恢复发布本地视频流。
     * @note
     * - 调用该方法取消发布本地视频流后，SDK 不再发送本地视频流。
     * - 该方法在加入房间前后均可调用。
     * - 若调用该方法取消发布本地视频流，通话结束后会被重置为默认状态，即默认发布本地视频流。
     * - 该方法与 enableLocalVideo(false) 的区别在于，enableLocalVideo(false) 会关闭本地摄像头设备，muteLocalVideoStream 不影响本地视频流采集，不禁用摄像头，且响应速度更快。
     * - 成功调用该方法后，远端会触发 onUserVideoMute 回调。
     * @param mute 是否取消发布本地视频流。
     * - true：不发布本地视频流。
     * - false：（默认）发布本地视频流。
     * @return
     * - 0: 方法调用成功；
     * - 其他: 方法调用失败。
     * @endif
     */
    public abstract int muteLocalVideoStream(boolean mute);

    /**
     * @if English
     * Initializes a local substream canvas.
     * <br>This method sets the video view of the local video stream on the local device. The app associates with the video view of the local substream by calling this method. During application development, in most cases, before joining a room, you must first call this method to set the local video view after the SDK is initialized.
     * @note 
     * - If the app uses external rendering, we recommend that you set the video view before joining the room.
     * - Before joining a room, you must call the method after the SDK is initialized.
     * - A canvas is configured for only one user.
     * @param render indicates the settings of the video canvas. For more information, see {@link video.NERtcVideoView}.
     * @return {@code 0}    A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @see NERtcVideoView
     * @endif
     * @if Chinese
     * 设置本地辅流视频画布。
     * <br>该方法设置本地辅流视频显示信息。App 通过调用此接口绑定本地辅流的显示视窗（view）。 在 App 开发中，通常在初始化后调用该方法进行本地视频设置，然后再加入房间。
     * @note
     * - 若使用外部渲染，建议在加入房间之前设置。
     * - 请在初始化后调用该方法，然后再加入房间。
     * - 同一个画布只能设置给一个用户。
     * @param render 视频画布设置，详细信息请参考 {@link video.NERtcVideoView}。
     * @return {@code 0} 方法调用成功，其他调用失败
     * @see NERtcVideoView
     * @endif
     */
    public abstract int setupLocalSubStreamVideoCanvas(IVideoRender render);

    /**
     * @if English
     * Sets a remote substream canvas.
     * <br>The method associates a remote user with a substream view. You can assign a specified uid to use a corresponding canvas.
     * @note 
     * - If the app uses external rendering, we recommend that you set the canvas after receiving the return of onUserJoined.
     * - f the app does not retrieve the ID of a remote user, the method can be called after the remote user joins the room. You can retrieve the uid of the remote user from the return of onUserJoined. You can use this method to set the substream video canvas.
     * - If the remote user exits the room, the SDK disassociates the remote user from the canvas. The setting automatically becomes invalid.
     * @param render indicates the video canvas settings.
     *               - NERtcVideoView: uses SDK built-in canvas. You can also use the IVideoRender interface to use external rendering.
     *               - setScalingType indicates the video display mode.@ note You can use NERtcVideoView to configure the setting.
     * @param uid    indicates the ID of a remote user.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @see NERtcVideoView
     * @endif
     * @if Chinese
     * 设置远端的辅流视频画布。
     * <br>该方法绑定远端用户和辅流显示视图，即指定某个 uid 使用对应的画布显示。
     * @note
     * - 若使用外部渲染，建议在收到 onUserJoined 后设置。
     * - 如果 App 无法事先知道对方的用户 ID，可以在远端加入房间后调用。从 onUserJoined 中获取对方的 uid，并通过本方法为该用户设置辅流视频画布。
     * - 退出房间后，SDK 清除远端用户和画布的的绑定关系，该设置自动失效。
     * @param render 视频画布设置：
     * - NERtcVideoView：使用 SDK 内置画布，如需使用外部渲染器，可以通过 IVideoRender 接口实现。
     * - setScalingType：设置视频的显示模式。@note该方法通过 NERtcVideoView 进行设置。
     * @param uid    远端用户 ID。
     * @return {@code 0} 方法调用成功，其他调用失败
     * @see NERtcVideoView
     * @endif
     */
    public abstract int setupRemoteSubStreamVideoCanvas(IVideoRender render, long uid);

    /**
     * @if English
     * Enables screen sharing. The content of the screen sharing is sent as a secondary stream.
     * <br>If you join a room and call this method to enable the secondary stream, the onUserSubStreamVideoStart callback is remotely triggered.
     * @note 
     * - Starting NERTC SDK V3.9.0, the Profile parameter changes to {@link video.NERtcScreenConfig}.
     * - You can call the method only after joining a room.
     * @param screenConfig                        indicates the setting for local substream transmission. For more information, see {@link video.NERtcScreenConfig}.
     * @param mediaProjectionPermissionResultData indicates the Intent of the Android screen recording request. The parameter is returned after requesting the screen recording permissions.
     * @param mediaProjectionCallback             indicates the screen recording status callback that is used to listen on the completion notification for screen recording.
     * @endif
     * @if Chinese
     * 开启屏幕共享，屏幕共享内容以辅流形式发送。
     * <br>如果您在加入房间后调用该方法开启辅流，调用成功后，远端触发 onUserSubStreamVideoStart 回调。
     * @note
     * - NERTC SDK V3.9.0 开始，Profile 参数修改为 {@link video.NERtcScreenConfig}。
     * - 只能在加入房间后调用。
     * @param screenConfig                          本地辅流发送配置，详细信息请参考 {@link video.NERtcScreenConfig}。
     * @param mediaProjectionPermissionResultData   Android 的录屏请求 Intent，在请求录屏权限时返回。
     * @param mediaProjectionCallback               录屏状态回调，用于监听录屏的结束通知。
     * @endif
     */
    public abstract int startScreenCapture(NERtcScreenConfig screenConfig, Intent mediaProjectionPermissionResultData,
                                           MediaProjection.Callback mediaProjectionCallback);

    /**
     * @if English
     * Disables screen sharing with substream transmission.
     * <br>If you use the method to disable the substream after joining a room, the onUserSubStreamVideoStop callback is remotely triggered.
     * @endif
     * @if Chinese
     * 关闭辅流形式的屏幕共享。
     * <br>如果您在加入房间后调用该方法关闭辅流，调用成功后，远端触发 onUserSubStreamVideoStop 回调。
     * @endif
     */
    public abstract void stopScreenCapture();

    /**
     * @if English
     * Enables or disables audio sharing.
     * @since V4.6.0 
     * @param enable Specifies whether to enable audio sharing.
     * - true: enable audio sharing.
     * - false: disable audio sharing.
     * @param mediaProjectionResultIntent   Permissions Intent request in Android. The value is returned when users request for permissions.
     * @param callback   Callback for audio sharing status, used to listen for the notification triggered when an audio sharing task ends.
     * @return
     * - 0: success.
     * - Others: failure.
     * @endif
     * @if Chinese
     * 开启或关闭音频共享。
     * @since V4.6.0 
     * @param enable 是否开启音频共享。
     * - true：开启音频共享。
     * - false：关闭音频共享。
     * @param mediaProjectionResultIntent   Android 的系统权限请求 Intent，在请求权限时返回。
     * @param callback   音频共享状态回调，用于监听音频共享任务的结束通知。
     * @return
     * - 0：方法调用成功。
     * - 其他：方法调用失败。
     * @endif
     */
    public abstract int enableLoopbackRecording(boolean enable, Intent mediaProjectionResultIntent, MediaProjection.Callback callback);

    /**
     * @if English
     * Adjusts the volume of audio sharing.
     * @since V4.6.0
     * @param volume   audio capture volume. Value range: 0 ~ 100.
     * @return
     * - 0: success.
     * - Others: failure.
     * @endif
     * @if Chinese
     * 调整共享音频音量。
     * @since V4.6.0
     * @param volume   采集信号量。该参数的取值范围为 0 ~ 100。
     * @return
     * - 0：方法调用成功。
     * - 其他：方法调用失败。
     * @endif
     */
    public abstract int adjustLoopBackRecordingSignalVolume(int volume);

    /**
     * @if English
     * Subscribes or unsubscribes video streams from specified remote users.
     * <br>After a user joins a room, the video streams from remote users are not subscribed by default. If you want to view video streams from specified remote users, you can call this method to subscribe to the video streams from the user when the user joins the room or publishes the video streams.
     * <br>You must join a room before you can call the method.
     * @param uid        specifies the user ID.
     * @param streamType specified the type of the video streams. For more information, see {@link video.NERtcRemoteVideoStreamType}.
     * @param subscribe  specifies whether to unsubscribe local video streams.
     *                   - true: subscribe specified video streams. This is the default value.
     *                   - false: does not subscribe to the specified video streams.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 订阅或取消订阅指定远端用户的视频流。
     * @note
     * - 用户加入房间之后，默认不订阅远端用户的视频流。如果希望看到指定远端用户的视频，可以在监听到对方加入房间或发布视频流之后，通过此方法订阅该用户的视频流。
     * - 该方法需要在加入房间后调用。
     * @param uid           指定用户的用户 ID。
     * @param streamType    订阅的视频流类型，详细信息请参考 {@link video.NERtcRemoteVideoStreamType}。
     * @param subscribe     是否取消订阅本地视频流。
     * - true：（默认）订阅指定视频流。
     * - false：不订阅指定视频流。
     * @return 
     * - 0: 方法调用成功；
     * - 其他: 方法调用失败。
     * @endif
     */
    public abstract int subscribeRemoteVideoStream(long uid, NERtcRemoteVideoStreamType streamType, boolean subscribe);

    /**
     * @if English
     * Subscribes or unsubscribes remote secondary video stream from screen sharing. You can receive the secondary video stream data only after you subscribe to the secondary video stream.
     * @param uid       indicates the ID of a remote user.
     * @param subscribe specifies whether to subscribe to the remote secondary video stream from screen sharing.
     *                  - YES: subscribed.
     *                  - NO: unsubscribed.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @note You must join a room before you can call the method.
     * @endif
     * @if Chinese
     * 订阅或取消订阅远端的屏幕共享辅流视频，订阅之后才能接收远端的辅流视频数据。
     * @note该方法只能在加入房间后调用。
     * @param uid       远端用户 ID。
     * @param subscribe 是否订阅远端的屏幕共享辅流视频：
     * - YES：订阅。
     * - NO：取消订阅。
     * @return {@code 0} 方法调用成功，其他调用失败
     * @endif
     */
    public abstract int subscribeRemoteSubStreamVideo(long uid, boolean subscribe);

    /**
     * @if English
     * Sets the role of a user in live streaming.
     * <br>The method sets the role to host or audience. The permissions of a host and a viewer are different.
     * - A host has the permissions to open or close a camera, publish streams, call methods related to publishing streams in interactive live streaming, and is visible to the users in the room when the host joins or leaves the room.
     * - The audience has no permissions to open or close a camera, call methods related to publishing streams in interactive live streaming, and is invisible to other users in the room when the audience joins or leaves the room.
     * <p>@note 
     * - By default, a user can join a room as a host. Before a user joins a room, the user can call this method to change the client role to audience. After a user joins a room, the user can call this method to switch the client role.
     * - If the role switches to audience, the SDK automatically closes the audio and video devices.
     * @param role specifies the role of a user. For more information, see {@link com.netease.lava.nertc.sdk.NERtcConstants.UserRole}.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * 
     * @endif
     * @if Chinese
     * 在直播场景中设置用户角色。
     * <br>用户角色支持设置为主播或观众，主播和观众的权限不同。
     * - 主播：可以开关摄像头等设备、可以发布流、可以操作互动直播推流相关接口、上下线对其他房间内用户可见
     * - 观众：不可以开关摄像头等设备、不可以发布流、不可以操作互动直播推流相关接口、上下线对其他房间内用户不可见。
     
     * <br>如果你在加入频道后调用该方法切换角色，调用成功后会收到以下回调： 
     * - 主播切观众，本端触发 onClientRoleChange 回调，远端触发 onUserLeave 回调。
     * - 观众切主播，本端触发 onClientRoleChange 回调，远端触发 onUserJoined 回调。
     * @note
     * - 默认情况下用户以主播角色加入房间。在加入房间前，用户可以调用本接口切换本端模式为观众。在加入房间后，用户也可以通过本接口切换用户模式。
     * - 用户切换为观众角色时，SDK 会自动关闭音视频设备。
     * @param role 用户角色。详细信息请参考 {@link com.netease.lava.nertc.sdk.NERtcConstants.UserRole}。
     * @return
     * - 0:(kNERtcNoError): 方法调用成功。
     * - < 0: 方法调用失败。
     *      - 30001:(kNERtcErrFatal): Engine 未创建。
     *      - 30101:(kNERtcErrChannelNotJoined): 尚未加入房间。
     * @endif
     */
    public abstract int setClientRole(int role);

    /**
     * @if English
     * Gets the channel connection state.
     * @return The current channel status. For more information, see {@link NERtcConstants.ConnectionState}.
     * @endif
     * @if Chinese
     * 获取当前房间连接状态。
     * @return 当前房间连接状态。详细信息请参考 {@link NERtcConstants.ConnectionState}。
     * @endif
     */
    public abstract int getConnectionState();


    /**
     * @if English
     * Adds a push task in a room.
     * <br>After the method is successfully called, the current user can receive a notification about the status of the live stream.
     * @note 
     * - The method is applicable to only live streaming.
     * - You can call the method in a room. The method is valid in calls.
     * - Only one address for the relayed stream is added in each call. You need to call the method multiple times if you want to push many streams. An RTC room with the same channelid can create three different push tasks.
     * @param taskInfo            specifies the information about the push task. For more information, see {@link live.NERtcLiveStreamTaskInfo}.
     * @param addLiveTaskCallback indicates the task callback. The callback is triggered after the method is called. For more information, see {@link live.AddLiveTaskCallback}.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 添加房间推流任务。
     * <br>成功调用该方法后，当前用户可以收到该直播流的状态通知。
     * @note
     * - 该方法仅适用直播场景。
     * - 请在房间内调用该方法，该方法在通话中有效。
     * - 该方法每次只能增加一路旁路推流地址。如需推送多路流，则需多次调用该方法。同一个音视频房间（即同一个 channelid）可以创建 3 个不同的推流任务。
     * @param taskInfo            推流任务信息，详细信息请参考 {@link live.NERtcLiveStreamTaskInfo}。
     * @param addLiveTaskCallback 操作结果回调，方法调用成功后会触发对应回调。详细信息请参考 {@link live.AddLiveTaskCallback}。
     * @return {@code 0} 方法调用成功  ， 其他失败。
     * @endif
     */
    public abstract int addLiveStreamTask(NERtcLiveStreamTaskInfo taskInfo, AddLiveTaskCallback addLiveTaskCallback);


    /**
     * @if English
     * Updates a push task.
     * @note 
     * - The method is applicable to only live streaming.
     * - You can call the method in a room. The method is valid in calls.
     * @param taskInfo               specifies the information about the push task. For more information, see {@link live.NERtcLiveStreamTaskInfo}。
     * @param updateLiveTaskCallback indicates the task callback. The callback is triggered after the method is called. For more information, see {@link live.UpdateLiveTaskCallback}。
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 更新修改房间推流任务。
     * @note
     * - 该方法仅适用直播场景。
     * - 请在房间内调用该方法，该方法在通话中有效。
     * @param taskInfo               推流任务信息，详细信息请参考 {@link live.NERtcLiveStreamTaskInfo}。
     * @param updateLiveTaskCallback 操作结果回调，方法调用成功后会触发对应回调。详细信息请参考 {@link live.UpdateLiveTaskCallback}。
     * @return {@code 0} 方法调用成功 ，其他失败 。
     * @endif
     */
    public abstract int updateLiveStreamTask(NERtcLiveStreamTaskInfo taskInfo, UpdateLiveTaskCallback updateLiveTaskCallback);


    /**
     * @if English
     * Deletes a push task.
     * @note 
     * - The method is applicable to only live streaming.
     * - You can call the method in a room. The method is valid in calls.
     * @param taskId                 specified the ID of a push task.
     * @param deleteLiveTaskCallback indicates the task callback. The callback is triggered after the method is called. For more information, see {@link live.DeleteLiveTaskCallback}。
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 删除房间推流任务。
     * @note
     * - 该方法仅适用直播场景。
     * - 请在房间内调用该方法，该方法在通话中有效。
     * @param taskId                 推流任务 ID。
     * @param deleteLiveTaskCallback 操作结果回调，方法调用成功后会触发对应回调。详细信息请参考 {@link live.DeleteLiveTaskCallback}。
     * @return {@code 0} 方法调用成功 ，其他失败。
     * @endif
     */
    public abstract int removeLiveStreamTask(String taskId, DeleteLiveTaskCallback deleteLiveTaskCallback);

    /**
     * @if English
     * Sends supplemental enhancement information (SEI) data through a specified primary or substream channel.
     * <br>When the local video stream is pushed, SEI data is also sent to sync some additional information. After SEI data is sent, the receiver can retrieve the content by listening to the onRecvSEIMsg callback.
     * - Condition: After the video stream (the primary stream) is enabled, the function can be invoked.
     * - Data size limits: The SEI data can contain a maximum of 4,096 bytes in size. Sending an SEI message fails if the data exceeds the size limit. If a large amount of data is sent, the video bitrate rises. This degrades the video quality or causes broken video frames.
     * - Frequency limit: We recommend that the maximum video frame rate does not exceed 10 fps.
     * - Time to take effect: After the method is called, the SEI data is sent from the next frame in the fastest fashion or after the next 5 frames at the slowest pace.
     * <p>@note 
     * - The SEI data is transmitted together with the video stream. Frame loss may occur due to poor connection quality. The SEI data will also get lost. We recommend that you increase the times within the transmission frequency limits. This way, the receiver can get the data.
     * - Before you specify a channel to transmit the SEI data, you must first enable the data transmission channel.
     * @param seiMsg     indicates custom SEI data.
     * @param streamType indicates the type of channel with which the SEI data is transmitted. For more information, see {@link video.NERtcVideoStreamType}.
     * @return {@code 0}    A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @see NERtcCallbackEx#onRecvSEIMsg(long, String)
     * @endif
     * @if Chinese
     * 指定主流或辅流通道发送媒体增强补充信息（SEI）。
     * <br>在本端推流传输视频流数据同时，发送流媒体补充增强信息来同步一些其他附加信息。当推流方发送 SEI 后，拉流方可通过监听 onRecvSEIMsg 的回调获取 SEI 内容。
     * - 调用时机：视频流（主流）开启后，可调用此函数。
     * - 数据长度限制： SEI 最大数据长度为 4096 字节，超限会发送失败。如果频繁发送大量数据会导致视频码率增大，可能会导致视频画质下降甚至卡顿。
     * - 发送频率限制：最高为视频发送的帧率，建议不超过 10 次/秒。
     * - 生效时间：调用本接口之后，最快在下一帧视频数据帧之后发送 SEI 数据，最慢在接下来的 5 帧视频之后发送。
     * <p>@note
     * - SEI 数据跟随视频帧发送，由于在弱网环境下可能丢帧，SEI 数据也可能随之丢失，所以建议在发送频率限制之内多次发送，保证接收端收到的概率。
     * - 指定通道发送SEI之前，需要提前开启对应的数据流通道。
     * @param seiMsg     自定义 SEI 数据。
     * @param streamType 发送 SEI 时，使用的流通道类型。详细信息请参考 {@link video.NERtcVideoStreamType}。
     * @return {@code 0} 方法调用成功，其他失败
     * @see NERtcCallbackEx#onRecvSEIMsg(long, String)
     * @endif
     */
    public abstract int sendSEIMsg(String seiMsg, NERtcVideoStreamType streamType);


    /**
     * @if English
     * Sends supplemental enhancement information (SEI) messages through a mainstream channel.
     * <br>When the local video stream is pushed, SEI data is also sent to sync some additional information. After SEI data is sent, the receiver can retrieve the content by listening to the onRecvSEIMsg callback.
     * - Condition: After the video stream (the primary stream) is enabled, the function can be invoked.
     * - Data size limits: The SEI data can contain a maximum of 4,096 bytes in size. Sending an SEI message fails if the data exceeds the size limit. If a large amount of data is sent, the video bitrate rises. This degrades the video quality or causes broken video frames.
     * - Frequency limit: We recommend that the maximum video frame rate does not exceed 10 fps.
     * - Time to take effect: After the method is called, the SEI data is sent from the next frame in the fastest fashion or after the next 5 frames at the slowest pace.
     * <p>@note 
     * - The SEI data is transmitted together with the video stream. Frame loss may occur due to poor connection quality. The SEI data will also get lost. We recommend that you increase the times within the transmission frequency limits. This way, the receiver can get the data.
     * - By default, the SEI is transmitted by using the mainstream channel.
     * @param seiMsg indicates the custom SEI data.
     * @return {@code 0}    A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * 
     * @see NERtcCallbackEx#onRecvSEIMsg(long, String)
     * @see NERtcEx#sendSEIMsg(String, NERtcVideoStreamType)
     * @endif
     * @if Chinese
     * 通过主流通道发送媒体补充增强信息（SEI）。
     * <br>在本端推流传输视频流数据同时，发送流媒体补充增强信息来同步一些其他附加信息。当推流方发送 SEI 后，拉流方可通过监听 onRecvSEIMsg 的回调获取 SEI 内容。
     * - 调用时机：视频流（主流）开启后，可调用此函数。
     * - 数据长度限制： SEI 最大数据长度为 4096 字节，超限会发送失败。如果频繁发送大量数据会导致视频码率增大，可能会导致视频画质下降甚至卡顿。
     * - 发送频率限制：最高为视频发送的帧率，建议不超过 10 次/秒。
     * - 生效时间：调用本接口之后，最快在下一帧视频数据帧之后发送 SEI 数据，最慢在接下来的 5 帧视频之后发送。
     * <p>@note
     * - SEI 数据跟随视频帧发送，由于在弱网环境下可能丢帧，SEI 数据也可能随之丢失，所以建议在发送频率限制之内多次发送，保证接收端收到的概率。
     * - 调用本接口时，默认使用主流通道发送 SEI。
     * @param seiMsg 自定义 SEI 数据。
     * @return {@code 0} 方法调用成功，其他失败
     * @see NERtcCallbackEx#onRecvSEIMsg(long, String)
     * @see NERtcEx#sendSEIMsg(String, NERtcVideoStreamType)
     * @endif
     */
    public abstract int sendSEIMsg(String seiMsg);

    /**
     * @if English
     * Adds a watermark image to the local video. 
     * @note 
     * - The setLocalCanvasWatermarkConfigs method applies to the local video canvas and does not affect the video stream. If the canvas is removed, the watermark will be automatically deleted.
     * - Before you set a watermark, you must first set the canvas by calling related methods.
     * @param type   specifies the type of video streams. You can set the type to mainstream or secondary stream. For more information, see {@link video.NERtcVideoStreamType}}。
     * @param config specifies the configuration of the watermark for the canvas. You can set text watermark, image watermark, and timestamp watermark. A value of null indicates removing the watermark.
     *               <br>For more information, see {@link watermark.NERtcCanvasWatermarkConfig}.
     * @see NERtcEx#setLocalVideoWatermarkConfigs(NERtcVideoStreamType, NERtcVideoWatermarkConfig)
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 添加本地视频画布水印。
     * @note
     * - setLocalCanvasWatermarkConfigs 方法作用于本地视频画布，不影响视频流。画布被移除时，水印也会自动移除。
     * - 设置水印之前，需要先通过画布相关方法设置画布。
     * @param type 视频流类型。支持设置为主流或辅流。详细信息请参考 {@link video.NERtcVideoStreamType}}。
     * @param config 画布水印设置。支持设置文字水印、图片水印和时间戳水印，设置为 null 表示清除水印。
     *               <br>详细信息请参考 {@link watermark.NERtcCanvasWatermarkConfig}。
     *
     * @see NERtcEx#setLocalVideoWatermarkConfigs(NERtcVideoStreamType, NERtcVideoWatermarkConfig)
     * @return {@code 0} 方法调用成功，其他调用失败。
     * @endif
     */
    @Deprecated
    public abstract int setLocalCanvasWatermarkConfigs(NERtcVideoStreamType type, NERtcCanvasWatermarkConfig config);

    /**
     * @if English
     * Adds a watermark to the remote video canvas.
     * @note 
     * - The setRemoteCanvasWatermarkConfigs method Adds a watermark to the remote video canvas and does not affect the video stream. If the canvas is removed, the watermark will be automatically deleted.
     * - Before you set a watermark, you must first set the canvas by calling related methods.
     * @param uid    specifies the ID of a remote user.
     * @param type   specifies the type of video streams. You can set the type to mainstream or secondary stream. For more information, see {@link video.NERtcVideoStreamType}.
     * @param config specifies the configuration of the watermark for the canvas. You can set text watermark, image watermark, and timestamp watermark. A value of null indicates removing the watermark.
     *               <br>
     *               For more information, see {@link watermark.NERtcCanvasWatermarkConfig}.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * 
     * @endif
     * @if Chinese
     * 添加远端视频画布水印。
     * @note
     * - setRemoteCanvasWatermarkConfigs 方法作用于远端视频画布，不影响视频流。画布被移除时，水印也会自动移除。
     * - 设置水印之前，需要先通过画布相关方法设置画布。
     * @param uid 远端用户 ID。
     * @param type 视频流类型。支持设置为主流或辅流。详细信息请参考 {@link video.NERtcVideoStreamType}。
     * @param config 画布水印设置。支持设置文字水印、图片水印和时间戳水印，设置为 null 表示清除水印。
     *               <br>详细信息请参考 {@link watermark.NERtcCanvasWatermarkConfig}。
     * @see NERtcEx#setLocalVideoWatermarkConfigs(NERtcVideoStreamType, NERtcVideoWatermarkConfig)
     * @return {@code 0} 方法调用成功，其他调用失败。
     * @endif
     */
    @Deprecated
    public abstract int setRemoteCanvasWatermarkConfigs(long uid, NERtcVideoStreamType type, NERtcCanvasWatermarkConfig config);

    /**
     * @if English
     * Takes a local video snapshot.
     * <br>The takeLocalSnapshot method takes a local video snapshot on the local mainstream or local secondary stream. The onTakeSnapshotResult callback that belongs to the NERtcTakeSnapshotCallback class returns the data of the snapshot image.
     * @note 
     * - Before you call the method to capture the snapshot from the primary stream, you must first call startVideoPreview or enableLocalVideo, and joinChannel.
     * - Before you call the method to capture the snapshot from the secondary stream, you must first call joinChannel and startScreenCapture.
     * - If you want to set text, timestamp, and image on a watermark, different types of watermarks may overlap. The sequence for the layers of the watermark image to override previous layers follows image, text, and timestamp.
     * @param streamType specifies the video stream type of the snapshot. For more information, see {@link video.NERtcVideoStreamType}.
     * @param callback   specifies the snapshot callback. For more information, see {@link video.NERtcTakeSnapshotCallback}.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 本地视频画面截图。
     * 调用 takeLocalSnapshot 截取本地主流或本地辅流的视频画面，并通过 NERtcTakeSnapshotCallback 的 onTakeSnapshotResult 回调返回截图画面的数据。
     * @note
     * - 本地主流截图，需要在 startVideoPreview 或者 enableLocalVideo 并 joinChannel 成功之后调用。
     * - 本地辅流截图，需要在 joinChannel 并 startScreenCapture 之后调用。
     * - 同时设置文字、时间戳或图片水印时，如果不同类型的水印位置有重叠，会按照图片、文本、时间戳的顺序进行图层覆盖。
     * @param streamType 截图的视频流类型。详细信息请参考 {@link video.NERtcVideoStreamType}。
     * @param callback 截图回调。详细信息请参考 {@link video.NERtcTakeSnapshotCallback}。
     * @return {@code 0} 方法调用成功，其他调用失败。
     * @endif
     */
    public abstract int takeLocalSnapshot(NERtcVideoStreamType streamType, NERtcTakeSnapshotCallback callback);

    /**
     * @if English
     * Takes a snapshot of a remote video.
     * <br>The takeRemoteSnapshot method takes a snapshot from the remote video published through the mainstream or substream with a specified uid. The onTakeSnapshotResult callback that belongs to the NERtcTakeSnapshotCallback class returns the data of the snapshot image.
     * @note 
     * - Before you call takeRemoteSnapshot, you must first call onUserVideoStart and onUserSubStreamVideoStart.
     * - If you want to set text, timestamp, and image on a watermark, different types of watermarks may overlap. The sequence for the layers of the watermark image to override previous layers follows image, text, and timestamp.
     * @param uid        indicates the ID of a remote user.
     * @param streamType specifies the video stream type of the snapshot. You can set the type to mainstream or secondary stream. For more information, see {@link video.NERtcVideoStreamType}.
     * @param callback   specifies the snapshot callback. For more information, see {@link video.NERtcTakeSnapshotCallback}.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 远端视频画面截图。
     * 调用 takeRemoteSnapshot 截取指定 uid 远端主流和远端辅流的视频画面，并通过 NERtcTakeSnapshotCallback 的 onTakeSnapshotResult 回调返回截图画面的数据。
     * @note
     * - takeRemoteSnapshot 需要在收到 onUserVideoStart 与 onUserSubStreamVideoStart 回调之后调用。
     * - 同时设置文字、时间戳或图片水印时，如果不同类型的水印位置有重叠，会按照图片、文本、时间戳的顺序进行图层覆盖。
     * @param uid 远端用户 ID。
     * @param streamType 截图的视频流类型。支持设置为主流或辅流。详细信息请参考 {@link video.NERtcVideoStreamType}。
     * @param callback 截图回调。详细信息请参考 {@link video.NERtcTakeSnapshotCallback}。
     * @return {@code 0} 方法调用成功，其他调用失败。
     * @endif
     */
    public abstract int takeRemoteSnapshot(long uid, NERtcVideoStreamType streamType, NERtcTakeSnapshotCallback callback);

    /**
     * @if English
     * Sets the priority of media streams from a local user.
     * <br>If a user has a high priority, the media stream from the user also has a high priority. In unreliable network connections, the SDK guarantees the quality of the media stream from users with a high priority.
     * @note 
     * - You can call the method after joining a room. 
     * - An RTC room has only one user that has a high priority. We recommend that only one user in a room call the setLocalMediaPriority method to set the local media stream a high priority. Otherwise, you need to enable the preempt mode to ensure the high priority of the local media stream.
     * @param priority     specifies the priority of the local media stream. The default value is {@link NERtcConstants.MediaPriority#MEDIA_PRIORITY_NORMAL}. For more information, see {@link NERtcConstants.MediaPriority}.
     * @param isPreemptive specifies whether to enable the preempt mode.
     *                     - If the preempt mode is enabled, the local media stream preempts the high priority over other users. The priority of the media stream whose priority is taken becomes normal. After the user whose priority is taken leaves the room, other users still keep the normal priority.
     *                     - If the preempt mode is disabled, and a user in the room has a high priority, then, the high priority of the local client remains invalid and is still normal.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 设置本地用户的媒体流优先级。
     * 如果某个用户的优先级为高，那么该用户媒体流的优先级就会高于其他用户，弱网环境下 SDK 会优先保证高优先级用户收到的媒体流的质量。
     * @note
     * - 请在加入房间前调用此方法。- 一个音视频房间中只有一个高优先级的用户。建议房间中只有一位用户调用 setLocalMediaPriority 将本端媒体流设为高优先级，否则需要开启抢占模式，保证本地用户的高优先级设置生效。
     * @param priority     本地用户的媒体流优先级，默认为 {@link NERtcConstants.MediaPriority#MEDIA_PRIORITY_NORMAL}，详细信息请参考 {@link NERtcConstants.MediaPriority}。
     * @param isPreemptive 是否开启抢占模式。
                            - 抢占模式开启后，本地用户可以抢占其他用户的高优先级，被抢占的用户的媒体优先级变为普通优先级，在抢占者退出房间后，其他用户的优先级仍旧维持普通优先级。
                            - 抢占模式关闭时，如果房间中已有高优先级用户，则本地用户的高优先级设置不生效，仍旧为普通优先级。
     * @return {@code 0} 方法调用成功，其他调用失败。
     * @endif
     */
    public abstract int setLocalMediaPriority(int priority, boolean isPreemptive);

    /**
     *  @if English
     * Starts to relay media streams across rooms.
     * - The method can invite co-hosts across rooms. Media streams from up to four rooms can be relayed. A room can receive multiple relayed media streams.
     * - After you call this method, the SDK triggers onMediaRelayStatesChange and onMediaRelayReceiveEvent. The return reports the status and events about the current relayed media streams across rooms.
     * @note 
     * - You can call this method after joining a room. Before you call the method, you must set the destination room in the config parameter in setDestChannelInfo.
     * - The method is applicable only to the host in live streaming.
     * - If you want to call the method again, you must first call the stopChannelMediaRelay method to exit the current relaying status.
     * - If you succeed in relaying the media stream across rooms, and want to change the destination room, for example, add or remove the destination room, you can call updateChannelMediaRelay to update the information about the destination room.
     * @param config    specifies the configuration for the media stream relay across rooms. For more information, see {@link NERtcMediaRelayParam.ChannelMediaRelayConfiguration}。
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 开始跨房间媒体流转发。
     * - 该方法可用于实现跨房间连麦等场景。支持同时转发到 4 个房间，同一个房间可以有多个转发进来的媒体流。
     * - 成功调用该方法后，SDK 会触发 onMediaRelayStatesChange 和 onMediaRelayReceiveEvent 回调，并在回调中报告当前的跨房间媒体流转发状态和事件。
     * @note
     * - 请在成功加入房间后调用该方法。调用此方法前需要通过 config 中的 setDestChannelInfo 设置目标房间。
     * - 该方法仅对直播场景下的主播角色有效。
     * - 成功调用该方法后，若您想再次调用该方法，必须先调用 stopChannelMediaRelay 方法退出当前的转发状态。
     * - 成功开始跨房间转发媒体流后，如果您需要修改目标房间，例如添加或删减目标房间等，可以调用方法 updateChannelMediaRelay 更新目标房间信息。
     * @param config     跨房间媒体流转发参数配置信息。详细信息请参考 {@link NERtcMediaRelayParam.ChannelMediaRelayConfiguration}。
     * @return {@code 0} 方法调用成功，其他调用失败
     * @endif
     */
    public abstract int startChannelMediaRelay(NERtcMediaRelayParam.ChannelMediaRelayConfiguration config);

    /**
     *  @if English
     * Updates the information of the destination room for the media stream relay.
     * <br>You can call this method to relay the media stream to multiple rooms or exit the current room.
     * - You can call this method to change the destination room, for example, add or remove the destination room.
     * - After you call this method, the SDK triggers onMediaRelayStatesChange and onMediaRelayReceiveEvent. The return reports the status and events about the current relayed media streams across rooms.
     * @note 
     * - Before you call the method, you must join the room and call startChannelMediaRelay to relay the media stream across rooms. Before you call the method, you must set the destination room in the config parameter in setDestChannelInfo.
     * - You can relay the media stream up to four destination rooms. You can first call removeDestChannelInfo that belongs to the ChannelMediaRelayConfiguration class to remove the rooms that you have no interest in and add new destination rooms.
     * @param config    specifies the configuration for media stream relay across rooms. For more information, see {@link NERtcMediaRelayParam.ChannelMediaRelayConfiguration}。
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 更新媒体流转发的目标房间。
     * 成功开始跨房间转发媒体流后，如果你希望将流转发到多个目标房间，或退出当前的转发房间，可以调用该方法。
     * - 成功开始跨房间转发媒体流后，如果您需要修改目标房间，例如添加或删减目标房间等，可以调用此方法。
     * - 成功调用此方法后，SDK 会触发 onMediaRelayStatesChange 和 onMediaRelayReceiveEvent 回调，并在回调中报告当前的跨房间媒体流转发状态和事件。
     * @note
     * - 请在加入房间并成功调用 startChannelMediaRelay 开始跨房间媒体流转发后，调用此方法。调用此方法前需要通过 config 中的 setDestChannelInfo 设置目标房间。
     * - 跨房间媒体流转发最多支持 4 个目标房间，您可以在调用该方法之前，通过 ChannelMediaRelayConfiguration 中的 removeDestChannelInfo 方法移除不需要的房间，再添加新的目标房间。
     * @param config     跨房间媒体流转发参数配置信息。详细信息请参考 {@link NERtcMediaRelayParam.ChannelMediaRelayConfiguration}。
     * @return {@code 0} 方法调用成功，其他调用失败。
     * @endif
     */
    public abstract int updateChannelMediaRelay(NERtcMediaRelayParam.ChannelMediaRelayConfiguration config);

    /**
     *  @if English
     * Stops media stream relay across rooms.
     * <br>If the host leaves the room, media stream replay across rooms automatically stops. You can also call stopChannelMediaRelay. In this case, the host exits all destination rooms.
     * - If you call this method, the SDK triggers the onMediaRelayStatesChange callback. If MEDIARELAY_STATE_IDLE is returned, the media stream relay stops.
     * - If the method call failed, the SDK triggers the onMediaRelayStatesChange callback that returns the status code MEDIARELAY_STATE_FAILURE.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 停止跨房间媒体流转发。
     * 主播离开房间时，跨房间媒体流转发自动停止，您也可以在需要的时候随时调用 stopChannelMediaRelay 方法，此时主播会退出所有目标房间。
     * - 成功调用该方法后，SDK 会触发 onMediaRelayStatesChange 回调。如果报告 MEDIARELAY_STATE_IDLE，则表示已停止转发媒体流。
     * - 如果该方法调用不成功，SDK 会触发 onMediaRelayStatesChange 回调，并报告状态码 MEDIARELAY_STATE_FAILURE。
     * @return {@code 0} 方法调用成功，其他调用失败。
     * @endif
     */
    public abstract int stopChannelMediaRelay();

    /**
     * @if English
     * Adjust the volume of local signal playback from a specified remote user.
     * <br>After you join the room, you can call the method to adjust the volume of local audio playback from different remote users or repeatedly adjust the volume of audio playback from a specified remote user.@note 
     * - You can call this method after joining a room.
     * - The method is valid in the current call. If a remote user exits the room and rejoins the room again, the setting is still valid until the call ends.
     * - The method adjusts the volume of the mixing audio published by a specified remote user. The volume of one remote user can be adjusted. If you want to adjust multiple remote users, you need to call the method for the required times.
     * @param uid    indicates the ID of a remote user.
     * @param volume specifies the playback volume. Valid values: 0 to 100.
     *               - 0: mute
     *               - 100: the original volume
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 调节本地播放的指定远端用户的信号音量。
     * 加入房间后，您可以多次调用该方法设置本地播放的不同远端用户的音量；也可以反复调节本地播放的某个远端用户的音量。
     * @noteF
     * - 请在成功加入房间后调用该方法。
     * - 该方法在本次通话中有效。如果远端用户中途退出房间，则再次加入此房间时仍旧维持该设置，通话结束后设置失效。
     * - 该方法调节的是本地播放的指定远端用户混音后的音量，且每次只能调整一位远端用户。若需调整多位远端用户在本地播放的音量，则需多次调用该方法。
     * @param uid    远端用户 ID。
     * @param volume 播放音量，取值范围为 [0,100]。
     *                  - 0：静音。
     *                  - 100：原始音量。
     * @return {@code 0} 方法调用成功，其他调用失败。
     * @endif
     */
    public abstract int adjustUserPlaybackSignalVolume(long uid, int volume);


    /**
     * @if English
     * Sets the fallback option for the published local video stream based on the network conditions.
     * <br>The quality of the published local audio and video streams is degraded with poor quality network connections. After calling this method and setting the option to {@link NERtcConstants.StreamFallbackOption#AUDIO_ONLY}:
     * - With unreliable upstream network connections and the quality of audio and video streams is downgraded, the SDK switches to receive a low-quality video stream or stops receiving video streams. This way, the communication quality is maintained or improved.
     * - The SDK monitors the network performance and recover audio and video streams if the network quality improves.
     * - If the locally published audio and video stream falls back to an audio stream, or an audio and video stream, the SDK triggers the onLocalPublishFallbackToAudioOnly callback.
     * <p>@note You must call the method before you call joinChannel.
     * @param option specifies the fallback option for the locally published stream. By default, the fallback option is disabled. {@link NERtcConstants.StreamFallbackOption#DISABLED}。
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 设置弱网条件下发布的音视频流回退选项。
     * 在网络不理想的环境下，发布的音视频质量都会下降。使用该接口并将 option 设置为 {@link NERtcConstants.StreamFallbackOption#AUDIO_ONLY} 后：
     * - SDK 会在上行弱网且音视频质量严重受影响时，自动关断视频流，尽量保证音频质量。
     * - 同时 SDK 会持续监控网络质量，并在网络质量改善时恢复音视频流。
     * - 当本地发布的音视频流回退为音频流时，或由音频流恢复为音视频流时，SDK 会触发本地发布的媒体流已回退为音频流 onLocalPublishFallbackToAudioOnly 回调。
     * <p>@note 请在加入房间（joinChannel）前调用此方法。
     * @param option 本地发布流回退处理选项。 默认为不回退处理 {@link NERtcConstants.StreamFallbackOption#DISABLED}。
     * @return {@code 0} 方法调用成功，其他调用失败。
     * @endif
     */
    public abstract int setLocalPublishFallbackOption(int option);


    /**
     * @if English
     * Sets the fallback option for the subscribed remote audio and video stream with poor network connections.
     * <br>The quality of the subscribed audio and video streams is degraded with unreliable network connections. After calling this method and setting the option to {@link NERtcConstants.StreamFallbackOption#VIDEO_STREAM_LOW} or {@link NERtcConstants.StreamFallbackOption#AUDIO_ONLY}:
     * - With unreliable downstream network connections, the SDK switches to receive a low-quality video stream or stops receiving video streams. This way, the communication quality is maintained or improved.
     * - The SDK monitors the network quality and resumes the video stream when the network conditions improve.
     * - If the subscribed remote video stream falls back to audio-only, or the audio-only stream switches back to the video stream, the SDK triggers the onRemoteSubscribeFallbackToAudioOnly callback.
     * <p>@note Make sure that you call this method before you call joinChannel.
     * @param option is the fallback option for the subscribed remote audio and video stream. The default setting is {@link NERtcConstants.StreamFallbackOption#VIDEO_STREAM_LOW}. With unreliable network connections, the stream falls back to a low-quality video stream.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 设置弱网条件下订阅的音视频流回退选项。
     * 弱网环境下，订阅的音视频质量会下降。调用该接口并将 option 设置为 {@link NERtcConstants.StreamFallbackOption#VIDEO_STREAM_LOW} 或者 {@link NERtcConstants.StreamFallbackOption#AUDIO_ONLY} 后：
     *  - SDK 会在下行弱网且音视频质量严重受影响时，将视频流切换为小流，或关断视频流，从而保证或提高通信质量。
     *  - SDK 会持续监控网络质量，并在网络质量改善时自动恢复音视频流。
     *  - 当远端订阅流回退为音频流时，或由音频流恢复为音视频流时，SDK 会触发远端订阅流已回退为音频流 onRemoteSubscribeFallbackToAudioOnly 回调。
     * <p>@note 请在加入房间（joinChannel）前调用此方法。
     * @param option 订阅音视频流的回退选项，默认为 {@link NERtcConstants.StreamFallbackOption#VIDEO_STREAM_LOW} 弱网时回退到视频小流。
     * @return {@code 0} 方法调用成功，其他调用失败。
     * @endif
     */
    public abstract int setRemoteSubscribeFallbackOption(int option);

    /** 
     * @if English 
     * Starts the last-mile network probe test.
     * <br>This method starts the last-mile network probe test before joining a channel to get the uplink and downlink last mile network statistics, including the bandwidth, packet loss, jitter, and round-trip time (RTT).This method is used to detect network quality before a call. Before a user joins a room, you can use this method to estimate the subjective experience and objective network status of a local user during an audio and video call. 
     * Once this method is enabled, the SDK returns the following callbacks:
     * - onLastmileQuality: the SDK triggers this callback within five seconds depending on the network conditions. This callback rates the network conditions with a score and is more closely linked to the user experience.
     * - onLastmileProbeResult: the SDK triggers this callback within 30 seconds depending on the network conditions. This callback returns the real-time statistics of the network conditions and is more objective.
     * <p>@note 
     * - You can call this method before joining a channel(joinChannel).
     * - Do not call other methods before receiving the onLastmileQuality and onLastmileProbeResult callbacks. Otherwise, the callbacks may be interrupted.
     * @param config    Sets the configurations of the last-mile network probe test.
     * @endif
     * @if Chinese
     * 开始通话前网络质量探测。
     * <br>启用该方法后，SDK 会通过回调方式反馈上下行网络的质量状态与质量探测报告，包括带宽、丢包率、网络抖动和往返时延等数据。一般用于通话前的网络质量探测场景，用户加入房间之前可以通过该方法预估音视频通话中本地用户的主观体验和客观网络状态。
     * <br>相关回调如下：
     * - onLastmileQuality：网络质量状态回调，以打分形式描述上下行网络质量的主观体验。该回调视网络情况在约 5 秒内返回。
     * - onLastmileProbeResult：网络质量探测报告回调，报告中通过客观数据反馈上下行网络质量。该回调视网络情况在约 30 秒内返回。
     * <p>@note 
     * - 请在加入房间（joinChannel）前调用此方法。
     * - 调用该方法后，在收到 onLastmileQuality 和 onLastmileProbeResult 回调之前请不要调用其他方法，否则可能会由于 API 操作过于频繁导致此方法无法执行。
     * @param config    Last mile 网络探测配置。
     * @return
     * - 0: 方法调用成功
     * - 其他: 调用失败
     * @endif
     */
    public abstract int startLastmileProbeTest(LastmileProbeConfig config);

    /** 
     * @if English 
     * Stops the last-mile network probe test.
     * @return
     * - 0: Success.
     * - Other values: Failure.
     * @endif
     * @if Chinese
     * 停止通话前网络质量探测。
     * @return
     * - 0: 方法调用成功
     * - 其他: 调用失败
     * @endif
     */
    public abstract int stopLastmileProbeTest();

    /**
     * @if English
     * Sets a remote audio stream to high priority.
     * If a remote audio stream is set to high priority during automatic stream subscription, users can hear the audio stream with high priority.
     * @note
     * - You must set the API during calling with automatic subscription enabled (default)。
     * - The API can only set one audio stream to high priority. Subsequent settings will override the previous ones.
     * - If a call ends, the priority setting will be reset.
     * @param enabled enables or disables the high priority of a remote audio stream
     * - true：sets the high priority of a remote audio stream.
     * - false：Does not set the high priority of a remote audio stream.
     * @param uid     User uid
     * @return
     * - 0:success
     * - Others: failure
     * @endif
     * @if Chinese
     * 设置远端用户音频流的高优先级。
     * 支持在音频自动订阅的情况下，设置某一个远端用户的音频为最高优先级，可以优先听到该用户的音频。
     * @note
     * - 该接口需要通话中设置，并需要自动订阅打开（默认打开）。
     * - 该接口只能设置一个用户的优先级，后设置的会覆盖之前的设置。
     * - 该接口通话结束后，优先级设置重置。
     * @param enabled 是否设置音频订阅优先级。
     * - true：设置音频订阅优先级。
     * - false：取消设置音频订阅优先级。
     * @param uid     用户 uid
     * @return
     * - 0：方法调用成功。
     * - 其他：方法调用失败。
     * @endif
     */
    public abstract int setRemoteHighPriorityAudioStream(boolean enabled, long uid);

    /**
     * @if English
     * Sets the local audio stream can be subscribed by specified participants in a room.
     * <br>All participants in the room can subscribe to the local audio stream by default.
     * @note
     *  - The API must be called after a user joins a room.
     *  - The API cannot be called by user IDs out of the room.
     * @since V4.6.10
     * @param uidArray The list of user IDs that can subscribe to the local audio stream.
     * @note The list contains all participants in a room. If the value is empty or null, all participants can subscribe to the local audio stream.
     * @return
     * - 0: success
     * - Others: failure.
     * @endif
     * @if Chinese
     * 设置自己的音频只能被房间内指定的人订阅。
     * <br>默认房间所有其他人都可以订阅自己的音频。
     * @note
     *  - 此接口需要在加入房间成功后调用。
     *  - 对于调用接口时不在房间的 uid 不生效。
     * @since V4.6.10
     * @param uidArray 可订阅自己音频的用户uid 列表。
     * @note 此列表为全量列表。如果列表为空或 null，表示其他所有人均可订阅自己的音频。
     * @return
     * - 0：方法调用成功。
     * - 其他：方法调用失败。
     * @endif
     */
    public abstract int setAudioSubscribeOnlyBy(long[] uidArray);


    /**
     * @if English
     * Enables or disables the audio substream.
     * <br>If the audio substream is enabled, remote clients will get notified by {@link NERtcCallbackEx#onUserSubStreamAudioStart(long)}, and {@link NERtcCallbackEx#onUserSubStreamAudioStop(long)} when the audio stream is disabled.
     * @since V4.6.10
     * @param enable specifies whether to enable the audio substream.
     * - true: enables the audio substream.
     * - false: disable the audio substream.
     * @return
     * - 0: success
     * - Others: failure
     * @endif
     * @if Chinese
     * 开启或关闭音频辅流。
     * <br>开启时远端会收到 {@link NERtcCallbackEx#onUserSubStreamAudioStart(long)}，关闭时远端会收到{@link NERtcCallbackEx#onUserSubStreamAudioStop(long)}
     * @since V4.6.10
     * @param enable 是否开启音频辅流。
     * - true：开启音频辅流。
     * - false：关闭音频辅流。
     * @return 
     * - 0：方法调用成功。
     * - 其他：方法调用失败。
     * @endif
     */
    public abstract int enableLocalSubStreamAudio(boolean enable);


    /**
     * @if English
     * Mutes or unmutes the local upstream audio stream.
     * @note The muted state will be reset to unmuted after a call ends.
     * @since V4.6.10
     * @param mute specifies whether to mute a local audio stream.
     *              - true (default): mutes a local audio stream.
     *              - false: unmutes a local audio stream.
     * @return
     * - 0: success
     * - Others: failure
     * @endif
     * @if Chinese
     * 静音或解除静音本地上行的音频辅流。
     * @note 静音状态会在通话结束后被重置为非静音。
     * @since V4.6.10
     * @param mute 是否静音本地音频辅流发送。
     *              - true（默认）：静音本地音频辅流。
     *              - false：取消静音本地音频辅流。
     * @return
     * - 0：方法调用成功。
     * - 其他：方法调用失败。
     * @endif
     */
    public abstract int muteLocalSubStreamAudio(boolean mute);

    /**
     * @if English
     * Subscribes or unsubscribes audio streams from specified remote users.
     * <br>After a user joins a room, audio streams from all remote users are subscribed by default. You can call this method to subscribe or unsubscribe audio streams from all remote users.
     * @note This method can be called only if a user joins a room.
     * @since V4.6.10
     * @param uid       indicates the user ID.
     * @param subscribe specifies whether to subscribe specified audio streams.
     *                  - true: subscribes audio steams. This is the default value.
     *                  - false: unsubscribes audio streams.
     * @return
     * - 0: success
     * - Others: failure
     * @endif
     * @if Chinese
     * 取消或恢复订阅指定远端用户音频辅流。
     * <br>加入房间时，默认订阅所有远端用户的音频流，您可以通过此方法取消或恢复订阅指定远端用户的音频辅流。
     * @note 该方法需要在对应 uid 加入房间后调用。
     * @since V4.6.10
     * @param uid       指定用户的 ID。
     * @param subscribe 是否订阅指定音频流。
     *                  - true（默认）：订阅音频流。
     *                  - false：取消订阅音频流。
     * @return 
     * - 0：方法调用成功。
     * - 其他：方法调用失败。
     * @endif
     */
    public abstract int subscribeRemoteSubStreamAudio(long uid, boolean subscribe);


    /**
     * @if English
     * Deletes a NERtc instance to release resources.
     * @endif
     * @if Chinese
     * 销毁 NERtcChannel 实例，释放资源。
     * @endif
     */
    public abstract void release();
}
