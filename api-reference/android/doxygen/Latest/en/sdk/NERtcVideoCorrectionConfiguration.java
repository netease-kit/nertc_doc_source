package com.netease.lava.nertc.sdk;

import android.graphics.PointF;

public class NERtcVideoCorrectionConfiguration {
    /**
     * 矫正区域的左上顶点（x 和 y 的取值范围为 0 ~ 1 的浮点数）
     */
    public PointF topLeft;
    /**
     * 矫正区域的右上顶点（x 和 y 的取值范围为 0 ~ 1 的浮点数）
     */
    public PointF topRight;
    /**
     * 矫正区域的左下顶点（x 和 y 的取值范围为 0 ~ 1 的浮点数）
     */
    public PointF bottomLeft;
    /**
     * 矫正区域的右下顶点（x 和 y 的取值范围为 0 ~ 1 的浮点数）
     */
    public PointF bottomRight;
    /**
     * 画布宽度（单位 px，使用外部视频渲染时需要传递）
     */
    public float canvasWidth;
    /**
     * 画布高度（单位 px，使用外部视频渲染时需要传递）
     */
    public float canvasHeight;
    /**
     * 是否镜像显示（使用外部视频渲染时需要传递）
     */
    public boolean enableMirror;

    public NERtcVideoCorrectionConfiguration() {
        topLeft = new PointF(0, 0);
        topRight = new PointF(1, 0);
        bottomRight = new PointF(1, 1);
        bottomLeft = new PointF(0, 1);
    }

    @Override
    public String toString() {
        return "NERtcVideoCorrectionConfiguration{" +
                "topLeft=" + topLeft +
                ", topRight=" + topRight +
                ", bottomLeft=" + bottomLeft +
                ", bottomRight=" + bottomRight +
                ", canvasWidth=" + canvasWidth +
                ", canvasHeight=" + canvasHeight +
                ", enableMirror=" + enableMirror +
                '}';
    }
}
