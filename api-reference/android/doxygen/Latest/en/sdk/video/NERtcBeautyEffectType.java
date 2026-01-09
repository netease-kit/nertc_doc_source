package com.netease.lava.nertc.sdk.video;

/** 
 * @if English
 * Beauty types.
 * @endif
 * @if Chinese
 * 美颜类型。
 * @endif
 */
public enum NERtcBeautyEffectType {
    kNERtcBeautyUnknownType(-1),
    /** 
     * @if English
     * Applies bright teeth. The default value of intensity is 0.0.
     * @endif
     * @if Chinese
     * 美牙。强度默认值为 0.0。
     * @endif
     */
    kNERtcBeautyWhiteTeeth(0), 

    /** 
     * @if English
     * Applies bright eyes. The default value of intensity is 0.0.
     * @endif
     * @if Chinese
     * 亮眼。强度默认值为 0.0。
     * @endif
     */
    kNERtcBeautyLightEye(1), 
     
    /** 
     * @if English
     * Whitening. The default value of intensity is 0.0.
     * @endif
     * @if Chinese
     * 美白。强度默认值为 0.0。
     * @endif
     */
    kNERtcBeautyWhiten(2), 

    /** 
     * @if English
     * Smoothing. The default value of intensity is 0.0.
     * @endif
     * @if Chinese
     * 磨皮。强度默认值为 0.0。
     * @endif
     */
    kNERtcBeautySmooth(3), 

    /** 
     * @if English
     * Applies a small nose. The default value of intensity is 0.0.
     * @endif
     * @if Chinese
     * 小鼻。强度默认值为 0.0。
     * @endif
     */ 
    kNERtcBeautySmallNose(4),
    
    /** 
     * @if English
     * Adjusts the eye distance. The default value of intensity is 0.5.
     * @endif
     * @if Chinese
     * 眼距调整。强度默认值为 0.5。
     * @endif
     */
    kNERtcBeautyEyeDis(5), 

    /** 
     * @if English
     * Adjusts the eye angle. The default value of intensity is 0.5.
     * @endif
     * @if Chinese
     * 眼角调整。强度默认值为 0.5。
     * @endif
     */
    kNERtcBeautyEyeAngle(6),   
    
    /** 
     * @if English
     * Adjusts the mouth shape. The default value of intensity is 0.5.
     * @endif
     * @if Chinese
     * 嘴型调整。强度默认值为 0.5。
     * @endif
     */
    kNERtcBeautyMouth(7),  
    /** 
     * @if English
     * Applies big eyes. The default value of intensity is 0.0.
     * @endif
     * @if Chinese
     * 大眼。强度默认值为 0.0。
     * @endif
     */ 
    kNERtcBeautyBigEye(8), 
    
    /** 
     * @if English
     * Applies a small face. The default value of intensity is 0.0.
     * @endif
     * @if Chinese
     * 小脸。强度默认值为 0.0。
     * @endif
     */
    kNERtcBeautySmallFace(9), 
    
    /** 
     * @if English
     * Adjusts the jaw. The default value of intensity is 0.0.
     * @endif
     * @if Chinese
     * 下巴调整。强度默认值为 0.0。
     * @endif
     */
    kNERtcBeautyJaw(10),  
      
    /** 
     * @if English
     * Applies a thin face. The default value of intensity is 0.0.
     * @endif
     * @if Chinese
     * 瘦脸。强度默认值为 0.0。
     * @endif
     */
    kNERtcBeautyThinFace(11),

    /** 
     * @if English
     * Applies a ruddy face. The default value of intensity is 0.0.
     * @endif
     * @if Chinese
     * 红润。强度默认值为 0.0。
     * @endif
     */
    kNERtcBeautyFaceRuddy(12),

    /** 
     * @if English
     * Applies a long nose. The default value of intensity is 0.5.
     * @endif
     * @if Chinese
     * 长鼻。强度默认值为 0.5。
     * @endif
     */
    kNERtcBeautyLongNose(13),

    /** 
     * @if English
     * Adjusts the philtrum. The default value of intensity is 0.5.
     * @endif
     * @if Chinese
     * 人中调整。强度默认值为 0.5。
     * @endif
     */
    kNERtcBeautyPhiltrum(14),

    /** 
     * @if English
     * Adjusts the mouth angle. The default value of intensity is 0.5.
     * @endif
     * @if Chinese
     * 嘴角调整。强度默认值为 0.5。
     * @endif
     */
    kNERtcBeautyMouthAngle(15),

    /** 
     * @if English
     * Applies round eyes. The default value of intensity is 0.0.
     * @endif
     * @if Chinese
     * 圆眼。强度默认值为 0.0。
     * @endif
     */
    kNERtcBeautyRoundEye(16),

    /** 
     * @if English
     * Adjusts the eye corners. The default value of intensity is 0.0.
     * @endif
     * @if Chinese
     * 开眼角。强度默认值为 0.0。
     * @endif
     */
    kNERtcBeautyEyeCorner(17),

    /** 
     * @if English
     * Applies a V-shaped face. The default value of intensity is 0.0.
     * @endif
     * @if Chinese
     * V脸。强度默认值为 0.0。
     * @endif
     */
    kNERtcBeautyVFace(18),

    /** 
     * @if English
     * Applies a thin jaw. The default value of intensity is 0.0.
     * @endif
     * @if Chinese
     * 瘦下颌。强度默认值为 0.0。
     * @endif
     */
    kNERtcBeautyUnderJaw(19),

    /** 
     * @if English
     * Applies a narrow face. The default value of intensity is 0.0.
     * @endif
     * @if Chinese
     * 窄脸。强度默认值为 0.0。
     * @endif
     */
    kNERtcBeautyNarrowFace(20),

    /** 
     * @if English
     * Adjusts the cheekbone. The default value of intensity is 0.0.
     * @endif
     * @if Chinese
     * 颧骨调整。强度默认值为 0.0。
     * @endif
     */
    kNERtcBeautyCheekBone(21),

    /** 
     * @if English
     * Sharpens the face. The default value of intensity is 0.0.
     * @endif
     * @if Chinese
     * 锐化调整。强度默认值为 0.0。
     * @endif
     */
    kNERtcBeautyFaceSharpen(22);

    private final int value;
    NERtcBeautyEffectType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
