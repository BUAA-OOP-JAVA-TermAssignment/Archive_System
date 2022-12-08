package view;

import panel.BriefPaperPanel;
import panel.SearchPanel;
import style.StyleCtrl;

import javax.swing.*;
import java.awt.*;

public class GuestSearchFrm extends MyInterFrame {
    private static volatile GuestSearchFrm guestSearchFrm = null;
    private final static int MAX_ELEMENT = 10;
    private final SearchPanel searchBar = SearchPanel.getInstance();
    private final BriefPaperPanel[] briefPanels = new BriefPaperPanel[MAX_ELEMENT];


    private GuestSearchFrm() {
        this.setSize(1000, 800);
        refreshDisplay();
        initLayOut();
        this.setVisible(true);
    }


    private void initLayOut() {
        JScrollPane scrollPane = new JScrollPane();

        JPanel container = new JPanel(){
            @Override
            public Dimension getPreferredSize() {
                Dimension preferredSize = super.getPreferredSize();
                preferredSize.setSize(scrollPane.getWidth(), (int) preferredSize.getHeight());
                return preferredSize;
            }
        };
        initWidgetInGridBag(container);

        container.setVisible(true);
        container.setFocusable(true);

        scrollPane.getViewport().setScrollMode(JViewport.BLIT_SCROLL_MODE);
        scrollPane.setViewportView(container);
        scrollPane.setVisible(true);


        this.setLayout(new BorderLayout());
        this.add(scrollPane);
    }

    private void initWidgetInGridBag(JComponent container) {
        container.setLayout(new GridBagLayout());
        final Insets entryInset = new Insets(20, 0, 20, 0);

        GridBagConstraints gbc = new GridBagConstraints();


        // �趨������Ĳ������������������
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.ipadx = 200;
        gbc.ipady = 90;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        container.add(searchBar, gbc);


        // �趨���ݿ�������������ݿ����
        gbc.ipadx = 200;
        gbc.ipady = 0;
        gbc.insets = entryInset;
        for(int i = 0; i < briefPanels.length; i++) {
            briefPanels[i] = new BriefPaperPanel();
            gbc.gridx = 0;
            gbc.gridy = i + 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.CENTER;
            container.add(briefPanels[i], gbc);
        }
    }

    public void refreshEntriesDataAndShow() {

        //TODO:ˢ�¸�����Ŀ��ʾ�����ݣ�����ѡ���Ƚ����е����ݻ����ĳ���������͵Ķ�����ͳһ����������ʾ��Ŀ�� Ҳ����ֱ�ӽ�����ʾ��Ŀ

        refreshDisplay();
    }

    public static GuestSearchFrm getInstance() {
        if(guestSearchFrm == null) {
            synchronized (GuestSearchFrm.class) {
                if(guestSearchFrm == null) {
                    guestSearchFrm = new GuestSearchFrm();
                }
            }
        }

        return guestSearchFrm;
    }

    private void refreshDisplay() {
        //TODO:ˢ�¸�����Ŀ����ʾ
    }

    public static void main(String[] args) {
        StyleCtrl.setStyle(StyleCtrl.DARK);
        MyFrame testFrame = new MyFrame() {
        };
        testFrame.getTable().add(getInstance());

        testFrame.setVisible(true);
    }
}
