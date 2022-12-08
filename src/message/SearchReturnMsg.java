package message;

/**
 * 返回搜索或者推荐结果。
 * 包含返回的结果的数目，以及各份文档简要信息。
 * 简要信息包括，标题，作者，关键词和摘要
 *
 * @author : AkashiSensei
 * &#064;date : 2022/12/8 22:04
 */
public class SearchReturnMsg extends BaseMsg{
    //TODO:没开始写呢

    public SearchReturnMsg() {
        super(- SEARCH_ARCHIVE);
    }
}
