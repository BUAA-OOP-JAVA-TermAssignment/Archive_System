package controller;


import view.MyBootFrame;

// 程序启动即工作，类方法
public class NetworkCtrl {
    //TODO:记得把初始值改回false
    private static boolean isChangingData = true;

    public static boolean isIsChangingData() {
        return isChangingData;
    }

    public static void timeoutWakeupTest(MyBootFrame sourceFrame) {
        //TODO:这是一个用来测试的函数，到时候相应的内容，需要接入相应的处理器中
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
