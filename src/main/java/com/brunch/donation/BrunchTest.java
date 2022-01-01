package com.brunch.donation;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class BrunchTest {
	public static void main(String[] args) {
//		try {
//
//			// To connect to mongodb server
//			MongoClient mongoClient = new MongoClient("localhost", 27017);
//
//			// Now connect to your databases
//			MongoDatabase mgdb = mongoClient.getDatabase("mycol");
//
//			System.out.println("Connect to database successfully!");
//			System.out.println("MongoDatabase inof is : " + mgdb.getName());
//			// If MongoDB in secure mode, authentication is required.
//			// boolean auth = db.authenticate(myUserName, myPassword);
//			// System.out.println("Authentication: "+auth);
//
//		} catch (Exception e) {
//			System.err.println(e.getClass().getName() + ": " + e.getMessage());
//		}
		
		
		ConnectionString connectionString = new ConnectionString("mongodb+srv://francis545:<password>@cluster0.kvk2z.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
		MongoClientSettings settings = MongoClientSettings.builder()
		        .applyConnectionString(connectionString)
		        .build();
		MongoClient mongoClient = MongoClients.create(settings);
		MongoDatabase database = mongoClient.getDatabase("test");
		System.out.println(database.getName());
	}
}
