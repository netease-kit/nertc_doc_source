package com.netease.lava.nertc.sdk;

import android.content.Context;

import com.netease.lava.nertc.pstn.NERtcDirectCallParam;
import com.netease.lava.nertc.pstn.NERtcLinkResult;
import com.netease.lava.nertc.pstn.NERtcPstnImpl;

public abstract class NERtcLinkEngine {

    /**
     * @if English
     * Get a NERtcPstn instance.
     * @return NERtcPstn instance
     * @endif
     * @if Chinese
     * 获取 NERtcPstn 实例。
     * @return NERtcPstn 实例
     * @endif
     */
    public static NERtcLinkEngine getInstance(){
        return NERtcPstnImpl.getInstance();
    }

    /**
     * @if English
     * Creates an NERtcPstn instance， and initializes the instance.
     * - You must create an NERtcPstn instance before calling any other PSTN related method.
     * - If you do not need the NERtcPstn instance, you can delete the instance by calling the {@link NERtcLinkEngine#release()} method.
     * @param context  Android Activity context。
     * @param callback The callback function. All APIs are called in the main thread.
     * @return For more information about initialization result, see {@link NERtcLinkResult}.
     * @endif
     * @if Chinese
     * 创建并初始化 NERtcPstn 实例。
     * - 请确保在调用其他 PSTN 相关 API 前先调用该方法创建并初始化 NERtcPstn 实例。
     * - 若不再使用 NERtcPstn 实例，需要 {@link NERtcLinkEngine#release()} 调用进行销毁。
     * @param context  安卓活动 (Android Activity) 的上下文。
     * @param callback 回调函数，所有接口均在主线程上回调。
     * @return 初始化结果请参考 {@link NERtcLinkResult}。
     * @endif
     */
    public abstract int init(Context context, NERtcLinkEngineCallback callback);

    /**
     * @if English
     * Initiates a direct call.
     * @param callParam     Call parameters. For more information, see {@link NERtcDirectCallParam}.
     * @return Call result. For more information, see {@link NERtcLinkResult}.
     * @endif
     * @if Chinese
     * 发起直呼。
     * @param callParam     呼叫参数。详细信息请参考 {@link NERtcDirectCallParam}。
     * @return 直呼的呼叫结果，详细信息请参考 {@link NERtcLinkResult}。
     * @endif
     */
    public abstract int directCallStartCall(NERtcDirectCallParam callParam);

    /**
     * @if English
     * Hangs up or cancels a call.
     * - If App user does not answer the direct call, you can call this interface to cancel the call. 
     * - During a call, both users can call this API to end the call at any time.  
     * @note If you want to cancel a call after starting a call by calling directCallStartCall, we recommend you cancel the call by calling directCallHangup after receiving notification returned by the onDirectStartCall callback. Otherwise, an error will occur.
     * @return Result of this call. For more information, see {@link NERtcLinkResult}.
     * @endif
     * @if Chinese
     * 挂断或取消呼叫。
     * - 发起直呼后，如果对方未接听，可以调用此接口取消呼叫。
     * - 通话过程中，通话双方也可以随时调用此接口挂断通话。
     * @note 调用 directCallStartCall 发起直呼后如果需要取消通话，建议在收到 onDirectStartCall 回调之后再调用 directCallHangup 取消通话，否则可能导致呼叫状态异常。
     * @return 挂断结果，详细信息请参考 {@link NERtcLinkResult}。
     * @endif
     */
    public abstract int directCallHangup();

    /**
     * @if English
     * Destroys the NERtcPstn instance, and release resources.
     * - Once you call `release` to destroy the created NERtcPstn instance, you cannot use any method or callback in the SDK any more.
     * - If you want to create NERtcPstn instance again, you must wait for the {@link NERtcLinkEngine#release()} result, then call `init` to create a new NERtcPstn instance.
     * @endif
     * @if Chinese
     * 销毁 NERtcPstn 实例，并释放资源。
     * - 调用该方法将会释放 NERtcPstn SDK中所有资源，之后将无法调用其他方法和接收到 SDK 回调。
     * - 如果想在销毁 NERtcPstn 实例之后再次创建 NERtcPstn 实例，需要等待 {@link NERtcLinkEngine#release()} 方法执行结束后再创建实例。
     * @endif
     */
    public abstract void release();


}
