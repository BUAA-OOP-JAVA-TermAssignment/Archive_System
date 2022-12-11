package data;

import message.AdminUserRequestMsg;

import java.util.ArrayList;

public class AdminData {
    private static volatile AdminData instance;
    private ArrayList<User> userArrayList = new ArrayList<>();

    private AdminData(){

    }

    public class User {
        String userName;
        String id;
        String password;
        int downloadCnt;
        String date;
        String email;

        /**
         * Òª×¢ÊÍµô£¡
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
    public void add(String id, String userName, String password, String email, int downloadCnt, String date){
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
}
