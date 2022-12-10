package message;

import java.util.ArrayList;
import java.util.Date;

/**
 * �������������Ƽ������
 * �������صĽ������Ŀ���Լ������ĵ���Ҫ��Ϣ��
 * ��Ҫ��Ϣ���������⣬���ߣ��ؼ��ʺ�ժҪ
 *
 * @author : AkashiSensei
 * &#064;date : 2022/12/8 22:04
 */
public class SearchReturnMsg extends BaseMsg{

    private ArrayList<Book> bookArrayList;
    class Book{
        private String Id;
        private String name;
        private String author;
        private String matchedText;
        private int matchedCnt;
    }

    public SearchReturnMsg(ArrayList<Book> bookArrayList) {
        super(- SEARCH_ARCHIVE);
        this.bookArrayList = bookArrayList;
    }

    public static SearchReturnMsg createSearchReturnMsg(ArrayList<Book> bookArrayList){
        return new SearchReturnMsg(bookArrayList);
    }

    public int getBookNum(){return bookArrayList.size();}
    public Book getBookInfo(int i){return bookArrayList.get(i);}
    public String getBookId(SearchReturnMsg.Book book){return book.Id;}
    public String getBookName(SearchReturnMsg.Book book){return book.name;}
    public String getBookAuthor(SearchReturnMsg.Book book){return book.author;}

    public String getBookId(int inx) {
        return bookArrayList.get(inx).Id;
    }
    public String getBookName(int inx) {
        return bookArrayList.get(inx).name;
    }
    public String getBookAuthor(int inx) {
        return bookArrayList.get(inx).author;
    }
    public String getBookMatchedText(int inx) {
        return bookArrayList.get(inx).matchedText;
    }
    public int getBookMatchedCnt(int inx) {
        return bookArrayList.get(inx).matchedCnt;
    }
}
