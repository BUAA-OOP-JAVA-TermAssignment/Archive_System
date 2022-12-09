package message;

import java.util.Date;

/**
 * 登录成功返回的信息
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
     * 创建LoginReturnMsg实例
     * @param downloadCnt 用户总计下载量，这个值由客户端统计，服务器只需要记录这个值
     * @param email 用户邮箱
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
