package models.getRecipeInformation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetRecipeInformationResponseError {

    private String status;
    private int code;
    private String message;
}
