import java.util.Properties
import org.apache.kafka.streams.scala.ImplicitConversions._
import org.apache.kafka.streams.scala.serialization.Serdes.stringSerde
import org.apache.kafka.streams.{KafkaStreams, StreamsConfig}

object KafkaStreamsDocker extends App {

  import org.apache.kafka.streams.scala._
  val bootstrapservers = sys.env.getOrElse("MATEUSZ_BOOTSTRAP_SERVERS", ":9092")
  println(s"MATEUSZ bootstrapservers=[$bootstrapservers]")
  val props: Properties = {
    val p = new Properties()
    p.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-docker-confluent")
    p.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapservers)
    p.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 0)
    p
  }

  val builder = new StreamsBuilder
  val input = builder.stream[String, String]("in")

  input.to("out")

  val streams: KafkaStreams = new KafkaStreams(builder.build(), props)
  streams.start()

  sys.ShutdownHookThread {
    streams.close()

  }
}

