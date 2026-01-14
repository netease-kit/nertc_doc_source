package com.netease.lava.nertc.sdk;

import android.graphics.Rect;

import com.netease.lava.nertc.sdk.stats.NERtcAudioVolumeInfo;
import com.netease.lava.nertc.sdk.video.NERtcVideoStreamType;

/**
 * @if English
 * A simple implement for {@link NERtcCallbackEx}
 * @endif
 * @if Chinese
 * {@link NERtcCallbackEx} 的简单实现。
 * @endif
 */
public abstract class AbsNERtcCallbackEx implements NERtcCallbackEx {

    /**
     * @if English
     * Occurs when a user joins a room. The callback indicates that the client has already signed in.
     * @param result    A value of 0 indicates that a user joins a room. Otherwise, the user fails to join a room. For more information, see {@link NERtcConstants.ErrorCode}.
     * @param channelId The ID of the room that the client joins.
     * @param elapsed   The time consumed from calling the joinChannel method to the time when the event occurs. Unit: milliseconds.
     * @param uid       User ID.
     * @endif
     * @if Chinese
     * 加入房间回调，表示客户端已经登入服务器。
     * @param result    0 表示加入房间成功；其他值表示加入房间失败，详细错误码请参考 {@link NERtcConstants.ErrorCode}。
     * @param channelId 客户端加入的房间 ID。
     * @param elapsed   从 joinChannel 开始到发生此事件过去的时间，单位为毫秒。
     * @param uid       用户 ID。如果在 joinChannel 方法中指定了 uid，此处会返回指定的 ID; 如果未指定 uid（joinChannel 时uid=0），此处将返回云信服务器自动分配的 ID。
     * @endif
     */
    @Override
    public abstract void onJoinChannel(int result, long channelId, long elapsed, long uid);

    /**
     * @if English
     * Occurs when a user leaves a room.
     * <br>After an app calls the leaveChannel method, SDK prompts whether the app successfully exits the room.
     * @param result 0 indicates success. Other values indicate failure. For more information about error codes, see {@link NERtcConstants.ErrorCode}.
     *               When a user switches rooms, the code parameter takes NERtcConstants.ErrorCode#LEAVE_CHANNEL_FOR_SWITCH.
     * @endif
     * @if Chinese
     * 退出房间回调。
     * <br>App 调用 leaveChannel 方法后，SDK 提示 App 退出房间是否成功。 
     * @param result 0 表示成功；其他值表示退出房间失败，错误码请参考 {@link NERtcConstants.ErrorCode}。
     *               在快速切换房间时 code 为 NERtcConstants.ErrorCode#LEAVE_CHANNEL_FOR_SWITCH。
     * @endif
     */
    @Override
    public abstract void onLeaveChannel(int result);

    /**
     * @if English
     * Occurs when a remote user joins the current room.
     * <br>The callback function prompts that a remote user joins the room and returns the ID of the user that joins the room. If the user ID already exists, the remote user also receives a message that the user already joins the room, which is returned by the callback function.
     * <br>The callback function will be triggered in the following cases:
     * - A remote user joins the room by calling the joinChannel method.
     * - A remote user rejoins the room after the client is disconnected.
     * @param uid indicates the ID of the user that joins the room.
     * @endif
     * @if Chinese
     * 远端用户（通信场景）/主播（直播场景）加入当前频道回调。
     * - 通信场景下，该回调提示有远端用户加入了频道，并返回新加入用户的 ID；如果加入之前，已经有其他用户在频道中了，新加入的用户也会收到这些已有用户加入频道的回调
     * - 直播场景下，该回调提示有主播加入了频道，并返回该主播的用户 ID。如果在加入之前，已经有主播在频道中了，新加入的用户也会收到已有主播加入频道的回调。
     
     * 该回调在如下情况下会被触发：
     * - 远端用户调用 joinChannel 方法加入房间。
     * - 远端用户网络中断后重新加入房间。
     * @note
     * 直播场景下：
        * - 主播间能相互收到新主播加入频道的回调，并能获得该主播的用户 ID。
        * - 观众也能收到新主播加入频道的回调，并能获得该主播的用户 ID。
        * - 当 Web 端加入直播频道时，只要 Web 端有推流，SDK 会默认该 Web 端为主播，并触发该回调。
     * @param uid 新加入房间的远端用户 ID。
     * @endif
     */
    @Override
    public abstract void onUserJoined(long uid);

    /**
     * @if English
     * Occurs when a remote user leaves a room.
     * <br>Prompts that a remote user leaves the room or becomes disconnected.
     * <br>A user leaves a room due to the following reasons: the user exit the room or connections time out.
     * - If a user exit the room, the user will receive a message that the user leaves the room.
     * - If connections time out, the user does not receive any data packets for a period of 40 to 50 seconds, then the user becomes disconnected.
     * @param uid indicates the ID of the user that leaves the room.
     * @param reason The reason why user leaves the channel.
     * @endif
     * @if Chinese
     * 远端用户离开当前房间回调。
     * <br>提示有远端用户离开了房间（或掉线）。
     * <br>用户离开房间有两个原因，即正常离开和超时掉线：
     * - 正常离开的时候，远端用户会收到消息提示，判断用户离开房间。
     * - 超时掉线的依据是，在一定时间内（40~50s），用户没有收到对方的任何数据包，则判定为对方掉线。
     * @param uid 离开房间的远端用户 ID。
     * @param reason 离开原因。
     * - kNERtcSessionLeaveNormal(0)：正常离开。
     * - kNERtcSessionLeaveForFailOver(1)：用户断线导致离开房间。
     * - kNERTCSessionLeaveForUpdate(2)：用户因 Failover 导致离开房间，仅 SDK 内部使用。
     * - kNERtcSessionLeaveForKick(3)：用户被踢导致离开房间。
     * - kNERtcSessionLeaveTimeout(4)：用户超时退出房间。
     * @endif
     */
    @Override
    public abstract void onUserLeave(long uid, int reason);

    /**
     * @if English
     * Occurs when a remote user enables audio.
     * @param uid indicates the ID of the user that leaves the room.
     * @endif
     * @if Chinese
     * 远端用户开启音频回调。
     * @note 该回调由远端用户调用 enableLocalAudio 方法开启音频采集和发送触发。
     * @param uid   远端用户 ID。
     * @endif
     */
    @Override
    public abstract void onUserAudioStart(long uid);

    /**
     * @if English
     * Occurs when a remote user disables audio.
     * @param uid indicates the ID of the user that leaves the room.
     * @endif
     * @if Chinese
     * 远端用户停用音频回调。
     * @note 该回调由远端用户调用 enableLocalAudio 方法开启音频采集和发送触发。
     * @param uid   远端用户 ID。
     * @endif
     */
    @Override
    public abstract void onUserAudioStop(long uid);


    /**
     * @if English
     * Occurs when a remote user enables video.
     * @param uid        indicates the ID of the user that sends the video streams.
     * @param maxProfile sets the video parameters. For more information, see {@link NERtcConstants.VideoProfile}.
     * @endif
     * @if Chinese
     * 远端用户开启视频回调。
     * <br> 启用后，用户可以进行视频通话或直播。
     * @param uid  用户 ID，提示是哪个用户的视频流。
     * @param maxProfile 视频编码配置，详细信息请参考 {@link NERtcConstants.VideoProfile}。
     * @endif
     */
    @Override
    public abstract void onUserVideoStart(long uid, int maxProfile);

    /**
     * @if English
     * Occurs when a remote user disables video.
     * @param uid indicates the ID of a remote user.
     * @endif
     * @if Chinese
     * 远端用户停用视频回调。
     * <br> 关闭后，用户只能进行语音通话或者直播。
     * @param uid  远端用户 ID。
     * @endif
     */
    @Override
    public abstract void onUserVideoStop(long uid);


    /**
     * @if English
     * Indicates that connection breaks down and the SDK fails to connect to the server three consecutive times.
     * @param reason indicates the reason for network disconnection. For more information, see {@link NERtcConstants.ErrorCode}.
     * @note - The callback function is triggered if the SDK fails to connect to the server three consecutive times after the joinChannel is successfully called.
     * - If the SDK fails to connect to the server three consecutive times, the SDK stops retries.
     * @endif
     * @if Chinese
     * 网络连接中断，且 SDK 连续 3 次重连服务器失败。
     * <br><b>注意</b>：
     * - SDK 在调用 joinChannel 加入房间成功后，如果和服务器失去连接且连续 3 次重连失败，就会触发该回调。 
     * - 如果 SDK 在断开连接后，连续 3 次重连失败，SDK 会停止尝试重连。
     * @param reason 网络连接中断原因。详细信息请查看 {@link NERtcConstants.ErrorCode}。
     * @endif
     */
    @Override
    public abstract void onDisconnect(int reason);


    /**
     * @if English
     * Occurs when an error occurs.
     * <br>The callback is triggered to report an error related to network or media during SDK runtime.
     * <br>In most cases, the SDK cannot fix the issue and resume running. The SDK requires the app to take action or informs the user about the issue.
     * @param code {@link NERtcConstants.RuntimeError}
     * @endif
     * @if Chinese
     * 发生错误回调。
     * <br>该回调方法表示 SDK 运行时出现了网络或媒体相关的错误。 
     * <br>通常情况下，SDK上报的错误意味着 SDK 无法自动恢复，需要 App 干预或提示用户。
     * @param code {@link NERtcConstants.RuntimeError}
     * @endif
     */
    @Override
    public abstract void onError(int code);

    /**
     * @if English
     * Occurs when a user changes the role in live streaming.
     * <br>After the user joins a room, the user can call the setClientRole method to change roles. Then, the callback is triggered. For example, switching from host to audience, or from audience to host.
     * @param oldRole indicates the role before the change. For more information, see {@link NERtcConstants.UserRole}.
     * @param newRole indicates the role after the change. For more information, see {@link NERtcConstants.UserRole}.
     * @note In live streaming, if you join a room and successfully call this method to change roles, the following callback functions are triggered.
     * - If the role is changed from host to audience, the onClientRoleChange callback is locally triggered, and the onUserLeave callback is remotely triggered.
     * - If the role is changed from audience to host, the onClientRoleChange callback is locally triggered, and the onUserJoined callback is remotely triggered.
     * @endif
     * @if Chinese
     * 直播场景下用户角色已切换回调。
     * <br>用户加入房间后，通过 {@link NERtcEx#setClientRole()} 切换用户角色后会触发此回调。例如从主播切换为观众、从观众切换为主播。
     * <br><b>注意</b>：
     * <br>直播场景下，如果您在加入房间后调用该方法切换用户角色，调用成功后，会触发以下回调：
     * - 主播切观众，本端触发 onClientRoleChange 回调，远端触发 {@link NERtcCallback#onUserLeave()} 回调。
     * - 观众切主播，本端触发 onClientRoleChange 回调，远端触发 {@link NERtcCallback#onUserJoined()} 回调。
     * @param oldRole 切换前的角色。详细信息请参考 {@link NERtcConstants.UserRole}。
     * @param newRole 切换后的角色。详细信息请参考 {@link NERtcConstants.UserRole}。
     * @endif
     */
    @Override
    public void onClientRoleChange(int oldRole, int newRole) {
    }


    /**
     * @if English
     * Occurs when a remote user enables screen sharing substream channel.
     * @param uid        indicates the ID of a remote user.
     * @param maxProfile indicates the resolution of the remote video. For more information, see {@link NERtcConstants.VideoProfile}.
     * @endif
     * @if Chinese
     * 远端用户开启屏幕共享辅流通道的回调。
     * @param uid 远端用户 ID。
     * @param maxProfile 远端视频分辨率等级。详细信息请参考 {@link NERtcConstants.VideoProfile}。
     * @endif
     */
    @Override
    public void onUserSubStreamVideoStart(long uid, int maxProfile) {
    }

    /**
     * @if English
     * Occurs when a remote user stops screen sharing using the substream channel.
     * @param uid indicates the ID of a remote user.
     * @endif
     * @if Chinese
     * 远端用户停止屏幕共享辅流通道的回调。
     * @param uid 远端用户 ID。
     * @endif
     */
    @Override
    public void onUserSubStreamVideoStop(long uid) {
    }

    /**
     * @if English
     * Occurs when a remote user stops/resumes sending audio streams.
     * @note The callback is triggered when a remote user publishes or unpublishes an audio stream by calling muteLocalAudioStream.
     * @param uid   indicates the ID of the user whose audio streams are sent.
     * @param muted specifies whether to stop sending audio streams.
     *              - true: The user stops sending audio streams.
     *              - false: The user resumes sending audio streams.
     * @endif
     * @if Chinese
     * 远端用户暂停或恢复发送音频流的回调。
     * @note 该回调由远端用户调用 muteLocalAudioStream 方法开启或关闭音频发送触发。
     * @param uid 用户 ID，提示是哪个用户的音频流。
     * @param muted 是否停止发送音频流。
     * - true：该用户已暂停发送音频流。
     * - false：该用户已恢复发送音频流。
     * @endif
     */
    @Override
    public void onUserAudioMute(long uid, boolean muted) {
    }

    /**
     * @if English
     * Occurs when a remote user stops or resumes sending video streams.
     * @param uid   indicates the ID of the user whose video streams are sent.
     * @param muted specifies whether to stop sending video streams.
     *              - true: The user stops sending video streams.
     *              - false: The user resumes sending video streams.
     * @endif
     * @if Chinese
     * 远端用户暂停或恢复发送视频流回调。
     * @note 当远端用户调用 muteLocalVideoStream 取消或者恢复发布视频流时，SDK会触发该回调向本地用户报告远程用户的发流状况。 
     * @param uid   用户 ID，提示是哪个用户的视频流。
     * @param muted 是否停止发送视频流。
     * - true：该用户已暂停发送视频流。
     * - false：该用户已恢复发送视频流。
     * @endif
     */
    @Override
    public void onUserVideoMute(long uid, boolean muted) {
    }

    /**
     * @if English
     * Occurs when the first audio frame from a remote user is received.
     * @param uid indicates the ID of a remote user whose audio streams are sent.
     * @endif
     * @if Chinese
     * 已接收到远端音频首帧回调。
     * @param uid 远端用户 ID，指定是哪个用户的音频流。  
     * @endif
     */
    @Override
    public void onFirstAudioDataReceived(long uid) {
    }

    /**
     * @if English
     * Occurs when the first video frame from a remote user is received.
     * <br>If the first video frame from a remote user is displayed in the view, the callback is triggered.
     * @param uid indicates the ID of a remote user whose audio streams are sent.
     * @endif
     * @if Chinese
     * 已显示首帧远端视频回调。
     * <br>第一帧远端视频显示在视图上时，触发此回调。
     * @param uid  远端用户 ID，指定是哪个用户的视频流。  
     * @endif
     */
    @Override
    public void onFirstVideoDataReceived(long uid) {
    }

    /**
     * @if English
     * Occurs when the first audio frame from a remote user is decoded.
     * @param userID indicates the ID of a remote user whose audio streams are sent.
     * @endif
     * @if Chinese
     * 已解码远端音频首帧的回调。
     * @param userID 远端用户 ID，指定是哪个用户的音频流。  
     * @endif
     */
    @Override
    public void onFirstAudioFrameDecoded(long userID) {
    }

    /**
     * @if English
     * Occurs when the first video frame from a remote user is received.
     * <br>If the engine receives the first frame of remote video streams, the callback is triggered. The callback allows the app to set the video canvas.
     * @param userID indicates the ID of a remote user whose video streams are sent.
     * @param width  indicates the width of the first video frame. Unit: px.
     * @param height indicates the height of the first video frame. Unit: px.
     * @endif
     * @if Chinese
     * 已显示首帧远端视频回调。
     * <br>引擎收到第一帧远端视频流并解码成功时，触发此调用。 App 可在此回调中设置该用户的视频画布。
     * @param userID 远端用户 ID，指定是哪个用户的视频流。  
     * @param width 首帧视频宽，单位为 px。
     * @param height 首帧视频高，单位为 px。
     * @endif
     */
    @Override
    public void onFirstVideoFrameDecoded(long userID, int width, int height) {
    }

    /**
     * @if English
     * Occurs when the profile of the video streams from a remote user is updated.
     * @param uid        indicates the ID of a remote user.
     * @param maxProfile sets the video parameters. For more information, see {@link NERtcConstants.VideoProfile}.
     * @endif
     * @if Chinese
     * 远端用户视频编码配置已更新回调。
     * @param uid 远端用户 ID。
     * @param maxProfile 视频编码配置，详细信息请参考 {@link NERtcConstants.VideoProfile}。
     * @endif
     */
    @Override
    public void onUserVideoProfileUpdate(long uid, int maxProfile) {
    }

    /**
     * @if English
     * Occurs when the audio device is changed.
     * @param selected indicates the selected device. For more information, see {@link NERtcConstants.AudioDevice}.
     * @endif
     * @if Chinese
     * 语音播放设备已改变回调。
     * @param selected 选择的设备，详细信息请参考 {@link NERtcConstants.AudioDevice}。
     * @endif
     */
    @Override
    public void onAudioDeviceChanged(final int selected) {
    }

    /**
     * @if English
     * Occurs when the state of the audio device is changed.
     * @param deviceType  indicates the type of the device. For more information, see {@link NERtcConstants.AudioDeviceType}.
     * @param deviceState indicates the state of the audio device. For more information, see {@link NERtcConstants.AudioDeviceState}.
     * @endif
     * @if Chinese
     * 音频设备状态已改变回调。
     * @param deviceType 设备类型。详细信息请参考 {@link NERtcConstants.AudioDeviceType}。
     * @param deviceState 设备状态。详细信息请参考 {@link NERtcConstants.AudioDeviceState}。
     * @endif
     */
    @Override
    public void onAudioDeviceStateChange(int deviceType, int deviceState) {
    }

    /**
     * @if English
     * Occurs when the state of the video device is changed.
     * <br>The callback returns that the state of the video device changes. For example, the device is unplugged or removed. If an external camera that the device uses is unplugged, the video streaming is interrupted.
     * @param deviceState indicates the state of the audio device. For more information, see {@link NERtcConstants.VideoDeviceState}.
     * @endif
     * @if Chinese
     * 视频设备状态已改变回调。
     * <br>该回调提示系统视频设备状态发生改变，比如被拔出或移除。如果设备已使用外接摄像头采集，外接摄像头被拔开后，视频会中断。
     * @param deviceState 设备状态。详细信息请参考 {@link NERtcConstants.VideoDeviceState}。
     * @endif
     */
    @Override
    public void onVideoDeviceStageChange(int deviceState) {
    }

    /**
     * @if English
     * Occurs when the local network type is changed.
     * <br>If the connection type of the local network changes, the callback is triggered and returns the network connection type that is being used.
     * @param newConnectionType indicates the current local network type. For more information, see {@link NERtcConstants.ConnectionType}.
     * @endif
     * @if Chinese
     * 本地网络类型已改变回调。
     * <br>本地网络连接类型发生改变时，SDK 会触发该回调，并在回调中声明当前正在使用的网络连接类型。
     * @param newConnectionType 当前的本地网络类型，详细信息请参考 {@link NERtcConstants.ConnectionType}。
     * @endif
     */
    @Override
    public void onConnectionTypeChanged(int newConnectionType) {
    }

    /**
     * @if English
     * Occurs when reconnection starts.
     * <br>If a client is disconnected from the server, the SDK starts reconnecting. The callback is triggered when the reconnection starts. For more information about reconnection, see onReJoinChannel and onDisconnect.
     * @see com.netease.lava.nertc.sdk.NERtcCallbackEx#onReJoinChannel(int, long)
     * @see com.netease.lava.nertc.sdk.NERtcCallbackEx#onDisconnect(int)
     * @endif
     * @if Chinese
     * 重连开始回调。
     * <br>客户端和服务器断开连接时，SDK 会进行重连，重连开始时触发此回调。重连结果请参考 onReJoinChannel、onDisconnect。
     * @see NERtcCallbackEx#onReJoinChannel(int, long)
     * @see NERtcCallbackEx#onDisconnect(int)
     * @endif
     */
    @Override
    public void onReconnectingStart() {
    }

    /**
     * @if English
     * Occurs when a user rejoins a room.
     * <br>If a client is disconnected from the server due to poor network quality, the SDK starts reconnecting. If the client and server are reconnected, the callback is triggered.
     * @param result    A value of 0 indicates success. Other values indicate that the user fails to rejoin the room. For more information about error codes, see {@link NERtcConstants.ErrorCode}.
     * @param channelId indicates the ID of the room that the client joins.
     * @endif
     * @if Chinese
     * 重新加入房间回调。
     * <br>在弱网环境下，若客户端和服务器失去连接，SDK 会自动重连。自动重连成功后触发此回调方法。
     * @param result 0 成功；其他值表示重新加入失败，错误码请参考{@link NERtcConstants.ErrorCode}。
     * @param channelId 客户端加入的房间 ID。
     * @endif
     */
    @Override
    public void onReJoinChannel(int result, long channelId) {
    }

    /**
     * @if English
     * Occurs when the state of the operation on a local music file is changed.
     * <br>If you call the startAudioMixing method to play a mixing music file and the state of the playing operation changes, the callback is triggered.
     * @param reason {@link NERtcConstants.AudioMixingError#AUDIO_MIXING_FINISH} indicates the music file finishes playing. Other status codes indicate that playing the music file fails. For more information, see {@link NERtcConstants.AudioMixingError}.
     * @endif
     * @if Chinese
     * 本地用户的音乐文件播放状态改变回调。
     * <br>调用 startAudioMixing 播放混音音乐文件后，当音乐文件的播放状态发生改变时，会触发该回调。
     * @param reason {@link NERtcConstants.AudioMixingError#AUDIO_MIXING_FINISH} 表示正常结束；其他状态码表示播放失败，详细信息请参考 {@link NERtcConstants.AudioMixingError}。
     * @endif
     */
    @Override
    public void onAudioMixingStateChanged(int reason) {
    }

    /**
     * @if English
     * Occurs when the timestamp of a playing music file is updated.
     * <br>If you call the startAudioMixing method to play a mixing music file and the progress of the playing operation changes, the callback is triggered.
     * @param timestampMs indicates the progress of the music file playing. Unit: milliseconds.
     * @endif
     * @if Chinese
     * 本地用户的音乐文件播放进度回调。
     * <br>调用 startAudioMixing 播放混音音乐文件后，当音乐文件的播放进度改变时，会触发该回调。
     * @param timestampMs  音乐文件播放进度，单位为毫秒。
     * @endif
     */
    @Override
    public void onAudioMixingTimestampUpdate(long timestampMs) {
    }

    /**
     * @if English
     * Occurs when a music file finishes playing.
     * @param effectId indicates the ID of the specified audio effect. Each audio effect has a unique ID.
     * @endif
     * @if Chinese
     * 本地音效文件播放已结束回调。
     * @param effectId 指定音效的 ID。每个音效均有唯一的 ID
     * @endif
     */
    @Override
    public void onAudioEffectFinished(int effectId) {
    }

    /**
     * @if English
     * Occurs when the system prompts the current local audio volume.
     * <br>By default, the callback is disabled. You can enable the callback by calling the enableAudioVolumeIndication method. After the callback is enabled, if a local user speaks, the SDK triggers the callback based on the time interval specified in the enableAudioVolumeIndication method.
     * @param volume indicates the audio volume. Value range: 0 to 100.
     * @endif
     * @if Chinese
     * 提示房间内本地用户瞬时音量的回调。 
     * <br>该回调默认为关闭状态。可以通过 enableAudioVolumeIndication 方法开启。开启后，本地用户说话，SDK 会按 enableAudioVolumeIndication 方法中设置的时间间隔触发该回调。
     * @param volume 混音后的音量，范围为 0~100。
     * @endif
     */
    @Override
    public void onLocalAudioVolumeIndication(int volume) {
    }

    /**
     * @if English
     * Occurs when the system prompts the active speaker and the audio volume.
     * <br>By default, the callback is disabled. You can enable the callback by calling the enableAudioVolumeIndication method. After the callback is enabled, if a local user speaks, the SDK triggers the callback based on the time interval specified in the enableAudioVolumeIndication method.
     * <br>In the returned array:
     * - If a uid is contained in the array returned in the last response but not in the array returned in the current time. The remote user with the uid does not speak.
     * - If the volume is 0, the user does not speak.
     * - If the array is empty, the remote user does not speak.
     * @param volumeArray indicates the array that contains the information about user IDs and volumes. For more information, see {@link stats.NERtcAudioVolumeInfo}.
     * @param totalVolume indicates the volume of mixed audio. Value range: 0 to 100.
     * @endif
     * @if Chinese
     * 提示房间内谁正在说话及说话者瞬时音量的回调。
     * <br>该回调默认为关闭状态。可以通过 enableAudioVolumeIndication 方法开启。开启后，无论房间内是否有人说话，SDK 都会按 enableAudioVolumeIndication 方法中设置的时间间隔触发该回调。
     * <br>在返回的数组中：
     * - 如果有 uid 出现在上次返回的数组中，但不在本次返回的数组中，则默认该 uid 对应的远端用户没有说话。 
     * -  如果 volume 为 0，表示该用户没有说话。
     * -  如果数组为空，则表示此时远端没有人说话。
     * @param volumeArray 每个说话者的用户 ID 和音量信息的数组。详细信息请参考 {@link stats.NERtcAudioVolumeInfo}。
     * @param totalVolume 混音后的总音量，取值范围为 0~100。
     * @endif
     */
    @Override
    public void onRemoteAudioVolumeIndication(NERtcAudioVolumeInfo[] volumeArray, int totalVolume) {
    }

    /**
     * @if English
     * Occurs when the state of live streams is changed.
     * @param taskId    indicates the ID of the push task.
     * @param pushUrl   indicates the URL of the push task.
     * @param liveState indicates the state of live streams. For more information, see {@link NERtcConstants.LiveStreamState}.
     * @endif
     * @if Chinese
     * 推流状态已改变回调。
     * @param taskId 推流任务 ID。
     * @param pushUrl 推流任务对应的 URL 地址。
     * @param liveState  推流状态，详细信息请参考  {@link NERtcConstants.LiveStreamState}。
     * @endif
     */
    @Override
    public void onLiveStreamState(String taskId, String pushUrl, int liveState) {
    }

    /**
     * @if English
     * Occurs when the channel connection state is changed.
     * <br>The callback is triggered when the channel connection state is changed. The callback returns the current channel connection state and the reason why the state changes.
     * @param state         indicates the channel connection state. For more information, see {@link NERtcConstants.ConnectionState}.
     * @param reason        indicates the reason why the channel state changes. For more information, see {@link NERtcConstants.ConnectionStateChangeReason}.
     * @endif
     * @if Chinese
     * 房间连接状态已改变回调。
     * <br>该回调在房间连接状态发生改变的时候触发，并告知用户当前的房间连接状态和引起房间状态改变的原因。
     * @param state 当前的房间连接状态。详细信息请参考 {@link NERtcConstants.ConnectionState}。
     * @param reason 引起当前房间连接状态发生改变的原因。详细信息请参考 {@link NERtcConstants.ConnectionStateChangeReason}。
     * @endif
     */
    @Override
    public void onConnectionStateChanged(int state, int reason) {
    }

    /**
     * @if English
     * Occurs when the camera focus position changes.
     * <br>The callback indicates that the camera focus position changes.
     * <br>The callback is triggered if a local user calls the setCameraFocusPosition method to change focus position.
     * @param rect indicates the new focus position.
     * @endif
     * @if Chinese
     * 摄像头对焦区域已改变回调。
     * <br>该回调表示相机的对焦区域发生了改变。
     * <br>该回调是由本地用户调用 setCameraFocusPosition 方法改变对焦位置触发的。
     * @param rect 新的对焦区域位置。
     * @endif
     */
    @Override
    public void onCameraFocusChanged(Rect rect) {
    }

    /**
     * @if English
     * Occurs when the camera exposure position changes.
     * <br>The callback is triggered if a local user calls the setCameraExposurePosition method to change the exposure position.
     * @param rect indicates the new exposure position.
     * @endif
     * @if Chinese
     * 摄像头曝光区域已改变回调。
     * <br>该回调是由本地用户调用 setCameraExposurePosition 方法改变曝光位置触发的。
     * @param rect 新的曝光区域位置。
     * @endif
     */
    @Override
    public void onCameraExposureChanged(Rect rect) {
    }

    /**
     * @if English
     * Occurs when the content of remote Supplemental Enhancement Information (SEI) is received.
     * <br>After a remote client successfully sends SEI, the local client receives a message returned by the callback.
     * @param userID indicates the ID of the user that sends SEI.
     * @param seiMsg indicates the message that contains SEI.
     * @see NERtcEx#sendSEIMsg(String)
     * @endif
     * @if Chinese
     * 收到远端流的 SEI 内容回调。
     * <br>当远端成功发送 SEI 后，本端会收到此回调。
     * @param userID 发送 SEI 的用户 ID。
     * @param seiMsg 对应用户的 SEI 信息。
     * @see NERtcEx#sendSEIMsg(String)
     * @endif
     */
    @Override
    public void onRecvSEIMsg(long userID, String seiMsg) {
    }


    /**
     * @if English
     * Occurs when displaying the state of audio recording.
     * @param code     indicates the status code of the audio recording. For more information, see {@link NERtcConstants.AudioRecordingCode}.
     * @param filePath indicates the path based on which the recording file is stored.
     * @endif
     * @if Chinese
     * 音频录制状态回调。
     * @param code     音频录制状态码。详细信息请参考 {@link NERtcConstants.AudioRecordingCode}。
     * @param filePath 音频录制文件保存路径。
     * @endif
     */
    @Override
    public void onAudioRecording(int code, String filePath) {
    }


    /**
     * @if English
     * Reports an error.
     * <br>The callback is triggered to report a warning related to network or media during SDK runtime.
     * <br>In most cases, the app ignores the warning message and the SDK resumes running.
     * @param code indicates the waring code. {@link NERtcConstants.WarningCode}
     * @endif
     * @if Chinese
     * 发生警告回调。
     * <br>该回调方法表示 SDK 运行时出现了网络或媒体相关的警告。 
     * <br>通常情况下，App 可以忽略 SDK 上报的警告信息，SDK 会自动恢复。
     * @param code 警告码。{@link NERtcConstants.WarningCode}
     * @endif
     */
    @Override
    public void onWarning(int code) {
    }

    /**
     * @if English
     * Occurs when the state of the media stream relay changes.
     * @param state       indicates the state of the media stream relay. For more information, see {@link NERtcConstants.ChannelMediaRelayState}.
     * @param channelName the name of the destination room where the media streams are forwarded.
     * @endif
     * @if Chinese
     * 跨房间媒体流转发状态发生改变回调。
     * @param state     当前跨房间媒体流转发状态。详细信息请参考 {@link NERtcConstants.ChannelMediaRelayState}。
     * @param channelName 媒体流转发的目标房间名。
     * @endif
     */
    @Override
    public void onMediaRelayStatesChange(int state, String channelName) {
    }

    /**
     * @if English
     * Reports events during the media stream relay.
     * @param event       indicates the media stream relay event. For more information, see {@link NERtcConstants.ChannelMediaRelayEvent}.
     * @param code        indicates an error code. For more information, see {@link NERtcConstants.ErrorCode}.
     * @param channelName the name of the destination room where the media streams are forwarded.
     * @endif
     * @if Chinese
     * 媒体流相关转发事件回调。
     * @param event     当前媒体流转发事件。详细信息请参考 {@link NERtcConstants.ChannelMediaRelayEvent}。
     * @param code      相关错误码。详细信息请参考 {@link NERtcConstants.ErrorCode}。
     * @param channelName 媒体流转发的目标房间名。
     * @endif
     */
    @Override
    public void onMediaRelayReceiveEvent(int event, int code, String channelName) {
    }


    /**
     * @if English
     * Occurs when the published local media stream falls back to an audio-only stream due to poor network conditions or switches back to audio and video stream after the network conditions improve.
     * <br>If you call setLocalPublishFallbackOption and set the option to AUDIO_ONLY, this callback is triggered when the locally published stream falls back to the audio-only mode due to poor upstream network conditions, or when the audio stream switches back to the audio and video stream after the upstream network conditions improve.
     * @param isFallback indicates that the locally published stream falls back to the audio-only mode or switches back to audio and video stream.
     *                   - true: The locally published stream falls back to the audio-only mode due to poor upstream network conditions.
     *                   - false: The audio stream switches back to the audio and video stream after the upstream network conditions improve.
     * @param streamType indicates the type of the video stream, such as mainstream and secondary stream.
     * @since V4.3.0
     * @endif
     * @if Chinese
     * 本地发布流已回退为音频流、或已恢复为音视频流回调。
     * <br>如果您调用了设置本地推流回退选项 setLocalPublishFallbackOption 接口，并将 option 设置为 AUDIO_ONLY 后，当上行网络环境不理想、本地发布的媒体流回退为音频流时，或当上行网络改善、媒体流恢复为音视频流时，会触发该回调。 
     * @since V4.3.0
     * @param isFallback 本地发布流已回退或已恢复。
     *                    - true： 由于网络环境不理想，发布的媒体流已回退为音频流。
     *                    - false：由于网络环境改善，从音频流恢复为音视频流。
     * @param streamType 对应的视频流类型，即主流或辅流。
     * @endif
     */
    @Override
    public void onLocalPublishFallbackToAudioOnly(boolean isFallback, NERtcVideoStreamType streamType) {
    }


    /**
     * @if English
     * Occurs when the subscribed remote media stream falls back to audio-only stream due to poor network conditions or switches back to video stream after the network conditions improve.
     * <br>If you call setRemoteSubscribeFallbackOption and set the option to AUDIO_ONLY, this callback is triggered when the subscribed remote media stream falls back to the audio-only mode due to poor downstream network conditions, or when the subscribed remote media stream switches back to the audio and video stream after the downstream network conditions improve.
     * @since V4.3.0
     * @param uid        indicates the ID of a remote user.
     * @param isFallback indicates that the subscribed remote media stream falls back to the audio-only mode or switches back to the audio and video stream.
     *                   - true: The subscribed remote media stream falls back to the audio-only mode due to poor downstream network conditions.
     *                   - false: The subscribed remote media stream switches back to the audio and video stream after the downstream network conditions improve.
     * @param streamType indicates the type of the video stream, such as mainstream and secondary stream.
     * @endif
     * @if Chinese
     * 订阅的远端流已回退为音频流、或已恢复为音视频流回调。
     * <br>如果你调用了设置远端订阅流回退选项 setRemoteSubscribeFallbackOption 接口并将 option 设置 AUDIO_ONLY 后，当下行网络环境不理想、仅接收远端音频流时，或当下行网络改善、恢复订阅音视频流时，会触发该回调。
     * @since V4.3.0
     * @param uid        远端用户的 ID。
     * @param isFallback 远端订阅流已回退或恢复：
     *                      - true： 由于网络环境不理想，订阅的远端流已回退为音频流。
     *                      - false：由于网络环境改善，订阅的远端流从音频流恢复为音视频流。
     * @param streamType 对应的视频流类型，即主流或辅流。
     * @endif
     */
    @Override
    public void onRemoteSubscribeFallbackToAudioOnly(long uid, boolean isFallback, NERtcVideoStreamType streamType) {

    }

    /**
     * @if English 
     * Reports the last mile network quality of the local user once every two seconds before the user joins the channel.
     * <br> After the application calls the startLastmileProbeTest method, this callback reports once every five seconds the uplink and downlink last mile network conditions of the local user before the user joins the channel.
     * @since V4.5.0
     * @param quality       The last mile network quality.
     *                      - QUALITY_UNKNOWN(0)：Unknown network quality.
     *                      - QUALITY_EXCELLENT(1)：Excellent network quality. 
     *                      - QUALITY_GOOD(2)：Good network quality is close to the excellent level but has the bitrate is lower an excellent network.
     *                      - QUALITY_POOR(3)：Poor network does not affect communication.
     *                      - QUALITY_BAD(4)：Users can communicate with each other without smoothness.
     *                      - QUALITY_VBAD(5)：The network quality is very poor. Basically users are unable to communicate. 
     *                      - QUALITY_DOWN(6)：Users are unable to communicate with each other.
     * @endif
     * @if Chinese
     * 通话前网络上下行 last mile 质量状态回调。
     * <br>该回调描述本地用户在加入房间前的 last mile 网络探测的结果，以打分形式描述上下行网络质量的主观体验，您可以通过该回调预估本地用户在音视频通话中的网络体验。
     * <br>在调用 startLastmileProbeTest 之后，SDK 会在约 5 秒内返回该回调。
     * @since V4.5.0
     * @param quality       网络上下行质量，基于上下行网络的丢包率和抖动计算，探测结果主要反映上行网络的状态。
     *                      - QUALITY_UNKNOWN(0)：质量未知
     *                      - QUALITY_EXCELLENT(1)：质量极好
     *                      - QUALITY_GOOD(2)：用户主观感觉和极好差不多，但码率可能略低于极好
     *                      - QUALITY_POOR(3)：用户主观感受有瑕疵但不影响沟通
     *                      - QUALITY_BAD(4)：勉强能沟通但不顺畅
     *                      - QUALITY_VBAD(5)：网络质量非常差，基本不能沟通
     *                      - QUALITY_DOWN(6)：完全无法沟通
     * @endif
     */
    @Override
    public void onLastmileQuality(int quality) {
    }

    /** 
     * @if English 
     * Reports the last-mile network probe result.
     * <br>This callback describes a detailed last-mile network detection report of a local user before joining a channel. The report provides objective data about the upstream and downstream network quality, including network jitter and packet loss rate.  You can use the report to objectively predict the network status of local users during an audio and video call. 
     * <br>The SDK triggers this callback within 30 seconds after the app calls the startLastmileProbeTest method.
     * @since V4.5.0
     * @param result        The uplink and downlink last-mile network probe test result. For more information, see {@link LastmileProbeResult}.
     * @endif
     * @if Chinese
     * 通话前网络上下行 Last mile 质量探测报告回调。
     * <br>该回调描述本地用户在加入房间前的 last mile 网络探测详细报告，报告中通过客观数据反馈上下行网络质量，包括网络抖动、丢包率等数据。您可以通过该回调客观预测本地用户在音视频通话中的网络状态。
     * <br>在调用 startLastmileProbeTest 之后，SDK 会在约 30 秒内返回该回调。
     * @since V4.5.0
     * @param result        上下行 Last mile 质量探测结果。详细信息请参考 {@link LastmileProbeResult}。
     * @endif
     */
    @Override
    public void onLastmileProbeResult(LastmileProbeResult result) {

    }



}
