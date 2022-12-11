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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GuestSearchFrm extends MyInterFrame {
    public final static int HEIGHT_BRIEF = 0, HEIGHT_MORE = 1;
    private final static Dimension BRIEF_DIMENSION = new Dimension(0, 300);
    private final static Dimension MORE_DIMENSION = new Dimension(0, 500);
    private static volatile GuestSearchFrm guestSearchFrm = null;
    private final static int MAX_ELEMENT = 5;
    private final SearchPanel searchBar = SearchPanel.getInstance();
    private final BriefPaperPanel[] briefPanels = new BriefPaperPanel[MAX_ELEMENT];
    private final JScrollPane scrollPane = new JScrollPane();
    private Dimension preferredSize;
    private JPanel container;
    private String searchText;
    private int offset = 0;


    private GuestSearchFrm() {
        this.setSize(1000, 800);
        this.setMinimumSize(new Dimension(800, 400));
        initLayOut();
        this.setVisible(true);
    }


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

    private void initWidgetAndButtonInGridBag(JComponent container) {
        container.setLayout(new GridBagLayout());
        final Insets entryInset = new Insets(20, 0, 20, 0);

        GridBagConstraints gbc = new GridBagConstraints();

        initSearchBarButton();

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
            //briefPanels[i].setVisible(true);

            briefPanels[i].getButtonChangeHeight().addActionListener(new ChangeHeightListener(i));
            briefPanels[i].setPreferredSize(BRIEF_DIMENSION);
            gbc.gridy = i + 1;
            container.add(briefPanels[i], gbc);
        }
    }

    private void hideAllEntries() {
        for(int i = 0; i < briefPanels.length; i++) {
            briefPanels[i].setVisible(false);
        }
    }

    /**
     * ˢ����ʾ���ݣ�������Ŀ�����Ļ��������ʾ��
     * @param returnMsg �ӷ��������ص����������Ϣ
     */
    public void refreshEntriesData(SearchReturnMsg returnMsg) {
        int bookNum = returnMsg.getBookNum();
        for(int i = 0; i < bookNum; i++) {
            briefPanels[i].getTitleLabel().setText(returnMsg.getBookName(i));
            briefPanels[i].getAuthorLabel().setText(returnMsg.getBookAuthor(i));
            // ����û����ؼ��ʣ��˴���ʾ�ĵ����
            briefPanels[i].getKeywordsLabel().setText(returnMsg.getBookId(i));
            briefPanels[i].getAbstractTextArea().setText(returnMsg.getBookMatchedText(i));
            briefPanels[i].getCntLabel().setText("ȫ��ƥ��" + returnMsg.getBookMatchedText(i) + "��");
            briefPanels[i].getButtonDetail().setText("����...");

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
     * Ϊ�������ĸ�����ť�󶨰�ť�¼���
     * ���������а��»س��ᴥ��������ť��
     * ����������ť�����������������searchText��ͬ��Ϊ��ʱ���ᴥ��������
     * ֻ�е�����������ɹ�����֮��Ż����searchText��
     * ��ҳ���߼����·�����ҳ�¼��ķ�����
     */
    private void initSearchBarButton() {
        // ������ť���߼���ֻ�е�����������ɹ�����֮��Ż����searchText
        searchBar.getSearchButton().addActionListener(actionEvent -> {
            System.out.println("GuestSearchFrm : search clicked");
            if(searchBar.getTextField().getText().length() == 0) {
                searchBar.emptySearchText();
                return;
            }

            if(searchBar.getTextField().getText().equals(searchText)) {
                return;
            }

            // ����������Ĭ�ϵ�һҳ
            GuestMainCtrl.trySearch(searchBar.getTextField().getText(), 0, briefPanels.length);
        });

        searchBar.getTextField().addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    searchBar.getSearchButton().doClick();
                }
            }
        });

        // �Ϸ�ҳ
        searchBar.getUpButton().addActionListener(actionEvent -> {
            System.out.println("GuestSearchFrm : page up");
            pageUp();
        });
        searchBar.getUpButton().setEnabled(false);

        // �·�ҳ
        searchBar.getDownButton().addActionListener(actionEvent -> {
            System.out.println("GuestSearchFrm : page down");
            pageDown();
        });
        searchBar.getDownButton().setEnabled(false);
    }

    static void makeEntryShowMore(int inx) {
        getInstance().briefPanels[inx].setPreferredSize(MORE_DIMENSION);
        getInstance().briefPanels[inx].getButtonChangeHeight().setText("����");
        getInstance().container.setSize(getInstance().container.getPreferredSize());
    }

    static void makeEntryShowLess(int inx) {
        getInstance().briefPanels[inx].setPreferredSize(BRIEF_DIMENSION);
        getInstance().briefPanels[inx].getButtonChangeHeight().setText("����");
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
     * ��һҳ�¼���
     * ��һҳ����һҳ��ť�����ܴ���searchText�ĸ��¡�
     * ������һҳ�����Բ�ͬ��offset������
     */
    private void pageUp() {
        if(offset == 0) {
            System.out.println("!!!GuestMainCtrl : page up illegal offset");
            return;
        }
        GuestMainCtrl.trySearch(searchText, offset -= briefPanels.length, briefPanels.length);
    }

    /**
     * ��һҳ�¼���
     * ��һҳ����һҳ��ť�����ܴ���searchText�ĸ��¡�
     * ������һҳ�����Բ�ͬ��offset������
     */
    private void pageDown() {
        if(briefPanels[briefPanels.length - 1] != null && !briefPanels[briefPanels.length - 1].isVisible()) {
            System.out.println("!!!GuestMainCtrl : page down illegal show cnt");
        }
        GuestMainCtrl.trySearch(searchText, offset += briefPanels.length, briefPanels.length);
    }

    /**
     * ����ҳ���еĸ�����ť����Ϊ���ɵ����
     */
    @Override
    public void enWaitMode() {
        searchBar.getUpButton().setEnabled(false);
        searchBar.getDownButton().setEnabled(false);
        searchBar.getSearchButton().setEnabled(false);

        for(int i = 0; i < briefPanels.length; i++) {
            if(briefPanels[i] != null) {
                briefPanels[i].getButtonDetail().setEnabled(false);
            }
        }
    }

    /**
     * ������ɵ��״̬��
     * ������һ�������ݣ�����Ϊ����ʹ����һҳ������
     * ���offset �� 0�������ʹ����һҳ������
     */
    @Override
    public void disWaitMode() {
        for (BriefPaperPanel briefPanel : briefPanels) {
            if (briefPanel != null) {
                // �������İ���˵Ӧ�ò��ɵ����������������
                briefPanel.getButtonDetail().setEnabled(true);
            }
        }

        searchBar.getDownButton().setEnabled(briefPanels[briefPanels.length - 1].isVisible());
        if(offset > 0)
            searchBar.getUpButton().setEnabled(true);
        searchBar.getSearchButton().setEnabled(true);
    }

    public void connectError() {
        searchBar.connectError();
    }

    public void timeoutError() {
        searchBar.timeoutError();
    }

    public void undefinedFailed() {
        searchBar.undefinedFailed();
    }

    /**
     * ���ӷ������õ��ɹ����صĽ��֮�󣬽������������Ļ��
     * ͬʱ���±���������ı��Լ�ƫ������
     * �����������������ݵ��޸ģ�������ǰ����������������������ʾ��
     * @param returnMsg ���������ص���Ϣ
     * @param searchText ����������͵�������Ϣ���ɹ����غ��䱣������ǰ�������ݣ�����ҳ���á�
     * @param offset ��ǰ�����ƫ����������ҳλ�á�
     */
    public void searchSuccess(SearchReturnMsg returnMsg, String searchText, int offset) {
        this.searchText = searchText;
        this.offset = offset;
        refreshEntriesData(returnMsg);
        searchBar.searchSuccess();
        searchBar.getTextField().setText(searchText);
    }

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
