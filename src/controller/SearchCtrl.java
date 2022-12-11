package controller;

import client.Client;
import data.BookData;
import message.BaseMsg;
import message.SearchRequestMsg;
import message.SearchReturnMsg;

import java.util.ArrayList;
import java.util.List;

public class SearchCtrl {

    private static SearchCtrl searchCtrl;

    private SearchCtrl() {
    }

    /**
     * ������ȡ����ʵ��
     *
     * @return
     */
    public static SearchCtrl getInstance() {
        if (searchCtrl == null) {
            searchCtrl = new SearchCtrl();
        }
        return searchCtrl;
    }

    /**
     * ��������
     *
     * @param keyWord �ؼ���
     * @param offset  ƫ����
     * @param count   ��ѯ����
     * @return List<BookData>
     */
    public List<BookData> search(String keyWord, int offset, int count) {
        List<BookData> list = new ArrayList<>();
        Client client = Client.getMyClient();

        int ret = client.sendMsg(SearchRequestMsg.createSearchRequestMsg(keyWord, offset, count));
        if (ret != 0) {
            System.out.println("������Ϣ����ʧ��");
            return null;
        }
        BaseMsg msg = client.waitMsg();
        if (msg.getMsgCode() != -BaseMsg.SEARCH_ARCHIVE) {
            System.out.println("�������յ�������Ϣ");
            return null;
        }
        SearchReturnMsg sr = (SearchReturnMsg) msg;
        for (int i = 0; i < count; i++) {
            list.add(new BookData(sr.getBookId(i),
                    sr.getBookName(i),
                    sr.getBookAuthor(i),
                    sr.getBookMatchedText(i),
                    sr.getBookDownloadCnt(i),
                    sr.gteBookMatchedScore(i)
            ));
        }
        return list;
    }

}
