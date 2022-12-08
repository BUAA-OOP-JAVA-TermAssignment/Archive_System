package message;

/**
 * �����������󣬷����������
 * �����������Ϊ�գ��򷵻��Ƽ�����
 *
 * @author : AkashiSensei
 * &#064;date  : 2022/12/8 20:21
 */
public class SearchRequestMsg extends BaseMsg{
    private final String searchText;
    private final int offset;
    private final int count;

    private SearchRequestMsg(String searchText, int offset, int count) {
        super(SEARCH_ARCHIVE);
        this.searchText = searchText;
        this.offset = offset;
        this.count = count;
    }

    /**
     * ����SearchRequestMsgʵ��
     * @param searchText ��������
     * @param offset ��ʾ���ؽ��������������������������offset��ֵΪ0����ʾֱ�ӷ���ǰ������������Ϊ10����ӵ�11�������ʼ����
     * @param count ��󷵻صĽ����������һҳ�ܹ���ʾ����Ŀ
     * @return �����½���ʵ��
     */
    public static SearchRequestMsg createSearchRequestMsg(String searchText, int offset, int count) {
        return new SearchRequestMsg(searchText, offset, count);
    }

    public String getSearchText() {
        return searchText;
    }

    public int getOffset() {
        return offset;
    }

    public int getCount() {
        return count;
    }
}
