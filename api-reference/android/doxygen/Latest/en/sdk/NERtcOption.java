/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk;

/**
 * @if English
 * NERtc Optional parameters.
 * @endif
 * @if Chinese
 * NERtc可选参数。
 * @endif
 */
public class NERtcOption {

    /**
     * @if English
     * Log level. Default (info). See NERtcConstants.LogLevel for details.
     * @endif
     * @if Chinese
     * 日志级别。默认为 info 级别。详细信息请参考 NERtcConstants.LogLevel。
     * @endif
     */
    public int logLevel = NERtcConstants.LogLevel.INFO;

    /**
     * @if English
     * The full path to the log directory. Applies UTF-8 encoding.
     * @endif
     * @if Chinese
     * 日志目录的完整路径，采用UTF-8 编码。可选。
     * @endif
     */
    public String logDir;

     /**
     * @if English
     * External users creates eglcontext.
     * @see android.opengl.EGLContext
     * @see javax.microedition.khronos.egl.EGLContext
     * @endif
     * @if Chinese
     * 外部用户创建 eglContext。
     * @see android.opengl.EGLContext
     * @see javax.microedition.khronos.egl.EGLContext
     * @endif
     */
    public Object eglContext;

    /**
     * @if English
     * Private server address. 
     * <br>If private function is required, gets detailed information from technical support.<br/> 
     * @endif
     * @if Chinese
     * 私有化服务器地址
     * <br>如需启用私有化功能，请联系技术支持获取详情。
     * @endif
     */
    public NERtcServerAddresses serverAddresses;
}
