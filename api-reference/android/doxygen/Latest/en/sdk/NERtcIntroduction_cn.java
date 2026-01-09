/*
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */
package com.netease.lava.nertc.sdk;

import com.netease.lava.nertc.sdk.NERtc;
import com.netease.lava.nertc.sdk.NERtcEx;
import com.netease.lava.nertc.sdk.channel.NERtcChannel;
import com.netease.lava.nertc.sdk.channel.NERtcChannelCallback;
import com.netease.lava.nertc.sdk.NERtcCallback;
import com.netease.lava.nertc.sdk.NERtcCallbackEx;
import com.netease.lava.nertc.sdk.video.NERtcVideoView;
import com.netease.lava.nertc.sdk.video.NERtcVideoCallback;
import com.netease.lava.nertc.sdk.stats.NERtcStatsObserver;
import com.netease.lava.nertc.sdk.audio.NERtcAudioFrameObserver;

/**
 * @mainpage 简介
 * @brief <p>网易云信 NERTC SDK
 * 提供完善的音视频通话开发框架，提供基于网络的点对点视频通话和语音通话功能，还提供多人视频和音频会议功能，支持通话中音视频设备控制和实时音视频模式切换，支持视频采集数据回调以实现美颜等自定义功能。</p>
 * - NERtc 接口类包含应用程序调用的主要方法。
 * - NERtcEx  接口类包含应用程序调用的扩展方法。
 * - NERtcCallback 类用于向应用程序发送用户状态回调通知。
 * - NERtcCallbackEx 类用于向应用程序发送音视频信息回调通知。
 * - {@link channel.NERtcChannel} 类在指定房间中实现实时音视频功能。通过创建多个 NERtcChannel 对象，用户可以同时加入多个房间。
 * - {@link channel.NERtcChannelCallback} 类监听和报告指定房间的事件和数据。
 * 
 * 
 * ## 错误码
 * 
 * 在调用 SDK API 的过程中，SDK 可能会返回错误码或状态码，您可以根据错误码或状态码判断当前 SDK 或任务的状态。如果遇到未知的错误码，请联系技术支持排查。
 * 
 * 当前 SDK API 的错误码如下：
 * - 通用错误码：{@link NERtcConstants.ErrorCode}
 * - 伴音错误码：{@link NERtcConstants.AudioMixingError}
 * - 直播推流错误码：{@link NERtcConstants.LiveStreamState}
 * - 客户端录音状态码：{@link NERtcConstants.AudioRecordingCode}
 * - 视频水印状态码：{@link NERtcConstants.NERtcLocalVideoWatermarkState}
 * - 其他错误码 {@link NERtcConstants.RuntimeError}

 <h2 id="房间管理">房间管理</h2>

 <table>
  <tr>
    <th width=400><b>方法</b></th>
    <th width=600><b>功能</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtc#getInstance()}</td>
    <td>创建 RTC 引擎对象。</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtc#init()}</td>
    <td> RTC 引擎对象初始化。</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtc#release()} </td>
    <td>销毁NERtc实例，释放资源。</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setChannelProfile()}</td>
    <td>设置房间场景。</td>
    <td>V3.6.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setClientRole()}</td>
    <td>设置用户角色。</td>
    <td>V3.9.0</td>
  </tr>
  <tr>
    <td> {@link NERtc#joinChannel()}</td>
    <td>加入房间。</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#switchChannel()}</td>
    <td>直播场景中快速切换房间。</td>
    <td>V4.1.0</td>
  </tr>
  <tr>
    <td> {@link NERtc#leaveChannel()}</td>
    <td>离开房间。</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtc#setParameters()}</td>
    <td>复杂参数设置接口。如果需要设置相关参数，请在调用 init 接口初始化之前调用此接口。</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#getConnectionState()}</td>
    <td>获取房间连接状态。</td>
    <td>V3.5.0</td>
  </tr>
 </table>

 ## 房间事件
 
 <table>
  <tr>
    <th width=400><b>事件</b></th>
    <th width=600><b>描述</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcCallback#onClientRoleChange()} </td>
    <td>用户角色已切换回调。</td>
    <td>V3.9.0</td>
  </tr>
  <tr>
    <td>  {@link NERtcCallback#onJoinChannel()} </td>
    <td>加入房间回调。</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onReJoinChannel()} </td>
    <td>重新加入房间回调。</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallback#onLeaveChannel()} </td>
    <td>离开房间回调。</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onReconnectingStart()} </td>
    <td>开始重连回调。</td>
    <td>V3.7.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallback#onUserJoined()} </td>
    <td>远端用户加入当前房间回调。</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallback#onUserLeave()} </td>
    <td>远端用户离开当前房间回调。</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallback#onDisconnect()} </td>
    <td>服务器连接断开回调。</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onConnectionTypeChanged()} </td>
    <td>本地网络类型已改变回调</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onConnectionStateChanged()}  </td>
    <td>房间连接状态已改变回调</td>
    <td>V3.8.0</td>
  </tr>
</table>

 ## 音频管理

 <table>
  <tr>
    <th width=400><b>方法</b></th>
    <th width=600><b>功能</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtc#setAudioProfile()} </td>
    <td>设置音频编码配置</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#adjustRecordingSignalVolume()} </td>
    <td>调节录音音量</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#adjustPlaybackSignalVolume()} </td>
    <td>调节播放音量</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#adjustUserPlaybackSignalVolume()} </td>
    <td>调节本地播放的指定远端用户的信号音量</td>
    <td>V4.2.1</td>
  </tr>
  <tr>
    <td> {@link NERtc#enableLocalAudio()} </td>
    <td>开关本地音频采集</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#muteLocalAudioStream()} </td>
    <td>开关本地音频发送</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtc#subscribeRemoteAudioStream()} </td>
    <td>订阅／取消订阅指定音频流</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtc#subscribeAllRemoteAudioStreams()} </td>
    <td>订阅／取消订阅所有远端音频流</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setAudioProcessObserver()} </td>
    <td>注册音频处理观测器，设置音频处理回调</td>
    <td>V3.9.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setRemoteHighPriorityAudioStream()} </td>
    <td>设置音频订阅优先级</td>
    <td>V4.6.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#enableLoopbackRecording()} </td>
    <td>开启或关闭音频共享</td>
    <td>V4.6.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#adjustLoopBackRecordingSignalVolume()} </td>
    <td>调整音频共享音量</td>
    <td>V4.6.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#enableLocalSubStreamAudio()} </td>
    <td>开启或关闭音频辅流</td>
    <td>V4.6.10</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#subscribeRemoteSubStreamAudio()} </td>
    <td>订阅远端用户辅流</td>
    <td>V4.6.10</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#muteLocalSubStreamAudio()} </td>
    <td>静音本地音频辅流</td>
    <td>V4.6.10</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setAudioSubscribeOnlyBy()} </td>
    <td>设置本地用户音频只能被房间内其他指定用户订阅</td>
    <td>V4.6.10</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#enableMediaPub()} </td>
    <td>发布或停止发布本地音频</td>
    <td>V4.6.10</td>
  </tr>
</table>

<br>

<table>
  <tr>
    <th width=400><b>事件</b></th>
    <th width=600><b>描述</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcAudioProcessObserver#onAudioHasHowling()} </td>
    <td>检测到啸叫回调。</td>
    <td>V3.9.0</td>
  </tr>
  <tr>
    <td> {@link NERtcAudioFrameObserver#onRecordSubStreamAudioFrame()} </td>
    <td>本地音频辅流数据回调。</td>
    <td>V4.6.10</td>
  </tr>
  <tr>
    <td> {@link NERtcAudioFrameObserver#onPlaybackSubStreamAudioFrameBeforeMixingWithUserID()} </td>
    <td>获取开启音频辅流的远端用户的辅流数据。</td>
    <td>V4.6.10</td>
  </tr>
</table>
 

 ## 视频管理
 
 <table>
  <tr>
    <th width=400><b>方法</b></th>
    <th width=600><b>功能</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtc#enableLocalVideo()} </td>
    <td>开关本地视频</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtc#setCameraCaptureConfig()} </td>
    <td>设置摄像头采集配置。</td>
    <td>V4.5.0</td>
    </tr>
  <tr>
    <td> {@link NERtc#setLocalVideoConfig()} </td>
    <td>设置视频发送配置</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtc#setupLocalVideoCanvas()} </td>
    <td>设置本地用户视图</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtc#setupRemoteVideoCanvas()} </td>
    <td>设置远端用户视图</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtc#startVideoPreview()} </td>
    <td>开启视频预览</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtc#stopVideoPreview()} </td>
    <td>停止视频预览</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#muteLocalVideoStream()} </td>
    <td>开关本地视频发送</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtc#subscribeRemoteVideoStream()} </td>
    <td>订阅 / 取消订阅指定远端用户的视频流</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcVideoView#setScalingType()} </td>
    <td>设置显示模式</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#postOnGLThread()} </td>
    <td>将操作设置到具有GLContext的线程中，如销毁第三方滤镜资源</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#enableSuperResolution()} </td>
    <td>启用或停止 AI 超分</td>
    <td>V4.4.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#enableVideoCorrection()} </td>
    <td>启用或关闭视频图像畸变矫正</td>
    <td>V4.6.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setVideoCorrectionConfig()} </td>
    <td>设置视频图像矫正参数</td>
    <td>V4.6.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#enableVirtualBackground()} </td>
    <td>开启虚拟背景</td>
    <td>V4.6.10</td>
  </tr>
</table>

<h2 id="本地媒体事件">本地媒体事件</h2>

<table>
  <tr>
    <th width=400><b>事件</b></th>
    <th width=600><b>描述</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onFirstAudioFrameDecoded()} </td>
    <td>已解码远端音频首帧的回调</td>
    <td>V3.9.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onFirstVideoFrameDecoded()}  </td>
    <td>已解码远端视频首帧的回调</td>
    <td>V3.9.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onFirstVideoDataReceived()} </td>
    <td>远端视频首帧回调</td>
    <td>V3.9.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onFirstAudioDataReceived()}  </td>
    <td>远端音频首帧回调</td>
    <td>V3.9.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onMediaRightChange()}  </td>
    <td>服务端禁言音视频权限变化回调</td>
    <td>V3.9.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onVirtualBackgroundSourceEnabled()}  </td>
    <td>通知虚拟背景是否成功开启的回调</td>
    <td>V4.6.10</td>
  </tr>
</table>

<h2 id="远端媒体事件">远端媒体事件</h2>

<table>
  <tr>
    <th width=400><b>方法</b></th>
    <th width=600><b>功能</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onUserVideoProfileUpdate()} </td>
    <td>远端用户更改视频分辨率类型的回调</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onUserVideoMute()} </td>
    <td>远端用户关闭视频发送的回调</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onUserAudioMute()} </td>
    <td>远端用户关闭音频发送的回调</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onUserSubStreamAudioMute()} </td>
    <td>远端用户关闭音频辅流发送的回调</td>
    <td>V4.6.10</td>
  </tr>
  <tr>
    <td> {@link NERtcCallback#onUserAudioStart()} </td>
    <td>远端用户开启音频的回调</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onUserSubStreamAudioStart()} </td>
    <td>远端用户开启音频辅流的回调</td>
    <td>V4.6.10</td>
  </tr>
  <tr>
    <td> {@link NERtcCallback#onUserAudioStop()} </td>
    <td>远端用户关闭音频的回调</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onUserSubStreamAudioStop()} </td>
    <td>远端用户关闭音频辅流的回调</td>
    <td>V4.6.10</td>
  </tr>
  <tr>
    <td> {@link NERtcCallback#onUserVideoStart()} </td>
    <td>远端用户开启视频的回调</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallback#onUserVideoStop()} </td>
    <td>远端用户关闭视频的回调</td>
    <td>V3.5.0</td>
  </tr>
</table>

<h2 id="数据统计事件">数据统计事件</h2>

<table>
  <tr>
    <th width=400><b>方法</b></th>
    <th width=600><b>功能</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcStatsObserver#onRtcStats()} </td>
    <td>当前通话统计回调，每2秒触发一次</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcStatsObserver#onNetworkQuality()} </td>
    <td>通话中每个用户的网络上下行质量报告回调</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcStatsObserver#onLocalAudioStats()} </td>
    <td>本地音频流统计信息回调</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcStatsObserver#onLocalVideoStats()} </td>
    <td>本地视频流统计信息回调</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcStatsObserver#onRemoteAudioStats()} </td>
    <td>通话中远端音频流的统计信息回调</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcStatsObserver#onRemoteVideoStats()} </td>
    <td>通话中远端视频流的统计信息回调</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setStatsObserver()} </td>
    <td>设置统计信息回调</td>
    <td>V3.5.0</td>
  </tr>
</table>


## 多房间管理

<table>
  <tr>
    <th width=400><b>方法</b></th>
    <th width=600><b>功能</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#createChannel()} </td>
    <td>创建并获取一个 NERtcChannel 对象。通过创建多个对象，用户可以同时加入多个房间。</td>
    <td>V4.5.0</td>
  </tr>
  <tr>
    <td>{@link channel.NERtcChannel}  </td>
    <td>该类提供在指定房间内实现实时音视频功能的方法。</td>
    <td>V4.5.0</td>
  </tr>
  <tr>
    <td>{@link channel.NERtcChannelCallback} </td>
    <td>该类提供监听指定房间事件和数据的回调。</td>
    <td>V4.5.0</td>
  </tr>
</table>

<h2 id="屏幕共享">屏幕共享</h2>

<table>
  <tr>
    <th width=400><b>方法</b></th>
    <th width=600><b>功能</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#setupLocalSubStreamVideoCanvas()} </td>
    <td>设置本端的辅流视频回放画布</td>
    <td>V3.9.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setupRemoteSubStreamVideoCanvas()} </td>
    <td>设置远端的辅流视频回放画布</td>
    <td>V3.9.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#startScreenCapture()} </td>
    <td>开启屏幕共享</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#stopScreenCapture()} </td>
    <td>停止屏幕共享</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#subscribeRemoteSubStreamVideo()}</td>
    <td>订阅或取消订阅远端的屏幕共享辅流视频，订阅之后才能接收远端的辅流视频数据</td>
    <td>V3.9.0</td>
  </tr>
</table>

<br>

<table>
  <tr>
    <th width=400><b>事件</b></th>
    <th width=600><b>描述</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onUserSubStreamVideoStart()} </td>
    <td>远端用户开启屏幕共享辅流通道的回调</td>
    <td>V3.9.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onUserSubStreamVideoStop()}  </td>
    <td>远端用户停止屏幕共享辅流通道的回调</td>
    <td>V3.9.0</td>
  </tr>
</table>

<h2 id="美颜">美颜</h2>

<table>
  <tr>
    <th width=400><b>方法</b></th>
    <th width=600><b>功能</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#startBeauty()} </td>
    <td>开启美颜功能模块</td>
    <td>V4.6.10</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#stopBeauty()} </td>
    <td>结束美颜功能模块</td>
    <td>V4.6.10</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#enableBeauty()} </td>
    <td>暂停或恢复美颜效果</td>
    <td>V4.6.10</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setBeautyEffect()} </td>
    <td>设置美颜效果</td>
    <td>V4.6.10</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#addBeautyFilter()}</td>
    <td>添加滤镜效果</td>
    <td>V4.6.10</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#removeBeautyFilter()}</td>
    <td>移除滤镜</td>
    <td>V4.6.10</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setBeautyFilterLevel()}</td>
    <td>设置滤镜强度</td>
    <td>V4.6.10</td>
  </tr>
</table>

<h2 id="视频大小流">视频大小流</h2>

<table>
  <tr>
    <th width=400><b>方法</b></th>
    <th width=600><b>功能</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#enableDualStreamMode()} </td>
    <td>设置是否开启视频大小流模式。</td>
    <td>V3.5.0</td>
  </tr>
</table>
 

<h2 id="音视频流回退">音视频流回退</h2>

<table>
  <tr>
    <th width=400><b>方法</b></th>
    <th width=600><b>功能</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#setLocalPublishFallbackOption()} </td>
    <td>设置弱网条件下发布的音视频流回退选项。</td>
    <td>V4.3.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setRemoteSubscribeFallbackOption()} </td>
    <td>设置弱网条件下订阅的音视频流回退选项。</td>
    <td>V4.3.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setLocalMediaPriority()} </td>
    <td>设置本地用户的媒体流优先级。</td>
    <td>V4.2.0</td>
  </tr>
</table>

<table>
  <tr>
    <th width=400><b>事件</b></th>
    <th width=600><b>描述</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onLocalPublishFallbackToAudioOnly()} </td>
    <td>本地发布流已回退为音频流或恢复为音视频流回调。</td>
    <td>V4.3.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onRemoteSubscribeFallbackToAudioOnly()}  </td>
    <td>远端订阅流已回退为音频流或恢复为音视频流回调。</td>
    <td>V4.3.0</td>
  </tr>
</table>

## 通话前网络测试

<table>
  <tr>
    <th width=400><b>方法</b></th>
    <th width=600><b>功能</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#startLastmileProbeTest()}</td>
    <td>开始通话前网络质量探测。</td>
    <td>V4.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#stopLastmileProbeTest()}</td>
    <td>停止通话前网络质量探测。</td>
    <td>V4.5.0</td>
  </tr>
</table>

<table>
  <tr>
    <th width=400><b>事件</b></th>
    <th width=600><b>描述</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onLastmileQuality()} </td>
    <td>报告本地用户的网络质量。</td>
    <td>V4.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onLastmileProbeResult()}  </td>
    <td>报告通话前网络上下行 last mile 质量。</td>
    <td>V4.5.0</td>
  </tr>
</table>

<h2 id="音乐文件播放及混音">音乐文件播放及混音</h2>

<table>
  <tr>
    <th width=400><b>方法</b></th>
    <th width=600><b>功能</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#startAudioMixing()} </td>
    <td>开始播放音乐文件</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#stopAudioMixing()} </td>
    <td>停止播放音乐文件</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#pauseAudioMixing()} </td>
    <td>暂停播放音乐文件</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#resumeAudioMixing()} </td>
    <td>恢复播放音乐文件</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setAudioMixingPlaybackVolume()} </td>
    <td>设置音乐文件播放音量</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setAudioMixingSendVolume()} </td>
    <td>设置音乐文件的发送音量</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#getAudioMixingPlaybackVolume()} </td>
    <td>获取音乐文件的播放音量</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#getAudioMixingSendVolume()} </td>
    <td>获取音乐文件的发送音量</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#getAudioMixingDuration()} </td>
    <td>获取音乐文件的总长度</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setAudioMixingPosition()} </td>
    <td>获取音乐文件的播放进度</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#getAudioMixingCurrentPosition()} </td>
    <td>设置音乐文件的播放进度</td>
    <td>V3.5.0</td>
  </tr>
</table>

<table>
  <tr>
    <th width=400><b>事件</b></th>
    <th width=600><b>描述</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onAudioMixingStateChanged()} </td>
    <td>本地用户的音乐文件播放状态改变回调</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onAudioMixingTimestampUpdate()} </td>
    <td>本地用户的音乐文件播放进度回调</td>
    <td>V3.5.0</td>
  </tr>
</table>


<h2 id="音效文件播放管理">音效文件播放管理</h2>

<table>
  <tr>
    <th width=400><b>方法</b></th>
    <th width=600><b>功能</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#getEffectPlaybackVolume()} </td>
    <td>获取音效文件播放音量。</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setEffectPlaybackVolume()} </td>
    <td>设置音效文件播放音量</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#playEffect()} </td>
    <td>播放指定音效文件</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#stopEffect()} </td>
    <td>停止播放指定音效文件</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#stopAllEffects()} </td>
    <td>停止播放所有音效文件</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#pauseEffect()} </td>
    <td>暂停音效文件播放</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#pauseAllEffects()} </td>
    <td>暂停所有音效文件播放</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#resumeEffect()} </td>
    <td>恢复播放指定音效文件</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#resumeAllEffects()} </td>
    <td>恢复播放所有音效文件</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setEffectSendVolume()} </td>
    <td>调节音效文件发送音量</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#getEffectSendVolume()} </td>
    <td>获取音效文件发送音量</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#getEffectDuration()} </td>
    <td>获取音效文件时长。</td>
    <td>V4.4.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#getEffectCurrentPosition()} </td>
    <td>获取音效的播放进度。</td>
    <td>V4.4.0</td>
  </tr>
</table>

<table>
  <tr>
    <th width=400><b>事件</b></th>
    <th width=600><b>描述</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onAudioEffectFinished()} </td>
    <td>本地音效文件播放已结束回调</td>
    <td>V3.5.0</td>
  </tr>
</table>

<h2 id="变声与混响">变声与混响</h2>

<table>
  <tr>
    <th width=400><b>方法</b></th>
    <th width=600><b>功能</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#setAudioEffectPreset()}</td>
    <td>设置 SDK 预设的人声的变声音效。</td>
    <td>4.1.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setVoiceBeautifierPreset()}</td>
    <td>设置 SDK 预设的美声效果。</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setLocalVoiceEqualization()}</td>
    <td>设置本地语音音效均衡，即自定义设置本地人声均衡波段的中心频率。</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setLocalVoicePitch()}</td>
    <td>设置本地语音音调。</td>
    <td>4.1.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setLocalVoiceReverbParam()}</td>
    <td>开启本地语音混响效果。</td>
    <td>4.6.10</td>
  </tr>
</table>

<h2 id="媒体增强信息">媒体增强信息</h2>

<table>
  <tr>
    <th width=400><b>方法</b></th>
    <th width=600><b>功能</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#sendSEIMsg()} </td>
    <td>通过主流通道发送媒体补充增强信息。	</td>
    <td>V4.0.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#sendSEIMsg()} </td>
    <td>发送媒体补充增强信息。通过本接口可指定发送 SEI 时使用主流或辅流通道。	</td>
    <td>V4.0.0</td>
  </tr>
</table>

<br>

<table>
  <tr>
    <th width=400><b>事件</b></th>
    <th width=600><b>描述</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onRecvSEIMsg()} </td>
    <td>收到远端流的 SEI 内容回调。</td>
    <td>V4.0.0</td>
  </tr>
</table>

<h2 id="旁路推流">旁路推流</h2>

注意：该组方法仅适用于互动直播 2.0。

<table>
  <tr>
    <th width=400><b>方法</b></th>
    <th width=600><b>功能</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#addLiveStreamTask()} </td>
    <td>添加房间推流任务</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#updateLiveStreamTask()} </td>
    <td>更新修改房间推流任务</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#removeLiveStreamTask()} </td>
    <td>删除房间推流任务</td>
    <td>V3.5.0</td>
  </tr>
</table>

<br>

<table>
  <tr>
    <th width=400><b>事件</b></th>
    <th width=600><b>描述</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onLiveStreamState()} </td>
    <td>直播推流状态回调</td>
    <td>V3.5.0</td>
  </tr>
 </table>

 <h2 id="跨房间流媒体转发">跨房间流媒体转发</h2>

<table>
  <tr>
    <th width=400><b>方法</b></th>
    <th width=600><b>功能</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#startChannelMediaRelay()} </td>
    <td>开始跨房间媒体流转发。</td>
    <td>V4.2.1</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#updateChannelMediaRelay()} </td>
    <td>更新媒体流转发的目标房间。</td>
    <td>V4.2.1</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#stopChannelMediaRelay()} </td>
    <td>停止跨房间媒体流转发。</td>
    <td>V4.2.1</td>
  </tr>
</table>

<table>
  <tr>
    <th width=400><b>事件</b></th>
    <th width=600><b>描述</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onMediaRelayStatesChange()} </td>
    <td>跨房间媒体流转发状态发生改变回调。</td>
    <td>V4.2.1</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onMediaRelayReceiveEvent()} </td>
    <td> 媒体流相关转发事件回调。</td>
    <td>V4.2.1</td>
  </tr>
</table>

 <h2 id="音量提示">音量提示</h2>

<table>
  <tr>
    <th width=400><b>方法</b></th>
    <th width=600><b>功能</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#enableAudioVolumeIndication(boolean enable,int interval)} </td>
    <td>启用说话者音量提示</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#enableAudioVolumeIndication(boolean enable,int interval,boolean enableVad)} </td>
    <td>启用说话者音量及本地是否有人声提示</td>
    <td>V4.6.10</td>
  </tr>
</table>

<table>
  <tr>
    <th width=400><b>事件</b></th>
    <th width=600><b>描述</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onRemoteAudioVolumeIndication()} </td>
    <td>提示房间内谁正在说话及说话者音量的回调</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onLocalAudioVolumeIndication(int volume)} </td>
    <td>提示房间内本地用户瞬时音量的回调</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onLocalAudioVolumeIndication(int volume, boolean vadFlag)} </td>
    <td>提示房间内本地用户瞬时音量及是否存在人声的回调</td>
    <td>V4.6.10</td>
  </tr>
</table>

<h2 id="耳返">耳返</h2>

<table>
  <tr>
    <th width=400><b>方法</b></th>
    <th width=600><b>功能</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#enableEarback()} </td>
    <td>开启耳返功能</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setEarbackVolume()} </td>
    <td>设置耳返音量</td>
    <td>V3.5.0</td>
  </tr>
</table>

<h2 id="音频播放路由">音频播放路由</h2>

<table>
  <tr>
    <th width=400><b>方法</b></th>
    <th width=600><b>功能</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#setSpeakerphoneOn()} </td>
    <td>设置扬声器是否开启</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#isSpeakerphoneOn()} </td>
    <td>获取扬声器是否开启</td>
    <td>V3.5.0</td>
  </tr>
</table>

<h2 id="视频大小流">视频大小流</h2>

<table>
  <tr>
    <th width=400><b>方法</b></th>
    <th width=600><b>功能</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#enableDualStreamMode()} </td>
    <td>设置是否开启视频大小流模式。</td>
    <td>V3.7.0</td>
  </tr>
</table>

<h2 id="自定义音频采集与渲染">自定义音频采集与渲染</h2>

<table>
  <tr>
    <th width=400><b>方法</b></th>
    <th width=600><b>功能</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#setExternalAudioSource()} </td>
    <td>启用外部自定义音频数据主流输入功能，并设置采集参数。</td>
    <td>V3.9.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setExternalSubStreamAudioSource()} </td>
    <td>启用外部自定义音频数据辅流输入功能，并设置采集参数。</td>
    <td>V4.6.10</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#pushExternalAudioFrame()} </td>
    <td>将外部音频主流数据帧推送给内部引擎。</td>
    <td>V3.9.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#pushExternalSubStreamAudioFrame()} </td>
    <td>将外部音频辅流数据帧推送给内部引擎。</td>
    <td>V4.6.10</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setExternalAudioRender()} </td>
    <td>设置外部音频渲染。</td>
    <td>V4.0.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#pullExternalAudioFrame()} </td>
    <td>拉取外部音频数据。</td>
    <td>V4.0.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setStreamAlignmentProperty()} </td>
    <td>对齐本地系统时间与服务端时间。</td>
    <td>V4.6.10</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#getNtpTimeOffset()} </td>
    <td>获取本地系统时间与服务端时间的差值。</td>
    <td>V4.6.10</td>
  </tr>
</table>



 
 <h2 id="自定义视频采集">自定义视频采集</h2>

<table>
  <tr>
    <th width=400><b>方法</b></th>
    <th width=600><b>功能</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#setExternalVideoSource()} </td>
    <td>配置外部视频源</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#pushExternalVideoFrame()} </td>
    <td>推送外部视频帧</td>
    <td>V3.5.0</td>
  </tr>
</table>

<h2 id="原始音频数据">原始音频数据</h2>

<table>
  <tr>
    <th width=400><b>方法</b></th>
    <th width=600><b>功能</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#setRecordingAudioFrameParameters()}</td>
    <td>设置录制的声音格式</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setPlaybackAudioFrameParameters()} </td>
    <td>设置播放的声音格式</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setAudioFrameObserver()}</td>
    <td>注册语音观测器对象</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setMixedAudioFrameParameters()}</td>
    <td>设置采集和播放后的混合后的采样率。需要在加入房间之前调用该接口</td>
    <td>V3.9.0</td>
  </tr>
</table>

<table>
  <tr>
    <th width=400><b>事件</b></th>
    <th width=600><b>描述</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcAudioFrameObserver#onRecordFrame()}</td>
    <td>采集音频数据回调。</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcAudioFrameObserver#onPlaybackFrame()}</td>
    <td>播放音频数据回调。</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcAudioFrameObserver#onMixedAudioFrame()}</td>
    <td>音频采集与播放混合后数据帧回调</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcAudioFrameObserver#onPlaybackAudioFrameBeforeMixingWithUserID(long, NERtcAudioFrame)} </td>
    <td>某一远端用户的原始音频帧回调。<br>该接口已废弃，请改用 {@link NERtcAudioFrameObserver#onPlaybackAudioFrameBeforeMixingWithUserID(long, NERtcAudioFrame, long)}。</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcAudioFrameObserver#onPlaybackAudioFrameBeforeMixingWithUserID(long, NERtcAudioFrame, long)} </td>
    <td>某一远端用户的原始音频帧回调。</td>
    <td>V4.5.0</td>
  </tr>
</table>

 <h2 id="原始视频数据">原始视频数据</h2>

<table>
  <tr>
    <th width=400><b>事件</b></th>
    <th width=600><b>描述</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#setVideoCallback()}</td>
    <td>采集视频数据回调。</td>
    <td>V3.5.0</td>
  </tr>
</table>

<table>
  <tr>
    <th width=400><b>事件</b></th>
    <th width=600><b>描述</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcVideoCallback#onVideoCallback()}</td>
    <td>本地视频数据采集回调</td>
    <td>V3.5.0</td>
  </tr>
</table>

<h2 id="截图">截图</h2>

<table>
  <tr>
    <th width=400><b>方法</b></th>
    <th width=600><b>功能</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#takeLocalSnapshot()}</td>
    <td>本地视频画面截图。</td>
    <td>V4.2.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#takeRemoteSnapshot()}</td>
    <td>远端视频画面截图。</td>
    <td>V4.2.0</td>
  </tr>
</table>

<table>
  <tr>
    <th width=400><b>事件</b></th>
    <th width=600><b>描述</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcTakeSnapshotCallback#onTakeSnapshotResult()}</td>
    <td>截图结果回调。</td>
    <td>V4.2.0</td>
  </tr>
</table>

<h2 id="水印">水印</h2>

<table>
  <tr>
    <th width=400><b>方法</b></th>
    <th width=600><b>功能</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#setLocalCanvasWatermarkConfigs()}</td>
    <td>（此接口已废弃）添加本地视频画布水印。</td>
    <td>V4.2.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setLocalVideoWatermarkConfigs()}</td>
    <td>添加本地视频水印。</td>
    <td>V4.6.10</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setRemoteCanvasWatermarkConfigs()}</td>
    <td>（此接口已废弃）添加远端视频画布水印。</td>
    <td>V4.2.0</td>
  </tr>
</table>

<br>

<table>
  <tr>
    <th width=400><b>事件</b></th>
    <th width=600><b>描述</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onLocalVideoWatermarkState()}</td>
    <td>水印结果回调。</td>
    <td>V4.6.10</td>
  </tr>
</table>

<h2 id="加密">加密</h2>

<table>
  <tr>
    <th width=400><b>方法</b></th>
    <th width=600><b>功能</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#enableEncryption()}</td>
    <td>开启或关闭媒体流加密。</td>
    <td>V4.4.0</td>
  </tr>
</table>

<h2 id="客户端音频录制">客户端音频录制</h2>

<table>
  <tr>
    <th width=400><b>方法</b></th>
    <th width=600><b>功能</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#startAudioRecording()}</td>
    <td>开始客户端录音。</td>
    <td>V4.2.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#startAudioRecordingWithConfig()}</td>
    <td>开始客户端录音。</td>
    <td>V4.6.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#stopAudioRecording()}</td>
    <td>停止客户端录音。</td>
    <td>V4.2.0</td>
  </tr>
</table>

<table>
  <tr>
    <th width=400><b>事件</b></th>
    <th width=600><b>描述</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onAudioRecording()}</td>
    <td>音频录制状态回调。</td>
    <td>V4.2.0</td>
  </tr>
</table>

<h2 id="云代理">云代理</h2>

<table>
  <tr>
    <th width=400><b>方法</b></th>
    <th width=600><b>功能</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#setCloudProxy()}</td>
    <td>开启并设置云代理服务。</td>
    <td>V4.6.0</td>
  </tr>
</table>

 <h2 id="设备管理">设备管理</h2>

<table>
  <tr>
    <th width=400><b>方法</b></th>
    <th width=600><b>功能</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtc#checkPermission()} </td>
    <td>设备权限检查，比如麦克风、摄像头权限</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#switchCamera()} </td>
    <td>切换前置/后置摄像头</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#switchCameraWithPosition()} </td>
    <td>指定前置/后置摄像头</td>
    <td>V4.6.10</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#getCurrentCamera()} </td>
    <td>查看当前摄像头方向。</td>
    <td>V4.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#isCameraZoomSupported()} </td>
    <td>检测设备是否支持摄像头缩放功能</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setCameraZoomFactor()} </td>
    <td>设置摄像头缩放比例</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#getCameraCurrentZoom()} </td>
    <td>获取当前缩放比例</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#getCameraMaxZoom()} </td>
    <td>获取摄像头支持的最大视频缩放比例</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#isCameraTorchSupported()} </td>
    <td>检测设备是否支持闪光灯常开</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#isCameraFocusSupported()} </td>
    <td>检测设备是否支持手动对焦功能</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setCameraFocusPosition()} </td>
    <td>设置当前摄像头聚焦点位置</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#isCameraExposurePositionSupported()} </td>
    <td>检测设备是否支持手动曝光功能</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setCameraExposurePosition()} </td>
    <td>设置手动曝光位置</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setCameraTorchOn()} </td>
    <td>设置是否打开闪光灯</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setPlayoutDeviceMute()} </td>
    <td>设置音频播放设备的状态</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#isPlayoutDeviceMute()} </td>
    <td>获取当前音频播放设备是否静音</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setRecordDeviceMute()} </td>
    <td>设置录音设备的状态</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#isRecordDeviceMute()} </td>
    <td>获取当前音频采集设备是否静音</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtc#version()} </td>
    <td> NERtc SDK版本号</td>
    <td>V3.5.0</td>
  </tr>
</table>

<br>

<table>
  <tr>
    <th width=400><b>方法</b></th>
    <th width=600><b>功能</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onAudioDeviceChanged()} </td>
    <td>音频设备状态更改回调</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onAudioDeviceStateChange()} </td>
    <td>音频设备状态切换回调</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onVideoDeviceStageChange()} </td>
    <td>视频设备状态切换回调</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onCameraFocusChanged()} </td>
    <td>摄像头对焦区域已改变回调</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onCameraExposureChanged()} </td>
    <td>摄像头曝光区域已改变回调</td>
    <td>V3.5.0</td>
  </tr>
</table>

 <h2 id="故障排查">故障排查</h2>

<table>
  <tr>
    <th width=400><b>方法</b></th>
    <th width=600><b>功能</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#uploadSdkInfo()} </td>
    <td>上传SDK日志信息</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#startAudioDump()} </td>
    <td>开始记录音频 dump</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#stopAudioDump()} </td>
    <td>结束记录音频 dump</td>
    <td>V3.5.0</td>
  </tr>
</table>

<br>

 <table>
  <tr>
    <th width=400><b>事件</b></th>
    <th width=600><b>描述</b></th>
    <th width=200><b>起始版本</b></th>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onWarning()} </td>
    <td>发生警告回调</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onError()} </td>
    <td>引擎发生了运行时的错误，需要用户干预</td>
    <td>V3.5.0</td>
  </tr>
</table>

*/

