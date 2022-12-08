package message;

/**
 * 管理员编辑文档的操作消息
 *
 * @author : AkashiSensei
 * @date : 2022/12/8 21:00
 */
public class AdminArchiveEditMsg extends BaseMsg{
    public static final int REQUEST_INFO = 0;
    private final int adminArchiveEditCode;
    //TODO:还没有设计具体包含什么

    public AdminArchiveEditMsg(int adminArchiveEditCode) {
        super(ADMIN_ARCHIVE_EDIT);
        this.adminArchiveEditCode = adminArchiveEditCode;
    }
}
