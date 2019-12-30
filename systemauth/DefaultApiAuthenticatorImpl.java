public class DefaultApiAuthenticatorImpl implements ApiAuthenticator {
    private CredentialStorage credentialStorage;
    
    public DefaultApiAuthenticatorImpl() {
      this.credentialStorage = new DefaultCredentialStorage();
    }
  
    @Override
    public void auth(String url) {
      ApiRequest apiRequest = ApiRequest.buildFromUrl(url);
      auth(apiRequest);
    }
  
    @Override
    public void auth(ApiRequest apiRequest) {
      String appId = apiRequest.getAppId();
      String token = apiRequest.getToken();
      long timestamp = apiRequest.getTimestamp();
  
      AuthToken clientAuthToken = new AuthToken(token, timestamp);
      if (clientAuthToken.isExpired()) {
        throw new RuntimeException("Token is expired.");
      }
  
      String password = credentialStorage.getPasswordByAppID(appId);
      AuthToken serverAuthToken = AuthToken.buildAuthToken(apiRequest,password);
      if (!serverAuthToken.match(clientAuthToken)) {
        throw new RuntimeException("Token verfication failed.");
      }
    }
  }