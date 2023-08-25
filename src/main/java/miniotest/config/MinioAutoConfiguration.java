package miniotest.config;

/**
 * @author zhg
 * @date 2023/8/25
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(
        prefix = "minio"
)
@ComponentScan({"com.wm.minio.core"})
public class MinioAutoConfiguration {
    private String endpoint;
    private int port;
    private String accessKey;
    private String secretKey;
    private Boolean secure;
    private String bucketName;
    private String configDir;

    @Bean
    public MinioClient minioClient() throws InvalidEndpointException, InvalidPortException {
        MinioClient minioClient = new MinioClient(this.endpoint, this.port, this.accessKey, this.secretKey, this.secure);
        return minioClient;
    }

    public MinioAutoConfiguration() {
    }

    public String getEndpoint() {
        return this.endpoint;
    }

    public int getPort() {
        return this.port;
    }

    public String getAccessKey() {
        return this.accessKey;
    }

    public String getSecretKey() {
        return this.secretKey;
    }

    public Boolean getSecure() {
        return this.secure;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public String getConfigDir() {
        return this.configDir;
    }

    public void setEndpoint(final String endpoint) {
        this.endpoint = endpoint;
    }

    public void setPort(final int port) {
        this.port = port;
    }

    public void setAccessKey(final String accessKey) {
        this.accessKey = accessKey;
    }

    public void setSecretKey(final String secretKey) {
        this.secretKey = secretKey;
    }

    public void setSecure(final Boolean secure) {
        this.secure = secure;
    }

    public void setBucketName(final String bucketName) {
        this.bucketName = bucketName;
    }

    public void setConfigDir(final String configDir) {
        this.configDir = configDir;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof MinioAutoConfiguration)) {
            return false;
        } else {
            MinioAutoConfiguration other = (MinioAutoConfiguration)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label87: {
                    Object this$endpoint = this.getEndpoint();
                    Object other$endpoint = other.getEndpoint();
                    if (this$endpoint == null) {
                        if (other$endpoint == null) {
                            break label87;
                        }
                    } else if (this$endpoint.equals(other$endpoint)) {
                        break label87;
                    }

                    return false;
                }

                if (this.getPort() != other.getPort()) {
                    return false;
                } else {
                    Object this$accessKey = this.getAccessKey();
                    Object other$accessKey = other.getAccessKey();
                    if (this$accessKey == null) {
                        if (other$accessKey != null) {
                            return false;
                        }
                    } else if (!this$accessKey.equals(other$accessKey)) {
                        return false;
                    }

                    label72: {
                        Object this$secretKey = this.getSecretKey();
                        Object other$secretKey = other.getSecretKey();
                        if (this$secretKey == null) {
                            if (other$secretKey == null) {
                                break label72;
                            }
                        } else if (this$secretKey.equals(other$secretKey)) {
                            break label72;
                        }

                        return false;
                    }

                    label65: {
                        Object this$secure = this.getSecure();
                        Object other$secure = other.getSecure();
                        if (this$secure == null) {
                            if (other$secure == null) {
                                break label65;
                            }
                        } else if (this$secure.equals(other$secure)) {
                            break label65;
                        }

                        return false;
                    }

                    Object this$bucketName = this.getBucketName();
                    Object other$bucketName = other.getBucketName();
                    if (this$bucketName == null) {
                        if (other$bucketName != null) {
                            return false;
                        }
                    } else if (!this$bucketName.equals(other$bucketName)) {
                        return false;
                    }

                    Object this$configDir = this.getConfigDir();
                    Object other$configDir = other.getConfigDir();
                    if (this$configDir == null) {
                        if (other$configDir != null) {
                            return false;
                        }
                    } else if (!this$configDir.equals(other$configDir)) {
                        return false;
                    }

                    return true;
                }
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof MinioAutoConfiguration;
    }

    public int hashCode() {
        int result = 1;
        Object $endpoint = this.getEndpoint();
        result = result * 59 + ($endpoint == null ? 43 : $endpoint.hashCode());
        result = result * 59 + this.getPort();
        Object $accessKey = this.getAccessKey();
        result = result * 59 + ($accessKey == null ? 43 : $accessKey.hashCode());
        Object $secretKey = this.getSecretKey();
        result = result * 59 + ($secretKey == null ? 43 : $secretKey.hashCode());
        Object $secure = this.getSecure();
        result = result * 59 + ($secure == null ? 43 : $secure.hashCode());
        Object $bucketName = this.getBucketName();
        result = result * 59 + ($bucketName == null ? 43 : $bucketName.hashCode());
        Object $configDir = this.getConfigDir();
        result = result * 59 + ($configDir == null ? 43 : $configDir.hashCode());
        return result;
    }

    public String toString() {
        return "MinioAutoConfiguration(endpoint=" + this.getEndpoint() + ", port=" + this.getPort() + ", accessKey=" + this.getAccessKey() + ", secretKey=" + this.getSecretKey() + ", secure=" + this.getSecure() + ", bucketName=" + this.getBucketName() + ", configDir=" + this.getConfigDir() + ")";
    }
}

