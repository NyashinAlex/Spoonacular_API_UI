package models.connectUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConnectUserResponse {

    private String status;
    private String username;
    private String spoonacularPassword;
    private String hash;

}
