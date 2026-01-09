/**
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 */

package com.netease.lava.nertc.sdk.video;

import android.graphics.SurfaceTexture;
import android.view.Surface;

import com.netease.lava.webrtc.EglBase;
import com.netease.lava.webrtc.EglBase14;

import static com.netease.lava.webrtc.EglBase.CONFIG_PLAIN;

/**
 * @if English
 * EGL help class.
 * @endif
 * @if Chinese
 * egl 帮助类
 * @endif
 */
public class NERtcEglContextWrapper {

    /** 
     * to be added
     */
    private EglBase eglBase;

    
    private NERtcEglContextWrapper(EglBase eglBase) {
        this.eglBase = eglBase;
    }

    /** 
     * to be added
     */
    public static NERtcEglContextWrapper createEglContext() {
        return new NERtcEglContextWrapper(EglBase.create());
    }

    /** 
     * to be added
     */
    public static NERtcEglContextWrapper createEgl10Context() {
        return new NERtcEglContextWrapper(EglBase.createEgl10(CONFIG_PLAIN));
    }

    /** 
     * to be added
     */
    public static NERtcEglContextWrapper createEgl14Context() {
        return new NERtcEglContextWrapper(EglBase.createEgl14(CONFIG_PLAIN));
    }

    /** 
     * to be added
     */
    public static NERtcEglContextWrapper createEgl10Context(int[] configAttributes) {
        return new NERtcEglContextWrapper(EglBase.createEgl10(configAttributes));
    }

    /** 
     * to be added
     */
    public static NERtcEglContextWrapper createEgl14Context(int[] configAttributes) {
        return new NERtcEglContextWrapper(EglBase.createEgl14(configAttributes));
    }

    /** 
     * to be added
     */
    public Object getEglContext() {
        if (eglBase != null) {
            return eglBase.getEglBaseContext().getEglContext();
        } else {
            return null;
        }
    }
    /** 
     * to be added
     */
    public boolean isEGL14Supported() {
        return eglBase != null && eglBase instanceof EglBase14;
    }

    /** 
     * to be added
    */
    public void createSurface(Surface surface) {
        if(eglBase != null) {
            eglBase.createSurface(surface);
        }
    }

    /** 
     * Create EGLSurface from the Android SurfaceTexture.
    */
    public void createSurface(SurfaceTexture surfaceTexture) {
        if(eglBase != null) {
            eglBase.createSurface(surfaceTexture);
        }
    }

    /** 
     * Create dummy 1x1 pixel buffer surface so the context can be made current.
    */
    public void createDummyPbufferSurface() {
        if(eglBase != null) {
            eglBase.createDummyPbufferSurface();
        }
    }

    /** 
     * to be added
     */
    public void createPbufferSurface(int width, int height) {
        if(eglBase != null) {
            eglBase.createPbufferSurface(width, height);
        }
    }

    /** 
     * to be added
     */
    public boolean hasSurface() {
        return eglBase != null && eglBase.hasSurface();
    }

    /** 
     * to be added
     */
    public int surfaceWidth() {
        return eglBase != null ? eglBase.surfaceWidth() : 0;
    }

    /** 
     * to be added
     */
    public int surfaceHeight() {
        return eglBase != null ? eglBase.surfaceHeight() : 0;
    }

    /** 
     * to be added
     */
    public void releaseSurface() {
        if(eglBase != null) {
            eglBase.releaseSurface();
        }
    }

    /** 
     * to be added
     */
    public void makeCurrent() {
        if(eglBase != null) {
            eglBase.makeCurrent();
        }
    }

    /** 
     * Detach the current EGL context, so that it can be made current on another thread.
     */
    public void detachCurrent() {
        if(eglBase != null) {
            eglBase.detachCurrent();
        }
    }

    /** 
     * Detach the current EGL context, so that it can be made current on another thread.
     */
    public void swapBuffers() {
        if(eglBase != null) {
            eglBase.swapBuffers();
        }
    }

    /** 
     * Detach the current EGL context, so that it can be made current on another thread.
     */
    public void swapBuffers(long presentationTimeStampNs) {
        if(eglBase != null) {
            eglBase.swapBuffers(presentationTimeStampNs);
        }
    }

    /** 
     * Detach the current EGL context, so that it can be made current on another thread.
     */
    public void release() {
        if (eglBase != null) {
            eglBase.release();
        }
    }

}
