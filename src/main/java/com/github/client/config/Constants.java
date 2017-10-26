package com.github.client.config;

/**
 * Application constants.
 */
public final class Constants {

    //Regex for acceptable logins
    public static final String LOGIN_REGEX = "^[_'.@A-Za-z0-9-]*$";

    public static final String SYSTEM_ACCOUNT = "system";
    public static final String ANONYMOUS_USER = "anonymoususer";
    public static final String DEFAULT_LANGUAGE = "en";

    /**
     * GITHUB AUTH
     */
    public static final String GITHUB_CLIENT_ID="Iv1.d9bfb337753f3e6c";
    public static final String GITHUB_CLIENT_SECRET="cbbf40dcbd3dd771f870d5577b3a6b4421c23d6b";
    public static final String GITHUB_BASE_URL = "https://github.com/";
    public static final String GITHUB_API_URL = "https://api.github.com/";

    private Constants() {
    }
}
