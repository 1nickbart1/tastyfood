package by.tastyfood.recepies.response;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Created by Nikolay on 25.07.2017.
 */
@JsonSerialize
public class LoginCheckResponse {
    boolean valid = true;

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
