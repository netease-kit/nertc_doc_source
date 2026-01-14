/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk;

/**
 * @if English
 * NERtc asynchronous callback interface. Users can use this interface to handle callbacks from various NERtc states.
 * @endif
 * @if Chinese
 * NERtc 异步回调接口，用户需要实现该接口来完成对NERtc各种状态回调的处理。
 * @endif
 */
public interface NERtcCallback {

    /**
     * @if English
     * Occurs when a user joins a room. The callback indicates that the client has already signed in.
     * @param result    A value of 0 indicates that a user joins a room. Otherwise, the user fails to join a room. For more information, see {@link NERtcConstants.ErrorCode}.
     * @param channelId The ID of the room that the client joins.
     * @param elapsed   The time consumed from calling the joinChannel method to the time when the event occurs. Unit: milliseconds.
     * @param uid       User ID.
     * @endif
     * @if Chinese
     * 加入房间回调，表示客户端已经登入服务器。
     * @param result    0 表示加入房间成功；其他值表示加入房间失败，详细错误码请参考 {@link NERtcConstants.ErrorCode}。
     * @param channelId 客户端加入的房间 ID。
     * @param elapsed   从 joinChannel 开始到发生此事件过去的时间，单位为毫秒。
     * @param uid       用户 ID。 如果在 joinChannel 方法中指定了 uid，此处会返回指定的 ID; 如果未指定 uid（joinChannel 时uid=0），此处将返回云信服务器自动分配的 ID。
     * @endif
     */
    void onJoinChannel(int result,long channelId,long elapsed, long uid);

    /**
     * @if English
     * Occurs when a user leaves a room.
     * <br>After an app calls the leaveChannel method, SDK prompts whether the app successfully exits the room. 
     * @param result 0 indicates success. Other values indicate failure. For more information about error codes, see {@link NERtcConstants.ErrorCode}.
     *               When a user switches rooms, the code parameter takes NERtcConstants.ErrorCode#LEAVE_CHANNEL_FOR_SWITCH.
     * @endif
     * @if Chinese
     * 退出房间回调。
     * <br>App 调用 leaveChannel 方法后，SDK 提示 App 退出房间是否成功。 
     * @param result 0 表示成功；其他值表示退出房间失败，错误码请参考 {@link NERtcConstants.ErrorCode}。
     *               在快速切换房间时 code 为 NERtcConstants.ErrorCode#LEAVE_CHANNEL_FOR_SWITCH。
     * @endif
     */
    void onLeaveChannel(int result);

    /**
     * @if English
     * Occurs when a remote user joins the current room.
     * <br>The callback function prompts that a remote user joins the room and returns the ID of the user that joins the room. If the user ID already exists, the remote user also receives a message that the user already joins the room, which is returned by the callback function.
     * <br>The callback function will be triggered in the following cases:
     * - A remote user joins the room by calling the joinChannel method.
     * - A remote user rejoins the room after the client is disconnected.
     * @param uid indicates the ID of the user that joins the room.
     * @endif
     * @if Chinese
     * 远端用户（通信场景）/主播（直播场景）加入当前频道回调。
     * - 通信场景下，该回调提示有远端用户加入了频道，并返回新加入用户的 ID；如果加入之前，已经有其他用户在频道中了，新加入的用户也会收到这些已有用户加入频道的回调
     * - 直播场景下，该回调提示有主播加入了频道，并返回该主播的用户 ID。如果在加入之前，已经有主播在频道中了，新加入的用户也会收到已有主播加入频道的回调。
    
     * 该回调在如下情况下会被触发：
     * - 远端用户调用 joinChannel 方法加入房间。
     * - 远端用户网络中断后重新加入房间。
     * @note
     * 直播场景下：
        * - 主播间能相互收到新主播加入频道的回调，并能获得该主播的用户 ID。
        * - 观众也能收到新主播加入频道的回调，并能获得该主播的用户 ID。
        * - 当 Web 端加入直播频道时，只要 Web 端有推流，SDK 会默认该 Web 端为主播，并触发该回调。
     * @param uid 新加入房间的远端用户 ID。
     * @endif
     */
    void onUserJoined(long uid);

    /**
     * @if English
     * Occurs when a remote user leaves a room.
     * <br>Prompts that a remote user leaves the room or becomes disconnected.
     * <br>A user leaves a room due to the following reasons: the user exit the room or connections time out.
     * - If a user exit the room, the user will receive a message that the user leaves the room.
     * - If connections time out, the user does not receive any data packets for a period of 40 to 50 seconds, then the user becomes disconnected.
     * @param uid indicates the ID of the user that leaves the room.
     * @param reason The reason why user leaves the channel.
     * @endif
     * @if Chinese
     * 远端用户离开当前房间回调。
     * <br>提示有远端用户离开了房间（或掉线）。
     * <br>用户离开房间有两个原因，即正常离开和超时掉线：
     * - 正常离开的时候，远端用户会收到消息提示，判断用户离开房间。
     * - 超时掉线的依据是，在一定时间内（40~50s），用户没有收到对方的任何数据包，则判定为对方掉线。
     * @param uid 离开房间的远端用户 ID。
     * @param reason 离开原因。
     * - kNERtcSessionLeaveNormal(0)：正常离开。
     * - kNERtcSessionLeaveForFailOver(1)：用户断线导致离开房间。
     * - kNERTCSessionLeaveForUpdate(2)：用户因 Failover 导致离开房间，仅 SDK 内部使用。
     * - kNERtcSessionLeaveForKick(3)：用户被踢导致离开房间。
     * - kNERtcSessionLeaveTimeout(4)：用户超时退出房间。
     * @endif
     */
    void onUserLeave(long uid,int reason);

    /**
     * @if English
     * Occurs when a remote user enables audio.
     * @param uid indicates the ID of the remote user.
     * @endif
     * @if Chinese
     * 远端用户开启音频回调。
     * @note 该回调由远端用户调用 enableLocalAudio 方法开启音频采集和发送触发。
     * @param uid   远端用户 ID。
     * @endif
     */
    void onUserAudioStart(long uid);

    /**
     * @if English
     * Occurs when a remote user disables audio.
     * @param uid indicates the ID of the remote user.
     * @endif
     * @if Chinese
     * 远端用户停用音频回调。
     * @note 该回调由远端用户调用 enableLocalAudio 方法关闭音频采集和发送触发。
     * @param uid   远端用户 ID。
     * @endif
     */
    void onUserAudioStop(long uid);


    /**
     * @if English
     * Occurs when a remote user enables video.
     * @param uid           indicates the ID of the user that sends the video streams.
     * @param maxProfile    sets the video parameters. For more information, see {@link NERtcConstants.VideoProfile}.
     * @endif
     * @if Chinese
     * 远端用户开启视频回调。
     * <br> 启用后，用户可以进行视频通话或直播。
     * @param uid  用户 ID，提示是哪个用户的视频流。
     * @param maxProfile 视频编码配置，详细信息请参考 {@link NERtcConstants.VideoProfile}。
     * @endif
     */
    void onUserVideoStart(long uid,int maxProfile);

    /**
     * @if English
     * Occurs when a remote user disables video.
     * @param uid indicates the ID of a remote user.
     * @endif
     * @if Chinese
     * 远端用户停用视频回调。
     * <br> 关闭后，用户只能进行语音通话或者直播。
     * @param uid  远端用户 ID。
     * @endif
     */
    void onUserVideoStop(long uid);

    /**
     * @if English
     * Indicates that connection breaks down and the SDK fails to connect to the server three consecutive times.
     * @note
     * - The callback function is triggered if the SDK fails to connect to the server three consecutive times after the joinChannel is successfully called. 
     * - If the SDK fails to connect to the server three consecutive times, the SDK stops retries.
     * @param reason indicates the reason for network disconnection. For more information, see {@link NERtcConstants.ErrorCode}.
     * @endif
     * @if Chinese
     * 网络连接中断，且 SDK 连续 3 次重连服务器失败。
     * <br><b>注意</b>：
     * - SDK 在调用 joinChannel 加入房间成功后，如果和服务器失去连接且连续 3 次重连失败，就会触发该回调。 
     * - 如果 SDK 在断开连接后，连续 3 次重连失败，SDK 会停止尝试重连。
     * @param reason 网络连接中断原因。详细信息请查看 {@link NERtcConstants.ErrorCode}。
     * @endif
     */
    void onDisconnect(int reason);


    /**
     * @if English
     * Occurs when a user changes the role in live streaming.
     * <br>After the user joins a room, the user can call the setClientRole method to change roles. Then, the callback is triggered. For example, switching from host to audience, or from audience to host.
     * <br>@note
     * <br>In live streaming, if you join a room and successfully call this method to change roles, the following callback functions are triggered.
     * - If the role is changed from host to audience, the onClientRoleChange callback is locally triggered, and the onUserLeave callback is remotely triggered.
     * - If the role is changed from audience to host, the onClientRoleChange callback is locally triggered, and the onUserJoined callback is remotely triggered.
     * @param oldRole indicates the role before the change. For more information, see {@link NERtcConstants.UserRole}.
     * @param newRole indicates the role after the change. For more information, see {@link NERtcConstants.UserRole}.
     * @endif
     * @if Chinese
     * 直播场景下用户角色已切换回调。
     * <br>用户加入房间后，通过 {@link NERtcEx#setClientRole()} 切换用户角色后会触发此回调。例如从主播切换为观众、从观众切换为主播。
     * <br><b>注意</b>：
     * <br>直播场景下，如果您在加入房间后调用该方法切换用户角色，调用成功后，会触发以下回调：
     * - 主播切观众，本端触发 onClientRoleChange 回调，远端触发 {@link NERtcCallback#onUserLeave()} 回调。
     * - 观众切主播，本端触发 onClientRoleChange 回调，远端触发 {@link NERtcCallback#onUserJoined()} 回调。
     * @param oldRole 切换前的角色。详细信息请参考 {@link NERtcConstants.UserRole}。
     * @param newRole 切换后的角色。详细信息请参考 {@link NERtcConstants.UserRole}。
     * @endif
     */
    void onClientRoleChange(int oldRole, int newRole);
}
