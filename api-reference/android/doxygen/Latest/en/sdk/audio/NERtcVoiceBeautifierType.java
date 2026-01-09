/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk.audio;

import com.netease.lava.api.model.RTCVoiceBeautifierType;

/** NERtcVoiceBeautifierType*/
public class NERtcVoiceBeautifierType {

    /**
     * @if English
     * The setting is disabled by default.
     * @endif
     * @if Chinese
     * 默认关闭。
     * @endif
     */
    public static final int VOICE_BEAUTIFIER_OFF = 0;

    /**
     * @if English
     * A deeper voice.
     * @endif
     * @if Chinese
     * 低沉。
     * @endif
     */
    public static final int VOICE_BEAUTIFIER_MUFFLED = 1;
    /**
     * @if English
     * A mellower voice.
     * @endif
     * @if Chinese
     * 圆润。
     * @endif
     */
    public static final int VOICE_BEAUTIFIER_MELLOW = 2;
    /**
     * @if English
     * A clearer voice.
     * @endif
     * @if Chinese
     * 清澈。
     * @endif
     */
    public static final int VOICE_BEAUTIFIER_CLEAR = 3;
    /**
     * @if English
     * A more magnetic voice.
     * @endif
     * @if Chinese
     * 磁性。
     * @endif
     */
    public static final int VOICE_BEAUTIFIER_MAGNETIC = 4;
    /**
     * @if English
     * A recording studio effect.
     * @endif
     * @if Chinese
     * 录音棚。
     * @endif
     */
    public static final int VOICE_BEAUTIFIER_RECORDINGSTUDIO = 5;
    /**
     * @if English
     * A more natural voice.
     * @endif
     * @if Chinese
     * 天籁。
     * @endif
     */
    public static final int VOICE_BEAUTIFIER_NATURE = 6;
    /**
     * @if English
     * An Online KTV effect. 
     * @endif
     * @if Chinese
     * KTV。
     * @endif
     */
    public static final int VOICE_BEAUTIFIER_KTV = 7;
    /**
     * @if English
     * A remoter voice.
     * @endif
     * @if Chinese
     * 悠远。
     * @endif
     */
    public static final int VOICE_BEAUTIFIER_REMOTE = 8;
    /**
     * @if English
     * A church-based scenario.
     * @endif
     * @if Chinese
     * 教堂。
     * @endif
     */
    public static final int VOICE_BEAUTIFIER_CHURCH = 9;
    /**
     * @if English
     * A bedroom-based scenario.
     * @endif
     * @if Chinese
     * 卧室。
     * @endif
     */
    public static final int VOICE_BEAUTIFIER_BEDROOM = 10;
    /**
     * @if English
     * A live scenario.
     * @endif
     * @if Chinese
     * Live。
     * @endif
     */
    public static final int VOICE_BEAUTIFIER_LIVE = 11;


}
