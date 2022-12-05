package view;

import style.StyleCtrl;

import javax.swing.*;

abstract public class MyBootFrame extends JFrame implements WaitModeAble{
    public MyBootFrame(){
        super();
        this.setLocationByPlatform(true);
        this.setLayout(null);
        this.setResizable(false);
    }
}
