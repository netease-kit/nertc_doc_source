/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk.video;

import android.graphics.Bitmap;
import com.netease.lava.nertc.sdk.NERtcConstants.ErrorCode;

/**
 * @if English
 * Occurs when a snapshot is made.
 * @endif
 * @if Chinese
 * 截图结果回调接口。
 * @endif
 */
public interface NERtcTakeSnapshotCallback {

    /**
     * @if English
     * Snapshot callback.
     * @param errorCode     For details about error code, See {@link NERtcConstants.ErrorCode}.
     * @param image         Snapshot images.
     * @endif
     * @if Chinese
     * 截图结果回调。
     * @param errorCode 错误码。详细信息请参考 {@link NERtcConstants.ErrorCode}。
     * @param image 截图图片。
     * @endif
     */
    void onTakeSnapshotResult (int errorCode, Bitmap image);
}
