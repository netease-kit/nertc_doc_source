//
//  INERtcChannel.h
//  NERtcSDK
//
//  Created by yuan on 2021/6/6.
//  Copyright © 2021 Netease. All rights reserved.
//

#ifndef INERtcChannel_h
#define INERtcChannel_h

#import <Foundation/Foundation.h>
#import "NERtcChannelDelegate.h"
#import "NERtcEngineStatistics.h"
#import "INERtcEngine.h"

NS_ASSUME_NONNULL_BEGIN

/**
 * @if English
 * The `INERtcChannel` class provides methods that enable real-time communications in a specified channel. By creating multiple NERtcChannel instances, users can join multiple channels.
 * @since V4.5.0
 * @endif
 * @if Chinese
 * INERtcChannel 类在指定房间中实现实时音视频功能。通过创建多个 NERtcChannel 对象，用户可以同时加入多个房间。
 * @since V4.5.0
 * @endif
 */
@protocol INERtcChannel <NSObject>

/**
 * @if English 
 * Destroys an IRtcChannel instance to release resources.
 * @since V4.5.0
 * @endif
 * @if Chinese
 * @since V4.5.0
 * 销毁 IRtcChannel 实例，释放资源。
* @endif
*/
- (int)destroy;

/**
 * @if English 
 * Sets the channel delegate.
 * @since V4.5.0
 * @param channelDelegate The channel delegate.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 设置 channel 的回调。
 * @since V4.5.0
 * @param channelDelegate channel 的回调对象。
 * @return 操作返回值，成功则返回 0
* @endif
*/
- (int)setChannelDelegate:(id<NERtcChannelDelegate>)channelDelegate;

/**
 * @if English 
 * Gets the current channel name.
 * @since V4.5.0
 * @return
 * - Success: Return IRtcChannel channel name.
 * - Fail: Return nothing.
 * @endif
 * @if Chinese
 * 获取当前房间名。
 * @since V4.5.0
 * @return
 * - 成功：当前 IRtcChannel 房间名。
 * - 失败：返回空。
 * @endif
 */
- (NSString *)getChannelName;

/**
 * @if English
 * Gets the connection status.
 * @since V4.5.0
 * @return The current channel status is returned.
 * @endif
 * @if Chinese
 * 获取当前房间连接状态。
 * @since V4.5.0
 * @return 当前房间连接状态。
 * @endif
 */
- (NERtcConnectionStateType)connectionState;

/**
 * @if English
 * Joins an RTC room.
 * <br>If the specified room does not exist when you join the room, a room with the specified name is automatically created in the server provided by CommsEase.
 * - After you join a room by calling the relevant method supported by the SDK, users in the same room can make audio or video calls. Users that join the same room can start a group chat. Apps that use different AppKeys cannot communicate with each other.
 * - If you join a room by calling this method, onNERtcEngineUserDidJoinWithUserID is triggered on a remote client.
 * - If you join a room, you subscribe to the audio streams from other users in the same room by default. In this case, the data usage is billed. To unsubscribe, you can call the mute method.
 * <p>@since V4.5.0
 * @param token The certification signature used in authentication (NERTC Token). Valid values:
 *                  - null。 You can set the value to null in the debugging mode. This poses a security risk. We recommend that you contact your business manager to change to the default safe mode before your product is officially launched.
 *                  - NERTC Token acquired. In safe mode, the acquired token must be specified. If the specified token is invalid, users are unable to join a room. We recommend that you use the safe mode.
 * @param completion The callback when the operation is complete.
 * @return The value returned. A value of 0 indicates that the operation is performed.
 * @endif
 * @if Chinese
 * 加入音视频房间。
 * <br>加入音视频房间时，如果指定房间尚未创建，云信服务器内部会自动创建一个同名房间。
 * - SDK 加入房间后，同一个房间内的用户可以互相通话，多个用户加入同一个房间，可以群聊。使用不同 App Key 的 App 之间不能互通。
 * - 成功调用该方加入房间后，远端会触发 onNERtcEngineUserDidJoinWithUserID 回调。
 * - 用户成功加入房间后，默认订阅房间内所有其他用户的音频流，可能会因此产生用量并影响计费。如果想取消订阅，可以通过调用相应的 mute 方法实现。
 * <p>@since V4.5.0
 * @param token 安全认证签名（NERTC Token）。可设置为：
                  - null。非安全模式下可设置为 null。安全性不高，建议在产品正式上线前联系对应商务经理转为安全模式。
                  - 已获取的NERTC Token。安全模式下必须设置为获取到的 Token 。若未传入正确的 Token 将无法进入房间。推荐使用安全模式。
 * @param completion 操作完成的 block 回调。
 * @return 操作返回值，被执行了则返回 0。
 * @endif
 */
- (int)joinChannelWithToken:(NSString *)token
         completion:(NERtcJoinChannelCompletion)completion;

/**
 * @if English
 * Leaves a room, such as hanging up or ending a call.
 * A user must call the leaveChannel method to end the call before the user makes another call.
 * After you leave the room by calling the method, the onNERtcEngineDidLeaveChannelWithResult callback is triggered on the local client, and the onNERtcEngineUserDidLeaveWithUserID callback is triggered on a remote client.
 * @since V4.5.0
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 离开房间，即挂断或退出通话。
 * <br>结束通话时，必须调用leaveChannel结束通话，否则无法开始下一次通话。
 * <br>成功调用该方法离开房间后，本地会触发 onNERtcEngineDidLeaveChannelWithResult 回调，远端会触发 onNERtcEngineUserDidLeaveWithUserID 回调。
 * @since V4.5.0
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)leaveChannel;

/**
 * @if English
 * Enables or disables local audio capture.
 * <brWhen an app joins a room, the voice module is enabled by default.
 * <brThe method does not affect receiving or playing back the remote audio stream. The enableLocalAudio(NO) method is suitable for scenarios where a user wants to receive the remote audio stream without sending audio streams to other users in the room.
 * @note
 * - The enableLocalAudio method is different from muteLocalAudioStream. The enableLocalAudio method is used to enable local audio capture and processing whereas the muteLocalAudioStream method is used to stop or restart pushing the local audio stream.
 * - The method enables the internal engine. The setting remains unchanged after the leaveChannel method is called.
 * - Starting from V4.4.0, turning on or off local audio capture does not affect the playback of music files. You can still play music files by calling startAudioMixingWithOption after you set enableLocalAudio(NO).
 * @since V4.5.0
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
 * @since V4.5.0
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
 * @since V4.5.0
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 是否开启本地视频采集。
 * @note
 * - 该方法在加入房间前和加入房间后均可调用。
 * - 成功启用或禁用本地视频采集后，远端会触发 onNERtcEngineUserVideoDidStartWithUserID 或 onNERtcEngineUserVideoDidStop 回调。
 * @since V4.5.0
 * @param enabled 是否开启本地视频采集。
 * @return 操作返回值，成功则返回 0
* @endif
*/
- (int)enableLocalVideo:(BOOL)enabled;

/**
 * @if English
 * Publishes or unpublishes the local audio stream.
 * <br>When a user joins a room, the feature is enabled by default.
 * <br>The method does not affect receiving or playback remote audio streams. The enableLocalAudio(false) method is suitable for scenarios where only downstream data is received without upstream data.
 * @note 
 * - The method controls only the main stream.
 * - The method can be called before or after a user joins a room.
 * @since V4.6.10
 * @param enabled specifies whether to publish the local audio stream.
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
 * Enables or disables the dual-stream mode.
 * <br>The method sets the individual-stream mode or dual-stream mode. If the dual-stream mode is enabled on the publishing client, the receiver can choose to receive the high-quality stream or low-quality stream video. The high-quality stream has a high resolution and high bitrate. The low-quality stream has a low resolution and low bitrate.
 * @note
 * - The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * - The method applies to only camera data. Video streams from external input and screen sharing are not affected.
 * - You can call this method before or after you join a room.
 * @since V4.5.0
 * @param enable A value of YES indicates that the dual-stream mode is enabled. A value of NO indicates that the dual stream mode is disabled.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 设置是否开启视频大小流模式。
 * <br>该方法设置单流或者双流模式。发送端开启双流模式后，接收端可以选择接收大流还是小流。其中，大流指高分辨率、高码率的视频流，小流指低分辨率、低码率的视频流。
 * @note
 * - 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * - 该方法只对摄像头数据生效，自定义输入、屏幕共享等视频流无效。
 * - 该方法在加入房间前后都能调用。
 * @since V4.5.0
 * @param enable  YES 发送双流， NO 发送单流。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)enableDualStreamMode:(BOOL)enable;

/**
* @if English
* Sets the camera capturer configuration.
* <br>For a video call or live streaming, generally the SDK controls the camera output parameters. By default, the SDK matches the most appropriate resolution based on the user's setLocalVideoConfig configuration. When the default camera capture settings do not meet special requirements, we recommend using this method to set the camera capturer configuration:
* - If you want better quality for the local video preview, we recommend setting config as kNERtcCameraOutputQuality. The SDK sets the camera output parameters with higher picture quality.
* - To customize the width and height of the video image captured by the local camera, set the camera capture configuration as kNERtcCameraOutputManual.
* <p>@note 
* - Call this method before or after joining the channel. The setting takes effect immediately without restarting the camera.
* - Higher collection parameters means higher performance consumption, such as CPU and memory usage, especially when video pre-processing is enabled. 
* @since V4.5.0
* @param config The camera capturer configuration.
* @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
* @endif
* @if Chinese
* 设置本地摄像头的采集偏好等配置。
* <br>在视频通话或直播中，SDK 自动控制摄像头的输出参数。默认情况下，SDK 根据用户的 setLocalVideoConfig 配置匹配最合适的分辨率进行采集。但是在部分业务场景中，如果采集画面质量无法满足实际需求，可以调用该接口调整摄像头的采集配置。
* <p>@note 
* - 该方法可以在加入房间前后动态调用，设置成功后，会自动重启采集模块。
* - 设置更高的采集参数会导致更大的性能消耗，例如 CPU 和内存占用等，尤其是在开启视频前处理的场景下。
* @since V4.5.0
* @param config 摄像头采集配置。
* @return
* - 0：方法调用成功。
* - 其他：方法调用失败。
* @endif
*/
- (int)setCameraCaptureConfig:(NERtcCameraCaptureConfiguration *)config;

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
 * Subscribes to or unsubscribes from audio streams from specified remote users.
 * <br>After a user joins a room, audio streams from all remote users are subscribed by default. You can call this method to subscribe to or unsubscribe from audio streams from all remote users.
 * @note You can call this method before or after you join a room.
 * @since V4.5.0
 * @param subscribe The option whether to subscribe to specified audio streams.
 * @param userID The ID of a specified remote user.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 取消或恢复订阅指定远端用户音频流。
 * <br>加入房间时，默认订阅所有远端用户的音频流，您可以通过此方法取消或恢复订阅指定远端用户的音频流。
 * @note 该方法需要在加入房间后调用。
 * @since V4.5.0
 * @param subscribe 是否订阅指定音频流。
 * @param userID 指定远端用户的 ID。
 * @return
 * - 0: 方法调用成功。
 * - 30005: 状态异常，可能是自动订阅打开，导致该接口无效。
 * - 其他: 方法调用失败。
 * @endif
 */
- (int)subscribeRemoteAudio:(BOOL)subscribe forUserID:(uint64_t)userID;

/**
 * @if English
 * Subscribes to or unsubscribes from audio streams from all remote users.
 * @note
 * - After a user joins a room, audio streams from all remote users are subscribed by default. In this case, do not repeat subscribing to audio streams from all remote users by calling subscribeAllRemoteAudioStreams(YES).
 * - You must join a room before you can call the method.
 * - This setting applies to subsequent users that join the room.
 * @since V4.5.0
 * @param subscribe The option whether to unsubscribe from audio streams from all remote users.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 取消或恢复订阅所有远端用户音频流。
 * @note
 * - 加入房间时，默认订阅所有远端用户的音频，即 setParameters 接口的 KEY_AUTO_SUBSCRIBE_AUDIO 参数默认设置为 true，只有在该参数设置为 false 时，本接口的调用才生效。
 * - 该方法加入房间前后都可调用。设置 subscribeAllRemoteAudio 的参数为 YES 后，对后续加入的用户同样生效。
 * @param subscribe 是否取消订阅所有远端用户的音频流。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)subscribeAllRemoteAudio:(BOOL)subscribe;

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
- (int)setAudioSubscribeOnlyBy:(NSArray<NSNumber*> *)uidArray;

/**
 * @if English
 * Stops or resumes publishing the local audio stream.
 * <br>The method is used to stop or resume publishing the local audio stream.
 * @note 
 * - This method does not change the audio capture state because the audio capture devices are not disabled.
 * - The mute state is reset to unmuted after the call ends.
 * @since V4.5.0
 * @param muted The option whether to enable publishing the local audio stream.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 开关本地音频发送。
 * <br>该方法用于允许或禁止向网络发送本地音频流。
 * @note 
 * - 该方法不影响音频采集状态，因为并没有禁用音频采集设备。
 * - 静音状态会在通话结束后被重置为非静音。
 * - 成功调用该方法后，房间内其他用户会收到 onNERtcEngineUser:audioMuted 回调。
 * @since V4.5.0 
 * @param muted 是否开启本地音频发送。
 * @return
 * - 0: 方法调用成功；
 * - 其他: 方法调用失败。
 * @endif
 */
- (int)muteLocalAudio:(BOOL)muted;

/**
 * @if English
 * Sets the local view.
 * <br>This method is used to set the display information about the local video. The method is applicable only to local users. Remote users are not affected. Apps can call this API operation to associate with the view that plays local video streams. During application development, in most cases, before joining a room, you must first call this method to set the local video view after the SDK is initialized.
 * @note The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * @since V4.5.0
 * @param canvas The video canvas. For more information, see {@link NERtcVideoCanvas}. If you want to delete the canvas, you can set the value to nil.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 设置本地视图。
 * <br>该方法设置本地视频显示信息。只影响本地用户看到的视频画面，不影响远端。 App 通过调用此接口绑定本地视频流的显示视窗（view）。 在 App 开发中，通常在初始化后调用该方法进行本地视频设置，然后再加入房间。
 * @note
 * - 该方法在加入频道前后都能调用。
 * - 如果您希望在通话中更新本地用户视图的渲染或镜像模式，请使用 {@link INERtcEngineEx#setLocalRenderScaleMode:} 方法。
 * @since V4.5.0
 * @param canvas 视频画布。详细信息请参考 {@link NERtcVideoCanvas}。如果需要删除则传 nil。
 * @return
 * - 0: 方法调用成功；
 * - 其他: 方法调用失败。
 * @endif
 */
- (int)setupLocalVideoCanvas:(NERtcVideoCanvas * _Nullable)canvas;

/**
 * @if English
 * Sets the local video display mode.
 * @note The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * @since V4.5.0
 * @param mode The video display mode. For more information, see {@link NERtcVideoRenderScaleMode}.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 设置本端的视频显示模式。
 * @note 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * @since V4.5.0
 * @param mode 视频显示模式。详细信息请参考 {@link NERtcVideoRenderScaleMode}。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)setLocalRenderScaleMode:(NERtcVideoRenderScaleMode)mode;

/**
 * @if English
 * Sets views for remote users.
 * <br>This method is used to associate remote users with display views and configure the rendering mode and mirror mode for remote views displayed locally. The method affects only the video screen viewed by local users.
 * @note 
 * - The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * - If the user ID is not retrieved, you can set the user ID after the app receives a message delivered when the onNERtcEngineUserDidJoinWithUserID event is triggered.
 * - To disassociate a specified user from a view, you can leave the canvas parameter empty.
 * - After a user leaves the room, the association between a remote user and the view is cleared.
 * @since V4.5.0
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
 * @since V4.5.0
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
 * Sets the remote video display mode.
 * @note The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * @since V4.5.0
 * @param mode The video display mode. For more information, see {@link NERtcVideoRenderScaleMode}.
 * @param userID The ID of a remote user.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 设置远端的视频显示模式。
 * @note 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * @since V4.5.0
 * @param mode 视频显示模式。详细信息请参考 {@link NERtcVideoRenderScaleMode}。
 * @param userID 远端用户 ID。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)setRemoteRenderScaleMode:(NERtcVideoRenderScaleMode)mode forUserID:(uint64_t)userID;

/**
 * @if English
 * Subscribes to or unsubscribes from video streams from specified remote users.
 * <br>After a user joins a room, the video streams from remote users are not subscribed by default. If you want to view video streams from specified remote users, you can call this method to subscribe to the video streams from the user when the user joins the room or publishes the video streams.
 * @note
 * - The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * - You must join a room before you can call the method.
 * @since V4.5.0
 * @param subscribe The option whether to unsubscribe from local video streams.
 * @param userID The ID of a specified user.
 * @param streamType The type of the video streams. For more information, see {@link NERtcRemoteVideoStreamType}.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 订阅或取消订阅指定远端用户的视频流。
 * @note
 * - 该方法需要在加入房间后调用。
 * - 用户加入房间之后，默认不订阅远端用户的视频流。如果希望看到指定远端用户的视频，可以在监听到对方加入房间或发布视频流之后，通过此方法订阅该用户的视频流。
 * @since V4.5.0
 * @param subscribe 是否取消订阅本地视频流。
 * - YES：（默认）订阅指定视频流。  
 * - NO：不订阅指定视频流。
 * @param userID 指定用户的用户 ID。
 * @param streamType 订阅的视频流类型，详细信息请参考  {@link NERtcRemoteVideoStreamType}。
 * @return
 * - 0: 方法调用成功；
 * - 其他: 方法调用失败。
 * @endif
 */
- (int)subscribeRemoteVideo:(BOOL)subscribe forUserID:(uint64_t)userID streamType:(NERtcRemoteVideoStreamType)streamType;

/**
 * @if English
 * Stops or resumes publishing the local video stream.
 * <br>If you call the method successfully, the remote client triggers the onNERtcEngineUser:videoMuted: callback.
 * @note
 * - The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * - If you call this method to stop publishing the local video stream, the SDK no longer publishes the local video stream.
 * You can call the method before or after you join a room.
 * - If you stop publishing the local video stream by calling this method, the setting is reset to the default state that allows the app to publish the local video stream.
 * - The method is different from enableLocalVideo. The enableLocalVideo method turns off local camera devices. The muteLocalVideovideo method does not affect local video capture, or disables cameras, and responds faster.
 * @since V4.5.0
 * @param muted The option whether to stop publishing the local video stream.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 取消或恢复发布本地视频流。
 * @note
 * - 调用该方法取消发布本地视频流后，SDK 不再发送本地视频流。
 * - 该方法在加入房间前后均可调用。
 * - 若调用该方法取消发布本地视频流，通话结束后会被重置为默认状态，即默认发布本地视频流。
 * - 该方法与 enableLocalVideo 的区别在于，enableLocalVideo 会关闭本地摄像头设备，muteLocalVideo 不影响本地视频流采集，不禁用摄像头，且响应速度更快。
 * - 成功调用该方法后，远端会触发 onNERtcEngineUser:videoMuted: 回调。
 * @since V4.5.0
 * @param muted 是否取消发布本地视频流。
 * - true：不发布本地视频流。
 * - false：（默认）发布本地视频流。
 * @return
 * - 0: 方法调用成功；
 * - 其他: 方法调用失败。
 * @endif
 */
- (int)muteLocalVideo:(BOOL)muted;

/**
 * @if English
 * Sets the role of a user in live streaming.
 * <br>The method sets the role to host or audience. The permissions of a host and an audience are different.
 * - A host has the permissions to open or close a camera, publish streams, call methods related to publishing streams in interactive live streaming. The status of the host is visible to the users in the room when the host joins or leaves the room.
 * - The audience has no permissions to open or close a camera, call methods related to publishing streams in interactive live streaming, and is invisible to other users in the room when the user that has the audience role joins or leaves the room.
 * <p>@note
 * - By default, a user joins a room as a host. Before a user joins a room, the user can call this method to change the client role to the audiences. After a user joins a room, the user can call this method to switch the client role.
 * - If the user switches the role to the audiences, the SDK automatically closes the audio and video devices.
 * @since V4.5.0
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
 * Adjust the volume of local signal playback from a specified remote user.
 * <br>After you join the room, you can call the method to set the volume of local audio playback from different remote users or repeatedly adjust the volume of audio playback from a specified remote user.
 * @note
 * - You can call this method after you join a room.
 * - The method is valid in the current call. If a remote user exits the room and rejoins the room again, the setting is still valid until the call ends.
 * - The method adjusts the volume of the mixing audio published by a specified remote user. Only one remote user can be adjusted. If you want to adjust multiple remote users, you need to call the method for the required times.
 * @since V4.5.0
 * @param userID The ID of a remote user.
 * @param volume The playback volume. Valid values: 0 to 100.
 *                 - 0: muted
 *                 - 100: the original volume
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 调节本地播放的指定远端用户的信号音量。
 * <br>加入房间后，您可以多次调用该方法设置本地播放的不同远端用户的音量；也可以反复调节本地播放的某个远端用户的音量。
 * @note
 * - 请在成功加入房间后调用该方法。
 * - 该方法在本次通话中有效。如果远端用户中途退出房间，则再次加入此房间时仍旧维持该设置，通话结束后设置失效。
 * - 该方法调节的是本地播放的指定远端用户混音后的音量，且每次只能调整一位远端用户。若需调整多位远端用户在本地播放的音量，则需多次调用该方法。
 * @since V4.5.0
 * @param userID    远端用户 ID。
 * @param volume 播放音量，取值范围为 [0,100]。
 *                 - 0：静音。
 *                 - 100：原始音量。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)adjustUserPlaybackSignalVolume:(uint32_t)volume forUserID:(uint64_t)userID;

#pragma mark -- Audio Sub Stream

/**
 * @if English
 * Enables or disables the audio substream.
 * <br>If the audio substream is enabled, remote clients will get notified by {@link NERtcChannelDelegate#onNERtcChannelUserSubStreamAudioDidStart:}, and {@link NERtcChannelDelegate#onNERtcChannelUserSubStreamAudioDidStop:} when the audio stream is disabled.
 * @since V4.6.10
 * @param enabled specifies whether to enable the audio substream.
 * - true: enables the audio substream.
 * - false: disable the audio substream.
 * @return
 * - 0: success
 * - Others: failure
 * @endif
 * @if Chinese
 * 开启或关闭音频辅流。
 * <br>开启时远端会收到 {@link NERtcChannelDelegate#onNERtcChannelUserSubStreamAudioDidStart:} 回调 ，关闭时远端会收到 {@link NERtcChannelDelegate#onNERtcChannelUserSubStreamAudioDidStop:} 回调。
 * @since V4.6.10
 * @param enabled 是否开启音频辅流。 
 * - YES：开启音频辅流。
 * - NO：关闭音频辅流。
 * @return
 * - 0 ：方法调用成功。
 * - 其他：方法调用失败。
 * @endif
 */
- (int)enableLocalSubStreamAudio:(BOOL)enabled;

/**
 * @if English
 * Subscribes or unsubscribes audio streams from specified remote users.
 * <br>After a user joins a room, audio streams from all remote users are subscribed by default. You can call this method to subscribe or unsubscribe audio streams from all remote users.
 * @note This method can be called only if a user joins a room.
 * @since V4.6.10
 * @param userID       indicates the user ID.
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
 * @param userID     指定用户的 userID。
 * @param subscribe  是否订阅指定音频流。
 *                  - YES（默认）：订阅音频流。
 *                  - NO：取消订阅音频流。
 * @return 
 * - 0：方法调用成功。
 * - 其他：方法调用失败。
 * @endif
 */
- (int)subscribeRemoteSubStreamAudio:(BOOL)subscribe forUserID:(uint64_t)userID;

/**
 * @if English
 * Mutes or unmutes the local upstream audio stream.
 * @note The muted state will be reset to unmuted after a call ends.
 * @since V4.6.10
 * @param muted specifies whether to mute a local audio stream.
 *              - true (default): mutes a local audio stream.
 *              - false: unmutes a local audio stream.
 * @return
 * - 0: success
 * - Others: failure
 * @endif
 * @if Chinese
 * 静音或解除静音本地上行的音频辅流。
 * @note  静音状态会在通话结束后被重置为非静音。
 * @since V4.6.10
 * @param muted 是否静音本地音频辅流发送。
 *               - YES（默认）：静音本地音频辅流。
 *               - NO：取消静音本地音频辅流。
 * @return
 * - 0：方法调用成功。
 * - 其他：方法调用失败。
 * @endif
 */
- (int)muteLocalSubStreamAudio:(BOOL)muted;


#pragma mark -- Sub Stream

/**
 * @if English
 * Sets the local substream canvas.
 * This method is used to set the display information about the local screen sharing with the substream video. The app associates with the video view of local substream by calling this method. During application development, in most cases, before joining a room, you must first call this method to set the local video view after the SDK is initialized.
 * @note
 * - If the app uses external rendering, we recommend that you set the video view before you join the room.
 * - Before you join a room, you must call the method after the SDK is initialized.
 * - A canvas is configured for only one user.
 * - The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * @since V4.5.0
 * @param canvas The video canvas. For more information, see {@link NERtcVideoCanvas}. To delete the canvas setting, set the value to nil.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 设置本地辅流视频画布。
 * <br>该方法设置本地辅流视频显示信息。App 通过调用此接口绑定本地辅流的显示视窗（view）。 在 App 开发中，通常在初始化后调用该方法进行本地视频设置，然后再加入房间。
 * @note
 * - 若使用外部渲染，建议在加入房间之前设置。
 * - 请在初始化后调用该方法，然后再加入房间。
 * - 同一个画布只能设置给一个用户。
 * - 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * @since V4.5.0
 * @param canvas 视频画布。详细信息请参考 {@link NERtcVideoCanvas}。删除画布设置时请传 nil。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)setupLocalSubStreamVideoCanvas:(NERtcVideoCanvas *)canvas;

/**
 * @if English
 * Enables screen sharing. The content of the screen sharing is published through the substream.
 * <br>You can call the method only after you join a room.
 * <br>If you join a room and call this method to enable the substream, the onUserSubStreamVideoStart callback is triggered on the remote client.
 * @note The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * @since V4.5.0
 * @param config The encoding configuration of the local substream. For more information, see {@link NERtcVideoSubStreamEncodeConfiguration}.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 开启屏幕共享，屏幕共享内容以辅流形式发送。
 * <br>只能在加入房间后调用。
 * <br>如果您在加入房间后调用该方法开启辅流，调用成功后，远端触发 onNERtcEngineUserSubStreamDidStartWithUserID 回调。
 * @since V4.5.0
 * @note 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * @param config 本地辅流发送配置，详细信息请参考  {@link NERtcVideoSubStreamEncodeConfiguration}。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)startScreenCapture:(NERtcVideoSubStreamEncodeConfiguration *)config;

/**
 * @if English
 * Disables screen sharing with the substream transmission.
 * If you use the method to disable the substream after you join a room, the onNERtcEngineUserSubStreamDidStop callback is triggered on the remote client.
 * @note The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * @since V4.5.0
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 关闭辅流形式的屏幕共享。
 * <br>如果您在加入房间后调用该方法关闭辅流，调用成功后，远端触发 onNERtcEngineUserSubStreamDidStop 回调。
 * @note 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * @since V4.5.0
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)stopScreenCapture;

/**
 * @if English
 * Sets the display mode of the local substream video for screen sharing.
 * Use this method if you want to enable screen sharing through the local substream. Apps can call this method multiple times to change the display mode.
 * @note
 * - The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * - Before you can call this method, you must set up the canvas for the local substream by calling setupLocalSubStreamVideoCanvas.
 * @since V4.5.0
 * @param mode The video display mode. For more information, see {@link NERtcVideoRenderScaleMode}.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 设置本端的屏幕共享辅流视频显示模式。
 * <br>在本端开启辅流形式的屏幕共享时使用。App 可以多次调用此方法更改显示模式。
 * @note
 * - 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * - 调用此方法前，必须先通过 setupLocalSubStreamVideoCanvas 设置本地辅流画布。
 * @since V4.5.0
 * @param mode 视频显示模式。详细信息请参考 {@link NERtcVideoRenderScaleMode}。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)setLocalRenderSubStreamScaleMode:(NERtcVideoRenderScaleMode)mode;

/**
 * @if English
 * Sets a remote substream canvas.
 * The method associates a remote user with a substream view. You can assign a specified userID to use a corresponding canvas.
 * @note 
 * - The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * - If the app uses external rendering, we recommend that you set the canvas after you receive the return of onUserJoined.
 * - If the app does not retrieve the ID of a remote user, you can call the method after the remote user joins the room. You can retrieve the uid of the remote user from the return of onNERtcEngineUserDidJoinWithUserID. You can use this method to set the substream video canvas.
 * - If the remote user leaves the room, the SDK disassociates the remote user from the canvas. The setting automatically becomes invalid.
 * @since V4.5.0
 * @param userID The ID of a remote user.
 * @param canvas The video canvas. For more information, see {@link NERtcVideoCanvas}. To delete the canvas setting, set the value to nil.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 设置远端的辅流视频画布。
 * <br>该方法绑定远端用户和辅流显示视图，即指定某个 userID 使用对应的画布显示。
 * @note
 * - 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * - 若使用外部渲染，建议在收到 onNERtcEngineUserDidJoinWithUserID 后设置。
 * - 如果 App 无法事先知道对方的用户 ID，可以在远端加入房间后调用。从 onNERtcEngineUserDidJoinWithUserID 中获取对方的 uid，并通过本方法为该用户设置辅流视频画布。
 * - 退出房间后，SDK 清除远端用户和画布的的绑定关系，该设置自动失效。
 * @since V4.5.0
 * @param userID 远端用户 ID。
 * @param canvas 视频画布。详细信息请参考 {@link NERtcVideoCanvas}。删除画布设置时请传 nil。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)setupRemoteSubStreamVideoCanvas:(NERtcVideoCanvas *)canvas forUserID:(uint64_t)userID;

/**
 * @if English
 * Subscribes to or unsubscribes from remote video substream for screen sharing. You can receive the video substream data only after you subscribe to the video substream.
 * @note
 * - The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * - You can call the method only after you join a room.
 * @since V4.5.0
 * @param subscribe The option whether to subscribe to remote video substream for screen sharing.
 * @param userID The ID of a remote user.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 订阅或取消订阅远端的屏幕共享辅流视频，订阅之后才能接收远端的辅流视频数据。
 * @note
 * - 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * - 只能在加入房间后调用。
 * @since V4.5.0
 * @param subscribe 是否订阅远端的屏幕共享辅流视频。
 * @param userID 远端用户 ID。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)subscribeRemoteSubStreamVideo:(BOOL)subscribe forUserID:(uint64_t)userID;

/**
 * @if English
 * Sets the display mode of to remote substream video for screen sharing.
 * @note
 * - The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * - Before you call this API, you must subscribe to the remote video substream for screen sharing by using subscribeRemoteSubStreamVideo.
 * @since V4.5.0
 * @param mode The video display mode. For more information, see {@link NERtcVideoRenderScaleMode}.
 * @param userID The ID of a remote user.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 设置远端的屏幕共享辅流视频显示模式。
 * @note
 * - 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * - 调用本接口之前，请先通过 subscribeRemoteSubStreamVideo 订阅远端的屏幕共享辅流视频。
 * @since V4.5.0
 * @param mode 视频显示模式。详细信息请参考 {@link NERtcVideoRenderScaleMode}。
 * @param userID 远端用户 ID。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)setRemoteRenderSubStreamVideoScaleMode:(NERtcVideoRenderScaleMode)mode forUserID:(uint64_t)userID;

/**
 * @if English
 * Sets a remote audio stream to high priority.
 * If a remote audio stream is set to high priority during automatic stream subscription, users can hear the audio stream with high priority.
 * @note
 * - You must set the API during calling with automatic subscription enabled (default)。
 * - The API can only set one audio stream to high priority. Subsequent settings will override the previous ones.
 * - If a call ends, the priority setting will be reset.
 * @since V4.6.0
 * @param enable enables or disables the high priority of a remote audio stream
 * - true：sets the high priority of a remote audio stream.
 * - false：Does not set the high priority of a remote audio stream.
 * @param userID    User ID
 * @param streamType The type of subscribed audio stream. The default type is kNERtcAudioStreamMain.
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
 * @since V4.6.0
 * @param enable 是否设置音频订阅优先级。
 * - true：设置音频订阅优先级。
 * - false：取消设置音频订阅优先级。
 * @param userID 用户 ID
 * @param streamType 订阅音频流的类型。默认为 kNERtcAudioStreamMain。
 * @return 操作返回值，成功则返回 0。
 * @endif
 */
- (int)setRemoteHighPriorityAudioStream:(BOOL)enable forUserID:(uint64_t)userID streamType:(NERtcAudioStreamType)streamType;

#pragma mark - waterMark

/**
 * @if English
 * Adds a watermark image to the local video.
 * @note 
 * - The setLocalCanvasWatermarkConfigs method applies to the local video canvas and does not affect the video stream. If the canvas is removed, the watermark will be automatically deleted.
 * - Before you set a watermark, you must first set the canvas by calling related methods.
 * @since V4.5.0
 * @param type The type of video streams. You can set the value to mainstream or substream. For more information, see {@link NERtcStreamChannelType}.
 * @param config The configuration of the watermark for the canvas. You can set text watermark, image watermark, and timestamp watermark. A value of null indicates removing the watermark. For more information, see {@link NERtcCanvasWatermarkConfig}.
 * @note The API is disabled in the audio-only SDK. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 添加本地视频画布水印。
 * @note
 * - setLocalCanvasWatermarkConfigs 方法作用于本地视频画布，不影响视频流。画布被移除时，水印也会自动移除。
 * - 设置水印之前，需要先通过画布相关方法设置画布。
 * @since V4.5.0
 * @param type 视频流类型。支持设置为主流或辅流。详细信息请参考 {@link NERtcStreamChannelType}。
 * @param config 画布水印设置。支持设置文字水印、图片水印和时间戳水印，设置为 null 表示清除水印。详细信息请参考 {@link NERtcCanvasWatermarkConfig}。
 * @note 纯音频SDK禁用该接口，如需使用请前往云信官网下载并替换成视频SDK
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)setLocalCanvasWatermarkConfigs:(nullable NERtcCanvasWatermarkConfig *)config
                       withStreamType:(NERtcStreamChannelType)type;

/**
 * @if English
 * Adds a watermark to the remote video canvas.
 * @note 
 * - The setRemoteCanvasWatermarkConfigs method Adds a watermark to the remote video canvas and does not affect the video stream. If the canvas is removed, the watermark will be automatically deleted.
 * - Before you set a watermark, you must first set the canvas by calling related methods.
 * @since V4.5.0
 * @param userID The ID of a remote user.
 * @param type The type of video streams. You can set the value to mainstream or substream. For more information, see {@link NERtcStreamChannelType}.
 * @param config The configuration of the watermark for the canvas. You can set text watermark, image watermark, and timestamp watermark. A value of null indicates to remove the watermark. For more information, see {@link NERtcCanvasWatermarkConfig}.
 * @note The API is disabled in the audio-only SDK. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 添加远端视频画布水印。
 * @note
 * - setRemoteCanvasWatermarkConfigs 方法作用于远端视频画布，不影响视频流。画布被移除时，水印也会自动移除。
 * - 设置水印之前，需要先通过画布相关方法设置画布。
 * @since V4.5.0
 * @param userID 远端用户 ID。
 * @param type 视频流类型。支持设置为主流或辅流。详细信息请参考 {@link NERtcStreamChannelType}。
 * @param config 画布水印设置。支持设置文字水印、图片水印和时间戳水印，设置为 null 表示清除水印。详细信息请参考 {@link NERtcCanvasWatermarkConfig}。
 * @note 纯音频SDK禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)setRemoteCanvasWatermarkConfigs:(nullable NERtcCanvasWatermarkConfig *)config
                             forUserID:(uint64_t)userID
                        withStreamType:(NERtcStreamChannelType)type;

#pragma mark -- snapshot
/**
 * @if English
 * Takes a local video snapshot.
 * <br>The takeLocalSnapshot method takes a local video snapshot on the local mainstream or local substream. The callback that belongs to the NERtcTakeSnapshotCallback class returns the data of the snapshot image.
 * @note<br>
 * - Before you call the method to capture the snapshot from the mainstream, you must first call startVideoPreview or enableLocalVideo, and joinChannel.
 * - Before you call the method to capture the snapshot from the substream, you must first call joinChannel and startScreenCapture.
 * - You can set text, timestamp, and image watermarks at the same time. If different types of watermarks overlap, the layers overlay previous layers in the image, text, and timestamp sequence.
 * @since V4.5.0
 * @param streamType The video stream type of the snapshot. You can set the value to mainstream or substream.
 * @param callback The snapshot callback.
 * @note The API is disabled in the audio-only SDK. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 本地视频画面截图。
 * <br>调用 takeLocalSnapshot 截取本地主流或本地辅流的视频画面，并通过 NERtcTakeSnapshotCallback 的回调返回截图画面的数据。
 * @note<br>
 * - 本地主流截图，需要在 startPreview 或者 enableLocalVideo 并 joinChannel 成功之后调用。
 * - 本地辅流截图，需要在 joinChannel 并 startScreenCapture 之后调用。
 * - 同时设置文字、时间戳或图片水印时，如果不同类型的水印位置有重叠，会按照图片、文本、时间戳的顺序进行图层覆盖。
 * @since V4.5.0
 * @param streamType 截图的视频流类型。支持设置为主流或辅流。
 * @param callback 截图回调。
 * @note 纯音频SDK禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)takeLocalSnapshot:(NERtcStreamChannelType)streamType callback:(NERtcTakeSnapshotCallback)callback;

/**
 * @if English
 * Takes a snapshot of a remote video.
 * <br>The takeRemoteSnapshot method takes a snapshot from the remote video published through the mainstream or substream with a specified uid. The callback that belongs to the NERtcTakeSnapshotCallback class returns the data of the snapshot image.
 * @note<br>
 * - Before you call takeRemoteSnapshot, you must first call onUserVideoStart and onNERtcEngineUserSubStreamDidStartWithUserID.
 * - You can set text, timestamp, and image watermarks at the same time. If different types of watermarks overlap, the layers overlay previous layers in the image, text, and timestamp sequence.
 * @since V4.5.0
 * @param userID The ID of a remote user.
 * @param streamType The video stream type of the snapshot. You can set the value to mainstream or substream.
 * @param callback The snapshot callback.
 * @note The API is disabled in the audio-only SDK. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 远端视频画面截图。
 * <br>调用 takeRemoteSnapshot 截取指定 uid 远端主流和远端辅流的视频画面，并通过 NERtcTakeSnapshotCallback 的回调返回截图画面的数据。
 * @note<br>
 * - takeRemoteSnapshot 需要在收到 onUserVideoStart 与 onNERtcEngineUserSubStreamDidStartWithUserID 回调之后调用。
 * - 同时设置文字、时间戳或图片水印时，如果不同类型的水印位置有重叠，会按照图片、文本、时间戳的顺序进行图层覆盖。
 * @since V4.5.0
 * @param userID 远端用户 ID。
 * @param streamType 截图的视频流类型。支持设置为主流或辅流。
 * @param callback 截图回调。
 * @note 纯音频SDK禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)takeRemoteSnapshot:(NERtcStreamChannelType)streamType forUserID:(uint64_t)userID callback:(NERtcTakeSnapshotCallback)callback;

#pragma mark - SEI

/**
 * @if English
 * Sends supplemental enhancement information (SEI) data through a specified mainstream or substream.
 * When you publish the local audio and video stream, SEI data is also sent to sync some additional information. After SEI data is sent, the receiver can retrieve the content by listening for the onRecvSEIMsg callback.
 * - Condition: After you publish the video stream using the mainstream and substream, you can invoke the method.
 * - Data limit in length: The SEI data can contain a maximum of 4,096 bytes in size. Sending an SEI message fails if the data exceeds the size limit. If a large amount of data is sent, the video bitrate rises. This degrades the video quality or causes frozen frames.
 * - Frequency limit: We recommend that the maximum video frame rate does not exceed 10 fps.
 * - Time to take effect: After the method is called, the SEI data is sent from the next frame in the fastest fashion or after the next 5 frames at the slowest pace.
 * <p>@note
 * - The SEI data is transmitted together with the video stream. If video frame loss occurs due to poor connection quality, the SEI data will also get dropped. We recommend that you increase the frequency within the transmission limits. This way, the receiver can get the data.
 * - Before you specify a channel to transmit the SEI data, you must first enable the data transmission channel.
 * - The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * @since V4.5.0
 * @param data The custom SEI data.
 * @param type The type of the channel with which the SEI data is transmitted. For more information, see {@link NERtcStreamChannelType}.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * -Success: The SEI data joins the queue and is ready for delivery. The data will be sent after the most recent video frame.
 * -Failure: If the data is restricted, the frequency may be too high, the queue is full, or the data exceeds the maximum value of 4k.
 * @endif
 * @if Chinese
 * 指定主流或辅流通道发送媒体增强补充信息（SEI）。
 * <br>在本端推流传输音视频流数据同时，发送流媒体补充增强信息来同步一些其他附加信息。当推流方发送 SEI 后，拉流方可通过监听 onRecvSEIMsg 的回调获取 SEI 内容。
 *  - 调用时机：视频流（主流、辅流）开启后，可调用此函数。
 *  - 数据长度限制： SEI 最大数据长度为 4096 字节，超限会发送失败。如果频繁发送大量数据会导致视频码率增大，可能会导致视频画质下降甚至卡顿。
 *  - 发送频率限制：最高为视频发送的帧率，建议不超过 10 次/秒。
 *  - 生效时间：调用本接口之后，最快在下一帧视频数据帧之后发送 SEI 数据，最慢在接下来的 5 帧视频之后发送。
 * <p>@note
 * - SEI 数据跟随视频帧发送，由于在弱网环境下可能丢帧，SEI 数据也可能随之丢失，所以建议在发送频率限制之内多次发送，保证接收端收到的概率。
 * - 指定通道发送 SEI 之前，需要提前开启对应的数据流通道。
 * - 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * @since V4.5.0
 * @param data 自定义 SEI 数据。
 * @param type  发送 SEI 时，使用的流通道类型。详细信息请参考 {@link NERtcStreamChannelType}。
 * @return 操作返回值，成功则返回 0
 * - 成功:  成功进入待发送队列，会在最近的视频帧之后发送该数据。
 * - 失败:  数据被限制发送，可能发送的频率太高，队列已经满了，或者数据大小超过最大值 4k。
 * @endif
 */
- (int)sendSEIMsg:(NSData *)data streamChannelType:(NERtcStreamChannelType)type;

/**
 * @if English
 * Sends SEI data through the mainstream.
 * When you publish the local audio and video stream, SEI data is also sent to sync some additional information. After SEI data is sent, the receiver can retrieve the content by listening for the onRecvSEIMsg callback.
 * - Condition: After you publish the video stream using the mainstream and substream, you can invoke the method.
 * - Data limit in length: The SEI data can contain a maximum of 4,096 bytes in size. Sending an SEI message fails if the data exceeds the size limit. If a large amount of data is sent, the video bitrate rises. This degrades the video quality or causes broken video frames.
 * - Frequency limit: We recommend that the maximum video frame rate does not exceed 10 fps.
 * - Time to take effect: After the method is called, the SEI data is sent from the next frame in the fastest fashion or after the next 5 frames at the slowest pace.
 * <p>@note
 * - The SEI data is transmitted together with the video stream. If video frame loss occurs due to poor connection quality, the SEI data will also get dropped. We recommend that you increase the frequency within the transmission limits. This way, the receiver can get the data.
 * - By default, the SEI is transmitted by using the mainstream.
 * - The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * @since V4.5.0
 * @param data The custom SEI data.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * -Success: The SEI data joins the queue and is ready for delivery. The data will be sent after the most recent video frame.
 * -Failure: If the data is restricted, the frequency may be too high, the queue is full, or the data size exceeds the maximum value of 4k
 * @endif
 * @if Chinese
 * 通过主流通道发送媒体增强补充信息（SEI）。
 * <br>在本端推流传输音视频流数据同时，发送流媒体补充增强信息来同步一些其他附加信息。当推流方发送 SEI 后，拉流方可通过监听 onRecvSEIMsg 的回调获取 SEI 内容。
 * - 调用时机：视频流（主流、辅流）开启后，可调用此函数。
 * - 数据长度限制： SEI 最大数据长度为 4096 字节，超限会发送失败。如果频繁发送大量数据会导致视频码率增大，可能会导致视频画质下降甚至卡顿。
 * - 发送频率限制：最高为视频发送的帧率，建议不超过 10 次/秒。
 * - 生效时间：调用本接口之后，最快在下一帧视频数据帧之后发送 SEI 数据，最慢在接下来的 5 帧视频之后发送。
 * <p>@note
 * - SEI 数据跟随视频帧发送，由于在弱网环境下可能丢帧，SEI 数据也可能随之丢失，所以建议在发送频率限制之内多次发送，保证接收端收到的概率。
 * - 调用本接口时，默认使用主流通道发送 SEI。
 * - 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * @since V4.5.0
 * @param data 自定义 SEI 数据。
 * @return 操作返回值，成功则返回 0。
 * - 成功: 成功进入待发送队列，会在最近的视频帧之后发送该数据。
 * - 失败: 数据被限制发送，可能发送的频率太高，队列已经满了，或者数据大小超过最大值 4k。
 * @endif
 */
- (int)sendSEIMsg:(NSData *)data;

#pragma mark -- Live Stream

/**
 * @if English
 * Adds a streaming task in a room.
 * After you call the method, the current user can receive a notification about the status of live streaming.
 * @note
 * - The method is applicable to only live streaming.
 * - You can call the method when you are in a room. The method is valid for calls.
 * - Only one URL for the relayed stream is added in each call. You need to call the method multiple times if you want to push many streams. An RTC room with the same channelid can create three different streaming tasks.
 * @since V4.5.0
 * @param taskInfo The information about the streaming task. For more information, {@link NERtcLiveStreamTaskInfo}.
 * @param completion The result. The callback is triggered after the method is called. For more information, see {@link NERtcLiveStreamCompletion}.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 添加房间推流任务。
 * <br>成功调用该方法后，当前用户可以收到该直播流的状态通知。
 * @note
 * - 该方法仅适用直播场景。
 * - 请在房间内调用该方法，该方法在通话中有效。
 * - 该方法每次只能增加一路旁路推流地址。如需推送多路流，则需多次调用该方法。同一个音视频房间（即同一个 channelid）可以创建 3 个不同的推流任务。
 * @since V4.5.0
 * @param taskInfo 推流任务信息，详细信息请参考  {@link NERtcLiveStreamTaskInfo}。
 * @param completion 操作结果回调，方法调用成功后会触发对应回调。详细信息请参考 {@link NERtcLiveStreamCompletion}。
 * @return 操作返回值，成功则返回 0
 * 
* @endif
*/
- (int)addLiveStreamTask:(NERtcLiveStreamTaskInfo *)taskInfo compeltion:(NERtcLiveStreamCompletion)completion;

/**
 * @if English
 * Updates a streaming task.
 * @note
 * - The method is applicable to only live streaming.
 * - You can call the method when you are in a room. The method is valid for calls.
 * @since V4.5.0
 * @param taskInfo The information about the streaming task. For more information, see {@link NERtcLiveStreamTaskInfo}.
 * @param completion The result. The callback is triggered after the method is called. For more information, see {@link NERtcLiveStreamCompletion}.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 更新房间推流任务。
 * @note
 * - 该方法仅适用直播场景。
 * - 请在房间内调用该方法，该方法在通话中有效。
 * @since V4.5.0
 * @param taskInfo 推流任务信息，详细信息请参考 {@link NERtcLiveStreamTaskInfo}。  
 * @param completion 操作结果回调，方法调用成功后会触发对应回调。详细信息请参考 {@link NERtcLiveStreamCompletion}。
 * @return 操作返回值，成功则返回 0
* @endif
*/
- (int)updateLiveStreamTask:(NERtcLiveStreamTaskInfo *)taskInfo compeltion:(NERtcLiveStreamCompletion)completion;

/**
 * @if English
 * Deletes a streaming task.
 * @note
 * - The method is applicable to only live streaming.
 * - You can call the method when you are in a room. The method is valid for calls.
 * @since V4.5.0
 * @param taskId The ID of a streaming task.  
 * @param completion The result. The callback is triggered after the method is called. For more information, see {@link NERtcLiveStreamCompletion}.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 删除房间推流任务。
 * @note
 * - 该方法仅适用直播场景。
 * - 请在房间内调用该方法，该方法在通话中有效。
 * @since V4.5.0
 * @param taskId 推流任务 ID。  
 * @param completion 操作结果回调，方法调用成功后会触发对应回调。详细信息请参考 {@link NERtcLiveStreamCompletion}。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)removeLiveStreamTask:(NSString *)taskId compeltion:(NERtcLiveStreamCompletion)completion;

/**
 * @if English 
 * Registers a channel media stats observer.
 * @since V4.5.0
 * @param observer The stats observer. For more information, see {@link NERtcChannelMediaStatsObserver}。
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 注册媒体统计信息观测器。
 * @since V4.5.0
 * @param observer 统计信息观测器。详细信息请参考  {@link NERtcChannelMediaStatsObserver}。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)addChannelMediaStatsObserver:(id<NERtcChannelMediaStatsObserver>)observer;

/**
 * @if English 
 * Remove a channel media stats observer.
 * @since V4.5.0
 * @param observer The stats observer. For more information, see {@link NERtcChannelMediaStatsObserver}。
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 移除指定媒体统计信息观测器。
 * @since V4.5.0
 * @param observer 统计信息观测器
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)removeChannelMediaStatsObserver:(id<NERtcChannelMediaStatsObserver>)observer;

/**
 * @if English 
 * Remove all channel media stats observers.
 * @since V4.5.0
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 清除全部媒体统计信息观测器。
 * @since V4.5.0
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)cleanupChannelMediaStatsObserver;


/**
 * @if English
 * Starts to relay media streams across rooms.
 * - The method can invite co-hosts across rooms. Media streams from up to four rooms can be relayed. A room can receive multiple relayed media streams.
 * - After you call the method, the SDK triggers onNERtcEngineChannelMediaRelayStateDidChange and onNERtcEngineDidReceiveChannelMediaRelayEvent. The callback reports the status and events about the current relayed media streams across rooms.
 * <p>@note
 * - You can call this method after you join a room. Before you call the method, you must set the destination room by calling setDestinationInfo in the config parameter.
 * - The method is applicable only to the host in live streaming.
 * - If you want to call the method again, you must first call the stopChannelMediaRelay method to exit the current relay status.
 * - If you succeed in relaying the media stream across rooms, and want to change the destination room, for example, add or remove the destination room, you can call updateChannelMediaRelay to update the information about the destination room. 
 * <p>@since V4.5.0
 * @param config The configuration for media stream relay across rooms. For more information, see {@link NERtcChannelMediaRelayConfiguration}.
 * @return A value of 0 returned indicates that the operation is successful. Otherwise, the operation fails.
 * @endif
 * @if Chinese
 * 开始跨房间媒体流转发。
 * - 该方法可用于实现跨房间连麦等场景。支持同时转发到 4 个房间，同一个房间可以有多个转发进来的媒体流。
 * - 成功调用该方法后，SDK 会触发 onNERtcEngineChannelMediaRelayStateDidChange 和 onNERtcEngineDidReceiveChannelMediaRelayEvent 回调，并在回调中报告当前的跨房间媒体流转发状态和事件。
 * <p>@note
 * - 请在成功加入房间后调用该方法。调用此方法前需要通过 config 中的 setDestinationInfo 设置目标房间。
 * - 该方法仅对直播场景下的主播角色有效。
 * - 成功调用该方法后，若您想再次调用该方法，必须先调用 stopChannelMediaRelay 方法退出当前的转发状态。
 * - 成功开始跨房间转发媒体流后，如果您需要修改目标房间，例如添加或删减目标房间等，可以调用方法 updateChannelMediaRelay 更新目标房间信息。
 * <p>@since V4.5.0
 * @param config 跨房间媒体流转发参数配置信息。详细信息请参考 {@link NERtcChannelMediaRelayConfiguration}。
 * @return 成功返回0，其他则失败
 * @endif
 */
- (int)startChannelMediaRelay:(NERtcChannelMediaRelayConfiguration *_Nonnull)config;

/**
 * @if English
 * Updates the information of the destination room for media stream relay.
 * <br>You can call this method to relay the media stream to multiple rooms or exit the current room.
 * - You can call this method to change the destination room, for example, add or remove the destination room.
 * - If you call this method, the SDK triggers the onNERtcEngineChannelMediaRelayStateDidChange callback. If NERtcChannelMediaRelayStateRunning is returned, the media stream relay is successful.
 * <p>@note
 * - Before you call the method, you must join the room and call startChannelMediaRelay to relay the media stream across rooms. Before you call the method, you must set the destination room by calling setDestinationInfo in the config parameter.
 * - You can relay the media stream up to four destination rooms. You can first call removeDestinationInfoForChannelName that belongs to the NERtcChannelMediaRelayConfiguration class to remove the rooms that you have no interest in and add new destination rooms.
 * @since V4.5.0
 * @param config The configuration for media stream relay across rooms. For more information, see {@link NERtcChannelMediaRelayConfiguration}.
 * @return A value of 0 returned indicates that the operation is successful. Otherwise, the operation fails.
 * @endif
 * @if Chinese
 * 更新媒体流转发的目标房间。
 * <br>成功开始跨房间转发媒体流后，如果你希望将流转发到多个目标房间，或退出当前的转发房间，可以调用该方法。
 * - 成功开始跨房间转发媒体流后，如果您需要修改目标房间，例如添加或删减目标房间等，可以调用此方法。
 * - 成功调用此方法后，SDK 会触发 onNERtcEngineChannelMediaRelayStateDidChange 回调。如果报告 NERtcChannelMediaRelayStateRunning，则表示已成功转发媒体流。
 * <p>@note
 * - 请在加入房间并成功调用 startChannelMediaRelay 开始跨房间媒体流转发后，调用此方法。调用此方法前需要通过 config 中的 setDestinationInfo 设置目标房间。
 * - 跨房间媒体流转发最多支持 4 个目标房间，您可以在调用该方法之前，通过 NERtcChannelMediaRelayConfiguration 中的 removeDestinationInfoForChannelName 方法移除不需要的房间，再添加新的目标房间。
 * @since V4.5.0
 * @param config 跨房间媒体流转发参数配置信息。详细信息请参考 {@link NERtcChannelMediaRelayConfiguration}。
 * @return 成功返回0，其他则失败
 * @endif
 */
- (int)updateChannelMediaRelay:(NERtcChannelMediaRelayConfiguration *_Nonnull)config;

/**
 * @if English
 * Stops media stream relay across rooms.
 * <br>If the host leave the room, media stream replay across rooms automatically stops. You can also call stopChannelMediaRelay. In this case, the host leaves all destination rooms.
 * - If you call this method, the SDK triggers the onNERtcEngineChannelMediaRelayStateDidChange callback. If NERtcChannelMediaRelayStateRunning is returned, the media stream relay stops.
 * - If the operation fails, the SDK triggers the onNERtcEngineChannelMediaRelayStateDidChange callback that returns the status code NERtcChannelMediaRelayStateFailure.
 * <p>@since V4.5.0
 * @return A value of 0 returned indicates that the operation is successful. Otherwise, the operation fails.
 * @endif
 * @if Chinese
 * 停止跨房间媒体流转发。
 * <br>主播离开房间时，跨房间媒体流转发自动停止，您也可以在需要的时候随时调用 stopChannelMediaRelay 方法，此时主播会退出所有目标房间。
 * - 成功调用该方法后，SDK 会触发 onNERtcEngineChannelMediaRelayStateDidChange 回调。如果报告 NERtcChannelMediaRelayStateIdle，则表示已停止转发媒体流。
 * - 如果该方法调用不成功，SDK 会触发 onNERtcEngineChannelMediaRelayStateDidChange 回调，并报告状态码 NERtcChannelMediaRelayStateFailure。
 * <p>@since V4.5.0
 * @return 成功返回0，其他则失败
 * @endif
 */
- (int)stopChannelMediaRelay;

/**
 * @if English
 * Sets the priority of media streams from a local user.
 * If a user has a high priority, the media stream from the user also has a high priority. In unreliable network connections, the SDK guarantees the quality of the media stream from users with a high priority.
 * @note 
 * - You must call the method before you call joinChannel.
 * - After you switch the room by calling switchChannel, the media stream priority is restored to the default normal priority.
 * @since V4.5.0
 * @param priority The priority of the local media stream. The default value is #kNERtcMediaPriorityNormal, which indicates the normal priority. For more information, see {@link NERtcMediaPriorityType}. 
 * @param preemptive The option whether to enable the preempt mode.
 *                    - If the preempt mode is enabled, the local media stream preempts the high priority over other users. The priority of the media stream whose priority is taken becomes normal. After the user who preempts the priority leaves the room, other users still keep the normal priority.
 *                    - If the preempt mode is disabled, and a user in the room has a high priority, then, the high priority of the local client remains invalid and is still normal.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 设置本地用户的媒体流优先级。
 * <br>如果某个用户的优先级为高，那么该用户媒体流的优先级就会高于其他用户，弱网环境下 SDK 会优先保证其他用户收到的、高优先级用户的媒体流的质量。
 * @note
 * - 请在加入房间（joinChannel）前调用此方法。
 * - 快速切换房间 （switchChannel） 后，媒体优先级会恢复为默认值，即普通优先级。
 * @since V4.5.0
 * @param priority   本地用户的媒体流优先级，默认为 #kNERtcMediaPriorityNormal ，即普通优先级。详细信息请参考 {@link NERtcMediaPriorityType}。
 * @param preemptive 是否开启抢占模式，默认为 NO，即不开启。
 *                    - 抢占模式开启后，本地用户可以抢占其他用户的高优先级，被抢占的用户的媒体优先级变为普通优先级，在抢占者退出房间后，其他用户的优先级仍旧维持普通优先级。
 *                    - 抢占模式关闭时，如果房间中已有高优先级用户，则本地用户的高优先级设置不生效，仍旧为普通优先级。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)setLocalMediaPriority:(NERtcMediaPriorityType)priority preemptive:(BOOL)preemptive;


/**
 * @if English
 * Sets the fallback option for the published local video stream based on the network conditions.
 * <br>The quality of the published local audio and video streams is degraded with poor quality network connections. After you call this method and set the option to kNERtcStreamFallbackOptionAudioOnly:
 * - With unreliable uplink network connections and the quality of audio and video streams degraded, the SDK switches to receive a low-quality video stream or stops receiving video streams. This way, the audio quality is maintained or improved.
 * - The SDK monitors the network performance and recover audio and video streams if the network quality improves.
 * - If the published audio and video stream from the local client falls back to the audio stream, or recovers to the audio and video stream, the SDK triggers the onLocalPublishFallbackToAudioOnly callback.
 * <p>@note You must call the method before you call joinChannel.
 * @since V4.5.0
 * @param option The fallback options for publishing audio and video streams. The default value is disabled. For more information, see {@link NERtcStreamFallbackOptions}.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 设置弱网条件下发布的音视频流回退选项。
 * <br>在网络不理想的环境下，发布的音视频质量都会下降。使用该接口并将 option 设置为 kNERtcStreamFallbackOptionAudioOnly 后:
 * - SDK 会在上行弱网且音视频质量严重受影响时，自动关断视频流，尽量保证音频质量。
 * - 同时 SDK 会持续监控网络质量，并在网络质量改善时恢复音视频流。
 * - 当本地发布的音视频流回退为音频流时，或由音频流恢复为音视频流时，SDK 会触发本地发布的媒体流已回退为音频流 onLocalPublishFallbackToAudioOnly 回调。
 * <p>@note 请在加入房间（joinChannel）前调用此方法。
 * @since V4.5.0
 * @param option   发布音视频流的回退选项，默认为不开启回退。 详细信息请参考 {@link NERtcStreamFallbackOptions}。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)setLocalPublishFallbackOption:(NERtcStreamFallbackOptions)option;

/**
 * @if English
 * Sets the fallback option for the subscribed remote audio and video stream with poor network connections.
 * <br>The quality of the subscribed audio and video streams is degraded with unreliable network connections. After you set the fallback options for the subscribed audio and video stream by using this method:
 * - With unreliable downstream network connections, the SDK switches to receive a low-quality video stream or stops receiving video streams. This way, the audio quality is maintained or improved.
 * - The SDK monitors the network quality and resumes the video stream when the network conditions improve.
 * - If the subscribed remote video stream falls back to an audio-only stream, or the audio-only stream switches back to the video stream, the SDK triggers the onNERtcEngineRemoteSubscribeFallbackToAudioOnly callback.
 * <p>@note You must call the method before you call joinChannel.
 * @since V4.5.0
 * @param option The fallback option for subscribing to audio and video streams. The default setting is to fall back to the low-quality video stream in the poor network. For more information, see {@link NERtcStreamFallbackOptions}.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 设置弱网条件下订阅的音视频流回退选项。
 * <br>弱网环境下，订阅的音视频质量会下降。通过该接口设置订阅音视频流的回退选项后：
 * - SDK 会在下行弱网且音视频质量严重受影响时，将视频流切换为小流，或关断视频流，从而保证或提高通信质量。
 * - SDK 会持续监控网络质量，并在网络质量改善时自动恢复音视频流。
 * - 当远端订阅流回退为音频流时，或由音频流恢复为音视频流时，SDK 会触发远端订阅流已回退为音频流回调。
 * <p>@note 请在加入房间（joinChannel）前调用此方法。
 * @since V4.5.0
 * @param option   订阅音视频流的回退选项，默认为弱网时回退到视频小流。详细信息请参考 {@link NERtcStreamFallbackOptions}。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)setRemoteSubscribeFallbackOption:(NERtcStreamFallbackOptions)option;

@end

NS_ASSUME_NONNULL_END

#endif /* INERtcChannel_h */
