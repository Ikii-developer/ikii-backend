package mx.ikii.commons.repository.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

/**
 * This class is used as MongoDB Config
 * 
 * @author Arturo Isaac Velázquez Vargas
 *
 */
@Profile("!test")
@Configuration
@EnableMongoRepositories(basePackages = { "mx.ikii", "mx.ikii.commons.repository",
		"mx.ikii.commons.persistence.collection" })
public class MongoConfig extends AbstractMongoConfiguration {

	@Override
	public MongoClient mongoClient() {
		return new MongoClient("mongo", 27017);
	}

	@Override
	protected String getDatabaseName() {
		return "ikkidb";
	}

	@Override
	protected String getMappingBasePackage() {
		return "mx.ikii.commons";
	}

}
