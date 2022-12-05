package controller;

import style.StyleCtrl;
import view.MyBootFrame;

import javax.swing.*;

public class LogonRegisterCtrl {
    public static void main(String[] args) {
        StyleCtrl.init();


    }

    public static void timeoutWakeupTest(MyBootFrame sourceFrame) {
        new Thread(() -> {
            try {
                Thread.sleep(3000);
            }catch (Exception e) {
                System.out.println("!!! LogonRegisterCtrl : Thread sleep error");
            }
            sourceFrame.disWaitMode();
        }).start();
    }
}
