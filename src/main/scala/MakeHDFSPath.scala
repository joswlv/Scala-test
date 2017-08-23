/**
	* Created by SeungWanJo on 2017. 8. 21.
	* Department : Ad Infra Dev. Team
	*/
object MakeHDFSPath extends App{
	def makePath(day: String, hour: Int, action:String):Array[String] = {
		val paths:Array[String] = new Array[String](hour+1)
		for (i <- 0 until hour+1) {
			if (i < 10){
				val path = s"/user/irteam/addinfra/dmp_log/dt=$day/hr=${day}0${i}/action=${action}/*"
				paths(i) = path
			} else {
				val path = s"/user/irteam/addinfra/dmp_log/dt=$day/hr=${day}${i}/action=${action}/*"
				paths(i) = path
			}
		}
		paths
	}

	makePath("20170821",18,"cnv_buy")
}
