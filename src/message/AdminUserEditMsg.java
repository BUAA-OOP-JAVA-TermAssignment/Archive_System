package message;

import java.util.*;

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

    private ArrayList<User> userArrayList;
    class User {
        private String userName;
        private String id;
        private String password;
        private int downloadCnt;
        private Date date;
        private String email;

    }


    private AdminUserEditMsg(int adminUserEditCode,ArrayList<User> userArrayList) {
        super(ADMIN_USER_EDIT);
        this.adminUserEditCode = adminUserEditCode;
        this.userArrayList = userArrayList;
    }

    public static AdminUserEditMsg createAdminUserEditMsg(int adminUserEditCode, ArrayList<User> userArrayList) {
        return new AdminUserEditMsg(adminUserEditCode,userArrayList);
    }

    public int getUserNum(){return userArrayList.size();}
    public User getUserInfo(int i){
        return userArrayList.get(i);
    }

    public String getUserName(User user){
        return user.userName;
    }

    public String getUserId(User user){
        return user.id;
    }

    public String getUserEmail(User user){
        return user.email;
    }

    public String getUserPassword(User user){
        return user.password;
    }

    public int getUserDownloadCnt(User user){
        return user.downloadCnt;
    }

    public Date getUserDate(User user){
        return user.date;
    }
}
