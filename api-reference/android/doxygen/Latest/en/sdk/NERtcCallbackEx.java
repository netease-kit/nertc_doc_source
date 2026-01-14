/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk;

import android.graphics.Rect;

import com.netease.lava.nertc.sdk.stats.NERtcAudioVolumeInfo;
import com.netease.lava.nertc.sdk.video.NERtcVideoStreamType;
import com.netease.lava.nertc.sdk.watermark.NERtcVideoWatermarkConfig;

/** NERtcCallbackEx*/
public interface NERtcCallbackEx extends NERtcCallback {


    /**
     * @if English
     * Occurs when a remote user enables screen sharing substream channel.
     * @param uid           indicates the ID of a remote user.
     * @param maxProfile    indicates the resolution of the remote video. For more information, see {@link NERtcConstants.VideoProfile}.
     * @endif
     * @if Chinese
     * 远端用户开启屏幕共享辅流通道的回调。
     * @param uid           远端用户 ID。
     * @param maxProfile    远端视频分辨率等级。详细信息请参考 {@link NERtcConstants.VideoProfile}。
     * @endif
     */
    void onUserSubStreamVideoStart(long uid,int maxProfile);

    /**
     * @if English
     * Occurs when a remote user stops screen sharing substream channel.
     * @param uid  indicates the ID of a remote user.
     * @endif
     * @if Chinese
     * 远端用户停止屏幕共享辅流通道的回调。
     * @param uid  远端用户 ID。
     * @endif
     */
    void onUserSubStreamVideoStop(long uid);

    /**
     * @if English
     * Occurs when a remote user stops/resumes sending audio streams.
     * @param uid           indicates the ID of the user whose audio streams are sent.
     * @param muted         specifies whether to stop sending audio streams.
     *                      - true: The user stops sending audio streams.
     *                      - false: The user resumes sending audio streams.
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
    void onUserAudioMute(long uid, boolean muted);

    /**
     * @if English
     * Occurs when a remote user stops/resumes sending video streams. 
     * @param uid           indicates the ID of the user whose video streams are sent.
     * @param muted         specifies whether to stop sending video streams.
     *                      - true: The user stops sending video streams.
     *                      - false: The user resumes sending video streams.
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
    void onUserVideoMute(long uid, boolean muted);

    /**
     * @if English
     * Occurs when the first audio frame from a remote user is received.
     * @param uid           indicates the ID of a remote user whose audio streams are sent.
     * @endif
     * @if Chinese
     * 已接收到远端音频首帧回调。
     * @param uid 远端用户 ID，指定是哪个用户的音频流。  
     * @endif
     */
    void onFirstAudioDataReceived(long uid);

    /**
     * @if English
     * Occurs when the first video frame from a remote user is received.
     * <br>If the first video frame from a remote user is displayed in the view, the callback is triggered.
     * @param uid           indicates the ID of a remote user whose audio streams are sent.  
     * @endif
     * @if Chinese
     * 已显示首帧远端视频回调。
     * <br>第一帧远端视频显示在视图上时，触发此回调。
     * @param uid  远端用户 ID，指定是哪个用户的视频流。  
     * @endif
     */
    void onFirstVideoDataReceived(long uid);

    /**
     * @if English
     * Occurs when the first audio frame from a remote user is decoded.
     * @param userID        indicates the ID of a remote user whose audio streams are sent.  
     * @endif
     * @if Chinese
     * 已解码远端音频首帧的回调。
     * @param userID 远端用户 ID，指定是哪个用户的音频流。  
     * @endif
     */
    void onFirstAudioFrameDecoded(long userID);

    /**
     * @if English
     * Occurs when the first video frame from a remote user is received.
     * <br>If the engine receives the first frame of remote video streams, the callback is triggered. The callback allows the app to set the video canvas.
     * @param userID        indicates the ID of a remote user whose video streams are sent.  
     * @param width         indicates the width of the first video frame. Unit: px.
     * @param height        indicates the height of the first video frame. Unit: px.
     * @endif
     * @if Chinese
     * 已显示首帧远端视频回调。
     * <br>引擎收到第一帧远端视频流并解码成功时，触发此调用。 App 可在此回调中设置该用户的视频画布。
     * @param userID 远端用户 ID，指定是哪个用户的视频流。  
     * @param width 首帧视频宽，单位为 px。
     * @param height 首帧视频高，单位为 px。
     * @endif
     */
    void onFirstVideoFrameDecoded(long userID,int width, int height);

    /**
     * @if English
     * Occurs when the profile of the video streams from a remote user is updated.
     * @param uid           indicates the ID of a remote user.
     * @param maxProfile    sets the video parameters. For more information, see {@link NERtcConstants.VideoProfile}.
     * @endif
     * @if Chinese
     * 远端用户视频编码配置已更新回调。
     * @param uid 远端用户 ID。
     * @param maxProfile 视频编码配置，详细信息请参考 {@link NERtcConstants.VideoProfile}。
     * @endif
     */
    void onUserVideoProfileUpdate(long uid, int maxProfile);

    /**
     * @if English
     * Occurs when the audio device is changed.
     * @param selected      indicates the selected device. For more information, see {@link NERtcConstants.AudioDevice}.
     * @endif
     * @if Chinese
     * 语音播放设备已改变回调。
     * @param selected 选择的设备，详细信息请参考 {@link NERtcConstants.AudioDevice}。
     * @endif
     */
    void onAudioDeviceChanged(final int selected);

    /**
     * @if English
     * Occurs when the state of the audio device is changed.
     * @param deviceType    indicates the type of the device. For more information, see {@link NERtcConstants.AudioDeviceType}.
     * @param deviceState   indicates the state of the audio device. For more information, see {@link NERtcConstants.AudioDeviceState}.
     * @endif
     * @if Chinese
     * 音频设备状态已改变回调。
     * @param deviceType 设备类型。详细信息请参考 {@link NERtcConstants.AudioDeviceType}。
     * @param deviceState 设备状态。详细信息请参考 {@link NERtcConstants.AudioDeviceState}。
     * @endif
     */
    void onAudioDeviceStateChange(int deviceType,int deviceState);

    /**
     * @if English
     * Occurs when the state of the video device is changed.
     * <br>The callback returns that the state of the video device changes. For example, the device is unplugged or removed. If an external camera that the device uses is unplugged, the video streaming is interrupted.
     * @param deviceState   indicates the state of the audio device. For more information, see {@link NERtcConstants.VideoDeviceState}.
     * @endif
     * @if Chinese
     * 视频设备状态已改变回调。
     * <br>该回调提示系统视频设备状态发生改变，比如被拔出或移除。如果设备已使用外接摄像头采集，外接摄像头被拔开后，视频会中断。
     * @param deviceState 设备状态。详细信息请参考 {@link NERtcConstants.VideoDeviceState}。
     * @endif
     */
    void onVideoDeviceStageChange(int deviceState);

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
    void onConnectionTypeChanged(int newConnectionType);

    /**
     * @if English
     * Occurs when reconnection starts.
     * <br>If a client is disconnected from the server, the SDK starts reconnecting. The callback is triggered when the reconnection starts. For more information about reconnection, see onReJoinChannel and onDisconnect.
     * @see NERtcCallbackEx#onReJoinChannel(int, long)
     * @see NERtcCallbackEx#onDisconnect(int)
     * @endif
     * @if Chinese
     * 重连开始回调。
     * <br>客户端和服务器断开连接时，SDK 会进行重连，重连开始时触发此回调。重连结果请参考 onReJoinChannel、onDisconnect。
     * @see NERtcCallbackEx#onReJoinChannel(int, long)
     * @see NERtcCallbackEx#onDisconnect(int)
     * @endif
     */
    void onReconnectingStart();

    /**
     * @if English
     * Occurs when a user rejoins a room.
     * <br>If a client is disconnected from the server due to poor network quality, the SDK starts reconnecting. If the client and server are reconnected, the callback is triggered.
     * @param result        A value of 0 indicates success. Other values indicate that the user fails to rejoin the room. For more information about error codes, see {@link NERtcConstants.ErrorCode}.
     * @param channelId     indicates the ID of the room that the client joins.
     * @endif
     * @if Chinese
     * 重新加入房间回调。
     * <br>在弱网环境下，若客户端和服务器失去连接，SDK 会自动重连。自动重连成功后触发此回调方法。
     * @param result 0 成功；其他值表示重新加入失败，错误码请参考{@link NERtcConstants.ErrorCode}。
     * @param channelId 客户端加入的房间 ID。
     * @endif
     */
    void onReJoinChannel(int result, long channelId);

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
    void onAudioMixingStateChanged(int reason);

    /**
     * @if English
     * Occurs when the timestamp of a playing music file is updated.
     * <br>If you call the startAudioMixing method to play a mixing music file and the progress of the playing operation changes, the callback is triggered.
     * @param timestampMs   indicates the progress of the music file playing. Unit: milliseconds.
     * @endif
     * @if Chinese
     * 本地用户的音乐文件播放进度回调。
     * <br>调用 startAudioMixing 播放混音音乐文件后，当音乐文件的播放进度改变时，会触发该回调。
     * @param timestampMs  音乐文件播放进度，单位为毫秒。
     * @endif
     */
    void onAudioMixingTimestampUpdate(long timestampMs);

    /**
     * @if English
     * <br>Occurs when a music file finishes playing.
     * @param effectId      indicates the ID of the specified audio effect. Each audio effect has a unique ID.
     * @endif
     * @if Chinese
     * <br>本地音效文件播放已结束回调。
     * @param effectId 指定音效的 ID。每个音效均有唯一的 ID
     * @endif
     */
    void onAudioEffectFinished(int effectId);

    /**
     * @if English
     * Occurs when the system prompts the current local audio volume. 
     * <br>By default, the callback is disabled. You can enable the callback by calling the enableAudioVolumeIndication method. After the callback is enabled, if a local user speaks, the SDK triggers the callback based on the time interval specified in the enableAudioVolumeIndication method.
     * @param volume  indicates the audio volume. Value range: 0 to 100.
     * @endif
     * @if Chinese
     * 提示房间内本地用户瞬时音量的回调。 
     * <br>该回调默认为关闭状态。可以通过 enableAudioVolumeIndication 方法开启。开启后，本地用户说话，SDK 会按 enableAudioVolumeIndication 方法中设置的时间间隔触发该回调。
     * @param volume  混音后的音量，范围为 0~100。
     * @endif
     */
    void onLocalAudioVolumeIndication(int volume);

    /**
     * @if English
     * Occurs when the system prompts the current local audio volume.
     * <br>By default, the callback is disabled. You can enable the callback by calling the {@link NERtcEx#enableAudioVolumeIndication()} method. After the callback is enabled, if a local user speaks, the SDK triggers the callback based on the time interval specified in the {@link NERtcEx#enableAudioVolumeIndication()} method.
     * <br>If the local audio is muted by calling {@link NERtcEx#muteLocalAudioStream()}, the SDK will set the volume to 0 and return to the application layer.
     * since V4.6.10
     * @param volume  indicates the audio volume. Value range: 0 to 100.
     * @param vadFlag indicates whether voice activity detection is enabled.
     * @endif
     * @if Chinese
     * 提示房间内本地用户瞬时音量的回调。
     * <br>该回调默认为关闭状态。可以通过 {@link NERtcEx#enableAudioVolumeIndication()} 方法开启。开启后，本地用户说话，SDK 会按 {@link NERtcEx#enableAudioVolumeIndication()} 方法中设置的时间间隔触发该回调。
     * <br>如果本地用户将自己静音（调用了 {@link NERtcEx#muteLocalAudioStream()}），SDK 将音量设置为 0 后回调给应用层。
     * @since V4.6.10
     * @param volume  混音后的音量，范围为 0 ~ 100。
     * @param vadFlag 是否检测到人声。
     * @endif
     */
    void onLocalAudioVolumeIndication(int volume, boolean vadFlag);

    /**
     * @if English
     * Occurs when the system prompts the active speaker and the audio volume.
     * <br>By default, the callback is disabled. You can enable the callback by calling the enableAudioVolumeIndication method. After the callback is enabled, if a local user speaks, the SDK triggers the callback based on the time interval specified in the enableAudioVolumeIndication method.
     * <br>In the returned array:
     - If a uid is contained in the array returned in the last response but not in the array returned in the current time. The remote user with the uid does not speak. 
     - If the volume is 0, the user does not speak.
     - If the array is empty, the remote user does not speak.
     * @param volumeArray  indicates the array that contains the information about user IDs and volumes. For more information, see {@link stats.NERtcAudioVolumeInfo}.
     * @param totalVolume  indicates the volume of mixed audio. Value range: 0 to 100.
     * @endif
     * @if Chinese
     * 提示房间内谁正在说话及说话者瞬时音量的回调。
     * <br>该回调默认为关闭状态。可以通过 enableAudioVolumeIndication 方法开启。开启后，无论房间内是否有人说话，SDK 都会按 enableAudioVolumeIndication 方法中设置的时间间隔触发该回调。
     * <br>在返回的数组中：
     * - 如果有 uid 出现在上次返回的数组中，但不在本次返回的数组中，则默认该 uid 对应的远端用户没有说话。 
     * - 如果 volume 为 0，表示该用户没有说话。
     * - 如果数组为空，则表示此时远端没有人说话。
     * @param volumeArray 每个说话者的用户 ID 和音量信息的数组。详细信息请参考 {@link stats.NERtcAudioVolumeInfo}。
     * @param totalVolume 混音后的总音量，取值范围为 0~100。
     * @endif
     */
    void onRemoteAudioVolumeIndication(NERtcAudioVolumeInfo[] volumeArray, int totalVolume);


    /**
     * @if English
     * Occurs when the state of live streams is changed.
     * @param taskId        indicates the ID of the push task.
     * @param pushUrl       indicates the URL of the push task.
     * @param liveState     indicates the state of live streams. For more information, see {@link NERtcConstants.LiveStreamState}.
     * @endif
     * @if Chinese
     * 推流状态已改变回调。
     * @param taskId 推流任务 ID。
     * @param pushUrl 推流任务对应的 URL 地址。
     * @param liveState  推流状态，详细信息请参考  {@link NERtcConstants.LiveStreamState}。
     * @endif
     */
    void onLiveStreamState(String taskId, String pushUrl, int liveState);

    /**
     * @if English
     * Occurs when the channel connection state is changed.
     * <br>The callback is triggered when the channel connection state is changed. The callback returns the current channel connection state and the reason why the state changes.
     * @param state         indicates the channel connection state. For more information, see {@link NERtcConstants.ConnectionState}.
     * @param reason        indicates the reason why the channel state changes. For more information, see {@link NERtcConstants.ConnectionStateChangeReason}.
     * @endif
     * @if Chinese
     * 房间连接状态已改变回调。
     * <br>该回调在房间连接状态发生改变的时候触发，并告知用户当前的房间连接状态和引起房间状态改变的原因。
     * @param state 当前的房间连接状态。详细信息请参考 {@link NERtcConstants.ConnectionState}。
     * @param reason 引起当前房间连接状态发生改变的原因。详细信息请参考 {@link NERtcConstants.ConnectionStateChangeReason}。
     * @endif
     */
    void onConnectionStateChanged(int state,int reason);

    /**
     * @if English
     * Occurs when the camera focus position changes.
     * <br>The callback indicates that the camera focus position changes.
     * <br>The callback is triggered if a local user calls the setCameraFocusPosition method to change focus position.
     * @param rect          indicates the new focus position.
     * @endif
     * @if Chinese
     * 摄像头对焦区域已改变回调。
     * <br>该回调表示相机的对焦区域发生了改变。
     * <br>该回调是由本地用户调用 setCameraFocusPosition 方法改变对焦位置触发的。
     * @param rect 新的对焦区域位置。
     * @endif
     */
    void onCameraFocusChanged(Rect rect);

    /**
     * @if English
     * Occurs when the camera exposure position changes.
     * <br>The callback is triggered if a local user calls the setCameraExposurePosition method to change the exposure position.
     * @param rect          indicates the new exposure position.
     * @endif
     * @if Chinese
     * 摄像头曝光区域已改变回调。
     * <br>该回调是由本地用户调用 setCameraExposurePosition 方法改变曝光位置触发的。
     * @param rect 新的曝光区域位置。
     * @endif
     */
    void onCameraExposureChanged(Rect rect);

    /**
     * @if English
     * Occurs when the content of remote Supplemental Enhancement Information (SEI) is received.
     * <br>After a remote client successfully sends SEI, the local client receives a message returned by the callback.
     * @param userID        indicates the ID of the user that sends SEI.
     * @param seiMsg        indicates the message that contains SEI.
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
    void onRecvSEIMsg(long userID, String seiMsg);


    /**
     * @if English
     * Occurs when displaying the state of audio recording.
     * @param code          indicates the status code of the audio recording. For more information, see {@link NERtcConstants.AudioRecordingCode}.
     * @param filePath      indicates the path based on which the recording file is stored.
     * @endif
     * @if Chinese
     * 音频录制状态回调。
     * @param code     音频录制状态码。详细信息请参考 {@link NERtcConstants.AudioRecordingCode}。
     * @param filePath 音频录制文件保存路径。
     * @endif
     */
    void onAudioRecording(int code, String filePath);

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
    void onError(int code);

    /**
     * @if English
     * Reports an error.
     * <br>The callback is triggered to report a warning related to network or media during SDK runtime. 
     * <br>In most cases, the app ignores the warning message and the SDK resumes running.
     * @param code          indicates the waring code. {@link NERtcConstants.WarningCode}
     * @endif
     * @if Chinese
     * 发生警告回调。
     * <br>该回调方法表示 SDK 运行时出现了网络或媒体相关的警告。 
     * <br>通常情况下，App 可以忽略 SDK 上报的警告信息，SDK 会自动恢复。
     * @param code 警告码。{@link NERtcConstants.WarningCode}
     * @endif
     */
    void onWarning(int code);

    /**
     * @if English
     * Occurs when the state of the media stream relay changes.
     * @param state         indicates the state of the media stream relay. For more information, see {@link NERtcConstants.ChannelMediaRelayState}.
     * @param channelName   the name of the destination room where the media streams are forwarded.
     * @endif
     * @if Chinese 
     * 跨房间媒体流转发状态发生改变回调。
     * @param state         当前跨房间媒体流转发状态。详细信息请参考 {@link NERtcConstants.ChannelMediaRelayState}。
     * @param channelName   媒体流转发的目标房间名。
     * @endif
     */
    void onMediaRelayStatesChange(int state, String channelName);

    /**
     * @if English 
     * Reports events during the media stream relay.
     * @param event         The media stream relay event. For more information, see {@link NERtcConstants.ChannelMediaRelayEvent}.
     * @param code          indicates an error code. For more information, see {@link NERtcConstants.ChannelMediaRelayState}.
     * @param channelName   the name of the destination room where the media streams are forwarded.
     * @endif
     * @if Chinese
     * 跨房间媒体流转发状态发生改变回调。
     * @param event         当前媒体流转发事件。详细信息请参考 {@link NERtcConstants.ChannelMediaRelayEvent}。
     * @param code          当前跨房间媒体流转发状态。详细信息请参考 {@link NERtcConstants.ChannelMediaRelayState}。
     * @param channelName   媒体流转发的目标房间名。
     * @endif
     */
    void onMediaRelayReceiveEvent(int event, int code, String channelName);


    /**
     * @if English
     * Occurs when the published local media stream falls back to an audio-only stream due to poor network conditions or switches back to audio and video stream after the network conditions improve.
     * If you call setLocalPublishFallbackOption and set the option to AUDIO_ONLY, this callback is triggered when the locally published stream falls back to the audio-only mode due to poor upstream network conditions, or when the audio stream switches back to the audio and video stream after the upstream network conditions improve. 
     * @since V4.3.0
     * @param isFallback indicates that the locally published stream falls back to the audio-only mode or switches back to audio and video stream.
     *                    - true: The locally published stream falls back to the audio-only mode due to poor upstream network conditions.
     *                    - false: The audio stream switches back to the audio and video stream after the upstream network conditions improve.
     * @param streamType indicates the type of the video stream, such as substream and substream.
     * @endif
     * @if Chinese 
     * 本地发布流已回退为音频流、或已恢复为音视频流回调。
     * 如果您调用了设置本地推流回退选项 setLocalPublishFallbackOption 接口，并将 option 设置为 AUDIO_ONLY 后，当上行网络环境不理想、本地发布的媒体流回退为音频流时，或当上行网络改善、媒体流恢复为音视频流时，会触发该回调。
     * @since V4.3.0
     * @param isFallback 本地发布流已回退或已恢复。
     *                    - true: 由于网络环境不理想，发布的媒体流已回退为音频流。
     *                    - false: 由于网络环境改善，从音频流恢复为音视频流。
     * @param streamType 对应的视频流类型，即主流或辅流。
     * @endif
     */
    void onLocalPublishFallbackToAudioOnly(boolean isFallback, NERtcVideoStreamType streamType);


    /**
     * @if English
     * Occurs when the subscribed remote media stream falls back to audio-only stream due to poor network conditions or switches back to video stream after the network conditions improve.
     * <br>If you call setRemoteSubscribeFallbackOption and set the option to AUDIO_ONLY, this callback is triggered when the subscribed remote media stream falls back to the audio-only mode due to poor downstream network conditions, or when the subscribed remote media stream switches back to the audio and video stream after the downstream network conditions improve.
     * @since V4.3.0
     * @param uid           indicates the ID of a remote user.
     * @param isFallback    indicates that the subscribed remote media stream falls back to the audio-only mode or switches back to the audio and video stream.
     *                      - true: The subscribed remote media stream falls back to the audio-only mode due to poor downstream network conditions.
     *                      - false: The subscribed remote media stream switches back to the audio and video stream after the downstream network conditions improve.
     * @param streamType    indicates the type of the video stream, such as mainstream and substream.
     * @endif
     * @if Chinese 
     * 订阅的远端流已回退为音频流、或已恢复为音视频流回调。
     * <br>如果你调用了设置远端订阅流回退选项 setRemoteSubscribeFallbackOption 接口并将 option 设置 AUDIO_ONLY 后，当下行网络环境不理想、仅接收远端音频流时，或当下行网络改善、恢复订阅音视频流时，会触发该回调。 
     * @since V4.3.0
     * @param uid           远端用户的 ID。
     * @param isFallback    远端订阅流已回退或恢复。
     *                      - true： 由于网络环境不理想，订阅的远端流已回退为音频流。
     *                      - false：由于网络环境改善，订阅的远端流从音频流恢复为音视频流。
     * @param streamType    对应的视频流类型，即主流或辅流。
     * @endif
     */
    void onRemoteSubscribeFallbackToAudioOnly(long uid, boolean isFallback, NERtcVideoStreamType streamType);

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
    void onLastmileQuality(int quality);

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
    void onLastmileProbeResult(LastmileProbeResult result);

    /**
     * @if English
     * Audio/Video Callback when banned by server.
     * @since v4.6.0
     * @param isAudioBannedByServer
     * @param isVideoBannedByServer
     * - true: banned
     * - false unbanned
     * @endif
     * @if Chinese
     * 服务端禁言音视频权限变化回调。
     * @since v4.6.0
     * @param isAudioBannedByServer 是否禁用音频。
     * - true：禁用音频。
     * - false：取消禁用音频。
     * @param isVideoBannedByServer 是否禁用视频。
     * - true：禁用视频。
     * - false：取消禁用视频。
     * @endif
     */
    void onMediaRightChange(boolean isAudioBannedByServer, boolean isVideoBannedByServer);

    /**
     * @if English
     * Reports whether the virtual background is successfully enabled.
     * @param enabled Whether the virtual background is successfully enabled:
     * - true: The virtual background is successfully enabled.
     * - false: The virtual background is not successfully enabled.
     * @param reason The reason why the virtual background is not successfully enabled or the message that confirms success:
     * {@link NERtcConstants.NERtcVirtualBackgroundSourceStateReason}
     * @endif
     * @if Chinese
     * 通知虚拟背景是否成功开启的回调。
     * <br> 调用 {@link NERtcEx.enableVirtualBackground} 接口启用虚拟背景功能后，SDK 会触发此回调。
     * @note 如果自定义虚拟背景是 PNG 或 JPG 格式的图片，SDK 会在读取图片后才会触发此回调，因此可能存在一定延时。
     * @since V4.6.10
     * @param enabled 是否已成功开启虚拟背景。
     * - true：成功开启虚拟背景。
     * - false：未成功开启虚拟背景。
     * @param reason 虚拟背景开启出错的原因或开启成功的提示。 {@link NERtcConstants.NERtcVirtualBackgroundSourceStateReason}
     * @endif
     */
    void onVirtualBackgroundSourceEnabled(boolean enabled, int reason);

    /**
     * @if English
     * Occurs when a remote user enables the audio substream.
     * @since V4.6.10
     * @param uid Remote user ID.
     * @endif
     * @if Chinese
     * 远端用户开启音频辅流回调。
     * @since V4.6.10
     * @param uid 远端用户 ID。
     * @endif
     */
    void onUserSubStreamAudioStart(long uid);

    /**
     * @if English
     * Occurs when a remote user stops the audio substream.
     * @since V4.6.10
     * @param uid remote user ID.
     * @endif
     * @if Chinese
     * 远端用户停用音频辅流回调。
     * @since V4.6.10
     * @param uid 远端用户 ID。
     * @endif
     */
    void onUserSubStreamAudioStop(long uid);


    /**
     * @if English
     * Occurs when a remote user pauses or resumes publishing the audio substream.
     * @since V4.6.10
     * @param uid   User ID indicating which user perform the operation.
     * @param muted indicates if the audio substream is stopped.
     *               - true: stops publishing the audio substream.
     *               - false: resumes publishing the audio substream.
     * @see NERtcEx#enableLocalSubStreamAudio(boolean)
     * @endif
     * @if Chinese
     * 远端用户暂停或恢复发送音频辅流的回调。
     * @since V4.6.10
     * @param uid   用户 ID，提示是哪个用户的音频辅流。
     * @param muted 是否停止发送音频辅流。
     *               - true：该用户已暂停发送音频辅流。
     *               - false：该用户已恢复发送音频辅流。
     * @see NERtcEx#enableLocalSubStreamAudio(boolean)
     * @endif
     */
    void onUserSubStreamAudioMute(long uid, boolean muted);



    /**
     * @if English
     * Occurs when the local video watermark takes affect.
     * @since V4.6.10
     * @param videoStreamType Type of video stream, main stream or substream. For more information, see {@link video.NERtcVideoStreamType}.
     * @param state           Watermark status. For more information, see {@link NERtcConstants.NERtcLocalVideoWatermarkState}.
     * @see NERtcEx#setLocalVideoWatermarkConfigs(NERtcVideoStreamType, NERtcVideoWatermarkConfig)
     * @endif
     * @if Chinese
     * 本地视频水印生效结果回调。
     * @since V4.6.10
     * @param videoStreamType 对应的视频流类型，即主流或辅流。详细信息请参考 {@link video.NERtcVideoStreamType}。
     * @param state           水印状态。详细信息请参考 {@link NERtcConstants.NERtcLocalVideoWatermarkState}。
     * @see NERtcEx#setLocalVideoWatermarkConfigs(NERtcVideoStreamType, NERtcVideoWatermarkConfig)
     * @endif
     */
    void onLocalVideoWatermarkState( NERtcVideoStreamType videoStreamType, int state);
}
