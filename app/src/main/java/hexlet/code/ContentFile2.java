package hexlet.code;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentFile2 {
    private int timeout;
    private boolean verbose;
    private String host;

    public ContentFile2() {
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public boolean isVerbose() {
        return verbose;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public String toString() {
        return "ContentFile2{"
                + "timeout: " + timeout
                + ", verbose: " + verbose
                + ", host: '" + host + '\''
                + '}';
    }
}
