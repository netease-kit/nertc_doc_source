package com.netease.lava.nertc.sdk;

import com.netease.lava.nertc.pstn.NERtcLinkKickOutReason;

public interface NERtcLinkEngineCallback {

    /**
     * @if English
     * Occurs when the direct call initiates.
     * <br>When initiates the direct call, the caller receives the report of call status.
     * @note The feature is currently unavailable for overseas customers.
     * @param code     Status code of direct calls returned by carriers. 
     * @param errMsg   Error messages that corresponds to call status codes
     * @endif
     * @if Chinese
     * 呼叫已开始回调。
     * <br>主叫方发起直呼后，会收到此回调。回调中报告直呼的状态信息。详细信息请参考[融合呼叫状态码](https://doc.yunxin.163.com/docs/jcyOTA0ODM/zg1MDI4MzE)
     * @param code      运营商返回的直呼状态码。
     * @param errMsg    直呼状态码错误信息。
     * @endif
     */
    void onDirectStartCall(int code, String errMsg);

    /**
     * @if English
     * The feature is currently unavailable for overseas customers.
     * @param code   a reserved parameter. Ignore it.   
     * @endif
     * @if Chinese
     * 直呼时，对端开始振铃回调。
     * <br>主叫方发起直呼后，如果对端开始振铃，会触发此回调。
     * @param code      预留参数，无需关注。
     * @endif
     */
    void onDirectCallRing(int code);

    /**
     * @if English
     * The feature is currently unavailable for overseas customers.
     * @param code  a reserved parameter. Ignore it.    
     * @endif
     * @if Chinese
     * 直呼时，对端已接听回调。
     * <br>主叫方发起直呼后，如果对端接听通话，会触发此回调。
     * @param code  预留参数，无需关注。
     * @endif
     */
    void onDirectCallAccept(int code);

    /**
     * @if English
     * The feature is currently unavailable for overseas customers.
     * @param reason                 
     * @param code                   
     * @param errMsg                 
     * @param isCallEstablished     
     * @endif
     * @if Chinese
     * 直呼时，通话已挂断回调。
     * <br>以下场景会触发此回调：
     * - 主叫方发起直呼后，取消呼叫。此时 reason 为 1000。
     * - 主叫方发起直呼后，被叫方拒接。
     * - 主叫方发起直呼，呼叫接通后，任意一方挂断通话。
     * - 由于运营商信号或其他原因导致的通话中断。
     * <br>详细信息请参考[融合呼叫状态码](https://doc.yunxin.163.com/docs/jcyOTA0ODM/zg1MDI4MzE)
     * @param reason                挂断原因。
     * @param code                  运营商返回的直呼状态码。
     * @param errMsg                错误信息，若状态正常则不返回 errMsg。
     * @param isCallEstablished     呼叫是否已接通。
     * @endif
     */
    void onDirectCallHangupWithReason(int reason, int code, String errMsg, boolean isCallEstablished);

    /**
     * @if English
     * The feature is currently unavailable for overseas customers.
     * Occurs when a call is disconnected for direct calls.
     * <br>After the call is accepted, the callback is triggered when the call is disconnected.
     * - Caller gets disconnected by the PSTN server.
     * - A call gets disconnected due to network or other errors.
     * <br>For more information, see[PSTN Status Code](https://doc.yunxin.163.com/docs/jcyOTA0ODM/zg1MDI4MzE)
     * @param code      Status code that indicates the reason for disconnection. 99999 indicates a user is removed from the channel. See the kickout_reason filed in an error message. For information about specific values, @see{@link NERtcLinkKickOutReason}.
     * @param errMsg    Reason for being removed.               
     * @endif
     * @if Chinese
     * 直呼时，通话断开连接回调。
     * <br>呼叫接通后，对方断开连接时会触发此回调。
     * - 主叫方被 PSTN 服务端踢出通话。
     * - 由于网络状态或其他原因导致的通话中断。
     * <br>详细信息请参考[融合呼叫状态码](https://doc.yunxin.163.com/docs/jcyOTA0ODM/zg1MDI4MzE)
     * @param code      状态码，表示通话连接断开的原因。99999 表示被踢导致断开链接，详细信息见 errMsg 中的 kickout_reason 字段。
     * @param errMsg    被踢原因。
     * @endif
     */
    void onDirectCallDisconnectWithError(int code, String errMsg);
}
