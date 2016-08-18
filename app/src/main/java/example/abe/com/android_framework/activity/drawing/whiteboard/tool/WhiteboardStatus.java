package example.abe.com.android_framework.activity.drawing.whiteboard.tool;

/**
 * Created by abe on 16/8/17.
 */
public class WhiteboardStatus {

    public Type type;
    public Width width;
    public Color color;
    public EraserSize eraserSize;

    public WhiteboardStatus(){
        this.type = WhiteboardStatus.Type.DRAW;
        this.width = WhiteboardStatus.Width.NORMAL;
        this.color = WhiteboardStatus.Color.BLUE;
        this.eraserSize = WhiteboardStatus.EraserSize.NORMAL;
    }

    public enum Type {
        DRAW,
        ERASER,
        TEXT,
        RECT
    }

    //画笔粗细
    public enum Width {
        BOLD(20),
        NORMAL(12),
        LIGHT(4);

        Width(int width){
            this.width = width;
        }
        private int width;

        public int getWidth() {
            return width;
        }
    }

    //画笔颜色
    public enum Color {
        RED(android.graphics.Color.RED),
        BLUE(android.graphics.Color.BLUE),
        GREEN(android.graphics.Color.GREEN);

        Color(int color){
            this.color = color;
        }
        private int color;

        public int getColor() {
            return color;
        }
    }

    //橡皮大小粗细
    public enum EraserSize {
        BIG(40),
        NORMAL(20),
        SMALL(10);

        EraserSize(int size){
            this.size = size;
        }
        private int size;

        public int getSize() {
            return size;
        }
    }
}
