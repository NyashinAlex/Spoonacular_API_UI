package models.searchRecipes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchRecipesResponse {

    private ArrayList<Result> results;
    private int offset;
    private int number;
    private int totalResults;

}
