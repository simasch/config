package ch.simas.service;

import ch.simas.config.model.Property;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Startup
@Singleton
public class ConfigurationService {

    private Map<String, Property> properties = new HashMap<String, Property>();

    @PersistenceContext
    private EntityManager em;

    @Lock(LockType.READ)
    public String getProperty(String key) {
        return properties.get(key).getValue();
    }

    public void setProperty(String key, String value) {
        properties.put(key, new Property(key, value));
    }

    @PostConstruct
    public void loadProperties() {
        loadEnv();
        loadSystemProperties();
        loadDatabaseProperties();
    }

    private void loadEnv() {
        Map<String, String> env = System.getenv();
        for (Entry<String, String> e : env.entrySet()) {
            properties.put(e.getKey(), new Property(e.getKey(), e.getValue()));
        }
    }

    private void loadSystemProperties() {
        Properties ps = System.getProperties();
        for (Entry<Object, Object> e : ps.entrySet()) {
            properties.put(e.getKey().toString(), new Property(e.getKey()
                    .toString(), e.getValue().toString()));
        }
    }

    private void loadDatabaseProperties() {
        List<Property> list = em.createQuery("SELECT p FROM Property p",
                Property.class).getResultList();
        for (Property p : list) {
            properties.put(p.getKey(), p);
        }
    }

}
