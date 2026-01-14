/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk.live;

import com.netease.lava.nertc.impl.RtcCode;

/** AddLiveTaskCallback*/
public interface AddLiveTaskCallback {

    /**
     * @if English
     * Occurs when the system create a pushing-stream task.
     * @param taskId    The task ID for the push stream task. Its format is a 64-bit string that consists of letters, numbers, and underscores. The ID must be unique.
     * @param errCode   Error code. OK: success. Otherwise: failure.
     * @endif
     * @if Chinese
     * 推流任务已创建回调。
     * @param taskId  自定义的推流任务ID。格式为字母、数字、下划线组成的 64 位以内的字符串。请保证此 ID 唯一。
     * @param errCode 错误码。 OK 表示操作成功；其他表示操作失败。
     * @endif
     */
    void onAddLiveStreamTask(String taskId, int errCode);
}
