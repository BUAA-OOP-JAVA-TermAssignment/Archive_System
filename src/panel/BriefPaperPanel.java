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
    private final JLabel titleLabel = new JLabel("文献标题");
    private final JLabel authorLabel = new JLabel("作者");
    private final JTextArea abstractTextArea = new JTextArea("要摘要摘要要摘要摘要摘要摘要要摘要摘要要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要要摘要摘要要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘要摘");
    private final JLabel keywordsLabel = new JLabel("关键词");
    private final JLabel imageLabel = new JLabel();
    private final JLabel cntLabel = new JLabel("全文匹配500次");
    private final JButton buttonDetail = new JButton("下载...");
    private final JButton buttonChangeHeight = new JButton("更多");

    public BriefPaperPanel() {
        this.setColor();
        this.setTextStyle();
        this.initWidgetInGridBag();
        // 这里设置边框大小
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
    }
    private void setColor() {
        // 根据当前的风格选择设置背景颜色
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
        // 这里这是摘要框边框
        abstractTextArea.setBorder(new EmptyBorder(5, 0, 0, 0));
        // 这个选项控制是否能被选中
        abstractTextArea.setEditable(false);
    }

    private void setAlignment(JLabel label, int horizontal, int vertical) {
        label.setHorizontalAlignment(horizontal);
        label.setVerticalAlignment(vertical);
    }

    private void initImage() {
        imageLabel.setBackground(Color.WHITE);
        // 这里设置图片大小
        imageLabel.setIcon(new ImageIcon(new ImageIcon(".//resource//book_init.png").getImage().getScaledInstance(200, 276, Image.SCALE_FAST)));
    }


    private void initWidgetInGridBag() {
        this.initImage();

        // 这里设置内部组件间距
        final Insets borderInsets = new Insets(0, 3, 0, 3);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = borderInsets;

        //图片
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

        //标题
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

        //作者
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        this.add(authorLabel, gbc);

        //关键词
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        gbc.weighty = 0;
        this.add(keywordsLabel, gbc);

        //摘要
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        this.add(abstractTextArea, gbc);

        //按钮
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

        //匹配次数标签
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
GridBagLayout布局管理器:
这就是最复杂的一个布局管理器了,网格包布局.在此布局中,组件大小不必相同.
GridBagLayout gb=new GridBagLayout();
ContainerName.setLayout(gb);
以上代码是让容器获得一个GridBagLayout.要使用网格包布局,还必须有其一个辅助类,GridBagContraints.它包含GridBagLayout类用来定位及调整组件大小所需要的全部信息.使用步骤如下:
1).创建网格包布局的一个实例,并将其定义为当前容器的布局管理器.
2).创建GridBagContraints的一个实例
3).为组件设置约束.
4).通过方法统治布局管理器有关组件及其约束等信息
5).将组件添加到容器.
6).对各个将被显示的组件重复以上步骤.
GridBagContraints类的成员变量列表如下:

1、gridx—组件的横向坐标；
2、girdy—组件的纵向坐标；

gridx=0,gridy=0时放在0行0列，GridBagConstraints.RELATIVE为默认值，表明当前组件紧跟在上一个组件之后。如果在行上不指定该值(同时也不指定gridx和gridy参数)，那么无论添加多少个组件都是在同一行上

3、gridwidth——组件的横向宽度，也就是指组件占用的列数，这与HTML的colspan类似；
4、gridheight—组件的纵向长度，也就是指组件占用的行数，这与HTML的rowspan类似；

设置组件横向纵向跨越多少个网格，他们的默认值都是1，如果该组件是横向或纵向的最后一个还可以将此值设为GridBagConstraints.REMAINDER，若为倒数第二个组件则可以设值为GridBagConstraints.RELATIVE。

gridwidth能否能够保留住该宽度取决于其正上/下方相应位置是否有足够的组件来占位，如果无，位置将会被压缩。比如，设置gridwidth=3，但只添加了一个JButton，这时如果其上下方相应位置没有其他组件或只有1个组件，那么它只占有1个网格大小。如果它上/下方相应位置有3个组件，那它就可以占3个网格大小了。如果它上/下方相应位置只有2个组件，那它就占2个网格大小。

gridheight能否保留住该高度取决于其左右两边是否有足够的组件来占位。也就是说它的最大高度不大于左右两边最大的高度。比如，设置gridheight=3，如果左右两边组件只有1行，则它仅仅只有1行的高度，有2行则占2行的高度。

5、weightx—指行的权重，告诉布局管理器如何分配额外的水平空间；
6、weighty—指列的权重，告诉布局管理器如何分配额外的垂直空间；

用来设置窗口变大时，各组件跟着变大的比例。当数字越大，表示组件能得到更多的空间，默认值皆为0。比如组件A的weightx=0.5，组件B的weightx=1，那么窗口X轴变大时剩余的空间就会以1：2的比例分配给组件A和B；

7、anchor—告诉布局管理器组件在表格空间中的位置,当组件小于其显示区域时使用此字段；

有CENTER(默认值)、NORTH、NORTHEAST、EAST、SOUTHEAST、WEST、NORTHWEST选择。

8、fill—如果显示区域比组件的区域大的时候，可以用来控制组件的行为。控制组件是垂直填充，还是水平填充，或者两个方向一起填充；

9、insets—指组件与表格空间四周边缘的空白区域的大小,内边距，算入组件自身大小中。它有四个参数，分别是上，左，下，右，默认为(0,0,0,0)。

10、ipadx—组件间的横向间距，组件的宽度就是这个组件的最小宽度加上ipadx值；
11、ipady—组件间的纵向间距，组件的高度就是这个组件的最小高度加上ipady值。
 */