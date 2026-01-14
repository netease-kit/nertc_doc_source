package com.netease.lava.nertc.sdk.audio;

public class NERtcReverbParam {

    /**
     * @if English
     * Wet sound signal. Value range: 0 ~ 1.
     * @endif
     * @if Chinese
     * 湿信号，取值范围为 0 ~ 1。
     * @endif
     */
    public float wetGain = 0.0f;

    /**
     * @if English
     * Dry sound signal. Value range: 0 ~ 1.
     * @endif
     * @if Chinese
     * 干信号，取值范围为 0 ~ 1。
     * @endif
     */
    public float dryGain = 1.0f;

    /**
     * @if English
     * Reverb damping. Value range: 0 ~ 1.
     * @endif
     * @if Chinese
     * 混响阻尼，取值范围为 0 ~ 1。
     * @endif
     */
    public float damping = 1.0f;

    /**
     * @if English
     * Room size. Value range: 0.1 ~ 2.
     * @endif
     * @if Chinese
     * 房间大小，取值范围为 0.1 ~ 2。
     * @endif
     */
    public float roomSize = 0.1f;

    /**
     * @if English
     * Decay time. Value range: 0.1 ~ 20.
     * @endif
     * @if Chinese
     * 持续强度（余响），取值范围为 0.1 ~ 20。
     * @endif
     */
    public float decayTime = 0.1f;

    /**
     * @if English
     * Pre-delay. Value range: 0 ~ 1.
     * @endif
     * @if Chinese
     * 延迟长度，取值范围为 0 ~ 1。
     * @endif
     */
    public float preDelay = 0.0f;
}
