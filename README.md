public void sendMessageToTopic(String message){
CompletableFuture<SendResult<String, Object>> future = template.send("strange_topic", 3, null, message);
future.whenComplete((result, ex) -> {
if (ex == null) {
System.out.println("Sent message to topic: " + message
+ " with offset: ["+ result.getRecordMetadata().offset()
+ " with partition: [" + result.getRecordMetadata().partition() +"]");
}else{
System.out.println("Unable to send message to topic: [" + message + "] due to " + ex.getMessage());
}
});
}

([When you want to send message to specific partition mention that here])
Params: topic_name, partition, key, message
CompletableFuture<SendResult<String, Object>> future = template.send("strange_topic", 3, null, message);


For Testing: 
--------------------------------------
1) Add these 3 dependencies
2) Disable config class and load config details from applcation.yml file
========================================
<!-- https://mvnrepository.com/artifact/org.testcontainers/testcontainers -->
		<dependency>
			<groupId>org.testcontainers</groupId>
			<artifactId>testcontainers</artifactId>
			<version>1.19.8</version>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.testcontainers</groupId>
			<artifactId>kafka</artifactId>
			<version>1.19.8</version>
			<scope>test</scope>
		</dependency>
		<!-- https://mvnrepository.com/artifact/org.testcontainers/junit-jupiter -->
		<dependency>
			<groupId>org.testcontainers</groupId>
			<artifactId>junit-jupiter</artifactId>
			<version>1.19.8</version>
			<scope>test</scope>
		</dependency>

<!--This is to make it wait for some time-->
		<!-- https://mvnrepository.com/artifact/org.awaitility/awaitility -->
		<dependency>
			<groupId>org.awaitility</groupId>
			<artifactId>awaitility</artifactId>
			<version>4.2.1</version>
			<scope>test</scope>
		</dependency>