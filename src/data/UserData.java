package data;

// 存放当前用户的数据
public class UserData {
    private static volatile UserData instance;
    private String userName;
    private String id;
    private String email;
    private String password;
    private String downloadNum;

    private UserData(){

    }
    // 双重检查锁定
    public static UserData getInstance(){
        if(instance == null){
            synchronized (UserData.class){
                if(instance == null){
                    instance = new UserData();
                }
            }
        }
        return instance;
    }

    public void readInfo(String userName,String id, String email, String password, String downloadNum){
        this.userName = userName;
        this.id = id;
        this.email = email;
        this.password = password;
        this.downloadNum = downloadNum;
    }

    // 每次下载后数量+1
    public void addDownloadNum(){
        this.downloadNum = this.downloadNum + 1;
    }

    // 其它内容板块需要获得用户信息时
    public String[] getInfo(){
        String[] info = new String[5];
        info[0] = this.userName;
        info[1] = this.id;
        info[2] = this.email;
        info[3] = this.password;
        info[4] = this.downloadNum;
        return info;
    }

    // 修改密码
    public void changePassword(String newPassword){
        this.password = newPassword;
    }


}
