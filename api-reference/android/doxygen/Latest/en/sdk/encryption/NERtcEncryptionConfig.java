/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk.encryption;
    /**
     * @if English
     * Configures the media stream encryption mode and key.
     * @endif
     * @if Chinese
     * 配置媒体流加密模式和密钥。
     * @endif
     */
public class NERtcEncryptionConfig {

    /**
     * @if English
     * The media stream encryption mode.
     * @endif
     * @if Chinese
     * 媒体流加密模式。
     * @endif
     */
    public final EncryptionMode mode ;

    /**
     * @if English
     * The media stream encryption key. The key is of STRING type. The encryption key is recommended to use letters.
     * @endif
     * @if Chinese
     * 媒体流加密密钥。字符串类型，推荐设置为英文字符串。
     * @endif
     */
    public final String key;

    /**
     * @if English
     * The media stream encryption mode.
     * @endif
     * @if Chinese
     * 媒体流加密模式。
     * @endif
     */
    public NERtcEncryptionConfig(EncryptionMode mode, String key) {
        this.mode = mode;
        this.key = key;
    }

    /**
     * @if English
     * The media stream encryption mode.
     * @endif
     * @if Chinese
     * 媒体流加密模式。
     * @endif
     */
    public enum EncryptionMode {
        /**
         * @if English
         * 128-bit SM4 encryption in ECB mode.
         * @endif
         * @if Chinese
         * 128 位 SM4 加密，ECB 模式。
         * @endif
         */
        GMCryptoSM4ECB,
    }

}
