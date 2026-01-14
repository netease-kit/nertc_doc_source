/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk.video;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.netease.lava.api.IVideoRender;
import com.netease.lava.api.LavaRtcView;
import com.netease.lava.api.Trace;
import com.netease.lava.base.thread.ThreadUtils;
import com.netease.lava.nertc.sdk.NERtcConstants;
import com.netease.lava.nertc.sdk.watermark.NERtcCanvasWatermarkConfig;
import com.netease.lava.nertc.sdk.watermark.NERtcImageWatermarkConfig;
import com.netease.lava.nertc.sdk.watermark.NERtcTextWatermarkConfig;
import com.netease.lava.nertc.sdk.watermark.NERtcTimestampWatermarkConfig;
import com.netease.lava.video.VideoViewActionListener;
import com.netease.lava.webrtc.EglRenderer;
import com.netease.lava.webrtc.VideoFrame;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import static com.netease.lava.api.IVideoRender.ScalingType.SCALE_ASPECT_BALANCED;
import static com.netease.lava.api.IVideoRender.ScalingType.SCALE_ASPECT_FILL;
import static com.netease.lava.api.IVideoRender.ScalingType.SCALE_ASPECT_FIT;

/**
 * @if English
 * to be added
 * @endif
 * @if Chinese
 * to be added
 * @endif
 */
public class NERtcVideoView extends FrameLayout implements IVideoRender {

    private static final String TAG = "NERtcVideoViewTag";
    /**
     * @if English
     * to be added
     * @endif
     * @if Chinese
     * to be added
     * @endif
     */
    public LavaRtcView mVideoView;
    private NERtcCanvasWatermarkConfig mWatermarkConfig;
    private TextView mTimeStampView;
    private Runnable mTimeStampRunnable;
    private ArrayList<TextView> mTextViewArray;

    private ArrayList<Runnable> mImageRunnableArray;
    private ArrayList<ImageView> mImageViewArray;
    private VideoViewActionListener viewActionListener;
    private long uid = -1;

    /**
     * @if English
     * @param context context
     * @endif
     * @if Chinese
     * @param context context
     * @endif
     */
    public NERtcVideoView(Context context) {
        super(context);
        init(context);
    }

    /**
     * @if English
     * @param context context
     * @param attrs   context
     * @endif
     * @if Chinese
     * @param context context
     * @param attrs   context
     * @endif
     */
    public NERtcVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mVideoView = new LavaRtcView(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        this.addView(mVideoView, layoutParams);
    }

    /**
     * @if English
     * Sets video display mode.
     * @param type {@link NERtcConstants.VideoScalingType}
     * @note Sets the method with NERtcVideoView.
     * @endif
     * @if Chinese
     * 设置视频的显示模式。
     * @param type {@link NERtcConstants.VideoScalingType}。
     * <br><b>注意</b>：该方法通过 NERtcVideoView 进行设置。
     * @endif
     */
    @Deprecated
    public void setScalingType(int type) {
        ScalingType scalingType;
        switch (type) {
            case NERtcConstants.VideoScalingType.SCALE_ASPECT_FILL:
                scalingType = SCALE_ASPECT_FILL;
                break;
            case NERtcConstants.VideoScalingType.SCALE_ASPECT_BALANCED:
                scalingType = SCALE_ASPECT_BALANCED;
                break;
            case NERtcConstants.VideoScalingType.SCALE_ASPECT_FIT:
            default:
                scalingType = SCALE_ASPECT_FIT;
                break;
        }
        this.setScalingType(scalingType);
    }

    /**
     * @if English
     * Sets the display mode.
     * @param type {@link NERtcConstants.VideoScalingType}
     * @endif
     * @if Chinese
     * 设置显示模式。
     * @param type {@link NERtcConstants.VideoScalingType}。
     * @endif
     */
    @Override
    public void setScalingType(ScalingType type) {
        if (mVideoView == null) {
            Trace.w(TAG, "call setScalingType , but mVideoView is null , please setup to engine first ");
            return;
        }
        mVideoView.setScalingType(type);
    }

    /**
     * @if English
     * Sets whether to enable the mirror mode, which refers to screen is flipped left or right.
     * <br>If you use the front camera, the SDK enables the mirror mode by default. if you use the rear camera, the SDK disables the mirror mode by default.
     * @param mirror Specifies whether to enable the mirror mode.
     * @endif
     * @if Chinese
     * 设置是否开启镜像模式，即画面是否左右翻转。
     * <br>如果使用前置摄像头，SDK 默认启用镜像模式；如果使用后置摄像头，SDK 默认关闭镜像模式。
     * @note 调用此方法前，请先调用 {@link NERtc.setupLocalVideoCanvas} 方法设置本地画布，否则镜像无法生效。
     * @param mirror 是否开启镜像模式。
     * @endif
     */
    @Override
    public void setMirror(boolean mirror) {
        if (mVideoView == null) {
            Trace.w(TAG, "call setMirror , but mVideoView is null , please setup to engine first ");
            return;
        }
        mVideoView.setMirror(mirror);

        VideoViewActionListener listener = viewActionListener;
        if (listener != null) {
            listener.mirrorChange(this, mirror);
        }
    }


    /**
     * @if English
     * Sets whether to overlay video view on the Z axis.
     * @param isMediaOverlay true: Yes; false: No.
     * @endif
     * @if Chinese
     * 设置视频View是否在Z轴上覆盖。
     * @param isMediaOverlay true：覆盖 false：不覆盖。
     * @endif
     */
    public void setZOrderMediaOverlay(boolean isMediaOverlay) {
        if (mVideoView == null) {
            Trace.w(TAG, "call setZOrderMediaOverlay , but mVideoView is null , please setup to engine first ");
            return;
        }
        mVideoView.setZOrderMediaOverlay(isMediaOverlay);
    }

    /**
     * @if English
     * @param visibility
     * @endif
     * @if Chinese
     * @param visibility
     * @endif
     */
    public void setVisibility(int visibility) {
        if (mVideoView == null) {
            Trace.w(TAG, "call setVisibility , but mVideoView is null , please setup to engine first ");
            return;
        }
        mVideoView.setVisibility(visibility);
        super.setVisibility(visibility);
    }

    /**
     * @if English
     * Clears canvas.
     * @endif
     * @if Chinese
     * 清除画布。
     * @endif
     */
    public void clearImage() {
        if (mVideoView == null) {
            Trace.w(TAG, "call clearImage , but mVideoView is null , please setup to engine first ");
            return;
        }
        mVideoView.clearImage();
    }

    /**
     * @if English
     * Releases canvas resources.
     * @endif
     * @if Chinese
     * 释放画布资源。
     * @endif
     */
    public void release() {
        if (mVideoView == null) {
            Trace.w(TAG, "call release , but mVideoView is null , please setup to engine first ");
            return;
        }
        mVideoView.release();
        clearAllWatermarks();
    }




    /**
     * @if English
     * Sets the user ID.
     * @param uid User ID.
     * @endif
     * @if Chinese
     * 设置用户 ID。
     * @param uid 用户 ID。
     * @endif
     */
    public void setUId(long uid) {
        this.uid = uid;
        if (mVideoView == null) {
            Trace.w(TAG, "call setUId , but mVideoView is null , please setup to engine first ");
            return;
        }
        mVideoView.setUserId(uid);
    }


    /**
     * @if English
     * @param listener
     * @param scale
     * to be added
     * @endif
     * @if Chinese
     * @param listener
     * @param scale
     * @endif
     */
    public void addFrameListener(EglRenderer.FrameListener listener, float scale) {
        if (mVideoView == null) {
            Trace.w(TAG, "call addFrameListener , but mVideoView is null , please setup to engine first ");
            return;
        }

        mVideoView.addFrameListener(listener, scale);
    }


    /**
     * @if English
     * to be added
     * @param videoFrame
     * @endif
     * @if Chinese
     * to be added
     * @param videoFrame
     * @endif
     */
    @Override
    public void onFrame(VideoFrame videoFrame) {
        mVideoView.onFrame(videoFrame);
    }

    /**
     * @if English
     * Sets watermark-related parameters.
     * @param watermarkConfig Watermark-related parameters.
     * @endif
     * @if Chinese
     * 设置水印参数。
     * @param watermarkConfig 水印参数
     * @endif
     */
    public void setWatermarkConfig(NERtcCanvasWatermarkConfig watermarkConfig) {
        ThreadUtils.getUiThreadHandler().post(() -> {
            if (mWatermarkConfig == null && watermarkConfig == null) {
                return;
            }
            mWatermarkConfig = watermarkConfig;
            clearAllWatermarks();
            if (mWatermarkConfig != null) {
                if (mWatermarkConfig.imageWatermarks != null) {
                    addImageWatermarks(mWatermarkConfig.imageWatermarks);
                }
                if (mWatermarkConfig.textWatermarks != null) {
                    addTextWatermarks(mWatermarkConfig.textWatermarks);
                }
                if (mWatermarkConfig.timestampWatermark != null) {
                    addTimeStampWatermark();
                }
            }
        });
    }

    @Override
    public boolean isMirror() {
        if (mVideoView == null) {
            Trace.w(TAG, "call isMirror , but mVideoView is null , please setup to engine first ");
            return false;
        }
        return mVideoView.isMirror();
    }

    public void setViewActionListener(VideoViewActionListener viewActionListener) {
        this.viewActionListener = viewActionListener;
    }

    private void addTextWatermarks(NERtcTextWatermarkConfig[] textWatermarks) {
        clearTextWatermarks();
        mTextViewArray = new ArrayList<>();
        for (NERtcTextWatermarkConfig watermark : textWatermarks) {
            if (watermark != null) {
                TextView textView = new TextView(this.getContext());
                LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                layoutParams.gravity = Gravity.LEFT;
                layoutParams.leftMargin = watermark.offsetX;
                layoutParams.topMargin = watermark.offsetY;
                if (watermark.wmWidth > 0 && watermark.wmHeight > 0) {
                    layoutParams.width = watermark.wmWidth;
                    layoutParams.height = watermark.wmHeight;
                    textView.setBackgroundColor(watermark.wmColor);
                }
                textView.setTextSize(watermark.fontSize);
                textView.setTextColor(watermark.fontColor);
                textView.setText(watermark.content);
                this.addView(textView, layoutParams);
                mTextViewArray.add(textView);
            }
        }
    }

    private void clearTextWatermarks() {
        if (mTextViewArray != null && !mTextViewArray.isEmpty()) {
            for (TextView textView : mTextViewArray) {
                this.removeView(textView);
            }
        }
        mTextViewArray = null;
    }

    private void addTimeStampWatermark() {
        clearTimeStampWatermark();
        mTimeStampRunnable = new WatermarkTimeStampRunnable();
        ThreadUtils.getUiThreadHandler().post(mTimeStampRunnable);
    }

    private void clearTimeStampWatermark() {
        if (mTimeStampRunnable != null) {
            ThreadUtils.getUiThreadHandler().removeCallbacks(mTimeStampRunnable);
            mTimeStampRunnable = null;
        }
        if (mTimeStampView != null) {
            NERtcVideoView.this.removeView(mTimeStampView);
            mTimeStampView = null;
        }
    }

    private void addImageWatermarks(NERtcImageWatermarkConfig[] imageWatermarks) {
        clearImageWatermarks();
        if (imageWatermarks != null) {
            mImageViewArray = new ArrayList<>();
            mImageRunnableArray = new ArrayList<>();
            for (NERtcImageWatermarkConfig config : imageWatermarks) {
                if (config != null && config.images != null && config.images.length > 0) {
                    if (config.images.length > 1) { //Dynamic watermarks.
                        if (config.fps > 0) {
                            Runnable runnable = new WatermarkImageRunnable(config);
                            mImageRunnableArray.add(runnable);
                            ThreadUtils.getUiThreadHandler().post(runnable);
                        } else {
                            this.addView(createImageView(config));
                        }
                    } else { //Static watermarks.
                        this.addView(createImageView(config));
                    }
                }
            }
        }
    }

    private ImageView createImageView(NERtcImageWatermarkConfig config) {
        ImageView imageView = new ImageView(this.getContext());
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        if (config.imageWidth > 0 && config.imageHeight > 0) {
            layoutParams.width = config.imageWidth;
            layoutParams.height = config.imageHeight;
        }
        layoutParams.gravity = Gravity.LEFT;
        layoutParams.leftMargin = config.offsetX;
        layoutParams.topMargin = config.offsetY;
        imageView.setLayoutParams(layoutParams);
        imageView.setImageBitmap(config.images[config.images.length - 1]);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        mImageViewArray.add(imageView);
        return imageView;
    }

    private void clearImageWatermarks() {
        if (mImageRunnableArray != null && !mImageRunnableArray.isEmpty()) {
            for (Runnable runnable : mImageRunnableArray) {
                ThreadUtils.getUiThreadHandler().removeCallbacks(runnable);
            }
            mImageRunnableArray = null;
        }

        if (mImageViewArray != null && !mImageViewArray.isEmpty()) {
            for (ImageView imageView : mImageViewArray) {
                this.removeView(imageView);
            }
            mImageViewArray = null;
        }
    }

    private void clearAllWatermarks() {
        clearTextWatermarks();
        clearTimeStampWatermark();
        clearImageWatermarks();
    }

    private class WatermarkTimeStampRunnable implements Runnable {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

        @Override
        public void run() {
            if (mWatermarkConfig != null && mWatermarkConfig.timestampWatermark != null) {
                if (mTimeStampView == null) {
                    mTimeStampView = new TextView(NERtcVideoView.this.getContext());
                    LayoutParams timeStampLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                    NERtcTimestampWatermarkConfig timestampWatermarks = mWatermarkConfig.timestampWatermark;
                    timeStampLayoutParams.gravity = Gravity.LEFT;
                    mTimeStampView.setTextSize(timestampWatermarks.fontSize);
                    mTimeStampView.setTextColor(timestampWatermarks.fontColor);
                    timeStampLayoutParams.leftMargin = timestampWatermarks.offsetX;
                    timeStampLayoutParams.topMargin = timestampWatermarks.offsetY;
                    if (timestampWatermarks.wmWidth > 0 && timestampWatermarks.wmHeight > 0) {
                        timeStampLayoutParams.width = timestampWatermarks.wmWidth;
                        timeStampLayoutParams.height = timestampWatermarks.wmHeight;
                        mTimeStampView.setBackgroundColor(timestampWatermarks.wmColor);
                    }
                    NERtcVideoView.this.addView(mTimeStampView, timeStampLayoutParams);
                }
                String timeStamp = simpleDateFormat.format(Calendar.getInstance().getTime());
                mTimeStampView.setText(timeStamp);
                ThreadUtils.getUiThreadHandler().postDelayed(this, 1000);
            }
        }
    }

    private class WatermarkImageRunnable implements Runnable {

        private NERtcImageWatermarkConfig mConfig;
        private ImageView mImageView;
        private int mIndex = 0;

        WatermarkImageRunnable(NERtcImageWatermarkConfig config) {
            this.mConfig = config;
        }

        @Override
        public void run() {
            if (mConfig != null && mConfig.images != null) {
                if (mImageView == null) {
                    mImageView = createImageView(mConfig);
                    NERtcVideoView.this.addView(mImageView);
                }
                mImageView.setImageBitmap(mConfig.images[mIndex]);
                mIndex++;
                if (mIndex >= mConfig.images.length) { //One loop completed.
                    if (mConfig.loop) {
                        mIndex = 0;
                        ThreadUtils.getUiThreadHandler().postDelayed(this, 1000 / mConfig.fps);
                    } else {
                        mImageView.setVisibility(GONE);
                    }
                } else {
                    ThreadUtils.getUiThreadHandler().postDelayed(this, 1000 / mConfig.fps);
                }
            }
        }
    }

}
