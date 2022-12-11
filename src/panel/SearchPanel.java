package panel;

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
    private SearchPanel(){
        initTextField();
        initSearchButton();
        initWidgetInGridBag();
    }

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
}
