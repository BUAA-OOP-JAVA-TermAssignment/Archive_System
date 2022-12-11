package style;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;

// �����������������෽��
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
     * ʹ��Ԥ��ֵ��ʼ�����
     */
    public static void init() {
        setStyle(INTELLIJ);
    }

    /**
     * ������Ӧ�ķ��Ӧ�÷���������ǰ�������Ϊ����ֵ��
     * ע��ֻ���ڷ����º���������Ż�ʹ���µķ�������Ҫ�����Ѿ������õ��������Ҫ����UIManager.setLookAndFeel(...)��������
     * @param styleType ������
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
     * Ӧ��ǳɫ���
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
     * Ӧ��IntelLij���
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
     * Ӧ����ɫ���
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
     * Ӧ��Darcula���
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
