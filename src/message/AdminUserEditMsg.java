package message;

/**
 * ����Ա��������û���Ϣ����Ϣ
 *
 * @author : AkashiSensei
 * &#064;date  : 2022/12/8 20:57
 */
public class AdminUserEditMsg extends BaseMsg{
    public static final int REQUEST_INFO = 0;

    private final int adminUserEditCode;
    //TODO:��û����ƾ������ʲô
    public AdminUserEditMsg(int adminUserEditCode) {
        super(ADMIN_USER_EDIT);
        this.adminUserEditCode = adminUserEditCode;
    }
}
