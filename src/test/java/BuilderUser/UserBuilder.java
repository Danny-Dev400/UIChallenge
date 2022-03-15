package BuilderUser;

import net.bytebuddy.dynamic.loading.ClassInjector;

public class UserBuilder implements Builder{

    private String username;
    private String password;
    private String token;
    private String apiKey;
    private String expiresToken;
    private String sessionId;

    public UserBuilder(String apiKey){
        this.apiKey = apiKey;
    }

    public UserBuilder setUsernameBuilder(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder setPasswordBuilder(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setTokenBuilder(String token) {
        this.token = token;
        return this;
    }

    public UserBuilder setApikeyBuilder(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public UserBuilder setExpiresTokenBuilder(String expiresToken) {
        this.expiresToken = expiresToken;
        return this;
    }

    public UserBuilder setSessionIdBuilder(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    @Override
    public User build() {
        User user = new User();
        user.setUsername(this.username);
        user.setPassword(this.password);
        user.setApiKey(this.apiKey);
        user.setExpiresToken(this.expiresToken);
        user.setToken(this.token);
        user.setSessionId(this.sessionId);
        return user;
    }
}
