package com.epam.apartmentbooking.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserPassword {

    @NotNull
    @Size(min = 5, max = 40, message = "{password.size.error}")
    private String oldPassword;

    @NotNull
    @Size(min = 5, max = 40, message = "{password.size.error}")
    private String newPassword;

    @NotNull
    @Size(min = 5, max = 40, message = "{password.size.error}")
    private String newPasswordCopy;

    public UserPassword() {
    }

    public UserPassword(String oldPassword, String newPassword, String newPasswordCopy) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.newPasswordCopy = newPasswordCopy;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordCopy() {
        return newPasswordCopy;
    }

    public void setNewPasswordCopy(String newPasswordCopy) {
        this.newPasswordCopy = newPasswordCopy;
    }
}
