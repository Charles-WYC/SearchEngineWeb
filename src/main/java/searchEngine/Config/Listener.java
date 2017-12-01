package searchEngine.Config;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import searchEngine.Service.TaskService;
import searchEngine.model.Task;

public class Listener {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TaskService taskService;

    @KafkaListener(topics = {"taskOperation"})
    public void listen(ConsumerRecord<?, ?> record) {
        logger.info("kafka的key: " + record.key());
        logger.info("kafka的value: " + record.value().toString());

        JSONObject kafkaRequest = new JSONObject(record.value().toString());
        String operation = kafkaRequest.getString("operation");
        String id = kafkaRequest.getString("id");

        logger.info("kafka operation: "+operation);
        logger.info("task Id: "+id);

        if(operation.equals("changeStatus")){
            logger.info("in changeStatus");
            String newStatus = kafkaRequest.getString("newStatus");
            logger.info("new status: "+newStatus);
            Task task = taskService.findById(id);
            task.setStatus(newStatus);
            taskService.save(task);
        }
    }
}