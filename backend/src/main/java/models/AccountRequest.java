package models;

public class AccountRequest {

    private String name;
    private String username;
    private String email;
    private Integer userType;
    private String procoName;
    private String phoneNumber;
    private int userApproved;

    public int getUserApproved() {
        return userApproved;
    }

    public void setUserApproved(int userApproved) {
        this.userApproved = userApproved;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getProcoName() {
        return procoName;
    }

    public void setProcoName(String procoName) {
        this.procoName = procoName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
