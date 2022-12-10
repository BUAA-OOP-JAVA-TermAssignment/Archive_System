package view;

public class GuestMainFrm extends MyFrame{
    private static final GuestMainFrm guestSearchFrm = new GuestMainFrm();
    private GuestMainFrm() {
        //TODO:添加添加用户独有的菜单啥的
    }

    public static GuestMainFrm getInstance() {
        return guestSearchFrm;
    }
}
