package data;

import java.util.Date;

// 存放当前用户的数据
public class UserData {
    private static volatile UserData instance;
    private String userName;
    private String id;
    private String email;
    private String password;
    private int downloadCnt;
    private String date;
    private boolean isReady = false;

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

    public void readInfo(String userName,String id, String email, String password, int downloadNum, String date){
        this.userName = userName;
        this.id = id;
        this.email = email;
        this.password = password;
        this.downloadCnt = downloadNum;
        this.date = date;
        this.isReady = true;
    }

    // 每次下载后数量+1
    public void addDownloadNum(){
        this.downloadCnt = this.downloadCnt + 1;
    }

    // 其它内容板块需要获得用户信息时
    public String getUserName(){
        if(isReady){
            return this.userName;
        } else {
            return "游客12138";
        }
    }

    public String getId(){
        if(isReady){
            return this.id;
        } else {
            return "19000000";
        }
    }

    public String getEmail(){
        if(isReady){
            return this.email;
        } else {
            return "NULL";
        }
    }

    public String getPassword(){
        if(isReady){
            return this.password;
        } else {
            return "NULL";
        }
    }

    public int getDownloadNum(){
        if(isReady){
            return this.downloadCnt;
        } else {
            return 0;
        }
    }
    public String getDate(){
        if(isReady){
            return this.date;
        } else {
            return "NULL";
        }
    }

    // 修改密码
    public void changePassword(String newPassword){
        this.password = newPassword;
    }


}
