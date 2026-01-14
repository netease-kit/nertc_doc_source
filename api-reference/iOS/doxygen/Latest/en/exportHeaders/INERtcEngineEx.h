/*
 * Copyright (c) 2021 NetEase, Inc. All rights reserved.
 */

#ifndef INERtcEngineEx_h
#define INERtcEngineEx_h

#import "INERtcEngine.h"
#import "NERtcEngineStatistics.h"
#import "NERtcEngineBase.h"
#import "NERtcEngineDelegate.h"

@class NERtcChannel;

NS_ASSUME_NONNULL_BEGIN

/**
 * @if English
 * NERtcEngine extended API
 * @endif
 * @if Chinese
 * NERtcEngine 扩展接口
 * @endif
 */
@protocol INERtcEngineEx <INERtcEngine>

/**
 * @if English
 * Enables or disables the dual-stream mode.
 * <br>The method sets the individual-stream mode or dual-stream mode. If the dual-stream mode is enabled on the publishing client, the receiver can choose to receive the high-quality stream or low-quality stream video. The high-quality stream has a high resolution and high bitrate. The low-quality stream has a low resolution and low bitrate.
 * @note
 * - The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * - The method applies to only camera data. Video streams from external input and screen sharing are not affected.
 * - You can call this method before or after you join a room.
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
 * @param enable  YES 发送双流， NO 发送单流。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)enableDualStreamMode:(BOOL)enable;

/**
 * @if English
 * Sets the priority of media streams from a local user.
 * <br>If a user has a high priority, the media stream from the user also has a high priority. In unreliable network connections, the SDK guarantees the quality of the media stream from users with a high priority.
 * @note 
 * - You must call the method before you call joinChannel.
 * - After you switch the room by calling switchChannel, the media stream priority is restored to the default normal priority.
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
 - 快速切换房间 （switchChannel） 后，媒体优先级会恢复为默认值，即普通优先级。
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
 * @note You must call the method before you call joinChannel.
 * @since V4.3.0
 * @param option The fallback options for publishing audio and video streams. The default value is disabled. For more information, see {@link NERtcStreamFallbackOptions}.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 设置弱网条件下发布的音视频流回退选项。
 * <br>在网络不理想的环境下，发布的音视频质量都会下降。使用该接口并将 option 设置为 kNERtcStreamFallbackOptionAudioOnly 后:
 * - SDK 会在上行弱网且音视频质量严重受影响时，自动关断视频流，尽量保证音频质量。
 * - 同时 SDK 会持续监控网络质量，并在网络质量改善时恢复音视频流。
 * - 当本地发布的音视频流回退为音频流时，或由音频流恢复为音视频流时，SDK 会触发本地发布的媒体流已回退为音频流 onLocalPublishFallbackToAudioOnly 回调。
 * @note 请在加入房间（joinChannel）前调用此方法。
 * @since V4.3.0
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
 * @note You must call the method before you call joinChannel.
 * @since V4.3.0
 * @param option The fallback option for subscribing to audio and video streams. The default setting is to fall back to the low-quality video stream in the poor network. For more information, see {@link NERtcStreamFallbackOptions}.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 设置弱网条件下订阅的音视频流回退选项。
 * <br>弱网环境下，订阅的音视频质量会下降。通过该接口设置订阅音视频流的回退选项后：
 * - SDK 会在下行弱网且音视频质量严重受影响时，将视频流切换为小流，或关断视频流，从而保证或提高通信质量。
 * - SDK 会持续监控网络质量，并在网络质量改善时自动恢复音视频流。
 * - 当远端订阅流回退为音频流时，或由音频流恢复为音视频流时，SDK 会触发远端订阅流已回退为音频流回调。
 * @note 请在加入房间（joinChannel）前调用此方法。
 * @since V4.3.0
 * @param option  订阅音视频流的回退选项，默认为弱网时回退到视频小流。详细信息请参考 {@link NERtcStreamFallbackOptions}。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)setRemoteSubscribeFallbackOption:(NERtcStreamFallbackOptions)option;

/**
 * @if English
 * Stops or resumes publishing the local audio stream.
 * <br>The method is used to stop or resume publishing the local audio stream.
 * @note 
 * - This method does not change the audio capture state because the audio capture devices are not disabled.
 * - The mute state is reset to unmuted after the call ends.
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
 * @param muted 是否开启本地音频发送。
 * @return
 * - 0: 方法调用成功；
 * - 其他: 方法调用失败。
 * @endif
 */
- (int)muteLocalAudio:(BOOL)muted;

/**
 * @if English
 * Subscribes to or unsubscribes from audio streams from specified remote users.
 * <br>After a user joins a room, audio streams from all remote users are subscribed by default. You can call this method to subscribe to or unsubscribe from audio streams from all remote users.
 * @note You can call this method before or after you join a room.
 * @param subscribe The option whether to subscribe to specified audio streams.
 * @param userID The ID of a specified remote user.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 取消或恢复订阅指定远端用户音频流。
 * <br>加入房间时，默认订阅所有远端用户的音频流，您可以通过此方法取消或恢复订阅指定远端用户的音频流。
 * @note 该方法需要在加入房间后调用。
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


/**
 * @if English
 * Enables or disables an external source input published over the audio substream.
 * <br>After the method is implemented, you can call {@link pushExternalSubStreamAudioFrame:} to send the PCM data of the audio substream.
 * @note
 * - Call the method before {@link pushExternalSubStreamAudioFrame:}.
 * - If the external audio substream is enabled, users can manage the audio substream and the setting becomes invalid after the user calls leaveChannel.
 * @since V4.6.10
 * @param enabled    specifies whether to enable an external source input as audio substream.
 *                   - true: enables an external source as audio substream. Users manage the audio substream.
 *                   - false(default): disable an external source. The SDK manages the audio substream.
 * @param sampleRate Sampling rate of an external audio source. Unit: Hz. 8000，16000，32000，44100, and 48000 are recommended.
 *                   @note You can set a random valid value when disabling an external source by calling this API. In this case, the setting is not applied.
 * @param channels   The number of channels of an external audio source.
 *                   - 1: mono
 *                   - 2: stereo
 *                   @note You can set a random valid value when disabling an external source by calling this API. In this case, the setting is not applied.
 * @return 
 * - 0: success
 * - Others: failure
 * @endif
 * @if Chinese
 * 开启或关闭外部音频辅流输入。
 * <br>调用成功后可以使用 {@link pushExternalSubStreamAudioFrame:} 接口发送音频辅流 PCM 数据。
 * @note
 * - 请在{@link pushExternalSubStreamAudioFrame:}前调用该方法。
 * - 开启后音频辅流将由用户驱动，在 leaveChannel 后自动失效。
 * @since V4.6.10
 * @param enabled    是否开启外部音频辅流数据输入。
 *                   - YES：开启外部音频辅流输入，使用外部音频源，音频辅流由用户驱动。
 *                   - NO（默认）：关闭外部数据输入，不使用外部音频源，音频辅流由 SDK 驱动。
 * @param sampleRate 外部音频源的数据采样率，单位为 Hz。建议设置为 8000，16000，32000，44100 或 48000。
 *                   @note 调用此接口关闭外部音频辅流输入时可传入任意合法值，此时设置不会生效。
 * @param channels   外部音频源的数据声道数。
 *                   - 1：单声道。
 *                   - 2：双声道。
 *                   @note 调用此接口关闭外部音频辅流输入时可传入任意合法值，此时设置不会生效。
 * @return 
 * - 0: 方法调用成功。
 * - 其他: 方法调用失败。
 * @endif
 */
- (int)setExternalSubStreamAudioSource:(BOOL)enabled sampleRate:(int32_t)sampleRate channels:(int32_t)channels;

/**
 * @if English
 * Pushes external audio source over the audio substream.
 * <br>The method pushes the audio frame data captured from an external source to the internal engine and enables the audio substream using {@link INERtcEngineEx.enableLocalSubStreamAudio:}. The method can send the PCM data of an audio substream.
 * @note
 * - The method must be called after a user joins a room
 * - We recommend the data frame match 10s as a cycle.
 * - The method is invalid after the audio substream is disabled.
 * @since V4.6.10
 * @param frame audio frame data.
 * @return 
 * - 0: success
 * - Others: failure
 * @endif
 * @if Chinese
 * 推送外部音频辅流数据帧。
 * <br>将外部音频辅流帧数据帧主动推送给内部引擎。通过 {@link INERtcEngineEx.enableLocalSubStreamAudio:} 启用音频辅流后，可以调用此接口发送音频辅流 PCM 数据。
 * @note
 * - 该方法需要在加入房间后调用。
 * - 数据帧时长建议匹配 10ms 周期。
 * - 该方法在音频辅流关闭后不再生效。
 * @since V4.6.10
 * @param frame 音频帧数据。
 * @return 
 * - 0: 方法调用成功。
 * - 其他: 方法调用失败。
 * @endif
 */
- (int)pushExternalSubStreamAudioFrame:(NERtcAudioFrame *)frame;

/**
 * @if English
 * Subscribes to or unsubscribes from video streams from specified remote users.
 * <br>After a user joins a room, the video streams from remote users are not subscribed by default. If you want to view video streams from specified remote users, you can call this method to subscribe to the video streams from the user when the user joins the room or publishes the video streams.
 * @note
 * - The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * - You must join a room before you can call the method.
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
 * Enables video preview.
 * - The method is used to enable the local video preview before you join a room. Before you can call the API, you must call setupLocalVideoCanvas to set up a video canvas.
 * @note The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 开启视频预览。
 * - 该方法用于在进入房间前启动本地视频预览。调用该 API 前，必须调用 setupLocalVideoCanvas 设置视频画布。
 * @note 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)startPreview;

/**
 * @if English
 * Stops video preview.
 * @note <br>
 * - You must call the method before you join a room.
 * - The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 停止视频预览。
 * @note <br>
 * - 该方法需要在加入房间前调用。
 * - 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)stopPreview;

/** 
 * @if English
 * Sets the local video display mode.
 * @note The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * @param mode The video display mode. For more information, see {@link NERtcVideoRenderScaleMode}.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 设置本端的视频显示模式。
 * @note 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * @param mode 视频显示模式。详细信息请参考 {@link NERtcVideoRenderScaleMode}。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)setLocalRenderScaleMode:(NERtcVideoRenderScaleMode)mode;

/**
 * @if English
 * Sets the remote video display mode.
 * @note The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * @param mode The video display mode. For more information, see {@link NERtcVideoRenderScaleMode}.
 * @param userID The ID of a remote user.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 设置远端的视频显示模式。
 * @note 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * @param mode 视频显示模式。详细信息请参考 {@link NERtcVideoRenderScaleMode}。
 * @param userID 远端用户 ID。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)setRemoteRenderScaleMode:(NERtcVideoRenderScaleMode)mode forUserID:(uint64_t)userID;

/**
 * @if English
 * Sets the orientation mode for the local video.
 * <br>This API is used to set the orientation mode of the local video screen on the local and remote devices. You can specify that the local screen adapts to the landscape or portrait mode of the system device, or the landscape or portrait mode of the app UI.
 * @note 
 * - You must call this method before you join a room.
 * - Regardless of which orientation mode you select, the modes of the capture client and the playback client must be the same. The local screen and the remote view of the local screen have the same mode.
 * - The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * @since V4.3.0
 * @param rotationMode The video orientation mode. For more information, see {@link NERtcVideoRotationMode}.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 设置本地视频画面的旋转模式。
 * <br>该接口用于设置本地视频画面在本地和远端设备上的旋转模式，可以指定本地画面和系统设备的横屏/竖屏模式一致、或者和 App UI的横屏/竖屏模式一致。
 * @note 
 * - 请在加入房间之前调用此接口。
 * - 无论在哪种旋转模式下，采集端和播放端的旋转模式均保持一致。即本地看到的本地画面和远端看到的本地画面总是同样横屏模式或同样竖屏模式。
 * - 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * @since V4.3.0
 * @param rotationMode 视频旋转模式。详细信息请参考 {@link NERtcVideoRotationMode}。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
 - (int)setVideoRotationMode:(NERtcVideoRotationMode)rotationMode;

/**
 * @if English
 * Stops or resumes publishing the local video stream.
 * <br>If you call the method successfully, the remote client triggers the onNERtcEngineUser:videoMuted: callback.
 * @note
 * - The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * - If you call this method to stop publishing the local video stream, the SDK no longer publishes the local video stream.
 * -  You can call the method before or after you join a room.
 * - If you stop publishing the local video stream by calling this method, the setting is reset to the default state that allows the app to publish the local video stream.
 * - The method is different from enableLocalVideo. The enableLocalVideo method turns off local camera devices. The muteLocalVideovideo method does not affect local video capture, or disables cameras, and responds faster.
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
 * Enables or disables the audio playback route to the speakerphone.
 * <br>The method is used to specify whether to route audio playback to the speakerphone.
 * @note You can call this method before or after you join a room.
 * @param enable The option whether to route the audio playback to the external loudspeaker.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 启用或关闭扬声器播放。
 * <br>该方法设置是否将语音路由到扬声器，即设备外放。
 * @note 该方法需要在加入房间后调用。
 * @param enable 是否将音频路由到外放。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)setLoudspeakerMode:(bool)enable;

/**
 * @if English
 * Checks whether the speakerphone is enabled.
 * @note You can call this method before or after you join a room.
 * @param enabled The option whether to enable the speakerphone mode.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 检查扬声器状态启用状态。
 * @note 该方法可在加入房间前后调用。
 * @param enabled 是否正在使用扬声器模式。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)getLoudspeakerMode:(bool *)enabled;


/**
 * @if English
 * Starts recording an audio dump file.
 * <br>Audio dump files can be used to analyze audio issues.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 开始记录音频 dump。
 * <br>音频 dump 可用于分析音频问题。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)startAudioDump;

/**
 * @if English
 * Starts recording an audio dump file.
 * @note You can call the method can be called before or after joining a room.
 * @param type audio dump type. For more information, see {@link NERtcAudioDumpType}.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese
 * 开始进行音频 dump。
 * @note 该方法可在加入房间前后调用。
 * @param type 音频dump类型。详细信息请参考 {@link NERtcAudioDumpType}。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)startAudioDumpWithType:(NERtcAudioDumpType)type;

/**
 * @if English
 * Stops recording an audio dump file.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 结束记录音频 dump。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)stopAudioDump;

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

/**
 * @if English
 * @endif
 * @if Chinese
 * 指定前置/后置摄像头。
 * <br>该方法需要在相机启动后调用，例如调用 startPreview 或 joinChannel 后。
 * @note 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * @since V4.6.10
 * @param position 摄像头类型。详细信息请参考 {@link NERtcCameraPosition}。该参数为必填参数，若未赋值，SDK 会报错。
 * @return 
 * - 0：方法调用成功。
 * - 其他：方法调用失败。
 * @endif
 */
- (int)switchCameraWithPosition:(NERtcCameraPosition)position;

#pragma mark -- External Video Input

/**
 * @if English
 * Enables or disables the external video input source.
 * - The method enables the internal engine. The setting remains unchanged after you call the leaveChannel method. If you want to disable the setting, you must disable the setting before the next call starts.
 * - If you want to use external video sources, you must call this method before you call startScreenCapture, enableLocalVideo, and startVideoPreview.
 * - By default, the external video source uses the substream for screen sharing and uses the mainstream in scenarios other than screen sharing. The external video source cannot use the same stream as the camera.
 * - If the mainstream is used and enabled for the external video source, do not change the setting. If the substream channel is used and enabled, do not change the setting.
 * @note The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * @param enable The option whether to use the external video source.
 * - true: uses the external video source.
 * - false: does not use the external video source. This is the default value.
 * @param isScreen The option whether the external video source is used for screen sharing.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 开启或关闭外部视频源数据输入。
 * - 该方法设置内部引擎为启用状态，在 leaveChannel 后仍然有效。如果需要关闭该功能，需要在下次通话前调用接口关闭该功能。
 * - 如果使用了外部视频源，请在调用 startScreenCapture、enableLocalVideo 或 startPreview 之前调用此 API。
 * - 屏幕共享时，外部输入视频源默认使用辅流通道；非屏幕共享时，外部输入视频源使用主流通道，此时与 Camera 互斥。
 * - 之前使用主流通道或者当前使用主流通道，且主流已经开启时，请勿更改设置。之前使用辅流通道或者当前使用辅流通道，且辅流已经开启时，请勿更改设置。
 * @note 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * @param enable 是否使用外部视频源。
 * - true：使用外部视频源。
 * - false：（默认）不使用外部视频源。
 * @param isScreen 使用外部视频源时，外部视频源是否为屏幕共享数据。
 * @return
 * - 0：方法调用成功；
 * - 其他：方法调用失败。
 * @endif
 */
- (int)setExternalVideoSource:(BOOL)enable isScreen:(BOOL)isScreen;

/**
 * @if English
 * Publishes the external video frames.
 * <br>The method actively publishes the data of video frames that are encapsulated with the NERtcVideoFrame class to the SDK.
 * @note
 * - The method enables the internal engine. The setting remains unchanged after you call the leaveChannel method.
 * - Make sure that you have already called setExternalVideoSource with a value of YES before you call this method. Otherwise, an error message is repeatedly prompted if you call the method.
 * - The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * @param frame The information about video frame data. For more information, see {@link NERtcVideoFrame}.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 推送外部视频帧。
 * <br>该方法主动将视频帧数据用 NERtcVideoFrame 类封装后传递给 SDK。
 * @note
 * - 该方法设置内部引擎为启用状态，在 leaveChannel 后不再有效。
 * - 请确保在您调用本方法前已调用 setExternalVideoSource，并将参数设为 YES，否则调用本方法后会一直报错。
 * - 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * @param frame 外部视频帧的数据信息。详细信息请参考 {@link NERtcVideoFrame}。
 * @return 
 * - 0：方法调用成功；
 * - 其他：方法调用失败。
 * @endif
 */
- (int)pushExternalVideoFrame:(NERtcVideoFrame*)frame;

#pragma mark -- Sub Stream

/**
 * @if English
 * Sets the local substream canvas.
 * <br>This method is used to set the display information about the local screen sharing with the substream video. The app associates with the video view of local substream by calling this method. During application development, in most cases, before joining a room, you must first call this method to set the local video view after the SDK is initialized.
 * @note
 * - If the app uses external rendering, we recommend that you set the video view before you join the room.
 * - Before you join a room, you must call the method after the SDK is initialized.
 * - A canvas is configured for only one user.
 * - The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
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
 * @param config The encoding configuration of the local substream. For more information, see {@link NERtcVideoSubStreamEncodeConfiguration}.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 开启屏幕共享，屏幕共享内容以辅流形式发送。
 * <br>只能在加入房间后调用。
 * <br>如果您在加入房间后调用该方法开启辅流，调用成功后，远端触发 onNERtcEngineUserSubStreamDidStartWithUserID 回调。
 * @note 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * @param config 本地辅流发送配置，详细信息请参考  {@link NERtcVideoSubStreamEncodeConfiguration}。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)startScreenCapture:(NERtcVideoSubStreamEncodeConfiguration *)config;

/**
 * @if English
 * Disables screen sharing with the substream transmission.
 * <br>If you use the method to disable the substream after you join a room, the onNERtcEngineUserSubStreamDidStop callback is triggered on the remote client.
 * @note The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 关闭辅流形式的屏幕共享。
 * <br>如果您在加入房间后调用该方法关闭辅流，调用成功后，远端触发 onNERtcEngineUserSubStreamDidStop 回调。
 * @note 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)stopScreenCapture;

/**
 * @if English
 * Sets the display mode of the local substream video for screen sharing.
 * <br>Use this method if you want to enable screen sharing through the local substream. Apps can call this method multiple times to change the display mode.
 * @note
 * - The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * - Before you can call this method, you must set up the canvas for the local substream by calling setupLocalSubStreamVideoCanvas.
 * @param mode The video display mode. For more information, see {@link NERtcVideoRenderScaleMode}.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 设置本端的屏幕共享辅流视频显示模式。
 * <br>在本端开启辅流形式的屏幕共享时使用。App 可以多次调用此方法更改显示模式。
 * @note
 * - 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * - 调用此方法前，必须先通过 setupLocalSubStreamVideoCanvas 设置本地辅流画布。
 * @param mode 视频显示模式。详细信息请参考 {@link NERtcVideoRenderScaleMode}。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)setLocalRenderSubStreamScaleMode:(NERtcVideoRenderScaleMode)mode;

/**
 * @if English
 * Sets a remote substream canvas.
 * <br>The method associates a remote user with a substream view. You can assign a specified userID to use a corresponding canvas.
 * @note 
 * - The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * - If the app uses external rendering, we recommend that you set the canvas after you receive the return of onUserJoined.
 * - If the app does not retrieve the ID of a remote user, you can call the method after the remote user joins the room. You can retrieve the uid of the remote user from the return of onNERtcEngineUserDidJoinWithUserID. You can use this method to set the substream video canvas.
 * - If the remote user leaves the room, the SDK disassociates the remote user from the canvas. The setting automatically becomes invalid.
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
 * @param subscribe The option whether to subscribe to remote video substream for screen sharing.
 * @param userID The ID of a remote user.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 订阅或取消订阅远端的屏幕共享辅流视频，订阅之后才能接收远端的辅流视频数据。
 * @note
 * - 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * - 只能在加入房间后调用。
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
 * @param mode The video display mode. For more information, see {@link NERtcVideoRenderScaleMode}.
 * @param userID The ID of a remote user.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 设置远端的屏幕共享辅流视频显示模式。
 * @note
 * - 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * - 调用本接口之前，请先通过 subscribeRemoteSubStreamVideo 订阅远端的屏幕共享辅流视频。
 * @param mode 视频显示模式。详细信息请参考 {@link NERtcVideoRenderScaleMode}。
 * @param userID 远端用户 ID。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)setRemoteRenderSubStreamVideoScaleMode:(NERtcVideoRenderScaleMode)mode forUserID:(uint64_t)userID;

#pragma mark - AUDIO SESSION

/**
 * @if English
 * Sets permissions of the SDK over Audio Session.
 * <br>This method is only applicable to the iOS platform.
 * <br>This method controls the permissions of the SDK over Audio Session. By default, the SDK and the app have control over the Audio Session. However, in some cases, the app wants to restrict the SDK's permissions over Audio Session and uses other applications or third-party components to control Audio Session. The app can adjust the permissions of the SDK by calling this method.
 * <br>You can call this method only before you join the room.
 * @note If you call this method to restrict the SDK's permissions over Audio Session, the SDK cannot set Audio Session. You need to manage Audio Session or use a third-party component to operate Audio Session.
 * @param restriction The restriction applied to the SDK for Audio Session. For more information, see {@link NERtcAudioSessionOperationRestriction}.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 设置 SDK 对 Audio Session 的控制权限。
 * <br>该方法仅适用于 iOS 平台。
 * <br>该方法限制  SDK 对 Audio Session 的操作权限。在默认情况下，SDK 和 App 对 Audio Session 都有控制权，但某些场景下，App 会希望限制  SDK 对 Audio Session 的控制权限，而使用其他应用或第三方组件对 Audio Session 进行操控。调用该方法可以实现该功能。
 * <br>该接口只能在入会之前调用。
 * @note 一旦调用该方法限制了 SDK 对 Audio Session 的控制权限， SDK 将无法对 Audio Session 进行相关设置，而需要用户自己或第三方组件进行维护。
 * @param restriction SDK 对 Audio Session 的控制权限。详细信息请参考 {@link NERtcAudioSessionOperationRestriction}。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)setAudioSessionOperationRestriction:(NERtcAudioSessionOperationRestriction)restriction;

#pragma mark -- AUDIO DEVICE MANAGER

/**
 * @if English
 * Mutes or unmutes the audio playback device.
 * @param muted The option whether to mute the playback device. By default, the setting is unmuted.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 设置是否静音音频播放设备。
 * @param muted 是否静音播放设备。默认为不静音状态。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)setPlayoutDeviceMute:(bool)muted;


/**
 * @if English
 * Gets the mute status of the audio playback device.
 * @param muted The option whether the device is muted.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 获取音频播放设备的静音状态。
 * @param muted 是否静音。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)getPlayoutDeviceMute:(bool *)muted;


/**
 * @if English
 * Mutes or unmutes the audio capture device.
 * @param muted The option whether to mute the audio capture device. The default setting is unmuted.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 设置是否静音音频采集设备。
 * @param muted 是否静音音频采集设备。默认为不静音。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)setRecordDeviceMute:(bool)muted;


/**
 * @if English
 * Checks whether the audio capture device is muted.
 * @param muted The option whether the device is muted.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 查询当前音频采集设备是否静音。
 * @param muted 是否静音。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)getRecordDeviceMute:(bool *)muted;


#pragma mark -- CAMERA & TORCH SETTINGS

/**
 * @if English
 * Checks whether the camera zooming feature is supported.
 * <br>Make sure that you call this method after the camera starts. For example, you can call this method after you call startPreview or joinChannel.
 * @note The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * @return YES: The camera zooming feature is supported. NO: The camera zooming feature is not supported.
 * @endif
 * @if Chinese 
 * 检测设备当前使用的摄像头是否支持缩放功能。
 * <br>该方法需要在相机启动后调用，例如调用 startPreview 或 joinChannel 后。
 * @note 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * @return YES 表示支持，NO 表示支持。
 * @endif
 */
- (BOOL)isCameraZoomSupported;

/**
 * @if English
 * Checks whether the camera flash feature is supported.
 * @note
 * - In most cases, the app opens the front camera by default. If the front camera does not support the flash feature and you call the method, a value of NO is returned. If you want to check whether the rear camera supports the flash feature, before you call this method, you must first call switchCamera to switch the camera.
 * - Make sure that you call this method after the camera starts. For example, you can call this method after you call startPreview or joinChannel.
 * - The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * @return YES: The camera flash feature is supported. NO: The camera flash feature is not supported.
 * @endif
 * @if Chinese 
 * 检测设备是否支持闪光灯常亮。
 * @note
 * - 一般情况下，App 默认开启前置摄像头，因此如果设备前置摄像头不支持闪光灯，直接使用该方法会返回 NO。如果需要检查后置摄像头是否支持闪光灯，需要先使用switchCamera切换摄像头，再使用该方法。
 * - 该方法需要在相机启动后调用，例如调用 startPreview 或 joinChannel 后。
 * - 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * @return YES 表示支持，NO 表示不支持。
 * @endif
 */
- (BOOL)isCameraTorchSupported;

/**
 * @if English
 * Checks whether the camera manual focus feature is supported.
 * @note
 * - Make sure that you call this method after the camera starts. For example, you can call this method after you call startPreview or joinChannel.
 * - The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * @return YES: The camera manual focus feature is supported. NO: The camera manual focus feature is not supported.
 * @endif
 * @if Chinese 
 * 检测设备是否支持手动对焦功能。
 * @note
 * - 该方法需要在相机启动后调用，例如调用 startPreview 或 joinChannel 后。
 * - 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * @return YES 表示支持，NO 表示不支持。
 * @endif
 */
- (BOOL)isCameraFocusSupported;

/**
 * @if English
 * Checks whether the camera exposure feature is supported.
 * @note
 * - Make sure that you call this method after the camera starts. For example, you can call this method after you call startPreview or joinChannel.
 * - The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * @return YES: The camera exposure feature is supported. NO: The camera exposure feature is not supported.
 * @endif
 * @if Chinese 
 * 检测设备是否支持手动曝光功能。
 * @note
 * - 该方法需要在相机启动后调用，例如调用 startPreview 或 joinChannel 后。
 * - 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * @return YES 表示支持，NO 表示不支持。
 * @endif
 */
- (BOOL)isCameraExposurePositionSupported;

/**
 * @if English
 * Sets the camera exposure position.
 * <br>After you call the method, the onCameraExposureChanged callback is triggered on the local client.
 * @note
 * - Make sure that you call this method after the camera starts. For example, you can call this method after you call startPreview or joinChannel.
 * - The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * @param positionInView The exposure position point.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 设置当前摄像头手动曝光位置。
 * <br>成功调用该方法后，本地会触发 onCameraExposureChanged 回调。
 * @note
 * - 该方法需要在相机启动后调用，例如调用 startPreview 或 joinChannel 后。
 * - 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * @param positionInView 曝光位置点。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)setCameraExposurePosition:(CGPoint)positionInView;

/**
 * @if English
 * Enables or disables the camera flash feature.
 * @note
 * - Make sure that you call this method after the camera starts. For example, you can call this method after you call startPreview or joinChannel.
 * - The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * @param on YES: turn on. NO: turn off.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 设置是否打开闪光灯。
 * @note
 * - 该方法需要在相机启动后调用，例如调用 startPreview 或 joinChannel 后。
 * - 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * @param on YES 表示开启；NO 表示关闭。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)setCameraTorchOn:(BOOL)on;

/**
 * @if English
 * Check whether the flash is turned on on the device.
 * @note The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * @return YES: turned on; NO: turned off.
 * @endif
 * @if Chinese 
 * 查询设备是否开启了闪光灯。
 * @note 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * @return YES 表示开启；NO 表示关闭。
 * @endif
 */
- (BOOL)isCameraTorchOn;

/**
 * @if English
 * Sets the current camera zoom ratio.
 * @note
 * - Make sure that you call this method after the camera starts. For example, you can call this method after you call startPreview or joinChannel.
 * - Before you call this method, we recommend that you view the maximum zoom ratio supported by the camera by calling getCameraMaxZoom and set a zooming ratio as required.
 * - The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * @param factor The zoom ratio supported by the camera.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 设置当前摄像头缩放比例。
 * @note
 * - 该方法需要在相机启动后调用，例如调用 startPreview 或 joinChannel 后。
 * - 建议在调用本接口前，先通过 getCameraMaxZoom 查看摄像头支持的最大缩放比例，并根据实际需求合理设置需要的缩放比例。
 * - 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * @param factor 摄像头缩放比例。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)setCameraZoomFactor:(float)factor;

/**
 * @if English
 * Gets maximum zoom ratio supported by the camera.
 * @note
 * - Make sure that you call this method after the camera starts. For example, you can call this method after you call startPreview or joinChannel.
 * - The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * @return The maximum zoom ratio is returned.
 * @endif
 * @if Chinese 
 * 获取摄像头支持最大缩放比例。
 * @note
 * - 该方法需要在相机启动后调用，例如调用 startPreview 或 joinChannel 后。
 * - 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * @return 最大缩放比例。
 * @endif
 */
 - (float)maxCameraZoomScale;

/**
 * @if English
 * Sets the camera manual focus position.
 * <br>After you call the method, the onNERtcCameraFocusChanged callback is triggered on the local client.
 * @note
 * - Make sure that you call this method after the camera starts. For example, you can call this method after you call startPreview or joinChannel.
 * - The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * @param focusX The horizontal coordinate of the touch point in the view. Value range: 0 to 1.
 * @param focusY The vertical coordinate of the touch point in the view. Value range: 0 to 1.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 设置手动对焦位置。
 * <br>成功调用该方法后，本地会触发  onNERtcCameraFocusChanged 回调。
 * @note
 * - 该方法需要在相机启动后调用，例如调用 startPreview 或 joinChannel 后。
 * - 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * @param focusX 触摸点相对于视图的横坐标，范围为 0~1。
 * @param focusY 触摸点相对于视图的纵坐标，范围为 0~1。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)setCameraFocusPositionX:(float)focusX Y:(float)focusY;

/**
 * @if English
 * Sets the camera capturer configuration.
 * <br>For a video call or live streaming, generally the SDK controls the camera output parameters. By default, the SDK matches the most appropriate resolution based on the user's setLocalVideoConfig configuration. When the default camera capture settings do not meet special requirements, we recommend using this method to set the camera capturer configuration:
 * - If you want better quality for the local video preview, we recommend setting config as kNERtcCameraOutputQuality. The SDK sets the camera output parameters with higher picture quality.
 * - To customize the width and height of the video image captured by the local camera, set the camera capture configuration as kNERtcCameraOutputManual.
 * @note 
 * - Call this method before or after joining the channel. The setting takes effect immediately without restarting the camera.
 * - Higher collection parameters means higher performance consumption, such as CPU and memory usage, especially when video pre-processing is enabled. 
 * @since V4.5.0
 * @param config The camera capturer configuration.
 * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
 * @endif
 * @if Chinese
 * 设置本地摄像头的采集偏好等配置。
 * <br>在视频通话或直播中，SDK 自动控制摄像头的输出参数。默认情况下，SDK 根据用户的 setLocalVideoConfig 配置匹配最合适的分辨率进行采集。但是在部分业务场景中，如果采集画面质量无法满足实际需求，可以调用该接口调整摄像头的采集配置。
 * - 需要采集并预览高清画质时，可以将采集偏好设置为 kNERtcCameraOutputQuality，此时 SDK 会自动设置较高的摄像头输出参数，本地采集与预览画面比编码参数更加清晰。
 * - 需要自定义设置摄像头采集的视频尺寸时，请通过参数 preference 将采集偏好设为 kNERtcCameraOutputManual，并通过 NERtcCameraPreference 中的 captureWidth 和 captureHeight 自定义设置本地摄像头采集的视频宽高。
 * @note 
 * - 该方法可以在加入房间前后动态调用，设置成功后，会自动重启采集模块。
 * - 设置更高的采集参数会导致更大的性能消耗，例如 CPU 和内存占用等，尤其是在开启视频前处理的场景下。
 * @since V4.5.0
 * @param config 摄像头采集配置。
 * @return {@code 0} 方法调用成功，其他调用失败
 * @endif
 */
 - (int)setCameraCaptureConfig:(NERtcCameraCaptureConfiguration *)config;

#pragma mark -- AUDIO MIXING

/**
 * @if English
 * Starts to play or mix the music file.
 * <br>This method mixes the specified local or online audio file with the audio stream captured from the recording device.
 * - Supported audio formats: MP3, M4A, AAC, 3GP, WMA, and WAV. Files that are stored in local storage or URLs are supported.
 * - If the playback status changes, the onAudioMixingStateChanged callback is triggered on the local client.
 * @note 
 * - You can call this method after you join a room.
 * - Starting from V4.3.0, if you call this method to play a music file during a call, and manually set the playback volume or publishing volume of the mixing audio, the setting is applied when you call the method again during the current call.
 * - Starting from V4.4.0, the operation to turn on or off local audio capture no longer affects the playback of music files. The music file is still playing after you call enablingLocalAudio(NO).
 * @param option The options when you configure a mixing audio task, such as the type of audio mixing tasks, the full path of the mixing audio file, or URL. For more information, see {@link NERtcCreateAudioMixingOption}.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 开始播放音乐文件。
 * <br>该方法指定本地或在线音频文件来和录音设备采集的音频流进行混音。
 * - 支持的音乐文件类型包括 MP3、M4A、AAC、3GP、WMA 和 WAV 格式，支持本地文件或在线 URL。
 * - 播放状态改变时，本地会触发 onAudioMixingStateChanged 回调。
 * @note 
 * - 请在加入房间后调用该方法。
 * - 从 V4.3.0 版本开始，若您在通话中途调用此接口播放音乐文件时，手动设置了伴音播放音量或发送音量，则当前通话中再次调用时默认沿用此设置。
 * - 在 V4.4.0 版本中，开启或关闭本地音频采集的操作不影响音乐文件在远端的播放，即 enableLocalAudio(NO) 后仍旧可以发送伴音；在其他版本中，必须开启音频采集才能发送伴音。
 * @param option 创建混音任务配置的选项，包括混音任务类型、混音文件全路径或 URL 等。详细信息请参考 {@link NERtcCreateAudioMixingOption}。
 * @return
 * - 0: 方法调用成功；
 * - 其他: 方法调用失败。详细信息请参考 {@link NERtcAudioMixingErrorCode}。
 * @endif
 */
- (int)startAudioMixingWithOption:(NERtcCreateAudioMixingOption *)option;

/**
 * @if English
 * Stops playing or mixing the music file.
 * <br>The method stops playing mixing audio. You can call the method when you are in a room.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 停止播放音乐文件及混音。
 * <br>该方法停止播放伴奏。请在房间内调用该方法。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)stopAudioMixing;

/**
 * @if English
 * Pauses playing and mixing the music file.
 * <br>The method pauses playing mixing audio. You can call the method when you are in a room.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 暂停播放音乐文件及混音。
 * <br>该方法暂停播放伴奏。请在房间内调用该方法。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)pauseAudioMixing;

/**
 * @if English
 * The method resumes mixing audio playback and continues playing the mixing audio. You can call the method when you are in a room.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 该方法恢复混音，继续播放伴奏。请在房间内调用该方法。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)resumeAudioMixing;

/**
 * @if English
 * Sets the publishing volume of the mixing audio.
 * @param volume The volume of publishing mixing audio. Valid values: 0 to 100. The default value of 100 indicates the original volume.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 设置伴奏的发送音量。
 * @param volume 伴奏发送音量。取值范围为 0~100。默认 100，即原始文件音量。
 * @return 操作返回值，成功则返回 0 
 * @endif
 */
- (int)setAudioMixingSendVolume:(uint32_t)volume;

/**
 * @if English
 * Gets the volume for publishing mixing audio.
 * <br>The method gets the volume of mixing audio. You can call the method when you are in a room.
 * @param volume The volume of publishing audio mixing.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 获取伴奏发送音量。
 * <br>该方法获取混音里伴奏的发送音量大小。请在房间内调用该方法。
 * @param volume 伴奏发送音量。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)getAudioMixingSendVolume:(uint32_t *)volume;

/**
 * @if English
 * Adjusts the volume of mixing audio for local playback.
 * <br>The method adjusts the volume of mixing audio for local playback. You can call the method when you are in a room.
 * @param volume The playback volume of the mixing audio. Valid values: 0 to 100. The default value of 100 indicates the original volume.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 调节伴奏播放音量。
 * <br>该方法调节混音里伴奏的播放音量大小。请在房间内调用该方法。
 * @param volume  伴奏播放音量。取值范围为 0~100。默认 100，即原始文件音量。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)setAudioMixingPlaybackVolume:(uint32_t)volume;

/**
 * @if English
 * Gets the playback volume of the mixing audio.
 * <br>The method gets the playback volume of mixing audio. You can call the method when you are in a room.
 * @param volume The playback volume of the mixing audio. Valid values: 0 to 100.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 获取伴奏播放音量。
 * <br>该方法获取混音里伴奏的播放音量大小。请在房间内调用该方法。
 * @param volume  伴奏播放音量。范围为 0~100。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)getAudioMixingPlaybackVolume:(uint32_t *)volume;

/**
 * @if English
 * Gets the duration of the mixing audio file.
 * <br>The method gets the duration of the mixing audio file. Unit: milliseconds. You can call the method when you are in a room.
 * @param duration The duration of the mixing audio file. Unit: milliseconds.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 获取伴奏时长。
 * <br>该方法获取伴奏时长，单位为毫秒。请在房间内调用该方法。
 * @param duration 伴奏时长，单位为毫秒。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)getAudioMixingDuration:(uint64_t *)duration;

/**
 * @if English
 * Gets the playback position of the mixing audio file.
 * <br>The method gets the playback position of the audio file. Unit: milliseconds. You can call the method when you are in a room.
 * @param position The playback position of the mixing audio file. Unit: milliseconds.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 获取音乐文件的播放进度。
 * <br>该方法获取当前伴奏播放进度，单位为毫秒。请在房间内调用该方法。
 * @param position 音乐文件的播放位置，单位为毫秒。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)getAudioMixingCurrentPosition:(uint64_t *)position;

/**
 * @if English
 * Sets the playback position of the mixing audio file to a different starting position.
 * <br>The method sets the playback position of the mixing audio file to a different starting position. The method allows you to play the mixing audio file from the position based on your requirements rather than from the beginning.
 * @param position The playback position of the mixing audio file. Unit: milliseconds.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 设置音乐文件的播放位置。
 * <br>该方法可以设置音频文件的播放位置，这样你可以根据实际情况播放文件，而非从头到尾播放整个文件。
 * @param position 音乐文件的播放位置，单位为毫秒。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)setAudioMixingPosition:(uint64_t)position;

#pragma mark -- AUDIO EFFECT
/**
 * @if English
 * Plays back a specified sound effect file.
 * <br>The method plays a specified local or online sound effect file.
 * - If you call the method and the playback ends, the onAudioEffectFinished playback is triggered.
 * - Supported audio formats: MP3, M4A, AAC, 3GP, WMA, and WAV. Files that are stored in local storage or URLs are supported.
 * @note
 * - You can call this method after you join a room.
 * - You can call the method multiple times. You can play multiple sound effect files simultaneously by passing in different effectId and options. Various sound effects are mixed. To gain optimal user experience, we recommend you play no more than three sound effect files at the same time.
 * @param effectId The ID of the specified sound effect. Each sound effect has a unique ID.
 * @param option The parameters, such as the type of mixing audio tasks, and the path of the mixing audio file. For more information, see {@link NERtcCreateAudioEffectOption}.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 播放指定音效文件。
 * <br>该方法播放指定的本地或在线音效文件。
 * - 成功调用该方法后，如果播放结束，本地会触发 onAudioEffectFinished 回调。
 * - 支持的音效文件类型包括 MP3、M4A、AAC、3GP、WMA 和 WAV 格式，支持本地文件和在线 URL。
 * @note
 * - 请在加入房间后调用该方法。
 * - 您可以多次调用该方法，通过传入不同的音效文件的 effectId 和 option ，同时播放多个音效文件，实现音效叠加。为获得最佳用户体验，建议同时播放的音效文件不超过 3 个。
 * @param effectId 指定音效的 ID。每个音效均应有唯一的 ID。
 * @param option 音效相关参数，包括混音任务类型、混音文件路径等。详细信息请参考 {@link NERtcCreateAudioEffectOption}。
 * @return
 * - 0: 方法调用成功；
 * - 其他: 方法调用失败。
 * @endif
 */
- (int)playEffectWitdId:(uint32_t)effectId effectOption:(NERtcCreateAudioEffectOption *)option;

/**
 * @if English
 * Stops playing all sound effect files.
 * <br>You can call this method after you join a room.
 * @param effectId The ID of the specified sound effect. Each sound effect has a unique ID.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 停止播放指定音效文件。
 * @note 请在加入房间后调用该方法。
 * @param effectId 指定音效的 ID。每个音效均有唯一的 ID。
 * @return
 * - 0: 方法调用成功；
 * - 其他: 方法调用失败。
 * @endif
 */
- (int)stopEffectWitdId:(uint32_t)effectId;

/**
 * @if English
 * Stops playing all sound effect files.
 * <br>You can call this method after you join a room.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 停止播放所有音效文件。
 * @note 请在加入房间后调用该方法。
 * @return
 * - 0: 方法调用成功；
 * - 其他: 方法调用失败。
 * @endif
 */
- (int)stopAllEffects;

/**
 * @if English
 * Pauses playing a specified sound effect file.
 * <br>You can call this method after you join a room.
 * @param effectId The ID of a sound effect file.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 暂停播放指定音效文件。
 * <br>请在加入房间后调用该方法。
 * @param effectId 音效 ID。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)pauseEffectWitdId:(uint32_t)effectId;

/**
 * @if English
 * Resumes playing a specified sound effect file.
 * <br>You can call this method after you join a room.
 * @param effectId The ID of the specified sound effect. Each sound effect has a unique ID.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 恢复播放指定音效文件。
 * <br>请在加入房间后调用该方法。
 * @param effectId 指定音效的 ID。每个音效均有唯一的 ID。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)resumeEffectWitdId:(uint32_t)effectId;

/**
 * @if English
 * Pauses playing all sound effect files.
 * <br>You can call this method after you join a room.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 暂停播放所有音效文件。
 * <br>请在加入房间后调用该方法。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)pauseAllEffects;

/**
 * @if English
 * Resumes playing all sound effect files.
 * <br>You can call this method after you join a room.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 恢复播放所有音效文件。
 * <br>请在加入房间后调用该方法。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)resumeAllEffects;

/**
 * @if English
 * Sets the publishing volume of a sound effect file.
 * <br>You can call this method after you join a room.
 * @param effectId The ID of the specified sound effect. Each sound effect has a unique ID.
 * @param volume The publishing volume of the sound effect file. Valid values: 0 to 100. The default value of 100 indicates the original volume.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 设置音效文件发送音量。
 * <br>请在加入房间后调用该方法。
 * @param effectId 指定音效的 ID。每个音效均有唯一的 ID。
 * @param volume 音效发送音量。范围为0~100，默认为  100，表示原始音量。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)setEffectSendVolumeWithId:(uint32_t)effectId volume:(uint32_t)volume;

/**
 * @if English
 * Gets the publishing volume of a specified sound effect file.
 * <br>You can call this method after you join a room.
 * @param effectId The ID of the specified sound effect file. Each sound effect file has a unique ID.
 * @param volume The returned publishing volume of the sound effect file.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 获取指定音效文件发送音量。
 * <br>请在加入房间后调用该方法。
 * @param effectId 指定音效的 ID。每个音效均有唯一的 ID。
 * @param volume 返回的发送音量。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)getEffectSendVolumeWithId:(uint32_t)effectId volume:(uint32_t *)volume;

/**
 * @if English
 * Sets the playback volume of the sound effect files.
 * <br>You can call this method after you join a room.
 * @param effectId The ID of the specified audio effect file. Each sound effect file has a unique ID.
 * @param volume The playback volume of the sound effect file. Valid values: 0 to 100. The default value is 100.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 获取音效文件播放音量。
 * <br>请在加入房间后调用该方法。
 * @param effectId 指定音效的 ID。每个音效均有唯一的 ID。
 * @param volume 音效播放音量。范围为0~100，默认为100。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)setEffectPlaybackVolumeWithId:(uint32_t)effectId volume:(uint32_t)volume;

/**
 * @if English
 * Gets the playback volume of the sound effect files
 * @param effectId The ID of a sound effect file.
 * @param volume The returned volume of the sound effect file.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 获取音效的回放音量
 * @param effectId 音效 ID。
 * @param volume 返回的音量值
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)getEffectPlaybackVolumeWithId:(uint32_t)effectId volume:(uint32_t *)volume;

/**
 * @if English
 * Gets the duration of a sound effect file.
 * @since V4.4.0
 * The method gets the duration of the sound effect file. Unit: milliseconds.
 * @note You can call the method when you join in a room.
 * @param[in] effectId The ID of a sound effect file.
 * @param[out] duration The duration of the sound effect file. Unit: milliseconds.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 获取音效文件时长。
 * @since V4.4.0
 * 该方法获取音效文件时长，单位为毫秒。
 * @note 请在房间内调用该方法。
 * @param[in] effectId 音效 ID。
 * @param[out] duration 音效文件时长，单位为毫秒。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)getEffectDurationWithId:(uint32_t)effectId duration:(uint64_t *)duration;

/**
 * @if English
 * Gets the playback position of a sound effect file.
 * @since V4.4.0 
 * The method gets the playback position of the audio file. Unit: milliseconds.
 * @note You can call the method when you join a room.
 * @param[in] effectId The ID of a sound effect file.
 * @param[out] position The playback position of the sound effect file. Unit: milliseconds.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 获取音效的播放进度。
 * @since V4.4.0 
 * 该方法获取当前音效播放进度，单位为毫秒。
 * @note 请在房间中调用该方法。
 * @param[in]  effectId 音效 ID。
 * @param[out] position 音效文件的播放位置，单位为毫秒。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)getEffectCurrentPositionWithId:(uint32_t)effectId position:(uint64_t *)position;

#pragma mark -- In-ear monitoring
/**
 * @if English
 * Enables the in-ear monitoring feature.
 * @note
 * - You can call the method when you join in a room.
 * - After in-ear monitoring is enabled, you must wear a headset or earpieces to use the in-ear monitoring feature. We recommend that you call onAudioDeviceChanged to monitor the changes of audio devices. Only when the device changes to headset, you can enable in-ear monitoring.
 * - In the V4.0.0 release, the volume parameter in enableEarback is invalid. You can call setEarbackVolume to set the volume for in-ear monitoring.
 * @param enabled The option whether to enable in-ear monitoring.
 * @param volume The volume for in-ear monitoring. Valid values: 0 to 100. The default value is 100.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 开启耳返功能。
 * @note
 * - 请在房间内调用该方法。
 * - 开启耳返功能后，必须连接上耳机或耳麦，才能正常使用耳返功能。建议通过 onAudioDeviceChanged  监听播放设备的变化，当监听到播放设备切换为耳机时才开启耳返。
 * - 在V4.0.0 版本中，enableEarback 的 volume 参数无效，请使用 setEarbackVolume 接口设置耳返音量。
 * @param enabled 开启耳返功能。
 * @param volume 设置耳返音量，可设置为0~100，默认为 100。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)enableEarback:(BOOL)enabled volume:(uint32_t)volume;

/**
 * @if English
 * Sets the volume for in-ear monitoring.
 * @param volume The volume for in-ear monitoring. Valid values: 0 to 100. The default value is 100.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 设置耳返音量。
 * @param volume 设置耳返音量，可设置为0~100，默认为 100。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)setEarbackVolume:(uint32_t)volume;

#pragma mark -- audio recording
/**
 * @if English
 * Starts an audio recording from a client.
 * <br>The method records the mixing audio from all room members in the room, and store the recording file locally. The onAudioRecording() callback is triggered when the recording starts or ends.
 * <br>If you specify a type of audio quality, the recording file is saved in different formats.
 * - A WAV file is large with high quality
 * - An AAC file is small with low quality.
 * @note
 * - You must call the method after you call joinChannel.
 * - A client can only run a recording task. If you repeatedly call the startAudioRecording method, the current recording task stops and a new recording task starts.
 * - If the current user leaves the room, audio recording automatically stops. You can call the stopAudioRecording method to manually stop recording during calls.
 * @param filePath The file path where the recording file is stored. The file name and format are required. For example, sdcard/xxx/audio.aac.
 *                   - Make sure that the path is valid and has the write permissions.
 *                   - Only WAV or AAC files are supported.
 * @param sampleRate The recording sample rate. Valid values: 16000,32000, 44100, and 48000. The default value is 32000.
 * @param quality The audio quality. The parameter is valid only the recording file is in AAC format. For more information, see {@link NERtcAudioRecordingQuality}.
   @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 开始客户端录音。 
 * <br>调用该方法后，客户端会录制房间内所有用户混音后的音频流，并将其保存在本地一个录音文件中。录制开始或结束时，自动触发 onAudioRecording() 回调。
 * <br>指定的录音音质不同，录音文件会保存为不同格式：
 * - WAV：音质保真度高，文件大。
 * - AAC：音质保真度低，文件小。
 * @note
 * - 请在加入房间（joinchannel）后调用此方法。
 * - 客户端只能同时运行一个录音任务，正在录音时，如果重复调用 startAudioRecording，会结束当前录制任务，并重新开始新的录音任务。
 * - 当前用户离开房间时，自动停止录音。您也可以在通话中随时调用 stopAudioRecording 手动停止录音。
 * @param filePath 录音文件在本地保存的绝对路径，需要精确到文件名及格式。例如：sdcard/xxx/audio.aac。
 *                   - 请确保指定的路径存在并且可写。
 *                   - 目前仅支持 WAV 或 AAC 文件格式。
 * @param sampleRate 录音采样率（Hz），可以设为 16000、32000（默认）、44100 或 48000。
 * @param quality 录音音质，只在 AAC 格式下有效。详细信息请参考 {@link NERtcAudioRecordingQuality}。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)startAudioRecording:(NSString *)filePath sampleRate:(int)sampleRate quality:(NERtcAudioRecordingQuality)quality;

/**
 * @if English
 * Starts an audio recording from a client.
 * <br>The method records the mixing audio from all room members in the room, and store the recording file locally. The onAudioRecording() callback is triggered when the recording starts or ends.
 * <br>If you specify a type of audio quality, the recording file is saved in different formats.
 * - A WAV file is large in size with high quality
 * - An AAC file is small in size with low quality.
 * @note
 * - You must call the method after you call joinChannel.
 * - A client can only run a recording task. If you repeatedly call the startAudioRecording method, the current recording task stops and a new recording task starts.
 * - If the current user leaves the room, audio recording automatically stops. You can call the stopAudioRecording method to manually stop recording during calls.
 * @param filePath The file path where the recording file is stored. The file name and format are required. For example, sdcard/xxx/audio.aac.
 *                   - Make sure that the path is valid and has the write permissions.
 *                   - Only WAV or AAC files are supported.
 * @param sampleRate The recording sample rate in Hz. Valid values: 16000,32000, 44100, and 48000. The default value is 32000.
 * @param quality The audio quality. The parameter is valid only the recording file is in AAC format. For more information, see {@link NERtcAudioRecordingQuality}.
 * @param position   The recording object. The mixed audio in the room is recorded by default.
 * @param cycleTime  The maximum number of seconds for loop caching. You can set the value to 0, 10, 60, 360, and 900. The default value is 0 indicates that the write operation runs in real time.
 * @return 
 * - 0: success
 * - Others: failure
 * @endif
 * @if Chinese
 * 开始客户端录音。
 * 调用该方法后，客户端会录制房间内所有用户混音后的音频流，并将其保存在本地一个录音文件中。录制开始或结束时，自动触发 onAudioRecording() 回调。
 * 指定的录音音质不同，录音文件会保存为不同格式：
 * - WAV：音质保真度高，文件大。
 * - AAC：音质保真度低，文件小。
 * @note
 * - 请在加入房间后调用此方法。
 * - 客户端只能同时运行一个录音任务，正在录音时，如果重复调用 startAudioRecordingWithConfig，会结束当前录制任务，并重新开始新的录音任务。
 * - 当前用户离开房间时，自动停止录音。您也可以在通话中随时调用 stopAudioRecording 手动停止录音。
 * @since V4.6.0
 * @param filePath   录音文件在本地保存的绝对路径，需要精确到文件名及格式。例如：sdcard/xxx/audio.aac。
 *                   - 请确保指定的路径存在并且可写。
 *                   - 目前仅支持 WAV 或 AAC 文件格式。
 * @param sampleRate 录音采样率（Hz），可以设为 16000、32000（默认）、44100 或 48000。
 * @param quality    录音音质，只在 AAC 格式下有效。详细信息请参考 {@link NERtcAudioRecordingQuality} 。
 * @param position   录音对象。详细信息请参考 {@link NERtcAudioRecordingPosition}。
 * @param cycleTime  循环缓存的最大时长跨度。该参数单位为秒，可以设为 0、10、60、360、900，默认值为 0，即实时写文件。
 * @return
 * - 0：方法调用成功。
 * - 其他：方法调用失败。
 * @endif
 */
- (int)startAudioRecordingWithConfig:(NERtcAudioRecordingConfiguration *_Nonnull)config;

/**
 * @if English
 * Stops the audio recording on the client.
 * <br>If the local client leaves the room, audio recording automatically stops. You can call the stopAudioRecording method to manually stop recording during calls.
 * @note You must call this method before you call leaveChannel.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 停止客户端录音。
 * <br>本端离开房间时自动停止录音，您也可以在通话中随时调用 stopAudioRecording 手动停止录音。
 * @note 该接口需要在 leaveChannel 之前调用。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)stopAudioRecording;

#pragma mark --External Audio
/**
 * @if English
 * Sets the external audio input source.
 * <br>After you call the method, the setting becomes invalid if you choose an audio input device or a sudden restart occurs. After the method is called, you can call pushExternalAudioFrame to send the pulse-code modulation (PCM) data.
 * @note
 * - You can call this method before you join a room.
 * - The method enables the internal engine. The virtual component works instead of the physical speaker. The setting remains valid after you call the leaveChannel method. If you want to disable the feature, you must disable the setting before the next call starts.
 * - After you enable the external audio data input, some functionalities of the speakerphone supported by the SDK are replaced by the external audio source. Settings that are applied to the speakerphone become invalid or do not take effect in calls. For example, you can hear the external data input when you use loopback for testing.
 * @param enabled The option whether to enable external data input. The default value is NO.
 * @param sampleRate The sample rate of the external audio source. Unit: Hz. Recommended values: 8000, 16000, 32000, 44100, and 48000.
 * @param channels The number of sound channels. Valid values:
 * - 1: mono sound
 * - 2: stereo sound
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 开启或关闭外部音频源数据输入。
 * <br>当该方法调用成功后，音频输入设备选择和异常重启会失效。调用成功后可以使用 pushExternalAudioFrame 接口发送音频 PCM 数据。
 * @note
 * - 该方法设置内部引擎为启用状态，启动时将用虚拟设备代替麦克风工作。
 * - 启用外部音频数据输入功能后，SDK 内部实现部分麦克风由外部输入数据代替，麦克风相关的设置会失败或不在通话中生效。例如进行 loopback 检测时，会听到输入的外部数据。
 * @param enabled 是否开启外部数据输入。默认为 NO。
 * @param sampleRate 外部音频源的数据采样率，单位为 Hz。建议设置为 8000，16000，32000，44100 或 48000。
 * @param channels 外部音频源的数据声道数。可设置为：
 * - 1：单声道。
 * - 2：双声道。
 * @return
 * - 0: 方法调用成功；
 * - 其他: 方法调用失败。
 * @endif
 */
- (int)setExternalAudioSource:(BOOL)enabled sampleRate:(int32_t)sampleRate channels:(int32_t)channels;

/**
 * @if English
 * Publishes the external audio frame.
 * <br>The method pushes the external audio frame data to the internal audio engine. If you enable the external audio data source by calling setExternalAudioSource, you can use pushExternalAudioFrame to send audio PCM data.
 * @note
 * - You must join a room before you can call the method.
 * - We recommend that you set the duration of data frames to match a cycle of 10 ms.
 * - The method becomes invalid if the audio input device is turned off. For example, disable local audio, end calls, and shut off the microphone test before calls.
 * @param frame The external audio frame data. The data cannot exceed 7,680 bytes.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 推送外部音频帧。
 * <br>将外部音频数据帧推送给内部引擎。 通过 setExternalAudioSource 启用外部音频数据输入功能成功后，可以使用 pushExternalAudioFrame 接口发送音频 PCM 数据。
 * @note
 * - 该方法需要在加入房间后调用。
 * - 数据帧时长建议匹配 10ms 周期。
 * - 该方法在音频输入设备关闭后不再生效。例如关闭本地音频、通话结束、通话前麦克风设备测试关闭等情况下，该设置不再生效。
 * @param frame 外部音频帧数据；数据长度不能超过 7680 字节，和调用周期时长一致。
 * @return
 * - 0: 方法调用成功；
 * - 其他: 方法调用失败。
 * @endif
 */

- (int)pushExternalAudioFrame:(NERtcAudioFrame *)frame;


/**
 * @if English
 * Sets external audio rendering.
 * <br>The method is suitable for scenarios that require personalized audio rendering. By default, the setting is disabled. If you choose an audio playback device or a sudden restart occurs, the setting becomes invalid.
 * <br>After you call the method, you can use pullExternalAudioFrame to get audio PCM data.
 * @note
 * - You can call this method before you join a room.
 * - The method enables the internal engine. The virtual component works instead of the physical speaker. The setting remains valid after you call the leaveChannel method. If you want to disable the functionality, you must disable the functionality before the next call starts.
 * - After you enable the external audio rendering, some functionalities of the speakerphone supported by the SDK are replaced by the external audio source. Settings that are applied to the speakerphone become invalid or do not take effect in calls. For example, external rendering is required to play the external audio when you use loopback for testing.
 * @param enabled The option whether to enable external audio rendering.
 * @param sampleRate The sample rate of the external audio rendering. Unit: Hz. Valid values: 16000, 32000, 44100, and 48000.
 * @param channels The number of channels for external audio rendering. Valid values:
 * - 1: mono sound 
 * - 2: stereo sound  
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 设置外部音频渲染。
 * <br>该方法适用于需要自行渲染音频的场景。默认为关闭状态。当该方法调用成功后，音频播放设备选择和异常重启失效。
 * <br>调用成功后可以使用 pullExternalAudioFrame 接口获取音频 PCM 数据。
 * @note
 * - 请在加入房间前调用该方法。
 * - 该方法设置内部引擎为启用状态，启动时将用虚拟设备代替扬声器工作。
 * - 启用外部音频渲染功能后，SDK 内部实现部分扬声器由外部输入数据代替，扬声器相关的设置会失败或不在通话中生效。例如进行 loopback 检测时，需要由外部渲染播放。
 * @param enabled 设置是否开启外部音频渲染。
 * @param sampleRate 外部音频渲染的采样率 (Hz)，可设置为 16000，32000，44100 或 48000。
 * @param channels 外部音频渲染的声道数，可设置为：
 * - 1：单声道
 * - 2：双声道
 * @return 操作返回值，成功则返回 0
 * @endif
 */
 - (int)setExternalAudioRender:(BOOL)enabled sampleRate:(int32_t)sampleRate channels:(int32_t)channels;

/**
 * @if English
 * Pulls the external audio data.
 * <br>The method pulls the audio data from the internal audio engine. After you enable the external audio data rendering functionality by calling setExternalAudioRender, you can use pullExternalAudioFrame to get the audio PCM data.
 * @note
 * - You must join a room before you can call the method.
 * - We recommend that you set the duration of data frames to match a cycle of 10 ms.
 * - The method becomes invalid if the audio rendering device is turned off. In this case, no data is returned. For example, when calls end, and when the speakerphone is shut off before calls.
 * @param data The data pointer.
 * @param len The size of the audio data that are pulled. Unit: bytes. We recommend that the duration of the audio data at least last 10 ms, and the data size cannot exceed 7680 bytes. <br>Formula: len = sampleRate/1000 × 2 × channels × duration of the audio data in milliseconds
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 拉取外部音频数据。
 * <br>该方法将从内部引擎拉取音频数据。 通过 setExternalAudioRender 启用外部音频数据渲染功能成功后，可以使用 pullExternalAudioFrame 接口获取音频 PCM 数据。
 * @note
 * - 该方法需要在加入房间后调用。
 * - 数据帧时长建议匹配 10ms 周期。
 * - 该方法在音频渲染设备关闭后不再生效，此时会返回空数据。例如通话结束、通话前扬声器设备测试关闭等情况下，该设置不再生效。
 * @param data 数据指针。
 * @param len 待拉取音频数据的字节数，单位为 byte。建议音频数据的时长至少为 10 毫秒，数据长度不能超过 7680字节。<br>计算公式为： len = sampleRate/1000 × 2 × channels × 音频数据时长（毫秒）。
 * @return 操作返回值，成功则返回 0

* @endif
*/
- (int)pullExternalAudioFrame:(void *_Nonnull)data length:(int)len;

#pragma mark -- Media Statistic Observer

/**
 * @if English
 * Registers a stats observer.
 * @param observer The stats observer. For more information, see {@link NERtcEngineMediaStatsObserver}.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 注册媒体统计信息观测器。
 * @param observer 统计信息观测器。详细信息请参考  {@link NERtcEngineMediaStatsObserver}。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)addEngineMediaStatsObserver:(id<NERtcEngineMediaStatsObserver>)observer;

/**
 * @if English
 * Removes the specified media stats observer.
 * @param observer The stats observer
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 移除指定媒体统计信息观测器。
 * @param observer 统计信息观测器
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)removeEngineMediaStatsObserver:(id<NERtcEngineMediaStatsObserver>)observer;

/**
 * @if English
 * Clears all media stats observers.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 清除全部媒体统计信息观测器。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)cleanupEngineMediaStatsObserver;


#pragma mark -- Live Stream
/**
 * @if English
 * Adds a streaming task in a room.
 * <br>After you call the method, the current user can receive a notification about the status of live streaming.
 * @param taskInfo The information about the streaming task. For more information, {@link NERtcLiveStreamTaskInfo}.
 * @param completion The result. The callback is triggered after the method is called. For more information, see {@link NERtcLiveStreamCompletion}.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @note
 * - The method is applicable to only live streaming.
 * - You can call the method when you are in a room. The method is valid for calls.
 * - Only one URL for the relayed stream is added in each call. You need to call the method multiple times if you want to push many streams. An RTC room with the same channelid can create three different streaming tasks.
 * @endif
 * @if Chinese 
 * 添加房间推流任务。
 * <br>成功调用该方法后，当前用户可以收到该直播流的状态通知。
 * @param taskInfo 推流任务信息，详细信息请参考  {@link NERtcLiveStreamTaskInfo}。    
 * @param completion 操作结果回调，方法调用成功后会触发对应回调。详细信息请参考 {@link NERtcLiveStreamCompletion}。
 * @return 操作返回值，成功则返回 0
 * @note
 * - 该方法仅适用直播场景。
 * - 请在房间内调用该方法，该方法在通话中有效。
 * - 该方法每次只能增加一路旁路推流地址。如需推送多路流，则需多次调用该方法。同一个音视频房间（即同一个 channelid）可以创建 3 个不同的推流任务。
 * @endif
 */
- (int)addLiveStreamTask:(NERtcLiveStreamTaskInfo *)taskInfo compeltion:(NERtcLiveStreamCompletion)completion;

/**
 * @if English
 * Updates a streaming task.
 * @note
 * - The method is applicable to only live streaming.
 * - You can call the method when you are in a room. The method is valid for calls.
 * @param taskInfo The information about the streaming task. For more information, see {@link NERtcLiveStreamTaskInfo}.    
 * @param completion The result. The callback is triggered after the method is called. For more information, see {@link NERtcLiveStreamCompletion}.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 更新房间推流任务。
 * @note
 * - 该方法仅适用直播场景。
 * - 请在房间内调用该方法，该方法在通话中有效。
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
 * @param taskId The ID of a streaming task.  
 * @param completion The result. The callback is triggered after the method is called. For more information, see {@link NERtcLiveStreamCompletion}.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 删除房间推流任务。
 * @note
 * - 该方法仅适用直播场景。
 * - 请在房间内调用该方法，该方法在通话中有效。
 * @param taskId 推流任务 ID。 
 * @param completion 操作结果回调，方法调用成功后会触发对应回调。详细信息请参考 {@link NERtcLiveStreamCompletion}。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)removeLiveStreamTask:(NSString *)taskId compeltion:(NERtcLiveStreamCompletion)completion;

#pragma mark -- Audio Frame Observer
/**
 * @if English
 * Sets the format of audio capture.
 * <br>The method sets the format of the recording for onNERtcEngineAudioFrameDidRecord.
 * @note
 * - You can set or modify the format before or after you join a room.
 * - To cancel listeners for the callback, reset the value to nil.
 * @param format The sample rate and the number of channels returned by onNERtcEngineAudioFrameDidRecord. <br>A value of nil is allowed. The default value is nil, which indicates that the original format of the audio file is used. For more information, see {@link NERtcAudioFrameRequestFormat}.   
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 设置采集的音频格式。
 * <br>该方法设置 {@link NERtcEngineAudioFrameObserver#onNERtcEngineAudioFrameDidRecord:} 回调的录制声音格式。
 * @note
 * - 该方法在加入房间前后均可设置或修改。
 * - 取消监听，重置为空。
 * @param format 指定 {@link NERtcEngineAudioFrameObserver#onNERtcEngineAudioFrameDidRecord:} 中返回数据的采样率和数据的通道数。<br>允许传入 nil，默认为 nil，表示使用音频的原始格式。详细信息请参考 {@link NERtcAudioFrameRequestFormat}。 
 * @return
 * - 0: 方法调用成功。
 * - 其他: 方法调用失败。
 * @endif
 */
- (int)setRecordingAudioFrameParameters:(nullable NERtcAudioFrameRequestFormat *)format;

  
/**
 * @if English
 * Sets the audio playback format.
 * <br>The method sets the playback format of the audio data returned by onNERtcEngineAudioFrameWillPlayback.
 * @note
 * - You can set or modify the format before or after you join a room.
 * - To cancel listeners for the callback, reset the value to nil.
 * @param format The sample rate and the number of channels returned by onNERtcEngineAudioFrameDidRecord. <br>A value of nil is allowed. The default value is nil, which indicates that the original format of the audio file is used. For more information, see {@link NERtcAudioFrameRequestFormat}.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 设置播放的声音格式。
 * <br>该方法设置 {@link NERtcEngineAudioFrameObserver#onNERtcEngineAudioFrameWillPlayback:} 回调的播放声音格式。
 * @note
 * - 该方法在加入房间前后均可设置或修改。
 * - 取消监听，重置为空。
 * @param format 指定 {@link NERtcEngineAudioFrameObserver#onNERtcEngineAudioFrameWillPlayback:} 中返回数据的采样率和数据的通道数。<br>允许传入 nil，默认为 nil，表示使用音频的原始格式。详细信息请参考 {@link NERtcAudioFrameRequestFormat}。   
 * @return
 * - 0: 方法调用成功；
 * - 其他: 方法调用失败。
 * @endif
 */
- (int)setPlaybackAudioFrameParameters:(nullable NERtcAudioFrameRequestFormat *)format;

/**
 * @if English
 * Sets the format of the recording and mixing audio.
 * <br>The method sets the format of the audio frames returned by onNERtcEngineMixedAudioFrame.
 * - You can set or modify the format before or after you join a room. leaveChannel is reset to nil.
 * - You can set only the sample rate.
 * - If you do not use the method to set the format, the default value of the sample rate supported by the SDK is returned.
 * @param format The sample rate and the number of channels returned by onNERtcEngineMixedAudioFrame. A value of nil is allowed. The default value is nil, which indicates that the original format of the audio file is used. For more information, see {@link NERtcAudioFrameRequestFormat}.   
 * @return The value returned. A value of 0 indicates that the operation is successful. 
 * @endif
 * @if Chinese 
 * 设置录制和播放声音混音后的数据格式。
 * <br>该方法设置 {@link NERtcEngineAudioFrameObserver#onNERtcEngineMixedAudioFrame:} 回调的声音格式。
 * - 该方法在加入房间前后均可设置或修改，{@link INERtcEngine#leaveChannel} 后重置为空。
 * - 目前只支持设置采样率。
 * - 未调用该接口设置数据格式时，回调中的采样率返回 SDK 默认值。
 * @param format 指定 {@link NERtcEngineAudioFrameObserver#onNERtcEngineMixedAudioFrame:} 中返回数据的采样率和数据的通道数。允许传入 nil，默认为 nil，表示使用音频的原始格式。详细信息请参考 {@link NERtcAudioFrameRequestFormat}。
 * @return
 * - 0: 方法调用成功；
 * - 其他: 方法调用失败。
* @endif
*/
- (int)setMixedAudioFrameParameters:(nullable NERtcAudioFrameRequestFormat *)format;

/**
 * @if English
 * Registers the audio observer.
 * <br>The method can set audio capture or play PCM data callbacks. The method can process audio streams. The method can register the callback that is triggered by the audio engine, such as onPlaybackFrame.
 * @note You can set or modify the method before or after you join a room. 
 * @param observer The audio frame observer. <br> If you pass in NULL, you cancel the registration and clear the settings of NERtcAudioFrameRequestFormat. For more information, see {@link NERtcEngineAudioFrameObserver}.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 注册语音观测器对象。
 * <br>该方法用于设置音频采集/播放 PCM 回调，可用于声音处理等操作。当需要引擎返回 {@link NERtcEngineAudioFrameObserver#onNERtcEngineAudioFrameDidRecord:} 或 {@link NERtcEngineAudioFrameObserver#onNERtcEngineAudioFrameWillPlayback:} 回调时，需要使用该方法注册回调。
 * @note 该方法在加入房间前后均可设置或修改。
 * @param observer 音频数据帧观测器。<br>如果传入 NULL，则取消注册，同时会清理 NERtcAudioFrameRequestFormat 相关设置。详细信息请参考 {@link NERtcEngineAudioFrameObserver}。
 * @return 
 * - 0: 方法调用成功；
 * - 其他: 方法调用失败。
 * @endif
 */
- (int)setAudioFrameObserver:(nullable id<NERtcEngineAudioFrameObserver>)observer;
  

#pragma mark -- Volume indication
/**
 * @if English
 * Enables reporting users' volume indication.
 * <br>The method allows the SDK to report to the app the information about the volume of the user that publishes local streams and the remote users (up to three users) that have the highest instantaneous volume. The information about the current speaker and the volume is reported.
 * <br>If this method is enabled. When a user joins a room and pushes streams, the SDK triggers the onRemoteAudioVolumeIndication callback based on the preset time intervals.
 * @param enable The option whether to prompt the speaker volume.
 * @param interval The time interval at which the volume prompt is displayed. Unit: milliseconds. The value must be multiples of 100 milliseconds. We recommend that you set the value 200 milliseconds or more.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 启用说话者音量提示。
 * <br>该方法允许 SDK 定期向 App 反馈本地发流用户和瞬时音量最高的远端用户（最多 3 位）的音量相关信息，即当前谁在说话以及说话者的音量。
 * <br>启用该方法后，只要房间内有发流用户，无论是否有人说话，SDK 都会在加入房间后根据预设的时间间隔触发 onRemoteAudioVolumeIndication 回调。
 * @param enable 是否启用说话者音量提示。
 * @param interval 指定音量提示的时间间隔。单位为毫秒。必须设置为 100 毫秒的整数倍值，建议设置为 200 毫秒以上。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)enableAudioVolumeIndication:(BOOL)enable interval:(uint64_t)interval;

/**
 * @if English
 * Enables volume indication for the speaker.
 * <br>The method allows the SDK to report to the app the information about the volume of the user that pushes local streams and the remote user (up to three users) that has the highest instantaneous volume. The information about the current speaker and the volume is reported.
 * <br>If this method is enabled, the SDK triggers {@link NERtcEngineDelegateEx#onRemoteAudioVolumeIndication:totalVolume:} based on the preset time intervals when a user joins a room and pushes streams. 
 * @since V4.6.10
 * @param enable    Specify whether to indicate the speaker volume.
 *                       - true: indicates the speaker volume.
 *                       - false: does not indicate the speaker volume.
 * @param interval  The time interval at which volume prompt is displayed. Unit: milliseconds. The value must be the multiples of 100 milliseconds. A value of 200 milliseconds is recommended.
 * @param enableVad Specify whether to monitor the voice capture.
 *                       - true: monitors the voice capture.
 *                       - false: does not monitor the voice capture.
 * @return 
 * - 0: success
 * - Others: failure
 * @endif
 * @if Chinese
 * 启用说话者音量提示。
 * <br>该方法允许 SDK 定期向 App 反馈本地发流用户和瞬时音量最高的远端用户（最多 3 位）的音量相关信息，即当前谁在说话以及说话者的音量。
 * <br>启用该方法后，只要房间内有发流用户，无论是否有人说话，SDK 都会在加入房间后根据预设的时间间隔触发 {@link NERtcEngineDelegateEx#onRemoteAudioVolumeIndication:totalVolume:} 回调。
 * @since V4.6.10
 * @param enable   是否启用说话者音量提示。
 *                  - YES：启用说话者音量提示。
 *                  - NO：关闭说话者音量提示。
 * @param interval 指定音量提示的时间间隔。单位为毫秒。必须设置为 100 毫秒的整数倍值，建议设置为 200 毫秒以上。
 * @param enableVad 是否启用本地采集人声监测。
 *                  - YES：启用本地采集人声监测。
 *                  - NO：关闭本地采集人声监测。
 * @return 
 * - 0：方法调用成功。
 * - 其他：方法调用失败。
 * @endif
 */
- (int)enableAudioVolumeIndication:(BOOL)enable interval:(uint64_t)interval vad:(BOOL)enableVad;


#pragma mark - Signal Volume
/**
 * @if English
 * Adjusts the volume of captured signals.
 * @param volume The volume of captured signals. Valid values: 0 to 400. Where:
 * - 0: muted.
 * - 100: the original volume. This is the default value.
 * - 400: The maximum value can be four times the original volume. The limit value is protected.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 调节采集信号音量。
 * @param volume 采集信号音量，取值范围为 [0, 400]。其中：
 * - 0：静音。
 * - 100：（默认）原始音量。
 * - 400：最大可为原始音量的 4 倍（自带溢出保护）。
 * @return
 * - 0: 方法调用成功；
 * - 其他: 方法调用失败。
 * @endif
 */ 
- (int)adjustRecordingSignalVolume:(uint32_t)volume;

/**
 * @if English
 * Adjusts the playback signal volume of all remote users.
 * @param volume The playback signal volume. Valid range: 0 to 400. Where:
 * - 0: muted.
 * - 100: the original volume. This is the default value.
 * - 400: The maximum value can be four times the original volume. The limit value is protected.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 调节本地播放的所有远端用户信号音量。
 * @param volume 播放音量。取值范围为 [0, 400]。其中：
 * - 0：静音。
 * - 100：（默认）原始音量。
 * - 400：最大可为原始音量的 4 倍（自带溢出保护）。
 * @return
 * - 0: 方法调用成功；
 * - 其他: 方法调用失败。
 * @endif
 */
- (int)adjustPlaybackSignalVolume:(uint32_t)volume;

/**
 * @if English
 * Adjust the volume of local signal playback from a specified remote user.
 * <br>After you join the room, you can call the method to set the volume of local audio playback from different remote users or repeatedly adjust the volume of audio playback from a specified remote user.
 * @note
 * - You can call this method after you join a room.
 * - The method is valid in the current call. If a remote user exits the room and rejoins the room again, the setting is still valid until the call ends.
 * - The method adjusts the volume of the mixing audio published by a specified remote user. Only one remote user can be adjusted. If you want to adjust multiple remote users, you need to call the method for the required times.
 * @param userID The ID of a remote user.
 * @param volume The playback volume. Valid values: 0 to 100.
                  - 0: muted
                  - 100: the original volume
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 调节本地播放的指定远端用户的信号音量。
 * <br>加入房间后，您可以多次调用该方法设置本地播放的不同远端用户的音量；也可以反复调节本地播放的某个远端用户的音量。
 * @note
 * - 请在成功加入房间后调用该方法。
 * - 该方法在本次通话中有效。如果远端用户中途退出房间，则再次加入此房间时仍旧维持该设置，通话结束后设置失效。
 * - 该方法调节的是本地播放的指定远端用户混音后的音量，且每次只能调整一位远端用户。若需调整多位远端用户在本地播放的音量，则需多次调用该方法。
 * @param userID    远端用户 ID。
 * @param volume 播放音量，取值范围为 [0,100]。
                  - 0：静音。
                  - 100：原始音量。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)adjustUserPlaybackSignalVolume:(uint32_t)volume forUserID:(uint64_t)userID;

#pragma mark - voice effects

/**
 * @if English
 * Sets the voice pitch of the local audio.
 * <br>The method changes the voice pitch of the local speaker.
 * @note
 * - After the call ends, the setting changes back to the default value 1.0.
 * - The method conflicts with setAudioEffectPreset. After you call this method, the preset voice beautifier effect will be removed.
 * @param pitch The voice frequency. Valid values: 0.5 to 2.0. Smaller values have lower pitches. The default value is 1, which indicates that the pitch is not changed.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 设置本地语音音调。
 * <br>该方法改变本地说话人声音的音调。
 * @note
 * - 通话结束后该设置会重置，默认为 1.0。
 * - 此方法与 setAudioEffectPreset 互斥，调用此方法后，已设置的变声效果会被取消。
 * @param pitch 语音频率。可以在 [0.5, 2.0] 范围内设置。取值越小，则音调越低。默认值为 1.0，表示不需要修改音调。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)setLocalVoicePitch:(double)pitch;

/**
 * @if English
 * Sets the local voice equalization effect, or customizes center frequencies of the local voice effects.
 * @note You can call this method before or after you join a room. By default, the audio effect is disabled after the call ends.
 * @param bandFrequency The band frequency. Value range: 0 to 9. The values represent the respective 10-band center frequencies of the voice effects, including 31, 62, 125, 250, 500, 1k, 2k, 4k, 8k, and 16k Hz.
 * @param gain The gain of each band (dB). Value range: -15 to 15. The default value is 0.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 设置本地语音音效均衡，即自定义设置本地人声均衡波段的中心频率。
 * @note 该方法在加入房间前后都能调用，通话结束后重置为默认关闭状态。
 * @param bandFrequency 频谱子带索引，取值范围是 [0-9]，分别代表 10 个频带，对应的中心频率是 [31，62，125，250，500，1k，2k，4k，8k，16k] Hz。
 * @param gain 每个 band 的增益，单位是 dB，每一个值的范围是 [-15，15]，默认值为 0。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)setLocalVoiceEqualizationOfBandFrequency:(NERtcAudioEqualizationBandFrequency)bandFrequency withGain:(NSInteger)gain;

/**
 * @if English
 * Sets an SDK preset voice beautifier effect.
 * <br>The method can set an SDK preset voice beautifier effect for a local user who publishes an audio stream.
 * @note You can call this method before or after you join a room. By default, the audio effect is disabled after the call ends.
 * @param type The type of the preset voice beautifier effect. By default, the voice beautifier effect is disabled. For more information, see {@link NERtcVoiceBeautifierType}.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 设置 SDK 预设的美声效果。
 * <br>调用该方法可以为本地发流用户设置 SDK 预设的人声美声效果。
 * @note 该方法在加入房间前后都能调用，通话结束后重置为默认关闭状态。
 * @param type 预设的美声效果模式。默认关闭美声效果。详细信息请参考 {@link NERtcVoiceBeautifierType}。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)setVoiceBeautifierPreset:(NERtcVoiceBeautifierType)type;

/**
 * @if English
 * Sets an SDK preset voice changer effect.
 * <br>The method can apply multiple preset voice changer effects to original human voices and change audio profiles.
 * @note
 * - You can call this method either before or after joining a room. By default, the audio effect is disabled after the call ends.
 * - The method conflicts with setLocalVoicePitch. After you call this method, the voice pitch is reset to the default value 1.0.
 * @param type The type of the preset voice changer effect. By default, the sound effect is disabled. For more information, see {@link NERtcVoiceChangerType}.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 设置 SDK 预设的人声的变声音效。
 * <br>设置变声音效可以将人声原音调整为多种特殊效果，改变声音特性。
 * @note
 * - 此方法在加入房间前后都能调用，通话结束后重置为默认关闭状态。
 * - 此方法和 setLocalVoicePitch 互斥，调用此方法后，本地语音语调会恢复为默认值 1.0。
 * @param type 预设的变声音效。默认关闭变声音效。详细信息请参考  {@link NERtcVoiceChangerType}。
 * @return 操作返回值，成功则返回 0
 * @endif
 */

- (int)setAudioEffectPreset:(NERtcVoiceChangerType)type;

/**
 * @if English
 * Sets the reverb effect for the local audio stream.
 * @note The method can be called before or after a user joins a room. The setting will be reset to the default value after a call ends.
 * @since V4.6.10
 * @param param For more information, see {@link NERtcReverbParam}.
 * @return 
 * - 0: success
 * - Others: failure
 * @endif
 * @if Chinese
 * 设置本地语音混响效果。
 * @note 该方法在加入房间前后都能调用，通话结束后重置为默认的关闭状态。
 * @since V4.6.10
 * @param param 详细信息请参考 {@link NERtcReverbParam}。
 * @return
 * - 0：方法调用成功。
 * - 其他：方法调用失败。
 * @endif
 */
- (int)setLocalVoiceReverbParam:(NERtcReverbParam *)param;

#pragma mark - waterMark

/**
 * @if English
 * Adds a watermark image to the local video.
 * @note 
 * - The setLocalCanvasWatermarkConfigs method applies to the local video canvas and does not affect the video stream. If the canvas is removed, the watermark will be automatically deleted.
 * - Before you set a watermark, you must first set the canvas by calling related methods.
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

/**
 * @if English
 * Sets a video watermark. The watermark takes effect for local preview and publishing.
 * @note If you set a watermark, we recommend you notice the status callback {@link NERtcEngineDelegateEx#onNERtcEngineLocalVideoWatermarkStateWithStreamType:state:}.
 * @since V4.6.10
 * @param type   The type of the video stream on which a watermark is applied, mainstream or substream. For more information, see {@link NERtcStreamChannelType}.
 * @param config Watermark configuration. If the value is set to null, all previous watermarks are canceled. For more information, see {@link NERtcVideoWatermarkConfig}.
 * @return
 * - 0: success
 * - Others: failure
 * @endif
 * @if Chinese
 * 设置视频水印。水印在本地预览及发送过程中均生效。
 * @note 设置水印后，建议关注水印状态回调 {@link NERtcEngineDelegateEx#onNERtcEngineLocalVideoWatermarkStateWithStreamType:state:}。
 * @since V4.6.10
 * @param type   水印的视频流类型。支持设置为主流或辅流。详细信息请参考 {@link NERtcStreamChannelType}。
 * @param config 水印设置。设置为 nil 表示取消之前的水印。详细信息请参考 {@link NERtcVideoWatermarkConfig}。
 * @return
 * - 0：方法调用成功。
 * - 其他：方法调用失败。
 * @endif
 */
- (int)setLocalVideoWatermarkConfigs:(nullable NERtcVideoWatermarkConfig *)config 
                      withStreamType:(NERtcStreamChannelType)type;

#pragma mark - snapshot
/**
 * @if English
 * Takes a local video snapshot.
 * <br>The takeLocalSnapshot method takes a local video snapshot on the local mainstream or local substream. The callback that belongs to the NERtcTakeSnapshotCallback class returns the data of the snapshot image.
 * @note<br>
 * - Before you call the method to capture the snapshot from the mainstream, you must first call startVideoPreview or enableLocalVideo, and joinChannel.
 * - Before you call the method to capture the snapshot from the substream, you must first call joinChannel and startScreenCapture.
 * - You can set text, timestamp, and image watermarks at the same time. If different types of watermarks overlap, the layers overlay previous layers in the image, text, and timestamp sequence.
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
 * @param streamType 截图的视频流类型。支持设置为主流或辅流。
 * @param callback 截图回调。
 * @note 纯音频SDK禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * @return 操作返回值，成功则返回 
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
 * @param userID 远端用户 ID。
 * @param streamType 截图的视频流类型。支持设置为主流或辅流。
 * @param callback 截图回调。
 * @note 纯音频SDK禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)takeRemoteSnapshot:(NERtcStreamChannelType)streamType forUserID:(uint64_t)userID callback:(NERtcTakeSnapshotCallback)callback;

#pragma mark -- Other
/**
 * @if English
 * Uploads the SDK information.
 * <br>The data that is published contains the log file and the audio dump file.
 * @note You must join a room before you can call the method.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * @endif
 * @if Chinese 
 * 上传 SDK 信息。
 * <br>上传的信息包括 log 和 Audio dump 等文件。
 * @note 只能在加入房间后调用。
 * @return 操作返回值，成功则返回 0
 * @endif
 */
- (int)uploadSdkInfo;

#pragma mark - SEI

/**
 * @if English
 * Sends supplemental enhancement information (SEI) data through a specified mainstream or substream.
 * <br>When you publish the local audio and video stream, SEI data is also sent to sync some additional information. After SEI data is sent, the receiver can retrieve the content by listening for the onRecvSEIMsg callback.
 * - Condition: After you publish the video stream using the mainstream and substream, you can invoke the method.
 * - Data limit in length: The SEI data can contain a maximum of 4,096 bytes in size. Sending an SEI message fails if the data exceeds the size limit. If a large amount of data is sent, the video bitrate rises. This degrades the video quality or causes frozen frames.
 * - Frequency limit: We recommend that the maximum video frame rate does not exceed 10 fps.
 * - Time to take effect: After the method is called, the SEI data is sent from the next frame in the fastest fashion or after the next 5 frames at the slowest pace.
 * @note
 * - The SEI data is transmitted together with the video stream. If video frame loss occurs due to poor connection quality, the SEI data will also get dropped. We recommend that you increase the frequency within the transmission limits. This way, the receiver can get the data.
 * - Before you specify a channel to transmit the SEI data, you must first enable the data transmission channel.
 * - The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
 * @param data The custom SEI data.
 * @param type The type of the channel with which the SEI data is transmitted. For more information, see {@link NERtcStreamChannelType}.
 * @return The value returned. A value of 0 indicates that the operation is successful.
 * - Success: The SEI data joins the queue and is ready for delivery. The data will be sent after the most recent video frame.
 * - Failure: If the data is restricted, the frequency may be too high, the queue is full, or the data exceeds the maximum value of 4k.
 * @endif
 * @if Chinese 
 * 指定主流或辅流通道发送媒体增强补充信息（SEI）。
 * <br>在本端推流传输音视频流数据同时，发送流媒体补充增强信息来同步一些其他附加信息。当推流方发送 SEI 后，拉流方可通过监听 onRecvSEIMsg 的回调获取 SEI 内容。
 * - 调用时机：视频流（主流、辅流）开启后，可调用此函数。
 * - 数据长度限制： SEI 最大数据长度为 4096 字节，超限会发送失败。如果频繁发送大量数据会导致视频码率增大，可能会导致视频画质下降甚至卡顿。
 * - 发送频率限制：最高为视频发送的帧率，建议不超过 10 次/秒。
 * - 生效时间：调用本接口之后，最快在下一帧视频数据帧之后发送 SEI 数据，最慢在接下来的 5 帧视频之后发送。
 * @note
 * - SEI 数据跟随视频帧发送，由于在弱网环境下可能丢帧，SEI 数据也可能随之丢失，所以建议在发送频率限制之内多次发送，保证接收端收到的概率。
 * - 指定通道发送 SEI 之前，需要提前开启对应的数据流通道。
 * - 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
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
 * <br>When you publish the local audio and video stream, SEI data is also sent to sync some additional information. After SEI data is sent, the receiver can retrieve the content by listening for the onRecvSEIMsg callback.
 * - Condition: After you publish the video stream using the mainstream and substream, you can invoke the method.
 * - Data limit in length: The SEI data can contain a maximum of 4,096 bytes in size. Sending an SEI message fails if the data exceeds the size limit. If a large amount of data is sent, the video bitrate rises. This degrades the video quality or causes broken video frames.
 * - Frequency limit: We recommend that the maximum video frame rate does not exceed 10 fps.
 * - Time to take effect: After the method is called, the SEI data is sent from the next frame in the fastest fashion or after the next 5 frames at the slowest pace.
 * @note
 * - The SEI data is transmitted together with the video stream. If video frame loss occurs due to poor connection quality, the SEI data will also get dropped. We recommend that you increase the frequency within the transmission limits. This way, the receiver can get the data.
 * - By default, the SEI is transmitted by using the mainstream.
 * - The audio-only SDK disables this API. If you need to use the API, you can download the standard SDK from the official website of CommsEase and replace the audio-only SDK.
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
 * @note
 * - SEI 数据跟随视频帧发送，由于在弱网环境下可能丢帧，SEI 数据也可能随之丢失，所以建议在发送频率限制之内多次发送，保证接收端收到的概率。
 * - 调用本接口时，默认使用主流通道发送 SEI。
 * - 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
 * @param data 自定义 SEI 数据。
 * @return 操作返回值，成功则返回 0
 * - 成功:  成功进入待发送队列，会在最近的视频帧之后发送该数据
 * - 失败:  数据被限制发送，可能发送的频率太高，队列已经满了，或者数据大小超过最大值 4k
 * @endif
 */
- (int)sendSEIMsg:(NSData *)data;

#pragma mark- MediaRealy

/**
 * @if English
 * Starts to relay media streams across rooms.
 * - The method can invite co-hosts across rooms. Media streams from up to four rooms can be relayed. A room can receive multiple relayed media streams.
 * - After you call the method, the SDK triggers onNERtcEngineChannelMediaRelayStateDidChange and onNERtcEngineDidReceiveChannelMediaRelayEvent. The callback reports the status and events about the current relayed media streams across rooms.
 * @note
 * - You can call this method after you join a room. Before you call the method, you must set the destination room by calling setDestinationInfo in the config parameter.
 * - The method is applicable only to the host in live streaming.
 * - If you want to call the method again, you must first call the stopChannelMediaRelay method to exit the current relay status.
 * - If you succeed in relaying the media stream across rooms, and want to change the destination room, for example, add or remove the destination room, you can call updateChannelMediaRelay to update the information about the destination room.
 * @param config The configuration for media stream relay across rooms. For more information, see {@link NERtcChannelMediaRelayConfiguration}.
 * @return A value of 0 returned indicates that the operation is successful. Otherwise, the operation fails.
 * @endif
 * @if Chinese 
 * 开始跨房间媒体流转发。
 * - 该方法可用于实现跨房间连麦等场景。支持同时转发到 4 个房间，同一个房间可以有多个转发进来的媒体流。
 * - 成功调用该方法后，SDK 会触发 onNERtcEngineChannelMediaRelayStateDidChange 和 onNERtcEngineDidReceiveChannelMediaRelayEvent 回调，并在回调中报告当前的跨房间媒体流转发状态和事件。
 * @note
 * - 请在成功加入房间后调用该方法。调用此方法前需要通过 config 中的 setDestinationInfo 设置目标房间。
 * - 该方法仅对直播场景下的主播角色有效。
 * - 成功调用该方法后，若您想再次调用该方法，必须先调用 stopChannelMediaRelay 方法退出当前的转发状态。
 * - 成功开始跨房间转发媒体流后，如果您需要修改目标房间，例如添加或删减目标房间等，可以调用方法 updateChannelMediaRelay 更新目标房间信息。
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
 * @note
 * - Before you call the method, you must join the room and call startChannelMediaRelay to relay the media stream across rooms. Before you call the method, you must set the destination room by calling setDestinationInfo in the config parameter.
 * - You can relay the media stream up to four destination rooms. You can first call removeDestinationInfoForChannelName that belongs to the NERtcChannelMediaRelayConfiguration class to remove the rooms that you have no interest in and add new destination rooms.
 * @param config The configuration for media stream relay across rooms. For more information, see {@link NERtcChannelMediaRelayConfiguration}.
 * @return A value of 0 returned indicates that the operation is successful. Otherwise, the operation fails.
 * @endif
 * @if Chinese 
 * 更新媒体流转发的目标房间。
 * <br>成功开始跨房间转发媒体流后，如果你希望将流转发到多个目标房间，或退出当前的转发房间，可以调用该方法。
 * - 成功开始跨房间转发媒体流后，如果您需要修改目标房间，例如添加或删减目标房间等，可以调用此方法。
 * - 成功调用此方法后，SDK 会触发 onNERtcEngineChannelMediaRelayStateDidChange 回调。如果报告 NERtcChannelMediaRelayStateRunning，则表示已成功转发媒体流。
 * @note
 * - 请在加入房间并成功调用 startChannelMediaRelay 开始跨房间媒体流转发后，调用此方法。调用此方法前需要通过 config 中的 setDestinationInfo 设置目标房间。
 * - 跨房间媒体流转发最多支持 4 个目标房间，您可以在调用该方法之前，通过 NERtcChannelMediaRelayConfiguration 中的 removeDestinationInfoForChannelName 方法移除不需要的房间，再添加新的目标房间。
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
 * @return A value of 0 returned indicates that the operation is successful. Otherwise, the operation fails.
 * @endif
 * @if Chinese 
 * 停止跨房间媒体流转发。
 * <br>主播离开房间时，跨房间媒体流转发自动停止，您也可以在需要的时候随时调用 stopChannelMediaRelay 方法，此时主播会退出所有目标房间。
 * - 成功调用该方法后，SDK 会触发 onNERtcEngineChannelMediaRelayStateDidChange 回调。如果报告 NERtcChannelMediaRelayStateIdle，则表示已停止转发媒体流。
 * - 如果该方法调用不成功，SDK 会触发 onNERtcEngineChannelMediaRelayStateDidChange 回调，并报告状态码 NERtcChannelMediaRelayStateFailure。
 * @return 成功返回0，其他则失败
 * @endif
 */
- (int)stopChannelMediaRelay;

/**
 * @if English
 * Enables or disables AI super resolution.
 * @since V4.4.0
 * @note 
 * - Before you enable the AI super resolution feature, contact technical support to activate the AI super resolution feature.
 * - AI super resolution is only valid for the following types of video streams:
 * - The first 360p video stream received by the local client.
 * - The camera feeds captured into the mainstream. The AI super-resolution feature does not currently support restoration and reconstruction of small streams and screen sharing in the substream.
 * @param enabled The option whether to enable AI super resolution. By default, the setting is disabled.
 * @return {@code 0} operation is successful. Otherwise, the operation fails.
 
 * @endif
 * @if Chinese 
 * 启用或停止 AI 超分。
 * @since V4.4.0
 * @note 
 * - 使用 AI 超分功能之前，请联系技术支持开通 AI 超分功能。
 * - AI 超分仅对以下类型的视频流有效：
 * - 必须为本端接收到第一路 360P 的视频流。
 * - 必须为摄像头采集到的主流大流视频。AI 超分功能暂不支持复原重建小流和屏幕共享辅流。
 * @param enabled 是否启用 AI 超分。默认为关闭状态。
 * @return {@code 0} 方法调用成功，其他调用失败
 * @endif
 */
- (int)enableSuperResolution:(BOOL)enabled;

/**
 * @if English
 * Turns on or off media stream encryption.
 * @since V4.4.0
 * In scenarios with high security requirements such as finance, you can use this method to set the media stream encryption mode before you join the room.
 * @note 
 * - You must call this method before you join the room, the encryption mode and key cannot be modified after you join the room. After you leave the room, the SDK will automatically turn off encryption. If you need to turn on encryption again, you need to call this method before you join the room again.
 * - In the same room, all users who enable media stream encryption must use the same encryption mode and key. Otherwise, an error kNERtcErrEncryptNotSuitable (30113) occurs if the members have different keys. 
 * - For security, we recommend that you replace the new key every time the media stream encryption is enabled.
 * @param enable The option whether to enable media stream encryption.
 *              - YES: enables media stream encryption.
 *              - NO: disables media stream encryption. This is the default value.
 * @param config The configuration for media stream relay. For more information,  see NERtcEncryptionConfig.
 * @return {@code 0} operation is successful. Otherwise, the operation fails.
 * @endif
 * @if Chinese 
 * 开启或关闭媒体流加密。
 * @since V4.4.0
 * 在金融行业等安全性要求较高的场景下，您可以在加入房间前通过此方法设置媒体流加密模式。
 * @note 
 * - 请在加入房间前调用该方法，加入房间后无法修改加密模式与密钥。用户离开房间后，SDK 会自动关闭加密。如需重新开启加密，需要在用户再次加入房间前调用此方法。
 * - 同一房间内，所有开启媒体流加密的用户必须使用相同的加密模式和密钥，否则使用不同密钥的成员加入房间时会报错 kNERtcErrEncryptNotSuitable（30113）。 
 * - 安全起见，建议每次启用媒体流加密时都更换新的密钥。
 * @param enable 是否开启媒体流加密。
 *                  - YES: 开启
 *                  - NO:（默认）关闭
 * @param config 媒体流加密方案。详细信息请参考 NERtcEncryptionConfig。
 * @return {@code 0} 方法调用成功，其他调用失败
 * @endif
 */
- (int)enableEncryption:(BOOL)enable config:(NERtcEncryptionConfig *)config;

#pragma mark - NetworkQualiityTest

/** 
 * @if English 
 * Starts the last-mile network probe test.
 * <br>This method starts the last-mile network probe test before joining a channel to get the uplink and downlink last mile network statistics, including the bandwidth, packet loss, jitter, and round-trip time (RTT).This method is used to detect network quality before a call. Before a user joins a room, you can use this method to estimate the subjective experience and objective network status of a local user during an audio and video call. 
 * Once this method is enabled, the SDK returns the following callbacks:
 * - `onNERtcEngineLastmileQuality`: the SDK triggers this callback within five seconds depending on the network conditions. This callback rates the network conditions with a score and is more closely linked to the user experience.
 * - `onNERtcEngineLastmileProbeTestResult`: the SDK triggers this callback within 30 seconds depending on the network conditions. This callback returns the real-time statistics of the network conditions and is more objective.
 * @note 
 * - You can call this method before joining a channel(joinChannel).
 * - Do not call other methods before receiving the onNERtcEngineLastmileQuality and onNERtcEngineLastmileProbeTestResult callbacks. Otherwise, the callbacks may be interrupted.
 * @since V4.5.0
 * @param config    Sets the configurations of the last-mile network probe test.
 * @endif
 * @if Chinese
 * 开始通话前网络质量探测。
 * <br>启用该方法后，SDK 会通过回调方式反馈上下行网络的质量状态与质量探测报告，包括带宽、丢包率、网络抖动和往返时延等数据。一般用于通话前的网络质量探测场景，用户加入房间之前可以通过该方法预估音视频通话中本地用户的主观体验和客观网络状态。
 * <br>相关回调如下：
 * - `onNERtcEngineLastmileQuality`：网络质量状态回调，以打分形式描述上下行网络质量的主观体验。该回调视网络情况在约 5 秒内返回。
 * - `onNERtcEngineLastmileProbeTestResult`：网络质量探测报告回调，报告中通过客观数据反馈上下行网络质量。该回调视网络情况在约 30 秒内返回。
 * @note 
 * - 请在加入房间（joinChannel）前调用此方法。
 * - 调用该方法后，在收到 onNERtcEngineLastmileQuality 和 onNERtcEngineLastmileProbeTestResult 回调之前请不要调用其他方法，否则可能会由于 API 操作过于频繁导致此方法无法执行。
 * @since V4.5.0
 * @param config    Last mile 网络探测配置。
 * @return
 * - 0: 方法调用成功
 * - 其他: 调用失败
 * @endif
 */
- (int)startLastmileProbeTest:(NERtcLastmileProbeConfig *_Nullable)config;

/** 
 * @if English 
 * Stops the last-mile network probe test.
 * @since V4.5.0
 * @return
 * - 0: Success.
 * - Other values: Failure.
 * @endif
 * @if Chinese
 * 停止通话前网络质量探测。
 * @since V4.5.0
 * @return
 * - 0: 方法调用成功
 * - 其他: 调用失败
 * @endif
 */
- (int)stopLastmileProbeTest;

#pragma mark -
/** 
 * @if English 
 * Create an IRtcChannel.
 * @param[in] channelName      The name of the room. Users that use the same name can join the same room. The name must be of STRING type and must be 1 to 64 characters in length. The following 89 characters are supported: a-z, A-Z, 0-9, space,!#$%&()+-:;≤.,>?@[]^_{|}~”.
 * @return IRtcChannel
 * @endif
 * @if Chinese
 * 创建一个 IRtcChannel 对象
 * @param[in] channelName      房间名。设置相同房间名称的用户会进入同一个通话房间。字符串格式，长度为1~ 64 字节。支持以下89个字符：a-z, A-Z, 0-9, space, !#$%&()+-:;≤.,>?@[]^_{|}~”
 * @return 返回 IRtcChannel 对象
 * - 0: 方法调用失败。
* @endif
*/
- (nullable NERtcChannel *)createChannel:(NSString *)channelName;

#pragma mark - Video Effect

/**
 * @if English
 * Check if video correction is enabled
 * @since V4.6.0
 * @note
 * - If you use a camera to shoot an object, a mapping process from 3D to 2D images is implemented. the image of an object is deformed when the position of the camera changes.
 * - If you enable video correction with appropriate parameters, video images can be restored by algorithms.
 * - To use video correction, the rendering mode of the local canvas must be set to fit. Video frames keeps aspect ratio unchanged and are all displayed in the current view. Otherwise, video correction may not take effect.
 * - If the parameters of video correction are applied, the local and remote video frames are all corrected
 * @param enable Enables or disables local video correction.
 * - true：enabled
 * - false (default): disabled
 * @return 
 * - 0: success.
 * - Others: failure
 * @endif
 * @if Chinese
 * 是否启用视频图像畸变矫正。
 * @since V4.6.0
 * @note
 * - 当使用相机去拍摄物体时，存在着一个从三维世界到二维图像的映射过程，这个过程中由于相机位置的变化和移动，会对拍摄物体的成像产生一定的形变影响。
 * - 开启该功能时，根据合适的参数，可以通过算法把这个形变进行复原。
 * - 使用该功能时，本地画布的渲染模式需要为 fit（即视频帧保持自身比例不变全部显示在当前视图中），否则矫正功能可能不会正常生效。
 * - 矫正参数生效后，本地画面和对端看到的画面，均会是矫正以后的画面。
 * @param enable 是否开启视频图像矫正。
 * - true：开启视频图像矫正。
 * - false（默认）：关闭视频图像矫正。
 * @return 
 * - 0：方法调用成功。
 * - 其他：方法调用失败。
 * @endif
 */
- (int)enableVideoCorrection:(BOOL)enable;

/**
 * @if English
 * Sets the parameters for video correction.
 * @since V4.6.0
 * @note
 * - The first 4 parameters of the config schema represent coordinates of areas to be corrected on the screen. The x and y coordinates of each point ranges from 0 to 1.
 * - The last 3 parameters are required only if external video rendering is used.
 * - You can pass nill in config. If config is set to nil, the SDK clears previous configurations for video correction. The video graph will be restored to the state without correction.
 * @param config Correction parameters. For more information, see {@link NERtcVideoCorrectionConfiguration}.
 * @return 
 * - 0: success.
 * - Others: failure
 * @endif
 * @if Chinese
 * 设置视频图像矫正参数。
 * @since V4.6.0
 * @note
 * - 矫正参数结构体的前 4 个参数，代表了待矫正区域相对于屏幕上视图的坐标，每个坐标点的 x 和 y 的取值范围均为 0 ~ 1 的浮点数。
 * - 矫正参数结构体的后 3 个参数只有在使用了外部视频渲染功能时才需要传入。
 * - config 可以传入 nil，清空之前设置过的矫正参数，将画面恢复至矫正之前的效果。
 * @param config 视频图像矫正相关参数。详细说明请参考 {@link NERtcVideoCorrectionConfiguration}。
 * @return 
 * - 0：方法调用成功。
 * - 其他：方法调用失败。
 * @endif
 */
- (int)setVideoCorrectionConfig:(nullable NERtcVideoCorrectionConfiguration *)config;

/**
 * @if English
 * Enables/Disables the virtual background.
 *
 * After enabling the virtual background feature, you can replace the original background image of the local user
 * with a custom background image. After the replacement, all users in the channel can see the custom background
 * image. You can find out from the
 * RtcEngineEventHandlerEx::onVirtualBackgroundSourceEnabled "onVirtualBackgroundSourceEnabled" callback
 * whether the virtual background is successfully enabled or the cause of any errors.
 * -  Recommends that you use this function in scenarios that meet the following conditions:
 * - A high-definition camera device is used, and the environment is uniformly lit.
 * - The captured video image is uncluttered, the user's portrait is half-length and largely unobstructed, and the
 * background is a single color that differs from the color of the user's clothing.
 * - The virtual background feature does not support video in the Texture format or video obtained from custom video capture by the Push method.
 * @since V4.6.1
 * @param enable Sets whether to enable the virtual background:
 * - true: Enable.
 * - false: Disable.
 * @param backData The custom background image. See NERtcVirtualBackgroundSource.
 * Note: To adapt the resolution of the custom background image to the resolution of the SDK capturing video,
 * the SDK scales and crops
 * the custom background image while ensuring that the content of the custom background image is not distorted.
 * @return
 * - 0: Success.
 * - < 0: Failure.
 * @endif
 * @if Chinese
 * 开启/关闭虚拟背景。
 * <br>启用虚拟背景功能后，您可以使用自定义背景图片替换本地用户的原始背景图片。
 * <br>替换后，频道内所有用户都可以看到自定义背景图片。
 * @note 
 * - 您可以通过 {@link NERtcEngineDelegateEx#onNERtcEngineVirtualBackgroundSourceEnabled:reason:} 回调查看虚拟背景是否开启成功或出错原因。
 * - 建议您在满足以下条件的场景中使用该功能：
 *    - 采用高清摄像设备，环境光线均匀。
 *    - 捕获的视频图像整洁，用户肖像半长且基本无遮挡，并且背景是与用户衣服颜色不同的单一颜色。
 * - 虚拟背景功能不支持在 Texture 格式的视频或通过 Push 方法从自定义视频源获取的视频中设置虚拟背景。
 * - 若您设置背景图片为自定义本地图片，SDK 会在保证背景图片内容不变形的前提下，对图片进行一定程度上的缩放和裁剪，以适配视频采集分辨率。
 * @since V4.6.10
 * @param enable 设置是否开启虚拟背景。
 * - true：开启。
 * - false: 关闭。
 * @param backData 自定义背景图片。详细信息请参考 {@link NERtcVirtualBackgroundSource}。
 * @return
 * - 0：方法调用成功。
 * - 其他：方法调用失败。
 * @endif
 */
- (int)enableVirtualBackground:(BOOL)enable backData:(NERtcVirtualBackgroundSource *_Nullable)backData;

#pragma mark - Cloud Proxy

/**
 * @if English
 * Sets the Agora cloud proxy service.
 * <br>When the user's firewall restricts the IP address and port, refer to Use Cloud Proxy to add the specific IP addresses and ports to the firewall whitelist; then, call this method to enable the cloud proxy and set the proxyType parameter as NERtcTransportTypeUDPProxy(1), which is the cloud proxy for the UDP protocol.
 * - After a successfully cloud proxy connection, the SDK triggers the `onNERtcEngineConnectionStateChangeWithState(kNERtcConnectionStateConnecting, kNERtcReasonConnectionChangedSettingProxyServer)` callback.
 * - To disable the cloud proxy that has been set, call setCloudProxy(NERtcTransportTypeNoneProxy).
 * @note We recommend that you call this method before joining the channel or after leaving the channel.
 * @param proxyType The cloud proxy type. For more information, see {@link NERtcTransportType}. This parameter is required, and the SDK reports an error if you do not pass in a value.
 * @return A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
 * @endif
 * @if Chinese
 * 开启并设置云代理服务。
 * <br>在内网环境下，如果用户防火墙开启了网络限制，请参考《使用云代理》将指定 IP 地址和端口号加入防火墙白名单，然后调用此方法开启云代理，并将 proxyType 参数设置为 NERtcTransportTypeUDPProxy(1)，即指定使用 UDP 协议的云代理。
 * - 成功连接云代理后，SDK 会触发 `onNERtcEngineConnectionStateChangeWithState(kNERtcConnectionStateConnecting, kNERtcReasonConnectionChangedSettingProxyServer)` 回调。
 * - 如果需要关闭已设置的云代理，请调用 `setCloudProxy(NERtcTransportTypeNoneProxy)`。
 * @note 请在加入房间前调用此方法。
 * @param proxyType 云代理类型。详细信息请参考 {@link NERtcTransportType}。该参数为必填参数，若未赋值，SDK 会报错。
 * @return {@code 0} 方法调用成功，其他失败。
 * @endif
 */
- (int)setCloudProxy:(NERtcTransportType)proxyType;

/**
 * @if English
 * Synchronizes the local time with the server time
 * @since V4.6.10
 * @param enable specifies whether to enable precise synchronization of the local time with the server time.
 * - true: enables the precise synchronization
 * - false: disables the precise synchronization.
 * @return 
 * - 0: success
 * - Others: failure
 * @endif
 * @if Chinese
 * 对齐本地系统与服务端的时间。
 * @since V4.6.10
 * @param enable 是否开启精准对齐功能。
 * - true：开启精准对齐功能。
 * - false：关闭精准对齐功能。
 * @return 
 * - 0：方法调用成功。
 * - 其他：方法调用失败。
 * @endif
 */
- (void)setStreamAlignmentProperty:(BOOL)enable;

/**
 * @if English
 * Gets the difference between the local time and the server time.
 * <br>The method can sync the time between the client and server. To get the current server time, call (System.currentTimeMillis() - offset).
 * @since V4.6.10
 * @return Difference between the local time and the server time. Unit: milliseconds(ms). If a user fails to join a room, a value of 0 is returned.
 * @endif
 * @if Chinese
 * 获取本地系统时间与服务端时间差值。
 * <br>可以用于做时间对齐，通过 (毫秒级系统时间 - offset) 可能得到当前服务端时间。
 * @since V4.6.10
 * @return 本地与服务端时间差值，单位为毫秒（ms）。如果没有成功加入音视频房间，返回 0。
 * @endif
 */
- (int64_t)getNtpTimeOffset;

@end

NS_ASSUME_NONNULL_END

#endif /* INERtcEngineEx_h */
