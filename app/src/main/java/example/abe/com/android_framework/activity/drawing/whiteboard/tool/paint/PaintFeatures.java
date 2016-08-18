package example.abe.com.android_framework.activity.drawing.whiteboard.tool.paint;

import example.abe.com.framework.util.DensityUtil;

/**
 * Created by abe on 16/8/18.
 */
public class PaintFeatures {

    /**
     * 画笔颜色
     */
    public enum PaintColor {
        RED(android.graphics.Color.RED),
        BLUE(android.graphics.Color.BLUE),
        GREEN(android.graphics.Color.GREEN);

        PaintColor(int value) {
            this.value = value;
        }

        public int value;
    }

    /**
     * 橡皮大小粗细
     */
    public enum PaintEraserSize {
        BIG(DensityUtil.dip2px(60)),
        NORMAL(DensityUtil.dip2px(30)),
        SMALL(DensityUtil.dip2px(10));

        PaintEraserSize(int value) {
            this.value = value;
        }

        public int value;
    }

    /**
     * 画笔粗细
     */
    public enum PaintWidth {
        BOLD(DensityUtil.dip2px(16)),
        NORMAL(DensityUtil.dip2px(10)),
        LIGHT(DensityUtil.dip2px(4));

        PaintWidth(int value) {
            this.value = value;
        }

        public int value;
    }

    /**
     * 字体
     */
    public enum PaintFont {
        BIG(DensityUtil.dip2px(16)),
        NORMAL(DensityUtil.dip2px(10)),
        SMALL(DensityUtil.dip2px(4));

        PaintFont(int value) {
            this.value = value;
        }

        public int value;
    }
}
