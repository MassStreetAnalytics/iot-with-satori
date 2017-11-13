//Be sure to check out the Spark Structured Streaming Programming Guide
//https://spark.apache.org/docs/latest/structured-streaming-programming-guide.html
//Simpler data feed: https://www.satori.com/opendata/channels/Everett-Transit
package net.massstreet

import org.apache.spark.sql.functions._
import org.apache.spark.sql.SparkSession
import org.apache.log4j._

object ProcessWeatherData {
  
 def main(args: Array[String]) {
 
    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)
   
    val spark = SparkSession
    .builder
    .appName("IoT-With-Satori")
    .master("local[*]")
    .getOrCreate()
    
    val weatherDF = spark
    .readStream
    .format("socket")
    .option("host", "localhost")
    .option("port", 9090)
    .load()
        
    val query = weatherDF
    .writeStream
    .outputMode("append")
    .format("console")
    .start()
  
    query.awaitTermination()
    
  }
  
}