package com.netease.lava.nertc.sdk.watermark;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @if English
 * Image watermark configuration.
 * @note
 * - If the width or height of a watermark box is specified, the image will be resized to the width and height of the watermark box.
 * - If the width or height of a watermark box is unspecified, the image will be displayed in the original size. If the original size is greater than that of the video, the image will be resized to the video size.
 * - For performance considerations, we recommended you set a proper size.
 * @endif
 * @if Chinese
 * 视频图片水印配置。
 * @note
 * - 如果设置了水印框宽或高，图像将缩放至水印框的宽高尺寸。
 * - 如果未设置水印框宽高，按图像原始尺寸展示；原始尺寸大于视频尺寸时，缩放至视频尺寸。
 * - 出于性能的考虑，建议设置尺寸合适的图片。
 * @endif
 */
public class NERtcVideoWatermarkImageConfig {

    /**
     * @if English
     * Overall watermark transparency. Value range: 0.0 ~ 1.0. Default value: 1.0 represents no transparency.
     * @endif
     * @if Chinese
     * 整体水印透明度，取值范围 为 0.0 ~ 1.0，默认值为 1.0，表示不透明。
     * @endif
     */
    public float wmAlpha = 1.0f;


    /**
     * @if English
     * The width of a watermark box. Unit: pixel. Default value: 0, following the original width.
     * @endif
     * @if Chinese
     * 水印框的宽度，单位为像素，默认值为 0，表示按原始图宽。
     * @endif
     */
    public int wmWidth = 0;


    /**
     * @if English
     * The height of a watermark box. Unit: pixels. Default value: 0, following the original height.
     * @endif
     * @if Chinese
     * 水印框的高度，单位为像素，默认值为 0，表示按原始图高。
     * @endif
     */
    public int wmHeight = 0;


    /**
     * @if English
     * The horizontal distance between the upper left corner of the screen and the upper left corner of the video image. Unit: px. Default value: 0.
     * @endif
     * @if Chinese
     * 水平左上角与视频图像左上角的水平距离，单位为像素，默认值为 0。
     * @endif
     */
    public int offsetX = 0;


    /**
     * @if English
     * The vertical distance between the upper left corner of the screen and the upper left corner of the video image. Unit: px. Default value: 0.
     * @endif
     * @if Chinese
     * 水平左上角与视频图像左上角的垂直距离，单位为像素，默认值为 0。
     * @endif
     */
    public int offsetY = 0;

    /**
     * @if English
     * The absolute path of an image. Multiple paths are allowed.
     * @endif
     * @if Chinese
     * 图片绝对路径，支持多个图片路径。
     * @endif
     */
    public ArrayList<String> imagePaths = new ArrayList<>();


    /**
     * @if English
     * Playback frame rate. The default value 0 indicates that images are not switched automatically. Images are displayed in a single frame.
     * <br>The maximum frame rate does not exceed 30fps. If the specified frame rate is higher than the video frame rate, images are displayed at the video frame rate.
     * @endif
     * @if Chinese
     * 播放帧率，默认为 0 表示不自动切换图片，图片单帧静态展示。
     * <br>帧率最高不超过 30fps，如果设置帧率高于视频流帧率，则按照视频流帧率展示。
     * @endif
     */
    public int fps = 0;

    /**
     * @if English
     * Specifies whether to set a loop. The default value is true. If the value is set to false, a watermark disappears when the playback of images ends.
     * @endif
     * @if Chinese
     * 是否设置循环，默认为 true，设置为 false 时图像组播放完毕后水印消失。
     * @endif
     */
    public boolean loop = true;


    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("image_paths", imagePaths);
            jsonObject.put("wm_alpha", wmAlpha);
            jsonObject.put("wm_width", wmWidth);
            jsonObject.put("wm_height", wmHeight);
            jsonObject.put("offset_x", offsetX);
            jsonObject.put("offset_y", offsetY);
            jsonObject.put("fps", fps);
            jsonObject.put("loop", loop);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }


    @Override
    public String toString() {
        return toJson().toString();
    }
}
