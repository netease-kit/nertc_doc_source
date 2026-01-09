//
//  NERtcChannelDelegate.h
//  NERtcSDK
//
//  Created by yuan on 2021/5/27.
//  Copyright © 2021 Netease. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "NERtcEngineErrorCode.h"
#import "NERtcEngineEnum.h"
#import "NERtcEngineBase.h"


NS_ASSUME_NONNULL_BEGIN

/**
 * @if English
 * `NERtcChannelDelegate` class provides callbacks that report events and statistics of a specified channel.
 * @since V4.5.0
 * @endif
 * @if Chinese
 * `NERtcChannelDelegate` 类监听和报告指定房间的事件和数据。
 * @since V4.5.0
 * @endif
 */
@protocol NERtcChannelDelegate <NSObject>

@optional

/**
 * @if English
 * Occurs when the state of the channel connection between the SDK and the server changes.
 * The callback is triggered when the state of the channel connection changes. The callback returns the current state of channel and the reason why the state changes.
 * @since V4.5.0
 * @param state The state of the channel connection. For more information, see {@link NERtcConnectionStateType}.
 * @param reason The reason why the state changes. For more information, see {@link NERtcReasonConnectionChangedType}.
 * @endif
 * @if Chinese
 * 房间连接状态已改变回调。
 * <br>该回调在房间连接状态发生改变时触发，并告知用户当前的房间连接状态和引起状态改变的原因。
 * @since V4.5.0
 * @param state  当前的连接状态。详细信息请参考  {@link NERtcConnectionStateType}。
 * @param reason 引起当前连接状态改变的原因。详细信息请参考 {@link NERtcReasonConnectionChangedType}。
 * @endif
 */
- (void)onNERtcChannelConnectionStateChangeWithState:(NERtcConnectionStateType)state
                                             reason:(NERtcReasonConnectionChangedType)reason;

/**
 * @if English 
 * Occurs when a user leaves a room.
 * After an app invokes the leaveChannel method, SDK prompts whether the app successfully leaves the room. 
 * @since V4.5.0
 * @param result The result of the leaveChannel operation. For more information, see {@link NERtcEngineErrorCode.NERtcError}.
 * @endif
 * @if Chinese
 * 退出房间回调。
 * App 调用 leaveChannel 方法后，SDK 提示 App 退出房间是否成功。
 * @since V4.5.0
 * @param result 退出房间结果。详细信息请参考 {@link NERtcEngineErrorCode.NERtcError}。在快速切换房间时 code 为 kNERtcErrChannelLeaveBySwitchAction。
 * @endif
 */
- (void)onNERtcChannelDidLeaveChannelWithResult:(NERtcError)result;

/**
 * @if English 
 * Occurs when a remote user joins the current room.
 * The callback prompts that a remote user joins the room and returns the ID of the user that joins the room. If the user ID already exists, the remote user also receives a message that the user already joins the room, which is returned by the callback.
 * The callback is triggered in the following cases:
 * - A remote user joins the room by calling the joinChannel method.
 * - A remote user rejoins the room after the client is disconnected.
 * @since V4.5.0
 * @param userID The ID of a remote user.
 * @param userName The name of the user who joins the room. The field is deprecated. Ignore the field.
 * @endif
 * @if Chinese
 * 远端用户（通信场景）/主播（直播场景）加入当前频道回调。
 * - 通信场景下，该回调提示有远端用户加入了频道，并返回新加入用户的 ID；如果加入之前，已经有其他用户在频道中了，新加入的用户也会收到这些已有用户加入频道的回调。
 * - 直播场景下，该回调提示有主播加入了频道，并返回该主播的用户 ID。如果在加入之前，已经有主播在频道中了，新加入的用户也会收到已有主播加入频道的回调。

 * 该回调在如下情况下会被触发：
 * - 远端用户调用 joinChannel 方法加入房间。
 * - 远端用户网络中断后重新加入房间。
 * @since V4.5.0
 * @note
 * 直播场景下：
        * - 主播间能相互收到新主播加入频道的回调，并能获得该主播的用户 ID。
        * - 观众也能收到新主播加入频道的回调，并能获得该主播的用户 ID。
        * - 当 Web 端加入直播频道时，只要 Web 端有推流，SDK 会默认该 Web 端为主播，并触发该回调。
 * @param userID 新加入房间的远端用户 ID。
 * @param userName 新加入房间的远端用户名。废弃字段，无需关注。
 * @endif
 */
- (void)onNERtcChannelUserDidJoinWithUserID:(uint64_t)userID userName:(NSString *)userName;

/**
 * @if English
 * Occurs when a remote user leaves a room.
 * A message is returned that a remote user leaves the room or becomes disconnected. A user leaves a room due to the following reasons: the user exit the room or connections time out.
 * - If a user leaves the room, the remote user receives a message that indicates that the user leaves the room.
 * - If the connection times out, and the user does not receive data packets for a time period of 40 to 50 seconds, then the user becomes disconnected.
 * <p>@since V4.5.0
 * @param userID The ID of the user that leaves the room.
 * @param reason The reason why the remote user leaves. For more information, see {@link NERtcSessionLeaveReason}.
 * @endif
 * @if Chinese
 * 远端用户离开当前房间回调。
 * <br>提示有远端用户离开了房间（或掉线）。用户离开房间有两个原因，即正常离开和超时掉线：
 * - 正常离开的时候，远端用户会收到相关消息消息，判断用户离开房间。
 * - 超时掉线的依据是，在一定时间内（40~50s），用户没有收到对方的任何数据包，则判定为对方掉线。
 * <p>@since V4.5.0
 * @param userID 离开房间的远端用户 ID。
 * @param reason 远端用户离开的原因。详细信息请参考  {@link NERtcSessionLeaveReason}。
 * @endif
 */
- (void)onNERtcChannelUserDidLeaveWithUserID:(uint64_t)userID reason:(NERtcSessionLeaveReason)reason;

/**
 * @if English 
 * Occurs when a user changes the role in live streaming.
 * After the user joins a room, the user can call the setClientRole method to change the role. Then, the callback is triggered. For example, switching from host to audience, or from audience to host.
 * @note In live streaming, if you join a room and successfully call this method to change the role, the following callbacks are triggered.
 * - If the role changes from host to audience, the onClientRoleChange callback is triggered on the client, and the onUserLeave callback is triggered on a remote client.
 * - If the role changes from audience to host, the onClientRoleChange callback is triggered on the client, and the onUserJoined callback is triggered on a remote client.
 * @param oldRole The role before the user changes the role.
 * @param newRole The role after the user changes the role.
 * @endif
 * @if Chinese
 * 直播场景下用户角色已切换回调。
 * <br>用户加入房间后，通过 {@link INERtcEngine#setClientRole:} 切换用户角色后会触发此回调。例如从主播切换为观众、从观众切换为主播。
 * @note 直播场景下，如果您在加入房间后调用该方法切换用户角色，调用成功后，会触发以下回调：
 * - 主播切观众，本端触发 onNERtcEngineDidClientRoleChanged 回调，远端触发 {@link NERtcEngineDelegate#onNERtcEngineUserDidLeaveWithUserID:reason:} 回调。
 * - 观众切主播，本端触发 onNERtcEngineDidClientRoleChanged 回调，远端触发 {@link NERtcEngineDelegate#onNERtcEngineUserDidJoinWithUserID:reason:} 回调。
 * <p>@since V4.5.0
 * @param oldRole 切换前的角色。详细信息请参考 {@link NERtcClientRole}。
 * @param newRole 切换后的角色。详细信息请参考 {@link NERtcClientRole}。
 * @endif
 */
- (void)onNERtcChannelDidClientRoleChanged:(NERtcClientRole)oldRole newRole:(NERtcClientRole)newRole;

/**
 * @if English
 * Occurs when a remote user enables audio.
 * @since V4.5.0
 * @param userID The ID of a remote user.
 * @endif
 * @if Chinese
 * 远端用户开启音频回调。
 * @since V4.5.0
 * @note 该回调由远端用户调用 enableLocalAudio 方法开启音频采集和发送触发。
 * @param userID 远端用户 ID。
 * @endif
 */
- (void)onNERtcChannelUserAudioDidStart:(uint64_t)userID;

/**
 * @if English
 * Occurs when a remote user disables audio.
 * @since V4.5.0
 * @param userID The ID of a remote user.
 * @endif
 * @if Chinese
 * 远端用户停用音频回调。
 * @since V4.5.0
 * @note 该回调由远端用户调用 enableLocalAudio 方法关闭音频采集和发送触发。
 * @param userID 远端用户 ID。
 * @endif
 */
- (void)onNERtcChannelUserAudioDidStop:(uint64_t)userID;

/**
 * @if English
 * Occurs when a remote user stops or resumes sending audio streams.
 * @since V4.5.0
 * @param userID The ID of the user whose audio streams are sent.
 * @param muted The option whether to pause sending audio streams.
 * @endif
 * @if Chinese
 * 远端用户暂停或恢复发送音频流的回调。
 * @since V4.5.0
 * @note 该回调由远端用户调用 muteLocalAudio 方法开启或关闭音频发送触发。
 * @param userID 用户 ID，提示是哪个用户的音频流。
 * @param muted 是否暂停发送音频流。
 * @endif
 */
- (void)onNERtcChannelUser:(uint64_t)userID audioMuted:(BOOL)muted;

/**
 * @if English
 * Occurs when a remote user enables the audio substream.
 * @since V4.6.10
 * @param userID Remote user ID.
 * @endif
 * @if Chinese
 * 远端用户开启音频辅流回调。
 * @since V4.6.10
 * @param userID 远端用户 ID。
 * @endif
 */
- (void)onNERtcChannelUserSubStreamAudioDidStart:(uint64_t)userID;

/**
 * @if English
 * Occurs when a remote user stops the audio substream.
 * @since V4.6.10
 * @param userID remote user ID.
 * @endif
 * @if Chinese
 * 远端用户停用音频辅流回调。
 * @since V4.6.10
 * @param userID 远端用户 ID。
 * @endif
 */
- (void)onNERtcChannelUserSubStreamAudioDidStop:(uint64_t)userID;

/**
 * @if English
 * Occurs when a remote user pauses or resumes publishing the audio substream.
 * @since V4.6.10
 * @param userID   User ID indicating which user performs the operation.
 * @param muted indicates if the audio substream is stopped.
 *               - YES: stops publishing the audio substream.
 *               - No: resumes publishing the audio substream.
 * @endif
 * @if Chinese
 * 远端用户暂停或恢复发送音频辅流的回调。
 * @since V4.6.10
 * @param userID 用户 ID，提示是哪个用户的音频辅流。
 * @param muted 是否停止发送音频辅流。
 *               - YES：该用户已暂停发送音频辅流。
 *               - NO：该用户已恢复发送音频辅流。
 * @endif
 */
- (void)onNERtcChannelUser:(uint64_t)userID subStreamAudioMuted:(BOOL)muted;

/**
 * @if English
 * Occurs when a remote user enables video.
 * @note The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * @since V4.5.0
 * @param userID The ID of the user whose video streams are sent.
 * @param profile The video profile of the remote user. For more information, see {@link NERtcVideoProfileType}.
 * @endif
 * @if Chinese
 * 远端用户开启视频回调。
 * <br> 启用后，用户可以进行视频通话或直播。
 * @note 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * @since V4.5.0
 * @param userID 用户 ID，提示是哪个用户的视频流。
 * @param profile 远端用户的视频 profile 档位。详细信息请参考  {@link NERtcVideoProfileType}。
 * @endif
 */
- (void)onNERtcChannelUserVideoDidStartWithUserID:(uint64_t)userID videoProfile:(NERtcVideoProfileType)profile;

/**
 * @if English
 * Occurs when a remote user disables video.
 * @note The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * @since V4.5.0
 * @param userID The ID of a remote user.
 * @endif
 * @if Chinese
 * 远端用户停用视频回调。
 * <br> 关闭后，用户只能进行语音通话或者直播。
 * @note 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * @since V4.5.0
 * @param userID 远端用户 ID。
 * @endif
 */
- (void)onNERtcChannelUserVideoDidStop:(uint64_t)userID;

/**
 * @if English
 * Occurs when a remote user enables screen sharing by using the substream.
 * @note The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * @since V4.5.0
 * @param userID The ID of a remote user.
 * @param profile The resolution of the remote video. For more information, see {@link NERtcVideoProfileType}.
 * @endif
 * @if Chinese
 * 远端用户开启屏幕共享辅流通道的回调。
 * @note 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * @since V4.5.0
 * @param userID 远端用户 ID。
 * @param profile 远端视频分辨率等级。详细信息请参考  {@link NERtcVideoProfileType}。
 * @endif
 */
- (void)onNERtcChannelUserSubStreamDidStartWithUserID:(uint64_t)userID subStreamProfile:(NERtcVideoProfileType)profile;

/**
 * @if English
 * Occurs when a remote user stops screen sharing by using the substream.
 * @note The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * @since V4.5.0
 * @param userID The ID of a remote user.
 * @endif
 * @if Chinese
 * 远端用户停止屏幕共享辅流通道的回调。
 * @note 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * @since V4.5.0
 * @param userID 远端用户 ID。
 * @endif
 */
- (void)onNERtcChannelUserSubStreamDidStop:(uint64_t)userID;

/**
 * @if English
 * Occurs when a remote user stops or resumes sending video streams. 
 * @note The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * @since V4.5.0
 * @param userID The ID of the user whose video streams are sent.
 * @param muted The option whether to pause sending video streams.
 * @endif
 * @if Chinese
 * 远端用户暂停或恢复发送视频流回调。
 * @note 当远端用户调用 muteLocalVideo 取消或者恢复发布视频流时，SDK会触发该回调向本地用户报告远程用户的发流状况。
 * @note 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * @since V4.5.0
 * @param userID 用户 ID，提示是哪个用户的视频流。
 * @param muted 是否暂停发送视频流。
     * - YES：该用户已暂停发送视频流。
     * = NO：该用户已恢复发送视频流。
 * @endif
 */
- (void)onNERtcChannelUser:(uint64_t)userID videoMuted:(BOOL)muted;

/**
 * @if English
 * After the connection breaks down and the SDK fails to connect to the server three consecutive times.
 * @note
 * - The callback is triggered if the SDK fails to connect to the server three consecutive times after you successfully call the joinChannel method.
 * - If the SDK fails to connect to the server three consecutive times, the SDK stops retries.
 * @since V4.5.0
 * @param reason The reason why the network is disconnected. For more information, see {@link NERtcEngineErrorCode.NERtcError}.
 * @endif
 * @if Chinese
 * 网络连接中断，且 SDK 连续 3 次重连服务器失败。
 * @note
 * - SDK 在调用 joinChannel 加入房间成功后，如果和服务器失去连接且连续 3 次重连失败，就会触发该回调。
 * - 如果 SDK 在断开连接后，连续 3 次重连失败，SDK 会停止尝试重连。
 * @since V4.5.0
 * @param reason 网络连接中断原因。详细信息请查看  {@link NERtcEngineErrorCode.NERtcError}。
 * @endif
 */
- (void)onNERtcChannelDidDisconnectWithReason:(NERtcError)reason;

/**
 * @if English
 * Occurs when reconnection starts.
 * <br>If a client is disconnected from the server, the SDK starts reconnecting. The callback is triggered when the reconnection starts. For more information about the reconnection result, see onNERtcEngineRejoinChannel and onNERtcEngineDidDisconnectWithReason.
 * @since V4.5.0
 * @endif
 * @if Chinese
 * 重连开始回调。
 * <br>客户端和服务器断开连接时，SDK 会进行重连，重连开始时触发此回调。重连结果请参考 onNERtcEngineRejoinChannel 、onNERtcEngineDidDisconnectWithReason。
 * @since V4.5.0
 * @endif
 */
- (void)onNERtcChannelReconnectingStart;

/**
 * @if English
 * Occurs when a user rejoins a room.
 * If a client is disconnected from the server due to poor network quality, the SDK starts reconnecting. If the client and server are reconnected, the callback is triggered.
 * @since V4.5.0
 * @param result The result of reconnection. For more information, see {@link NERtcEngineErrorCode.NERtcError}.
 * @endif
 * @if Chinese
 * 重新加入房间回调。
 * <br>在弱网环境下，若客户端和服务器失去连接，SDK会自动重连。自动重连成功后触发此回调方法。
 * @since V4.5.0
 * @param result 重连结果。详细信息请参考 {@link NERtcEngineErrorCode.NERtcError}。
* @endif
*/
- (void)onNERtcChannelRejoinChannel:(NERtcError)result;

/**
 * @if English
 * Occurs when the first audio frame from a remote user is received.
 * @since V4.5.0
 * @param userID The ID of a remote user whose audio streams are sent.
 * @endif
 * @if Chinese
 * 已接收到远端音频首帧回调。
 * @since V4.5.0
 * @param userID 远端用户 ID，指定是哪个用户的音频流。
 * @endif
 */
- (void)onNERtcChannelFirstAudioDataDidReceiveWithUserID:(uint64_t)userID;

/**
 * @if English
 * Occurs when the first audio frame from a remote user is received.
 * If the first video frame from a remote user is displayed in the view, the callback is triggered.
 * @note The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * @since V4.5.0
 * @param userID The ID of a remote user whose video streams are sent. 
 * @endif
 * @if Chinese
 * 已接收到远端视频首帧回调。
 * <br>第一帧远端视频显示在视图上时，触发此调用。
 * @note 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * @since V4.5.0
 * @param userID  远端用户 ID，指定是哪个用户的视频流。
 * @endif
 */
- (void)onNERtcChannelFirstVideoDataDidReceiveWithUserID:(uint64_t)userID;

/**
 * @if English
 * Occurs when the first audio frame from a remote user is decoded.
 * @since V4.5.0
 * @param userID The ID of a remote user whose audio streams are sent.
 * @endif
 * @if Chinese
 * 已解码远端音频首帧的回调。
 * @since V4.5.0
 * @param userID 远端用户 ID，指定是哪个用户的音频流。
* @endif
*/
- (void)onNERtcChannelFirstAudioFrameDecoded:(uint64_t)userID;

/**
 * @if English
 * Occurs when the first video frame from a remote user is displayed.
 * If the engine receives the first frame of remote video streams, the callback is triggered. The callback allows the app to set the video canvas.
 * @note The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * @since V4.5.0
 * @param userID The ID of a remote user whose video streams are sent.
 * @param width The width of the first video frame. Unit: px.
 * @param height The height of the first video frame. Unit: px.
 * @endif
 * @if Chinese
 * 已显示首帧远端视频回调。
 * <br>引擎收到第一帧远端视频流并解码成功时，触发此调用。 App 可在此回调中设置该用户的视频画布。
 * @note 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * @since V4.5.0
 * @param userID 远端用户 ID，指定是哪个用户的视频流。
 * @param width  首帧视频宽，单位为 px。
 * @param height 首帧视频高，单位为 px。
* @endif
*/
- (void)onNERtcChannelFirstVideoFrameDecoded:(uint64_t)userID width:(uint32_t)width height:(uint32_t)height;


/**
 * @if English
 * Occurs when the content of remote SEI is received.
 * After a remote client successfully sends SEI, the local client receives a message returned by the callback.
 * @note The audio-only SDK disables this callback. If you need to use the callback, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * @since V4.5.0
 * @param userID The ID of the user who sends SEI.
 * @param message The message that contains SEI.
 * @endif
 * @if Chinese
 * 收到远端流的 SEI 内容回调。
 * <br>当远端成功发送 SEI 后，本端会收到此回调。
 * @note 纯音频 SDK 禁用该回调，如需使用请前往云信官网下载并替换成视频 SDK。
 * @since V4.5.0
 * @param userID 发送 SEI 的用户 ID。
 * @param message 对应用户的 SEI 信息。
 * @endif
 */
- (void)onNERtcChannelRecvSEIMsg:(uint64_t)userID message:(NSData *)message;


/**
 * @if English
 * Occurs when the streaming state in live streaming changes.
 * @since V4.5.0
 * @param state The streaming state in live streaming. For more information, see {@link NERtcLiveStreamStateCode}.
 * @param taskID The ID of a streaming task.
 * @param url The URL for the streaming task.
 * @endif
 * @if Chinese
 * 推流状态已改变回调。
 * @since V4.5.0
 * @param state 推流状态，详细信息请参考  {@link NERtcLiveStreamStateCode}。
 * @param taskID 推流任务 ID。
 * @param url 推流任务对应的 URL 地址。
* @endif
*/
- (void)onNERtcChannelLiveStreamState:(NERtcLiveStreamStateCode)state taskID:(NSString *)taskID url:(NSString *)url;


/**
 * @if English
 * Occurs when the system prompts the current local audio volume. 
 * This callback is disabled by default. You can enable the callback by calling the enableAudioVolumeIndication method.
 * After the callback is enabled, if a local user speaks, the SDK triggers the callback based on the time interval specified in the enableAudioVolumeIndication method. 
 * If the local user mutes the local audio by calling muteLocalAudio, the SDK no longer reports this callback.
 * @since V4.5.0
 * @param volume The audio volume. Value range: 0 to 100.
 * @endif
 * @if Chinese
 * 提示房间内本地用户瞬时音量的回调。
 * <br>该回调默认禁用。可以通过  enableAudioVolumeIndication 方法开启。
 * <br>开启后，本地用户说话，SDK 会按  enableAudioVolumeIndication 方法中设置的时间间隔触发该回调。
 * <br>如果本地用户将自己静音，即调用了 muteLocalAudio，SDK 不再报告该回调。
 * @since V4.5.0
 * @param volume 混音后的音量，范围为 0~100。
* @endif
*/
- (void)onLocalAudioVolumeIndication:(int)volume;

/**
 * @if English
 * Occurs when the system prompts the current local audio volume.
 * <br>By default, the callback is disabled. You can enable the callback by calling the {@link enableAudioVolumeIndication} method. After the callback is enabled, if a local user speaks, the SDK triggers the callback based on the time interval specified in the {@link enableAudioVolumeIndication} method.
 * <br>If the local audio is muted by calling {@link muteLocalAudioStream}, the SDK will set the volume to 0 and return to the application layer.
 * @since V4.6.10
 * @param volume  indicates the mixing audio volume. Value range: 0 to 100.
 * @param enableVad indicates whether voice activity detection is enabled.
 * @endif
 * @if Chinese
 * 提示房间内本地用户瞬时音量的回调。
 * <br>该回调默认为关闭状态。可以通过 {@link enableAudioVolumeIndication} 方法开启。开启后，本地用户说话，SDK 会按 {@link enableAudioVolumeIndication} 方法中设置的时间间隔触发该回调。
 * <br>如果本地用户将自己静音（调用了 {@link muteLocalAudioStream}），SDK 将音量设置为 0 后回调给应用层。
 * @since V4.6.10
 * @param volume  混音后的音量，范围为 0 ~ 100。
 * @param enableVad 是否检测到人声。
 * @endif
 */
- (void)onLocalAudioVolumeIndication:(int)volume withVad:(BOOL)enableVad;


/**
 * @if English
 * Occurs when the system prompts the active speaker and the audio volume.
 * By default, the callback is disabled. You can enable the callback by calling the enableAudioVolumeIndication method. After the callback is enabled, if a local user speaks, the SDK triggers the callback based on the time interval specified in the enableAudioVolumeIndication method.
 * In the array returned:
 * - If a uid is contained in the array returned in the last response but not in the array returned in the current response. The remote user with the uid does not speak by default. 
 * - If the volume is 0, the user does not speak.
 * - If the array is empty, the remote user does not speak.
 * <p>@since V4.5.0
 * @param speakers The array that contains the information about user IDs and volumes. For more information, see {@link NERtcAudioVolumeInfo}.
 * @param totalVolume The volume of the mixing audio. Value range: 0  to 100.
 * @endif
 * @if Chinese
 * 提示房间内谁正在说话及说话者瞬时音量的回调。
 * <br>该回调默认为关闭状态。可以通过 enableAudioVolumeIndication 方法开启。开启后，无论房间内是否有人说话，SDK 都会按 enableAudioVolumeIndication 方法中设置的时间间隔触发该回调。
 * <br>在返回的数组中：
 * - 如果有 uid 出现在上次返回的数组中，但不在本次返回的数组中，则默认该 uid 对应的远端用户没有说话。
 * - 如果 volume 为 0，表示该用户没有说话。
 * - 如果数组为空，则表示此时远端没有人说话。
 * <p>@since V4.5.0
 * @param speakers 每个说话者的用户 ID 和音量信息的数组。详细信息请参考 {@link NERtcAudioVolumeInfo}。
 * @param totalVolume 混音后的总音量，范围为 0~100。
 * @endif
 */
- (void)onRemoteAudioVolumeIndication:(nullable NSArray<NERtcAudioVolumeInfo*> *)speakers totalVolume:(int)totalVolume;


/**
 * @if English
 * Occurs when the published local media stream falls back to an audio-only stream due to poor network conditions or switches back to audio and video stream after the network conditions improve.
 * If you call setLocalPublishFallbackOption and set option to AUDIO_ONLY, this callback is triggered when the locally published stream falls back to audio-only mode due to poor uplink network conditions, or when the audio stream switches back to the audio and video stream after the uplink network conditions improve.
 * @since V4.5.0
 * @param isFallback The option whether the locally published stream falls back to audio-only mode or switches back to audio and video stream.
 *                   - YES: The published stream from a local client falls back to audio-only mode due to poor uplink network conditions.
 *                   - NO: The audio stream switches back to the audio and video stream after the uplink network condition improves.
 * @param streamType The type of the video stream, such as mainstream and substream. For more information, see {@link NERtcStreamChannelType}.
 * @endif
 * @if Chinese
 * 本地发布流已回退为音频流、或已恢复为音视频流回调。
 * <br>如果您调用了设置本地推流回退选项 setLocalPublishFallbackOption 接口，并将 option 设置为 kNERtcStreamFallbackOptionAudioOnly 后，当上行网络环境不理想、本地发布的媒体流回退为音频流时，或当上行网络改善、媒体流恢复为音视频流时，会触发该回调。
 * @since V4.5.0
 * @param isFallback 本地发布流已回退或已恢复。
 *                   - YES： 由于网络环境不理想，发布的媒体流已回退为音频流。
 *                   - NO：由于网络环境改善，从音频流恢复为音视频流。
 * @param streamType 对应的视频流类型，即主流或辅流。详细信息请参考 {@link NERtcStreamChannelType}。
* @endif
*/
- (void)onNERtcChannelLocalPublishFallbackToAudioOnly:(BOOL)isFallback streamType:(NERtcStreamChannelType)streamType;

/**
 * @if English
 * Occurs when the subscribed remote media stream falls back to audio-only stream due to poor network conditions or switches back to the audio and video stream after the network conditions improve.
 * If you call setRemoteSubscribeFallbackOption and set option to AUDIO_ONLY, this callback is triggered when the subscribed remote media stream falls back to audio-only mode due to poor downlink network conditions, or when the subscribed remote media stream switches back to the audio and video stream after the downlink network conditions improve.
 * @since V4.5.0
 * @param isFallback The option whether the subscribed remote media stream falls back to audio-only mode or switches back to the audio and video stream.
 *                           - YES: The subscribed remote media stream falls back to audio-only mode due to poor downstream network conditions.
 *                           - NO: The subscribed remote media stream switches back to the audio and video stream after the downstream network conditions improve.
 * @param uid The ID of a remote user.
 * @param streamType The type of the video stream, such as mainstream and substream. For more information, see {@link NERtcStreamChannelType}.
 * @endif
 * @if Chinese
 * 订阅的远端流已回退为音频流、或已恢复为音视频流回调。
 * <br>如果你调用了设置远端订阅流回退选项 setRemoteSubscribeFallbackOption 接口并将 option 设置 kNERtcStreamFallbackOptionAudioOnly 后，当下行网络环境不理想、仅接收远端音频流时，或当下行网络改善、恢复订阅音视频流时，会触发该回调。
 * @since V4.5.0
 * @param isFallback 远端订阅流已回退或恢复：
 *                           - YES： 由于网络环境不理想，订阅的远端流已回退为音频流。
 *                           - NO：由于网络环境改善，订阅的远端流从音频流恢复为音视频流。
 * @param uid 远端用户 ID。
 * @param streamType 对应的视频流类型，即主流或辅流。详细信息请参考 {@link NERtcStreamChannelType}。
* @endif
*/
- (void)onNERtcChannelRemoteSubscribeFallbackToAudioOnly:(uint64_t)uid isFallback:(BOOL)isFallback streamType:(NERtcStreamChannelType)streamType;

/**
 * @if English
 * Occurs when an error occurs.
 * The callback is triggered to report an error related to network or media during SDK runtime.
 * In most cases, the SDK cannot fix the issue and resume running. The SDK requires the app to take action or informs the user of the issue.
 * @since V4.5.0
 * @param errCode The error code. For more information, see {@link NERtcEngineErrorCode.NERtcError}.
 * @endif
 * @if Chinese
 * 发生错误回调。
 * <br>该回调方法表示 SDK 运行时出现了网络或媒体相关的错误。
 * <br>通常情况下，SDK上报的错误意味着 SDK 无法自动恢复，需要 App 干预或提示用户。
 * @since V4.5.0
 * @param errCode 错误码。详细信息请参考 {@link NERtcEngineErrorCode.NERtcError}。
 * @endif
 */
- (void)onNERtcChannelDidError:(NERtcError)errCode;

/// 音视频权限被禁止通知
/// @param isAudioBannedByServer 音频是否被服务器禁止
/// @param isVideoBannedByServer 视频是否被服务器禁止
- (void)onNERtcChannelMediaRightChangeWithAudio:(BOOL)isAudioBannedByServer
                                          video:(BOOL)isVideoBannedByServer;
/**
 * @if English
 * Occurs when a warning occurs.
 * The callback is triggered to report a warning related to network or media during SDK runtime.
 * In most cases, the SDK cannot fix the issue and resume running. The SDK requires the app to take action or informs the user of the issue.
 * @since V4.5.0
 * @param warnCode The warn code. For more information, see {@link NERtcWarning}.
 * @param msg The warning description.
 * @endif
 * @if Chinese
 * 发生警告回调。
 * <br>该回调方法表示 SDK 运行时出现了网络或媒体相关的警告。
 * <br>通常情况下，SDK上报的警告意味着 SDK 无法自动适配，需要 App 干预或提示用户。
 * @since V4.5.0
 * @param warnCode 错误码。详细信息请参考 {@link NERtcWarning}。
 * @param msg 警告描述。
 * @endif
 */
- (void)onNERtcChannelDidWarning:(NERtcWarning)warnCode msg:(NSString *)msg;

/**
 * @if English
 * Occurs when the state of the media stream relay changes.
 * @since V4.5.0
 * @param state The state of the media stream relay. For more information, see {@link NERtcChannelMediaRelayState}.
 * @param channelName The name of the destination room where the media streams are relayed.
 * @endif
 * @if Chinese
 * 跨房间媒体流转发状态发生改变回调。
 * @since V4.5.0
 * @param state 当前跨房间媒体流转发状态。详细信息请参考 {@link NERtcChannelMediaRelayState}。
 * @param channelName 媒体流转发的目标房间名。
 * @endif
 */
- (void)onNERtcChannelMediaRelayStateDidChange:(NERtcChannelMediaRelayState)state channelName:(NSString *)channelName;

/**
 * @if English
 * Returns forwarding events during the media stream relay.
 * @since V4.5.0
 * @param event The media stream relay event. For more information, see {@link NERtcChannelMediaRelayEvent}.
 * @param channelName The name of the destination room where the media streams are relayed.
 * @param error The specific error code. For more information, see {@link NERtcEngineErrorCode.NERtcError}.
 * @endif
 * @if Chinese
 * 媒体流相关转发事件回调。
 * @since V4.5.0
 * @param event 当前媒体流转发事件。详细信息请参考 {@link NERtcChannelMediaRelayEvent}。
 * @param channelName 转发的目标房间名。
 * @param error 相关错误码。详细信息请参考 {@link NERtcEngineErrorCode.NERtcError}。
 * @endif
 */
- (void)onNERtcChannelDidReceiveChannelMediaRelayEvent:(NERtcChannelMediaRelayEvent)event channelName:(NSString *)channelName error:(NERtcError)error;

@end

NS_ASSUME_NONNULL_END
