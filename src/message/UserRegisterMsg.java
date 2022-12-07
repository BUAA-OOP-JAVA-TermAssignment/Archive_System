package message;

import java.util.Date;

public class UserRegisterMsg {
    private int id;
    private String userName;
    private String password;
    private String email;
    private Date createTime;

    public UserRegisterMsg(int id, String userName, String password, String email, Date createTime){
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.createTime = createTime;
    }
    private int getId(){return id;}
    public String getUserName(){return userName;}
    public String getPassword(){return password;}
    public String getEmail(){return email;}
    public Date getCreateTime(){return createTime;}
}
