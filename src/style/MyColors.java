package style;

import java.awt.*;

public class MyColors {
    private static Color paperInfoPaneColor;
    private static Color specialButtonForeColor;
    private static Color specialButtonBackColor;

    public static void setColorAfterStyleChange() {
        changePaperInfoPaneColor();
        changeSpecialButtonColor();
    }

    public static void changePaperInfoPaneColor() {
        switch (StyleCtrl.getStyle()) {
            case StyleCtrl.INTELLIJ, StyleCtrl.LIGHT -> paperInfoPaneColor = Color.LIGHT_GRAY;
            case StyleCtrl.DARK, StyleCtrl.DARCULA -> paperInfoPaneColor = Color.DARK_GRAY;
        }
    }

    public static void changeSpecialButtonColor() {
        switch (StyleCtrl.getStyle()) {
            case StyleCtrl.INTELLIJ, StyleCtrl.LIGHT -> {
                specialButtonBackColor = Color.DARK_GRAY;
                specialButtonForeColor = Color.WHITE;
            }
            case StyleCtrl.DARK, StyleCtrl.DARCULA -> {
                specialButtonBackColor = Color.LIGHT_GRAY;
                specialButtonForeColor = Color.BLACK;
            }
        }
    }

    public static Color getPaperInfoPaneColor() {
        return paperInfoPaneColor;
    }

    public static Color getSpecialButtonForeColor() {
        return specialButtonForeColor;
    }

    public static Color getSpecialButtonBackColor() {
        return specialButtonBackColor;
    }
}
