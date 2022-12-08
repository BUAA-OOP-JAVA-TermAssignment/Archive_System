package message;

import java.lang.invoke.StringConcatFactory;

/**
 * ��¼������Ϣ������������id�����룬����
 * ���سɹ���񼴿�
 *
 * @author : AkashiSensei
 * &#064;date  : 2022/12/8 20:20
 */
public class RegisterRequestMsg extends BaseMsg{
    private final String name;
    private final String id;
    private final String password;
    private final String email;

    private RegisterRequestMsg(String name, String id, String password, String email) {
        super(REGISTER);
        this.name = name;
        this.id = id;
        this.password = password;
        this.email = email;
    }


    /**
     * ����RegisterRequestMsg��ʵ��
     * @param name �û�����
     * @param id ѧ����
     * @param password ����
     * @param email ��������
     * @return �����½���ʵ��
     */
    public static RegisterRequestMsg createRegisterRequestMsg(String name, String id, String password, String email) {
        return new RegisterRequestMsg(name, id, password, email);
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
