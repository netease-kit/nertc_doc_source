//
//  NERtcEngine.h
//  NERtcSDK
//
//  Created by Sampson on 2019/4/25.
//  Copyright © 2019 Netease. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "INERtcEngineEx.h"
#import "NERtcEngineErrorCode.h"

NS_ASSUME_NONNULL_BEGIN


/**
 * @if English
 * NERtcEngine class
 * @endif
 * @if Chinese
 * NERtcEngine 类
 * @endif
 */
NERTC_EXPORT @interface NERtcEngine : NSObject <INERtcEngineEx>

/**
 * @if English
 * Gets an NERtcEngine instance
 * If you create an NERtcEngine instance, the instance remains the same before you destroy it by calling destroyEngine.
 * @return NERtcEngine instance
 * @endif
 * @if Chinese
 * 获取 NERtcEngine 实例
 * 获取之后一直到调用destroyEngine之前，返回的实例都将是同一个
 * @return NERtcEngine 实例
 * @endif
 */
+ (instancetype)sharedEngine;

/**
 * @if English
 * Destroys an NERtcEngine instance and releases the resources consumed by the instance.
 * <br>This method frees up all resources used by the NERTC SDK. Some apps instantiate an NERtcEngine instance when making audio and video calls and release the instance after calls end.
 * - The interface must be called after users invoke `leaveChannel` to leave a room and the relevant callback returns the notification for leaving the room. Alternatively, when clients receives notifications returned by the `onNERtcEngineDidDisconnectWithReason:` callback and reconnection fails, the interface can be called to destroy the instance and release resources.
 * - If you call `destroyEngine`, you are unable to invoke other methods or callbacks supported by the SDK. To make audio and video calls again, you must wait until the `destroyEngine` method is implemented completely and create a new NERtc instance.
 * @note 
 * - The method is called synchronously. You must wait until the resources consumed by the NERtcEngine instance are released. Then perform other operations. We recommend you implement the method in a child thread to avoid blocking the main thread. We recommend you not call destroy in a callback. Otherwise, a deadlock may occur because the SDK must wait for the callback to return before recycling related resources.
 * - The interface cannot be called in a callback. No interfaces can be called before the return of the interface.
 * - To create a new NERtcEngine instance and call APIs after an instance is destroyed, you must wait until the `destroyEngine` method is implemented completely. Then, call`sharedEngine` to create a new NERtcEngine instance and initialize the SDK by calling `setupEngineWithContext`.
 * @endif
 * @if Chinese
 * 销毁 NERtcEngine 实例，并释放资源。
 * <br>该方法释放 NERTC SDK 使用的所有资源。有些 App 只在用户需要时才进行实时音视频通信，完成音视频通话后，则将资源释放出来用于其他操作，该方法适用于此类情况。
 * - 该接口需要在调用 `leaveChannel`、并收到本端离开房间的回调后调用。或收到 `onNERtcEngineDidDisconnectWithReason:` 回调、重连失败时调用此接口销毁实例，并释放资源。
 * - 调用 `destroyEngine` 方法后，您将无法再使用 SDK 的其它方法和回调。如需再次使用实时音视频通话功能，您必须等待 `destroyEngine` 方法执行结束后，重新创建一个新的 NERtc 实例。
 * @note 
 * - 该方法为同步调用，需要等待 NERtcEngine 实例资源释放后才能执行其他操作，建议在子线程中调用该方法，避免主线程阻塞。此外，网易云信不建议 在 SDK 的回调中调用 destroy，否则由于 SDK 要等待回调返回才能回收相关的对象资源，会造成死锁。
 * - 该接口不得在 SDK 的回调中调用，在接口返回前也不允许调用 SDK 的其他任何接口。
 * - 如需在销毁后再次创建 NERtcEngine 实例、调用 SDK 接口，需要等待 `destroyEngine` 方法执行结束后，调用`sharedEngine` 获取一个新的 NERtcEngine 实例，再调用 `setupEngineWithContext` 初始化 SDK。
 * @endif
 */
+ (int)destroyEngine;

/**
 * @if English
 * Checks the SDK version number. You can call this method before or after you join a room.
 * @since V4.6.10
 * @return version number in string format, such as 1.0.0.
 * @endif
 * @endif
 * @if Chinese
 * 查询 SDK 版本号， 该方法在加入房间前后都能调用
 * @since V4.6.10
 * @return 版本号，格式为字符串，如 1.0.0。
 * @endif
 */
+ (NSString *)getVersion;

@end

NS_ASSUME_NONNULL_END
