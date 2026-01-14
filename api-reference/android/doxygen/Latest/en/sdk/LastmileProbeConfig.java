package com.netease.lava.nertc.sdk;
/** 
 * @if English
 * Configurations of the last-mile network probe test.
 * @endif
 * @if Chinese
 * Last mile 网络探测配置。 
 * @endif
 */
public class LastmileProbeConfig {
    /**
     * @if English
     * Sets whether to test the uplink network. 
     * <br>Some users, for example, the audience in a kNERtcChannelProfileLiveBroadcasting channel, do not need such a test。
     * - true: test.
     * - false: do not test.
     * @endif
     * @if Chinese
     * 是否探测上行网络。
     * <br>不发流的用户，例如直播房间中的普通观众，无需进行上行网络探测。
     * - true: 探测。
     * - false: 不探测。
     * @endif
     */
    public boolean probeUplink;

    /**
     * @if English
     * Sets whether to test the downlink network:
     * - true: test.
     * - false: do not test.
     * @endif
     * @if Chinese
     * 是否探测下行网络。
     * - true: 探测。
     * - false: 不探测。
     * @endif
     */
    public boolean probeDownlink;

    /**
     * @if English
     * The expected maximum sending bitrate (bps) of the local user. 
     * <br>The value ranges between 100000 and 5000000. 
     * <br>We recommend setting this parameter according to the bitrate value set by setVideoConfig.
     * @endif
     * @if Chinese
     * 本端期望的最高发送码率。
     * <br>单位为 bps，范围为 [100000, 5000000]。
     * <br>推荐参考 setVideoConfig 中的码率值设置该参数的值。
     * @endif
     */
    public int expectedUplinkBitrate;

    /**
     * @if English
     * The expected maximum receiving bitrate (bps) of the local user. The value ranges between 100000 and 5000000.
     * @endif
     * @if Chinese
     * 本端期望的最高接收码率。
     * <br>单位为 bps，范围为 [100000, 5000000]。 
     * @endif
     */
    public int expectedDownlinkBitrate;

    public LastmileProbeConfig() {
        probeUplink = true;
        probeDownlink = true;
        expectedUplinkBitrate = 2000000;
        expectedDownlinkBitrate = 2000000;
    }

    @Override
    /**
     * to be added
     */
    public String toString() {
        return "LastmileProbeConfig{" +
            "probeUplink=" + probeUplink +
            ", probeDownlink=" + probeDownlink +
            ", expectedUplinkBitrate=" + expectedUplinkBitrate +
            ", expectedDownlinkBitrate=" + expectedDownlinkBitrate +
            '}';
    }
}
