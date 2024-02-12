package hexlet.code;

public class ContentFile1 {
    private String host;
    private int timeout;
    private String proxy;
    private boolean follow;
    public ContentFile1(String host, int timeout, String proxy, boolean follow) {
        this.host = host;
        this.timeout = timeout;
        this.proxy = proxy;
        this.follow = follow;
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
        return "ContentFile1{" +
                "host='" + host + '\'' +
                ", timeout=" + timeout +
                ", proxy='" + proxy + '\'' +
                ", follow='" + follow + '\'' +
                '}';
    }
}
