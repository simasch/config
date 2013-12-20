package ch.simas.config.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Property implements Serializable {

    @Id
    private String pkey;
    private String pvalue;
    private Integer ttl;
    private String scope;

    public Property() {

    }

    public Property(String key, String value) {
        this.pkey = key;
        this.pvalue = value;
    }

    public Property(String key, String value, Integer ttl) {
        this.pkey = key;
        this.pvalue = value;
        this.ttl = ttl;
    }

    public String getKey() {
        return pkey;
    }

    public void setKey(String key) {
        this.pkey = key;
    }

    public String getValue() {
        return pvalue;
    }

    public void setValue(String value) {
        this.pvalue = value;
    }

    public Integer getTtl() {
        return ttl;
    }

    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

}
