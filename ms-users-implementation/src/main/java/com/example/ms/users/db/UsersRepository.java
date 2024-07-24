package com.example.ms.users.db;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.ms.users.MSUsersApp;
import com.example.ms.users.def.model.User;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class UsersRepository {
	private static final Logger LOGGER = LoggerFactory.getLogger(UsersRepository.class);

	private static final DataSource DS_DEFAULT;
	private static final SqlSessionFactory SSF_DEFAULT;

	static {
		/*
		 * Initialize the DataSource.
		 */
		LOGGER.info("Initializing DataSource...");

		String jdbcURL = MSUsersApp.getApplicationProperties().getProperty("msusers.jdbc.url");
		String driver = MSUsersApp.getApplicationProperties().getProperty("msusers.jdbc.driver");

		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(jdbcURL);
		config.setDriverClassName(driver);
		config.setAutoCommit(false);

		DS_DEFAULT = new HikariDataSource(config);

		LOGGER.info("DataSource initialized successfully");

		/*
		 * Initialize the MyBatis objects.
		 */
		LOGGER.info("Initializing MyBatis...");

		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		Environment environment = new Environment("microservice", transactionFactory, DS_DEFAULT);
		Configuration configuration = new Configuration(environment);

		configuration.addMapper(UsersMapper.class);

		SSF_DEFAULT = new SqlSessionFactoryBuilder().build(configuration);

		LOGGER.info("MyBatis initialized successfully");
	}

	public static void init() {
		// Empty body, to call the initialization static block.
	}

	public static User select(String username) {
		try (SqlSession ss = SSF_DEFAULT.openSession()) {
			UsersMapper mapper = ss.getMapper(UsersMapper.class);

			return mapper.select(username);
		}
	}

	public static int insert(User user) {
		try (SqlSession ss = SSF_DEFAULT.openSession()) {
			UsersMapper mapper = ss.getMapper(UsersMapper.class);

			int i = mapper.insert(user);

			ss.commit();

			return i;
		}
	}

	public static boolean exists(String username) {
		return select(username) != null;
	}
}
