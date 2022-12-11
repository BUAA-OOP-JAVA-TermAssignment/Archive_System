package view;

import javax.swing.*;

abstract public class MyBootFrame extends JFrame implements WaitModeAble{
    public MyBootFrame(){
        super();
        this.setIconImage(new ImageIcon("./resource/loginIcon.png").getImage());
        this.setLocationByPlatform(true);
        this.setLayout(null);
        this.setResizable(false);
        this.setFocusable(true);
    }
    abstract boolean checkInputLegalAndSend();

    boolean isLegalId(String number){
        int n = number.length();
        if(8 == n && number.matches("\\d+")){
            int num1 = Integer.parseInt(number.substring(0,2));
            if(num1 < 17 || num1 > 22){ return false;}
            int num2 = Integer.parseInt(number.substring(2,4));
            if(num2 < 1 || num2 >43) {
                return false;
            }
            int num3 = Integer.parseInt(number.substring(4,5));
            if(num3 < 1 || num3 > 9) {
                return false;
            }
            int num4 = Integer.parseInt(number.substring(5,8));
            return num4 >= 1;
        } else if(9 == n && number.matches("[ZBS]Y\\d+")){
            int num2 = Integer.parseInt(number.substring(4,6));
            if(num2 < 1 || num2 >43) {
                return false;
            }
            int num3 = Integer.parseInt(number.substring(6,7));
            if(num3 < 1 || num3 > 9) {
                return false;
            }
            int num4 = Integer.parseInt(number.substring(7,9));
            if(num4 < 1) {
                return false;
            }
            if((number.startsWith("ZY"))|| (number.startsWith("SY"))){
                int num1 = Integer.parseInt(number.substring(2,4));
                return num1 >= 19 && num1 <= 22;
            } else if(number.startsWith("BY")){
                int num1 = Integer.parseInt(number.substring(2,4));
                return num1 >= 17 && num1 <= 22;
            } else {
                return false;
            }
        } else if(5 == n && number.matches("\\d+")){
            int num1 = Integer.parseInt(number);
            return num1 >= 1;
        } else {
            return false;
        }

    }

    static boolean isLegalEmail(String email){
        return email.matches("^\\w+@\\w+(\\.\\w+)+$");
    }

    static boolean isLegalPassword(String password){
        return password.matches("^([A-Z]|[a-z])([0-9]|[A-Z]|[a-z]|_){7,15}$");
    }

    static boolean isLegalName(String name){
        return name.matches("^(([\\u4e00-\\u9fa5]){1,5}¡¤?([\\u4e00-\\u9fa5])+){1,10}$");
    }

    static boolean isSamePassword(String rePassword,String password){
        return rePassword.matches(password);
    }


}
