package domain;

import java.io.Serializable;
import presentation.LoginJFrame;

public class Login implements Serializable {
    
    public static void main(String[] args) {
        LoginJFrame loginJFrame = new LoginJFrame();
        loginJFrame.setVisible(true);
    }
    
}