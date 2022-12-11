package style;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;

// 程序启动即工作，类方法
public class StyleCtrl {
    final public static int NOT_SET = -1;
    final public static int LIGHT = 0;
    final public static int INTELLIJ = 1;
    final public static int DARK = 2;
    final public static int DARCULA = 4;
    private static FlatLightLaf flatLightLaf = null;
    private static FlatIntelliJLaf flatIntelliJLaf = null;
    private static FlatDarkLaf flatDarkLaf = null;
    private static FlatDarculaLaf flatDarculaLaf = null;
    private static int style = NOT_SET;

    /**
     * 使用预设值初始化风格。
     */
    public static void init() {
        setStyle(INTELLIJ);
    }

    /**
     * 调用相应的风格应用方法，将当前风格设置为给定值。
     * 注意只有在风格更新后建立的组件才会使用新的风格，如果想要更新已经建立好的组件，需要调用UIManager.setLookAndFeel(...)方法更新
     * @param styleType 风格代码
     */
    public static void setStyle(int styleType) {
        switch (styleType) {
            case LIGHT -> applyLight();
            case INTELLIJ -> applyIntelliJ();
            case DARK -> applyDark();
            case DARCULA -> applyDarcula();
            default -> System.out.println("!!! StyleCtrl : undefined style code");
        }
        style = styleType;
        MyColors.setColorAfterStyleChange();
    }

    /**
     * 应用浅色风格
     */
    private static void applyLight() {
        if(flatLightLaf == null) {
            flatLightLaf = new FlatLightLaf();
        }

        try {
            UIManager.setLookAndFeel(flatDarculaLaf);
        } catch (UnsupportedLookAndFeelException e) {
            System.out.println("!!! StyleCtrl : style set error :" + e);
        }
    }

    /**
     * 应用IntelLij风格
     */
    private static void applyIntelliJ() {
        if(flatIntelliJLaf == null) {
            flatIntelliJLaf = new FlatIntelliJLaf();
        }

        try {
            UIManager.setLookAndFeel(flatIntelliJLaf);
        } catch (UnsupportedLookAndFeelException e) {
            System.out.println("!!! StyleCtrl : style set error :" + e);
        }
    }

    /**
     * 应用深色风格
     */
    private static void applyDark() {
        if(flatDarkLaf == null) {
            flatDarkLaf = new FlatDarkLaf();
        }

        try {
            UIManager.setLookAndFeel(flatDarkLaf);
        } catch (UnsupportedLookAndFeelException e) {
            System.out.println("!!! StyleCtrl : style set error :" + e);
        }
    }

    /**
     * 应用Darcula风格
     */
    private static void applyDarcula() {
        if(flatDarculaLaf == null) {
            flatDarculaLaf = new FlatDarculaLaf();
        }

        try {
            UIManager.setLookAndFeel(flatDarculaLaf);
        } catch (UnsupportedLookAndFeelException e) {
            System.out.println("!!! StyleCtrl : style set error :" + e);
        }
    }

    public static int getStyle() {
        return style;
    }
}
