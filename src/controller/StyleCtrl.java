package controller;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;

public class StyleCtrl {
    private static FlatLightLaf flatLightLaf = null;
    private static FlatIntelliJLaf flatIntelliJLaf = null;
    private static FlatDarkLaf flatDarkLaf = null;
    private static FlatDarculaLaf flatDarculaLaf = null;

    public static void init() {
        applyDark();
    }

    public static void applyLight() {
        if(flatLightLaf == null) {
            flatLightLaf = new FlatLightLaf();
        }

        try {
            UIManager.setLookAndFeel(flatDarculaLaf);
        } catch (UnsupportedLookAndFeelException e) {
            System.out.println("Style Set Error :" + e);
        }
    }

    public static void applyIntelliJ() {
        if(flatIntelliJLaf == null) {
            flatIntelliJLaf = new FlatIntelliJLaf();
        }

        try {
            UIManager.setLookAndFeel(flatIntelliJLaf);
        } catch (UnsupportedLookAndFeelException e) {
            System.out.println("Style Set Error :" + e);
        }
    }

    public static void applyDark() {
        if(flatDarkLaf == null) {
            flatDarkLaf = new FlatDarkLaf();
        }

        try {
            UIManager.setLookAndFeel(flatDarkLaf);
        } catch (UnsupportedLookAndFeelException e) {
            System.out.println("Style Set Error :" + e);
        }
    }

    public static void applyDarcula() {
        if(flatDarculaLaf == null) {
            flatDarculaLaf = new FlatDarculaLaf();
        }

        try {
            UIManager.setLookAndFeel(flatDarculaLaf);
        } catch (UnsupportedLookAndFeelException e) {
            System.out.println("Style Set Error :" + e);
        }
    }
}
