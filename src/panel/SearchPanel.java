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
    private final JButton searchButton = new JButton("����");
    private final JButton upButton = new JButton("��һҳ");
    private final JButton downButton = new JButton("��һҳ");
    private final JLabel msgLabel = new JLabel("��������������");
    private SearchPanel(){
        initTextField();
        initSearchButton();
        initWidgetInGridBag();
    }

    /**
     * ����������Ϊ�����һ���س������¼���Ӧ����������focusʱ���û��س�����������
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
//            //TODO:�����������������������
//        });
        searchButton.setFont(MyFonts.TEXT_FONT_18);
    }

    /**
     * �ڸ������У�ʹ��GridBagLayout��Ӹ��������
     * �������Ĵ�С�仯ʱ�������������ĸ߶Ȳ���仯�������ǽ���ʼ����������ֱ��������С�
     * ˮƽ�����ϣ�������ť�����Բ�ͬ��Ȩ�غ������졣
     */
    private void initWidgetInGridBag() {
        final Insets borderInsets = new Insets(0, 10, 0, 10);
        JLabel emptyLabelLeft = new JLabel();
        JLabel emptyLabelRight = new JLabel();

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = borderInsets;

        // �ı���
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

        // ��Ϣ��ʾ
        gbc.gridy = 1;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(msgLabel, gbc);

        // ��ť
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

        // ռλ
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

        //��ҳ
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
     * �������������ж�ʱ���û�����ʾ
     */
    public void connectError() {
        msgLabel.setText("����������ʧ�ܣ����Ժ�����");
        msgLabel.setForeground(Color.RED);
        msgLabel.setVisible(true);
    }

    /**
     * ��������������������Ƽ���Ϣʱ���û�����ʾ
     */
    public void showPrepareSuggest() {
        msgLabel.setText("���ڼ����Ƽ���Ϣ...");
        msgLabel.setForeground(Color.WHITE);
        msgLabel.setVisible(true);
    }

    /**
     * �����ȴ���Ϣ��ʱʱ���û�����ʾ
     */
    public void timeoutError() {
        msgLabel.setText("���������ӳ�ʱ�����Ժ�����");
        msgLabel.setForeground(Color.RED);
        msgLabel.setVisible(true);
    }

    /**
     * ������������δ֪����ʱ���û�����ʾ
     */
    public void undefinedFailed() {
        msgLabel.setText("��������δ֪����");
        msgLabel.setForeground(Color.RED);
        msgLabel.setVisible(true);
    }

    /**
     * �����ɹ��������û�����ʾ
     */
    public void searchSuccess() {
        msgLabel.setVisible(false);
    }

    /**
     * �����ȴ���������Ϣʱ���û�����ʾ
     */
    public void sendMsgNotice() {
        msgLabel.setText("�ȴ���������Ӧ...");
        msgLabel.setForeground(Color.YELLOW);
        msgLabel.setVisible(true);
    }

    public void showSuggest() {
        msgLabel.setText("����Ϊ����ʾ�Ƽ�����");
        msgLabel.setForeground(Color.WHITE);
        msgLabel.setVisible(true);
    }

    public void sameSearchText() {
        msgLabel.setText("�������ݵĽ�����ڱ�չʾ");
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
