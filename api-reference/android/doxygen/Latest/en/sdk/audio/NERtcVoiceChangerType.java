/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk.audio;

import com.netease.lava.api.model.RtcVoiceChangerType;
/** NERtcVoiceChangerType*/
public class NERtcVoiceChangerType {

    /**
     * @if English
     * The setting is disabled by default.
     * @endif
     * @if Chinese
     * 默认关闭。
     * @endif
     */
    public final static int AUDIO_EFFECT_OFF = 0;

    /**
     * @if English
     * The voice of a robot.
     * @endif
     * @if Chinese
     * 机器人。
     * @endif
     */

    public final static int VOICE_CHANGER_EFFECT_ROBOT = 1;

    /**
     * @if English
     * The voice of a giant.
     * @endif
     * @if Chinese
     * 巨人。
     * @endif
     */

    public final static int VOICE_CHANGER_EFFECT_GIANT = 2;

    /**
     * @if English
     * A more horrific voice.
     * @endif
     * @if Chinese
     * 恐怖。
     * @endif
     */
    public final static int VOICE_CHANGER_EFFECT_HORROR = 3;

    /**
     * @if English
     * A maturer voice.
     * @endif
     * @if Chinese
     * 成熟。
     * @endif
     */
    public final static int VOICE_CHANGER_EFFECT_MATURE = 4;

    /**
     * @if English
     * A male-sounding voice to a female-sounding voice.
     * @endif
     * @if Chinese
     * 男变女。
     * @endif
     */
    public final static int VOICE_CHANGER_EFFECT_MANTOWOMAN = 5;

    /**
     * @if English
     * A female-sounding voice to a male-sounding voice.
     * @endif
     * @if Chinese
     * 女变男。
     * @endif
     */
    public final static int VOICE_CHANGER_EFFECT_WOMANTOMAN = 6;

    /**
     * @if English
     * A male-sounding voice to a loli voice.
     * @endif
     * @if Chinese
     * 男变萝莉。
     * @endif
     */
    public final static int VOICE_CHANGER_EFFECT_MANTOLOLI = 7;

    /**
     * @if English
     * A female-sounding voice to a loli voice.
     * @endif
     * @if Chinese
     * 女变萝莉。
     * @endif
     */
    public final static int VOICE_CHANGER_EFFECT_WOMANTOLOLI = 8;

}
