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
     * 单例获取对象实例
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
     * 搜索方法
     *
     * @param keyWord 关键词
     * @param offset  偏移量
     * @param count   查询数量
     * @return List<BookData>
     */
    public List<BookData> search(String keyWord, int offset, int count) {
        List<BookData> list = new ArrayList<>();
        Client client = Client.getMyClient();

        int ret = client.sendMsg(SearchRequestMsg.createSearchRequestMsg(keyWord, offset, count));
        if (ret != 0) {
            System.out.println("搜索信息发送失败");
            return null;
        }
        BaseMsg msg = client.waitMsg();
        if (msg.getMsgCode() != -BaseMsg.SEARCH_ARCHIVE) {
            System.out.println("搜索接收到错误信息");
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
