import java.util.Properties
import org.apache.kafka.streams.scala.ImplicitConversions._
import org.apache.kafka.streams.scala._
import org.apache.kafka.streams.scala.kstream._
import org.apache.kafka.streams.{KafkaStreams, StreamsConfig}
import org.apache.kafka.streams.scala.serialization.Serdes._


object Main extends App {

  val multiplier = args(0)
  val inTopic = "input-topic"
  val outTopic = "output-topic"

  def isPositiveNumber(s: String) = s.forall(_.isDigit)

  def isNegativeNumber(s: String) = s(0) == '-' && s.drop(1).forall(_.isDigit)

  def convertText(text: String, multiplier: Int) = {
    text.split(" +").map {
      case w if isPositiveNumber(w) => (w.toInt * multiplier).toString
      case w if isNegativeNumber(w) => "negative number: " + w
      case word => word.toUpperCase()
    }
      .mkString(" ")
  }

  val props: Properties = {
    val p = new Properties()
    p.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-streams-app")
    p.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, ":9092")
    p.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String.getClass)
    p.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String.getClass)
    p
  }

  val builder: StreamsBuilder = new StreamsBuilder
  val lines: KStream[String, String] = builder.stream[String, String](inTopic)

  val wordCounts: KTable[String, String] = lines
    .flatMapValues(textLine => textLine.toLowerCase.split("\\W+"))
    .groupBy((_, word) => word)
    .count()/*(Materialized.as("counts-store"))*/
    .mapValues(_.toString)

  wordCounts.toStream.to(outTopic)

  val topology = builder.build()
  val streams: KafkaStreams = new KafkaStreams(topology, props)
  streams.start()

  //  sys.ShutdownHookThread {
  //    streams.close(10, TimeUnit.SECONDS)
  //  }
}