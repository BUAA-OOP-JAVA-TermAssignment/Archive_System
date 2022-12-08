package message;

/**
 * �û���½ʱ���������͵�����
 *
 * @author : AkashiSensei
 * &#064;date  : 2022/12/8 20:19
 */
public class LoginRequestMsg extends BaseMsg{
    private String id;
    private String password;
    private LoginRequestMsg(String id, String password) {
        super(LOGIN);
        this.id = id;
        this.password = password;
    }

    /**
     * ����LoginRequestMsgʵ��
     * @param id �û�ѧ����
     * @param password �û�����
     * @return �����½���ʵ��
     */
    public static LoginRequestMsg createLoginRequestMsg(String id, String password) {
        return new LoginRequestMsg(id, password);
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
