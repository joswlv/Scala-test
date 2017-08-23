package study

/**
	* Created by SeungWanJo on 2017. 8. 22.
	* Department : Ad Infra Dev. Team
	*/
object fib {
	def fibo(n: Int): Int = {
//		꼬리위치에서 발생하는재귀함수는 컴파일 단계에서 자동으로 while문으로 변경된다
//		이를 tail call elimination이라고 부른다
//		여기서 `@annotaion.tailrec`를 붙여주면 컴파일단계에서 while문으로 변경안하면 컴파일 오류를 발생시킨다
		@annotation.tailrec
		def loop(n: Int, prev: Int, cur: Int): Int =
			if (n == 0) prev
			else loop(n - 1, cur, prev + cur)

		loop(n, 0, 1)
	}
}
