package message;

import data.UserData;

import java.util.ArrayList;

public class AdminUserRequestMsg extends BaseMsg{
    public static final int REQUEST_INFO = 0;

    private ArrayList<User> userArrayList = new ArrayList<>(){{
        add(new User("ºº×Ö", "12345678", "wefwe", 3, "123//33", "cdcdcd@qwe"));
        add(new User("ºº×Ö", "12345678", "wefwe", 3, "123//33", "cdcdcd@qwe"));
        add(new User("ºº×Ö", "12345678", "wefwe", 3, "123//33", "cdcdcd@qwe"));
        add(new User("ºº×Ö", "12345678", "wefwe", 3, "123//33", "cdcdcd@qwe"));
        add(new User("ºº×Ö", "12345678", "wefwe", 3, "123//33", "cdcdcd@qwe"));
        add(new User("ºº×Ö", "12345678", "wefwe", 3, "123//33", "cdcdcd@qwe"));
        add(new User("ºº×Ö", "12345678", "wefwe", 3, "123//33", "cdcdcd@qwe"));
        add(new User("ºº×Ö", "12345678", "wefwe", 3, "123//33", "cdcdcd@qwe"));
        add(new User("ºº×Ö", "12345678", "wefwe", 3, "123//33", "cdcdcd@qwe"));
        add(new User("ºº×Ö", "12345678", "wefwe", 3, "123//33", "cdcdcd@qwe"));
        add(new User("ºº×Ö", "12345678", "wefwe", 3, "123//33", "cdcdcd@qwe"));
    }};


    class User {
        private String userName;
        private String id;
        private String password;
        private int downloadCnt;
        private String date;
        private String email;

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

    private AdminUserRequestMsg(ArrayList<User> userArrayList) {
        super(ADMIN_USER_REQUEST);
        this.userArrayList = userArrayList;
    }

    public AdminUserRequestMsg createAdminUserRequestMsg(ArrayList<User> userArrayList){
        return new AdminUserRequestMsg(userArrayList);
    }



    public int getUserNum(){
        if(userArrayList == null){
            return 0;
        }
        return userArrayList.size();
    }
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

    public String getUserDate(User user){
        return user.date;
    }
}
