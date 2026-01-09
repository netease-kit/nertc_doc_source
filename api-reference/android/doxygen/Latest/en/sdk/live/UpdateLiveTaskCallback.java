/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk.live;

import com.netease.lava.nertc.impl.RtcCode;

/**UpdateLiveTaskCallback */
public interface UpdateLiveTaskCallback {

    /**
     * @if English
     * Occurs when the system updates the push stream task.
     * @param taskId    The task ID for the push stream task. The task ID is a 64-bit string that can contain letters, numbers, and underscores. The ID must be unique.
     * @param errCode   Error code. {@link NERtcConstants.ErrorCode#OK} indicates success. Otherwise, failure. 
     * @endif
     * @if Chinese
     * 推流任务已更新回调。
     * @param taskId  自定义的推流任务 ID。格式为字母、数字、下划线组成的 64 位以内的字符串。请保证此 ID 唯一。
     * @param errCode 错误码。{@link NERtcConstants.ErrorCode#OK} 表示操作成功； 其他表示操作失败。
     * @endif
     */
    void onUpdateLiveStreamTask(String taskId, int errCode);

}
