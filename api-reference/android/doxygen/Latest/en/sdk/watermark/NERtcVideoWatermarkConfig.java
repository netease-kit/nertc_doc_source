package com.netease.lava.nertc.sdk.watermark;

import com.netease.lava.base.util.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * @if English
 * Video watermark settings. Three types of watermarks are supported. You can select one of the three types.
 * @endif
 * @if Chinese
 * 视频水印设置，目前支持三种类型的水印，但只能其中选择一种水印生效。
 * @endif
 */
public class NERtcVideoWatermarkConfig {
    /**
     * @if English
     * Video watermark type enumerations
     * @endif
     * @if Chinese
     * 视频水印类型枚举。
     * @endif
     */
    public enum WatermarkType {
        kNERtcWatermarkTypeImage,
        kNERtcWatermarkTypeText,
        kNERtcWatermarkTypeTimestamp,
    }


    /**
     * @if English
     * Video watermark types
     * @endif
     * @if Chinese
     * 视频水印类型。
     * @endif
     */
    public WatermarkType watermarkType = WatermarkType.kNERtcWatermarkTypeImage;


    /**
     * @if English
     * Image watermark configuration. The setting takes effect only when watermarkType is set to kNERtcWatermarkTypeImage
     * @endif
     * @if Chinese
     * 图片水印配置，watermarkType = kNERtcWatermarkTypeImage 时生效。
     * @endif
     */
    public NERtcVideoWatermarkImageConfig imageWatermark;


    /**
     * @if English
     * Text watermark configuration. The setting takes effect only when watermarkType is set to kNERtcWatermarkTypeText
     * @endif
     * @if Chinese
     * 文字水印配置，watermarkType = kNERtcWatermarkTypeText 时生效。
     * @endif
     */
    public NERtcVideoWatermarkTextConfig textWatermark;


    /**
     * @if English
     * Timestamp watermark configuration. The setting takes effect only when watermarkType is set to kNERtcWatermarkTypeTimestamp
     * @endif
     * @if Chinese
     * 时间戳水印配置，watermarkType = kNERtcWatermarkTypeTimestamp 时生效。
     * @endif
     */
    public NERtcVideoWatermarkTimestampConfig timestampWatermark;


    public boolean isValid() {
        if (watermarkType == WatermarkType.kNERtcWatermarkTypeImage) {
            if (imageWatermark == null) {
                return false;
            }
            if (imageWatermark.imagePaths == null || imageWatermark.imagePaths.isEmpty()) {
                return false;
            }

        }

        if (watermarkType == WatermarkType.kNERtcWatermarkTypeText) {
            if (textWatermark == null) {
                return false;
            }
            if (StringUtils.isEmpty(textWatermark.fontPath)) {
                return false;
            }

            if (StringUtils.isEmpty(textWatermark.content)) {
                return false;
            }
        }


        if (watermarkType == WatermarkType.kNERtcWatermarkTypeTimestamp) {
            if (timestampWatermark == null) {
                return false;
            }
            return !StringUtils.isEmpty(timestampWatermark.fontPath);
        }

        return true;
    }


    public JSONObject toJson() {
        JSONObject jsonObject;
        if (watermarkType == WatermarkType.kNERtcWatermarkTypeImage && imageWatermark != null) {
            jsonObject = imageWatermark.toJson();
        } else if (watermarkType == WatermarkType.kNERtcWatermarkTypeText && textWatermark != null) {
            jsonObject = textWatermark.toJson();
        } else if (watermarkType == WatermarkType.kNERtcWatermarkTypeTimestamp && timestampWatermark != null) {
            jsonObject = timestampWatermark.toJson();
        } else {
            jsonObject = new JSONObject();
        }
        try {
            jsonObject.put("watermark_type", watermarkType);
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
