package hexlet.code;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentFile1 {
    private String host;
    private int timeout;
    private String proxy;
    private boolean follow;

    public ContentFile1() {
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getProxy() {
        return proxy;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy;
    }

    public boolean isFollow() {
        return follow;
    }

    public void setFollow(boolean follow) {
        this.follow = follow;
    }

    @Override
    public String toString() {
        return "ContentFile1{"
                + "host: '" + host + '\''
                + ", timeout: " + timeout
                + ", proxy: '" + proxy + '\''
                + ", follow: '" + follow + '\''
                + '}';
    }
}
