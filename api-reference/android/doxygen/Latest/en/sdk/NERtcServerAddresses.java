/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk;

/**
 * @if English
 * Private server configuration items.
 * @endif
 * @if Chinese 
 * 私有化服务器配置项。
 * @endif
 */
public class NERtcServerAddresses {

    /**
     * @if English
     * Gets channel information server.
     * @endif
     * @if Chinese 
     * 获取通道信息服务器。
     * @endif
     */
    public String channelServer;

    /**
     * @if English
     * Uploads statistics to server.
     * @endif
     * @if Chinese 
     * 统计上报服务器。
     * @endif
     */
    public String statisticsServer;

    /**
     * @if English
     * RoomServer server.
     * @endif 
     * @if Chinese 
     * roomServer服务器。
     * @endif
     */
    public String roomServer;

    /**
     * @if English
     * Compatibility configuration server.
     * @endif
     * @if Chinese 
     * 兼容性配置服务器
     * @endif
     */
    public String compatServer;

    /**
     * @if English
     * nos domain name resolution server. 
     * @endif
     * @if Chinese 
     * nos 域名解析服务器
     * @endif
     */
    public String nosLbsServer;

    /**
     * @if English
     * (Default) nos upload server.
     * @endif
     * @if Chinese 
     * 默认nos 上传服务器
     * @endif
     */
    public String nosUploadSever;

    /**
     * @if English
     * Gets NOS token server.
     * @endif
     * @if Chinese 
     * 获取NOS token 服务器
     * @endif
     */
    public String nosTokenServer;

    /**
     * 美颜鉴权服务
     */
    public String sdkConfigServer;

    /**
     * cloud proxy server
     */
    public String cloudProxyServer;

    /**
     * webSocket 信令代理地址
     */
    public String webSocketProxyServer;

    /**
     * quic 信令代理地址
     */
    public String quicProxyServer;

    /**
     * 媒体代理地址
     */
    public String mediaProxyServer;


    /**
     * @if English
     * Whether to use IPv6. Default: false. 
     * @endif
     * @if Chinese 
     * 是否使用IPv6（默认false）
     * @endif
     */
    public boolean useIPv6 = false;

}
