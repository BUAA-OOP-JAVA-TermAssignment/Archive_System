package view;

import message.SearchReturnMsg;
import panel.BriefPaperPanel;
import panel.SearchPanel;
import style.StyleCtrl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuestSearchFrm extends MyInterFrame {
    public final static int HEIGHT_BRIEF = 0, HEIGHT_MORE = 1;
    private final static Dimension BRIEF_DIMENSION = new Dimension(0, 300);
    private final static Dimension MORE_DIMENSION = new Dimension(0, 500);
    private static volatile GuestSearchFrm guestSearchFrm = null;
    private final static int MAX_ELEMENT = 5;
    private final SearchPanel searchBar = SearchPanel.getInstance();
    private final BriefPaperPanel[] briefPanels = new BriefPaperPanel[MAX_ELEMENT];
    private final int[] entriesShowOption = new int[MAX_ELEMENT];
    private Dimension preferredSize;
    private JPanel container;


    private GuestSearchFrm() {
        this.setSize(1000, 800);
        this.setMinimumSize(new Dimension(800, 400));
        refreshDisplay();
        initLayOut();
        this.setVisible(true);
    }


    private void initLayOut() {
        JScrollPane scrollPane = new JScrollPane();

        container = new JPanel(){
            @Override
            public Dimension getPreferredSize() {
                preferredSize = super.getPreferredSize();
                preferredSize.setSize(scrollPane.getWidth() - 75, (int) preferredSize.getHeight());
                System.out.println("container  " + preferredSize);
                return preferredSize;
            }
        };
        container.setBorder(new EmptyBorder(30, 30, 100, 45));
        initWidgetAndButtonInGridBag(container);

        container.setVisible(true);
        container.setFocusable(true);

        scrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setViewportView(container);
        scrollPane.setVisible(true);


        this.setLayout(new BorderLayout());
        this.add(scrollPane);
    }

    private void initWidgetAndButtonInGridBag(JComponent container) {
        container.setLayout(new GridBagLayout());
        final Insets entryInset = new Insets(20, 0, 20, 0);

        GridBagConstraints gbc = new GridBagConstraints();


        // 设定搜索框的参数，并将搜索框加入
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


        // 设定内容框参数，并将数据框加入
        gbc.ipadx = 200;
        gbc.ipady = 0;
        gbc.insets = entryInset;
        for(int i = 0; i < briefPanels.length; i++) {
            briefPanels[i] = new BriefPaperPanel();

            entriesShowOption[i] = HEIGHT_BRIEF;
            briefPanels[i].getButtonChangeHeight().addActionListener(new ChangeHeightListener(i));
            briefPanels[i].getButtonDetail().addActionListener(new DetailListener(i));
            briefPanels[i].setPreferredSize(BRIEF_DIMENSION);
            gbc.gridy = i + 1;
            container.add(briefPanels[i], gbc);
        }
    }

    public void refreshEntriesDataAndShow() {
        //TODO:刷新各个条目显示的内容，刻意选择先将所有的数据缓存成某种数据类型的对象，再统一交给各个显示条目， 也可以直接交给显示条目

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
        //TODO:刷新各个条目的显示
    }

    static void makeEntryShowMore(int inx) {
        getInstance().briefPanels[inx].setPreferredSize(MORE_DIMENSION);
        getInstance().briefPanels[inx].getButtonChangeHeight().setText("收起");
        getInstance().container.setSize(getInstance().container.getPreferredSize());
    }

    static void makeEntryShowLess(int inx) {
        getInstance().briefPanels[inx].setPreferredSize(BRIEF_DIMENSION);
        getInstance().briefPanels[inx].getButtonChangeHeight().setText("更多");
        getInstance().container.setSize(getInstance().container.getPreferredSize());
    }

    public static void main(String[] args) {
        StyleCtrl.setStyle(StyleCtrl.DARCULA);
        MyFrame testFrame = new MyFrame() {
        };
        testFrame.getTable().add(getInstance());

        testFrame.setVisible(true);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //getInstance().briefPanels[4].setPreferredSize(null);
        //getInstance().container.setSize(getInstance().container.getPreferredSize());

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //getInstance().briefPanels[4].setPreferredSize(new Dimension(0, 500));
        //getInstance().container.setSize(getInstance().container.getPreferredSize());
    }

    private void pageUp() {

    }

    private void pageDown() {

    }
}

class ChangeHeightListener implements ActionListener {
    private final int buttonInx;
    private int showOption = GuestSearchFrm.HEIGHT_BRIEF;

    public ChangeHeightListener(int buttonInx) {
        this.buttonInx = buttonInx;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (showOption) {
            case GuestSearchFrm.HEIGHT_BRIEF -> {
                GuestSearchFrm.makeEntryShowMore(buttonInx);
                showOption = GuestSearchFrm.HEIGHT_MORE;
            }
            case GuestSearchFrm.HEIGHT_MORE -> {
                GuestSearchFrm.makeEntryShowLess(buttonInx);
                showOption = GuestSearchFrm.HEIGHT_BRIEF;
            }
        }
    }
}

class DetailListener implements ActionListener {
    private final int buttonInx;

    public DetailListener(int buttonInx) {
        this.buttonInx = buttonInx;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO:添加点击详细信息按钮的事件响应
    }
}
