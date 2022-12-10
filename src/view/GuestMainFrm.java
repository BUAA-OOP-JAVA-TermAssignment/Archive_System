package view;

import controller.Closer;

import javax.swing.*;


public class GuestMainFrm extends MyFrame{
    private static final GuestMainFrm guestMainFrm = new GuestMainFrm();

    private GuestMainFrm() {
        this.addWindowListener(Closer.getDataUploadCloser());
    }

    public static GuestMainFrm getInstance() {
        return guestMainFrm;
    }
}
