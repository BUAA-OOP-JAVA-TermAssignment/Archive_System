package controller;


import view.MyBootFrame;

// �����������������෽��
public class NetworkCtrl {
    //TODO:�ǵðѳ�ʼֵ�Ļ�false
    private static boolean isChangingData = true;

    public static boolean isIsChangingData() {
        return isChangingData;
    }

    public static void timeoutWakeupTest(MyBootFrame sourceFrame) {
        //TODO:����һ���������Եĺ�������ʱ����Ӧ�����ݣ���Ҫ������Ӧ�Ĵ�������
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
