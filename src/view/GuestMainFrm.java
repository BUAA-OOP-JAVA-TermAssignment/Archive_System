package view;

import controller.Closer;


public class GuestMainFrm extends MyFrame{
    private static final GuestMainFrm guestMainFrm = new GuestMainFrm();

    private GuestMainFrm() {
        // Ϊ�û�������ӹر���Ӧ
        this.addWindowListener(Closer.getDataUploadCloser());
    }

    public static GuestMainFrm getInstance() {
        return guestMainFrm;
    }
}
