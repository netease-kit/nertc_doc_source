/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk;

import java.util.HashMap;
import java.util.Map;

/**
 * @if English
 * Related parameters settings relaying cross-room media. 
 * @endif
 * @if Chinese
 * 跨房间媒体转发相关的参数设置。
 * @endif
 */
public class NERtcMediaRelayParam {

    /**
     * @if English
     * {@code NERtc} Relays channel parameters.
     * @endif
     * @if Chinese
     * {@code NERtc} 转发房间参数
     * @endif
     */
    public class ChannelMediaRelayInfo {

        private long channelCid;
        private long channelUid = -1;// -1 ：user not set , 0 local uid
        private String channelName;
        private String channelToken;

        /**
         * @if English
         * {@code NERtc} Channel constructor.
         * @param token         Token with channel access.
         * @param channelName   Channel name. 
         * @param uid           User ID.
         * @endif
         * @if Chinese
         * {@code NERtc} 房间构造函数
         * @param token         能加入房间的 Token。
         * @param channelName   房间名。
         * @param uid           用户 ID。
         * @endif
         */
        public ChannelMediaRelayInfo(String token, String channelName, long uid) {
            channelUid = uid;
            this.channelName = channelName;
            channelToken = token;
            channelCid = 0;
        }

        /**
         * @if English
         * {@code NERtc} Gets token with channel access.
         * @endif
         * @if Chinese
         * {@code NERtc} 获取能加入房间的 Token。
         * @endif
         */
        public String getChannelToken() {
            return channelToken;
        }

        /**
         * @if English
         * {@code NERtc} Gets channel name. 
         * @endif
         * @if Chinese
         * {@code NERtc} 获取房间名。
         * @endif
         */
        public String getChannelName() {
            return channelName;
        }

        /**
         * @if English
         * {@code NERtc} Gets host ID in the channel.
         * @endif
         * @if Chinese
         * {@code NERtc} 获取房间中的主播 ID。
         * @endif
         */
        public long getChannelUid() {
            return channelUid;
        }

        /**
         * @if English
         * {@code NERtc} Gets channel ID.
         * @endif
         * @if Chinese
         * {@code NERtc} 获取房间 ID。
         * @endif
         */
        public long getChannelCid() {
            return channelCid;
        }
    }

    /**
     * @if English
     * {@code NERtc} Relays parameters including source channels and target channel lists.
     * @endif
     * @if Chinese
     * {@code NERtc} 转发参数，包括源房间、目标房间列表等。
     * @endif
     */
    public class ChannelMediaRelayConfiguration {

        /**
         * @if English
         * Source channel information {@link NERtcMediaRelayParam.ChannelMediaRelayInfo}
         * - channelName: Source channel name.
         * - token: Token with access to source channel. 
         * - uid: Identifies the UID of relaying media streams in the source channel. 
         * @endif
         * @if Chinese
         * 源房间信息。{@link NERtcMediaRelayParam.ChannelMediaRelayInfo}
         * - channelName：源房间名。
         * - token：能加入源房间的 token。
         * - uid：标识源房间中的转发媒体流的 UID。
         * @endif
         */
        public ChannelMediaRelayInfo sourceMediaInfo;
        /**
         * @if English
         * Target channel information list. {@link NERtcMediaRelayParam.ChannelMediaRelayInfo}
         * - channelName: Target channel names. 
         * - token: Token with access to target channels. 
         * - uid: Identifies the UID of relaying media stream in the target channel. Do not set this parameter as the UID of the host in the target channel. The parameter is different from all UIDs in the target channel.
         * @endif
         * @if Chinese
         * 目标房间信息列表。{@link NERtcMediaRelayParam.ChannelMediaRelayInfo}
         * - channelName：目标房间的房间名。
         * - token：可以加入目标房间的 Token。
         * - uid：标识目标房间中的转发媒体流的 UID。请确保不要将该参数设为目标房间的主播的 UID，并与目标房间中的 所有 UID 都不同。
         * @endif
         */
        public Map<String, ChannelMediaRelayInfo> destMediaInfo = new HashMap<String, ChannelMediaRelayInfo>();


        /**
         * @if English
         * {@code NERtc} ChannelMediaRelayConfiguration Default constructor function. 
         * @endif
         * @if Chinese
         * {@code NERtc} ChannelMediaRelayConfiguration默认构造函数。
         * @endif
         */
        public ChannelMediaRelayConfiguration() {

        }

        /**
         * @if English
         * {@code NERtc} Sets to relay source channel. Optional. The initial channel of current join channel is by default.
         * @param srcInfo   Source channel information.{@link NERtcMediaRelayParam.ChannelMediaRelayInfo}
         * @endif
         * @if Chinese
         * {@code NERtc} 设置转发源房间。可选，默认为当前 joinChannel 的初始房间。
         * @param srcInfo 源房间信息。{@link NERtcMediaRelayParam.ChannelMediaRelayInfo}
         * @endif
         */
        public void setSrcChannelInfo(ChannelMediaRelayInfo srcInfo) {
            sourceMediaInfo = srcInfo;
        }

        /**
         * @if English
         * Gets information about target channels relaying cross-room media streams.
         * <br>If you need to relay media streams to multiple channels, call this method for many times to set ChannelMediaRelayInfo in many channels. The method supports to set 4 target channels at most.
         * @param channelName   Target channel names. The parameter is mandatory, which should follow channelName used in the destInfo parameter.
         * @param destInfo      Target channel information.{@link NERtcMediaRelayParam.ChannelMediaRelayInfo}
         * @endif
         * @if Chinese
         * 设置跨房间媒体流转发的目标房间信息。
         * <br>如果您需要将媒体流转发到多个房间，可以多次调用该方法，设置多个房间的 ChannelMediaRelayInfo。该方法支持最多设置 4 个目标房间。
         * @param channelName   目标房间名称。该参数必填，且需与该方法 destInfo 参数中的 channelName 一致。
         * @param destInfo      目标房间信息。{@link NERtcMediaRelayParam.ChannelMediaRelayInfo}
         * @endif
         */
        public void setDestChannelInfo (String channelName, ChannelMediaRelayInfo destInfo) {
            if (destMediaInfo.get(channelName) == null) {
                destMediaInfo.put(channelName, destInfo);
            }
        }

        /**
         * @if English
         * Deletes channel information about relaying cross-room media streams.
         * @param channelName Removes the name of target channel relaying media streams.
         * @endif
         * @if Chinese
         * 删除跨房间媒体流转发的房间信息。
         * @param channelName 需要取消转发媒体流的目标房间名称
         * @endif
         */
        public void removeDestChannelInfo (String channelName) {
            destMediaInfo.remove(channelName);
        }

        /**
         * @if English
         * {@code NERtc} Gets information about relaying source channels.
         * @endif
         * @if Chinese
         * {@code NERtc} 获取转发源房间信息。
         * @endif
         */
        public ChannelMediaRelayInfo getSrcMediaInfo() {
            return sourceMediaInfo;
        }

        /**
         * @if English
         * {@code NERtc} Gets information about relaying target channels. 
         * @endif
         * @if Chinese
         * {@code NERtc} 获取转发目标房间信息。
         * @endif
         */
        public Map<String, ChannelMediaRelayInfo> getDstMediaInfo() {
            return destMediaInfo;
        }
    }
}
