/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk;

import android.content.Context;

import com.netease.lava.api.IVideoRender;
import com.netease.lava.nertc.BuildConfig;
import com.netease.lava.nertc.impl.Config;
import com.netease.lava.nertc.impl.NERtcImpl;
import com.netease.lava.nertc.sdk.NERtcConstants.AudioProfile;
import com.netease.lava.nertc.sdk.NERtcConstants.AudioScenario;
import com.netease.lava.nertc.sdk.video.NERtcRemoteVideoStreamType;
import com.netease.lava.nertc.sdk.video.NERtcCameraCaptureConfig;
import com.netease.lava.nertc.sdk.video.NERtcVideoConfig;
import com.netease.lava.nertc.sdk.video.NERtcVideoView;

import java.util.List;

/** NERtc*/
public abstract class NERtc {

    /**
     * @if English
     * Get a NERtc instance.
     * @return NERtc instance
     * @endif
     * @if Chinese
     * 获取 NERtc 实例。
     * @return NERtc 实例
     * @endif
     */
    public static NERtc getInstance() {
        return NERtcImpl.getInstance();
    }

    /**
     * @if English
     * Creates a NERtc instance and initializes the NERTC SDK.
     * - Before you call other APIs, you must first call this method to create and initialize a NERtc instance.
     * - Apps that use the same AppKey can make audio or video calls, or perform live streaming in the same room.
     * - One AppKey can be used to create only one NERtc instance. If you need to change the AppKey, you must first delete the current instance by calling the {@link NERtc#release()} method, then, call this method to create a new instance.
     * - If you do not need the NERtc instance, you can delete the instance by calling the {@link NERtc#release()} method.
     * - If you specify invalid parameters when you create an instance, an error may occur, such as RuntimeException and UnsatisfiedLinkError.
     * @param context  The RTC engine context object passed.
     * @param appkey   The AppKey of an app. You can view the AppKey in the <a href="https://app.netease.im">CommsEase console</a> after you create an app.
     * @param callback The callback function. All APIs are called in the main thread. A simple implement {@link AbsNERtcCallbackEx}
     * @param option   Other options. For more information, see {@link NERtcOption}. You can set the value to null.
     * @endif
     * @if Chinese
     * 创建 NERtc 实例并初始化 NERTC SDK 服务。
     * - 请确保在调用其他 API 前先调用该方法创建并初始化 NERtc 实例。
     * - 使用同一个 App Key 的 App 才能进入同一个房间进行通话或直播。
     * - 一个 App Key 只能用于创建一个 NERtc 实例。如需更换 App Key，必须先调用 {@link NERtc#release()} 方法销毁当前实例，再调用本方法重新创建实例。
     * - 若不再使用 NERtc 实例，需要 {@link NERtc#release()} 调用进行销毁。
     * - 创建实例时，如果参数设置错误，可能会出现异常 RuntimeException、UnsatisfiedLinkError。
     * @param context 传入的 RTC engine context 对象。
     * @param appkey 应用的 App Key。在 <a href="https://app.netease.im">云信控制台</a> 创建应用后，可以查看对应的 App Key。
     * @param callback 回调函数，所有接口均在主线程上回调， 可继承简易实现{@link AbsNERtcCallbackEx}， 或自行选择实现{@link NERtcCallback}、{@link NERtcCallbackEx}
     * @param option 其他可选配置，详细信息请参考 {@link NERtcOption} 。可设置为 null。
     * @endif
     */
    public abstract void init(Context context, String appkey, NERtcCallback callback, NERtcOption option) throws Exception;

    /**
     * @if English 
     * Deletes a NERtc instance to release resources.
     * <br>This method frees up all resources used by the NERTC SDK. In some cases, real-time communication is only needed upon your demands. If no audio or video calls are required, you can call this method to release resources.
     * <br>After you call the release method, other methods and callbacks supported by the SDK become unavailable. If you want to use RTC calls, you must create a new NERtc instance.
     * @note 
     * - After you delete an instance, you must wait until the release method is complete before you create a new instance.
     * - release 方法需要和 leaveChannel 方法在同一线程中，且需要在 leaveChannel 后执行。
     * @endif
     * @if Chinese
     * 销毁 NERtc 实例，释放资源。
     * <br>该方法释放 NERTC SDK 使用的所有资源。有些 App 只在用户需要时才进行实时音视频通信，完成音视频通话后，则将资源释放出来用于其他操作，该方法适用于此类情况。
     * - 该接口需要在调用 `leaveChannel`、并收到 `onUserLeave` 回调后调用。或收到 `onDisconnect` 回调、重连失败时调用此接口销毁实例，并释放资源。
     * - 调用 `release` 方法后，您将无法再使用 SDK 的其它方法和回调。如需再次使用实时音视频通话功能，您必须等待 `release` 方法执行结束后，重新创建一个新的 NERtc 实例。
     * @note 
     * - {@link NERtc#release()} 方法需要和 {@link NERtc#leaveChannel()} 方法在同一线程中，且需要在 leaveChannel 后执行。如果在不同线程中并发调用此接口可能会引起应用崩溃。
     * - 如果 `init` 初始化引擎失败，需要等待 `release` 同步返回后再次初始化引擎，否则重复初始化引擎，SDK 会抛出 RuntimeException。
     * @endif
     */
    public abstract void release();

    /**
     * @if English 
     * Joins an RTC room.
     * <br> If the specified room does not exist when you join the room, a room with the specified name is automatically created in the servers provided by CommsEase.
     * @param token       The signature used in authentication (NERTC Token). Valid values:
     *                    - null. You can specify null in the debugging mode. This poses a security risk. We recommend that you change to the safe mode in the CommsEase console by using the authentication mode before your product is officially launched.
     *                    - The NERTC token that is obtained. In safe mode, the acquired token must be specified. If the specified token is invalid, users are unable to join a room. Safe mode is recommended.
     * @param channelName The name of the room. Users that use the same name can join the same room.
     *                    - The name is in STRING format and must be 1 to 64 characters in length.
     *                    - The following 89 characters are supported: a-z, A-Z, 0-9, space, !#$%&()+-:;≤.,>? @[]^_{|}~”
     * @param uid         The unique identifier of a user. The uid of each user in a room must be unique.
     *                    <br> The uid is optional. The default value is 0. If the parameter is not specified or set to 0, the SDK assigns a random uid and returns by the onJoinChannel callback. The app instead of the SDk keeps the return value.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @note - After joining a room by calling methods supported by the SDK, users in the same room can make audio or video calls with each other. Multiple users that join the same room can start group chat. Apps that use different App Keys cannot communicate with each other.
     * - After you call the method, the onJoinChannel callback is triggered on the local client, and the onUserJoined callback is triggered on the remote client.
     * - If you join a room, the audio streams from other users in the same room are subscribed by default. In this case, the data usage is billed. To unsubscribe, you can call the mute method.
     * - In live streaming, audience can switch rooms by calling switchChannel.
     * 调用 joinChannel 之后，NERTC SDK 会通过 Android 的 [AudioManager.setMode()](https://developer.android.com/reference/android/media/AudioManager#setMode(int)) 方法调整音频模式（audio mode），此后请勿修改 SDK 调整过的音频模式，否则会导致音频路由错误等问题。     
     * @see NERtc#leaveChannel()
     * @endif
     * @if Chinese
     * 加入音视频房间。
     * <br>加入音视频房间时，如果指定房间尚未创建，云信服务器内部会自动创建一个同名房间。
     * <br>成功调用该方加入房间后，本地会触发 onJoinChannel 回调，远端会触发 onUserJoined 回调。
     * @note
     * - SDK 加入房间后，同一个房间内的用户可以互相通话，多个用户加入同一个房间，可以群聊。使用不同 App Key 的 App 之间不能互通。
     * - 直播场景中，观众角色可以通过 switchChannel 切换房间。
     * - 调用 joinChannel 之后，NERTC SDK 会通过 Android 的 [AudioManager.setMode()](https://developer.android.com/reference/android/media/AudioManager#setMode(int)) 方法调整音频模式（audio mode），此后请勿修改 SDK 调整过的音频模式，否则会导致音频路由错误等问题。
     * @param token       安全认证签名（NERTC Token）。可设置为：
     * - null。调试模式下可设置为 null。安全性不高，建议在产品正式上线前在云信控制台中将鉴权方式恢复为默认的安全模式。
     * - 已获取的NERTC Token。安全模式下必须设置为获取到的 Token 。若未传入正确的 Token 将无法进入房间。推荐使用安全模式。
     * @param channelName 房间名称，设置相同房间名称的用户会进入同一个通话房间。
     * - 字符串格式，长度为 1~64 字节。
     * - 支持以下89个字符：a-z, A-Z, 0-9, space, !#$%&()+-:;≤.,>? @[]^_{|}~”
     * @param uid         用户的唯一标识 id，房间内每个用户的 uid 必须是唯一的。
     *                    <br>uid 可选，默认为 0。如果不指定（即设为 0），SDK 会自动分配一个随机 uid，并在 onJoinChannel 回调方法中返回，App 层必须记住该返回值并维护，SDK 不对该返回值进行维护。
     * @return {@code 0} 方法调用成功，其他失败
     * @see NERtc#leaveChannel()
     * @endif
     */
    public abstract int joinChannel(String token, String channelName, long uid);


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
     * @see NERtc#joinChannel(String, String, long)
     * @endif
     */
    public abstract int leaveChannel();

    /**
     * @if English
     * Enables or disables local audio capture.
     * <br>When an app joins a room, the audio module is enabled by default.
     * <br>The method does not affect receiving or playing remote audio streams. The enableLocalAudio(false) method is suitable for scenarios where the user wants to receive remote audio streams without sending audio streams to other users in the room.
     * @param enable specifies whether to enable local audio.
     *               - true: enables local audio capture. This is the default value.
     *               - false: disables local audio capture.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @note - The enableLocalAudio method is different from muteLocalAudioStream. The enableLocalAudio method is used to enable local audio capturing and processing whereas the muteLocalAudioStream method is used to stop or restart pushing local audio streams.
     * - The method enables the internal engine. The setting remains unchanged after the leaveChannel method is called.
     * - From V4.4.0, the operation to enable or disable the local audio capture does not affect playback of the music file. If you have called enableLocalAudio(NO), you can still play back the music file by calling startAudioMixing.
     * @endif
     * @if Chinese 
     * 开启/关闭本地音频采集和发送。
     * <br>当 App 加入房间时，语音功能默认为开启状态。
     * <br>该方法不影响接收或播放远端音频流，enableLocalAudio(false) 适用于只下行不上行音频流的场景。
     * <br>成功调用该方法后，房间内其他用户触发 onUserAudioStart 或 onUserAudioStop 回调。
     * @note
     * - 该方法与 muteLocalAudioStream 的区别在于，enableLocalAudio 用于开启本地语音采集及处理；muteLocalAudioStream 用于停止或继续发送本地音频流。
     * - 该方法设置内部引擎为启用状态，在 leaveChannel 后仍然有效。
     * - 从 V4.4.0 版本开始，开启或关闭本地音频采集的操作不再影响音乐文件播放，即 enableLocalAudio(NO) 后仍旧可以通过 startAudioMixing 播放音乐文件。
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
     * <br><b>注意</b>：该方法需要在加入房间后调用。
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
     * @param subscribe specifies whether to unsubscribe audio streams from all remote users.
     *                  - true: subscribes audio streams. This is the default value.
     *                  - false: unsubscribes audio streams.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @note - After a user joins a room, audio streams from all remote users are subscribed by default. In this case, do not repeat subscribing audio streams from all remote users by calling the subscribeAllRemoteAudioStreams(true) method.
     * - This method can be called only if a user joins a room.
     * - This is applicable for subsequent users that join the room.
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
     * Sets the audio profile.
     * @param profile  sets the sample rate, bitrate, encoding mode, and the number of rooms. For more information, see {@link NERtcConstants.AudioProfile}.
     * @param scenario sets the audio application scenario. For more information, see {@link NERtcConstants.AudioScenario}。
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @note You must call this init method before you call the joinChannel method.
     * @endif
     * @if Chinese
     * 设置音频编码属性。
     * @note 该方法支持在房间内动态调用。
     * @param profile 设置采样率、码率、编码模式和声道数。详细信息请参考 {@link NERtcConstants.AudioProfile}。
     * @param scenario 设置音频应用场景。详细信息请参考 {@link NERtcConstants.AudioScenario}。
     * @return
     * - 0: 方法调用成功；
     * - 其他: 方法调用失败。
     * @endif
     */
    public abstract int setAudioProfile(int profile, int scenario);

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
     * Sets the camera capturer configuration.
     * <br>For a video call or live streaming, generally the SDK controls the camera output parameters. By default, the SDK matches the most appropriate resolution based on the user's setLocalVideoConfig configuration. When the default camera capture settings do not meet special requirements, we recommend using this method to set the camera capturer configuration:
     * - If you want better quality for the local video preview, we recommend setting config as CAPTURE_PREFERENCE_OUTPUT_QUALITY. The SDK sets the camera output parameters with higher picture quality.
     * - To customize the width and height of the video image captured by the local camera, set the camera capture configuration as CAPTURE_PREFERENCE_MANUAL.
     * @note 
     * - Call this method before or after joining the channel. The setting takes effect immediately without restarting the camera.
     * - Higher collection parameters means higher performance consumption, such as CPU and memory usage, especially when video pre-processing is enabled. 
     * @since V4.5.0
     * @param captureConfig The camera capturer configuration. For details, see {@link video.NERtcCameraCaptureConfig}。
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 设置本地摄像头的采集偏好等配置。
     * <br>在视频通话或直播中，SDK 自动控制摄像头的输出参数。默认情况下，SDK 根据用户的 setLocalVideoConfig 配置匹配最合适的分辨率进行采集。但是在部分业务场景中，如果采集画面质量无法满足实际需求，可以调用该接口调整摄像头的采集配置。
     * @note 
     * - 该方法可以在加入房间前后动态调用，设置成功后，会自动重启采集模块。
     * - 设置更高的采集参数会导致更大的性能消耗，例如 CPU 和内存占用等，尤其是在开启视频前处理的场景下。
     * @since V4.5.0
     * @param captureConfig 摄像头采集配置，详细信息请参考 {@link video.NERtcCameraCaptureConfig}。
     * @return
     * - 0：方法调用成功。
     * - 其他：方法调用失败。
     * @endif
     */
    public abstract int setCameraCaptureConfig(NERtcCameraCaptureConfig captureConfig);

    /**
     * @if English
     * Enables video preview.
     * - The method is used to enable local video preview before you enter a room. Before you can call the API, you must call setupLocalVideoCanvas to set up a video canvas.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 开启视频预览。
     * - 该方法用于在进入房间前启动本地视频预览。调用该 API 前，必须调用 setupLocalVideoCanvas 设置视频画布。
     * @return {@code 0} 方法调用成功，其他调用失败
     * @endif
     */
    public abstract int startVideoPreview();

    /**
     * @if English
     * Stops video preview.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 停止视频预览。
     * @return {@code 0} 方法调用成功，其他调用失败
     * @endif
     */
    public abstract int stopVideoPreview();

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
     * @param enable specifies whether to enable local video capture.
     *               - true: enables local video capture. This is the default value.
     *               - false: disables local cameras. After local video capture is disabled, remote users cannot receive video streams from local users. However, local users can still receive video streams from remote users.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @note 
     * - You can call this method before or after joining a room.
     * - After local video capture is successfully enabled or disabled,  the onUserVideoStop or onUserVideoStart callback is remotely triggered.
     * @endif
     * @if Chinese
     * 是否开启本地视频采集。
     * <br><b>注意</b>：
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
     * @param uid 指定用户的用户 ID。
     * @param streamType 订阅的视频流类型，详细信息请参考 {@link video.NERtcRemoteVideoStreamType}。
     * @param subscribe 是否取消订阅本地视频流。
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
     * Set parameters for audio and video calls.
     * @param params specifies parameters for audio and video calls.
     * @note
     * - You must call this method before calling the init method.
     * - This method provides a technology preview or personalized features. If you want to use this API, contact technical support.
     * @endif
     * @if Chinese
     * 设置音视频通话的相关参数。
     * <br><b>注意</b>：
     * - 请在调用 init 初始化之前调用此方法。
     * - 此方法提供技术预览或特别定制功能，若您需要使用此接口，请咨询技术支持获取帮助。
     * @param params 音视频通话的相关参数。
     * @endif
     */
    public abstract void setParameters(NERtcParameters params) throws IllegalArgumentException;

    /**
     * @if English
     * Query the SDK version number.
     * <br>You can call this method before or after the user join a room.
     * @return {@link NERtcVersion} The version number is returned.
     * @endif
     * @if Chinese
     * 查询 SDK 版本号。
     * <br>该方法在加入房间前后都能调用。
     * @return {@link NERtcVersion} 版本号
     * @endif
     */
    public static NERtcVersion version() {
        NERtcVersion ver = new NERtcVersion();
        ver.versionCode = BuildConfig.VERSION_CODE;
        ver.versionName = BuildConfig.VERSION_NAME;
        ver.buildRevision = BuildConfig.GIT_REVISION;
        ver.buildDate = BuildConfig.BUILD_TIME;
        ver.buildType = BuildConfig.BUILD_TYPE;
        ver.buildHost = BuildConfig.BUILD_HOST;
        ver.serverEnv = Config.getServerEnv();
        ver.buildBranch = BuildConfig.GIT_BRANCH;
        ver.engineRevision = BuildConfig.SUBMODULE_LAVA_REVISION;
        return ver;
    }

    /**
     * @if English
     * Checks permissions for media devices that are used to make audio and video calls.
     * @param context specifies the context parameter.
     * @return unauthorized permissions are returned.
     * @endif
     * @if Chinese
     * 音视频通话相关的多媒体设备权限检查。
     * @param context 上下文。
     * @return 返回缺失的权限。
     * @endif
     */
    public static List<String> checkPermission(Context context) {
        return NERtcImpl.checkPermission(context);
    }
}
