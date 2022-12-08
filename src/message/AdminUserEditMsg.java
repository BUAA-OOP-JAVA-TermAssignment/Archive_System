package message;

/**
 * 管理员请求操作用户信息的消息
 *
 * @author : AkashiSensei
 * &#064;date  : 2022/12/8 20:57
 */
public class AdminUserEditMsg extends BaseMsg{
    public static final int REQUEST_INFO = 0;

    private final int adminUserEditCode;
    //TODO:还没有设计具体包含什么
    public AdminUserEditMsg(int adminUserEditCode) {
        super(ADMIN_USER_EDIT);
        this.adminUserEditCode = adminUserEditCode;
    }
}
