package study.ch01

import study.ch01.List._

/**
	* Created by SeungWanJo on 2017. 8. 23.
	*/
object PattenMatchTest {
	def main(args: Array[String]): Unit = {
		val x = List(1, 11, 2, 4, 5) match {
			case Cons(x, Cons(2, Cons(4, _))) => x
			case Nil => 42
			case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
			case Cons(h, t) => h + sum(t)
			case _ => 101
		}
		println(x) // result : 12 | match is `Cons(x, Cons(y, Cons(3, Cons(4, _))))`

		val y = product(List(1,2,0,4,5))
		println(y)
	}
}
