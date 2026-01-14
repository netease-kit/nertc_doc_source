/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk;

/**
 * @if English
 * Web proxy server configuration.
 * @endif
 * @if Chinese
 * 网络代理服务器配置。
 * @endif
 */
public class NERtcNetworkProxy {
    /** SOCKS5*/
    public static final String SOCKS5 = "socks5";

    /**
     * @if English
     * Currently Only supports SOCKS5.
     * @endif
     * @if Chinese
     * 目前仅支持SOCKS5。
     * @endif
     */
    public String scheme;

    /**
     * @if English
     * User name.
     * @endif
     * @if Chinese
     * 用户名。
     * @endif
     */
    public String userName;

    /**
     * @if English
     * User password.
     * @endif
     * @if Chinese
     * 用户密码。
     * @endif
     */
    public String userPassword;

    /**
     * @if English
     * Proxy server address. 
     * @endif
     * @if Chinese
     * 代理服务器地址。
     * @endif
     */
    public String host;

    /**
     * @if English
     * Proxy server port.
     * @endif
     * @if Chinese
     * 代理服务器端口。
     * @endif
     */
    public int port;

}
