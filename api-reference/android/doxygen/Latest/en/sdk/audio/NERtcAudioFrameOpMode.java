package com.netease.lava.nertc.sdk.audio;

/** NERtcAudioFrameOpMode */
public interface NERtcAudioFrameOpMode {

    /**
     * @if English
     * Supports read-only mode.
     * <br>Users can only get raw audio data from AudioFrame.
     * <br>For example, if users capture data by using the SDK and publish a RTMP/RTMP stream, select this mode.
     * @endif
     * @if Chinese
     * 只读模式。
     * <br>用户仅从 AudioFrame 获取原始音频数据。
     * <br>例如，如用户通过 SDK 采集数据，自己进行 RTMP/RTMPS 推流，则可以选择该模式。
     * @endif
     */
    int kNERtcAudioFrameOpModeReadOnly    = 0;

    /**
     * @if English
     * Supports read and write mode.
     * <br>Users get and modify data by calling the AudioFrame API, and return the data to SDK for encoding transmission.
     * <br>For example, in certain cases, if users have processing modules for sound effect, and want to pre-process data such as voice changer, select the mode.
     * @endif
     * @if Chinese
     * 读写模式。
     * <br>用户从 AudioFrame 获取并修改数据，并返回给 SDK 进行编码传输。
     * <br>例如，如用户自己有音效处理模块，且想要根据实际需要对数据进行前处理 （例如变声），则可以选择该模式。
     * @endif
     */
    int kNERtcAudioFrameOpModeReadWrite   = 1;
}
