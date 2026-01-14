package com.netease.lava.nertc.sdk.watermark;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @if English
 * Timestamp watermark configuration.
 * @endif
 * @if Chinese
 * 视频时间戳水印配置。
 * @endif
 */
public class NERtcVideoWatermarkTimestampConfig {

    /**
     * @if English
     * The absolute path of a font file.
     * @endif
     * @if Chinese
     * 字体文件绝对路径。
     * @endif
     */
    public String fontPath;


    /**
     * @if English
     * font color in ARGB format. The default value is 0xffffffff, white.
     * @endif
     * @if Chinese
     * 字体颜色。ARGB 格式。默认为 0xFFFFFFFF，即白色。
     * @endif
     */
    public int fontColor = 0xFFFFFFFF;


    /**
     * @if English
     * font size. The default value is 15. Unit: px.
     * @endif
     * @if Chinese
     * 字体大小。默认值为 15，单位为像素(px)。
     * @endif
     */
    public int fontSize = 15;


    /**
     * @if English
     * The background color in a watermark box in ARGB format. The default value is 0x88888888, gray.
     * <br>Transparency setting is supported.
     * @endif
     * @if Chinese
     * 水印框内背景颜色。ARGB 格式，默认值为 0x88888888，即灰色。
     * <br>支持透明度设置。
     */
    public int wmColor = 0x88888888;


    /**
     * @if English
     * Overall watermark transparency. Value range: 0.0 ~ 1.0. Default value: 1.0 represents no transparency.
     * @endif
     * @if Chinese
     * 整体水印透明度，取值范围 0.0 ~ 1.0，默认值为 1.0，表示不透明。
     * @endif
     */
    public float wmAlpha = 1.0f;


    /**
     * @if English
     * The width of a watermark box. Unit: px. The default value 0 indicates no watermark box is applied.
     * @endif
     * @if Chinese
     * 水印框宽度，单位为像素(px) ，默认值为 0，表示没有水印框。
     * @endif
     */
    public int wmWidth = 0;


    /**
     * @if English
     * The height of a watermark box. Unit: px. The default value 0 indicates no watermark box is applied.
     * @endif
     * @if Chinese
     * 水印框高度，单位为像素(px) ，默认值为 0，表示没有水印框。
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
     * The vertical distance between the upper left corner  of the screen and the upper left corner of the video image. Unit: px. Default value: 0.
     * @endif
     * @if Chinese
     * 水平左上角与视频图像左上角的垂直距离，单位为像素，默认值为 0。
     * @endif
     */
    public int offsetY = 0;

    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("font_path", fontPath);
            jsonObject.put("font_size", fontSize);
            jsonObject.put("wm_color", "0x" + Integer.toHexString(wmColor)); //颜色转成"#xxxxxx" ,增加可读性
            jsonObject.put("font_color", "0x" + Integer.toHexString(fontColor));//颜色转成"#xxxxxx" ,增加可读性
            jsonObject.put("wm_alpha", wmAlpha);
            jsonObject.put("wm_width", wmWidth);
            jsonObject.put("wm_height", wmHeight);
            jsonObject.put("offset_x", offsetX);
            jsonObject.put("offset_y", offsetY);
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
