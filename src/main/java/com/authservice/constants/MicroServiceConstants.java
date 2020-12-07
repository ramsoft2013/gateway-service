package com.authservice.constants;

public class MicroServiceConstants {

    public static final String LOGIN_MICROSERVICE = "/login-service/**";

    public static final String EMP_MICROSERVICE = "employee-service";
    public static final String BASE_API = "/api";

    public interface AdminMicroServiceConstants {
        String FETCH_ADMIN_BY_USERNAME = "/fetch-admin/{username}";
    }
}
