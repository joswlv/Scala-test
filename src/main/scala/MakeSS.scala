/**
	* Created by SeungWanJo on 2017. 8. 17.
	*/
object MakeSS {

	def makess(format:String, path:String) = {
		import org.apache.spark.sql.SparkSession
		val spark = SparkSession.builder().getOrCreate()
		import spark.implicits._
		val test = spark.read.format(format).load(path)
		test
	}
	def main(args: Array[String]): Unit = {
		println("load good!")
	}
}

