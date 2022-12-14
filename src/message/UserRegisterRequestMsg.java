package message;

/**
 * 登录请求信息，包含姓名，id，密码，邮箱
 * 返回成功与否即可
 *
 * @author : AkashiSensei
 * &#064;date  : 2022/12/8 20:20
 */
public class UserRegisterRequestMsg extends BaseMsg {
    private final String name;
    private final String id;
    private final String password;
    private final String email;

    private UserRegisterRequestMsg(String name, String id, String password, String email) {
        super(REGISTER);
        this.name = name;
        this.id = id;
        this.password = password;
        this.email = email;
    }


    /**
     * 创建RegisterRequestMsg的实例
     *
     * @param name     用户姓名
     * @param id       学工号
     * @param password 密码
     * @param email    电子邮箱
     * @return 返回新建的实例
     */
    public static UserRegisterRequestMsg createRegisterRequestMsg(String name, String id, String password, String email) {
        return new UserRegisterRequestMsg(name, id, password, email);
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
