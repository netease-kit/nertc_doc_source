package com.netease.lava.nertc.sdk;

/** 
 * @if English
 * The uplink and downlink last-mile network probe test result.
 * @endif
 * @if Chinese
 * 上下行 Last mile 网络质量探测结果。 
 * @endif
 */
public class LastmileProbeResult {

    /**
     * @if English 
     * States of the last-mile network probe test. 
     * - LASTMILE_PROBE_RESULT_COMPLETE(1)：The last-mile network probe test is complete.
     * - LASTMILE_PROBE_RESULT_INCOMPLETE_NO_BWE(2)：The last-mile network probe test is incomplete and the bandwidth estimation is not available, probably due to limited test resources.
     * - LASTMILE_PROBE_RESULT_UNAVAILABLE(3)：The last-mile network probe test is not carried out, probably due to poor network conditions.
     * @endif
     * @if Chinese
     * Last-mile 质量探测结果的状态，有如下几种：
     * - LASTMILE_PROBE_RESULT_COMPLETE(1)：表示本次 Last-mile 质量探测是完整的。
     * - LASTMILE_PROBE_RESULT_INCOMPLETE_NO_BWE(2)：表示本次 Last-mile 质量探测未进行带宽预测，因此结果不完整。通常原因为测试资源暂时受限。
     * - LASTMILE_PROBE_RESULT_UNAVAILABLE(3)：未进行 Last-mile 质量探测。通常原因为网络连接中断。
     * @endif
     */
    public short state;

    /**
     * @if English
     * The round-trip delay time (ms).
     * @endif
     * @if Chinese
     * 往返时延，单位为毫秒(ms)。
     * @endif
     */
    public int rtt;

    /**
     * @if English
     * The uplink last-mile network probe test result.
     * @endif
     * @if Chinese
     * 上行网络质量报告。
     * @endif
     */
    public LastmileProbeResult.LastmileProbeOneWayResult uplinkReport = new LastmileProbeResult.LastmileProbeOneWayResult();

    /**
     * @if English
     * The downlink last-mile network probe test result.
     * @endif
     * @if Chinese
     * 下行网络质量报告。
     * @endif
     */
    public LastmileProbeResult.LastmileProbeOneWayResult downlinkReport = new LastmileProbeResult.LastmileProbeOneWayResult();

    public LastmileProbeResult() {
    }

    /** 
     * @if English
     * The uplink or downlink last-mile network probe test result.
     * @endif
     * @if Chinese
     * 单向 Last mile 网络质量探测结果报告。 
     * @endif
     */
    public static class LastmileProbeOneWayResult {
        /**
         * @if English
         * The network jitter (ms).
         * @endif
         * @if Chinese
         * 网络抖动，单位为毫秒 (ms)。
         * @endif
         */
        public int packetLossRate;

        /**
         * @if English
         * The packet loss rate (%).
         * @endif
         * @if Chinese
         * 丢包率（%）。
         * @endif
         */
        public int jitter;

        /**
         * @if English
         * The available band width (bps).
         * @endif
         * @if Chinese
         * 可用网络带宽预估，单位为 bps。
         * @endif
         */
        public int availableBandwidth;

        public LastmileProbeOneWayResult() {
        }

        @Override
        /**
         * to be added
         */
        public String toString() {
            return "LastmileProbeOneWayResult{" +
                "packetLossRate=" + packetLossRate +
                ", jitter=" + jitter +
                ", availableBandwidth=" + availableBandwidth +
                '}';
        }
    }

    @Override
    /**
     * to be added
     */
    public String toString() {
        return "LastmileProbeResult{" +
            "state=" + state +
            ", rtt=" + rtt +
            ", uplinkReport=" + uplinkReport +
            ", downlinkReport=" + downlinkReport +
            '}';
    }
}
