package view;

import controller.GuestMainCtrl;
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
    public final static int MAX_ELEMENT = 5;
    private final SearchPanel searchBar = SearchPanel.getInstance();
    private final BriefPaperPanel[] briefPanels = new BriefPaperPanel[MAX_ELEMENT];
    private final JScrollPane scrollPane = new JScrollPane();
    private Dimension preferredSize;
    private JPanel container;
    private String searchText = "";
    private int offset = 0;


    private GuestSearchFrm() {
        this.setSize(1000, 800);
        this.setTitle("搜索");
        this.setMinimumSize(new Dimension(800, 400));
        initLayOut();
        this.setVisible(true);

    }


    /**
     * 初始化用户搜索窗口的页面布局。
     * 首先创建容器，并重写容器的getPreferredSize()，以避免其在滚动窗口中无限扩张。
     * 之后调用initWidgetAndButtonInGridBag(JComponent container)填充容器。
     * 最后将其设置为至滚动窗口的ViewportView。
     */
    private void initLayOut() {
        container = new JPanel(){
            @Override
            public Dimension getPreferredSize() {
                preferredSize = super.getPreferredSize();
                preferredSize.setSize(scrollPane.getWidth() - 75, (int) preferredSize.getHeight());
                //System.out.println("container  " + preferredSize);
                return preferredSize;
            }
        };
        container.setBorder(new EmptyBorder(30, 30, 100, 45));
        initWidgetAndButtonInGridBag(container);

        hideAllEntries();

        container.setVisible(true);
        container.setFocusable(true);

        scrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setViewportView(container);
        scrollPane.setVisible(true);


        this.setLayout(new BorderLayout());
        this.add(scrollPane);
    }


    /**
     * 在所给的容器中应用GridBagLayout，并将该InternalFrame所需要的组件加入其中。
     * 组件纵向生长，从上至下依次是搜索栏，以及各个搜索结果的显示条目。
     * 各个组件均能够适应窗口的横向增长，但对于纵向增长则不会改变自己的大小。
     * 此方法调用另外的方法用于为搜索栏的按钮绑定事件响应。
     * 此方法添加内部类作为显示条目改变高度的事件响应。
     * @param container 将会把各个组件添加至此容器中
     */
    private void initWidgetAndButtonInGridBag(JComponent container) {
        container.setLayout(new GridBagLayout());
        final Insets entryInset = new Insets(20, 0, 20, 0);

        GridBagConstraints gbc = new GridBagConstraints();

        initSearchBarButton();

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
            briefPanels[i] = BriefPaperPanel.createBriefPaperPanel();
            //briefPanels[i].setVisible(true);

            briefPanels[i].getButtonChangeHeight().addActionListener(new ChangeHeightListener(i));
            briefPanels[i].setPreferredSize(BRIEF_DIMENSION);
            gbc.gridy = i + 1;
            container.add(briefPanels[i], gbc);
        }
    }

    private void hideAllEntries() {
        for (BriefPaperPanel briefPanel : briefPanels) {
            briefPanel.setVisible(false);
        }
    }

    /**
     * 刷新显示内容，数据条目不够的话其余项不显示。
     * @param returnMsg 从服务器返回的搜索结果消息
     */
    public void refreshEntriesData(SearchReturnMsg returnMsg, boolean isSuggest) {
        int bookNum = returnMsg.getBookNum();
        if(bookNum == 0)
            searchBar.emptySearchResult();

        for(int i = 0; i < bookNum; i++) {
            briefPanels[i].getTitleLabel().setText(returnMsg.getBookName(i));
            briefPanels[i].getAuthorLabel().setText(returnMsg.getBookAuthor(i));
            // 由于没保存关键词，此处显示文档编号
            briefPanels[i].getKeywordsLabel().setText(returnMsg.getBookId(i));
            briefPanels[i].getAbstractTextArea().setText(returnMsg.getBookMatchedText(i));
            if(isSuggest)
                briefPanels[i].getCntLabel().setText("被下载" + returnMsg.getBookDownloadCnt(i) + "次");
            else
                briefPanels[i].getCntLabel().setText("全文匹配值：" + returnMsg.gteBookMatchedScore(i));
            briefPanels[i].getButtonDetail().setText("下载...");

            briefPanels[i].setVisible(true);
        }
        for(int i = bookNum; i < briefPanels.length; i++) {
            briefPanels[i].setVisible(false);
        }
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


    /**
     * 为搜索栏的各个按钮绑定按钮事件。
     * 在搜索框中按下回车会触发搜索按钮。
     * 对于搜索按钮，当输入框中内容与searchText相同或为空时不会触发搜索。
     * 只有点击了搜索并成功返回之后才会更新searchText。
     * 翻页的逻辑见下方处理翻页事件的方法。
     */
    private void initSearchBarButton() {
        // 搜索按钮的逻辑，只有点击了搜索并成功返回之后才会更新searchText
        searchBar.getSearchButton().addActionListener(actionEvent -> {
            System.out.println("GuestSearchFrm : search clicked");
            if(searchBar.getTextField().getText().equals(searchText)) {
                if(searchText.length() != 0)
                    searchBar.sameSearchText();
                return;
            }

            // 新搜索内容默认第一页
            GuestMainCtrl.trySearch(searchBar.getTextField().getText(), 0, briefPanels.length);
        });


        // 上翻页
        searchBar.getUpButton().addActionListener(actionEvent -> {
            System.out.println("GuestSearchFrm : page up");
            pageUp();
        });
        searchBar.getUpButton().setEnabled(false);

        // 下翻页
        searchBar.getDownButton().addActionListener(actionEvent -> {
            System.out.println("GuestSearchFrm : page down");
            pageDown();
        });
        searchBar.getDownButton().setEnabled(false);

        searchBar.showPrepareSuggest();
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

    /**
     * 上一页事件。
     * 上一页和下一页按钮均不能触发searchText的更新。
     * 触发之后，将以不同的offset值发送新的搜索消息。
     */
    private void pageUp() {
        if(offset == 0) {
            System.out.println("!!!GuestMainCtrl : page up illegal offset");
            return;
        }
        GuestMainCtrl.trySearch(searchText, offset -= briefPanels.length, briefPanels.length);
    }

    /**
     * 下一页事件。
     * 上一页和下一页按钮均不能触发searchText的更新。
     * 触发之后，将以不同的offset值发送新的搜索消息。
     */
    private void pageDown() {
        if(briefPanels[briefPanels.length - 1] != null && !briefPanels[briefPanels.length - 1].isVisible()) {
            System.out.println("!!!GuestMainCtrl : page down illegal show cnt");
        }
        GuestMainCtrl.trySearch(searchText, offset += briefPanels.length, briefPanels.length);
    }

    /**
     * 将该页面中的各个按钮均设为不可点击。
     */
    @Override
    public void enWaitMode() {
        searchBar.getUpButton().setEnabled(false);
        searchBar.getDownButton().setEnabled(false);
        searchBar.getSearchButton().setEnabled(false);

        for (BriefPaperPanel briefPanel : briefPanels) {
            if (briefPanel != null) {
                briefPanel.getButtonDetail().setEnabled(false);
            }
        }
    }

    /**
     * 解除不可点击状态。
     * 如果最后一项有内容，则认为可以使用下一页操作。
     * 如果offset > 0，则可以使用上一页操作。
     */
    @Override
    public void disWaitMode() {
        for (BriefPaperPanel briefPanel : briefPanels) {
            if (briefPanel != null) {
                // 看不到的按理说应该不可点击，但反正看不到
                briefPanel.getButtonDetail().setEnabled(true);
            }
        }

        searchBar.getDownButton().setEnabled(briefPanels[briefPanels.length - 1].isVisible());
        if(offset > 0)
            searchBar.getUpButton().setEnabled(true);
        searchBar.getSearchButton().setEnabled(true);
    }

    /**
     * 处理当网络连接中断时对用户的显示
     */
    public void connectError() {
        searchBar.connectError();
    }

    /**
     * 处理当网络连接超时时对用户的显示
     */
    public void timeoutError() {
        searchBar.timeoutError();
    }

    /**
     * 处理当遇到错误时对用户的显示
     */
    public void undefinedFailed() {
        searchBar.undefinedFailed();
    }

    /**
     * 当从服务器拿到成功返回的结果之后，将结果更新至屏幕。
     * 同时更新保存的搜索文本以及偏移量。
     * 假如当前的搜索内容为空，则认为是推荐内容，调用相应提示。
     * 将会无视搜索框内容的修改，并将当前的搜索内容在搜索框中显示。
     * @param returnMsg 服务器返回的消息
     * @param searchText 向服务器发送的搜索消息，成功返回后将其保存至当前搜索内容，供翻页调用。
     * @param offset 当前结果的偏移量，代表翻页位置。
     */
    public void searchSuccess(SearchReturnMsg returnMsg, String searchText, int offset) {
        this.searchText = searchText;
        this.offset = offset;
        // 搜索内容为空串则代表是推荐内容
        if(searchText.length() == 0) {
            searchBar.showSuggest();
            refreshEntriesData(returnMsg, true);
        }else {
            searchBar.searchSuccess();
            refreshEntriesData(returnMsg, false);
        }
        searchBar.getTextField().setText(searchText);
    }

    /**
     * 处理当等待服务器响应时对用户的显示
     */
    public void sendMsgNotice() {
        searchBar.sendMsgNotice();
    }

    static class ChangeHeightListener implements ActionListener {
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
}
