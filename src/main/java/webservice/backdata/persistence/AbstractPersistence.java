package webservice.backdata.persistence;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

public abstract class AbstractPersistence {
    MongoTemplate mongoTemplate;
    @Autowired
    Environment environment;

    /**
     * init the Template after constructor getting infos in properties file
     */
    @PostConstruct
    protected void init() {
        checkProperties();
        MongoClient mongo = new MongoClient(environment.getProperty("mongo.host"),
                Integer.parseInt(environment.getProperty("mongo.port")));
        mongoTemplate = new MongoTemplate(mongo, environment.getProperty("mongo.database"));

    }

    /**
     * check if properties are well set
     *
     * @return boolean
     */
    private void checkProperties() {
        if (environment.getProperty("mongo.host") == null || environment.getProperty("mongo.port") == null || environment.getProperty("mongo.database") == null) {
            throw new IllegalArgumentException("At least one property missing");
        }
    }

}
