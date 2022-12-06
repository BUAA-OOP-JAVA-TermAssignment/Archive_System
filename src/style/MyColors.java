package style;

import java.awt.*;

public class MyColors {
    public static Color paperInfoPaneColor;

    public static void setColorAfterStyleChange() {
        changePaperInfoPaneColor();
    }

    public static void changePaperInfoPaneColor() {
        switch (StyleCtrl.getStyle()) {
            case StyleCtrl.INTELLIJ, StyleCtrl.LIGHT -> paperInfoPaneColor = Color.LIGHT_GRAY;
            case StyleCtrl.DARK, StyleCtrl.DARCULA -> paperInfoPaneColor = Color.DARK_GRAY;
        }
    }

    public static Color getPaperInfoPaneColor() {
        return paperInfoPaneColor;
    }
}
