package mx.ikii.commons.repository.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

/**
 * This class is used as MongoDB Config
 * 
 * @author Arturo Isaac Velázquez Vargas
 *
 */
@Profile("!development")
@Configuration
@EnableMongoRepositories(basePackages = {"mx.ikii.commons.persistence.collection" })
public class MongoConfigProd extends AbstractMongoConfiguration {

	@Override
	public MongoClient mongoClient() {
		System.out.println("############# MONGO PRODUCTION #############");
		MongoClientOptions options = MongoClientOptions.builder().maxConnectionLifeTime(0).connectionsPerHost(10000)
				.threadsAllowedToBlockForConnectionMultiplier(3000).maxConnectionIdleTime(0).build();

		MongoCredential credential = 
				MongoCredential.createCredential("ik1iU53r<3", "ikiidb","iki1P@5s<3".toCharArray());

		ServerAddress mongoServer = new ServerAddress("mongo", 27017);
		
		MongoClient mongoClient = new MongoClient(mongoServer, credential, options);

		return mongoClient;
	}

	@Override
	protected String getDatabaseName() {
		return "ikiidb";
	}

	@Override
	protected String getMappingBasePackage() {
		return "mx.ikii.commons";
	}

}
