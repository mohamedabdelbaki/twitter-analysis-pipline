import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.from_json

object kafka2sparkStreaming {
  def main(args: Array[String]): Unit = {
    System.setProperty("hadoop.home.dir", "C:\\hadoop")
    val spark = SparkSession.builder().master("local[5]").appName("SparkByExample").getOrCreate();

    import spark.implicits._
//    val jsonstr = """{id:1264891373448200196,text:'RT @AdamSchefter: Well done, men. A win for you, sports for us, and $20 million for COVID-19 relief efforts. https://t.co/OKFloTncKt',lang:'en',user:{id:353437359,name:'Timmy Sickmeier',screenName:'TheTSick_33',location:'Indiana, USA',followersCount:308},retweetCount:0,favoriteCount:0}"""
    val BatchSchema = spark.read
      .option("multiLine", true).option("mode", "PERMISSIVE")
      .json("src\\main\\resources\\tweetsdata.json").schema //if schema has the nested json structure


    val inputDf = spark.readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "192.168.1.6:9092")
      .option("subscribe", "covid-tweets")
      .option("startingOffsets", "latest") //earliest,latest
      .load()
    inputDf.printSchema()

    val dataDf = inputDf.selectExpr("CAST(value AS STRING) as json").
      select(from_json($"json",schema=BatchSchema).as("data"))
      .select("data.*").na.drop("all")

//    val consoldf= dataDf.writeStream
//      .outputMode("append")
//      .queryName("table")
//      .format("console")
//      .start()
//    consoldf.awaitTermination()


    val parquetdf = dataDf.writeStream.outputMode("append")
      .format("parquet")
      .option("path", "hdfs://192.168.1.6:8022/user/cloudera/tweetsDir")
      .option("checkpointLocation", "hdfs://192.168.1.6:8022/user/cloudera/checkpointsDir").start()

    parquetdf.awaitTermination()
  }
}
