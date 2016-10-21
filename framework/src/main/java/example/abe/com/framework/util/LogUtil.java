package example.abe.com.framework.util;

import android.util.Log;

/**
 * Created by abe on 16/5/8.
 */
public class LogUtil {

    private static boolean sIsDebug = true; //判断是会否输出Log
    private static int sCurLogLevel = Log.VERBOSE; //输出级别

    private static StackTraceElement[] sCurrentThread=null;
    private static String sTagName="";
    private static String sMsgT="";
    private static String sMsgC="";

    private static final int PRINT_STACK_NUM = 10;
//    private static final String MARK = "AB-";
    private static final String MARK = "ABE";

    /**
     * Log.e信息，默认不显示，函数调用队列
     * @param msg 消息
     */
    public static void e(String msg) {
        if (!sIsDebug) return;
        if (sCurLogLevel > Log.ERROR) return;

        initTrace(msg, false);
        Log.e(sTagName, sMsgT + sMsgC);
    }

    /**
     * Log.w信息，默认不显示，函数调用队列
     * @param msg 消息
     */
    public static void w(String msg) {
        if (!sIsDebug) return;
        if (sCurLogLevel > Log.WARN) return;

        initTrace(msg, false);
        Log.w(sTagName, sMsgT + sMsgC);
    }

    /**
     * Log.i信息，默认不显示，函数调用队列
     * @param msg 消息
     */
    public static void i(String msg) {
        if (!sIsDebug) return;

        if (sCurLogLevel > Log.INFO) return;

        initTrace(msg, false);
        Log.i(sTagName, sMsgT + sMsgC);
    }

    /**
     * Log.d信息，默认不显示，函数调用队列
     * @param msg 消息
     */
    public static void d(String msg) {
        if (!sIsDebug) return;
        if (sCurLogLevel > Log.DEBUG) return;

        initTrace(msg, false);
        Log.d(sTagName, sMsgT + sMsgC);
    }

    /**
     * Log.v信息，默认不显示，函数调用队列
     * @param msg 消息
     */
    public static void v(String msg) {
        if (!sIsDebug) return;
        if (sCurLogLevel > Log.VERBOSE) return;

        initTrace(msg, false);
        Log.v(sTagName, sMsgT + sMsgC);
    }


    /**
     * Log.e信息
     * @param msg 消息
     * @param isPrintStack 是否显示函数调用队列
     */
    public static void e(String msg, boolean isPrintStack) {
        if (!sIsDebug) return;
        if (sCurLogLevel > Log.ERROR) return;

        initTrace(msg, isPrintStack);
        Log.e(sTagName, sMsgT + sMsgC);
    }

    /**
     * Log.w信息
     * @param msg 消息
     * @param isPrintStack 是否显示函数调用队列
     */
    public static void w(String msg, boolean isPrintStack) {
        if (!sIsDebug) return;
        if (sCurLogLevel > Log.WARN) return;

        initTrace(msg, isPrintStack);
        Log.w(sTagName, sMsgT + sMsgC);
    }

    /**
     * Log.i信息
     * @param msg 消息
     * @param isPrintStack 是否显示函数调用队列
     */
    public static void i(String msg, boolean isPrintStack) {
        if (!sIsDebug) return;

        if (sCurLogLevel > Log.INFO) return;

        initTrace(msg, isPrintStack);
        Log.i(sTagName, sMsgT + sMsgC);
    }

    /**
     * Log.d信息
     * @param msg 消息
     * @param isPrintStack 是否显示函数调用队列
     */
    public static void d(String msg, boolean isPrintStack) {
        if (!sIsDebug) return;
        if (sCurLogLevel > Log.DEBUG) return;

        initTrace(msg, isPrintStack);
        Log.d(sTagName, sMsgT + sMsgC);
    }

    /**
     * Log.v信息
     * @param msg 消息
     * @param isPrintStack 是否显示函数调用队列
     */
    public static void v(String msg, boolean isPrintStack) {
        if (!sIsDebug) return;
        if (sCurLogLevel > Log.VERBOSE) return;

        initTrace(msg, isPrintStack);
        Log.v(sTagName, sMsgT + sMsgC);
    }

    /**
     * 初始化
     * @param msg 消息
     * @param isPrintStack 是否初始化函数调用队列信息
     */
    private synchronized static void initTrace(String msg, boolean isPrintStack) {
        int curentIndex = 4; //getStackTrace数组中，0~3元素，需要跳过（自行debug）
        int printStackNumber = isPrintStack ? PRINT_STACK_NUM : 0;
        sCurrentThread = Thread.currentThread().getStackTrace();

        String className = sCurrentThread[curentIndex].getFileName();
        int endIndex = className.lastIndexOf(".");
//        sTagName = MARK + (endIndex < 0 ? className : className.substring(0, endIndex));
        sTagName = MARK;
        sMsgT = "[" + className + ":" + sCurrentThread[curentIndex].getLineNumber() + ":"
                + sCurrentThread[curentIndex].getMethodName() + "()]---";
        sMsgC = "msg:[" + msg + "]\n";
        if (printStackNumber > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("callTraceStack:[");
            for (int i = curentIndex; i < curentIndex + printStackNumber && i < sCurrentThread.length; i++) {
                sb.append("\n<--" + sCurrentThread[i].getFileName() + ":" + sCurrentThread[i].getLineNumber() + ":"
                        + sCurrentThread[i].getMethodName() + "()");
            }
            sb.append("]");
            sMsgC += sb.toString();
        }
    }
}
