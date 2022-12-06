package data;

// ��ŵ�ǰ�û�������
public class UserData {
    private static volatile UserData instance;
    private String userName;
    private String id;
    private String email;
    private String password;
    private String downloadNum;
    private boolean isReady = false;

    private UserData(){

    }
    // ˫�ؼ������
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
        this.isReady = true;
    }

    // ÿ�����غ�����+1
    public void addDownloadNum(){
        this.downloadNum = this.downloadNum + 1;
    }

    // �������ݰ����Ҫ����û���Ϣʱ
    public String getUserName(){
        if(isReady){
            return this.userName;
        } else {
            return "�ο�12138";
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

    public String getDownloadNum(){
        if(isReady){
            return this.downloadNum;
        } else {
            return "NULL";
        }
    }

    // �޸�����
    public void changePassword(String newPassword){
        this.password = newPassword;
    }


}
