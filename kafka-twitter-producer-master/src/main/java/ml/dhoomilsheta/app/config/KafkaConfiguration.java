package ml.dhoomilsheta.app.config;

public class KafkaConfiguration {
    public static final String SERVERS = "192.168.1.6:9092, 192.168.1.6:9093";
    public static final String TOPIC = "covid-tweets";
    public static final long SLEEP_TIMER = 1000;
}
