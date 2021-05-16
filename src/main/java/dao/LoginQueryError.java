
package dao;


public class LoginQueryError extends Exception {

    public LoginQueryError(String message, Exception e) {
        super(message, e);
    }
    
}
