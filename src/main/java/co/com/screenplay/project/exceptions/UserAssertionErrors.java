package co.com.screenplay.project.exceptions;

public class UserAssertionErrors extends AssertionError {

    public static final String USER_DETAIL_NOT_CORRECT = "User details information is not correct";
    public static final String USER_NOT_CREATED = "User not created correctly";
    public static final String USER_NOT_SAVED = "User not saved correctly";
    public static final String USER_NOT_UPDATED = "User not updated correctly";
    public static final String USER_NOT_DELETED = "User not deleted correctly";
    public static final String RESPONSE_BODY_NOT_CORRECT = "Response body is not correct";
    public static final String CUSTOMER_NOT_FOUND = "Customer was found when it should not exist";
    public static final String CUSTOMER_CREATION_FAILED = "Customer creation did not fail as expected";
    public static final String ERROR_MESSAGE_INCORRECT = "Error message is not correct";



    public UserAssertionErrors(String message) {
        super(message);
    }

    public UserAssertionErrors(String message, Throwable testErrorException) {
        super(message, testErrorException);
    }

}
