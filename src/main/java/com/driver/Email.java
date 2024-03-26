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
            boolean validPass = validatePass(newPassword);

            if(validPass) {
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
        int uc=0, lc=0, di=0, sp=0;

        char[] nP = newPassword.toCharArray();
        for (char ch : nP) {
            if(ch>='A' && ch <='B'){
                uc++;
            } else if(ch>='a' && ch<='z'){
                lc++;
            } else if(ch>='0' && ch<='9'){
                di++;
            }
        }

        if((boolean)Pattern.compile("[\\W\\S_]").matcher(newPassword).find()){
            sp++;
        }

        return uc>0 && lc>0 && di>0 && sp>0 && newPassword.length()>=8;
    }

}
