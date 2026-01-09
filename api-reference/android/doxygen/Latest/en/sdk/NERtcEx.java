/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk;

import android.content.Intent;
import android.media.projection.MediaProjection;

import com.netease.lava.api.IVideoRender;
import com.netease.lava.nertc.impl.NERtcImpl;
import com.netease.lava.nertc.sdk.audio.NERtcAudioExternalFrame;
import com.netease.lava.nertc.sdk.audio.NERtcAudioFrame;
import com.netease.lava.nertc.sdk.audio.NERtcAudioFrameObserver;
import com.netease.lava.nertc.sdk.audio.NERtcAudioFrameRequestFormat;
import com.netease.lava.nertc.sdk.audio.NERtcAudioProcessObserver;
import com.netease.lava.nertc.sdk.audio.NERtcAudioRecordingConfiguration;
import com.netease.lava.nertc.sdk.audio.NERtcCreateAudioEffectOption;
import com.netease.lava.nertc.sdk.audio.NERtcCreateAudioMixingOption;
import com.netease.lava.nertc.sdk.audio.NERtcReverbParam;
import com.netease.lava.nertc.sdk.encryption.NERtcEncryptionConfig;
import com.netease.lava.nertc.sdk.channel.NERtcChannel;
import com.netease.lava.nertc.sdk.live.AddLiveTaskCallback;
import com.netease.lava.nertc.sdk.live.DeleteLiveTaskCallback;
import com.netease.lava.nertc.sdk.live.NERtcLiveStreamTaskInfo;
import com.netease.lava.nertc.sdk.live.UpdateLiveTaskCallback;
import com.netease.lava.nertc.sdk.stats.NERtcStatsObserver;
import com.netease.lava.nertc.sdk.video.NERtcBeautyEffectType;
import com.netease.lava.nertc.sdk.video.NERtcScreenConfig;
import com.netease.lava.nertc.sdk.video.NERtcTakeSnapshotCallback;
import com.netease.lava.nertc.sdk.video.NERtcVideoCallback;
import com.netease.lava.nertc.sdk.video.NERtcVideoFrame;
import com.netease.lava.nertc.sdk.video.NERtcVideoStreamType;
import com.netease.lava.nertc.sdk.video.NERtcVideoView;
import com.netease.lava.nertc.sdk.video.NERtcVirtualBackgroundSource;
import com.netease.lava.nertc.sdk.watermark.NERtcCanvasWatermarkConfig;
import com.netease.lava.nertc.sdk.watermark.NERtcVideoWatermarkConfig;

import java.nio.ByteBuffer;

/** NERtcEx */
public abstract class NERtcEx extends NERtc {

    /**
     * @if English
     * Gets a NERtc instance.
     * @return A NERtc instance is returned.
     * @endif
     * @if Chinese
     * 
     * @endif
     */
    public static NERtcEx getInstance() {
        return NERtcImpl.getInstance();
    }

    /**
     * @if English
     * Stops or resumes publishing the local audio stream.
     * <br>The method is used to stop or resume publishing the local audio stream.
     * @param mute specifies whether to enable sending the local audio stream.
     *             - true: mutes the local audio stream. This is the default value.
     *             - false: unmutes the local audio stream.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @note 
     * - This method does not change the usage state of the audio-capturing device because the audio capture devices are not disabled.
     * - The muted state is reset to unmuted after the call ends.
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
     * Stops or resumes publishing the local video stream.
     * <br>If the method is called successfully, onUserVideoMute is triggered remotely.
     * @param mute specifies whether to stop publishing the local video stream.
     *             - true: does not publish the local video stream.
     *             - false: publish the local video stream. This is the default value.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @note 
     * - If you call this method to stop publishing the local video stream, the SDK no longer sends the local video stream.
     * - The method can be called before or after a user joins a room.
     * - If you stop publishing the local video stream by calling this method, the option is reset to the default state that allows the app to publish the local video stream.
     * - The method is different from enableLocalVideo(false).  The enableLocalVideo(false) method turns off local camera devices. The muteLocalVideoStreamvideo method does not affect local video capture, or disables cameras, and responds faster.
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
     * Sends supplemental enhancement information (SEI) data through a specified primary or substream channel.
     * <br>When the local video stream is pushed, SEI data is also sent to sync some additional information. After SEI data is sent, the receiver can retrieve the content by listening to the onRecvSEIMsg callback.
     * - Condition: After the video stream (the primary stream) is enabled, the function can be invoked.
     * - Data size limits: The SEI data can contain a maximum of 4,096 bytes in size. Sending an SEI message fails if the data exceeds the size limit. If a large amount of data is sent, the video bitrate rises. This degrades the video quality or causes broken video frames.
     * - Frequency limit: We recommend that the maximum video frame rate does not exceed 10 fps.
     * - Time to take effect: After the method is called, the SEI data is sent from the next frame in the fastest fashion or after the next 5 frames at the slowest pace.
     * @param seiMsg     indicates custom SEI data.
     * @param streamType indicates the type of channel with which the SEI data is transmitted. For more information, see {@link video.NERtcVideoStreamType}.
     * @return {@code 0}    A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @note - The SEI data is transmitted together with the video stream. Frame loss may occur due to poor connection quality. The SEI data will also get lost. We recommend that you increase the times within the transmission frequency limits. This way, the receiver can get the data.
     * - Before you specify a channel to transmit the SEI data, you must first enable the data transmission channel.
     * @see NERtcCallbackEx#onRecvSEIMsg(long, String)
     * @endif
     * @if Chinese
     * 指定主流或辅流通道发送媒体增强补充信息（SEI）。
     * <br>在本端推流传输视频流数据同时，发送流媒体补充增强信息来同步一些其他附加信息。当推流方发送 SEI 后，拉流方可通过监听 onRecvSEIMsg 的回调获取 SEI 内容。
     * - 调用时机：视频流（主流）开启后，可调用此函数。
     * - 数据长度限制： SEI 最大数据长度为 4096 字节，超限会发送失败。如果频繁发送大量数据会导致视频码率增大，可能会导致视频画质下降甚至卡顿。
     * - 发送频率限制：最高为视频发送的帧率，建议不超过 10 次/秒。
     * - 生效时间：调用本接口之后，最快在下一帧视频数据帧之后发送 SEI 数据，最慢在接下来的 5 帧视频之后发送。
     * @note
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
     * @param seiMsg indicates the custom SEI data.
     * @return {@code 0}    A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @note - The SEI data is transmitted together with the video stream. Frame loss may occur due to poor connection quality. The SEI data will also get lost. We recommend that you increase the times within the transmission frequency limits. This way, the receiver can get the data.
     * - By default, the SEI is transmitted by using the mainstream channel.
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
     * @note
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
     * Switches between the front and rear cameras.
     * <br>Make sure that you call this method after the camera starts. For example, calling startVideoPreview or joinChannel.
     * @return {@code 0}    A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 切换前置/后置摄像头。
     * <br>该方法需要在相机启动后调用，例如调用 startVideoPreview 或 joinChannel 后。
     * @return {@code 0} 方法调用成功，其他调用失败
     * @endif
     */
    public abstract int switchCamera();

    /**
     * @if English 
     * Gets the current camera direction.
     * - This method is used to check whether the current camera is front-facing or rear. 
     * - Call this method after enabling the local camera. That said, you can call this method before calling startVideoPreview or joinChannel, depending on which method you use to turn on your local camera.
     * @since V4.5.0
     * @return 
     * - {@code 0}：The rear camera.
     * - Others：The front camera. 
     * @endif
     * @if Chinese
     * 查看当前摄像头配置。
     * - 该方法用于查看当前使用的摄像头为前置摄像头还是后置摄像头。
     * - 该方法需要在相机启动后调用，例如调用 startVideoPreview 或 joinChannel 后。
     * @since V4.5.0
     * @return 
     * - {@code 0}：后置摄像头
     * - 其他值：前置摄像头
     * @endif
     */
    public abstract int getCurrentCamera();

    /**
     * @if English
     * Checks whether the camera zoom function is supported.
     * <br>Make sure that you call this method after the camera starts. For example, calling startVideoPreview or joinChannel.
     * @return true: The device supports the camera zoom feature. false: The device does not support the camera zoom feature
     * @endif
     * @if Chinese
     * 检测设备当前使用的摄像头是否支持缩放功能。
     * <br>该方法需要在相机启动后调用，例如调用 startVideoPreview 或 joinChannel 后。
     * @return true:设备支持摄像头缩放功能； false: 设备不支持摄像头缩放功能。
     * @endif
     */
    public abstract boolean isCameraZoomSupported();

    /**
     * @if English
     * Sets the camera zoom ratio.
     * @param zoomValue indicates the zoom ratio supported by the camera.
     * @note - Make sure that you call this method after the camera starts. For example, calling startVideoPreview or joinChannel.
     * - Before you call this method, we recommend that you view the maximum zoom ratio supported by the camera by calling getCameraMaxZoom.
     * @endif
     * @if Chinese
     * 设置摄像头缩放比例。
     * @note
     * - 该方法需要在相机启动后调用，例如调用 startVideoPreview 或 joinChannel 后。
     * - 建议在调用本接口前，先通过 getCameraMaxZoom 查看摄像头支持的最大缩放比例，并根据实际需求合理设置需要的缩放比例。
     * @param zoomValue 摄像头缩放比例。
     * @endif
     */
    public abstract void setCameraZoomFactor(int zoomValue);

    /**
     * @if English
     * Gets the zoom ratio supported by the current camera.
     * @return The current zoom ratio is returned.
     * @endif
     * @if Chinese
     * 获取当前摄像头缩放比例。
     * @return 当前缩放比例。
     * @endif
     */
    public abstract int getCameraCurrentZoom();

    /**
     * @if English
     * Gets the maximum zoom ratio supported by the camera.
     * <br>Make sure that you call this method after the camera starts. For example, calling startVideoPreview or joinChannel.
     * @return The maximum zoom ratio supported by the camera is returned.
     * @endif
     * @if Chinese
     * 获取摄像头支持的最大缩放比例。
     * <br>该方法需要在相机启动后调用，例如调用 startVideoPreview 或 joinChannel 后。
     * @return 摄像头支持的最大视频缩放比例。
     * @endif
     */
    public abstract int getCameraMaxZoom();

    /**
     * @if English
     * Checks whether the camera flash function is supported.
     * @return true: The device supports turning on the flash.  false: the device does not support turning on the flash.
     * @note - In most cases, the app opens the front camera by default. If the front camera does not support the flash, and the method is called, false is returned. If you want to check whether the rear camera supports the flash, you must first call the switchCamera method to switch the camera.
     * - Make sure that you call this method after the camera starts. For example, calling startVideoPreview or joinChannel.
     * @endif
     * @if Chinese
     * 检测设备是否支持闪光灯常亮。
     * @note
     * - 一般情况下，App 默认开启前置摄像头，因此如果设备前置摄像头不支持闪光灯，直接使用该方法会返回 false。如果需要检查后置摄像头是否支持闪光灯，需要先使用 switchCamera 切换摄像头，再使用该方法。
     * - 该方法需要在相机启动后调用，例如调用 startVideoPreview 或 joinChannel 后。
     * @return true：设备支持闪光灯常亮；  false：设备不支持闪光灯常亮。
     * @endif
     */
    public abstract boolean isCameraTorchSupported();

    /**
     * @if English
     * Enables the camera flash function.
     * <br>Make sure that you call this method after start the camera by calling startVideoPreview or joinChannel.
     * @param on specifies whether to turn on the flash.
     *           - true: turns on the flash.
     *           - false turns off the flash.
     * @return 0: success  1:  failure  2: The device does not support flash
     * @endif
     * @if Chinese
     * 设置是否打开闪光灯。
     * <br>该方法需要在相机启动后调用，例如调用 startVideoPreview 或 joinChannel 后。
     * @param on 是否打开闪光灯。
     * - true：打开闪光灯。
     * - false：关闭闪光灯。
     * @return 0:成功  1: 失败  2:设备不支持闪光灯。
     * @endif
     */
    public abstract int setCameraTorchOn(boolean on);

    /**
     * @if English
     * Checks whether the camera exposure function is supported.
     * <br>Make sure that you call this method after the camera starts. For example, calling startVideoPreview or joinChannel.
     * @return true: enables manual exposure setting. false: The device does not support manual exposure.
     * @endif
     * @if Chinese
     * 检测设备是否支持手动曝光功能。
     * <br>该方法需要在相机启动后调用，例如调用 startVideoPreview 或 joinChannel 后。
     * @return true：设置支持手动曝光功能；false：设备不支持手动曝光功能。
     * @endif
     */
    public abstract boolean isCameraExposurePositionSupported();

    /**
     * @if English
     * Sets the camera exposure position.
     * <br>Make sure that you call this method after the camera starts. For example, calling startVideoPreview or joinChannel.
     * <br>After the method is called, the onCameraExposureChanged callback is triggered.
     * @param x indicates the x coordinate of the exposure position.
     * @param y indicates the y coordinate of the exposure position.
     * @return 0: success
     * @endif
     * @if Chinese
     * 设置手动对焦位置。
     * <br>该方法需要在相机启动后调用，例如调用 startVideoPreview 或 joinChannel 后。
     * <br>成功调用该方法后，本地会触发 onCameraFocusChanged 回调。
     * @param x 触摸点相对于视图的横坐标。
     * @param y 触摸点相对于视图的纵坐标。
     * @endif
     */
    public abstract int setCameraExposurePosition(float x, float y);

    /**
     * @if English
     * Checks whether the camera manual focus function is supported.
     * <br>Make sure that you call this method after the camera starts. For example, calling startVideoPreview or joinChannel.
     * @return true: The device supports manual focus feature. false: does not support manual focus feature.
     * @endif
     * @if Chinese
     * 检测设备是否支持手动对焦功能。
     * <br>该方法需要在相机启动后调用，例如调用 startVideoPreview 或 joinChannel 后。
     * @return true：设备支持手动对焦功能；false：设备不支持手动对焦功能。
     * @endif
     */
    public abstract boolean isCameraFocusSupported();

    /**
     * @if English
     * Sets the camera manual focus position.
     * <br>Make sure that you call this method after the camera starts. For example, calling startVideoPreview or joinChannel.
     * <br>After the method is called, the onCameraFocusChanged callback is triggered.
     * @param x indicates the horizontal coordinate of the touch point in the view.
     * @param y indicates the vertical coordinate of the touch point in the view.
     * @endif
     * @if Chinese
     * 设置手动对焦位置。
     * <br>该方法需要在相机启动后调用，例如调用 startVideoPreview 或 joinChannel 后。
     * <br>成功调用该方法后，本地会触发 onCameraFocusChanged 回调。
     * @param x 触摸点相对于视图的横坐标。
     * @param y 触摸点相对于视图的纵坐标。
     * @endif
     */
    public abstract int setCameraFocusPosition(float x, float y);

    /**
     * @if English
     * Checks whether the speakerphone is enabled.
     * @return indicates whether to enable the speakerphone.
     * - true: The speakerphone is enabled, and the audio plays from the speakerphone.
     * - false: The speakerphone is not enabled, and the audio plays from devices other than the speakerphone. For example, a headset or an earpiece.
     * @note The method can be called before or after a user joins a room.
     * @endif
     * @if Chinese
     * 检查扬声器状态启用状态。
     * @note
     * 该方法可在加入房间前后调用。
     * @return 扬声器是否开启。
     * - true：扬声器已开启，语音输出到扬声器。
     * - false：扬声器未开启，语音输出到其他音频设备，例如听筒、耳机等。
     * @endif
     */
    public abstract boolean isSpeakerphoneOn();

    /**
     * @if English
     * Enables or disables the audio playback route to the speakerphone.
     * <br>The method is used to specify whether to route audio playback to the speakerphone.
     * @param enable specifies whether to route the audio playback to loud:
     *               - true: switches to the speakerphone. If the device connects to earpieces or Bluetooth, you cannot switch to the speakerphone.
     *               - false: Switches to earpieces. If the device connects to earpieces, the audio playback is routed to the earpieces
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @note You can call this method either before or after joining a channel.
     * @endif
     * @if Chinese
     * 启用或关闭扬声器播放。
     * <br>该方法设置是否将语音路由到扬声器，即设备外放。
     * @note该方法需要在加入房间后调用。
     * @param enable 是否将音频路由到外放：
     * - true: 切换到外放。如果设备连接了耳机或蓝牙，则无法切换到外放。
     * - false: 切换到听筒。如果设备连接了耳机，则语音路由走耳机。
     * @return {@code 0} 方法调用成功，其他失败。
     * @endif
     */
    public abstract int setSpeakerphoneOn(boolean enable);

    /**
     * @if English
     * Sets the audio focus mode.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 设置音频焦点模式。
     * @return {@code 0} 方法调用成功，其他失败。
     * @endif
     */
    public abstract int setAudioFocusMode(int focusMode);

    /**
     * @if English
     * Starts recording an audio dump file.
     * <br>Audio dump files can be used to analyze audio issues.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 开始记录音频 dump。
     * <br>音频 dump 可用于分析音频问题。
     * @return {@code 0} 方法调用成功，其他失败。
     * @endif
     */
    public abstract int startAudioDump();


    /**
     * @if English
     * Starts recording an audio dump file.
     * <br>Audio dump files can be used to analyze audio issues.
     * @param dumpType dump type {@link NERtcConstants.AUDIO_DUMP_MODE}。
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 开始记录音频 dump。
     * <br>音频 dump 可用于分析音频问题。
     * @param dumpType dump 类型 详细信息请参考 {@link NERtcConstants.AUDIO_DUMP_MODE}。
     * @return {@code 0} 方法调用成功，其他失败。
     * @endif
     */
    public abstract int startAudioDumpWithType(int dumpType);

    /**
     * @if English
     * Finishes recording an audio dump file.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 结束记录音频 dump。
     * @return {@code 0} 方法调用成功，其他失败。
     * @endif
     */
    public abstract int stopAudioDump();

    /**
     * @if English
     * Sets an SDK preset audio effect.
     * <br>The method can add multiple preset audio effects to original human voices and change audio profiles.
     * @param preset indicates the preset audio effect. By default, the audio effect is disabled. For more information, see {@link audio.NERtcVoiceChangerType} .
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @note - You can call this method either before or after joining a room. By default, the audio effect is disabled after the call ends.
     * - The method conflicts with {@link #setLocalVoicePitch}. After you call this method, the voice pitch is reset to the default value 1.0.
     * @endif
     * @if Chinese
     * 设置 SDK 预设的人声的变声音效。
     * <br>设置变声音效可以将人声原音调整为多种特殊效果，改变声音特性。
     * @note
     * - 此方法在加入房间前后都能调用，通话结束后重置为默认关闭状态。
     * - 此方法和 {@link #setLocalVoicePitch} 互斥，调用此方法后，本地语音语调会恢复为默认值 1.0。
     * @param preset 预设的变声音效。默认关闭变声音效。详细信息请参考 {@link audio.NERtcVoiceChangerType} 。
     * @return {@code 0} 方法调用成功，其他失败。
     * @endif
     */
    public abstract int setAudioEffectPreset(int preset);


    /**
     * @if English
     * Sets an SDK preset voice beautifier effect.
     * <br>The method can set an SDK preset voice beautifier effect for a local user who sends an audio stream.
     * @param preset indicates the present voice beautifier effect. By default, the voice beautifier effect is disabled. For more information, see {@link audio.NERtcVoiceBeautifierType}.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @note You can call this method either before or after joining a room. By default, the audio effect is disabled after the call ends.
     * @endif
     * @if Chinese
     * 设置 SDK 预设的美声效果。
     * <br>调用该方法可以为本地发流用户设置 SDK 预设的人声美声效果。
     * @note 该方法在加入房间前后都能调用，通话结束后重置为默认关闭状态。
     * @param preset 预设的美声效果模式。默认关闭美声效果。详细信息请参考 {@link audio.NERtcVoiceBeautifierType} 。
     * @return {@code 0} 方法调用成功，其他失败。
     * @endif
     */
    public abstract int setVoiceBeautifierPreset(int preset);


    /**
     * @if English
     * Changes the voice pitch of a local speaker.
     * <br>The method changes the voice pitch of the local speaker.
     * @param pitch indicates the voice frequency. Valid values: 0.5 to 2.0. Smaller values have lower pitches. The default value is 1, which indicates that the pitch is not changed.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @note - After the call ends, the setting changes back to the default value 1.0.
     * - The method conflicts with {@link #setAudioEffectPreset}. After you call this method, the preset voice beautifier effect will be removed.
     * @endif
     * @if Chinese
     * 设置本地语音音调。
     * <br>该方法改变本地说话人声音的音调。
     * @note
     * - 通话结束后该设置会重置，默认为 1.0。
     * - 此方法与 {@link #setAudioEffectPreset} 互斥，调用此方法后，已设置的变声效果会被取消。
     * @param pitch 语音频率。可以在 [0.5, 2.0] 范围内设置。取值越小，则音调越低。默认值为 1.0，表示不需要修改音调。
     * @return {@code 0} 方法调用成功，其他失败
     * @endif
     */
    public abstract int setLocalVoicePitch(double pitch);

    /**
     * @if English
     * Sets the local voice equalization effect, or customizes center frequencies of the local voice effects.
     * @param bandFrequency sets the band frequency. Value range: 0 to 9. representing the respective 10-band center frequencies of the voice effects, including 31, 62, 125, 250, 500, 1k, 2k, 4k, 8k, and 16k Hz.
     * @param bandGain      sets the gain of each band (dB). Value range: -15 to 15. The default value is 0.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @note You can call this method either before or after joining a room. By default, the audio effect is disabled after the call ends.
     * @endif
     * @if Chinese
     * 设置本地语音音效均衡，即自定义设置本地人声均衡波段的中心频率。
     * @note 该方法在加入房间前后都能调用，通话结束后重置为默认关闭状态。
     * @param bandFrequency 频谱子带索引，取值范围是 [0-9]，分别代表 10 个频带，对应的中心频率是 [31，62，125，250，500，1k，2k，4k，8k，16k] Hz。
     * @param bandGain      每个 band 的增益，单位是 dB，每一个值的范围是 [-15，15]，默认值为 0。
     * @return {@code 0} 方法调用成功，其他失败
     * @endif
     */
    public abstract int setLocalVoiceEqualization(int bandFrequency, int bandGain);

    /**
     * @if English
     * Sets the reverb effect for the local audio stream.
     * @note The method can be called before or after a user joins a room. The setting will be reset to the default value after a call ends.
     * @since V4.6.10
     * @param param For more information, see {@link audio.NERtcReverbParam}.
     * @return 
     * - 0: success
     * - Others: failure
     * @endif
     * @if Chinese
     * 设置本地语音混响效果。
     * @note 该方法在加入房间前后都能调用，通话结束后重置为默认的关闭状态。
     * @since V4.6.10
     * @param param 详细信息请参考 {@link audio.NERtcReverbParam}。
     * @return
     * - 0：方法调用成功。
     * - 其他：方法调用失败。
     * @endif
     */
    public abstract int setLocalVoiceReverbParam(NERtcReverbParam param);

    /**
     * @if English
     * Initializes a local substream canvas.
     * <br>This method sets the video view of the local video stream on the local device. The app associates with the video view of the local substream by calling this method. During application development, in most cases, before joining a room, you must first call this method to set the local video view after the SDK is initialized.
     * @param render indicates the settings of the video canvas. For more information, see {@link video.NERtcVideoView}.
     * @return {@code 0}    A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @note 
     * - If the app uses external rendering, we recommend that you set the video view before joining the room.
     * - Before joining a room, you must call the method after the SDK is initialized.
     * - A canvas is configured for only one user.
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
     * @see NERtcVideoView。
     * @endif
     */
    public abstract int setupLocalSubStreamVideoCanvas(IVideoRender render);

    /**
     * @if English
     * Sets a remote substream canvas.
     * <br>The method associates a remote user with a substream view. You can assign a specified uid to use a corresponding canvas.
     * @param render indicates the video canvas settings.
     *               - NERtcVideoView: uses SDK built-in canvas. You can also use the IVideoRender interface to use external rendering.
     *               - setScalingType indicates the video display mode.@ note You can use NERtcVideoView to configure the setting.
     * @param uid    indicates the ID of a remote user.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @note - If the app uses external rendering, we recommend that you set the canvas after receiving the return of onUserJoined.
     * - f the app does not retrieve the ID of a remote user, the method can be called after the remote user joins the room. You can retrieve the uid of the remote user from the return of onUserJoined. You can use this method to set the substream video canvas.
     * - If the remote user exits the room, the SDK disassociates the remote user from the canvas. The setting automatically becomes invalid.
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
     * @param screenConfig                        indicates the setting for local substream transmission. For more information, see {@link video.NERtcScreenConfig}.
     * @param mediaProjectionPermissionResultData indicates the Intent of the Android screen recording request. The parameter is returned after requesting the screen recording permissions.
     * @param mediaProjectionCallback             indicates the screen recording status callback that is used to listen on the completion notification for screen recording.
     * @return RTCResult
     * @note - Starting NERTC SDK V3.9.0, the Profile parameter changes to {@link video.NERtcScreenConfig}.
     * - You can call the method only after joining a room.
     * @endif
     * @if Chinese
     * 开启屏幕共享，屏幕共享内容以辅流形式发送。
     * <br>如果您在加入房间后调用该方法开启辅流，调用成功后，远端触发 onUserSubStreamVideoStart 回调。
     * @note
     * - NERTC SDK V3.9.0 开始，Profile 参数修改为 {@link video.NERtcScreenConfig}。
     * - 只能在加入房间后调用。
     * @param screenConfig  本地辅流发送配置，详细信息请参考 {@link video.NERtcScreenConfig}。
     * @param mediaProjectionPermissionResultData Android 的录屏请求 Intent，在请求录屏权限时返回。
     * @param mediaProjectionCallback   录屏状态回调，用于监听录屏的结束通知。
     * @return RTCResult。
     * @endif
     */
    public abstract int startScreenCapture(NERtcScreenConfig screenConfig, Intent mediaProjectionPermissionResultData,
                                           MediaProjection.Callback mediaProjectionCallback);

    /**
     * @if English
     * Disables screen sharing using the substream.
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
     * @param enable specifies whether to enable audio sharing.
     * - true: enables audio sharing.
     * - false: disables audio sharing.
     * @param mediaProjectionResultIntent   Android access request Intent. The parameter is returned when a permission request is sent.
     * @param callback   returns the status of audio sharing and listens to the result of an audio sharing task.
     * @return
     * - 0: success
     * - Others: failure
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
     * Adjust the volume of the sharing audio.
     * @since V4.6.0
     * @param volume   Captured audio volume. Value range: 0 ~ 100.
     * @return
     * - 0: success
     * - Others: failure
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
     * Enables or disables the external video source.
     * <br>The method enables the external video source.
     * @param enable specifies whether to use the external video source.
     *               - true: uses the external video source.
     *               - false: does not use the external video source. This is the default value.
     * @note - The method is enabled in the internal engine and remains valid after you call the leaveChannel method. If you want to disable the setting, you must disable the setting before the next call starts.
     * - If you want to use external video sources, you must call this method before you call startScreenCapture, enableLocalVideo, and startVideoPreview.
     * - By default, the external video source uses the substream channel during screen sharing and uses the mainstream channel in scenarios other than screen sharing. In this case, the method conflicts with the channel used by the camera.
     * - If the mainstream channel is used and enabled, do not change the setting. If the substream channel is used and enabled, do not change the setting.
     * @endif
     * @if Chinese
     * 开启或关闭外部视频源数据输入。
     * <br>该方法启用外部视频数据输入功能。
     * @note
     * - 该方法设置内部引擎为启用状态，在 leaveChannel 后仍然有效。如果需要关闭该功能，需要在下次通话前调用接口关闭该功能。
     * - 如果使用了外部视频源，请在调用 startScreenCapture、enableLocalVideo 或 startVideoPreview 之前调用此 API。
     * - 屏幕共享时，外部输入视频源默认使用辅流通道；非屏幕共享时，外部输入视频源使用主流通道，此时与 Camera 互斥。
     * - 之前使用主流通道或者当前使用主流通道，且主流已经开启时，请勿更改设置。之前使用辅流通道或者当前使用辅流通道，且辅流已经开启时，请勿更改设置。
     * @param enable 是否使用外部视频源。
     * - true：使用外部视频源。
     * - false：（默认）不使用外部视频源。
     * @endif
     */
    public abstract int setExternalVideoSource(boolean enable);

    /**
     * @if English
     * Pushes the external video frames.
     * <br>The method actively pushes the data of video frames that are encapsulated with the NERtcVideoFrame class to the SDK. Make sure that you have already called setExternalVideoSource with a value of true before you call this method. Otherwise, an error message is repeatedly prompted if you call the method.
     * @param frame indicates the information about video frame data. For more information, see {@link video.NERtcVideoFrame}.
     * @return true: success. false: failure.
     * @note The method enables the internal engine. The setting becomes invalid after you call the leaveChannel method.
     * @endif
     * @if Chinese
     * 推送外部视频帧。
     * <br>该方法主动将视频帧数据用 NERtcVideoFrame 类封装后传递给 SDK。
     * @note 
     * - 该方法设置内部引擎为启用状态，在 leaveChannel 后不再有效。
     * - 请确保在你调用本方法前已调用 setExternalVideoSource，并将参数设为 true，否则调用本方法后会一直报错。
     * @param frame 外部视频帧的数据信息。详细信息请参考 {@link video.NERtcVideoFrame}。
     * @return 
     * - true：该帧推送成功；
     * - false：该帧推送不成功。
     * @endif
     */
    public abstract boolean pushExternalVideoFrame(NERtcVideoFrame frame);

    /**
     * @if English
     * Sets the external audio source.
     * <br>After you call the method, the setting becomes invalid if you choose an audio input device or a sudden restart occurs. After the method is called, you can call pushExternalAudioFrame to send the pulse-code modulation (PCM) data.
     * @param enabled     specifies whether to enable external data input. The default value is false.
     *                    <br>Valid values:
     *                    - true: enables external data input and uses external audio source.
     *                    - false: disables external data input and does not use external video source. This is the default value.
     * @param sample_rate indicates the sample rate of the external audio source. Unit: Hz. Recommend values: 8000, 16000, 32000, 44100, and 48000.
     *                    <br>**NOTE**: If you call the method to disable the functionality, you can pass in a random value. In this case, the setting does not take effect.
     * @param channels    indicates the number of audio channels Valid values:
     *                    - 1: mono
     *                    - 2: stereo
     *                    <br>**NOTE**: If you call the method to disable the functionality, you can pass in a random value. In this case, the setting does not take effect.
     * @return - 0: success
     * - Other values: failure
     * @note - You can call the method only after joining a room.
     * - The method enables the internal engine. The virtual component works instead of the physical speaker. The setting remains valid after you call the leaveChannel method. If you want to disable the feature, you must disable the setting before the next call starts.
     * - After you enable the external audio data input, some functionalities of the speakerphone supported by the SDK are replaced by the external audio source. Settings that are applied to the speakerphone become invalid or do not take effect in calls. For example, you can hear the external data input when you use loopback for testing.
     * @endif
     * @if Chinese
     * 开启或关闭外部音频源数据输入。
     * <br>当该方法调用成功后，音频输入设备选择和异常重启会失效。调用成功后可以使用 pushExternalAudioFrame 接口发送音频 PCM 数据。
     * @note
     * - 请在加入房间前调用该方法。
     * - 该方法设置内部引擎为启用状态，启动时将用虚拟设备代替麦克风工作，在 leaveChannel 后仍然有效。如果需要关闭该功能，需要在下次通话前调用接口关闭外部音频数据输入功能。
     * - 启用外部音频数据输入功能后，SDK 内部实现部分麦克风由外部输入数据代替，麦克风相关的设置会失败或不在通话中生效。例如进行 loopback 检测时，会听到输入的外部数据。
     * @param enabled     是否开启外部数据输入。默认为 false。
     *                      <br>可设置为：
     *                      - true：开启外部数据输入，使用外部视频源。
     *                      - false：（默认）关闭外部数据输入，不使用外部视频源。
     * @param sample_rate 外部音频源的数据采样率，单位为 Hz。建议设置为 8000，16000，32000，44100 或 48000。
     *                      @note 调用接口关闭功能时可传入任意合法值，此时设置不会生效。
     * @param channels    外部音频源的数据声道数。可设置为：
     *                      - 1：单声道。
     *                      - 2：双声道。
     *                      @note调用接口关闭功能时可传入任意合法值，此时设置不会生效。
     * @return 
     * - 0: 方法调用成功；
     * - 其他: 方法调用失败。
     * @endif
     */
    public abstract int setExternalAudioSource(boolean enabled, int sample_rate, int channels);


    /**
     * @if English
     * Pushes the external audio frame.
     * <br>The method pushed the external audio frame data to the internal audio engine. If you enable the external audio data source by calling setExternalAudioSource, you can use pushExternalAudioFrame to send audio PCM data.
     * @param frame indicates the audio frame data.
     * @return - 0: success.
     * - Other values: failure
     * @note - You can call this method after joining a room.
     * -  We recommend that you set the duration of data frames to match a cycle of 10 ms.
     * - The method becomes invalid if the audio input device is turned off. For example, disable local audio, end calls, and shut off the microphone test before calls.
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
    public abstract int pushExternalAudioFrame(NERtcAudioExternalFrame frame);

    /**
     * @if English
     * Sets external audio rendering.
     * <br>The method is suitable for scenarios that require personalized audio rendering. By default, the setting is disabled. If you choose an audio playback device or a sudden restart occurs, the setting becomes invalid. After you call the method, you can use pullExternalAudioFrame to get audio PCM data.
     * @param enable     specifies whether to enable external audio rendering.
     *                   - true: enables external audio rendering.
     *                   - false: disables external audio rendering. This is the default value.
     * @param sampleRate indicates the sample rate of the external audio rendering. Unit: Hz. Valid values: 16000, 32000, 44100, and 48000.
     *                   <br>**NOTE**: If you call the method to disable the functionality, you can pass in a random value. In this case, the setting does not take effect.
     * @param channels   indicates the channel of external audio rendering. Valid values:
     *                   - 1: mono
     *                   - 2: stereo
     *                   <br>**NOTE**: If you call the method to disable the functionality, you can pass in a random value. In this case, the setting does not take effect.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @note - You can call the method only after joining a room.
     * - The method enables the internal engine. The virtual component works instead of the physical speaker. The setting remains valid after you call the leaveChannel method. If you want to disable the functionality, you must disable the functionality before the next call starts.
     * - After you enable the external audio rendering, some functionalities of the speakerphone supported by the SDK are replaced by the external audio source. Settings that are applied to the speakerphone become invalid or do not take effect in calls. For example, external rendering is required to play the external audio when you use loopback for testing.
     * @endif
     * @if Chinese
     * 设置外部音频渲染。
     * <br>该方法适用于需要自行渲染音频的场景。默认为关闭状态。当该方法调用成功后，音频播放设备选择和异常重启失效。 调用成功后可以使用 pullExternalAudioFrame 接口获取音频 PCM 数据。
     * @note
     * - 请在加入房间前调用该方法。
     * - 该方法设置内部引擎为启用状态，启动时将用虚拟设备代替扬声器工作，在leaveChannel 后仍然有效。如果需要关闭该功能，需要在下次通话前调用接口关闭外部音频数据渲染功能。
     * - 启用外部音频渲染功能后，SDK 内部实现部分扬声器由外部输入数据代替，扬声器相关的设置会失败或不在通话中生效。例如进行 loopback 检测时，需要由外部渲染播放。
     * @param enable     设置是否开启外部音频渲染：
     *                      - true：开启外部音频渲染。
     *                      - false：（默认）关闭外部音频渲染。
     * @param sampleRate 外部音频渲染的采样率 (Hz)，可设置为 16000，32000，44100 或 48000。
     *                      @note 调用接口关闭功能时可传入任意合法值，此时设置不会生效。
     * @param channels   外部音频渲染的声道数，可设置为：
     *                      - 1：单声道
     *                      - 2：双声道
     *                      @note 调用接口关闭功能时可传入任意合法值，此时设置不会生效。
     * @return {@code 0} 方法调用成功，其他调用失败。
     * @endif
     */
    public abstract int setExternalAudioRender(boolean enable, int sampleRate, int channels);

    /**
     * @if English
     * Pulls the external audio data
     * <br>The method pulls the audio data from the internal audio engine. After you enable the external audio data rendering functionality by calling setExternalAudioRender, you can use pullExternalAudioFrame to get the audio PCM data.
     * @param buffer indicates the required buffer array. The caller must use ByteBuffer.allocateDirect to create the array.
     * @param len    indicates the size of the audio data that are pulled. Unit: bytes. We recommend that the duration of the audio data at least last 10 ms, and the data size cannot exceed 7680 bytes.
     *               <br>Formula: len = sampleRate/1000 × 2 × channels × duration of the audio data in milliseconds
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @note - You can call this method after joining a room.
     * -  We recommend that you set the duration of data frames to match a cycle of 10 ms.
     * - The method becomes invalid if the audio rendering device is turned off. In this case, no data is returned. For example, a call ends, and the speakerphone is shut off before a call starts.
     * @endif
     * @if Chinese
     * 拉取外部音频数据。
     * <br>该方法将从内部引擎拉取音频数据。 通过 setExternalAudioRender 启用外部音频数据渲染功能成功后，可以使用 pullExternalAudioFrame 接口获取音频 PCM 数据。
     * @note
     * - 该方法需要在加入房间后调用。
     * - 数据帧时长建议匹配 10ms 周期。
     * - 该方法在音频渲染设备关闭后不再生效，此时会返回空数据。例如通话结束、通话前扬声器设备测试关闭等情况下，该设置不再生效。
     * @param buffer 需要填充的 buffer 数组。需要调用方创建，且必须使用 ByteBuffer.allocateDirect 创建。
     * @param len    待拉取音频数据的字节数，单位为 byte。建议音频数据的时长至少为 10 毫秒，数据长度不能超过 7680字节。
     *                计算公式为： len = sampleRate/1000 × 2 × channels × 音频数据时长（毫秒）。
     * @return {@code 0} 方法调用成功，其他调用失败
     * @endif
     */
    public abstract int pullExternalAudioFrame(ByteBuffer buffer, int len);

    /**
     * @if English
     * Sets video capture callbacks, such as beautifier.
     * @param videoCallback   indicates the video capture callbacks. For more information, see {@link video.NERtcVideoCallback}.
     * @param textureWithI420 specifies whether to return data in YUV I420 and Texture format. The parameter is required only if a third-party filter is used. By default, the SDK only returns data in Texture format.
     *                        <br>**NOTE**: The operation is time-consuming.
     * @endif
     * @if Chinese
     * 设置视频采集数据回调，用于美颜等操作。
     * @param videoCallback   视频采集数据，详细信息请参考 {@link video.NERtcVideoCallback}。
     * @param textureWithI420 是否需要同时返回 YUV I420 和 Texture 格式的数据，仅在第三方滤镜率需要 YUV 数据时设置。默认情况下 SDK 仅返回 Texture 数据。
     *                          @note 该操作会有一定耗时。
     * @endif
     */
    public abstract void setVideoCallback(NERtcVideoCallback videoCallback, boolean textureWithI420);

    /**
     * @if English
     * Registers the audio observer object.
     * <br>The method sets a PCM data callback for audio capture or play. The method processes audio streams. The method can register the callback that is returned by the audio engine, such as onPlaybackFrame.
     * @param observer indicates the object instance.
     *                 <br>If you pass in NULL, you cancel the registration and clear the settings of {@link audio.NERtcAudioFrameRequestFormat}. For more information, see {@link audio.NERtcAudioFrameObserver}.
     * @return RTCResult
     * @note you can set or modify the method before or after joining a room.
     * @endif
     * @if Chinese
     * 注册语音观测器对象。
     * <br>该方法用于设置音频采集/播放 PCM 回调，可用于声音处理等操作。当需要引擎返回 {@link NERtcAudioFrameObserver#onPlaybackFrame()} 或 {@link NERtcAudioFrameObserver#onRecordFrame()} 回调时，需要使用该方法注册回调。
     * @note 该方法在加入房间前后均可设置或修改。
     * @param observer 接口对象实例。
     *                  如果传入 NULL，则取消注册，同时会清理 {@link audio.NERtcAudioFrameRequestFormat} 相关设置。详细信息请参考 {@link audio.NERtcAudioFrameObserver}。
     * @return
     * - 0: 方法调用成功；
     * - 其他: 方法调用失败。
     * @endif
     */
    public abstract int setAudioFrameObserver(NERtcAudioFrameObserver observer);

    /**
     * @if English
     * Registers the audio processing observer object.
     * <br>After the method registers the object of the audio processing observer, if howling is detected, the onAudioHasHowling callback is triggered.
     * @param audioProcessObserver registers the object of the audio processing observer. For more information {@link audio.NERtcAudioProcessObserver}。
     * @return RTCResult
     * @endif
     * @if Chinese
     * 注册音频处理观测器对象。
     * <br>调用本方法注册音频处理观测器后，如果检测到啸叫，会发送 onAudioHasHowling 回调。
     * @param audioProcessObserver 注册音频处理观测器对象。详细说明请参考 {@link audio.NERtcAudioProcessObserver}。
     * @return RTCResult
     * @endif
     */
    public abstract int setAudioProcessObserver(NERtcAudioProcessObserver audioProcessObserver);


    /**
     * @if English
     * Sets the audio sampling format
     * <br>The method sets the format of the captured audio file that is passed in {@link NERtcEx#setAudioFrameObserver}.
     * @param format specifies {@link NERtcAudioFrameObserver#onRecordFrame(com.netease.lava.nertc.sdk.audio.NERtcAudioFrame)}
     *               <br>for the sample rate and channel count of the returned data. A value of NULL is allowed. The default value is NULL. For more information, see {@link audio.NERtcAudioFrameRequestFormat}.
     * @return - 0: success.
     * - Other values: failure
     * @note - The method can be called before or after a user joins a room.
     * - The format is reset to empty if listening is canceled or leaveChannel is called.
     * @endif
     * @if Chinese
     * 设置采集的音频格式。
     * <br>该方法设置 {@link NERtcEx#setAudioFrameObserver} 回调的采集声音格式。
     * <br><b>注意：</b>
     * - 该方法在加入房间前后均可设置或修改。
     * - 取消监听，重置为空。
     * @param format 指定 {@link NERtcAudioFrameObserver#onRecordFrame(com.netease.lava.nertc.sdk.audio.NERtcAudioFrame)} 中返回数据的采样率和数据的通道数。允许传入 NULL，默认为 NULL。详细信息请参考 {@link audio.NERtcAudioFrameRequestFormat}。
     * @return 
     * - 0: 方法调用成功。
     * - 其他: 方法调用失败。
     * @endif
     */
    public abstract int setRecordingAudioFrameParameters(NERtcAudioFrameRequestFormat format);

    /**
     * @if English
     * Sets the audio playback format.
     * <br>The method sets the format of audio playback that is passed in {@link NERtcEx#setAudioFrameObserver}.
     * @param format specifies {@link NERtcAudioFrameObserver#onPlaybackFrame(com.netease.lava.nertc.sdk.audio.NERtcAudioFrame)}
     *               <br>for the sample rate and channel count of the returned data. A value of NULL is allowed. The default value is NULL. For more information, see {@link audio.NERtcAudioFrameRequestFormat}.
     * @return - 0: success
     * - Other values: failure
     * @note - The method can be called before or after a user joins a room.
     * - The format is reset to empty if listening is canceled or leaveChannel is called.
     * @endif
     * @if Chinese
     * 设置播放的声音格式。
     * <br>该方法设置 {@link NERtcAudioFrameObserver#onPlaybackFrame} 回调的播放声音格式。
     * @note
     * - 该方法在加入房间前后均可设置或修改。
     * - 取消监听，重置为空。
     * @param format 指定 {@link NERtcAudioFrameObserver#onPlaybackFrame(com.netease.lava.nertc.sdk.audio.NERtcAudioFrame)}
     *               中返回数据的采样率和数据的通道数。允许传入 NULL，默认为 NULL。详细信息请参考 {@link audio.NERtcAudioFrameRequestFormat}。
     * @return 
     * - 0: 方法调用成功；
     * - 其他: 方法调用失败。
     * @endif
     */
    public abstract int setPlaybackAudioFrameParameters(NERtcAudioFrameRequestFormat format);

    /**
     * @if English
     * Sets the mixed audio format.
     * <br>The method sets the mixed audio format that is passed in {@link NERtcEx#setAudioFrameObserver}.
     * @param format specifies {@link NERtcAudioFrameObserver#onMixedAudioFrame(NERtcAudioFrame)} (com.netease.lava.nertc.sdk.audio.NERtcAudioFrame)}
     *               <br>for the sample rate and channel count of the returned data. A value of NULL is allowed. The default value is NULL.
     * @return - 0: success. Other values: failure
     * @note - The method can be called before or after a user joins a room. The format is reset to empty after leaveChannel is called.
     * - You can set sample rate.
     * - If you do not use the method to set the format, the default value of the sample rate supported by the SDK is returned.
     * @endif
     * @if Chinese
     * 设置录制和播放声音混音后的数据格式。
     * <br>该方法设置 {@link NERtcAudioFrameObserver#onMixedAudioFrame} 回调的声音格式。
     * @note
     * - 该方法在加入房间前后均可设置或修改。{@link NERtc#leaveChannel()} 后重置为空。
     * - 目前只支持设置采样率。
     * - 未调用该接口设置数据格式时，回调中的采样率返回 SDK 默认值。
     * @param format 指定 {@link NERtcAudioFrameObserver#onMixedAudioFrame(NERtcAudioFrame)(com.netease.lava.nertc.sdk.audio.NERtcAudioFrame)}
     *               中返回数据的采样率和数据的通道数。允许传入 NULL，默认为 NULL。详细信息请参考 NERtcAudioFrameRequestFormat。
     * @return 
     * - 0: 方法调用成功；
     * - 其他: 方法调用失败。
     * @endif
     */
    public abstract int setMixedAudioFrameParameters(NERtcAudioFrameRequestFormat format);

    /**
     * @if English
     * Integrate a specific operation in a thread that contains GLContext.
     * <br>The method is required if a third-party filter is used. For example, remove the resources used by the third-party filter.
     * @param runnable specifies a specific task.
     * @endif
     * @if Chinese
     * 将操作设置到具有GLContext的线程中。
     * <br>第三方滤镜时需要用到，例如销毁第三方滤镜资源。
     * @param runnable 具体操作任务。
     * @endif
     */
    public abstract void postOnGLThread(Runnable runnable);

    /**
     * @if English
     * Registers a stats observer and set the stats callback.
     * @param statsObserver specifies the stats observer. For more information {@link stats.NERtcStatsObserver}。
     * @endif
     * @if Chinese
     * 注册统计信息观测器，设置统计信息回调。
     * @param statsObserver 统计信息观测器。详细信息请参考 {@link stats.NERtcStatsObserver}。
     * @endif
     */
    public abstract void setStatsObserver(NERtcStatsObserver statsObserver);

    /**
     * @if English
     * Enables reporting users' volume indication.
     * <br>The method allows the SDK to report to the app the information about the volume of the user that pushes local streams and the remote user (up to three users) that has the highest instantaneous volume. The information about the current speaker and the volume is reported.
     * <br>If this method is enabled. When a user joins a room and pushes streams, the SDK triggers the onRemoteAudioVolumeIndication callback based on the preset time intervals.
     * @param enable   specifies whether to prompt the speaker volume.
     *                 - true: enables the speaker volume.
     *                 - false: disables the speaker volume.
     * @param interval specifies the time interval at which volume prompt is displayed. Unit: milliseconds. The value must be multiples of 100 milliseconds. We recommend that you set the value 200 milliseconds or more.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @see NERtcCallbackEx.onRemoteAudioVolumeIndication
     * @endif
     * @if Chinese
     * 启用说话者音量提示。
     * <br>该方法允许 SDK 定期向 App 反馈本地发流用户和瞬时音量最高的远端用户（最多 3 位）的音量相关信息，即当前谁在说话以及说话者的音量。
     * <br>启用该方法后，只要房间内有发流用户，无论是否有人说话，SDK 都会在加入房间后根据预设的时间间隔触发 onRemoteAudioVolumeIndication 回调。
     * <br>{@see NERtcCallbackEx.onRemoteAudioVolumeIndication}
     * @param enable   是否启用说话者音量提示。
     *                  - true：启用说话者音量提示。
     *                  - false：关闭说话者音量提示。
     * @param interval 指定音量提示的时间间隔。单位为毫秒。必须设置为 100 毫秒的整数倍值，建议设置为 200 毫秒以上。
     * @return {@code 0} 方法调用成功，其他失败。
     * @endif
     */
    public abstract int enableAudioVolumeIndication(boolean enable, int interval);

    /**
     * @if English
     * Enables reporting users' volume indication.
     * <br>The method allows the SDK to report to the app the information about the volume of the user that pushes local streams and the remote user (up to three users) that has the highest instantaneous volume. The information about the current speaker and the volume is reported.
     * <br>If this method is enabled. When a user joins a room and pushes streams, the SDK triggers the onRemoteAudioVolumeIndication callback based on the preset time intervals.
     * @since V4.6.10
     * @param enable   specifies whether to prompt the speaker volume.
     *                 - true: enables the speaker volume.
     *                 - false: disables the speaker volume.
     * @param interval specifies the time interval at which volume prompt is displayed. Unit: milliseconds. The value must be multiples of 100 milliseconds. We recommend that you set the value 200 milliseconds or more.
     * @param enableVad specifies whether to enable voice activity detection(VAD).
     *                  - true：enable VAD
     *                  - false：disable VAD
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @see NERtcCallbackEx.onRemoteAudioVolumeIndication
     * @endif
     * @if Chinese
     * 启用说话者音量提示。
     * <br>该方法允许 SDK 定期向 App 反馈本地发流用户和瞬时音量最高的远端用户（最多 3 位）的音量相关信息，即当前谁在说话以及说话者的音量。
     * <br>启用该方法后，只要房间内有发流用户，无论是否有人说话，SDK 都会在加入房间后根据预设的时间间隔触发 {@link NERtcCallbackEx.onRemoteAudioVolumeIndication} 回调。
     * @since V4.6.10
     * @param enable   是否启用说话者音量提示。
     *                  - true：启用说话者音量提示。
     *                  - false：关闭说话者音量提示。
     * @param interval 指定音量提示的时间间隔。单位为毫秒。必须设置为 100 毫秒的整数倍值，建议设置为 200 毫秒以上。
     * @param enableVad 是否启用本地采集人声监测。
     *                  - true：启用本地采集人声监测。
     *                  - false：关闭本地采集人声监测。
     * @return 
     * - 0：方法调用成功。
     * - 其他：方法调用失败。
     * @endif
     */
    public abstract int enableAudioVolumeIndication(boolean enable, int interval, boolean enableVad);

    /**
     * @if English
     * Adjusts the volume of captured signals.
     * @param volume specifies the volume of captured signals. Valid values: 0 to 400. For which:
     *               - 0: muted.
     *               - 100: the original volume. This is the default value.
     *               - 400: The maximum value can be four times the original volume. The limit value is protected.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 调节采集信号音量。
     * @param volume 采集信号音量，取值范围为 [0, 400]。其中：
     *                  - 0：静音。
     *                  - 100：（默认）原始音量。
     *                  - 400：最大可为原始音量的 4 倍（自带溢出保护）。
     * @return
     * - 0: 方法调用成功；
     * - 其他: 方法调用失败。
     * @endif
     */
    public abstract int adjustRecordingSignalVolume(int volume);

    /**
     * @if English
     * Adjusts the playback signal volume of all remote users.
     * @param volume indicates the playback signal volume. Valid range: 0 to 400. For which:
     *               - 0: muted
     *               - 100: the original volume. This is the default value.
     *               - 400: The maximum value can be four times the original volume. The limit value is protected.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 调节本地播放的所有远端用户信号音量。
     * @param volume 播放音量。取值范围为 [0, 400]。其中：
     *                  - 0：静音。
     *                  - 100：（默认）原始音量。
     *                  - 400：最大可为原始音量的 4 倍（自带溢出保护）。
     * @return
     * - 0: 方法调用成功；
     * - 其他: 方法调用失败。
     * @endif
     */
    public abstract int adjustPlaybackSignalVolume(int volume);

    //*************** Mixing audio **************//

    /**
     * @if English
     * Starts playing and mixing the music file.
     * <br>This method mixes the specified local or online audio file with the audio stream from the microphone.
     * <br>- Supported audio formats: MP3、M4A、AAC、3GP、WMA, and WAV. Files that are stored in a local SD card or URLs are supported.
     * - If the playback status changes, the onAudioMixingStateChanged callback is triggered.
     * @param option specifies the option when you configure a mixing task, such as the type of mixing tasks, and the full path or URL of a mixing file. For more information, see {@link audio.NERtcCreateAudioMixingOption}.
     * @return {@code 0}    A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @note - You can call this method after joining a room.
     * - From V4.3.0, If you call this method to play a music file during a call, and manually set the volume of the backing track or audio stream that is pushed, the setting is used when you call the method again during the current call.
     * - In V4.4.0, the operation to enable or disable the local audio capture does not affect playback of the music file. If you have called enableLocalAudio(NO), you can still play back the music file by calling startAudioMixing.
     * @endif
     * @if Chinese
     * 开始播放音乐文件。
     * <br>该方法指定本地或在线音频文件来和录音设备采集的音频流进行混音。
     * <br>支持的音乐文件类型包括 MP3、M4A、AAC、3GP、WMA 和 WAV 格式，支持本地文件或在线 URL。
     * - 播放状态改变时，本地会触发 onAudioMixingStateChanged 回调。
     * @note 
     * - 请在加入房间后调用该方法。
     * - 从 V4.3.0 版本开始，若您在通话中途调用此接口播放音乐文件时，手动设置了伴音播放音量或发送音量，则当前通话中再次调用时默认沿用此设置。
     * - 在 V4.4.0 版本中，开启或关闭本地音频采集的操作不再影响音乐文件在远端的播放，即 enableLocalAudio(false) 后仍旧可以播放音乐文件。在其他版本中，必须开启音频采集才能发送伴音。
     * @param option 创建混音任务配置的选项，包括混音任务类型、混音文件全路径或 URL 等，详细信息请参考 {@link audio.NERtcCreateAudioMixingOption}。
     * @return 
     * - 0: 方法调用成功；
     * - 其他: 方法调用失败。详细信息请参考 {@link NERtcConstants.AudioMixingError}。
     * @endif
     */
    public abstract int startAudioMixing(NERtcCreateAudioMixingOption option);

    /**
     * @if English
     * Stops playing or mixing the music file.
     * <br>The method stops playing backing tracks You can call the method when you are in a room.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 停止播放音乐文件及混音。
     * <br>该方法停止播放伴奏。请在房间内调用该方法。
     * @return {@code 0} 方法调用成功，其他失败。
     * @endif
     */
    public abstract int stopAudioMixing();

    /**
     * @if English
     * Pauses playing and mixing the music file.
     * <br>The method pauses backing track playback. You can call the method when you are in a room.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 暂停播放音乐文件及混音。
     * <br>该方法暂停播放伴奏。请在房间内调用该方法。
     * @return {@code 0} 方法调用成功，其他失败。
     * @endif
     */
    public abstract int pauseAudioMixing();

    /**
     * @if English
     * Resumes playing and mixing the music file.
     * <br>The method resumes mixing audio playback and continues playing the backing track. You can call the method when you are in a room.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 恢复播放伴奏。
     * <br>该方法恢复混音，继续播放伴奏。请在房间内调用该方法。
     * @return {@code 0} 方法调用成功，其他失败。
     * @endif
     */
    public abstract int resumeAudioMixing();

    /**
     * @if English
     * Adjusts the audio mixing volume for publishing.
     * <br>The method adjusts the audio mixing volume for publishing. You can call the method when you are in a room.
     * @param volume specifies the audio mixing volume for publishing. Valid values: 0 to 200. The default value of 100 indicates the original volume.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 调节伴奏发送音量。
     * <br>该方法调节混音里伴奏的发送音量大小。请在房间内调用该方法。
     * @param volume  伴奏发送音量。取值范围为 0~200。默认 100，即原始文件音量。
     * @return {@code 0} 方法调用成功，其他失败。
     * @endif
     */
    public abstract int setAudioMixingSendVolume(int volume);

    /**
     * @if English
     * Gets the audio mixing volume for publishing.
     * <br>The method gets the audio mixing volume for publishing. You can call the method when you are in a room.
     * @return specifies the current mixing audio volume for publishing.
     * @endif
     * @if Chinese
     * 获取伴奏发送音量。
     * <br>该方法获取混音里伴奏的发送音量大小。请在房间内调用该方法。
     * @return 当前伴奏发送音量。
     * @endif
     */
    public abstract int getAudioMixingSendVolume();

    /**
     * @if English
     * Adjusts the volume of audio mixing for local playback.
     * <br>The method adjusts the volume of audio mixing for local playback. You can call the method when you are in a room.
     * @param volume specifies the audio mixing volume for publishing. Valid values: 0 to 200. The default value of 100 indicates the original volume.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 调节伴奏播放音量。
     * <br>该方法调节混音里伴奏的播放音量大小。请在房间内调用该方法。
     * @param volume  伴奏发送音量。取值范围为 0~200。默认 100，即原始文件音量。
     * @return {@code 0} 方法调用成功，其他失败。
     * @endif
     */
    public abstract int setAudioMixingPlaybackVolume(int volume);

    /**
     * @if English
     * Gets the volume of audio mixing for local playback.
     * <br>The method gets the volume of audio mixing for local playback. You can call the method when you are in a room.
     * @return The volume of current backing track playback.
     * @endif
     * @if Chinese
     * 获取伴奏播放音量。
     * <br>该方法获取混音里伴奏的播放音量大小。请在房间内调用该方法。
     * @return 当前伴奏播放音量。
     * @endif
     */
    public abstract int getAudioMixingPlaybackVolume();

    /**
     * @if English
     * Gets the duration of the music file.
     * <br>The method gets the duration of the music file. Unit: milliseconds.
     * @return The duration of the music file. Unit: milliseconds.
     * @note - You must join the room when you call this method.
     * - The methods related to audio are invoked in asynchronous fashion. If you call this method at the beginning of audio playback, the duration returned may be less or equal to 0. You can try to call the method again if you have such issues.
     * @endif
     * @if Chinese
     * 获取伴奏时长。
     * <br>该方法获取伴奏时长，单位为毫秒。
     * @note
     * - 请在加入房间后调用该方法。
     * - 伴音相关方法为异步加载，刚开始伴音时，如果立即调用此方法，获取到的伴奏时长可能为小于或等于 0 。如果遇到此类问题，请稍后重试。
     * @return 伴奏时长，单位为毫秒。
     * @endif
     */
    public abstract long getAudioMixingDuration();

    /**
     * @if English
     * Gets the playback position of the music file.
     * <br>The method gets the playback position of the music file. Unit: milliseconds. You can call the method when you are in a room.
     * @return The playback position of the music file. Unit: milliseconds.
     * @endif
     * @if Chinese
     * 获取音乐文件的播放进度。
     * <br>该方法获取当前伴奏播放进度，单位为毫秒。请在房间内调用该方法。
     * @return 音乐文件的播放位置，单位为毫秒。
     * @endif
     */
    public abstract long getAudioMixingCurrentPosition();

    /**
     * @if English
     * Sets the playback position of the music file to a different starting position.
     * <br>The method sets the playback position of the music file to a different starting position. The method allows you to play the music file from the position based on your requirements rather than from the beginning of the music file.
     * @param position specifies the playback position of the music file. Unit: milliseconds.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 设置音乐文件的播放位置。
     * <br>该方法可以设置音频文件的播放位置，这样你可以根据实际情况播放文件，而非从头到尾播放整个文件。
     * @param position 音乐文件的播放位置，单位为毫秒。
     * @return {@code 0} 方法调用成功，其他失败。
     * @endif
     */
    public abstract int setAudioMixingPosition(long position);

    //***************** Audio effects *******************//

    /**
     * @if English
     * Plays a specified local or online audio effect file.
     * <br>The method Plays a specified local or online audio effect file.
     * <br>- If the method is called and playback ends, the onAudioEffectFinished playback is triggered.
     * - Supported audio formats: MP3、M4A、AAC、3GP、WMA, and WAV. Files that are stored in local SD cards or online URLs are supported.
     * @param effectId indicates the ID of the specified audio effect. Each audio effect has a unique ID.
     * @param option   indicates the parameters, such as the type of audio mixing tasks, and path of the mixing audio file.
     *                 <br>After you call this method to play audio effect files and repeatedly pause or resume play the audio effect file with the same effectId, the option setting takes effect only for the first time. The option setting becomes invalid for subsequent playbacks.
     *                 <br>For more information, see {@link audio.NERtcCreateAudioEffectOption}。
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @note - You can call this method after joining a room.
     * - You can call the method multiple times. You can play multiple audio effect files simultaneously by passing in different effect_ids and options. Various audio effects are mixed. To gain optimal user experience, we recommend you play no more than three audio effect files at the same time.
     * @endif
     * @if Chinese
     * 播放指定音效文件。
     * 该方法播放指定的本地或在线音效文件。
     * - 成功调用该方法后，如果播放结束，本地会触发 onAudioEffectFinished 回调。
     * - 支持的音效文件类型包括 MP3、M4A、AAC、3GP、WMA 和 WAV 格式，支持本地 SD 卡中的文件和在线 URL。
     * @note
     * - 请在加入房间后调用该方法。
     * - 您可以多次调用该方法，通过传入不同的音效文件的 effect_id 和 option ，同时播放多个音效文件，实现音效叠加。为获得最佳用户体验，建议同时播放的音效文件不超过 3 个。
     * @param effectId 指定音效的 ID。每个音效均应有唯一的 ID。
     * @param option   音效相关参数，包括混音任务类型、混音文件路径等。详细信息请参考 {@link audio.NERtcCreateAudioEffectOption}。                
     *                 @note 若通过本接口成功播放音效文件后，反复停止或重新播放该 effectId 对应的音效文件，仅首次播放时设置的 option 有效，后续的 option 设置无效。
     * @return 
     * - 0: 方法调用成功；
     * - 其他: 方法调用失败。
     * @endif
     */
    public abstract int playEffect(int effectId, NERtcCreateAudioEffectOption option);

    /**
     * @if English
     * Stops playing all audio effects.
     * <br>You can call the method after joining a room.
     * @param effectId indicates the ID of the specified audio effect. Each audio effect has a unique ID.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
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
    public abstract int stopEffect(int effectId);

    /**
     * @if English
     * Stops playing all audio effects.
     * <br>You can call the method after joining a room.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 停止播放所有音效文件。
     * @note 请在加入房间后调用该方法。
     * @return
     * - 0: 方法调用成功；
     * - 其他: 方法调用失败。
     * @endif
     */
    public abstract int stopAllEffects();

    /**
     * @if English
     * Pauses a specified audio effect.
     * <br>You can call the method after joining a room.
     * @param effectId indicates the ID of the specified audio effect. Each audio effect has a unique ID.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 暂停播放指定音效文件。
     * <br>请在加入房间后调用该方法。
     * @param effectId 指定音效的 ID。每个音效均有唯一的 ID。
     * @return {@code 0} 方法调用成功，其他失败。
     * @endif
     */
    public abstract int pauseEffect(int effectId);

    /**
     * @if English
     * Resumes playing a specified audio effect.
     * <br>You can call the method after joining a room.
     * @param effectId indicates the ID of the specified audio effect. Each audio effect has a unique ID.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 恢复播放指定音效文件。
     * <br>请在加入房间后调用该方法。
     * @param effectId 指定音效的 ID。每个音效均有唯一的 ID。
     * @return {@code 0} 方法调用成功，其他失败。
     * @endif
     */
    public abstract int resumeEffect(int effectId);

    /**
     * @if English
     * Pauses all audio effects.
     * <br>You can call the method after joining a room.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 暂停播放所有音效文件。
     * <br>请在加入房间后调用该方法。
     * @return {@code 0} 方法调用成功，其他失败。
     * @endif
     */
    public abstract int pauseAllEffects();

    /**
     * @if English
     * Resumes playing all audio effects.
     * <br>You can call the method after joining a room.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 恢复播放所有音效文件。
     * <br>请在加入房间后调用该方法。
     * @return {@code 0} 方法调用成功，其他失败。
     * @endif
     */
    public abstract int resumeAllEffects();

    /**
     * @if English
     * Sets the volume of an audio effect file that is sent.
     * <br>You can call the method after joining a room.
     * @param effectId indicates the ID of the specified audio effect. Each audio effect has a unique ID.
     * @param volume   indicates the audio effect volume for publishing. Valid value: 0 to 200. The default value of 100 indicates the original volume.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 设置音效文件发送音量。
     * <br>请在加入房间后调用该方法。
     * @param effectId 指定音效的 ID。每个音效均有唯一的 ID。
     * @param volume   音效发送音量。范围为0~200，默认为100，表示原始音量。
     * @return {@code 0} 方法调用成功，其他失败。
     * @endif
     */
    public abstract int setEffectSendVolume(int effectId, int volume);

    /**
     * @if English
     * Gets the volume of a specified audio effect file that is sent.
     * <br>You can call the method after joining a room.
     * @param effectId indicates the ID of the specified audio effect. Each audio effect has a unique ID.
     * @return The audio effect volume for publishing.
     * @endif
     * @if Chinese
     * 获取指定音效文件发送音量。
     * <br>请在加入房间后调用该方法。
     * @param effectId 指定音效的 ID。每个音效均有唯一的 ID。
     * @return 音效发送音量。
     * @endif
     */
    public abstract int getEffectSendVolume(int effectId);

    /**
     * @if English
     * Sets the playback volume of an audio effect file.
     * <br>You can call the method after joining a room.
     * @param effectId indicates the ID of the specified audio effect. Each audio effect has a unique ID.
     * @param volume   indicates the audio effect volume for publishing. Valid values: 0 to 200. The default value is 100.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 设置音效文件播放音量。
     * <br>请在加入房间后调用该方法。
     * @param effectId 指定音效的 ID。每个音效均有唯一的 ID。
     * @param volume   音效播放音量。范围为 0~200，默认为 100。
     * @return {@code 0} 方法调用成功，其他失败。
     * @endif
     */
    public abstract int setEffectPlaybackVolume(int effectId, int volume);

    /**
     * @if English
     * Gets the volume of the audio effects.
     * <br>You can call the method after joining a room.
     * @param effectId indicates the ID of the specified audio effect. Each audio effect has a unique ID.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 获取音效文件播放音量。
     * <br>请在加入房间后调用该方法。
     * @param effectId 指定音效的 ID。每个音效均有唯一的 ID。
     * @return {@code 0} 方法调用成功，其他失败。
     * @endif
     */
    public abstract int getEffectPlaybackVolume(int effectId);


    //************* In-ears ****************//

    /**
     * @if English
     * Enables or disables in-ear monitoring
     * @param enabled specifies whether to enable in-ear monitoring.
     *                - true: enables the in-ear monitoring feature
     *                - false: By default, in-ear monitoring is disabled.
     * @param volume  specifies the volume for in-ear monitoring. Valid values: 0 to 100. The default value is 100.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @note - You must call the method in a room.
     * - After in-ear monitoring is enabled, you must wear a headset or earpieces to use the in-ear monitoring feature. We recommend you call onAudioDeviceChanged to monitor the changes of audio devices. Only when the device changes to headset, you can enable in-ear monitoring.
     * - In V4.0.0 release, the volume parameter in enableEarback is invalid. You can call setEarbackVolume to set the volume for in-ear monitoring.
     * @endif
     * @if Chinese
     * 开启或关闭耳返功能。
     * @note
     * - 请在房间内调用该方法。
     * - 开启耳返功能后，必须连接上耳机或耳麦，才能正常使用耳返功能。建议通过 onAudioDeviceChanged 监听播放设备的变化，当监听到播放设备切换为耳机时才开启耳返。
     * - 在V4.0.0 版本中，enableEarback 的 volume 参数无效，请使用 setEarbackVolume 接口设置耳返音量。
     * @param enabled 是否开启耳返功能。
     *                  - true: 开启耳返功能。
     *                  - false: （默认）关闭耳返功能。
     * @param volume  设置耳返音量，可设置为 0~100，默认为 100。
     * @return {@code 0} 方法调用成功，其他失败。
     * @endif
     */
    public abstract int enableEarback(boolean enabled, int volume);

    /**
     * @if English
     * Sets the volume for in-ear monitoring.
     * @param volume specifies the volume for in-ear monitoring. Valid values: 0 to 100. The default value is 100.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 设置耳返音量。
     * @param volume 设置耳返音量，可设置为 0~100，默认为 100。
     * @return {@code 0} 方法调用成功，其他失败。
     * @endif
     */
    public abstract int setEarbackVolume(int volume);

    //************* 音频设备管理 ****************//

    /**
     * @if English
     * Mutes or unmutes the audio playback device.
     * @param enable specifies whether to mute the audio playback device.
     *               - true: mute
     *               - false: unmute
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 设置是否静音音频播放设备。
     * @param enable 是否静音音频播放设备。
     *                  - true：静音
     *                  - false：不静音。
     * @return {@code 0} 方法调用成功，其他失败。
     * @endif
     */
    public abstract int setPlayoutDeviceMute(boolean enable);

    /**
     * @if English
     * Check whether to mute the audio playback device.
     * @return true: mute. - false: unmute.
     * @endif
     * @if Chinese
     * 查看当前音频播放设备是否静音。
     * @return true：静音；- false：未静音。
     * @endif
     */
    public abstract boolean isPlayoutDeviceMute();

    /**
     * @if English
     * Mutes or unmutes the audio capture device.
     * @param enable specifies whether to mute the audio capture device.
     *               - true: mute.
     *               - false: unmute
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 设置是否静音音频采集设备。
     * @param enable 是否静音音频采集设备。
     *                  - true：静音。
     *                  - false：不静音。
     * @return {@code 0} 方法调用成功，其他失败。
     * @endif
     */
    public abstract int setRecordDeviceMute(boolean enable);

    /**
     * @if English
     * Check whether the audio device is muted.
     * @return true: mute. false: unmute.
     * @endif
     * @if Chinese
     * 查看当前音频采集设备是否静音。
     * @return true：静音；false：未静音。
     * @endif
     */
    public abstract boolean isRecordDeviceMute();


    /**
     * @if English
     * Publish the SDK information.
     * <br>You can call the method after joining a channel. The data that is published contains the log file and the audio dump file.
     * @endif
     * @if Chinese
     * 上传 SDK 信息。
     * <br>只能在加入房间后调用。上传的信息包括 log 和 Audio dump 等文件。
     * @endif
     */
    public abstract void uploadSdkInfo();

    /**
     * @if English
     * Adds a push task in a room.
     * <br>After the method is successfully called, the current user can receive a notification about the status of the live stream.
     * @param taskInfo            specifies the information about the push task. For more information, see {@link live.NERtcLiveStreamTaskInfo}.
     * @param addLiveTaskCallback indicates the task callback. The callback is triggered after the method is called. For more information, see {@link live.AddLiveTaskCallback}.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @note - The method is applicable to only live streaming.
     * - You can call the method in a room. The method is valid in calls.
     * - Only one address for the relayed stream is added in each call. You need to call the method multiple times if you want to push many streams. An RTC room with the same channelid can create three different push tasks.
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
     * @return {@code 0} 方法调用成功，其他失败。
     * @endif
     */
    public abstract int addLiveStreamTask(NERtcLiveStreamTaskInfo taskInfo, AddLiveTaskCallback addLiveTaskCallback);


    /**
     * @if English
     * Updates a push task.
     * @param taskInfo               specifies the information about the push task. For more information, see {@link live.NERtcLiveStreamTaskInfo}。
     * @param updateLiveTaskCallback indicates the task callback. The callback is triggered after the method is called. For more information, see {@link live.UpdateLiveTaskCallback}。
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @note 
     * - The method is applicable to only live streaming.
     * - You can call the method in a room. The method is valid in calls.
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
     * @param taskId                 specified the ID of a push task.
     * @param deleteLiveTaskCallback indicates the task callback. The callback is triggered after the method is called. For more information, see {@link live.DeleteLiveTaskCallback}。
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @note - The method is applicable to only live streaming.
     * - You can call the method in a room. The method is valid in calls.
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
     * Sets a room scene.
     * <br>You can set a call or live streaming scene. Different QoS policies are applied in different scenes.
     * @param channelProfile sets the room scene. For more information, see {@link NERtcConstants.RTCChannelProfile}。
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @note You must call the method before you join a room. If you have joined a room, you cannot set the room scene.
     * @endif
     * @if Chinese
     * 设置房间场景。
     * <br>房间场景可设置为通话（默认）或直播场景。针对不同场景采取的优化策略不同，如通话场景侧重语音流畅度，直播场景侧重视频清晰度。
     * @note 该方法必须在调用 joinChannel 前使用，进入房间后无法再设置房间场景。
     * @param channelProfile 设置房间场景。详细信息请参考 {@link NERtcConstants.RTCChannelProfile}。
     * @return {@code 0} 方法调用成功，其他调用失败。
     * @endif
     */
    public abstract int setChannelProfile(int channelProfile);

    /**
     * @if English
     * Enables or disables dual streams.
     * <br>The method sets single-stream mode or dual-stream mode. If the dual-stream mode is enabled, the receiver can choose to receive the high-quality stream or low-quality stream video. The high-quality stream has a high resolution and high bitrate. The low-quality stream has a low resolution and low bitrate.
     * @param enable specifies whether to enable dual-stream mode.
     *               - true: enables the dual-stream mode. This is the default value.
     *               - false: disables the dual-stream mode.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @note - The method applies to only camera data. Video streams from custom input and screen sharing are not affected.
     * - You can call this method before or after joining a room.
     * @endif
     * @if Chinese
     * 设置是否开启视频大小流模式。
     * <br>该方法设置单流或者双流模式。发送端开启双流模式后，接收端可以选择接收大流还是小流。其中，大流指高分辨率、高码率的视频流，小流指低分辨率、低码率的视频流。
     * @note
     * - 该方法只对摄像头数据生效，自定义输入、屏幕共享等视频流无效。
     * - 该方法在加入房间前后都能调用。
     * @param enable 指定是否开启双流模式。
     *                  - true：（默认）开启双流模式。
     *                  - false：关闭双流模式。
     * @return {@code 0} 方法调用成功，其他调用失败。
     * @endif
     */
    public abstract int enableDualStreamMode(boolean enable);

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
     * Sets the role of a user in live streaming.
     * <br>The method sets the role to host or audience. The permissions of a host and a viewer are different.
     * - A host has the permissions to open or close a camera, publish streams, call methods related to publishing streams in interactive live streaming, and is visible to the users in the room when the host joins or leaves the room.
     * - The audience has no permissions to open or close a camera, call methods related to publishing streams in interactive live streaming, and is invisible to other users in the room when the audience joins or leaves the room.
     * @param role specifies the role of a user. For more information, see {@link com.netease.lava.nertc.sdk.NERtcConstants.UserRole}.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @note 
     * - By default, a user can join a room as a host. Before a user joins a room, the user can call this method to change the client role to audience. After a user joins a room, the user can call this method to switch the client role.
     * - If the role switches to audience, the SDK automatically closes the audio and video devices.
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
     * Adds a watermark image to the local video.
     * @param type   specifies the type of video streams. You can set the type to mainstream or secondary stream. For more information, see {@link video.NERtcVideoStreamType}。
     * @param config specifies the configuration of the watermark for the canvas. You can set text watermark, image watermark, and timestamp watermark. A value of null indicates removing the watermark.
     *               <br>For more information, see {@link watermark.NERtcCanvasWatermarkConfig}.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @note
     * - The setLocalCanvasWatermarkConfigs method applies to the local video canvas and does not affect the video stream. If the canvas is removed, the watermark will be automatically deleted.
     * - Before you set a watermark, you must first set the canvas by calling related methods.
     *@see NERtcEx#setLocalVideoWatermarkConfigs(NERtcVideoStreamType, NERtcVideoWatermarkConfig)
     * @endif
     * @if Chinese
     * 添加本地视频画布水印。
     * @note
     * - setLocalCanvasWatermarkConfigs 方法作用于本地视频画布，不影响视频流。画布被移除时，水印也会自动移除。
     * - 设置水印之前，需要先通过画布相关方法设置画布。
     * @param type 视频流类型。支持设置为主流或辅流。详细信息请参考 {@link video.NERtcVideoStreamType}。
     * @param config 画布水印设置。支持设置文字水印、图片水印和时间戳水印，设置为 null 表示清除水印。
     *               <br>详细信息请参考 {@link watermark.NERtcCanvasWatermarkConfig}。
     * @see NERtcEx#setLocalVideoWatermarkConfigs(NERtcVideoStreamType, NERtcVideoWatermarkConfig)
     * @return {@code 0} 方法调用成功，其他调用失败。
     * @endif
     */
    @Deprecated
    public abstract int setLocalCanvasWatermarkConfigs(NERtcVideoStreamType type, NERtcCanvasWatermarkConfig config);

    /**
     * @if English
     * Adds a watermark to the remote video canvas.
     * @param uid    specifies the ID of a remote user.
     * @param type   specifies the type of video streams. You can set the type to mainstream or secondary stream. For more information, see {@link video.NERtcVideoStreamType}.
     * @param config specifies the configuration of the watermark for the canvas. You can set text watermark, image watermark, and timestamp watermark. A value of null indicates removing the watermark.
     *               <br>
     *               For more information, see {@link watermark.NERtcCanvasWatermarkConfig}.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @note - The setRemoteCanvasWatermarkConfigs method Adds a watermark to the remote video canvas and does not affect the video stream. If the canvas is removed, the watermark will be automatically deleted.
     * - Before you set a watermark, you must first set the canvas by calling related methods.
     * @see NERtcEx#setLocalVideoWatermarkConfigs(NERtcVideoStreamType, NERtcVideoWatermarkConfig)
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
     *@see NERtcEx#setLocalVideoWatermarkConfigs(NERtcVideoStreamType, NERtcVideoWatermarkConfig)
     * @return {@code 0} 方法调用成功，其他调用失败。
     * @endif
     */
    @Deprecated
    public abstract int setRemoteCanvasWatermarkConfigs(long uid, NERtcVideoStreamType type, NERtcCanvasWatermarkConfig config);

    /**
     * @if English
     * Takes a local video snapshot.
     * <br>The takeLocalSnapshot method takes a local video snapshot on the local mainstream or local secondary stream. The onTakeSnapshotResult callback that belongs to the NERtcTakeSnapshotCallback class returns the data of the snapshot image.
     * @param streamType specifies the video stream type of the snapshot. For more information, see {@link video.NERtcVideoStreamType}.
     * @param callback   specifies the snapshot callback. For more information, see {@link video.NERtcTakeSnapshotCallback}.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @note - Before you call the method to capture the snapshot from the primary stream, you must first call startVideoPreview or enableLocalVideo, and joinChannel.
     * - Before you call the method to capture the snapshot from the secondary stream, you must first call joinChannel and startScreenCapture.
     * - If you want to set text, timestamp, and image on a watermark, different types of watermarks may overlap. The sequence for the layers of the watermark image to override previous layers follows image, text, and timestamp.
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
     * @param uid        indicates the ID of a remote user.
     * @param streamType specifies the video stream type of the snapshot. You can set the type to mainstream or secondary stream. For more information, see {@link video.NERtcVideoStreamType}.
     * @param callback   specifies the snapshot callback. For more information, see {@link video.NERtcTakeSnapshotCallback}.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @note - Before you call takeRemoteSnapshot, you must first call onUserVideoStart and onUserSubStreamVideoStart.
     * - If you want to set text, timestamp, and image on a watermark, different types of watermarks may overlap. The sequence for the layers of the watermark image to override previous layers follows image, text, and timestamp.
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
     * Switches to a different RTC room.
     * <br>In live streaming, the audience can call this method to switch from the current room to another room.
     * <br>After you switch to another room:
     * - The local client first receives the return from {@link NERtcCallback#onLeaveChannel(int result)}. The result parameter is {@link NERtcConstants.ErrorCode#LEAVE_CHANNEL_FOR_SWITCH}. Then, the client receives the return from {@link NERtcCallback#onJoinChannel()}.
     * - Remote clients receive the return from onUserLeave and onUserJoined.
     * @param token       specifies the security signature generated in the server and used for authentication. Valid values:
     *                    - NERTC Token obtained. The token is required in safe mode. By default, the token expires after 10 minutes. You can also request the token on a regular basis from CommsEase server or a token that has a long lifetime. We recommend that you use the safe mode.
     *                    - null. You can specify null in the debugging mode. This poses a security risk. We recommend that you change to the safe mode in the CommsEase console by using the authentication mode before your product is officially launched.
     * @param channelName specifies the room name that a user wants to switch to.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * - 30109(NERtcConstants.ErrorCode.SWITCH_CHANNEL_NOT_JOINED): The user is not in the room.
     * - 403(NERtcConstants.ErrorCode.RESERVE_ERROR_NO_PERMISSION): The request is rejected, probably because the user is not an audience.
     * - 30100(NERtcConstants.ErrorCode.ENGINE_ERROR_ROOM_ALREADY_JOINED): The room name is invalid. The user already joined the room.
     * @note - By default, after a room member switches to another room, the room member subscribes to audio streams from other members of the new room. In this case, data usage is charged and billing is updated. If you want to unsubscribe to the previous audio stream, you can call the subscribeRemoteAudio method with a value of false passed in.
     * - The method applies to only live streaming. The role is the audience in the RTC room. The setChannelProfile method set the room scene to live streaming, and the setClientRole method set the role of room members to audience.
     * @endif
     * @if Chinese
     * 快速切换音视频房间。
     * 房间场景为直播场景时，房间中角色为观众的成员可以调用该方法从当前房间快速切换至另一个房间。
     * 成功调用该方切换房间后：
     * - 本端会先收到离开房间的回调 {@link NERtcCallback#onLeaveChannel(int result)}，其中 result 参数为{@link NERtcConstants.ErrorCode#LEAVE_CHANNEL_FOR_SWITCH}。再收到成功加入新房间的回调 {@link NERtcCallback#onJoinChannel()}。
     * - 远端用户会收到 onUserLeave 和 onUserJoined 的回调。
     * @note
     * - 房间成员成功切换房间后，默认订阅房间内所有其他成员的音频流，因此产生用量并影响计费。如果想取消订阅，可以通过调用相应的 subscribeRemoteAudio 方法传入 false 实现。
     * - 该方法仅适用于直播场景中，角色为观众的音视频房间成员。即已通过接口 setChannelProfile 设置房间场景为直播，通过 setClientRole 设置房间成员的角色为观众。
     * @param token       在服务器端生成的用于鉴权的安全认证签名（Token）。可设置为：
     *                      - 已获取的 NERTC Token。安全模式下必须设置为获取到的 Token。默认 token 有效期 10 min，也可以定期通过应用服务器向云信服务器申请 token 或者申请长期且可复用的 token。推荐使用安全模式。
     *                      - null。调试模式下可设置为 null。安全性不高，建议在产品正式上线前在云信控制台中将鉴权方式恢复为默认的安全模式。
     * @param channelName 期望切换到的目标房间名称。
     * @return
     * - 0(NERtcConstants.ErrorCode.OK)：方法调用成功。
     * - 30001(NERtcConstants.ErrorCode.ENGINE_ERROR_FATAL)：内部错误。
     * - 30003(NERtcConstants.ErrorCode.ENGINE_ERROR_INVALID_PARAM)：参数错误。
     * - 30109(NERtcConstants.ErrorCode.SWITCH_CHANNEL_NOT_JOINED): 切换房间时不在会议中。
     * - 403(NERtcConstants.ErrorCode.RESERVE_ERROR_NO_PERMISSION): 用户角色不是观众。
     * - 30100(NERtcConstants.ErrorCode.ENGINE_ERROR_ROOM_ALREADY_JOINED): 房间名无效，已在此房间中。
     * @endif
     */
    public abstract int switchChannel(String token, String channelName);

    /**
     * @if English
     * Starts an audio recording from a client.
     * <br>The method records the mixing audio from all room members in the room, and store the recording file locally. The onAudioRecording() callback is triggered when the recording starts or ends.
     * <br>If you specify a type of audio quality, the recording file is saved in different formats.
     * - A WAV file is large with high quality
     * - A AAC file is small with low quality.
     * @param filePath   specifies the file path where the recording file is saved. The file name and format are required. For example, sdcard/xxx/audio.aac.
     *                   - Make sure that the path is valid and has the write permission.
     *                   - Only WAV or AAC files are supported.
     * @param sampleRate specifies the audio sample rate. Valid values: 16000,32000, 44100, and 48000. The default value is 32000.
     * @param quality    specifies the audio quality. The parameter is valid only the audio file is in AAC format. For more information, see  {@link NERtcConstants.AudioRecordingQuality} 。
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @note - You can call the method after joining a room.
     * - A client can only run a recording task. If you repeatedly call the startAudioRecording method, the current recording task stops and a new recording task starts.
     * - If the current user leaves the room, the audio recording automatically stops. You can call the stopAudioRecording method to manually stop recording during calls.
     * @endif
     * @if Chinese
     * 开始客户端录音。
     * 调用该方法后，客户端会录制房间内所有用户混音后的音频流，并将其保存在本地一个录音文件中。录制开始或结束时，自动触发 onAudioRecording() 回调。
     * 指定的录音音质不同，录音文件会保存为不同格式：
     * - WAV：音质保真度高，文件大。
     * - AAC：音质保真度低，文件小。
     * @note
     * - 请在加入房间后调用此方法。
     * - 客户端只能同时运行一个录音任务，正在录音时，如果重复调用 startAudioRecording，会结束当前录制任务，并重新开始新的录音任务。
     * - 当前用户离开房间时，自动停止录音。您也可以在通话中随时调用 stopAudioRecording 手动停止录音。
     * @param filePath   录音文件在本地保存的绝对路径，需要精确到文件名及格式。例如：sdcard/xxx/audio.aac。
     *                   - 请确保指定的路径存在并且可写。
     *                   - 目前仅支持 WAV 或 AAC 文件格式。
     * @param sampleRate 录音采样率（Hz），可以设为 16000、32000（默认）、44100 或 48000。
     * @param quality    录音音质，只在 AAC 格式下有效。详细说明请参考  {@link NERtcConstants.AudioRecordingQuality} 。
     * @return {@code 0} 方法调用成功，其他调用失败。
     * @endif
     */
    public abstract int startAudioRecording(String filePath, int sampleRate, int quality);

    /**
     * @if English
     * Starts an audio recording from a client.
     * <br>The method records the mixing audio from all room members in the room, and store the recording file locally. The onAudioRecording() callback is triggered when the recording starts or ends.
     * <br>If you specify a type of audio quality, the recording file is saved in different formats.
     * - A WAV file is large in size with high quality
     * - An AAC file is small in size with low quality.
     * @note
     * - You must call the method after you call joinChannel.
     * - A client can only run a recording task. If you repeatedly call the startAudioRecordingWithConfig method, the current recording task stops and a new recording task starts.
     * - If the current user leaves the room, audio recording automatically stops. You can call the stopAudioRecording method to manually stop recording during calls.
     * @since V4.6.0
     * @param recordFilePath     The absolute file path where the recording file is stored. The file name and format are required. For example, sdcard/xxx/audio.aac.
     *                           - Make sure that the path is valid and has the write permissions.
     *                           - Only WAV or AAC files are supported.
     * @param recordSampleRate   The recording sample rate in Hz. Valid values: 16000,32000, 44100, and 48000. The default value is 32000.
     * @param recordQuality      The audio quality. The parameter is valid only if the recording file is in AAC format. For more information, see {@link NERtcConstants.AudioRecordingQuality}.
     * @param recordPosition     The recording object. 
                                 - MIXED_RECORDING_AND_PLAYBACK (default): records the audio stream mixed from all participants in the room.
                                 - RECORDING: records only the local audio stream.
                                 - PLAYBACK: records the audio stream mixed from all remote participants in the room.
     * @param recordCycleTime    The maximum number of seconds for loop caching. You can set the value to 0, 10, 60, 360, and 900. The default value is 0 indicates that the write operation runs in real time.
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
     * @param recordFilePath   录音文件在本地保存的绝对路径，需要精确到文件名及格式。例如：sdcard/xxx/audio.aac。
     *                         - 请确保指定的路径存在并且可写。
     *                         - 目前仅支持 WAV 或 AAC 文件格式。
     * @param recordSampleRate 录音采样率（Hz），可以设为 16000、32000（默认）、44100 或 48000。
     * @param recordQuality    录音音质，只在 AAC 格式下有效。详细说明请参考  {@link NERtcConstants.AudioRecordingQuality} 。
     * @param recordPosition   录音对象。
                               - MIXED_RECORDING_AND_PLAYBACK（默认）：录制房间内所有成员混流后的音频数据。
                               - RECORDING：仅录制本地用户发布的音频流。
                               - PLAYBACK：仅录制所有远端用户混流后的音频数据。
     * @param recordCycleTime  循环缓存的最大时长跨度。该参数单位为秒，可以设为 0、10、60、360、900，默认值为 0，即实时写文件。
     * @return
     * - 0：方法调用成功。
     * - 其他：方法调用失败。
     * @endif
     */
    public abstract int startAudioRecordingWithConfig(NERtcAudioRecordingConfiguration audioRecordConfig);

    /**
     * @if English
     * Stops the audio recording on the client.
     * <br>If the local client leaves the room, the audio recording automatically stops. You can call the stopAudioRecording method to manually stop recording during calls.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @note You must call this method before you call leaveChannel.
     * @endif
     * @if Chinese
     * 停止客户端录音。
     * 本端离开房间时自动停止录音，您也可以在通话中随时调用 stopAudioRecording 手动停止录音。
     * @note 该接口需要在 leaveChannel 之前调用。
     * @return {@code 0} 方法调用成功，其他调用失败。
     * @endif
     */
    public abstract int stopAudioRecording();


    /**
     * @if English
     * Sets the priority of media streams from a local user.
     * <br>If a user has a high priority, the media stream from the user also has a high priority. In unreliable network connections, the SDK guarantees the quality of the media stream from users with a high priority.
     * @param priority     specifies the priority of the local media stream. The default value is {@link NERtcConstants.MediaPriority#MEDIA_PRIORITY_NORMAL}. For more information, see {@link NERtcConstants.MediaPriority}.
     * @param isPreemptive specifies whether to enable the preempt mode.
     *                     - If the preempt mode is enabled, the local media stream preempts the high priority over other users. The priority of the media stream whose priority is taken becomes normal. After the user whose priority is taken leaves the room, other users still keep the normal priority.
     *                     - If the preempt mode is disabled, and a user in the room has a high priority, then, the high priority of the local client remains invalid and is still normal.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @note - You can call the method after joining a room. - An RTC room has only one user that has a high priority. We recommend that only one user in a room call the setLocalMediaPriority method to set the local media stream a high priority. Otherwise, you need to enable the preempt mode to ensure the high priority of the local media stream.
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
     * @if English
     * Starts to relay media streams across rooms.
     * - The method can invite co-hosts across rooms. Media streams from up to four rooms can be relayed. A room can receive multiple relayed media streams.
     * - After you call this method, the SDK triggers onMediaRelayStatesChange and onMediaRelayReceiveEvent. The return reports the status and events about the current relayed media streams across rooms.
     * @param config specifies the configuration for the media stream relay across rooms. For more information, see {@link NERtcMediaRelayParam.ChannelMediaRelayConfiguration}。
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @note - You can call this method after joining a room. Before you call the method, you must set the destination room in the config parameter in setDestChannelInfo.
     * - The method is applicable only to the host in live streaming.
     * - If you want to call the method again, you must first call the stopChannelMediaRelay method to exit the current relaying status.
     * - If you succeed in relaying the media stream across rooms, and want to change the destination room, for example, add or remove the destination room, you can call updateChannelMediaRelay to update the information about the destination room.
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
     * @if English
     * Updates the information of the destination room for the media stream relay.
     * <br>You can call this method to relay the media stream to multiple rooms or exit the current room.
     * - You can call this method to change the destination room, for example, add or remove the destination room.
     * - After you call this method, the SDK triggers onMediaRelayStatesChange and onMediaRelayReceiveEvent. The return reports the status and events about the current relayed media streams across rooms.
     * @param config specifies the configuration for media stream relay across rooms. For more information, see {@link NERtcMediaRelayParam.ChannelMediaRelayConfiguration}。
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @note - Before you call the method, you must join the room and call startChannelMediaRelay to relay the media stream across rooms. Before you call the method, you must set the destination room in the config parameter in setDestChannelInfo.
     * - You can relay the media stream up to four destination rooms. You can first call removeDestChannelInfo that belongs to the ChannelMediaRelayConfiguration class to remove the rooms that you have no interest in and add new destination rooms.
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
     * @if English
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
     * <br>After you join the room, you can call the method to adjust the volume of local audio playback from different remote users or repeatedly adjust the volume of audio playback from a specified remote user.
     * @param uid    indicates the ID of a remote user.
     * @param volume specifies the playback volume. Valid values: 0 to 100.
     *               - 0: mute
     *               - 100: the original volume
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @note - You can call this method after joining a room.
     * - The method is valid in the current call. If a remote user exits the room and rejoins the room again, the setting is still valid until the call ends.
     * - The method adjusts the volume of the mixing audio published by a specified remote user. The volume of one remote user can be adjusted. If you want to adjust multiple remote users, you need to call the method for the required times.
     * @endif
     * @if Chinese
     * 调节本地播放的指定远端用户的信号音量。
     * 加入房间后，您可以多次调用该方法设置本地播放的不同远端用户的音量；也可以反复调节本地播放的某个远端用户的音量。
     * @note
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
     * @param option specifies the fallback option for the locally published stream. By default, the fallback option is disabled. {@link NERtcConstants.StreamFallbackOption#DISABLED}。
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @note You must call the method before you call joinChannel.
     * @since version 4.3.0
     * @endif
     * @if Chinese
     * 设置弱网条件下发布的音视频流回退选项。
     * 在网络不理想的环境下，发布的音视频质量都会下降。使用该接口并将 option 设置为 {@link NERtcConstants.StreamFallbackOption#AUDIO_ONLY} 后：
     * - SDK 会在上行弱网且音视频质量严重受影响时，自动关断视频流，尽量保证音频质量。
     * - 同时 SDK 会持续监控网络质量，并在网络质量改善时恢复音视频流。
     * - 当本地发布的音视频流回退为音频流时，或由音频流恢复为音视频流时，SDK 会触发本地发布的媒体流已回退为音频流 onLocalPublishFallbackToAudioOnly 回调。
     * @note 请在加入房间（joinChannel）前调用此方法。
     * @since V4.3.0
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
     * @param option is the fallback option for the subscribed remote audio and video stream. The default setting is {@link NERtcConstants.StreamFallbackOption#VIDEO_STREAM_LOW}. With unreliable network connections, the stream falls back to a low-quality video stream.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @note Make sure that you call this method before you call joinChannel.
     * @since version 4.3.0
     * @endif
     * @if Chinese
     * 设置弱网条件下订阅的音视频流回退选项。
     * 弱网环境下，订阅的音视频质量会下降。调用该接口并将 option 设置为 {@link NERtcConstants.StreamFallbackOption#VIDEO_STREAM_LOW} 或者 {@link NERtcConstants.StreamFallbackOption#AUDIO_ONLY} 后：
     *  - SDK 会在下行弱网且音视频质量严重受影响时，将视频流切换为小流，或关断视频流，从而保证或提高通信质量。
     *  - SDK 会持续监控网络质量，并在网络质量改善时自动恢复音视频流。
     *  - 当远端订阅流回退为音频流时，或由音频流恢复为音视频流时，SDK 会触发远端订阅流已回退为音频流 onRemoteSubscribeFallbackToAudioOnly 回调。
     * @note 请在加入房间（joinChannel）前调用此方法。
     * @since V4.3.0
     * @param option 订阅音视频流的回退选项，默认为 {@link NERtcConstants.StreamFallbackOption#VIDEO_STREAM_LOW} 弱网时回退到视频小流。
     * @return {@code 0} 方法调用成功，其他调用失败。
     * @endif
     */
    public abstract int setRemoteSubscribeFallbackOption(int option);

    /**
     * @if English
     * Gets the duration of the sound effect file.
     * @param effectId The ID of the sound effect file.
     * @return The duration of the sound effect file. Unit: milliseconds.
     * @note You must join the room before you call this method.
     * @since V4.4.0
     * <br>The methods gets the duration of the sound effect file. Unit: milliseconds.
     * @endif
     * @if Chinese
     * 获取音效文件时长。
     * @since V4.4.0
     * 该方法获取音效文件时长，单位为毫秒。
     * @note 请在房间内调用该方法。
     * @param effectId 音效 ID。
     * @return 音效文件时长，单位为毫秒。
     * @endif
     */
    public abstract long getEffectDuration(int effectId);


    /**
     * @if English
     * Gets the position of the sound effect playback.
     * @param effectId The ID of the sound effect file.
     * @return The position of the sound effect playback. Unit: milliseconds.
     * @note You must join the room when you call this method.
     * @since V4.4.0
     * <br>The method gets the position of the sound effect playback. Unit: milliseconds.
     * @endif
     * @if Chinese
     * 获取音效的播放进度。
     * @since V4.4.0 
     * 该方法获取当前音效播放进度，单位为毫秒。
     * @note 请在房间中调用该方法。
     * @param  effectId 音效 ID。
     * @return 音效的播放进度，单位为毫秒。
     * @endif
     */
    public abstract long getEffectCurrentPosition(int effectId);


    /**
     * @if English
     * Enables or disables AI-enabled super resolution.
     * @param enable Specifies whether to enable the AI-enabled super resolution. The default value is disabled.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @note - Before you use the AI-enabled super resolution, you can contact the technical support to activate the AI-enabled super resolution feature.
     * - The AI-enabled super resolution applies only to the following video streams.
     * - The first 360p video stream received by the local client.
     * - The high-resolution mainstream captured by the camera. The AI-enabled super resolution does not apply to restore the low-resolution stream or screen sharing substream.
     * @since V4.4.0
     * @endif
     * @if Chinese
     * 启用或停止 AI 超分。
     * @since V4.4.0
     * @note 
     * - 使用 AI 超分功能之前，请联系技术支持开通 AI 超分功能。
     * - AI 超分仅对以下类型的视频流有效：
     *   - 必须为本端接收到第一路 360P 的视频流。
     *   - 必须为摄像头采集到的主流大流视频。AI 超分功能暂不支持复原重建小流和屏幕共享辅流。
     * @param enable 是否启用 AI 超分。默认为关闭状态。
     * @return {@code 0} 方法调用成功，其他调用失败。
     * @endif
     */
    public abstract int enableSuperResolution(boolean enable);


    /**
     * @if English
     * Enables or disables the media stream encryption.
     * @param enable Specifies whether to enable the media steam encryption.
     *               - true: enables the media steam encryption.
     *               - false: disables the media steam encryption. This is the default value.
     * @param config The media stream encryption scheme. For more information, see NERtcEncryptionConfig.
     * @return {@code 0} A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @note - You must call the method before you join the room. You cannot change the encryption scheme and key after you join the room. If all participants leave the room, the SDK automatically disables the encryption scheme. To enable encryption again, you must call the method before participants join the room.
     * - All participants who have enabled the media stream encryption in the same room must use the same encryption scheme and key. Otherwise, an error occurs. ENGINE_ERROR_ENCRYPT_NOT_SUITABLE (30113).
     * - For security, we recommend that you use a new key when you enable the media stream encryption.
     * @since V4.4.0
     * <br>For high security requirements in the finance sector, you can specify an encryption mode for the media stream before you join the room.
     * @endif
     * @if Chinese
     * 开启或关闭媒体流加密。
     * @since V4.4.0
     * 在金融行业等安全性要求较高的场景下，您可以在加入房间前通过此方法设置媒体流加密模式。
     * @note 
     * - 请在加入房间前调用该方法，加入房间后无法修改加密模式与密钥。用户离开房间后，SDK 会自动关闭加密。如需重新开启加密，需要在用户再次加入房间前调用此方法。
     * - 同一房间内，所有开启媒体流加密的用户必须使用相同的加密模式和密钥，否则使用不同密钥的成员加入房间时会报错 ENGINE_ERROR_ENCRYPT_NOT_SUITABLE（30113）。
     * - 安全起见，建议每次启用媒体流加密时都更换新的密钥。
     * @param enable 是否开启媒体流加密。
     *                  - true: 开启
     *                  - false:（默认）关闭
     * @param config 媒体流加密方案。详细信息请参考 NERtcEncryptionConfig。
     * @return {@code 0} 方法调用成功，其他调用失败
     * @endif
     */
    public abstract int enableEncryption(boolean enable, NERtcEncryptionConfig config);

    /**
     * @if English 
     * Creates and gets an NERtcChannel instance.
     * - You can call this method multiple times to create multiple NERtcChannel objects, and then call the joinChannel method in each NERtcChannel object to join multiple channels.
     * - After joining multiple channels, you can subscribe to the audio and video streams of each channel. However, you can only send audio and video streams at to one channel.  
     * @param channelName The name of the channel. Users that use the same name can join the same channel.
     *                    - The name is in STRING format and must be 1 to 64 characters in length.
     *                    - The following 89 characters are supported: a-z, A-Z, 0-9, space, !#$%&()+-:;≤.,>?@[]^_{|}~”
     * @return 
     * - The {@link channel.NERtcChannel} instance, if the method call succeeds.
     * - Null, if the method call fails.
     * @endif
     * @if Chinese
     * 创建并获取一个 NERtcChannel 对象。
     * - 你可以多次调用该方法，创建多个 NERtcChannel 对象，再调用各 NERtcChannel 对象中的 joinChannel 方法，实现同时加入多房间。
     * - 加入多个房间后，你可以同时订阅各个房间的音、视频流；但是同一时间只能在一个房间发布一路音、视频流。
     * @param channelName 房间名称，设置相同房间名称的用户会进入同一个通话房间。
     * - 字符串格式，长度为 1~64 字节。
     * - 支持以下89个字符：a-z, A-Z, 0-9, space, !#$%&()+-:;≤.,>?@[]^_{|}~”
     * @return 方法调用成功，返回 {@link channel.NERtcChannel} 对象。方法调用失败，返回null。
     * @endif
     */
    public abstract NERtcChannel createChannel(String channelName);

    /** 
     * @if English 
     * Starts the last-mile network probe test.
     * <br>This method starts the last-mile network probe test before joining a channel to get the uplink and downlink last mile network statistics, including the bandwidth, packet loss, jitter, and round-trip time (RTT).This method is used to detect network quality before a call. Before a user joins a room, you can use this method to estimate the subjective experience and objective network status of a local user during an audio and video call. 
     * Once this method is enabled, the SDK returns the following callbacks:
     * - `onLastmileQuality`: the SDK triggers this callback within five seconds depending on the network conditions. This callback rates the network conditions with a score and is more closely linked to the user experience.
     * - `onLastmileProbeResult`: the SDK triggers this callback within 30 seconds depending on the network conditions. This callback returns the real-time statistics of the network conditions and is more objective.
     * @note 
     * - You can call this method before joining a channel(joinChannel).
     * - Do not call other methods before receiving the onLastmileQuality and onLastmileProbeResult callbacks. Otherwise, the callbacks may be interrupted.
     * @since V4.5.0
     * @param config    Sets the configurations of the last-mile network probe test. For more information, see {@link LastmileProbeConfig}.
     * @endif
     * @if Chinese
     * 开始通话前网络质量探测。
     * <br>启用该方法后，SDK 会通过回调方式反馈上下行网络的质量状态与质量探测报告，包括带宽、丢包率、网络抖动和往返时延等数据。一般用于通话前的网络质量探测场景，用户加入房间之前可以通过该方法预估音视频通话中本地用户的主观体验和客观网络状态。
     * <br>相关回调如下：
     * - `onLastmileQuality`：网络质量状态回调，以打分形式描述上下行网络质量的主观体验。该回调视网络情况在约 5 秒内返回。
     * - `onLastmileProbeResult`：网络质量探测报告回调，报告中通过客观数据反馈上下行网络质量。该回调视网络情况在约 30 秒内返回。
     * @note 
     * - 请在加入房间（joinChannel）前调用此方法。
     * - 调用该方法后，在收到 `onLastmileQuality` 和 `onLastmileProbeResult` 回调之前请不要调用其他方法，否则可能会由于 API 操作过于频繁导致此方法无法执行。
     * @since V4.5.0
     * @param config    Last mile 网络探测配置。详细说明请参考 {@link LastmileProbeConfig}。
     * @return
     * - 0: 方法调用成功
     * - 其他: 调用失败
     * @endif
     */
    public abstract int startLastmileProbeTest(LastmileProbeConfig config);

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
    public abstract int stopLastmileProbeTest();

    /**
     * @if English
     * Check if video correction is enabled
     * @note
     * - If you use a camera to shoot an object, a mapping process from 3D to 2D images is implemented. the image of an object is deformed when the position of the camera changes.
     * - If you enable video correction with appropriate parameters, video images can be restored by algorithms.
     * - To use video correction, the rendering mode of the local canvas must be set to fit. Video frames keeps aspect ratio unchanged and are all displayed in the current view. Otherwise, video correction may not take effect.
     * - If the parameters of video correction are applied, the local and remote video frames are all corrected
     * @since V4.6.0
     * @param enable Enables or disables local video correction.
     * - true：enabled
     * - false (default): disabled
     * @return 
     * @endif
     * @if Chinese
     * 是否启用视频图像畸变矫正。
     * @note
     * - 当使用相机去拍摄物体时，存在着一个从三维世界到二维图像的映射过程，这个过程中由于相机位置的变化和移动，会对拍摄物体的成像产生一定的形变影响。
     * - 开启该功能时，根据合适的参数，可以通过算法把这个形变进行复原。
     * - 使用该功能时，本地画布的渲染模式需要为 fit（即视频帧保持自身比例不变全部显示在当前视图中），否则矫正功能可能不会正常生效。
     * - 矫正参数生效后，本地画面和对端看到的画面，均会是矫正以后的画面。
     * @since V4.6.0
     * @param enable 是否开启视频图像矫正。
     * - true：开启视频图像矫正。
     * - false（默认）：关闭视频图像矫正。
     * @return
     * - 0：方法调用成功。
     * - 其他：方法调用失败。
     * @endif
     */
    public abstract int enableVideoCorrection(boolean enable);

    /**
     * @if English
     * Sets the parameters for video correction.
     * @note
     * - The first 4 parameters of the config schema represent coordinates of areas to be corrected on the screen. The x and y coordinates of each point ranges from 0 to 1.
     * - The last 3 parameters are required only if external video rendering is used.
     * - You can pass nill in config. If config is set to nil, the SDK clears previous configurations for video correction. The video graph will be restored to the state without correction.
     * @since V4.6.0
     * @param config Correction parameters. For more information, see {@link NERtcVideoCorrectionConfiguration}.
     * @return 
     * - 0: success.
     * - Others: failure
     * @endif
     * @if Chinese
     * 设置视频图像矫正参数。
     * @note
     * - 矫正参数结构体的前 4 个参数，代表了待矫正区域相对于屏幕上视图的坐标，每个坐标点的 x 和 y 的取值范围均为 0 ~ 1 的浮点数。
     * - 矫正参数结构体的后 3 个参数只有在使用了外部视频渲染功能时才需要传入。
     * - config 可以传入 nil，清空之前设置过的矫正参数，将画面恢复至矫正之前的效果。
     * @since V4.6.0
     * @param config 视频图像矫正相关参数。详细说明请参考 {@link NERtcVideoCorrectionConfiguration}。
     * @return
     * - 0：方法调用成功。
     * - 其他：方法调用失败。
     * @endif
     */
    public abstract int setVideoCorrectionConfig(NERtcVideoCorrectionConfiguration config);

    /**
     * @if English
     * Enables/Disables the virtual background.
     * @param enabled Sets whether to enable the virtual background:
     * - true：Enable.
     * - false: Disable.
     * @param backgroundSource
     * @endif
     * @if Chinese
     * 开启/关闭虚拟背景。
     * <br>启用虚拟背景功能后，您可以使用自定义背景图片替换本地用户的原始背景图片。
     * <br>替换后，频道内所有用户都可以看到自定义背景图片。
     * @note 
     * - 您可以通过 {@link NERtcCallbackEx#onVirtualBackgroundSourceEnabled()} 回调查看虚拟背景是否开启成功或出错原因。
     * - 建议您在满足以下条件的场景中使用该功能：
     *    - 采用高清摄像设备，环境光线均匀。
     *    - 捕获的视频图像整洁，用户肖像半长且基本无遮挡，并且背景是与用户衣服颜色不同的单一颜色。
     * - 虚拟背景功能不支持在 Texture 格式的视频或通过 Push 方法从自定义视频源获取的视频中设置虚拟背景。
     * - 若您设置背景图片为自定义本地图片，SDK 会在保证背景图片内容不变形的前提下，对图片进行一定程度上的缩放和裁剪，以适配视频采集分辨率。
     * @since V4.6.10
     * @param enabled 设置是否开启虚拟背景。
     * - true：开启。
     * - false: 关闭。
     * @param backgroundSource 自定义背景图片。详细信息请参考 {@link video.NERtcVirtualBackgroundSource}。
     * @return
     * - 0：方法调用成功。
     * - 其他：方法调用失败。
     * @endif
     */
    public abstract int enableVirtualBackground(boolean enabled, NERtcVirtualBackgroundSource backgroundSource);


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
     * - 0: success
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
     * Sets the Agora cloud proxy service.
     * <br>When the user's firewall restricts the IP address and port, refer to Use Cloud Proxy to add the specific IP addresses and ports to the firewall whitelist; then, call this method to enable the cloud proxy and set the proxyType parameter as TRANSPORT_TYPE_UDP_PROXY(1), which is the cloud proxy for the UDP protocol.
     * - After a successfully cloud proxy connection, the SDK triggers the `onConnectionStateChanged(CONNECTION_STATE_CONNECTING, CONNECTION_CHANGED_SETTING_PROXY_SERVER)` callback.
     * - To disable the cloud proxy that has been set, call setCloudProxy(NONE_PROXY).
     * @note We recommend that you call this method before joining the channel or after leaving the channel.
     * @param proxyType The cloud proxy type. For more information, see {@link NERtcConstants.TransportType}. This parameter is required, and the SDK reports an error if you do not pass in a value.
     * @return A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 开启并设置云代理服务。
     * <br>在内网环境下，如果用户防火墙开启了网络限制，请参考《使用云代理》将指定 IP 地址和端口号加入防火墙白名单，然后调用此方法开启云代理，并将 proxyType 参数设置为 TRANSPORT_TYPE_UDP_PROXY(1)，即指定使用 UDP 协议的云代理。
     * - 成功连接云代理后，SDK 会触发 `onConnectionStateChanged(CONNECTION_STATE_CONNECTING, CONNECTION_CHANGED_SETTING_PROXY_SERVER)` 回调。
     * - 如果需要关闭已设置的云代理，请调用 `setCloudProxy(NONE_PROXY)`。
     * @note 请在加入房间前调用此方法。
     * @param proxyType 云代理类型。详细信息请参考 {@link NERtcConstants.TransportType}。该参数为必填参数，若未赋值，SDK 会报错。
     * @return {@code 0} 方法调用成功，其他失败。
     * @endif
     */
    public abstract int setCloudProxy(int proxyType);

    /**
     * @if English
     * Enables the beauty module.
     * - The API starts the beauty engine. If beauty is not needed, you can call {@link NERtcEx#stopBeauty()} to end the beauty module, destroy the beauty engine and release resources.
     * - When the beauty module is enabled, no beauty effect is applied by default. You must set beauty effects or filters by calling {@link NERtcEx#setBeautyEffect()} or other filters and stickers methods.
     * @note The method must be called before {@link NERtc#enableLocalVideo()}.
     * @since V4.2.202
     * @return
     * - 0: success.
     * - 30001 (kNERtcErrFatal): failure.
     * - 30004 (kNERtcErrNotSupported): beauty is not supported.
     * @endif
     * @if Chinese
     * 开启美颜功能模块。
     * - 调用此接口后，开启美颜引擎。如果后续不再需要使用美颜功能，可以调用 {@link NERtcEx#stopBeauty()} 结束美颜功能模块，销毁美颜引擎并释放资源。
     * - 开启美颜功能模块后，默认无美颜效果，您需要通过 {@link NERtcEx#setBeautyEffect()} 或其他滤镜、贴纸相关接口设置美颜或滤镜效果。
     * @note 该方法需要在 {@link NERtc#enableLocalVideo()} 之前设置。
     * @since V4.2.202
     * @return
     * - 0：方法调用成功；
     * - 30001（kNERtcErrFatal）：方法调用失败；
     * - 30004（kNERtcErrNotSupported）：不支持美颜功能。
     * @endif
     */
    public abstract int startBeauty();

    /**
     * @if English
     * Stops the beauty module.
     * <br>If the beauty module is not needed, you can call {@link NERtcEx#stopBeauty()} to stop the module. The SDK will automatically destroy the beauty engine and release the resources.
     * @since V4.2.202
     * @return
     * - 0: success
     * - Others: failure
     * @endif
     * @if Chinese
     * 结束美颜功能模块。
     * <br>如果后续不再需要使用美颜功能，可以调用 {@link NERtcEx#stopBeauty()} 结束美颜功能模块，SDK 会自动销毁美颜引擎并释放资源。
     * @since V4.2.202
     * @return
     * - 0：方法调用成功。
     * - 其他：方法调用失败。
     * @endif
     */
    public abstract void stopBeauty();

    /**
     * @if English
     * Pauses or resumes the beauty effect
     * <br> The beauty effect is paused, including the global beauty effect, filters, stickers, and makeups, until the effect is resumed.
     * @note Beauty effect is enabled by default. If you want to temporarily disable the beauty effect, call this method after invoking {@link NERtcEx#startBeauty()}.
     * @since V4.2.202
     * @param enable specifies whether to resume the beauty effect.
     * - YES (default): resumes the beauty effect.
     * - NO: pauses the beauty effect.
     * @return
     * - 0: success
     * - Others: failure
     * @endif
     * @if Chinese
     * 暂停或恢复美颜效果。
     * <br> 暂停美颜效果后，包括全局美颜、滤镜、贴纸和美妆在内的所有美颜效果都会暂时关闭，直至重新恢复美颜效果。
     * @note 美颜效果默认开启。若您需要临时关闭美颜功能，需要在 {@link NERtcEx#startBeauty()} 之后调用该方法。
     * @since V4.2.202
     * @param enable 是否恢复美颜效果。
     * - true：恢复美颜效果。
     * - false：暂停美颜效果。
     * @return
     * - 0：方法调用成功。
     * - 其他：方法调用失败。
     * @endif
     */
    public abstract int enableBeauty(boolean enable);

    /**
     * @if English
     * Sets the beauty type and intensity.
     * - The method can set various types of beauty effects, such as smoothing, whitening, and big eyes.
     * - Multiple method calls can apply multiple global effects. Filters, stickers, and makeups can be added in the same way.
     * @since V4.2.202
     * @param beautyType Beauty type. For more information, see {@link video.NERtcBeautyEffectType}.
     * @param level      Beauty intensity. Value range: [0, 1]. The default values of effects are different.
     * @return
     * - 0: success
     * - Others: failure
     * @endif
     * @if Chinese
     * 设置美颜类型和强度。
     * - 此方法可用于设置磨皮、美白、大眼等多种全局美颜类型。
     * - 多次调用此接口可以叠加多种全局美颜效果，也可以通过相关方法叠加滤镜、贴纸、美妆等自定义效果。
     * @since V4.2.202
     * @param beautyType 美颜类型。详细信息请参考 {@link video.NERtcBeautyEffectType}。
     * @param level      对应美颜类型的强度。取值范围为 [0, 1]，各种美颜效果的默认值不同。
     * @return
     * - 0：方法调用成功。
     * - 其他：方法调用失败。
     * @endif
     */
    public abstract int setBeautyEffect(NERtcBeautyEffectType beautyType, float level);

    /**
     * @if English
     * Add filters.
     * <br>The API is used to load filter assets and add related filter effects. To change a filter, call this method for a new filter.
     * @note 
     * - Before applying filters, stickers, and makeups, contact your account manager to get beauty assets or models.
     * - A filter effect can be applied together with global beauty effects, stickers, and makeups. However, multiple filters cannot be applied at the same time.
     * @since V4.2.202
     * @param path The path of the filter assets or models. An absolute path on an SD card or the asset directory is supported.
     * - SD cardL: "/storage/emulated/0/Android/data/com.netease.lava.nertc.demo/files/filter_portrait/filter_style_FN1".
     * - asset: "2D/bunny".
     * @return
     * - 0: success
     * - Others: failure
     * @endif
     * @if Chinese
     * 添加滤镜效果。
     * <br>此接口用于加载滤镜资源，并添加对应的滤镜效果。需要更换滤镜时，重复调用此接口使用新的滤镜资源即可。
     * @note
     * - 使用滤镜、贴纸和美妆等自定义美颜效果之前，请联系商务经理获取美颜资源或模型。
     * - 滤镜效果可以和全局美颜、贴纸、美妆等效果互相叠加，但是不支持叠加多个滤镜。
     * @since V4.2.202
     * @param path 滤镜资源或模型所在路径。支持 SD 卡上的绝对路径，或 asset 目录下的相对路径。
     * - SD卡："/storage/emulated/0/Android/data/com.netease.lava.nertc.demo/files/filter_portrait/filter_style_FN1"。
     * - asset: "2D/bunny"。
     * @return
     * - 0：方法调用成功。
     * - 其他：方法调用失败。
     * @endif
     */
    public abstract int addBeautyFilter(String path);

    /**
     * @if English
     * Removes a filter effect.
     * @since V4.2.202
     * @return
     * - 0: success
     * - Others: failure
     * @endif    
     * @if Chinese
     * 取消滤镜效果。
     * @since V4.2.202
     * @return
     * - 0：方法调用成功。
     * - 其他：方法调用失败。
     * @endif
     */
    public abstract void removeBeautyFilter();

    /**
     * @if English
     * Sets the filter intensity
     * A larger value indicates more intensity. You can adjust a custom value based on business requirements.
     * @note The setting takes effect when it is applied. The intensity remains if a filter is changes. You can adjust the intensity by setting this property.
     * @since V4.2.202
     * @param level Filter intensity. Value range: 0 - 1. Default value: 0.5.
     * @return
     * - 0: success
     * - Others: failure
     * @endif
     * @if Chinese
     * 设置滤镜强度。
     * <br>取值越大，滤镜强度越大，您可以根据业务需求自定义设置滤镜强度。
     * @note 滤镜强度设置实时生效，更换滤镜后滤镜强度不变，如需调整，可以再次调用此接口重新设置滤镜强度。
     * @since V4.2.202
     * @param level 滤镜强度。取值范围为 [0 - 1]，默认值为 0.5。
     * @return
     * - 0：方法调用成功。
     * - 其他：方法调用失败。
     * @endif
     */
    public abstract int setBeautyFilterLevel(float level);

    /**
     * @if English
     * Adds a sticker (beta).
     * <br>The API is used to load sticker assets and add related sticker effects. To change a sticker, call this method for a new sticker.
     * @note 
     * - Before applying filters, stickers, and makeups, you must prepare beauty assets or models.
     * - A sticker effect can be applied together with global beauty effects, stickers, and makeups. However, multiple stickers cannot be applied at the same time.
     * @since V4.2.202
     * @param path The path of the sticker assets or models. An absolute path on an SD card or the asset directory is supported.
     * - SD cardL: "/storage/emulated/0/Android/data/com.netease.lava.nertc.demo/files/filter_portrait/filter_style_FN1".
     * - asset: "2D/bunny".
     * @return
     * - 0: success
     * - Others: failure
     * @endif
     * @if Chinese
     * （此接口为 beta 版本）添加贴纸效果。
     * <br>此接口用于加载贴纸资源，添加对应的贴纸效果。需要更换贴纸时，重复调用此接口使用新的贴纸资源即可。
     * @note
     * - 使用滤镜、贴纸和美妆等自定义美颜效果之前，需要先准备好对应的美颜资源或模型。
     * - 贴纸效果可以和全局美颜、滤镜、美妆等效果互相叠加，但是不支持叠加多个贴纸。
     * @since V4.2.202
     * @param path 贴纸资源所在路径。支持 SD 卡上的绝对路径，或 asset 目录下的相对路径。
     * - SD卡："/storage/emulated/0/Android/data/com.netease.lava.nertc.demo/files/filter_portrait/filter_style_FN1"
     * - asset: "2D/bunny"
     * @return
     * - 0：方法调用成功。
     * - 其他：方法调用失败。
     * @endif
     */
    public abstract int addBeautySticker(String path);

    /**
     * @if English
     * Removes a sticker (beta).
     * @since V4.2.202
     * @return
     * - 0: success
     * - Others: failure
     * @endif
     * @if Chinese
     * （此接口为 beta 版本）取消贴纸效果。
     * @since V4.2.202
     * @return
     * - 0：方法调用成功。
     * - 其他：方法调用失败。
     * @endif
     */
    public abstract void removeBeautySticker();

    /**
     * @if English
     * Adds a makeup effect (beta).
     * <br>The API is used to load makeup assets and add related sticker effects. To change a makeup effect, call this method for a new makeup effect.
     * @note 
     * - Before applying filters, stickers, and makeups, you must call {@link NERtcBeauty#addTempleteWithPath:andName:} to import beauty assets or models.
     * - A makeup effect can be applied together with global beauty effects, stickers, and makeups. However, multiple makeup effects cannot be applied at the same time.
     * @since V4.2.202
     * @param path The path of the makeup assets or models. An absolute path on an SD card or the asset directory is supported.
     * - SD cardL: "/storage/emulated/0/Android/data/com.netease.lava.nertc.demo/files/filter_portrait/filter_style_FN1".
     * - asset: "2D/bunny".
     * @return
     * - 0: success
     * - Others: failure
     * @endif
     * @if Chinese
     * （此接口为 beta 版本）添加美妆效果。
     * <br>此接口用于加载美妆模型，添加对应的美妆效果。需要更换美妆效果时，重复调用此接口使用新的美妆模型即可。
     * @note
     * - 使用滤镜、贴纸和美妆等自定义美颜效果之前，需要先准备好对应的美颜资源或模型。
     * - 美妆效果可以和全局美颜、滤镜、贴纸等效果互相叠加，但是不支持叠加多个美妆效果。
     * @since V4.2.202
     * @param path 美妆模型所在路径。支持 SD 卡上的绝对路径，或 asset 目录下的相对路径。
     * - SD卡："/storage/emulated/0/Android/data/com.netease.lava.nertc.demo/files/filter_portrait/filter_style_FN1"
     * - asset: "2D/bunny"
     * @return
     * - 0：方法调用成功。
     * - 其他：方法调用失败。
     * @endif
     */
    public abstract int addBeautyMakeup(String path);

    /**
     * @if English
     * Removes a makeup effect (beta).
     * @since V4.2.202
     * @return
     * - 0: success
     * - Others: failure
     * @endif
     * @if Chinese
     * （此接口为 beta 版本）取消美妆效果。
     * @since V4.2.202
     * @return
     * - 0：方法调用成功。
     * - 其他：方法调用失败。
     * @endif
     */
    public abstract void removeBeautyMakeup();

    /**
     * @if English
     * Sets a video watermark. The watermark takes effect for local preview and publishing.
     * @note If you set a watermark, we recommend you notice the status callback {@link NERtcCallbackEx#onLocalVideoWatermarkState}.
     * @since V4.6.10
     * @param type   The type of the video stream on which a watermark is applied, mainstream or substream. For more information, see {@link video.NERtcVideoStreamType}.
     * @param config Watermark configuration. If the value is set to null, all previous watermarks are canceled. For more information, see {@link watermark.NERtcVideoWatermarkConfig}.
     * @return
     * - 0: success
     * - Others: failure
     * @endif
     * @if Chinese
     * 设置视频水印。水印在本地预览及发送过程中均生效。
     * @note 设置水印后，建议关注水印状态回调 {@link NERtcCallbackEx#onLocalVideoWatermarkState}。
     * @since V4.6.10
     * @param type   水印的视频流类型。支持设置为主流或辅流。详细信息请参考 {@link video.NERtcVideoStreamType}。
     * @param config 水印设置。设置为 null 表示取消之前的水印。详细信息请参考 {@link watermark.NERtcVideoWatermarkConfig}。
     * @return
     * - 0：方法调用成功。
     * - 其他：方法调用失败。
     * @endif
     */
    public abstract int setLocalVideoWatermarkConfigs(NERtcVideoStreamType type, NERtcVideoWatermarkConfig config);

    /**
     * @if English
     * Switches between the front and rear cameras.
     * <br>Make sure that you call this method after the camera starts. For example, calling startVideoPreview or joinChannel.
     * @return {@code 0}    A value of 0 returned indicates that the method call is successful. Otherwise, the method call fails.
     * @endif
     * @if Chinese
     * 指定前置/后置摄像头。
     * <br>该方法需要在相机启动后调用，例如调用 {@link NERtc#startVideoPreview()} 或 {@link NERtc#joinChannel()} 后。
     * @note 纯音频 SDK 禁用该接口，如需使用请前往云信官网下载并替换成视频 SDK。
     * @since V4.6.10
     * @param cameraPos 相机类型。详细信息请参考 {@link NERtcConstants.CameraPosition}。该参数为必填参数，若未赋值，SDK 会报错。
     * @return
     * - 0：方法调用成功。
     * - 其他：方法调用失败。
     * @endif
     */
    public abstract int switchCameraWithPosition(int cameraPos);

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
     * Synchronizes the local time with the server time
     * @since V4.6.10
     * @param enable specifies whether to enable precise synchronization of the local time with the server time
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
    public abstract void setStreamAlignmentProperty(boolean enable);

    /**
     * @if English
     * Gets the difference between the local time and the server time.
     * <br>The method can sync the time between the client and server. To get the current server time, call (System.currentTimeMillis() - offset).
     * @since V4.6.10
     * @return Difference between the local time and the server time. Unit: milliseconds(ms). If a user fails to join a room, a value of 0 is returned.
     * @endif
     * @if Chinese
     * 获取本地系统时间与服务端时间差值。
     * <br>可以用于做时间对齐，通过 (System.currentTimeMillis() - offset) 可能得到当前服务端时间。
     * @since V4.6.10
     * @return 本地与服务端时间差值，单位为毫秒（ms）。如果没有成功加入音视频房间，返回 0。
     * @endif
     */
    public abstract long getNtpTimeOffset();

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
     * Enables or disables an external source input published over the audio substream.
     * <br>After the method is implemented, you can call {@link NERtcEx#pushExternalSubStreamAudioFrame(NERtcAudioExternalFrame)} to send the PCM data of the audio substream.
     * @note
     * - Call the method before {@link NERtcEx#pushExternalSubStreamAudioFrame(NERtcAudioExternalFrame)}.
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
     * <br>调用成功后可以使用 {@link NERtcEx#pushExternalSubStreamAudioFrame(NERtcAudioExternalFrame)} 接口发送音频辅流 PCM 数据。
     * @note
     * - 请在{@link NERtcEx#pushExternalSubStreamAudioFrame(NERtcAudioExternalFrame) }前调用该方法。
     * - 开启后音频辅流将由用户驱动，在 leaveChannel 后自动失效。
     * @since V4.6.10
     * @param enabled    是否开启外部音频辅流数据输入。
     *                   - true：开启外部音频辅流输入，使用外部音频源，音频辅流由用户驱动。
     *                   - false（默认）：关闭外部数据输入，不使用外部音频源，音频辅流由 SDK 驱动。
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
    public abstract int setExternalSubStreamAudioSource(boolean enabled, int sampleRate, int channels);


    /**
     * @if English
     * Pushes external audio source over the audio substream.
     * <br>The method pushes the audio frame data captured from an external source to the internal engine and enables the audio substream using {@link NERtcEx#enableLocalSubStreamAudio(boolean)}. The method can send the PCM data of an audio substream.
     * @note
     * - The method must be called after a user joins a room
     * - We recommend the data frame match 10ms as a cycle.
     * - The method is invalid after the audio substream is disabled.
     * @since V4.6.10
     * @param frame audio frame data.
     * @return 
     * - 0: success
     * - Others: failure
     * @endif
     * @if Chinese
     * 推送外部音频辅流数据帧。
     * <br>将外部音频辅流帧数据帧主动推送给内部引擎。通过 {@link NERtcEx#enableLocalSubStreamAudio(boolean)} 启用音频辅流后，可以调用此接口发送音频辅流 PCM 数据。
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
    public abstract int pushExternalSubStreamAudioFrame(NERtcAudioExternalFrame frame);

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
}
