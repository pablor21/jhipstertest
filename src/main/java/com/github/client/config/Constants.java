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
    public static final String GITHUB_CLIENT_ID="6dc2f2a5b6540055df91";
    public static final String GITHUB_CLIENT_SECRET="f6e26f295ee6cb0d916a948bec3bf104a8c24db1";
    public static final String GITHUB_BASE_URL = "https://github.com/";
    public static final String GITHUB_API_URL = "https://api.github.com/";

    private Constants() {
    }
}
