package com.driver;

import java.util.regex.Pattern;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword){
        if(oldPassword.equals(password)){
            if(validatePass(newPassword)) {
                this.password = newPassword;
            }
        }
    }

    public boolean validatePass(String newPassword){
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character
        boolean uc=false, lc=false, di=false, sp=false;

        char[] nP = newPassword.toCharArray();
        for (char ch : nP) {
            if(ch>='A' && ch <='Z'){
                uc=!(uc);
            } else if(ch>='a' && ch<='z'){
                lc=!(lc);
            } else if(ch>='0' && ch<='9'){
                di=!(di);
            } else {
                sp=!(sp);
            }
        }

        return uc && lc && di && sp && newPassword.length()>=8;
    }

}
