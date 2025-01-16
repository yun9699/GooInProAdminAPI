package org.gooinpro.gooinproadminapi.admin.exception;

public enum AdminExceptions {

    BAD_AUTH(400, "ID/PW incorrect"),
    TOKEN_NOT_ENOUGH(401, "More Tokens required"),
    ACCESSTOKEN_TOO_SHORT(402, "Access Token Too short"),
    REQUIRE_SIGN_IN(403, "Require sign in"),
    ADMIN_NOT_FOUND(404, "Admin not found"),
    DUPLICATE_ADMIN_ID(409, "Admin ID already exists");

    private final AdminTaskException exception;

    AdminExceptions(int status, String msg) {
        this.exception = new AdminTaskException(status, msg);
    }

    public AdminTaskException get() {
        return exception;
    }
}
