package view;

public class GuestMainFrm extends MyFrame{
    private static final GuestMainFrm guestSearchFrm = new GuestMainFrm();
    private GuestMainFrm() {
        //TODO:�������û����еĲ˵�ɶ��
    }

    public static GuestMainFrm getInstance() {
        return guestSearchFrm;
    }
}
