package com.netease.lava.nertc.sdk.audio;
import com.netease.lava.nertc.sdk.NERtcEx;

/**
 * @if English
 * Observes PCM data callback. 
 * @endif
 * @if Chinese
 * PCM数据回调监听
 * @endif
 */
public interface NERtcAudioFrameObserver {

    /**
     * @if English
     * Occurs when the system captures audio data. The callback is used to perform operations such as processing audio data.
     * <br>@note
     * - The audio data that is returned has read and write permissions.
     * - The callback occurs when local audio data needs to be processed.
     * @see NERtcEx#setRecordingAudioFrameParameters(NERtcAudioFrameRequestFormat)
     * @param audioFrame    For more information about PCM audio frame data, see {@link audio.NERtcAudioFrame}.
     * @endif
     * @if Chinese
     * 采集音频数据回调，用于声音处理等操作。
     * <br><b>注意</b>：
     * - 返回音频数据支持读写。
     * - 有本地音频数据驱动就会回调。
     * @see NERtcEx#setRecordingAudioFrameParameters(NERtcAudioFrameRequestFormat)
     * @param audioFrame PCM 音频帧数据，详细信息请参考 {@link audio.NERtcAudioFrame}。
     * @endif
     */
    void onRecordFrame(NERtcAudioFrame audioFrame);


    /**
     * @if English
     * Returns the data of the local audio substream, used for the custom audio substream.
     * @note
     * - If the substream is enabled, the SDK will return the data.
     * - The returned audio data supports the write or read modes. The callback is applied for custom audio substream. A write mode is recommended.
     * @see NERtcEx#setRecordingAudioFrameParameters(NERtcAudioFrameRequestFormat)
     * @see NERtcEx#enableLocalSubStreamAudio(boolean)
     * @param audioFrame PCM audio frame data. For more information, see {@link audio.NERtcAudioFrame}.
     * @endif
     * @if Chinese
     * 本地音频辅流数据回调，用于自定义音频辅流数据。
     * @note
     * - 只要开启辅流，SDK 就会返回此回调。
     * - 返回音频数据支持读或写模式，由于该回调一般用于自定义音频辅流数据，建议使用写模式。
     * @see NERtcEx#setRecordingAudioFrameParameters(NERtcAudioFrameRequestFormat)
     * @see NERtcEx#enableLocalSubStreamAudio(boolean)
     * @param audioFrame PCM 音频帧数据，详细信息请参考 {@link audio.NERtcAudioFrame}。
     * @endif
     */
    void onRecordSubStreamAudioFrame(NERtcAudioFrame audioFrame);

    /**
     * @if English
     * Occurs when the system captures audio data. The callback is used to perform operations such as processing audio data.
     * <br>@note
     * - The audio data that is returned has read and write permissions.
     * - The callback occurs when local audio data needs to be processed. 
     * @param audioFrame    For more information about PCM audio frame data, see {@link audio.NERtcAudioFrame}.
     * @endif
     * @if Chinese
     * 播放音频数据回调，用于声音处理等操作。
     * <br><b>注意</b>：
     * - 返回音频数据支持读写。
     * - 有本地音频数据驱动就会回调。
     * @param audioFrame PCM 音频帧数据，详细信息请参考 {@link audio.NERtcAudioFrame}。
     * @endif
     */
    void onPlaybackFrame(NERtcAudioFrame audioFrame);

    /**
     * @if English
     * Gets the audio data from specific remote users before the audio stream is mixed with other audio streams. 
     * <br>After registering the audio viewer, if remote users subscribe remote audio (subscription by default) and enable it, the SDK triggers the callback to send you the audio data when the system captures video data before the mix. 
     * @note The audio data that is returned has only read permissions.
     * @deprecated This API is about to be deprecated. Use {@link NERtcAudioFrameObserver#onPlaybackAudioFrameBeforeMixingWithUserID(long, NERtcAudioFrame, long)} instead. In multi-channel scenarios, channelId is used to identify different channels.
     * @param userID        Remote users ID.
     * @param audioFrame    For information about PCM video frame data, see {@link audio.NERtcAudioFrame}.
     * @endif
     * @if Chinese
     * 获取指定远端用户混音前的音频数据。
     * <br>成功注册音频观测器后，如果订阅了远端音频（默认订阅）且远端用户开启音频后，SDK 会在捕捉到混音前的音频数据时，触发该回调，将音频数据发送给您。
     * @note  返回音频数据只读。
     * @deprecated 即将废弃，请改用接口 {@link NERtcAudioFrameObserver#onPlaybackAudioFrameBeforeMixingWithUserID(long, NERtcAudioFrame, long)}。在多房间场景下，此接口可通过 channelId 识别不同房间。 
     * @param userID 远端用户的 ID。
     * @param audioFrame PCM 音频帧数据，详细信息请参考 {@link audio.NERtcAudioFrame}。
     * @endif
     */
    @Deprecated
    void onPlaybackAudioFrameBeforeMixingWithUserID(long userID , NERtcAudioFrame audioFrame);

    /**
     * @if English
     * Gets the audio data from specific remote users before the audio stream is mixed with other audio streams.
     * <br>After registering the audio viewer, if remote users subscribe remote audio (subscription by default) and enable it, the SDK triggers the callback to send you the audio data when the system captures video data before the mix.
     * @note The audio data that is returned has only read permissions.
     * @since V4.5.0
     * @param userID        Remote users ID.
     * @param audioFrame    For information about PCM video frame data, see {@link audio.NERtcAudioFrame}.
     * @param channelId     Channel ID. In multi-channel scenarios, channelId is used to identify different channels.   
     * @endif
     * @if Chinese
     * 获取指定远端用户混音前的音频数据。
     * <br>成功注册音频观测器后，如果订阅了远端音频（默认订阅）且远端用户开启音频后，SDK 会在捕捉到混音前的音频数据时，触发该回调，将音频数据发送给您。
     * @note 返回音频数据只读。
     * @since V4.5.0
     * @param userID        远端用户的 ID。
     * @param audioFrame    PCM 音频帧数据，详细信息请参考 {@link audio.NERtcAudioFrame}。
     * @param channelId     房间 ID。在多房间场景下，channelId 用于识别不同的房间。
     * @endif
     */
    void onPlaybackAudioFrameBeforeMixingWithUserID(long userID , NERtcAudioFrame audioFrame,long channelId);

    /**
     * @if English
     * Gets raw audio data after the audio data is mixed with audio streams from local users and all remote users.
     * <br>@note
     * - The audio data that is returned has only read permissions.
     * - The callback occurs when local audio data needs to be processed.
     * @param audioFrame    For more information about PCM audio frame data, see {@link audio.NERtcAudioFrame}.
     * @endif
     * @if Chinese
     * 获取本地用户和所有远端用户混音后的原始音频数据。
     * <br><b>注意</b>
     * - 返回音频数据只读。
     * - 有本地音频数据驱动就会回调。
     * @param audioFrame PCM 音频帧数据，详细信息请参考 {@link audio.NERtcAudioFrame}。
     * @endif
     */
    void onMixedAudioFrame(NERtcAudioFrame audioFrame);


    /**
     * @if English
     * Gets the audio substream data from a specified remote user before mixing audio.
     * <br>After the audio observer is registered, the SDK will get the audio data from a specified remote user before mixing if the remote audio substream is subscribed to and the remote user publishes the audio substream.
     * @note The returned audio data can only be read.
     * @param userID     A remote user ID.
     * @param audioFrame PCM audio frame data. For more information, see {@link audio.NERtcAudioFrame}.
     * @param channelId  Room ID. For multiple rooms, channelId is used to identify the rooms.
     * @see NERtcEx#enableLocalSubStreamAudio(boolean)
     * @see NERtcEx#enableLocalSubStreamAudio(boolean)
     * @endif
     * @if Chinese
     * 获取指定远端用户混音前的音频辅流数据。
     * <br>成功注册音频观测器后，如果订阅了远端音频辅流（默认订阅）且远端用户开启音频辅流后，SDK 会在捕捉到混音前的音频数据时，触发该回调，将音频数据发送给您。
     * @note 返回的音频数据只读。
     * @param userID     远端用户的 ID。
     * @param audioFrame PCM 音频帧数据，详细信息请参考 {@link audio.NERtcAudioFrame}。
     * @param channelId  房间 ID。在多房间场景下，channelId 用于识别不同的房间。
     * @see NERtcEx#enableLocalSubStreamAudio(boolean)
     * @see NERtcEx#enableLocalSubStreamAudio(boolean)
     * @endif
     */
    void onPlaybackSubStreamAudioFrameBeforeMixingWithUserID(long userID, NERtcAudioFrame audioFrame, long channelId);


}
