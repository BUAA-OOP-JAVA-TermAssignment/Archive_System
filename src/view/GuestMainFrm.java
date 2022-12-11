package view;

import controller.Closer;


public class GuestMainFrm extends MyFrame{
    private static final GuestMainFrm guestMainFrm = new GuestMainFrm();

    private GuestMainFrm() {
        // 为用户窗口添加关闭响应
        this.addWindowListener(Closer.getDataUploadCloser());
    }

    public static GuestMainFrm getInstance() {
        return guestMainFrm;
    }
}
