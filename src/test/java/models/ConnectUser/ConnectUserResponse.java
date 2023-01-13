package models.ConnectUser;

public class ConnectUserResponse {

    private String status;
    private String username;
    private String spoonacularPassword;
    private String hash;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSpoonacularPassword() {
        return spoonacularPassword;
    }

    public void setSpoonacularPassword(String spoonacularPassword) {
        this.spoonacularPassword = spoonacularPassword;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
