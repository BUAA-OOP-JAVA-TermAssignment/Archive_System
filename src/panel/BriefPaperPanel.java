package panel;

import style.MyColors;
import style.MyFonts;
import style.StyleCtrl;
import view.MyFrame;
import view.MyInterFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BriefPaperPanel extends JPanel {
    private final JLabel titleLabel = new JLabel("���ױ���");
    private final JLabel authorLabel = new JLabel("����");
    private final JTextArea abstractTextArea = new JTextArea("ҪժҪժҪҪժҪժҪժҪժҪҪժҪժҪҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪҪժҪժҪҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժҪժ");
    private final JLabel keywordsLabel = new JLabel("�ؼ���");
    private final JLabel imageLabel = new JLabel();
    private final JLabel cntLabel = new JLabel("ȫ��ƥ��500��");
    private final JButton buttonDetail = new JButton("����...");
    private final JButton buttonChangeHeight = new JButton("����");

    public BriefPaperPanel() {
        this.setColor();
        this.setTextStyle();
        this.initWidgetInGridBag();
        // �������ñ߿��С
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
    }
    private void setColor() {
        // ���ݵ�ǰ�ķ��ѡ�����ñ�����ɫ
        this.setBackground(MyColors.getPaperInfoPaneColor());
    }

    private void setTextStyle() {
        final Font titleFont = MyFonts.TITLE_FONT_24;
        final Font keywordFont = MyFonts.SUB_TITLE_FONT_18;
        final Font abstractFont = MyFonts.TEXT_FONT_18;

        titleLabel.setFont(titleFont);
        authorLabel.setFont(keywordFont);
        keywordsLabel.setFont(keywordFont);
        abstractTextArea.setFont(abstractFont);
        cntLabel.setFont(keywordFont);

        setAlignment(titleLabel, JLabel.CENTER, JLabel.TOP);
        setAlignment(authorLabel, JLabel.LEFT, JLabel.TOP);
        setAlignment(keywordsLabel, JLabel.LEFT, JLabel.TOP);
        setAlignment(cntLabel, JLabel.RIGHT, JLabel.TOP);

        abstractTextArea.setOpaque(false);
        abstractTextArea.setLineWrap(true);
        abstractTextArea.setEditable(false);
        // ��������ժҪ��߿�
        abstractTextArea.setBorder(new EmptyBorder(5, 0, 0, 0));
        // ���ѡ������Ƿ��ܱ�ѡ��
        abstractTextArea.setEditable(false);
    }

    private void setAlignment(JLabel label, int horizontal, int vertical) {
        label.setHorizontalAlignment(horizontal);
        label.setVerticalAlignment(vertical);
    }

    private void initImage() {
        imageLabel.setBackground(Color.WHITE);
        // ��������ͼƬ��С
        imageLabel.setIcon(new ImageIcon(new ImageIcon(".//resource//book_init.png").getImage().getScaledInstance(200, 276, Image.SCALE_FAST)));
    }


    private void initWidgetInGridBag() {
        this.initImage();

        // ���������ڲ�������
        final Insets borderInsets = new Insets(0, 3, 0, 3);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = borderInsets;

        //ͼƬ
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 5;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.weighty = 1;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.NORTH;
        this.add(imageLabel, gbc);

        //����
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.ipadx = 20;
        gbc.ipady = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(titleLabel, gbc);

        //����
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        this.add(authorLabel, gbc);

        //�ؼ���
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        gbc.weighty = 0;
        this.add(keywordsLabel, gbc);

        //ժҪ
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        this.add(abstractTextArea, gbc);

        //��ť
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.1;
        gbc.weighty = 0;
        gbc.ipadx = 10;
        gbc.ipady = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        buttonDetail.setBackground(MyColors.getSpecialButtonBackColor());
        buttonDetail.setForeground(MyColors.getSpecialButtonForeColor());
        this.add(buttonDetail, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        this.add(buttonChangeHeight, gbc);

        //ƥ�������ǩ
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        this.add(cntLabel, gbc);
    }

    private void testColorSet() {
        this.setBackground(Color.BLUE);
        titleLabel.setBackground(Color.WHITE);
        titleLabel.setOpaque(true);
        authorLabel.setBackground(Color.WHITE);
        authorLabel.setOpaque(true);
        keywordsLabel.setBackground(Color.WHITE);
        keywordsLabel.setOpaque(true);
        abstractTextArea.setBackground(Color.WHITE);
        abstractTextArea.setOpaque(true);
    }

    @Override
    public Dimension getPreferredSize() {
        System.out.println("Brief " + super.getPreferredSize());
        return super.getPreferredSize();
    }

    public static void main(String[] args) {
        StyleCtrl.setStyle(StyleCtrl.DARK);
        MyFrame testFrame = new MyFrame() {
        };
        MyInterFrame testInterFrame = new MyInterFrame() {
        };

        BriefPaperPanel briefPaperPanel_0 = new BriefPaperPanel();
        BriefPaperPanel briefPaperPanel_1 = new BriefPaperPanel();
        BriefPaperPanel briefPaperPanel_2 = new BriefPaperPanel();

        briefPaperPanel_0.setBounds(30, 30, 800, 200);
        briefPaperPanel_1.setBounds(30, 250, 700, 200);
        briefPaperPanel_2.setBounds(30, 470, 600, 300);

//        briefPaperPanel_0.testColorSet();
//        briefPaperPanel_1.testColorSet();
//        briefPaperPanel_2.testColorSet();

        testInterFrame.getContentPane().add(briefPaperPanel_0);
        testInterFrame.getContentPane().add(briefPaperPanel_1);
        testInterFrame.getContentPane().add(briefPaperPanel_2);

        testInterFrame.setSize(1600, 900);
        testFrame.getTable().add(testInterFrame);
        testFrame.setVisible(true);
        testInterFrame.setVisible(true);
    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }

    public JLabel getAuthorLabel() {
        return authorLabel;
    }

    public JTextArea getAbstractTextArea() {
        return abstractTextArea;
    }

    public JLabel getKeywordsLabel() {
        return keywordsLabel;
    }

    public JLabel getImageLabel() {
        return imageLabel;
    }

    public JButton getButtonDetail() {
        return buttonDetail;
    }

    public JButton getButtonChangeHeight() {
        return buttonChangeHeight;
    }
}
/*
GridBagLayout���ֹ�����:
�������ӵ�һ�����ֹ�������,���������.�ڴ˲�����,�����С������ͬ.
GridBagLayout gb=new GridBagLayout();
ContainerName.setLayout(gb);
���ϴ��������������һ��GridBagLayout.Ҫʹ�����������,����������һ��������,GridBagContraints.������GridBagLayout��������λ�����������С����Ҫ��ȫ����Ϣ.ʹ�ò�������:
1).������������ֵ�һ��ʵ��,�����䶨��Ϊ��ǰ�����Ĳ��ֹ�����.
2).����GridBagContraints��һ��ʵ��
3).Ϊ�������Լ��.
4).ͨ������ͳ�β��ֹ������й��������Լ������Ϣ
5).�������ӵ�����.
6).�Ը���������ʾ������ظ����ϲ���.
GridBagContraints��ĳ�Ա�����б�����:

1��gridx������ĺ������ꣻ
2��girdy��������������ꣻ

gridx=0,gridy=0ʱ����0��0�У�GridBagConstraints.RELATIVEΪĬ��ֵ��������ǰ�����������һ�����֮����������ϲ�ָ����ֵ(ͬʱҲ��ָ��gridx��gridy����)����ô������Ӷ��ٸ����������ͬһ����

3��gridwidth��������ĺ����ȣ�Ҳ����ָ���ռ�õ�����������HTML��colspan���ƣ�
4��gridheight����������򳤶ȣ�Ҳ����ָ���ռ�õ�����������HTML��rowspan���ƣ�

����������������Խ���ٸ��������ǵ�Ĭ��ֵ����1�����������Ǻ������������һ�������Խ���ֵ��ΪGridBagConstraints.REMAINDER����Ϊ�����ڶ�������������ֵΪGridBagConstraints.RELATIVE��

gridwidth�ܷ��ܹ�����ס�ÿ��ȡ����������/�·���Ӧλ���Ƿ����㹻�������ռλ������ޣ�λ�ý��ᱻѹ�������磬����gridwidth=3����ֻ�����һ��JButton����ʱ��������·���Ӧλ��û�����������ֻ��1���������ô��ֻռ��1�������С���������/�·���Ӧλ����3������������Ϳ���ռ3�������С�ˡ��������/�·���Ӧλ��ֻ��2�������������ռ2�������С��

gridheight�ܷ���ס�ø߶�ȡ���������������Ƿ����㹻�������ռλ��Ҳ����˵�������߶Ȳ����������������ĸ߶ȡ����磬����gridheight=3����������������ֻ��1�У���������ֻ��1�еĸ߶ȣ���2����ռ2�еĸ߶ȡ�

5��weightx��ָ�е�Ȩ�أ����߲��ֹ�������η�������ˮƽ�ռ䣻
6��weighty��ָ�е�Ȩ�أ����߲��ֹ�������η������Ĵ�ֱ�ռ䣻

�������ô��ڱ��ʱ����������ű��ı�����������Խ�󣬱�ʾ����ܵõ�����Ŀռ䣬Ĭ��ֵ��Ϊ0���������A��weightx=0.5�����B��weightx=1����ô����X����ʱʣ��Ŀռ�ͻ���1��2�ı�����������A��B��

7��anchor�����߲��ֹ���������ڱ��ռ��е�λ��,�����С������ʾ����ʱʹ�ô��ֶΣ�

��CENTER(Ĭ��ֵ)��NORTH��NORTHEAST��EAST��SOUTHEAST��WEST��NORTHWESTѡ��

8��fill�������ʾ����������������ʱ�򣬿������������������Ϊ����������Ǵ�ֱ��䣬����ˮƽ��䣬������������һ����䣻

9��insets��ָ�������ռ����ܱ�Ե�Ŀհ�����Ĵ�С,�ڱ߾࣬������������С�С������ĸ��������ֱ����ϣ����£��ң�Ĭ��Ϊ(0,0,0,0)��

10��ipadx�������ĺ����࣬����Ŀ�Ⱦ�������������С��ȼ���ipadxֵ��
11��ipady�������������࣬����ĸ߶Ⱦ�������������С�߶ȼ���ipadyֵ��
 */