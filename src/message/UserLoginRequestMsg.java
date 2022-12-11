package message;

/**
 * �û���½ʱ���������͵�����
 *
 * @author : AkashiSensei
 * &#064;date  : 2022/12/8 20:19
 */
public class UserLoginRequestMsg extends BaseMsg {
    public static final int GUEST = 1, ADMIN = 2;
    private int userType;
    private String id;
    private String password;

    private UserLoginRequestMsg(int userType, String id, String password) {
        super(LOGIN);
        this.userType = userType;
        this.id = id;
        this.password = password;
    }

    /**
     * ����LoginRequestMsgʵ��
     *
     * @param id       �û�ѧ����
     * @param password �û�����
     * @return �����½���ʵ��
     */
    public static UserLoginRequestMsg createLoginRequestMsg(int userType, String id, String password) {
        return new UserLoginRequestMsg(userType, id, password);
    }

    public int getUserType() {
        return userType;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
