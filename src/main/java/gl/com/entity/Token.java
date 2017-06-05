package gl.com.entity;

import java.io.Serializable;

/**
 * Created by penlymeng on 5/3/17.
 */
public class Token implements Serializable{
    private String auth;

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
}
