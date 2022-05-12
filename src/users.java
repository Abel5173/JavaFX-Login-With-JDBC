public class users {
    private String Username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    users(String uname, String fName, String lName, String email, String password) {
        this.Username = uname;
        this.firstName = fName;
        this.lastName = lName;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // @Override
    // public String toString() {
    // return (getFirstName())
    // }
}
