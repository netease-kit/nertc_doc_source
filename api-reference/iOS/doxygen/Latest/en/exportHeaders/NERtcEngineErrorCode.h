/*
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

/**
 * @if English
 * Engine Error Code
 * @endif
 * @if Chinese
 * Engine Error Code。
 * @endif
 */
typedef NS_ENUM(int, NERtcError) {

    /**
     * @if English
     * No errors
     * @endif
     * @if Chinese
     * 没有错误。
     * @endif
     */
    kNERtcNoError = 0,        
    // Resource allocation errors. No such errors are returned during regular requests.


    /**
     * @if English
     * No permissions. Reasons:
	 * - The Audio and Video Call service is not activated. The trial period expires. The service is not renewed.
	 * - Token is not set when a user joins a room in safe mode.
	 * - Others issues.
     * @endif
     * @if Chinese
     * 权限不足。原因包括：。
     * - 未开通音视频通话 2.0 服务，或试用期已过、未及时续费等。
     * - 安全模式下加入房间时未设置 Token。
     * - 其他权限问题。 
     * @endif
     */
    KNERtcErrChannelReservePermissionDenied = 403,

    /**
     * @if English
     * The request timed out
     * @endif
     * @if Chinese
     * 请求超时。
     * @endif
     */  
    kNERtcErrChannelReserveTimeOut = 408,  
    /**
     * @if English
     * Request parameters error
     * @endif
     * @if Chinese
     * 服务器请求参数错误 。
     * @endif
     */          
    kNERtcErrChannelReserveErrorParam = 414, 
     
    /**
     * @if English
     * Invalid APP KEY 
     * @endif
     * @if Chinese
     * 非法的APP KEY。
     * @endif
     */     
    kNERtcErrChannelReserveErrorAppKey = 417, 
    /**
     * @if English
     * Only two users are supported. A third user tries to allocate a room with the same room name.
     * @endif
     * @if Chinese
     * 只支持两个用户, 有第三个人试图使用相同的房间名分配房间。
     * @endif
     */     
    kNERtcErrChannelReserveMoreThanTwoUser = 600,    
    /**
     * @if English
     * Server error in allocating rooms
     * @endif
     * @if Chinese
     * 分配房间服务器出错。
     * @endif
     */
    kNERtcErrChannelReserveServerFail = 601,         
    
    //Engine error code
    /**
     * @if English
     * Common error
     * @endif
     * @if Chinese
     * 通用错误 。
     * @endif
     */
    kNERtcErrFatal = 30001, 
    /**
     * @if English
     * Out of memory
     * @endif
     * @if Chinese
     * 内存耗尽。
     * @endif
     */                  
    kNERtcErrOutOfMemory = 30002,  
    /**
     * @if English
     * Invalid parameter
     * @endif
     * @if Chinese
     * 错误的参数。
     * @endif
     */           
    kNERtcErrInvalidParam = 30003,    
    /**
     * @if English
     * Unsupported operation
     * @endif
     * @if Chinese
     * 不支持的操作。
     * @endif
     */        
    kNERtcErrNotSupported = 30004,
    /**
     * @if English
     * Operations not supported by the current state
     * @endif
     * @if Chinese
     * 当前状态不支持的操作。
     * @endif
     */            
    kNERtcErrInvalidState = 30005,  
    /**
     * @if English
     * Resource exhausted
     * @endif
     * @if Chinese
     * 资源耗尽。
     * @endif
     */          
    kNERtcErrLackOfResource = 30006,  
    /**
     * @if English
     * Invalid index 
     * @endif
     * @if Chinese
     * 非法 index。
     * @endif
     */        
    kNERtcErrInvalidIndex = 30007, 
    /**
     * @if English
     * Device not found
     * @endif
     * @if Chinese
     * 设备未找到。
     * @endif
     */           
    kNERtcErrDeviceNotFound = 30008,  
    /**
     * @if English
     * Invalid device ID 
     * @endif
     * @if Chinese
     * 非法设备 ID。
     * @endif
     */        
    kNERtcErrInvalidDeviceSourceID = 30009,  
    /**
     * @if English
     * Invalid video profile type 
     * @endif
     * @if Chinese
     * 非法的视频 profile type。
     * @endif
     */ 
    kNERtcErrInvalidVideoProfile = 30010, 
     /**
     * @if English
     * Device source error
     * @endif
     * @if Chinese
     * 设备创建错误。
     * @endif
     */    
    kNERtcErrCreateDeviceSourceFail = 30011,   
    /**
     * @if English
     * Invalid rendering container
     * @endif
     * @if Chinese
     * 非法的渲染容器。
     * @endif
     */
    kNERtcErrInvalidRender = 30012,          
    /**
     * @if English
     * The device already turned on
     * @endif
     * @if Chinese
     * 设备已经打开。
     * @endif
     */ 
    kNERtcErrDevicePreviewAlreadyStarted = 30013,  
    /**
     * @if English
     * Transmission error
     * @endif
     * @if Chinese
     * 传输错误。
     * @endif
     */
    kNERtcErrTransmitPendding = 30014,   
    /**
     * @if English
     * Connecting server error
     * @endif
     * @if Chinese
     * 连接服务器错误。
     * @endif
     */     
    kNERtcErrConnectFail = 30015,
    /**
     * @if English
     * A user already joined a room.
     * @endif
     * @if Chinese
     * 重复加入房间。
     * @endif
     */
    kNERtcErrChannelAlreadyJoined = 30100,      
    /**
     * @if English
     * A user has not joined the room.
     * @endif
     * @if Chinese
     * 尚未加入房间。
     * @endif
     */
    kNERtcErrChannelNotJoined = 30101,   
    /**
     * @if English
     * A user repeatedly leaves the room.
     * @endif
     * @if Chinese
     * 重复离开房间。
     * @endif
     */     
    kNERtcErrChannelRepleatedlyLeave = 30102,  
    /**
     * @if English
     * Fail to join the room.
     * @endif
     * @if Chinese
     * 加入房间操作失败。
     * @endif
     */
    kNERtcErrRequestJoinChannelFail = 30103,    
    /**
     * @if English
     * Session not found.
     * @endif
     * @if Chinese
     * 会话未找到 。
     * @endif
     */
    kNERtcErrSessionNotFound = 30104,      
    /**
     * @if English
     * User not found
     * @endif
     * @if Chinese
     * 用户未找到。
     * @endif
     */   
    kNERtcErrUserNotFound = 30105,        
    /**
     * @if English
     * Invalid user ID 
     * @endif
     * @if Chinese
     * 非法的用户 ID。
     * @endif
     */    
    kNERtcErrInvalidUserID = 30106,   
    /**
     * @if English
     * Multimedia data is not transmitted.
     * @endif
     * @if Chinese
     * 用户多媒体数据未连接。
     * @endif
     */        
    kNERtcErrMediaNotStarted = 30107,  
    /**
     * @if English
     * source not found
     * @endif
     * @if Chinese
     * source 未找到。
     * @endif
     */       
    kNERtcErrSourceNotFound = 30108,        
    /**
     * @if English
     * Invalid room status during switching
     * @endif
     * @if Chinese
     * 切换房间状态无效。
     * @endif
     */  
    kNERtcErrSwitchChannelInvalidState = 30109,
    /**
     * @if English
     * Invalid status during the media stream relay.
     * <br>The typical reason is repeated calling startChannelMediaRelay. If you want to call the startChannelMediaRelay method for another time, you must first call the stopChannelMediaRelay method to exit the current relaying status.
     * @endif
     * @if Chinese
     * 媒体流转发状态无效。
     * <br>原因通常为重复调用 startChannelMediaRelay。成功调用startChannelMediaRelay后，必须先调用 stopChannelMediaRelay 方法退出当前的转发状态，才能再次调用该方法。
     * @endif
     */
    kNERtcErrChannelMediaRelayInvalidState = 30110,
    /**
     * @if English
     * No permissions to relay media stream.
     * <br>Reasons:
     * - The type of the original room is a double room (1V1 mode). The media stream cannot be relayed at this moment.
     * - The member role that calls startChannelMediaRelay to relay media stream is audience. Only the host role can relay the media stream. 
     * @endif
     * @if Chinese
     * 媒体流转发权限不足。
     * <br>原因通常包括：
     * - 源房间的房间类型为双人房间（1V1模式）。此时无法转发媒体流。
     * - 调用 startChannelMediaRelay 开启媒体流转发的成员角色为观众角色，仅主播角色可以转发媒体流。
     * @endif
     */
    kNERtcErrChannelMediaRelayPermissionDenied = 30111,
    /**
     * @if English
     * Failed to stop relaying media stream.
     * The typical reason is that the media stream relay is disabled. Before you call stopChannelMediaRelay, make sure that you have called startChannelMediaRelay to enable the media stream relay. 
     * @endif
     * @if Chinese
     * 停止媒体流转发操作失败。
     * 原因通常为未开启媒体流转发。请确认调用 stopChannelMediaRelay 前，是否已成功调用 startChannelMediaRelay 开启媒体流转发。
     * @endif
     */
    kNERtcErrChannelMediaRelayStopFailed= 30112,
    
    /**
     * @if English
     * Failed to join the room because the specified media stream encryption key is inconsistent with the key specified by one or more of the members who join the room.
     * Reset the encryption key by calling enableEncryption.
     * @endif
     * @if Chinese
     * 设置的媒体流加密密钥与房间中其他成员不一致，加入房间失败。
     * 请通过 enableEncryption 重新设置加密密钥。
     * @endif
     */
    kNERtcErrEncryptNotSuitable= 30113,
    /**
     * @if English
     * Connection not found.
     * @endif
     * @if Chinese
     * 连接未找到
     * @endif
     */
    kNERtcErrConnectionNotFound = 30200, 
    /**
     * @if English
     * Media stream not found
     * @endif
     * @if Chinese
     * 媒体流未找到
     * @endif
     */     
    kNERtcErrStreamNotFound = 30201,    
     /**
     * @if English
     * Failed to join track.
     * @endif
     * @if Chinese
     * 加入 track 失败
     * @endif
     */      
    kNERtcErrAddTrackFail = 30202, 
    /**
     * @if English
     * Track not found.
     * @endif
     * @if Chinese
     * track 未找到
     * @endif
     */          
    kNERtcErrTrackNotFound = 30203,  
    /**
     * @if English
     * Media stream disconnected. 
     * @endif
     * @if Chinese
     * 媒体连接断开
     * @endif
     */         
    kNERtcErrMediaConnectionDisconnected = 30204,  
    /**
     * @if English
     * Signaling disconnected. 
     * @endif
     * @if Chinese
     * 信令连接断开
     * @endif
     */  
    kNERtcErrSignalDisconnected = 30205,       
    /**
     * @if English
     * Removed from the room.
     * @endif
     * @if Chinese
     * 被踢出房间
     * @endif
     */  
    kNERtcErrServerKicked = 30206,       
    /**
     * @if English
     * The room is deleted.
     * @endif
     * @if Chinese
     * 房间已被关闭
     * @endif
     */     
    kNERtcErrChannelBeClosed = 30207,          
    /**
     * @if English
     * The room was closed due to the operation of switching rooms.
     * @endif
     * @if Chinese
     * 房间被关闭因为切换房间的操作
     * @endif
     */  
    kNERtcErrChannelLeaveBySwitchAction = 30208,
    /**
     * @if English
     * The room was closed due to duplicate uid logins
     * @endif
     * @if Chinese
     * 房间被关闭因为有重复 uid 登录
     * @endif
     */
    kNERtcErrChannelLeaveByDuplicateUidLogin = 30209,
    
    /** ICE 连接超时 */
    kNERtcErrICETimeOut = 30210,
    /**
     * @if English
     * Operating system permissions issue
     * @endif
     * @if Chinese
     * 操作系统权限问题
     * @endif
     */
    kNERtcErrOSAuthorize = 30300,
    /**
     被服务器禁言
     */
    kNERtcErrMediaOpenBannedByServer = 30403,
    
    /**
     * @if English
     * have no audio device permissions
     * @endif
     * @if Chinese
     * 没有音频设备权限
     * @endif
     */
    kNERtcRuntimeErrADMNoAuthorize = 40000,  
    /**
     * @if English
     * Failed to initialize the audio capture device
     * @endif
     * @if Chinese
     * 音频采集设备初始化失败
     * @endif
     */ 
    kNERtcRuntimeErrADMInitRecording = 40001,   
    /**
     * @if English
     * Failed to start the audio capture device
     * @endif
     * @if Chinese
     * 音频采集设备开始失败
     * @endif
     */
    kNERtcRuntimeErrADMStartRecording = 40002, 
    /**
     * @if English
     * Failed to stop the audio capture device
     * @endif
     * @if Chinese
     * 音频采集设备停止失败
     * @endif
     */  
    kNERtcRuntimeErrADMStopRecording= 40003,   
     /**
     * @if English
     * Failed to initialize the audio playback device
     * @endif
     * @if Chinese
     * 音频播放设备初始化失败 
     * @endif
     */  
    kNERtcRuntimeErrADMInitPlayout = 40004,  
     /**
     * @if English
     * Failed to start the audio playback device
     * @endif
     * @if Chinese
     * 音频播放设备开始失败
     * @endif
     */ 
    kNERtcRuntimeErrADMStartPlayout = 40005,   
    /**
     * @if English
     * Failed to stop the audio playback device
     * @endif
     * @if Chinese
     * 音频播放设备停止失败
     * @endif
     */
    kNERtcRuntimeErrADMStopPlayout = 40006,     
    /**
     * @if English
     * Have no video device permissions
     * @endif
     * @if Chinese
     * 没有视频设备权限
     * @endif
     */
    kNERtcRuntimeErrVDMNoAuthorize = 50000,     
    /**
     * @if English
     * Use substream without the screen-sharing stream
     * @endif
     * @if Chinese
     * 非屏幕共享使用辅流
     * @endif
     */
    kNERtcRuntimeErrVDMNotScreenUseSubStream = 50001, 
};

typedef NS_ENUM(int, NERtcWarning) {

    /**
     * @if English
     * No errors
     * @endif
     * @if Chinese
     * 没有错误
     * @endif
     */
    kNERtcNoWarning = 0,

    // Resource allocation errors. No such errors are returned during regular requests.
    /**
     * @if English
     * The video encoding capability of the current client device does not match the encoding support of the room. For example, the device does not support encoding types such as VP8. In this room, video streams cannot be encoded and decoded. In this case, the local client may not be able to display some remote videos, and the remote client may be unable to display videos from the local client. 
     * @endif
     * @if Chinese
     * 当前客户端设备视频编解码能力与房间不匹配，例如设备不支持 VP8 等编码类型。在此房间中可能无法成功进行视频编解码，即本端可能无法正常显示某些远端的视频画面，同样远端也可能无法显示本端画面。
     * @endif
     */
    KNERtcWarningChannelAbilityNotMatch = 406,
  
    /**
     * @if English
     * Automatic audio quality fallback.
     * @endif
     * @if Chinese
     * 音频自动选路回退。
     * @endif
     */
    kNERtcWarningASLFallback = 407,
};

/**
 * @if English
 * LiveStream Error Code
 * @endif
 * @if Chinese
 * LiveStream Error Code。
 * @endif
 */

typedef NS_ENUM(int, kNERtcLiveStreamError) {

    /**
     * @if English
     * The task request is invalid and overwritten by subsequent operations
     * @endif
     * @if Chinese
     * task请求无效，被后续操作覆盖。
     * @endif
     */
    kNERtcLiveStreamErrorRequestIsInvaild = 1301, 

    /**
     * @if English
     * The task parameter has an invalid format
     * @endif
     * @if Chinese
     * task参数格式错误。
     * @endif
     */         
    kNERtcLiveStreamErrorIsInvaild = 1400, 
    /**
     * @if English
     * The client already leaves the room
     * @endif
     * @if Chinese
     * 房间已经退出。
     * @endif
     */               
    kNERtcLiveStreamErrorRoomExited = 1401, 

    /**
     * @if English
     * The number of streaming tasks reaches the upper limit.
     * @endif
     * @if Chinese
     * 推流任务超出上限。
     * @endif
     */
    kNERtcLiveStreamErrorNumLimit = 1402,   

    /**
     * @if English
     * Duplicate ID of a streaming task
     * @endif
     * @if Chinese
     * 推流ID重复。
     * @endif
     */                 
    kNERtcLiveStreamErrorDuplicateId = 1403, 

    /**
     * @if English
     * The task ID does not exist, or the room does not exist
     * @endif
     * @if Chinese
     * taskId任务不存在，或房间不存在。
     * @endif
     */        
    kNERtcLiveStreamErrorNotFound = 1404,        
    /**
     * @if English
     * Request failed
     * @endif
     * @if Chinese
     * 请求失败。
     * @endif
     */
    kNERtcLiveStreamErrorRequestErr = 1417,  

     /**
     * @if English
     * Server internal error
     * @endif
     * @if Chinese
     * 服务器内部错误。
     * @endif
     */  
    kNERtcLiveStreamErrorInternalServerErr = 1500,   

    /**
     * @if English
     * The layout parameter is invalid
     * @endif
     * @if Chinese
     * 布局参数错误。
     * @endif
     */    
    kNERtcErrLsTaskInvalidLayout = 1501,          
    /**
     * @if English
     * User picture error
     * @endif
     * @if Chinese
     * 用户图片错误。
     * @endif
     */        
    kNERtcErrLsTaskUserPicErr = 1502,                     
};


extern __attribute__((visibility("default"))) NSString * const NERtcReserveChannelErrorDomain;
extern __attribute__((visibility("default"))) NSString * const NERtcRoomServerErrorDomain;
extern __attribute__((visibility("default"))) NSString * const NERtcLocalErrorDomain;
extern __attribute__((visibility("default"))) NSString * const NERtcRemoteErrorDomain;

#ifdef __cplusplus
extern "C" {
#endif
    


    /**
     * @if English
     * Gets the error code description
     * @param errorCode The error codes. For more information, see {@link NERtcEngineErrorCode.NERtcError}.
     * @return The strings in the description.
     * @endif
     * @if Chinese
     * 获取错误码描述。
     * @param errorCode 错误码，详细信息请参考 {@link NERtcEngineErrorCode.NERtcError}。
     * @return 描述字符串。
     * @endif
     */
__attribute__((visibility("default")))  NSString * NERtcErrorDescription(int errorCode);
    
#ifdef __cplusplus
} // extern "C"
#endif

NS_ASSUME_NONNULL_END
