package message;

/**
 * ����Ա�༭�ĵ��Ĳ�����Ϣ
 *
 * @author : AkashiSensei
 * @date : 2022/12/8 21:00
 */
public class AdminArchiveEditMsg extends BaseMsg{
    public static final int REQUEST_INFO = 0;
    private final int adminArchiveEditCode;
    //TODO:��û����ƾ������ʲô

    public AdminArchiveEditMsg(int adminArchiveEditCode) {
        super(ADMIN_ARCHIVE_EDIT);
        this.adminArchiveEditCode = adminArchiveEditCode;
    }
}
