package models.getRecipeInformation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetRecipeInformationResponseError {

    private String status;
    private int code;
    private String message;
}
