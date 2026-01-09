package com.netease.lava.nertc.sdk.audio;

/**
 * @if English
 * Audio processing observer.
 * @endif
 * @if Chinese
 * 音频处理Observer
 * @endif
 */
public interface NERtcAudioProcessObserver {

    /**
     * @if English
     * Occurs when the system detects a howling.
     * <br>After calling setAudioProcessObserver to register an audio processing observer, the system triggers onAudioHasHowling if howling is detected.
     * <br>If the audio source is too close to amplification devices, howling is likely to take place. NERTC SDK supports howling detection. When detecting howling, the system automatically triggers the callback until the howling stops.
     * <br>The application layer can prompt the user to mute the microphone or mute the microphone itself when receiving a howl callback.
     * @note In most cases, you can implement howling detection to voice-only scenarios such as voice chatroom and online meetings. We recommend that you do not use the feature in recreational activities with background music.
     * @param flag true: Detects howling.
     * @endif
     * @if Chinese
     * 检测到啸叫回调。
     * <br>调用 setAudioProcessObserver 注册音频处理观测器后，如果检测到啸叫，会发送onAudioHasHowling 回调。
     * <br>当声源与扩音设备之间因距离过近时，可能会产生啸叫。NERTC SDK 支持啸叫检测，当检测到有啸叫信号产生的时候，自动触发该回调直至啸叫停止。
     * <br>App 应用层可以在收到啸叫回调时，提示用户静音麦克风，或直接静音麦克风。
     * @note 啸叫检测功能一般用于语音聊天室或在线会议等纯人声环境，不推荐在包含背景音乐的娱乐场景中使用。
     * @param flag true:检测到啸叫。
     * @endif
     */
    void onAudioHasHowling(boolean flag);

}
