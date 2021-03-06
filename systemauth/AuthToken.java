import java.util.Base64;
import java.util.Date;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


class AuthToken {
    private static final long DEFAULT_EXPIRED_TIME_INTERVAL = 1*60*1000L;
    private String token;
    private String baseUrl;
    private long createTime;
    private long expiredTimeInterval = DEFAULT_EXPIRED_TIME_INTERVAL;

    public AuthToken (String token, long createTime){
        this.token = token;
        this.createTime = createTime;
    }

    public AuthToken (String baseUrl, long createTime, String token){
        this.token = token;
        this.createTime = createTime;
        this.baseUrl = baseUrl;
    }

    public static AuthToken buildAuthToken(ApiRequest req, String password) {
        // srcStr format:
        // "GETcvm.api.qcloud.com/v2/index.php?"
        //        + "AppID=AKIDz8krbsJ5yKBZQpn74WFkmLPx3gnPhESA"
        //        + "&Timestamp=1465185768";
        String srcStr = ""; // TODO
        srcStr = "geekbang?"
            + "AppID=designpattern"
            + "&Timestamp=1465185768";
        String token = generateToken(srcStr, password);
        System.out.println("buildAuthToken: " + token);
        AuthToken authToken = new AuthToken(token, new Date().getTime());
        return authToken;
      }

    public String getToken(){
        return this.token;
    }

    public static String generateToken(String value, String key) {
        return hmacSha1(value, key);
      }
    
    
      private static String hmacSha1(String value, String key) {
        try {
          // Get an hmac_sha1 key from the raw key bytes
          byte[] keyBytes = key.getBytes();
          SecretKeySpec signingKey = new SecretKeySpec(keyBytes, "HmacSHA1");
    
          // Get an hmac_sha1 Mac instance and initialize with the signing key
          Mac mac = Mac.getInstance("HmacSHA1");
          mac.init(signingKey);
    
          // Compute the hmac on input data bytes
          byte[] rawHmac = mac.doFinal(value.getBytes());
    
          byte[] result = Base64.getEncoder().encode(rawHmac);
    
          //  Covert array of Hex bytes to a String
          return new String(result, "UTF-8");
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      }

    public boolean isExpired(){
        return this.createTime > new Date().getTime() + expiredTimeInterval;
    }

    public boolean match(AuthToken authToken){
        return this.token.equals(authToken.token);
    }
}