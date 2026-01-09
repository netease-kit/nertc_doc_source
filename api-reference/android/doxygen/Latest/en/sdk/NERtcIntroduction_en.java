/*
 * Copyright (c) 2021 CommsEase, Inc.  All rights reserved.
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
 * @mainpage Introduction
 * @brief<p> CommsEase NERTC SDK
 * provides a comprehensive Real-time Communication (RTC) development platform that allows developers to implement Internet-based peer-to-peer audio and video calls, and group audio and video conferencing. The SDK enables users to manage audio and video devices and switch audio and video modes during calls. The SDK also implements capturing video data callbacks and offers additional features, such as personalized image enhancement. </p>
 * - NERtc interface classes that contain main methods invoked by applications.
 * - NERtcEx interface classes that contain extension methods invoked by applications
 * - NERtcCallback classes that contain callbacks that return user states to applications using notifications
 * - NERtcCallbackEx classes that contain callbacks that send notifications for audio and video information.
 * - {@link channel.NERtcChannel} classes used to implement audio and video calling in a specified room. Users can join multiple rooms by creating multiple NERtcChannel objects.
 * - {@link channel.NERtcChannelCallback} used to listen for and report events and data for a specified room.
 * 
 * 
 * ## Error codes
 * 
 * The SDK may return error codes or status codes when your app calls an API. You can learn about the status of the SDK or specific tasks based on the information provided by the error codes or status codes. If unknown errors are returned, you can contact our technical support for help.
 * 
 * The error codes of the current SDK APIs are as follows:
 * - Common error codes: {@link NERtcConstants.ErrorCode}
 * - Error codes for mixing audio operations: {@link NERtcConstants.AudioMixingError}
 * - Error codes for publishing audio and video streams in live streaming: {@link NERtcConstants.LiveStreamState}
 * - Status codes for client recording: {@link NERtcConstants.AudioRecordingCode}
 * - Video watermark status codes: {@link NERtcConstants.NERtcLocalVideoWatermarkState}
 * - Other error codes {@link NERtcConstants.RuntimeError}

 <h2 id="Room management">Room management</h2>

 <table>
  <tr>
    <th width= 400><b>Method</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtc#getInstance()}</td>
    <td> Creates an RTC engine object. </td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtc#init()}</td>
    <td> Initializes an RTC engine object. </td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtc#release()} </td>
    <td> Destroys an NERtc instance and releases resources. </td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setChannelProfile()}</td>
    <td>Sets a room scene. </td>
    <td>V3.6.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setClientRole()}</td>
    <td>Sets the role of a user.</td> </td>
    <td>V3.9.0</td>
  </tr>
  <tr>
    <td> {@link NERtc#joinChannel()}</td>
    <td>Joins a channel.</td> </td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#switchChannel()}</td>
    <td>Switches to another room in live streaming.</td> </td>
    <td>V4.1.0</td>
  </tr>
  <tr>
    <td> {@link NERtc#leaveChannel()}</td>
    <td> Leaves a channel. </td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtc#setParameters()}</td>
    <td>Sets complex parameters. To set relevant parameters, you must call this method before calling the init method. </td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#getConnectionState()}</td>
    <td>Gets the connection state of a channel. </td>
    <td>V3.5.0</td>
  </tr>
 </table>

 ## Room event
 
 <table>
  <tr>
    <th width= 400><b>Event</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcCallback#onClientRoleChange()} </td>
    <td>Occurs when a user changes the role in live streaming. </td>
    <td>V3.9.0</td>
  </tr>
  <tr>
    <td>  {@link NERtcCallback#onJoinChannel()} </td>
    <td> Occurs when a user joins a channel. </td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onReJoinChannel()} </td>
    <td> Occurs when a user rejoins a room. </td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallback#onLeaveChannel()} </td>
    <td> Occurs when a user leaves a room. </td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onReconnectingStart()} </td>
    <td>Occurs when reconnection starts. </td>
    <td>V3.7.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallback#onUserJoined()} </td>
    <td> Occurs when a remote user joins the current room. </td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallback#onUserLeave()} </td>
    <td> Occurs when a remote user leaves a room. </td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallback#onDisconnect()} </td>
    <td> Occurs when the server becomes disconnected. callback. </td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onConnectionTypeChanged()} </td>
    <td> Occurs when the type of the local network changes.
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onConnectionStateChanged()}  </td>
    <td>Occurs when the connection state changes</td>
    <td>V3.8.0</td>
  </tr>
</table>

 ## Audio management

 <table>
  <tr>
    <th width= 400><b>Method</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtc#setAudioProfile()} </td>
    <td> Sets the audio profile.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#adjustRecordingSignalVolume()} </td>
    <td>Adjusts the recording volume.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#adjustPlaybackSignalVolume()} </td>
    <td>Adjusts the playback volume.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#adjustUserPlaybackSignalVolume()} </td>
    <td> Adjusts the local playback volume of the stream from a specified remote user.</td>
    <td>V4.2.1</td>
  </tr>
  <tr>
    <td> {@link NERtc#enableLocalAudio()} </td>
    <td> Stops or resumes local audio capture.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#muteLocalAudioStream()} </td>
    <td> Mutes or unmutes local audio.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtc#subscribeRemoteAudioStream()} </td>
    <td> Subscribes to or unsubscribes from all remote audio streams.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtc#subscribeAllRemoteAudioStreams()} </td>
    <td> Subscribes to or unsubscribes from all remote audio streams.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setAudioProcessObserver()} </td>
    <td>Registers the audio processing observer and set the audio processing callback</td>
    <td>V3.9.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setRemoteHighPriorityAudioStream()} </td>
    <td> Sets high priority to a remote audio stream.</td>
    <td>V4.6.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#enableLoopbackRecording()} </td>
    <td> Enables or disables audio sharing.</td>
    <td>V4.6.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#adjustLoopBackRecordingSignalVolume()} </td>
    <td> Adjusts audio sharing volume</td>
    <td>V4.6.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#enableLocalSubStreamAudio()} </td>
    <td> Enables or disables the audio substream.</td>
    <td>V4.6.10</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#subscribeRemoteSubStreamAudio()} </td>
    <td> Subscribes to the audio substream from a specified remote user.</td>
    <td>V4.6.10</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#muteLocalSubStreamAudio()} </td>
    <td> Mutes the local audio substream.</td>
    <td>V4.6.10</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setAudioSubscribeOnlyBy()} </td>
    <td>Allows the local audio stream to be subscribed only by specified users in the room.</td>
    <td>V4.6.10</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#enableMediaPub()} </td>
    <td> Publishes or unpublishes the local audio stream.</td>
    <td>V4.6.10</td>
  </tr>
</table>

<br>

<table>
  <tr>
    <th width= 400><b>Event</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcAudioProcessObserver#onAudioHasHowling()} </td>
    <td> Occurs when howling is detected. </td>
    <td>V3.9.0</td>
  </tr>
  <tr>
    <td> {@link NERtcAudioFrameObserver#onRecordSubStreamAudioFrame()} </td>
    <td> Returns the audio substream data. </td>
    <td>V4.6.10</td>
  </tr>
  <tr>
    <td> {@link NERtcAudioFrameObserver#onPlaybackSubStreamAudioFrameBeforeMixingWithUserID()} </td>
    <td>Gets the substream data of a remote user publishing the audio substream. </td>
    <td>V4.6.10</td>
  </tr>
</table>
 

 ## Video management
 
 <table>
  <tr>
    <th width= 400><b>Method</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtc#enableLocalVideo()} </td>
    <td> Enables or disables the local video stream.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtc#setCameraCaptureConfig()} </td>
    <td>Sets the configuration for capturing the camera data. </td>
    <td>V4.5.0</td>
    </tr>
  <tr>
    <td> {@link NERtc#setLocalVideoConfig()} </td>
    <td> Sets the local video configuration.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtc#setupLocalVideoCanvas()} </td>
    <td>Sets the local video canvas.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtc#setupRemoteVideoCanvas()} </td>
    <td> Sets the remote video canvas. </td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtc#startVideoPreview()} </td>
    <td> Starts video preview.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtc#stopVideoPreview()} </td>
    <td> Stops video preview</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#muteLocalVideoStream()} </td>
    <td> Mutes or unmutes local video stream.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtc#subscribeRemoteVideoStream()} </td>
    <td> Subscribes to or unsubscribes from video streams from specified remote users.
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcVideoView#setScalingType()} </td>
    <td> Sets the rendering mode.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#postOnGLThread()} </td>
    <td> Integrates a specific operation in a thread that contains GLContext. For example, destroying third-party filter assets.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#enableSuperResolution()} </td>
    <td> Enables or disables AI-powered super resolution.</td>
    <td>V4.4.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#enableVideoCorrection()} </td>
    <td>Enables or disables video correction.</td>
    <td>V4.6.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setVideoCorrectionConfig()} </td>
    <td>Sets the parameters of video correction configuration.
    <td>V4.6.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#enableVirtualBackground()} </td>
    <td> Enables the virtual background.</td>
    <td>V4.6.10</td>
  </tr>
</table>

<h2 id="Local media events">Local media events</h2>

<table>
  <tr>
    <th width= 400><b>Event</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onFirstAudioFrameDecoded()} </td>
    <td> Occurs when the first audio frame from a remote user is decoded.</td>
    <td>V3.9.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onFirstVideoFrameDecoded()}  </td>
    <td> Occurs when the first video frame from a remote user is decoded.</td>
    <td>V3.9.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onFirstVideoDataReceived()} </td>
    <td> Occurs when the first video frame from a remote user is received.</td>
    <td>V3.9.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onFirstAudioDataReceived()}  </td>
    <td> Occurs when the first audio frame from a remote user is received.</td>
    <td>V3.9.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onMediaRightChange()}  </td>
    <td>Occurs when audio and video permissions are revoked on the server side.</td>
    <td>V3.9.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onVirtualBackgroundSourceEnabled()}  </td>
    <td>Callback that returns the result of enabling the virtual background</td>
    <td>V4.6.10</td>
  </tr>
</table>

<h2 id="Remote media event">Remote media event</h2>

<table>
  <tr>
    <th width= 400><b>Method</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onUserVideoProfileUpdate()} </td>
    <td> Occurs when a remote user changes the video resolution type.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onUserVideoMute()} </td>
    <td> Occurs when a remote user closes the video.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onUserAudioMute()} </td>
    <td> Occurs when a remote user mutes the audio.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onUserSubStreamAudioMute()} </td>
    <td> Occurs when a remote user unpublishes the audio substream.</td>
    <td>V4.6.10</td>
  </tr>
  <tr>
    <td> {@link NERtcCallback#onUserAudioStart()} </td>
    <td> Occurs when a remote user enables the audio.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onUserSubStreamAudioStart()} </td>
    <td> Occurs when a remote user publishes the audio substream.</td>
    <td>V4.6.10</td>
  </tr>
  <tr>
    <td> {@link NERtcCallback#onUserAudioStop()} </td>
    <td> Occurs when a remote user stops the audio stream.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onUserSubStreamAudioStop()} </td>
    <td> Occurs when a remote user unpublishes the audio substream.</td>
    <td>V4.6.10</td>
  </tr>
  <tr>
    <td> {@link NERtcCallback#onUserVideoStart()} </td>
    <td> Occurs when a remote user enables the video.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallback#onUserVideoStop()} </td>
    <td> Occurs when a remote user stops sending the video stream.</td>
    <td>V3.5.0</td>
  </tr>
</table>

<h2 id="Stats event">Stats event</h2>

<table>
  <tr>
    <th width= 400><b>Method</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcStatsObserver#onRtcStats()} </td>
    <td> Occurs when the stats for the current call is collected. The callback is triggered every 2 seconds.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcStatsObserver#onNetworkQuality()} </td>
    <td> Occurs when the stats of uplink and downlink network quality for each user are reported during the call</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcStatsObserver#onLocalAudioStats()} </td>
    <td> Occurs when the stats of the local audio stream are collected.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcStatsObserver#onLocalVideoStats()} </td>
    <td> Occurs when the stats of the local video stream are collected.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcStatsObserver#onRemoteAudioStats()} </td>
    <td> Occurs when the stats of the remote audio stream in the call are collected.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcStatsObserver#onRemoteVideoStats()} </td>
    <td> Occurs when the stats of the video stream in the call are collected.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setStatsObserver()} </td>
    <td> Gets notified when the stats are collected.</td>
    <td>V3.5.0</td>
  </tr>
</table>


## Multiple room management

<table>
  <tr>
    <th width= 400><b>Method</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#createChannel()} </td>
    <td>Creates and gets an NERtcChannel object. Users can join multiple rooms by creating multiple NERtcChannel objects. </td>
    <td>V4.5.0</td>
  </tr>
  <tr>
    <td>{@link channel.NERtcChannel}  </td>
    <td>The classes provide methods to implement audio and video calling in a specified room. </td>
    <td>V4.5.0</td>
  </tr>
  <tr>
    <td>{@link channel.NERtcChannelCallback} </td>
    <td>The classes provide callbacks for listening to events and returning data in a specified room. </td>
    <td>V4.5.0</td>
  </tr>
</table>

<h2 id="Screen sharing">Screen sharing</h2>

<table>
  <tr>
    <th width= 400><b>Method</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#setupLocalSubStreamVideoCanvas()} </td>
    <td> Sets a playback canvas for local video substream.</td>
    <td>V3.9.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setupRemoteSubStreamVideoCanvas()} </td>
    <td> Sets a playback canvas for remote video substream.</td>
    <td>V3.9.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#startScreenCapture()} </td>
    <td> Starts screen sharing.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#stopScreenCapture()} </td>
    <td> Stops screen sharing</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#subscribeRemoteSubStreamVideo()}</td>
    <td> Subscribes to or unsubscribes from the remote video substream for screen sharing. You can receive the video substream data only after you subscribe to the remote video substream. </td>
    <td>V3.9.0</td>
  </tr>
</table>

<br>

<table>
  <tr>
    <th width= 400><b>Event</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onUserSubStreamVideoStart()} </td>
    <td> Occurs when a remote user starts screen sharing through the substream.</td>
    <td>V3.9.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onUserSubStreamVideoStop()}  </td>
    <td> Occurs when a remote user stops screen sharing through the substream.</td>
    <td>V3.9.0</td>
  </tr>
</table>

<h2 id="Beauty">Beauty</h2>

<table>
  <tr>
    <th width= 400><b>Method</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#startBeauty()} </td>
    <td> Enables the beauty module.</td>
    <td>V4.6.10</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#stopBeauty()} </td>
    <td>Stops the beauty module.</td>
    <td>V4.6.10</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#enableBeauty()} </td>
    <td>Pauses or resumes beauty effects</td>
    <td>V4.6.10</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setBeautyEffect()} </td>
    <td>Sets a beauty effect.</td>
    <td>V4.6.10</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#addBeautyFilter()}</td>
    <td>Adds a filter effect</td>
    <td>V4.6.10</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#removeBeautyFilter()}</td>
    <td>Removes a filter</td>
    <td>V4.6.10</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setBeautyFilterLevel()}</td>
    <td>Sets the filter intensity</td>
    <td>V4.6.10</td>
  </tr>
</table>

<h2 id="Video dual stream mode">Video dual stream mode</h2>

<table>
  <tr>
    <th width= 400><b>Method</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#enableDualStreamMode()} </td>
    <td> Enables or disables the video dual stream mode. </td>
    <td>V3.5.0</td>
  </tr>
</table>
 

<h2 id="Audio and video stream fallback">Audio and video stream fallback</h2>

<table>
  <tr>
    <th width= 400><b>Method</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#setLocalPublishFallbackOption()} </td>
    <td> Sets the fallback option for the published local video stream for unreliable network conditions. </td>
    <td>V4.3.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setRemoteSubscribeFallbackOption()} </td>
    <td> Sets the fallback option for the subscribed remote audio and video stream for unreliable network conditions. </td>
    <td>V4.3.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setLocalMediaPriority()} </td>
    <td> Sets the priority of local media streams. </td>
    <td>V4.2.0</td>
  </tr>
</table>

<table>
  <tr>
    <th width= 400><b>Event</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onLocalPublishFallbackToAudioOnly()} </td>
    <td> Occurs when the published local media stream falls back to an audio-only stream or switches back to an audio and video stream. </td>
    <td>V4.3.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onRemoteSubscribeFallbackToAudioOnly()}  </td>
    <td> Occurs when the subscribed remote media stream falls back to an audio-only stream or switches back to an audio and video stream. </td>
    <td>V4.3.0</td>
  </tr>
</table>

## Network probe testing before calling

<table>
  <tr>
    <th width= 400><b>Method</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#startLastmileProbeTest()}</td>
    <Td>Performs a probe test before starting a call. </td>
    <td>V4.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#stopLastmileProbeTest()}</td>
    <td>Performs a probe test before stopping a call. </td>
    <td>V4.5.0</td>
  </tr>
</table>

<table>
  <tr>
    <th width= 400><b>Event</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onLastmileQuality()} </td>
    <td>Reports the network quality of the current user. </td>
    <td>V4.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onLastmileProbeResult()}  </td>
    <td>Reports the upstream and downstream network quality of the last mile before starting a call. </td>
    <td>V4.5.0</td>
  </tr>
</table>

<h2 id="Music file playback and mixing">Music file playback and mixing</h2>

<table>
  <tr>
    <th width= 400><b>Method</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#startAudioMixing()} </td>
    <td> Starts to play a music file.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#stopAudioMixing()} </td>
    <td> Stops playing a music file.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#pauseAudioMixing()} </td>
    <td> Pauses playing a music file.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#resumeAudioMixing()} </td>
    <td> Resumes playing a music file.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setAudioMixingPlaybackVolume()} </td>
    <td> Sets the playback volume of a music file.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setAudioMixingSendVolume()} </td>
    <td> Sets the publishing volume of a music file.
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#getAudioMixingPlaybackVolume()} </td>
    <td> Gets the playback volume of a music file.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#getAudioMixingSendVolume()} </td>
    <td>Get the publishing volume of a music file.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#getAudioMixingDuration()} </td>
    <td> Gets the total duration of a music file</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setAudioMixingPosition()} </td>
    <td> Sets the playback position of a music file.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#getAudioMixingCurrentPosition()} </td>
    <td> Gets the current playback position of a music file.</td>
    <td>V3.5.0</td>
  </tr>
</table>

<table>
  <tr>
    <th width= 400><b>Event</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onAudioMixingStateChanged()} </td>
    <td> Occurs when the playback status of a local music file changes.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onAudioMixingTimestampUpdate()} </td>
    <td> Occurs when the timestamp of a music file is updated.</td>
    <td>V3.5.0</td>
  </tr>
</table>


<h2 id="Audio effect file playback management">Audio effect file playback management</h2>

<table>
  <tr>
    <th width= 400><b>Method</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#getEffectPlaybackVolume()} </td>
    <td> Gets the playback volume of an audio effect file. </td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setEffectPlaybackVolume()} </td>
    <td> Sets the playback volume of an audio effect file.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#playEffect()} </td>
    <td> Plays back a specified audio effect file</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#stopEffect()} </td>
    <td> Stops playing a specified audio effect file.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#stopAllEffects()} </td>
    <td> Stops playing all audio effect files.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#pauseEffect()} </td>
    <td> Pauses the playback of an audio effect file.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#pauseAllEffects()} </td>
    <td> Pauses all audio file playback</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#resumeEffect()} </td>
    <td> Resumes playing back a specified audio effect file.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#resumeAllEffects()} </td>
    <td> Resumes playing back all audio effect files.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setEffectSendVolume()} </td>
    <td> Adjusts the publishing volume of a effect file.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#getEffectSendVolume()} </td>
    <td>Gets the publishing volume of an effect file.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#getEffectDuration()} </td>
    <td> Gets the duration of of an audio effect file. </td>
    <td>V4.4.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#getEffectCurrentPosition()} </td>
    <td> Gets the playback position of an audio effect file. </td>
    <td>V4.4.0</td>
  </tr>
</table>

<table>
  <tr>
    <th width= 400><b>Event</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onAudioEffectFinished()} </td>
    <td> Occurs when the playback of an audio effect file ends</td>
    <td>V3.5.0</td>
  </tr>
</table>

<h2 id="Voice change and reverberation">Voice change and reverberation</h2>

<table>
  <tr>
    <th width= 400><b>Method</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#setAudioEffectPreset()}</td>
    <td> Sets a voice change effect preset by the SDK. </td>
    <td>4.1.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setVoiceBeautifierPreset()}</td>
    <td> Sets an voice beautifier effect preset by the SDK. </td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setLocalVoiceEqualization()}</td>
    <td> Sets the local voice equalization effect. You can customize the center frequencies of the local voice effects. </td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setLocalVoicePitch()}</td>
    <td> Sets the voice pitch of a local voice. </td>
    <td>4.1.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setLocalVoiceReverbParam()}</td>
    <td>Enables the local reverb effect. </td>
    <td>4.6.10</td>
  </tr>
</table>

<h2 id="Supplemental enhancement information">Supplemental enhancement information</h2>

<table>
  <tr>
    <th width= 400><b>Method</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#sendSEIMsg()} </td>
    <td> Sends supplemental enhancement information (SEI) messages through the bigtream. 	</td>
    <td>V4.0.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#sendSEIMsg()} </td>
    <td> Sends SEI messages. You can use the bigstream or substream to send SEI messages by calling this method. 	</td>
    <td>V4.0.0</td>
  </tr>
</table>

<br>

<table>
  <tr>
    <th width= 400><b>Event</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onRecvSEIMsg()} </td>
    <td> Occurs when the message that contains the SEI of the remote stream. </td>
    <td>V4.0.0</td>
  </tr>
</table>

<h2 id="CDN relayed streaming">CDN relayed streaming</h2>

Note: This methods are applicable only to Interactive Live Streaming v2.0.

<table>
  <tr>
    <th width= 400><b>Method</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#addLiveStreamTask()} </td>
    <td> Adds a streaming task in a room.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#updateLiveStreamTask()} </td>
    <td> Updates a streaming task in a room.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#removeLiveStreamTask()} </td>
    <td> Deletes a streaming task in a room.</td>
    <td>V3.5.0</td>
  </tr>
</table>

<br>

<table>
  <tr>
    <th width= 400><b>Event</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onLiveStreamState()} </td>
    <td>Live streaming status callbacks</td>
    <td>V3.5.0</td>
  </tr>
 </table>

 <h2 id="Media stream relay across channels">Media stream relay across channels</h2>

<table>
  <tr>
    <th width= 400><b>Method</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#startChannelMediaRelay()} </td>
    <td> Starts relaying media streams across channels.</td> </td>
    <td>V4.2.1</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#updateChannelMediaRelay()} </td>
    <td> Updates the information about the destination room to which the media stream is relayed. </td>
    <td>V4.2.1</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#stopChannelMediaRelay()} </td>
    <td> Stops media stream relay across rooms. </td>
    <td>V4.2.1</td>
  </tr>
</table>

<table>
  <tr>
    <th width= 400><b>Event</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onMediaRelayStatesChange()} </td>
    <td> Occurs when the status of media stream relay changes. </td>
    <td>V4.2.1</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onMediaRelayReceiveEvent()} </td>
    <td>Occurs when events about media stream relay are triggered. </td>
    <td>V4.2.1</td>
  </tr>
</table>

 <h2 id="Volume reminder">Volume reminder</h2>

<table>
  <tr>
    <th width= 400><b>Method</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#enableAudioVolumeIndication(boolean enable,int interval)} </td>
    <td> Enables volume indication for the current speaker. </td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#enableAudioVolumeIndication(boolean enable,int interval,boolean enableVad)} </td>
    <td> Enables volume indication for the current speaker and voice detection. </td>
    <td>V4.6.10</td>
  </tr>
</table>

<table>
  <tr>
    <th width= 400><b>Event</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onRemoteAudioVolumeIndication()} </td>
    <td> Occurs when the system indicates the active speaker and the audio volume.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onLocalAudioVolumeIndication(int volume)} </td>
    <td> Occurs when the system indicates current local audio volume in the room.
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onLocalAudioVolumeIndication(int volume, boolean vadFlag)} </td>
    <td> Occurs when the system indicates current local audio volume in the room and detects voice activities.</td>
    <td>V4.6.10</td>
  </tr>
</table>

<h2 id="In-ears monitoring">In-ears monitoring</h2>

<table>
  <tr>
    <th width= 400><b>Method</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#enableEarback()} </td>
    <td> enables the in-ear monitoring feature.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setEarbackVolume()} </td>
    <td> Sets the volume for in-ear monitoring.</td>
    <td>V3.5.0</td>
  </tr>
</table>

<h2 id="Audio playback routing">Audio playback routing</h2>

<table>
  <tr>
    <th width= 400><b>Method</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#setSpeakerphoneOn()} </td>
    <td> Turns on or off the speaker</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#isSpeakerphoneOn()} </td>
    <td> Checks if the speaker is turned on</td>
    <td>V3.5.0</td>
  </tr>
</table>

<h2 id="Video dual stream mode">Video dual stream mode</h2>

<table>
  <tr>
    <th width= 400><b>Method</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#enableDualStreamMode()} </td>
    <td> Enables or disables the video dual stream mode. </td>
    <td>V3.7.0</td>
  </tr>
</table>

<h2 id="External audio source capture and rendering">External audio source capture and rendering</h2>

<table>
  <tr>
    <th width= 400><b>Method</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#setExternalAudioSource()} </td>
    <td> Enables external source for the audio mainstream and sets the capture parameters. </td>
    <td>V3.9.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setExternalSubStreamAudioSource()} </td>
    <td> Enables external source for the audio substream and sets the capture parameters. </td>
    <td>V4.6.10</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#pushExternalAudioFrame()} </td>
    <td> Pushes the mainstream audio data captured from the external audio source to the internal audio engine.</td> </td>
    <td>V3.9.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#pushExternalSubStreamAudioFrame()} </td>
    <td> Pushes the substream audio data captured from the external audio source to the internal audio engine.</td> </td>
    <td>V4.6.10</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setExternalAudioRender()} </td>
    <td> Sets external audio rendering. </td>
    <td>V4.0.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#pullExternalAudioFrame()} </td>
    <td> Pulls the external audio data </td>
    <td>V4.0.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setStreamAlignmentProperty()} </td>
    <td> Syncs the local system time with the server time. </td>
    <td>V4.6.10</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#getNtpTimeOffset()} </td>
    <td>Gets the difference between the local system time and the server time. </td>
    <td>V4.6.10</td>
  </tr>
</table>



 
 <h2 id="External video source capture">External video source capture</h2>

<table>
  <tr>
    <th width= 400><b>Method</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#setExternalVideoSource()} </td>
    <td> Configures external video source.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#pushExternalVideoFrame()} </td>
    <td> Pushes the external video frame data captured from the external video source.</td>
    <td>V3.5.0</td>
  </tr>
</table>

<h2 id="Raw audio data">Raw audio data</h2>

<table>
  <tr>
    <th width= 400><b>Method</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#setRecordingAudioFrameParameters()}</td>
    <td> Sets the audio recording format.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setPlaybackAudioFrameParameters()} </td>
    <td> Sets the audio playback format.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setAudioFrameObserver()}</td>
    <td> Registers the audio frame observer.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setMixedAudioFrameParameters()}</td>
    <td> Sets the sample rate of the mixed stream after the audio is captured and playback. You must call this method before you join a room.
    <td>V3.9.0</td>
  </tr>
</table>

<table>
  <tr>
    <th width= 400><b>Event</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcAudioFrameObserver#onRecordFrame()}</td>
    <td> Retrieves the audio data captured. </td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcAudioFrameObserver#onPlaybackFrame()}</td>
    <td> Retrieves the audio playback data. </td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcAudioFrameObserver#onMixedAudioFrame()}</td>
    <td> Retrieves the mixed recorded and playback audio frame.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcAudioFrameObserver#onPlaybackAudioFrameBeforeMixingWithUserID(long, NERtcAudioFrame)} </td>
    <td>Returns the raw audio frames of a remote user. <br> The API is deprecated. Use {@link NERtcAudioFrameObserver#onPlaybackAudioFrameBeforeMixingWithUserID(long, NERtcAudioFrame, long)} instead. </td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcAudioFrameObserver#onPlaybackAudioFrameBeforeMixingWithUserID(long, NERtcAudioFrame, long)} </td>
    <td>Returns the raw audio frames of a remote user. </td>
    <td>V4.5.0</td>
  </tr>
</table>

 <h2 id="Raw video data">Raw video data</h2>

<table>
  <tr>
    <th width= 400><b>Event</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#setVideoCallback()}</td>
    <td> Retrieves the video data captured. </td>
    <td>V3.5.0</td>
  </tr>
</table>

<table>
  <tr>
    <th width= 400><b>Event</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcVideoCallback#onVideoCallback()}</td>
    <td> Returns the local video data captured.</td>
    <td>V3.5.0</td>
  </tr>
</table>

<h2 id="Screenshots">Screenshots</h2>

<table>
  <tr>
    <th width= 400><b>Method</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#takeLocalSnapshot()}</td>
    <td> Takes a local video snapshot. </td>
    <td>V4.2.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#takeRemoteSnapshot()}</td>
    <td> Takes a snapshot of a remote video screen. </td>
    <td>V4.2.0</td>
  </tr>
</table>

<table>
  <tr>
    <th width= 400><b>Event</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcTakeSnapshotCallback#onTakeSnapshotResult()}</td>
    <td> Returns the screenshot result. </td>
    <td>V4.2.0</td>
  </tr>
</table>

<h2 id="Watermark">Watermark</h2>

<table>
  <tr>
    <th width= 400><b>Method</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#setLocalCanvasWatermarkConfigs()}</td>
    <td> Adds a watermark to the local video canvas (deprecated). </td>
    <td>V4.2.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setLocalVideoWatermarkConfigs()}</td>
    <td> Adds a watermark to the local video. </td>
    <td>V4.6.10</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setRemoteCanvasWatermarkConfigs()}</td>
    <td> Adds a watermark to the remote video canvas (deprecated). </td>
    <td>V4.2.0</td>
  </tr>
</table>

<br>

<table>
  <tr>
    <th width= 400><b>Event</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onLocalVideoWatermarkState()}</td>
    <td> Returns the watermark result. </td>
    <td>V4.6.10</td>
  </tr>
</table>

<h2 id="Encryption">Encryption</h2>

<table>
  <tr>
    <th width= 400><b>Method</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#enableEncryption()}</td>
    <td>  Enables or disables media stream encryption. </td>
    <td>V4.4.0</td>
  </tr>
</table>

<h2 id="Client audio recording">Client audio recording</h2>

<table>
  <tr>
    <th width= 400><b>Method</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#startAudioRecording()}</td>
    <td> Starts an audio recording on a client. </td>
    <td>V4.2.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#startAudioRecordingWithConfig()}</td>
    <td> Starts an audio recording on a client. </td>
    <td>V4.6.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#stopAudioRecording()}</td>
    <td> Stops an audio recording on the client. </td>
    <td>V4.2.0</td>
  </tr>
</table>

<table>
  <tr>
    <th width= 400><b>Event</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onAudioRecording()}</td>
    <td> Returns the audio recording status. </td>
    <td>V4.2.0</td>
  </tr>
</table>

<h2 id="Cloud proxy">Cloud prox</h2>

<table>
  <tr>
    <th width= 400><b>Method</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#setCloudProxy()}</td>
    <td>Enables and sets the cloud proxy service. </td>
    <td>V4.6.0</td>
  </tr>
</table>

 <h2 id="Device management">Device management</h2>

<table>
  <tr>
    <th width= 400><b>Method</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtc#checkPermission()} </td>
    <td>Checks the device permissions, for example, microphone and camera permissions.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#switchCamera()} </td>
    <td> Switches between front and rear cameras.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#switchCameraWithPosition()} </td>
    <td> Specifies the front or rear camera.</td>
    <td>V4.6.10</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#getCurrentCamera()} </td>
    <td>Check the direction of the current camera. </td>
    <td>V4.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#isCameraZoomSupported()} </td>
    <td> Checks whether the camera zoom function is supported.
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setCameraZoomFactor()} </td>
    <td> Sets the camera zoom ratio.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#getCameraCurrentZoom()} </td>
    <td> Gets the scale of the current camera</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#getCameraMaxZoom()} </td>
    <td> Gets the maximum zoom ratio supported by the camera.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#isCameraTorchSupported()} </td>
    <td> Checks whether the camera flash is supported.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#isCameraFocusSupported()} </td>
    <td> Checks whether the camera manual focus function is supported.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setCameraFocusPosition()} </td>
    <td> Sets the current camera focus position</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#isCameraExposurePositionSupported()} </td>
    <td> Checks whether the camera exposure function is supported.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setCameraExposurePosition()} </td>
    <td> Sets the camera manual exposure position.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setCameraTorchOn()} </td>
    <td> Sets the camera flash function.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setPlayoutDeviceMute()} </td>
    <td> Mutes or unmutes the audio playback device.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#isPlayoutDeviceMute()} </td>
    <td> Gets whether the audio playback device is muted.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#setRecordDeviceMute()} </td>
    <td> Sets the status of the recording device</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#isRecordDeviceMute()} </td>
    <td> Gets whether the audio capture device is muted.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtc#version()} </td>
    <td> NERtc SDK version number</td>
    <td>V3.5.0</td>
  </tr>
</table>

<br>

<table>
  <tr>
    <th width= 400><b>Method</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onAudioDeviceChanged()} </td>
    <td> Occurs when the status of the audio playback device changes.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onAudioDeviceStateChange()} </td>
    <td> Occurs when the status of the video playback device.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onVideoDeviceStageChange()} </td>
    <td>Occurs when video devices change</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onCameraFocusChanged()} </td>
    <td> Occurs when the camera focus position changes.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onCameraExposureChanged()} </td>
    <td> Occurs when the camera exposure position changes.</td>
    <td>V3.5.0</td>
  </tr>
</table>

 <h2 id="Troubleshooting">Troubleshooting</h2>

<table>
  <tr>
    <th width= 400><b>Method</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcEx#uploadSdkInfo()} </td>
    <td> Uploads the log records collected by the SDK.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#startAudioDump()} </td>
    <td> Starts recording an audio dump file.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcEx#stopAudioDump()} </td>
    <td> Stops recording an audio dump file.</td>
    <td>V3.5.0</td>
  </tr>
</table>

<br>

 <table>
  <tr>
    <th width= 400><b>Event</b></th>
    <th width= 600><b>Description</b></th>
    <th width= 200><b>Supported version</b></th>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onWarning()} </td>
    <td> Occurs when a warning is triggered.</td>
    <td>V3.5.0</td>
  </tr>
  <tr>
    <td> {@link NERtcCallbackEx#onError()} </td>
    <td> Occurs when a runtime error occurred in the engine and manual troubleshooting is required.</td>
    <td>V3.5.0</td>
  </tr>
</table>

*/

