package com.fixit.areas.user.models.binding;

import com.fixit.areas.user.models.binding.validations.IsEmailRegistered;
import com.fixit.areas.user.models.binding.validations.IsPasswordMatching;
import com.fixit.areas.user.models.binding.validations.IsUsernameTaken;
import com.fixit.constants.Constants;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@IsPasswordMatching
public class UserRegisterBindingModel {
    @Size(min = 4, max = 20, message = Constants.USERNAME_LENGTH)
    @IsUsernameTaken
    private String username;

    @Size(min = 4, max = 30, message = Constants.PASSWORD_LENGTH)
    private String password;

    private String confirmPassword;

    private boolean admin;

    @NotEmpty(message = Constants.ENTER_VALID_EMAIL)
    @Email(message = Constants.ENTER_VALID_EMAIL)
    @IsEmailRegistered
    private String email;
    
    private String company;
    
    @Pattern(regexp = "(^$|[0-9]{0,15})", message = Constants.PHONE_VALIDATION)
    private String phone;

    public UserRegisterBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getCompany() {
    	return company;
    }
    
    public void setCompany(String company) {
    	this.company = company;
    }
    
    public String getPhone() {
    	return phone;
    }
    
    public void setPhone(String phone) {
    	this.phone = phone;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
