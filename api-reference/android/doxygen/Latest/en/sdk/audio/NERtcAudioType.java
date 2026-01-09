package com.netease.lava.nertc.sdk.audio;

/** NERtcAudioType */
public enum NERtcAudioType {
    /** 
     * @if English
     * Unknown type
     * @endif
     * @if Chinese
     * 未知类型。
     * @endif
     */
    kAudioTypeUnknown(-1),
    /** 
     * PCM16
     */
    kAudioTypePCM16(0)
    ;

    private int type;
    /** NERtcAudioType */
    NERtcAudioType(int type) {
        this.type = type;
    }

    /** getType */
    public int getType() {
        return type;
    }

    /** find */
    public static NERtcAudioType find(int type) {
        for (NERtcAudioType t : NERtcAudioType.values()) {
            if (type == t.type) {
                return t;
            }
        }

        return kAudioTypeUnknown;
    }
}
