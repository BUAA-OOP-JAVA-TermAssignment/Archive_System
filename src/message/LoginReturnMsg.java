package message;

import java.util.Date;

/**
 * ��¼�ɹ����ص���Ϣ
 *
 * @author : AkashiSensei
 * &#064;date  : 2022/12/8 21:02
 */
public class LoginReturnMsg extends BaseMsg{
    private final int downloadCnt;
    //private final Date date;
    private final String email;


    private LoginReturnMsg(int downloadCnt, String email) {
        super(- LOGIN);
        this.downloadCnt = downloadCnt;
        this.email = email;
    }


    /**
     * ����LoginReturnMsgʵ��
     * @param downloadCnt �û��ܼ������������ֵ�ɿͻ���ͳ�ƣ�������ֻ��Ҫ��¼���ֵ
     * @param email �û�����
     */
    public static LoginReturnMsg createLoginReturnMsg(int downloadCnt, String email) {
        return new LoginReturnMsg(downloadCnt, email);
    }

    public int getDownloadCnt() {
        return downloadCnt;
    }

    public String getEmail() {
        return email;
    }
}
