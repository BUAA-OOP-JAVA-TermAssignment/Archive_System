package data;

import message.AdminUserRequestMsg;

import java.util.ArrayList;

public class AdminData {
    private static volatile AdminData instance;
    /**
     * 删掉static！！！！和下面的User一起删
     */
    private static ArrayList<User> userArrayList = new ArrayList<>();

    private AdminData(){

    }

    public static class User {
        String userName;
        String id;
        String password;
        int downloadCnt;
        String date;
        String email;

        /**
         * 要注释掉！
         */
        User(String userName, String id, String password, int downloadCnt, String date,String email){
            this.userName = userName;
            this.id = id;
            this.password = password;
            this.downloadCnt = downloadCnt;
            this.date = date;
            this.email = email;
        }
    }

    public static AdminData getInstance(){
        if(instance == null){
            synchronized (UserData.class){
                if(instance == null){
                    instance = new AdminData();
                }
            }
        }
        return instance;
    }

    public int getUserNum(){
        if(userArrayList == null){
            return 0;
        }
        return userArrayList.size();
    }
    public void add(String id, String userName, String password, int downloadCnt, String email, String date){
        userArrayList.add(new User(userName,id,password,downloadCnt,date,email));
    }
    public User getUserInfo(int i){
        return userArrayList.get(i);
    }

    public String getUserName(int idx){
        return userArrayList.get(idx).userName;
    }

    public String getUserId(int idx){
        return userArrayList.get(idx).id;
    }

    public String getUserEmail(int idx){
        return userArrayList.get(idx).email;
    }

    public String getUserPassword(int idx){
        return userArrayList.get(idx).password;
    }

    public int getUserDownloadCnt(int idx){
        return userArrayList.get(idx).downloadCnt;
    }

    public String getUserDate(int idx){
        return userArrayList.get(idx).date;
    }

    public void ChangeDownloadCnt(int idx){
        userArrayList.get(idx).downloadCnt = 0;
    }

    public void ChangePassword(int idx, String password){
        userArrayList.get(idx).password = password;
    }

    public void ChangeEmail(int idx, String email){
        userArrayList.get(idx).email = email;
    }
}
