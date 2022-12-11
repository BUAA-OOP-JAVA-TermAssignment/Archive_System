package panel;

import style.MyColors;
import style.MyFonts;
import style.StyleCtrl;
import view.MyFrame;
import view.MyInterFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SearchPanel extends JPanel {
    private static volatile SearchPanel searchPanel= new SearchPanel();
    private final JTextField textField = new JTextField("");
    private final JButton searchButton = new JButton("搜索");
    private final JButton upButton = new JButton("上一页");
    private final JButton downButton = new JButton("下一页");
    private final JLabel msgLabel = new JLabel("请输入搜索内容");
    private SearchPanel(){
        initTextField();
        initSearchButton();
        initWidgetInGridBag();
    }

    /**
     * 设置搜索框，为其添加一个回车键的事件响应，当搜索框被focus时，敲击回车即触发搜索
     */
    private void initTextField() {
        textField.setOpaque(false);
        textField.setFont(MyFonts.TEXT_FONT_18);
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    searchButton.doClick();
                }
            }
        });
        textField.setFocusable(true);
    }

    private void initSearchButton() {
//        searchButton.addActionListener(actionEvent -> {
//            System.out.println("SearchPanel : search button clicked " + textField.getText());
//            //TODO:向网络控制器发送搜索请求
//        });
        searchButton.setFont(MyFonts.TEXT_FONT_18);
    }

    /**
     * 在该容器中，使用GridBagLayout添加各个组件。
     * 当容器的大小变化时，各个组件自身的高度不会变化，但他们将会始终在容器竖直方向的正中。
     * 水平方向上，各个按钮将会以不同的权重横向拉伸。
     */
    private void initWidgetInGridBag() {
        final Insets borderInsets = new Insets(0, 10, 0, 10);
        JLabel emptyLabelLeft = new JLabel();
        JLabel emptyLabelRight = new JLabel();

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = borderInsets;

        // 文本框
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.ipadx = 50;
        gbc.ipady = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(textField, gbc);

        // 消息提示
        gbc.gridy = 1;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(msgLabel, gbc);

        // 按钮
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.ipadx = 30;
        gbc.ipady = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(searchButton, gbc);

        // 占位
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.3;
        gbc.weighty = 0;
        gbc.ipadx = 30;
        gbc.ipady = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(emptyLabelLeft, gbc);
        gbc.gridx = 5;
        this.add(emptyLabelRight, gbc);

        //翻页
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.ipadx = 15;
        gbc.ipady = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(upButton, gbc);
        gbc.gridx = 4;
        this.add(downButton, gbc);


    }

    public static SearchPanel getInstance() {
        if(searchPanel == null) {
            synchronized (SearchPanel.class) {
                if(searchPanel == null) {
                    searchPanel = new SearchPanel();
                }
            }
        }

        return searchPanel;
    }

    public static void main(String[] args) {
        StyleCtrl.setStyle(StyleCtrl.DARK);
        MyFrame testFrame = new MyFrame() {
        };
        MyInterFrame testInterFrame = new MyInterFrame() {
        };

        SearchPanel searchPanel0 = new SearchPanel();
        SearchPanel searchPanel1 = new SearchPanel();
        SearchPanel searchPanel2 = new SearchPanel();
        SearchPanel searchPanel3 = new SearchPanel();
        SearchPanel searchPanel4 = new SearchPanel();

        searchPanel0.setBounds(20, 20, 300, 30);
        searchPanel1.setBounds(20, 120, 500, 50);
        searchPanel2.setBounds(20, 220, 800, 70);
        searchPanel3.setBounds(20, 520, 1000, 100);
        searchPanel4.setBounds(20, 820, 1600, 150);

        testInterFrame.add(searchPanel0);
        testInterFrame.add(searchPanel1);
        testInterFrame.add(searchPanel2);
        testInterFrame.add(searchPanel3);
        testInterFrame.add(searchPanel4);

        testInterFrame.setSize(1600, 900);
        testFrame.getTable().add(testInterFrame);
        testFrame.setVisible(true);
        testInterFrame.setVisible(true);
    }

    /**
     * 处理当网络连接中断时对用户的显示
     */
    public void connectError() {
        msgLabel.setText("搜索请求发送失败，请稍后重试");
        msgLabel.setForeground(Color.RED);
        msgLabel.setVisible(true);
    }

    /**
     * 处理当正在向服务器请求推荐信息时对用户的显示
     */
    public void showPrepareSuggest() {
        msgLabel.setText("正在加载推荐信息...");
        msgLabel.setForeground(Color.WHITE);
        msgLabel.setVisible(true);
    }

    /**
     * 处理当等待消息超时时对用户的显示
     */
    public void timeoutError() {
        msgLabel.setText("服务器连接超时，请稍后重试");
        msgLabel.setForeground(Color.RED);
        msgLabel.setVisible(true);
    }

    /**
     * 处理当搜索遇到未知错误时对用户的显示
     */
    public void undefinedFailed() {
        msgLabel.setText("搜索发生未知错误");
        msgLabel.setForeground(Color.RED);
        msgLabel.setVisible(true);
    }

    /**
     * 处理当成功搜索对用户的显示
     */
    public void searchSuccess() {
        msgLabel.setVisible(false);
    }

    /**
     * 处理当等待服务器消息时对用户的显示
     */
    public void sendMsgNotice() {
        msgLabel.setText("等待服务器响应...");
        msgLabel.setForeground(Color.YELLOW);
        msgLabel.setVisible(true);
    }

    public void showSuggest() {
        msgLabel.setText("正在为您显示推荐内容");
        msgLabel.setForeground(Color.WHITE);
        msgLabel.setVisible(true);
    }

    public void sameSearchText() {
        msgLabel.setText("搜索内容的结果正在被展示");
        msgLabel.setForeground(Color.YELLOW);
        msgLabel.setVisible(true);
    }

    public JTextField getTextField() {
        return textField;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JButton getUpButton() {
        return upButton;
    }

    public JButton getDownButton() {
        return downButton;
    }
}
