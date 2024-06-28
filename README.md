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