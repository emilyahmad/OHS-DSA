/******************************************************************************
 * File: StudentAccount.java
 * 
 * @author Emily Ahmad
 *         Date: 1/19/26
 *
 *         Runtime exception
 ******************************************************************************/

public final class InconsistentKeyException extends RuntimeException {
    public InconsistentKeyException(String message) {
        super(message);
    }
}
