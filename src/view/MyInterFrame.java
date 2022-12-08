package view;

import style.StyleCtrl;

import javax.swing.*;
import java.awt.*;

public abstract class MyInterFrame extends JInternalFrame implements WaitModeAble{
    public MyInterFrame() {
        super();
        // ����С�����Թرգ������ر�Ĭ�ϲ�������Ϊ����
        this.setClosable(true);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        // �����ڲ����þ��Բ���ģʽ��֮�����������Ӧ�����󣬿����޸�
        this.setLayout(null);
        // ���ÿ������
        this.setMaximizable(true);
        this.setMinimumSize(new Dimension(200, 150));
        // ���ÿ�����С��
        this.setIconifiable(true);
        // ���ÿ��Ե�����С
        this.setResizable(true);
        // ����Ĭ�ϴ�С�����������и���
        this.setSize(600, 400);


        //TODO:���㳢����С������ʹ��GroupLayout����ʵ�ֲ����Ч������û��GridBagLayout��ô���ӣ���Ҳ���Բ��ã����ͳ����
    }

    public static void main(String[] args) {
        StyleCtrl.setStyle(StyleCtrl.DARCULA);
        MyFrame testFrame = new MyFrame() {
        };
        MyInterFrame testInterFrame = new MyInterFrame() {
        };

        TestPanel testPanel = new TestPanel();
        TestPanel testPanel1 = new TestPanel();
        testPanel.addComponentsToPane(testPanel);
        testPanel1.addComponentsToPane(testPanel1);
        testPanel.setBounds(10, 25, 200, 200);
        testPanel1.setBounds(10, 300, 200, 200);
        testInterFrame.getContentPane().add(testPanel1);
        testInterFrame.getContentPane().add(testPanel);

        testFrame.getTable().add(testInterFrame);
        testInterFrame.setVisible(true);

        testFrame.setVisible(true);
    }

    public void enWaitMode() {
        this.setEnabled(false);
    }


    public void disWaitMode() {
        this.setEnabled(true);
    }
}

class TestPanel extends JPanel {
    public TestPanel() {
    }

    public void addComponentsToPane(Container pane) {
        JButton button;
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        button = new JButton("Button 1");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        this.add(button, c);

        button = new JButton("Button 2");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        this.add(button, c);

        button = new JButton("Button 3");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 0;
        this.add(button, c);

        button = new JButton("Long-Named Button 4");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40; // make this component tall
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        this.add(button, c);

        JLabel jLabel = new JLabel("hello");
        c.gridy = 2;
        c.gridx = 0;
        this.add(jLabel,c);

        button = new JButton("5");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0; // reset to default
        c.weighty = 1.0; // request any extra vertical space
        c.anchor = GridBagConstraints.PAGE_END; // bottom of space
        c.insets = new Insets(10, 0, 0, 0); // top padding
        c.gridx = 1; // aligned with button 2
        c.gridwidth = 2; // 2 columns wide
        c.gridy = 2; // third row
        this.add(button, c);
    }


}
