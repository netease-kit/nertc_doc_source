package com.netease.lava.nertc.sdk.video;

/**
 * @if English
 * Custom background image.
 * @endif
 * @if Chinese
 * 自定义背景图像。
 * @endif
 */
public class NERtcVirtualBackgroundSource {
    /**
     * @if English
     * Custom background image is in pure color.
     * @endif
     * @if Chinese
     * 自定义背景图为纯色。
     * @endif
     */
    public static final int BACKGROUND_COLOR = 1;
    /**
     * @if English
     * 自定义背景图为图片。
     * @note 仅支持 PNG 或 JPG 格式的文件。
     * @endif
     * @if Chinese
     * 自定义背景图为图片。
     * @note 仅支持 PNG 或 JPG 格式的文件。
     * @endif
     */
    public static final int BACKGROUND_IMG = 2;
    /**
     * @if English
     * Blurry effect of a custom background image.
     * @endif
     * @if Chinese
     * 自定义背景图为虚化后的效果图。
     * @endif
     */
    public static final int BACKGROUND_BLUR = 3;

    /**
     * @if English
     * Low blurry effect of a custom background image.
     * @endif
     * @if Chinese
     * 自定义背景图的虚化程度为低。用户差不多能看清背景。
     * @endif
     */
    public static final int BLUR_DEGREE_LOW = 1;
    /**
     * @if English
     * Medium blurry effect of a custom background image.
     * @endif
     * @if Chinese
     * 自定义背景图的虚化程度为中。用户较难看清背景。
     * @endif
     */
    public static final int BLUR_DEGREE_MEDIUM = 2;
    /**
     * @if English
     * High blurry effect of a custom background image.
     * @endif
     * @if Chinese
     * 自定义背景图的虚化程度为高。用户很难看清背景。4
     * @endif
     */
    public static final int BLUR_DEGREE_HIGH = 3;
    /**
     * @if English
     * Custom background image type. Enumeration values:
     *      - 1：BACKGROUND_COLOR(custom pure color)；
     *      - 2：BACKGROUND_IMG(custom image)；
     *      - 3：BACKGROUND_BLUR(blurry effect).
     * @endif
     * @if Chinese
     * 自定义背景图的类型。可以设置的枚举值包括：
     *      - 1：BACKGROUND_COLOR（自定义纯色）；
     *      - 2：BACKGROUND_IMG（自定义图片）；
     *      - 3：BACKGROUND_BLUR（虚化效果）。
     * @endif
     */
    public int backgroundSourceType;
    /**
     * @if English
     * Custom color of a background image。The image format is RGB. The hexadecimal integer is used without the symbol #.
     * <br>For example, 0xffb6c1 represents light pink. The default value is 0xffffff that represents white. Value range: [0x000000, 0xffffff]. If the value is invalid, the SDK replaces the original background image with a white image.
     * @note This parameter takes effect only when the type of a background image is `Background_color`.
     * @endif
     * @if Chinese
     * 自定义背景图的颜色。格式为 RGB 定义的十六进制整数，不带 # 号。
     * <br>例如 0xFFB6C1 代表浅粉色。默认值为 0xFFFFFF，表示白色。取值范围是 [0x000000,0xFFFFFF]。如果该值无效，SDK 将原始背景图片替换为白色的图片
     * @note 该参数仅在自定义背景图类型为 `BACKGROUND_COLOR` 时生效。
     * @endif
     */
    public int color;
    /**
     * @if English
     * The local absolute path of a custom background image. PNG and JPG images are supported.
     * @note This parameter takes effect only when the type of a background image is `Background_img`.
     * @endif
     * @if Chinese
     * 自定义背景图片的本地绝对路径。支持 PNG 和 JPG 格式。
     * @note 该参数仅在自定义背景图类型为 `BACKGROUND_IMG` 时生效。
     * @endif
     */
    public String source = null;
    /**
     * @if English
     * The blurring degree of a custom background image. Enumeration values:
     *      - 1：BLUR_DEGREE_LOW (low blurry)
     *      - 2：BLUR_DEGREE_MEDIUM (medium blurry)
     *      - 3：BLUR_DEGREE_HIGH (high blurry)
     * @note This parameter takes effect only when the type of a background image is `Background_blur`.
     * @endif
     * @if Chinese
     * 自定义背景图的虚化程度。可以设置的枚举值包括：
     *      - 1：BLUR_DEGREE_LOW（虚化程度为低）；
     *      - 2：BLUR_DEGREE_MEDIUM（虚化程度为中）；
     *      - 3：BLUR_DEGREE_HIGH（虚化程度为高）。
     * @note 该参数仅在自定义背景图类型为 `BACKGROUND_BLUR` 时生效。
     * @endif
     */
    public int blur_degree;

    public NERtcVirtualBackgroundSource(int backgroundSourceType, int color, String source, int blur_degree) {
        this.backgroundSourceType = backgroundSourceType;
        this.color = color;
        this.source = source;
        this.blur_degree = blur_degree;
    }

    public NERtcVirtualBackgroundSource() {
        this.backgroundSourceType = 1;
        this.color = 0xffffff;
        this.source = "";
        this.blur_degree = BLUR_DEGREE_HIGH;
    }

    @Override
    public String toString() {
        return "NERtcVirtualBackgroundSource{" +
                "backgroundSourceType=" + backgroundSourceType +
                ", color=0x" + Integer.toHexString(color) +
                ", source='" + source + '\'' +
                ", blur_degree=" + blur_degree +
                '}';
    }
}
