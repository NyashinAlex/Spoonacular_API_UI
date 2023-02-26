package models.connectUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConnectUserRequest {

    private String username;
    private String firstName;
    private String lastName;
    private String email;

}
