import java.util.Date;

class ApiRequest{
    private String baseUrl;
    private String token;
    private String appId;
    private long timestamp;

    public ApiRequest(String baseUrl, String token, String AppId, long timestamp){
        this.baseUrl = baseUrl;
        this.token = token;
        this.appId = AppId;
        this.timestamp = timestamp;
    }

    public static ApiRequest buildFromUrl(String url){
        ApiRequest req = new ApiRequest("geekbang", "IXIGIpJ9hdOBCyjStaDJ5Nom07g=", "designpattern", new Date().getTime());
        return req;
    }

    public String getBaseUrl(){
        return this.baseUrl;
    }

    public String getToken(){
        return this.token;
    }

    public String getAppId(){
        return this.appId;
    }

    public long getTimestamp(){
        return this.timestamp;
    }



}