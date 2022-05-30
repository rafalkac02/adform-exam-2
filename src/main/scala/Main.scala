import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object Main extends App {

  val spark = SparkSession
    .builder()
    .appName("Spark application")
    .master("local[*]")
    .getOrCreate()

  import spark.implicits._
  println("Hello, world!")

  // solution: spark-submit
}