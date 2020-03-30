package pers.xy.zk.config.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "zk")
public class ZkProps {
    /**
     * 连接地址
     */
    private String url = "127.0.0.1:2181";

    /**
     * 工作空间
     */
    private String namespace = "cloud";

    /**
     *
     */
    private String scheme = "digest";

    /**
     *
     */
    private String auth = "cloud:admin";

    /**
     * 超时时间(毫秒)，默认1000
     */
    private int timeout = 1000;

    /**
     * 重试次数，默认3
     */
    private int retry = 3;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getRetry() {
        return retry;
    }

    public void setRetry(int retry) {
        this.retry = retry;
    }
}
