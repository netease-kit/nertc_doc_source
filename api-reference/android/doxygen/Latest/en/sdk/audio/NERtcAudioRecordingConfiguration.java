package com.netease.lava.nertc.sdk.audio;

public class NERtcAudioRecordingConfiguration {

    public enum NERtcAudioRecordingPosition {
        /**
        * 录制本地和所有远端用户混音后的音频（默认）
        */
        MIXED_RECORDING_AND_PLAYBACK,

        /**
         * 仅录制采集的音频
         */
        RECORDING,

        /**
         * 仅录制播放的音频
         */
        PLAYBACK,
    }

    public enum NERtcAudioRecordingCycleTime {

        /**
         * 音频录制缓存时间为0，实时写文件（默认）
         */
        CYCLE_TIME_0(0),

        /**
         * 音频录制缓存时间为10s，StopAudioRectording()后，将缓存都写到文件，文件数据时间跨度为: [0,10s]
         */
        CYCLE_TIME_10(10),

        /**
         * 音频录制缓存时间为60s，StopAudioRectording()后，将缓存都写到文件，文件数据时间跨度为: [0,60s]
         */
        CYCLE_TIME_60(60),

        /**
         * 音频录制缓存时间为360s，StopAudioRectording()后，将缓存都写到文件，文件数据时间跨度为: [0,360s]
         */
        CYCLE_TIME_360(360),

        /**
         * 音频录制缓存时间为900s，StopAudioRectording()后，将缓存都写到文件，文件数据时间跨度为: [0,900s]
         */
        CYCLE_TIME_900(900);

        private final int value;
        NERtcAudioRecordingCycleTime(int value) {
            this.value = value;
        }
        public int getValue() {
            return value;
        }
    }

    /**
     * 录音文件在本地保存的绝对路径，需要精确到文件名及格式。例如：sdcard/xxx/audio.aac。请确保指定的路径存在并且可写。目前仅支持 WAV 或 AAC 文件格式
     */
    public String recordFilePath;

    /**
     * 录音采样率（Hz），可以设为 16000、32000（默认）、44100 或 48000
     */
    public int recordSampleRate;

    /**
     * 录音音质，只在 AAC 格式下有效
     */
    public int recordQuality;

    /**
     * 录音文件所包含的内容。详细信息请参考 {@link NERtcAudioRecordingPosition}
     */
    public NERtcAudioRecordingPosition recordPosition;

    /**
     * 录制过程中，循环缓存的最大时间长度，单位(s)。详细信息请参考 {@link NERtcAudioRecordingCycleTime}
     */
    public NERtcAudioRecordingCycleTime recordCycleTime;
}
