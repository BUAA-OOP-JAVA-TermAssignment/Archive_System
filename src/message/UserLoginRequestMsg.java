package message;

/**
 * 用户登陆时向主机发送的请求
 *
 * @author : AkashiSensei
 * &#064;date  : 2022/12/8 20:19
 */
public class UserLoginRequestMsg extends BaseMsg{
    private String id;
    private String password;
    private UserLoginRequestMsg(String id, String password) {
        super(LOGIN);
        this.id = id;
        this.password = password;
    }

    /**
     * 创建LoginRequestMsg实例
     * @param id 用户学工号
     * @param password 用户密码
     * @return 返回新建的实例
     */
    public static UserLoginRequestMsg createLoginRequestMsg(String id, String password) {
        return new UserLoginRequestMsg(id, password);
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
