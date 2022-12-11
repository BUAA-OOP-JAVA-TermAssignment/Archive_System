package message;

/**
 * �û��޸��Լ��ĸ�����Ϣʱ���͵����ݡ�
 * �û��޸ĸ�����Ϣ֮ǰ���������������������Ҫ�󣬵���ʱӦ�ý��û�����������뱾�ر��������ȶԣ�����Ҫ���ʷ�����
 * ���͵����ݽ������µ����룬�û����䣬Ҳ���԰����û�����������
 * ��Ӧ�ķ�����Ϣ�ǳɹ�����Լ�������������͡�
 *
 * @author : AkashiSensei
 * @date : 2022/12/8 20:53
 */
public class ModifyUserInfo extends BaseMsg {
    private final String id;
    private final String password;
    private final String email;
    private final int downloadCnt;


    private ModifyUserInfo(String id, String password, String email, int downloadCnt) {
        super(MODIFY_USER_INFO);
        this.id = id;
        this.password = password;
        this.email = email;
        this.downloadCnt = downloadCnt;
    }

    /**
     * ����ModifyUserInfo��ʵ��
     *
     * @param password    ���룬ֱ�Ӹ��£����û���޸Ļ�ѾɵĴ�����
     * @param email       ��������
     * @param downloadCnt ������
     * @return �����½���ʵ��
     */
    public static ModifyUserInfo createModifyUserInfo(String id, String password, String email, int downloadCnt) {
        return new ModifyUserInfo(id, password, email, downloadCnt);
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

    public int getDownloadCnt() {
        return downloadCnt;
    }
}
