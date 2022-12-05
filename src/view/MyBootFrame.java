package view;

import javax.swing.*;

abstract public class MyBootFrame extends JFrame implements WaitModeAble{
    public MyBootFrame(){
        super();
        this.setLayout(null);
        this.setResizable(false);
    }
}
