/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk;

import com.netease.lava.base.annotation.IntDef;
import com.netease.lava.base.annotation.Runtime;
import com.netease.lava.base.annotation.StringDef;
import com.netease.lava.base.annotation.Writable;
import com.netease.lava.nertc.impl.RtcKey;
import com.netease.lava.nertc.impl.RtcParameters;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.netease.lava.base.util.Checker.checkNotNull;
import static com.netease.lava.nertc.sdk.NERtcConstants.MediaCodecMode;
import static com.netease.lava.nertc.sdk.NERtcConstants.ServerRecordMode;
import static com.netease.lava.nertc.sdk.NERtcConstants.VideoSendMode;


/**
 * @if English
 * Obtains and sets the parameters related to audio and video calls before or during the call.
 * @endif
 * @if Chinese
 * 音视频通话相关的参数设置, 可以在通话前或者在通话过程中获取与设置这些参数。
 * @endif
 */
public class NERtcParameters {

    /**
     * @if English
     * {@code NERtc} Optional settings help to get whether the parameter is settable, and whether the parameter is adaptable during a call.
     * @endif
     * @if Chinese
     * {@code NERtc} 可选设置项, 可以获取参数是否支持可设置，以及参数是否能够在通话过程中进行操作。
     * @endif
     */

    public static class Key<T> implements Serializable {

        private static final long serialVersionUID = -6134470425545069806L;

        private final RtcKey<T> mKey;
        private final boolean mWritable;
        private final boolean mRuntime;

        Key(final String name, Class<T> clazz) {
            mKey = new RtcKey<>(name, clazz);
            mWritable = RtcParameters.writeSupported(name);
            mRuntime = RtcParameters.runtimeSupported(name);
        }


        private static class SpecializedKey<T> extends Key<T> {
            private static final long serialVersionUID = -45256262481811531L;

            SpecializedKey(String name, Class<T> clazz) {
                super(name, clazz);
            }
        }


        /**
         * @if English
         * Creates specific key.
         * @param name the parameter name
         * @return Key
         * @endif
         * @if Chinese
         * 创建特殊Key。
         * @param name 参数名
         * @return Key
         * @endif
         */
        public static Key<?> createSpecializedKey(final String name) {
            Class<?> clazz = RtcParameters.getKeyType(name);
            if (clazz == null) {
                clazz = Object.class;
//                throw new IllegalArgumentException(
//                        "Key " + name + " unsupported！");
            }
            return new SpecializedKey<>(name, clazz);
        }

        /**
         * @if English 
         * Parameter name.
         * @return Returns to an actual parameter name.
         * @endif
         * @if Chinese
         * 参数名
         * @return 返回实际的参数名
         * @endif
         */
        public String name() {
            return mKey.getName();
        }

        /**
         * @if English 
         * Whether to support parameter settings during a call.  
         * @return {@code true} Support. 
         * @endif
         * @if Chinese
         * 是否支持通话过程中设置
         * @return {@code true} 支持
         * @endif
         */
        public boolean runtime() {
            return mRuntime;
        }

        /**
         * @if English 
         * Whether the parameters are writable. All parameters are readable by default.
         * @return {@code true} Writable.
         * @endif
         * @if Chinese
         * 参数是否支持可写，默认所有参数都支持可读
         * @return {@code true} 可写
         * @endif
         */
        public boolean writable() {
            return mWritable;
        }


        /**
         * @if English 
         * Data types supported by parameters. 
         * @return Data types. 
         * @endif
         * @if Chinese
         * 参数支持的数据类型
         * @return 数据类型
         * @endif
         */
        public final Class<T> type() {
            return mKey.getType();
        }

        /**
         * @if English 
         * {@inheritDoc}
         * @endif
         * @if Chinese
         * {@inheritDoc}
         * @endif
         */
        @Override
        public final int hashCode() {
            return mKey.hashCode();
        }

        /**
         * @if English 
         * {@inheritDoc}
         * @endif
         * @if Chinese
         * {@inheritDoc}
         * @endif
         */
        @SuppressWarnings("unchecked")
        @Override
        public final boolean equals(Object o) {
            return o instanceof Key && ((Key<T>) o).mKey.equals(mKey);
        }

        /**
         * @if English
         * {@inheritDoc}
         * @endif
         * @if Chinese
         * {@inheritDoc}
         * @endif
         */
        @Override
        /**
         * to be added
         */
        public String toString() {
            return String.format("Key(Name:%s, Type:%s, Writable:%s, Runtime:%s)", name(), type(), writable(), runtime());
        }

    }

//    /**
//     * @if English 
//     * Whether to select p2p mode.
//     * @endif
//     * @if Chinese
//     * 是否P2P模式
//     * @endif
//     */
//    @Writable
//    public static final Key<Boolean> KEY_CHANNEL_P2P_ONLY =
//            new Key<>(RtcParameters.KEY_CHANNEL_P2P_ONLY, Boolean.class);

//    /**
//     * @if English 
//     * Whether to send audio automatically after joining a channel. 
//     * @endif
//     * @if Chinese
//     * 是否在加入房间后自动发送音频
//     * @endif
//     */
//    @Writable
//    public static final Key<Boolean> KEY_AUTO_START_LOCAL_AUDIO =
//            new Key<>(RtcParameters.KEY_AUTO_START_LOCAL_AUDIO, Boolean.class);
//
//    /**
//     * @if English 
//     * Whether to send video automatically after joining a channel. 
//     * @endif
//     * @if Chinese
//     * 是否在加入房间后自动发送视频
//     * @endif
//     */
//    @Writable
//    public static final Key<Boolean> KEY_AUTO_START_LOCAL_VIDEO =
//            new Key<>(RtcParameters.KEY_AUTO_START_LOCAL_VIDEO, Boolean.class);

    /**
     * @if English
     * Whether to subscribe to audio automatically. true is subscription by default.
     * @endif
     * @if Chinese
     * 是否自动订阅音频，默认为 true，即订阅音频。
     * @endif
     */

    @Writable
    public static final Key<Boolean> KEY_AUTO_SUBSCRIBE_AUDIO =
            new Key<>(RtcParameters.KEY_AUTO_SUBSCRIBE_AUDIO, Boolean.class);



    /**
     * @if English
     * Whether to subscribe to video automatically. False is subscription by default.
     * @since 4.6.0
     * @endif
     * @if Chinese
     * 是否自动订阅视频（包括主流、辅流），默认为 false, 即不订阅视频。
     * @since 4.6.0
     * @endif
     */
    @Writable
    public static final Key<Boolean> KEY_AUTO_SUBSCRIBE_VIDEO =
            new Key<>(RtcParameters.KEY_AUTO_SUBSCRIBE_VIDEO, Boolean.class);


    /**
     * @if English
     * Whether to enable bluetooth SCO.
     * @endif
     * @if Chinese
     * 是否关闭蓝牙SCO
     * @endif
     */

    @Writable
    public static final Key<Boolean> KEY_AUDIO_BLUETOOTH_SCO =
            new Key<>(RtcParameters.KEY_AUDIO_BLUETOOTH_SCO, Boolean.class);

//    /**
//     * @if English 
//     * Whether to subscribe to video automatically (Valid in one-to-one mode)
//     * @endif
//     * @if Chinese
//     * 是否自动订阅视频（仅一对一模式下有效）
//     * @endif
//     */
//    @Writable
//    public static final Key<Boolean> KEY_AUTO_SUBSCRIBE_VIDEO =
//            new Key<>(RtcParameters.KEY_AUTO_SUBSCRIBE_VIDEO, Boolean.class);

    /**
     * @if English
     * Whether to preview the front camera with mirror mode. Default: true. The setting enables the mirror mode. 
     * @endif
     * @if Chinese
     * 前置摄像头预览是否采用镜像模式。默认为 true，开启镜像模式。
     * @endif
     */

    @Writable
    @Runtime
    public static final Key<Boolean> KEY_VIDEO_LOCAL_PREVIEW_MIRROR =
            new Key<>(RtcParameters.KEY_VIDEO_LOCAL_PREVIEW_MIRROR, Boolean.class);

    /**
     * @if English
     * Video camera type.
     * @endif
     * @if Chinese
     * 摄像头类型。
     * @endif
     */

    @Writable
    @Runtime
    public static final Key<Integer> KEY_VIDEO_CAMERA_TYPE =
            new Key<>(RtcParameters.KEY_VIDEO_CAMERA_TYPE, Integer.class);
    /**
     * @if English
     * Video encoding mode. 
     * @see MediaCodecMode#MEDIA_CODEC_DEFAULT
     * @see MediaCodecMode#MEDIA_CODEC_HARDWARE
     * @see MediaCodecMode#MEDIA_CODEC_SOFTWARE
     * @endif
     * @if Chinese
     * 视频编码模式。
     * @see MediaCodecMode#MEDIA_CODEC_DEFAULT
     * @see MediaCodecMode#MEDIA_CODEC_HARDWARE
     * @see MediaCodecMode#MEDIA_CODEC_SOFTWARE
     * @endif
     */

    @Writable
    @StringDef({
            MediaCodecMode.MEDIA_CODEC_DEFAULT,
            MediaCodecMode.MEDIA_CODEC_HARDWARE,
            MediaCodecMode.MEDIA_CODEC_SOFTWARE})
    public static final Key<String> KEY_VIDEO_ENCODE_MODE =
            new Key<>(RtcParameters.KEY_VIDEO_ENCODE_MODE, String.class);

    /**
     * @if English
     * Video decoding mode.
     * @see MediaCodecMode#MEDIA_CODEC_DEFAULT
     * @see MediaCodecMode#MEDIA_CODEC_HARDWARE
     * @see MediaCodecMode#MEDIA_CODEC_SOFTWARE
     * @endif
     * @if Chinese
     * 视频解码模式。
     * @see MediaCodecMode#MEDIA_CODEC_DEFAULT
     * @see MediaCodecMode#MEDIA_CODEC_HARDWARE
     * @see MediaCodecMode#MEDIA_CODEC_SOFTWARE
     * @endif
     */

    @Writable
    @StringDef({
            MediaCodecMode.MEDIA_CODEC_DEFAULT,
            MediaCodecMode.MEDIA_CODEC_HARDWARE,
            MediaCodecMode.MEDIA_CODEC_SOFTWARE})
    public static final Key<String> KEY_VIDEO_DECODE_MODE =
            new Key<>(RtcParameters.KEY_VIDEO_DECODE_MODE, String.class);

    /**
     * @if English
     * Whether to enable cloud audio recording. Default: false. Disables audio recording.
     * @endif
     * @if Chinese
     * 是否开启云端音频录制。默认为 false，即关闭音频录制。
     * @endif
     */

    @Writable
    public static final Key<Boolean> KEY_SERVER_RECORD_AUDIO =
            new Key<>(RtcParameters.KEY_SERVER_RECORD_AUDIO, Boolean.class);


    /**
     * @if English
     * Whether to enable cloud video recording. Default: false. Disables video recording.
     * @endif
     * @if Chinese
     * 是否开启云端视频录制。默认为 false，即关闭视频录制。
     * @endif
     */

    @Writable
    public static final Key<Boolean> KEY_SERVER_RECORD_VIDEO =
            new Key<>(RtcParameters.KEY_SERVER_RECORD_VIDEO, Boolean.class);

//    /**
//     * @if English 
//     * Whether to automatically adjust resolution rate according to network conditions.
//     * @endif
//     * @if Chinese
//     * 是否允许根据网络情况自动调节分辨率
//     * @endif
//     */
//    @Writable
//    public static final Key<Boolean> KEY_VIDEO_ADAPT =
//            new Key<>(RtcParameters.KEY_VIDEO_ADAPT, Boolean.class);


    /**
     * @if English
     * Cloud recording mode.
     * @see ServerRecordMode#MIX_AND_SINGLE
     * @see ServerRecordMode#MIX
     * @see ServerRecordMode#SINGLE
     * @endif
     * @if Chinese
     * 云端录制模式。
     * @see ServerRecordMode#MIX_AND_SINGLE
     * @see ServerRecordMode#MIX
     * @see ServerRecordMode#SINGLE
     * @endif
     */

    @Writable
    @IntDef({ServerRecordMode.MIX_AND_SINGLE,
            ServerRecordMode.MIX,
            ServerRecordMode.SINGLE})
    public static final Key<Integer> KEY_SERVER_RECORD_MODE =
            new Key<>(RtcParameters.KEY_SERVER_RECORD_MODE, Integer.class);

    /**
     * @if English
     * Whether to set the local server as the speaker of the cloud server recording. 
     * @endif
     * @if Chinese
     * 本端是否为云端录制的主讲人。
     * @endif
     */

    @Writable
    public static final Key<Boolean> KEY_SERVER_RECORD_SPEAKER =
            new Key<>(RtcParameters.KEY_SERVER_RECORD_SPEAKER, Boolean.class);

    /**
     * @if English
     * Whether to push your own video streams while the channel pushes the stream to CDN. The default value is true.
     * @deprecated
     * @endif
     * @if Chinese
     * 在旁路推流场景中，是否允许推送本地媒体流到 CDN。默认值 true。
     * @deprecated 该参数已废弃。
     * @endif
     */
    @Writable
    public static final Key<Boolean> KEY_PUBLISH_SELF_STREAM =
            new Key<>(RtcParameters.KEY_PUBLISH_SELF_STREAM, Boolean.class);


    /**
     * @if English
     * Video publish mode.
     * @see VideoSendMode#SEND_ON_PUB_NONE
     * @see VideoSendMode#SEND_ON_PUB_HIGH
     * @see VideoSendMode#SEND_ON_PUB_LOW
     * @see VideoSendMode#SEND_ON_PUB_ALL
     * @endif
     * @if Chinese
     * 视频发布模式
     * @see VideoSendMode#SEND_ON_PUB_NONE
     * @see VideoSendMode#SEND_ON_PUB_HIGH
     * @see VideoSendMode#SEND_ON_PUB_LOW
     * @see VideoSendMode#SEND_ON_PUB_ALL
     * @endif
     */

    @Writable
    @IntDef({VideoSendMode.SEND_ON_PUB_NONE,
            VideoSendMode.SEND_ON_PUB_HIGH,
            VideoSendMode.SEND_ON_PUB_LOW,
            VideoSendMode.SEND_ON_PUB_ALL})
    public static final Key<Integer> KEY_VIDEO_SEND_MODE =
            new Key<>(RtcParameters.KEY_VIDEO_SEND_MODE, Integer.class);


    /**
     * @if English
     * AI noise reduction switch.
     * <br>NERTC SDK developes noise reduction algorithm. With it enabled, reduces directional noise toward non-steady noises such as background and keyboard voices in noisy environments. The algorithm also can control environmental steady noises and retain more purer voices. * 
     * @endif
     * @if Chinese
     * AI 降噪开关。
     * <br>NERTC SDK 自研 AI 降噪算法，开启 AI 降噪之后，在嘈杂的环境中可以针对背景人声、键盘声等非稳态噪声进行定向降噪，同时也会提升对于环境稳态噪声的抑制，保留更纯粹的人声。     * 
     * @endif
     */

    @Writable
    public static final Key<Boolean> KEY_AUDIO_AI_NS_ENABLE =
            new Key<>(RtcParameters.KEY_AUDIO_AI_NS_ENABLE, Boolean.class);


    /**
     * @if English
     * Whether to enable dual call mode. Default: Disabled.
     * <br>Applicable to 1v1 call scenarios.
     * <br>@note
     * - While creating client server of the dual call mode and joining the channel. The channel is for the dual call with only client server enabling the mode permitted joining.
     * - Sets before you join the channel.
     * @endif
     * @if Chinese
     * 是否开启双人通话模式。默认为关闭状态。
     * <br>适用于 1v1 通话场景。
     * <br><b>注意</b>：
     * - 开启了双人通话模式的客户端创建并加入房间时，该房间会成为一个双人通话房间，只允许同样开启了双通话模式的客户端加入。
     * - 请在加入房间前设置。
     * @endif
     */

    @Writable
    public static final Key<Boolean> KEY_ENABLE_1V1_MODEL = new Key<>(RtcParameters.KEY_ENABLE_1V1_MODEL, Boolean.class);

    /**
     * @if English
     * Whether to support negative uid setting. Default: false. Negative uid is not supported.
     * <br>Sets the parameter before joining the channel. We don't recommend you to changed the parameter halfway. 
     * <br>@note
     * - We strongly recommend you use the negative uid because it is just a contingent remedy; if it exceeds 32 unsigned integers, duplicate uid may occur.
     * - If you set to support negative uid, the SDK will process the negative uid input from all APIs(uid & 0xffffffffL) to get the positive uid. All SDK callbacks follow with long positive uid. Therefore, you need to change the int yourself to get the corresponding negative uid.
     * @endif
     * @if Chinese
     * 是否支持设置负数 uid ，默认为 false，即不支持设置负数 uid。 
     * <br>该参数需要在加入房间前设置，且不建议中途更改。
     * <br><b>注意</b>：
     * - 强烈不建议使用负数 uid ，这个只是一个应急补救措施，如果超过了 32 无符整型，那么可能存在 uid 重复的问题。
     * - 如果设置支持负数 uid ，那么 SDK 会将通过所有 API 输入的负数uid 进行处理（uid & 0xffffffffL）得到正数 uid，所有 SDK 回调都是相应long 正数 uid，您需要自己强行转成 int 以能得到相应的负数。
     * @endif
     */

    @Writable
    public static final Key<Boolean> KEY_ENABLE_NEGATIVE_UID =
            new Key<>(RtcParameters.KEY_ENABLE_NEGATIVE_UID, Boolean.class);


    /**
     * @if English
     * A user-defined field in the login event is suitable for assisting clients in identifying additional information.
     * @endif
     * @if Chinese
     * Login 事件中的一个自定义字段，适用于协助客户标识一些额外信息
     * @endif
     */

    @Writable
    public static final Key<String> KEY_CUSTOM_EXTRA_INFO =
            new Key<String>(RtcParameters.KEY_CUSTOM_EXTRA_INFO, String.class);


    /**
     * @if English
     * Whether to return original volume when the local user is muted.  Boolean value, default: false.  
     * - true：Return the original volume in `NERtcCallbackEx#onLocalAudioVolumeIndication`.
     * - false：Return the recording volume(0) in `NERtcCallbackEx#onLocalAudioVolumeIndication`. 
     * @note Set the parameter before joining the channel.
     * @endif
     * @if Chinese
     * 本地用户静音时是否返回原始音量。 布尔值，默认值为 false。
     * - true：返回 `NERtcCallbackEx#onLocalAudioVolumeIndication` 中的原始音量。  
     * - false：返回 `NERtcCallbackEx#onLocalAudioVolumeIndication` 中的录音音量，静音时为 0。
     * @note 请在加入房间前调用此接口。
     * @endif
     */
    @Writable
    public static final Key<Boolean> KEY_ENABLE_REPORT_VOLUME_WHEN_MUTE = new Key<>(RtcParameters.KEY_ENABLE_REPORT_VOLUME_WHEN_MUTE, Boolean.class);

    private RtcParameters mRawParams;
    
    /** NERtcParameters*/
    public NERtcParameters() {
        mRawParams = new RtcParameters();
    }

    /** to be added*/
    public void clear() {
        mRawParams.clear();
    }
    /** to be added*/
    public RtcParameters getRawParameters() {
        return mRawParams;
    }
    /** to be added*/
    public void setRawParameters(RtcParameters parameters) {
        mRawParams = new RtcParameters();

        Set<String> keys = parameters.keys();

        if (keys != null) {
            for (String key : keys) {
                mRawParams.setObject(key, parameters.getObject(key));
            }
        }
    }
    /** to be added*/
    public final NERtcParameters setRequestKeys(Set<Key> keys) {
        checkNotNull(keys);

        Set<String> keyName = new HashSet<>(keys.size());
        for (Key key : keys) {
            keyName.add(key.name());
        }
        mRawParams.setRequestKeys(keyName);
        return this;
    }

    /** to be added*/
    public final NERtcParameters setRequestKey(Key key) {
        checkNotNull(key);
        mRawParams.setRequestKey(key.name());
        return this;
    }

    /** to be added*/
    public final boolean getBoolean(Key<Boolean> key) {
        return get(key);
    }
    /** to be added*/
    public final int getInteger(Key<Integer> key) {
        return get(key);
    }
    /** to be added*/
    public final String getString(Key<String> key) {
        return get(key);
    }
    /** to be added*/
    public final void setInteger(Key<Integer> key, int value) {
        set(key, value);
    }
    /** to be added*/
    public final void setBoolean(Key<Boolean> key, boolean value) {
        set(key, value);
    }
    /** to be added*/
    public final void setString(Key<String> key, String value) {
        set(key, value);
    }
    /** to be added*/
    public final boolean containsKey(Key key) {
        checkNotNull(key);
        return mRawParams.containsKey(key.name());
    }
    /** to be added*/
    public final <T> void set(Key<T> key, T value) {
        checkNotNull(key);
        mRawParams.setObject(key.name(), value);
    }
    /** to be added*/
    public final <T> T get(Key<T> key) {
        checkNotNull(key);
        T value = (T) mRawParams.getObject(key.name());
        return value;
    }
    /** to be added*/
    public final void setFloat(Key<Float> key, float value) {
        set(key, value);
    }
    /** to be added*/
    public final float getFloat(Key<Float> key) {
        return get(key);
    }
    /** to be added*/
    public final void removeParameters(Key key) {
        checkNotNull(key);
        mRawParams.removeParameters(key.name());
    }

}
