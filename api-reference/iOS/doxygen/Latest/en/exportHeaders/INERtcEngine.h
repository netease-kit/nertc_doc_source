/*
 * Copyright (c) 2021 NetEase, Inc. All rights reserved.
 */

#ifndef INERtcEngine_h
#define INERtcEngine_h

#import <Foundation/Foundation.h>
#import "NERtcEngineEnum.h"
#import "NERtcEngineErrorCode.h"

NS_ASSUME_NONNULL_BEGIN


/**
 * @if English 
 * Joins the room.
 * @param error A specific error. If the operation is successful, a value of nil is returned.
 * @param channelId If the operation is successful, a valid channelId is returned.
 * @param elapesd The total time spent joining the room in milliseconds.
 * @param uid The local uid of the client.
 * @endif
 * @if Chinese
 * 加入房间 block。
 * @param error 错误，如果成功则为 nil。
 * @param channelId 如果成功，会返回有效的 channelId。
 * @param elapesd 加入操作总耗时(毫秒)。
 * @param uid 用户 ID。如果在 joinChannel 方法中指定了 uid，此处会返回指定的 ID; 如果未指定 uid（joinChannel 时uid=0），此处将返回云信服务器自动分配的 ID。
 * @endif
 */ 
typedef void(^NERtcJoinChannelCompletion)(NSError * _Nullable error, uint64_t channelId, uint64_t elapesd, uint64_t uid);


/**
 * @if English   
 * Interactive live streaming.
 * @param taskId The ID of a streaming task.
 * @param errorCode The status code of the streaming task. For more information, see {@link NERtcEngineErrorCode.kNERtcLiveStreamError}.
 * @endif
 * @if Chinese
 * 互动直播推流 block。
 * @param taskId 推流任务 id。
 * @param errorCode 状态码。详细信息请参考 {@link NERtcEngineErrorCode.kNERtcLiveStreamError}
 * @endif
 */
typedef void(^NERtcLiveStreamCompletion)(NSString *taskId, kNERtcLiveStreamError errorCode);

/**
 * @if English 
 * Returns the screenshot.
 * @param errorCode The error code. For more information, see {@link NERtcEngineErrorCode.NERtcError}.
 * @param image The screenshot image.
 * @endif
 * @if Chinese
 * 截图结果 block 回调。
 * @param errorCode 错误码。详细信息请参考 {@link NERtcEngineErrorCode.NERtcError}。
 * @param image 截图图片。
 * @endif
 */
typedef void(^NERtcTakeSnapshotCallback)(int errorCode, UIImage * _Nullable image);

@class NERtcEngineContext;
@class NERtcCameraCaptureConfiguration;
@class NERtcVideoEncodeConfiguration;
@class NERtcVideoCanvas;

/**
 * @if English 
 * The common interface of NERtcEngine.
 * @endif
 * @if Chinese
 * NERtcEngine 的常用接口。
 * @endif
 */
@protocol INERtcEngine <NSObject>

/**
 * @if English 
 * Gets the connection status.
 * @return The current network status is returned.
 * @endif
 * @if Chinese
 * 获取当前房间连接状态。
 * @return 当前房间连接状态。
 * @endif
 */
- (NERtcConnectionStateType)connectionState;

/**
 * @if English 
 * Creates an NERtcEngine instance and initializes the NERTC SDK.
 * @note
 * - Before you call other APIs, you must first call this method to create and initialize an NERtc instance.
 * - Apps that use the same AppKey can make audio or video calls, or perform live events in the same room.
 * - One AppKey can be used to create only one NERtc instance. If you want to change the AppKey, you must first delete the current instance by calling the destroyEngine method, then, call this method to create a new instance.
 * - If you do not need the NERtc instance, you can delete the instance by calling the destroyEngine method.
 * @param context The RTC engine context object passed. For more information, see {@link NERtcEngineContext}.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 创建 NERtcEngine 并初始化 NERTC SDK 服务。
 * @note
 * - 请确保在调用其他 API 前先调用该方法创建并初始化 NERtc 实例。
 * - 使用同一个 App Key 的 App 才能进入同一个房间进行通话或直播。
 * - 一个App Key 只能用于创建一个NERtc 实例。如需更换 App Key，必须先调用 destroyEngine 方法销毁当前实例，再调用本方法重新创建实例。
 * - 若不再使用 NERtc  实例，需要调用 destroyEngine 进行销毁。
 * @param context 传入的RTC engine context对象。详细信息请参考 {@link NERtcEngineContext}。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)setupEngineWithContext:(NERtcEngineContext *)context;

/**
 * @if English 
 * Joins an RTC room.
 * <br>If the specified room does not exist when you join the room, a room with the specified name is automatically created in the server provided by CommsEase.
 * - After you join a room by calling the relevant method supported by the SDK, users in the same room can make audio or video calls. Users that join the same room can start a group chat. Apps that use different AppKeys cannot communicate with each other.
 * - If you join a room by calling this method, onNERtcEngineUserDidJoinWithUserID is triggered on a remote client.
 * - If you join a room, you subscribe to the audio streams from other users in the same room by default. In this case, the data usage is billed. To unsubscribe, you can call the mute method.
 * - In live streaming, the audience can switch rooms by calling switchChannelWithToken.
 * @param token The certification signature used in authentication (NERTC Token). Valid values:
                  - null. You can set the value to null in the debugging mode. This poses a security risk. We recommend that you contact your business manager to change to the default safe mode before your product is officially launched.
                  - NERTC Token acquired. In safe mode, the acquired token must be specified. If the specified token is invalid, users are unable to join a room. We recommend that you use the safe mode.
 * @param channelName The name of the room. Users that use the same name can join the same room. < br>The name must be of STRING type and must be 1 to 64 characters in length. The following 89 characters are supported: a-z, A-Z, 0-9, space, !#$%&()+-:;≤.,>? @[]^_{|}~”
 * @param uId The unique identifier of a user. The uId of each user in a room must unique.
            <br>uId is optional, The default value is 0. If the uId is not specified (set to 0), the SDK automatically assigns a random uId and returns the uId in NERtcJoinChannelCompletion. The application layer must keep and maintain the return value. The SDK does not maintain the return value.
 * @param completion The callback when the operation is complete.
 * @return The value returned. A value of 0 indicates that the operation is performed.
 * @endif
 * @if Chinese
 * 加入音视频房间。
 * <br>加入音视频房间时，如果指定房间尚未创建，云信服务器内部会自动创建一个同名房间。
 * - SDK 加入房间后，同一个房间内的用户可以互相通话，多个用户加入同一个房间，可以群聊。使用不同 App Key 的 App 之间不能互通。
 * - 成功调用该方加入房间后，远端会触发 onNERtcEngineUserDidJoinWithUserID 回调。
 * - 用户成功加入房间后，默认订阅房间内所有其他用户的音频流，可能会因此产生用量并影响计费。如果想取消订阅，可以通过调用相应的 mute 方法实现。
 * - 直播场景下的观众角色可以通过 switchChannelWithToken 快速切换房间。
 * @param token 安全认证签名（NERTC Token）。可设置为：
                  - null。调试模式下可设置为 null。安全性不高，建议在产品正式上线前在云信控制台中将鉴权方式恢复为默认的安全模式。
                  - 已获取的NERTC Token。安全模式下必须设置为获取到的 Token 。若未传入正确的 Token 将无法进入房间。推荐使用安全模式。
 * @param channelName 房间名称，设置相同房间名称的用户会进入同一个通话房间。<br>字符串格式，长度为1~ 64 字节。支持以下89个字符：a-z, A-Z, 0-9, space, !#$%&()+-:;≤.,>? @[]^_{|}~”
 * @param uId 用户的唯一标识 id，房间内每个用户的 uId 必须是唯一的。
            <br>uId 可选，默认为 0。如果不指定（即设为 0），SDK 会自动分配一个随机 uId，并在 NERtcJoinChannelCompletion 中返回，App 层必须记住该返回值并维护，SDK 不对该返回值进行维护。
 * @param completion 操作完成的 block 回调。
 * @return 操作返回值，被执行了则返回 0。
 * @endif
 */
- (int)joinChannelWithToken:(NSString *)token
                channelName:(NSString *)channelName
                      myUid:(uint64_t)uId
                 completion:(NERtcJoinChannelCompletion)completion;

/**
 * @if English 
 * Leaves a room, such as hanging up or ending a call.
 * <br>A user must call the leaveChannel method to end the call before the user makes another call.
 * <br>After you leave the room by calling the method, the onNERtcEngineDidLeaveChannelWithResult callback is triggered on the local client, and the onNERtcEngineUserDidLeaveWithUserID callback is triggered on a remote client.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 离开房间，即挂断或退出通话。
 * <br>结束通话时，必须调用leaveChannel结束通话，否则无法开始下一次通话。
 * <br>成功调用该方法离开房间后，本地会触发 onNERtcEngineDidLeaveChannelWithResult 回调，远端会触发 onNERtcEngineUserDidLeaveWithUserID 回调。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)leaveChannel;

/**
 * @if English 
 * Switches to a different RTC room.
 * <br>In live streaming, the audience can call this method to switch from the current room to another room.
 * <br>After you successfully switch to the destination room, the local client receives a message sent by onNERtcEngineDidLeaveChannelWithResult. The remote user receives messages sent by onNERtcEngineUserDidLeaveWithUserID and onNERtcEngineUserDidJoinWithUserID.
 * @note 
 * - By default, after a room member switches to another room, the room member subscribes to audio streams from other members of the new room. In this case, data usage is charged and billing is updated. If you want to unsubscribe from the previous audio stream, you can call the subscribeRemoteAudio method.
 * - The method applies to only live streaming. The role is the audience in the RTC room. The room scene is set to live streaming by calling the setchannelprofile method, and the role of room members is set to the audiences by calling the setClientRole method.
 * @param token The certification signature generated in the server and used for authentication. Valid values:
                  - null. You can set the value to null in the debugging mode. This poses a security risk. We recommend that you contact your business manager to change to the default safe mode before your product is officially launched.
                  - NERTC Token acquired. In safe mode, the acquired token must be specified. If the specified token is invalid, users are unable to join a channel. We recommend that you use the safe mode.
 * @param channelName The room name that a user wants to switch to.
 * @param completion The callback when the operation is complete.
 * @return The value returned. A value of 0 indicates that the operation is successful. A value that is less than 0 indicates that The operation fails.
 * @endif
 * @if Chinese
 * 快速切换音视频房间。
 * <br>房间场景为直播场景时，房间中角色为观众的成员可以调用该方法从当前房间快速切换至另一个房间。
 * <br>成功调用该方切换房间后，本端会收到离开房间的回调 onNERtcEngineDidLeaveChannelWithResult；远端用户会收到 onNERtcEngineUserDidLeaveWithUserID 和 onNERtcEngineUserDidJoinWithUserID 的回调。 
 * @note 
 * - 房间成员成功切换房间后，默认订阅房间内所有其他成员的音频流，因此产生用量并影响计费。如果想取消订阅，可以通过调用相应的 subscribeRemoteAudio 方法实现。
 * - 该方法仅适用于直播场景中，角色为观众的音视频房间成员。即已通过接口 setchannelprofile 设置房间场景为直播，通过 setClientRole 设置房间成员的角色为观众。
 * @param token 在服务器端生成的用于鉴权的安全认证签名（Token）。可设置为：
 *                  - null。调试模式下可设置为 null。安全性不高，建议在产品正式上线前在云信控制台中将鉴权方式恢复为默认的安全模式。
 *                  - 已获取的NERTC Token。安全模式下必须设置为获取到的 Token 。若未传入正确的 Token 将无法进入房间。推荐使用安全模式。
 * @param channelName 期望切换到的目标房间名称
 * @param completion 操作完成的 block 回调
 * @return 
 * - 0({@link NERtcEngineErrorCode.kNERtcNoError})：方法调用成功。
 * - 30001({@link NERtcEngineErrorCode.kNERtcErrFatal})：通用错误。
 * - 30003({@link NERtcEngineErrorCode.kNERtcErrInvalidParam})：参数错误。
 * - 30109({@link NERtcEngineErrorCode.kNERtcErrSwitchChannelInvalidState}): 切换房间状态无效。
 * - 403({@link NERtcEngineErrorCode.KNERtcErrChannelReservePermissionDenied}): 用户角色不是观众。
 * - 30100({@link NERtcEngineErrorCode.kNERtcErrChannelAlreadyJoined}): 房间名无效，已在此房间中。
 * @endif
 */
- (int)switchChannelWithToken:(NSString *)token channelName:(NSString *)channelName completion:(NERtcJoinChannelCompletion)completion;

/**
 * @if English 
 * Enables or disables local audio capture.
 * <br>When an app joins a room, the voice module is enabled by default.
 * <br>The method does not affect receiving or playing back the remote audio stream. The enableLocalAudio(NO) method is suitable for scenarios where a user wants to receive the remote audio stream without sending audio streams to other users in the room.
 * @note
 * - The enableLocalAudio method is different from muteLocalAudioStream. The enableLocalAudio method is used to enable local audio capture and processing whereas the muteLocalAudioStream method is used to stop or restart pushing the local audio stream.
 * - The method enables the internal engine. The setting remains unchanged after the leaveChannel method is called.
 * - Starting from V4.4.0, turning on or off local audio capture does not affect the playback of music files. You can still play music files by calling startAudioMixingWithOption after you set enableLocalAudio(NO).
 * @param enabled The option whether to enable local Audio capture.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 开启/关闭本地音频采集和发送。
 * <br>当 App 加入房间时，语音功能默认为开启状态。
 * <br>该方法不影响接收或播放远端音频流，enableLocalAudio(NO) 适用于只下行不上行音频流的场景。
 * <br>成功调用该方法后，房间内其他用户触发 onNERtcEngineUserAudioDidStart 或 onNERtcEngineUserAudioDidStop 回调。
 * @note
 * - 该方法与 muteLocalAudio 的区别在于，enableLocalAudio 用于开启本地语音采集及处理；muteLocalAudio 用于停止或继续发送本地音频流。
 * - 该方法设置内部引擎为启用状态，在 leaveChannel 后仍然有效。
 * - 从 V4.4.0 版本开始，开启或关闭本地音频采集的操作不再影响音乐文件播放，即 enableLocalAudio(NO) 后仍旧可以通过 startAudioMixingWithOption: 播放音乐文件。
 * @param enabled 是否开启本地音频采集。
 * @return
 * - 0: 方法调用成功；
 * - 其他: 方法调用失败。
 * @endif
 */
 - (int)enableLocalAudio:(BOOL)enabled;

/**
 * @if English 
 * Specifies whether to enable local video capture.
 * @note
 * - You can call this method before or after you join a room.
 * - After you enable or disable local video capture, the onNERtcEngineUserVideoDidStartWithUserID or onNERtcEngineUserVideoDidStop callback is triggered on a remote client.
 * @param enabled The option whether to enable local video capture.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 是否开启本地视频采集。
 * @note
 * - 该方法在加入房间前和加入房间后均可调用。
 * - 成功启用或禁用本地视频采集后，远端会触发 onNERtcEngineUserVideoDidStartWithUserID 或 onNERtcEngineUserVideoDidStop 回调。
 * @param enabled 是否开启本地视频采集。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
 - (int)enableLocalVideo:(BOOL)enabled;


/**
 * @if English
 * Publishes or unpublishes the local audio stream.
 * <br>When a user joins a room, the feature is enabled by default.
 * <br>The method does not affect receiving or playing the remote audio stream. The enableLocalAudio(false) method is suitable for scenarios where clients only receives remote media streams and does not publish any local streams.
 * @note 
 * - The method controls data transmitted over the main stream
 * - The method can be called before or after a user joins a room.
 * @since V4.6.10
 * @param enable specifies whether to publish the local audio stream.
 * - true(default): publishes the local audio stream.
 * - false: unpublishes the local audio stream.
 * @param mediaType  media type. Audio type is supported.
 * @return 
 * - 0: success
 * - Others: failure
 * @endif
 * if Chinese
 * 发布/停止本地音频。
 * <br>当用户加入房间时，此功能默认为开启状态。
 * <br>该方法不影响接收或播放远端音频流，enableLocalAudio(false) 适用于只下行不上行音频流的场景。
 * @note 
 * - 该方法只控制主流。
 * - 该方法在加入房间前后均可调用。
 * @since V4.6.10
 * @param enabled 是否发布本地语音。
 * - YES（默认）：发布本地语音，即音频上行。 
 * - NO：不发布本地语音，即停止本地音频上行。
 * @param mediaType 发布类型，暂时仅支持音频。
 * @return 
 * - 0：方法调用成功。
 * - 其他：方法调用失败。
 * @endif
 */
- (int)enableMediaPub:(BOOL)enabled withMediaType:(NERtcMediaPubType)mediaType;

/**
 * @if English 
 * Sets a room scene.
 * <br>You can set a room scene for a call or live event. Different QoS policies are applied to different scenes.
 * @note You must call the method before you join a room. If you have joined a room, you cannot set the room scene.
 * @param channelProfile The room scene. For more information, see {@link NERtcEngineEnum.NERtcChannelProfileType}.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 设置房间场景。
 * <br>房间场景可设置为通话或直播场景，不同的场景中 QoS 策略不同。
 * @note 该方法必须在加入房间前调用，进入房间后无法再设置房间场景。
 * @param channelProfile 设置房间场景。详细信息请参考 {@link NERtcEngineEnum.NERtcChannelProfileType}。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)setChannelProfile:(NERtcChannelProfileType)channelProfile;


/**
 * @if English 
 * Sets the video encoding profile.
 * @note <br>
 * - The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * - You can call this method before or after you join the room.
 * - After the setting is configured. The setting takes effect the next time local video is enabled.
 * - Each profile has a set of video parameters, such as resolution, frame rate, and bitrate. All the specified values of the parameters are the maximum values in optimal conditions. If the video engine cannot use the maximum value of resolution, frame rate, or bitrate due to unreliable network conditions, the value closest to the maximum value is used.
 * @param config The video encoding profile. For more information, see {@link NERtcVideoEncodeConfiguration}.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 设置视频编码属性。
 * - 可以在加入房间前或加入房间后调用此接口。
 * - `NERtcVideoConfig` 中的 `videoProfile` 可以指定预设的 Profile 模式，但是预设模式往往无法满足实际场景需求，网易云信建议您通过 `width` 和 `height` 进行自定义设置。详细信息请参考[设置视频属性](https://doc.yunxin.163.com/docs/jcyOTA0ODM/zcwMTY4MzM)。
 * @note <br>
 * - 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * - V4.5.0 开始，`setLocalVideoConfig` 方法实时生效；此前的版本中，`setLocalVideoConfig` 方法设置成功后，下次开启本端视频时生效。
 * - 每个属性对应一套视频参数，例如分辨率、帧率、码率等。所有设置的参数均为理想情况下的最大值。当视频引擎因网络环境等原因无法达到设置的分辨率、帧率或码率的最大值时，会取最接近最大值的那个值。
 * - setLocalVideoConfig 为全量参数配置接口，重复调用此接口时，SDK 会刷新此前的所有参数配置，以最新的传参为准。所以每次修改配置时都需要设置所有参数，未设置的参数将取默认值。
 * @param config 视频编码属性配置，详细信息请参考 {@link NERtcVideoEncodeConfiguration}。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)setLocalVideoConfig:(NERtcVideoEncodeConfiguration *)config;

/**
 * @if English 
 * Sets the audio encoding profile.
 * @note You must call this method before you call the joinChannel method. Otherwise, the settings of joinChannel do not take effect.
 * @param profile The sample rate, bitrate, encoding mode, and the number of channels. For more information, see {@link NERtcAudioProfileType}.
 * @param scenario The type of an audio scenario. For more information, see {@link NERtcAudioScenarioType}.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 设置音频编码属性。
 * @note 该方法支持在房间内动态调用。
 * @param profile 设置采样率、码率、编码模式和声道数。详细信息请参考 {@link NERtcAudioProfileType}。
 * @param scenario 设置音频应用场景。详细信息请参考 {@link NERtcAudioScenarioType}。
 * @return
 * - 0: 方法调用成功；
 * - 其他: 方法调用失败。
 * @endif
 */
- (int)setAudioProfile:(NERtcAudioProfileType)profile scenario:(NERtcAudioScenarioType)scenario;

/**
 * @if English 
 * Sets the local view.
 * <br>This method is used to set the display information about the local video. The method is applicable only to local users. Remote users are not affected. Apps can call this API operation to associate with the view that plays local video streams. During application development, in most cases, before joining a room, you must first call this method to set the local video view after the SDK is initialized.
 * @note The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * @param canvas The video canvas. For more information, see {@link NERtcVideoCanvas}. If you want to delete the canvas, you can set the value to nil.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 设置本地视图。
 * <br>该方法设置本地视频显示信息。只影响本地用户看到的视频画面，不影响远端。 App 通过调用此接口绑定本地视频流的显示视窗（view）。 在 App 开发中，通常在初始化后调用该方法进行本地视频设置，然后再加入房间。
 * @note 
 * - 该方法在加入频道前后都能调用。
 * - 如果您希望在通话中更新本地用户视图的渲染或镜像模式，请使用 {@link INERtcEngineEx#setLocalRenderScaleMode:} 方法。
 * @param canvas 视频画布。详细信息请参考 {@link NERtcVideoCanvas}。如果需要删除则传 nil。
 * @return
 * - 0: 方法调用成功；
 * - 其他: 方法调用失败。
 * @endif
 */
- (int)setupLocalVideoCanvas:(NERtcVideoCanvas * _Nullable)canvas;

/**
 * @if English 
 * Sets views for remote users.
 * <br>This method is used to associate remote users with display views and configure the rendering mode and mirror mode for remote views displayed locally. The method affects only the video screen viewed by local users.
 * @note 
 * - The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * - If the user ID is not retrieved, you can set the user ID after the app receives a message delivered when the onNERtcEngineUserDidJoinWithUserID event is triggered.
 * - To disassociate a specified user from a view, you can leave the canvas parameter empty.
 * - After a user leaves the room, the association between a remote user and the view is cleared.
 * @param userID The ID of a remote user.
 * @param canvas The video window. if you want to delete the canvas, you can set the value to nil.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 设置远端用户视图。
 * <br>该方法绑定远端用户和显示视图，并设置远端用户视图在本地显示时的渲染模式和镜像模式，只影响本地用户看到的视频画面。
 * @note 
 * - 如果 App 无法事先知道对方的用户 ID，可以在 APP 收到 onNERtcEngineUserDidJoinWithUserID 事件时设置。
 * - 解除某个用户的绑定视图可以把 canvas 设置为空。
 * - 退出房间后，SDK 会清除远程用户和视图的绑定关系。
 * - 如果您希望在通话中更新本地用户视图的渲染或镜像模式，请使用 {@link INERtcEngineEx#setRemoteRenderScaleMode:forUserID:} 方法。
 * @param userID 远端用户 ID。
 * @param canvas 视频窗口。详细信息请参考 {@link NERtcVideoCanvas}。如果需要删除则传 nil。
 * @return 
 * - 0: 方法调用成功；
 * - 其他: 方法调用失败。
 * @endif
 */
- (int)setupRemoteVideoCanvas:(NERtcVideoCanvas * _Nullable)canvas forUserID:(uint64_t)userID;

/**
 * @if English 
 * Switches between the front and rear cameras.
 * <br>Make sure that you call this method after the camera starts. For example, you can call this method after you call startPreview or joinChannel.
 * @note The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 切换前置/后置摄像头。
 * <br>该方法需要在相机启动后调用，例如调用 startPreview 或 joinChannel 后。
 * @note 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
 - (int)switchCamera;

/**
 * @if English 
 * Sets the role of a user in live streaming.
 * <br>The method sets the role to host or audience. The permissions of a host and an audience are different.
 * - A host has the permissions to open or close a camera, publish streams, call methods related to publishing streams in *Interactive live streaming. The status of the host is visible to the users in the room when the host joins or leaves the room.
 * - The audience has no permissions to open or close a camera, call methods related to publishing streams in interactive live streaming, and is invisible to other users in the room when the user that has the audience role joins or leaves the room.
 * @note <br>
 * - By default, a user joins a room as a host. Before a user joins a room, the user can call this method to change the client role to the audiences. After a user joins a room, the user can call this method to switch the client role.
 * - If the user switches the role to the audiences, the SDK automatically closes the audio and video devices.
 * @param role The role of a user. For more information, see {@link NERtcClientRole}.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 在直播场景中设置用户角色。
 * <br>用户角色支持设置为主播或观众，主播和观众的权限不同。
 * - 主播：可以开关摄像头等设备、可以发布流、可以操作互动直播推流相关接口、上下线对其他房间内用户可见。
 * - 观众：不可以开关摄像头等设备、不可以发布流、不可以操作互动直播推流相关接口、上下线对其他房间内用户不可见。
 
 * 如果你在加入频道后调用该方法切换角色，调用成功后会收到以下回调：  
 * - 主播切观众，本端触发 onNERtcEngineDidClientRoleChanged 回调，远端触发 onNERtcEngineUserDidLeaveWithUserID 回调。  
 * - 观众切主播，本端触发 onNERtcEngineDidClientRoleChanged 回调，远端触发 onNERtcEngineUserDidJoinWithUserID 回调。
 * <p>@note
 * - 默认情况下用户以主播角色加入房间。在加入房间前，用户可以调用本接口切换本端模式为观众。在加入房间后，用户也可以通过本接口切换用户模式。
 * - 用户切换为观众角色时，SDK 会自动关闭音视频设备。
 * @since V4.5.0
 * @param role 用户角色。详细信息请参考 {@link NERtcClientRole}。
 * @return
 * - 0(kNERtcNoError): 方法调用成功。  
 * - < 0: 方法调用失败。  
 *      - 30001(kNERtcErrFatal): Engine 未创建。  
 *      - 30101(kNERtcErrChannelNotJoined): 尚未加入房间。
 * @endif
 */
- (int)setClientRole:(NERtcClientRole)role;

/**
 * @if English 
 * Sets parameters for audio and video calls.
 * @note<br>
 * - You can call the method only after you join a room. When you use kNERtcKeyVideoPreferHWEncode and kNERtcKeyVideoPreferHWDecode, you must call this method before you initialize the SDK.
 * - This method provides a technology preview or personalized features. If you want to use this API, contact technical support.
 * @param parameters The parameters that you want to specify. For more information about the key parameters, see the definition in NERtcEngineBase.h.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 设置音视频通话的相关参数。
 * @note<br>
 * - 此接口可在加入房间前后调用。使用参数 kNERtcKeyVideoPreferHWEncode、kNERtcKeyVideoPreferHWDecode 时，请在初始化前调用此接口。
 * - 此方法提供技术预览或特别定制功能，若您需要使用此接口，请咨询技术支持获取帮助。
 * @param parameters 参数内容  参数 key，请参阅 NERtcEngineBase.h 中的定义
 * @return 操作返回值，成功则返回 0
 * @endif
 */

- (int)setParameters:(NSDictionary *)parameters;

@end


NS_ASSUME_NONNULL_END

#endif /* INERtcEngine_h */
