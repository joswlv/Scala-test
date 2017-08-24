package study.ch01

/**
	* Created by SeungWanJo on 2017. 8. 23.
	*/
sealed trait List[+A] // `List` data type, parameterized on a type, `A`
case object Nil extends List[Nothing] // A `List` data constructor representing the empty list
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List { // `List` companion object. Contains functions for creating and working with lists.

	def apply[A](as: A*): List[A] = // Variadic function syntax
		if (as.isEmpty) Nil
		else Cons(as.head, apply(as.tail: _*))

	//func : List의 첫요소 제거
	def tail[A](l: List[A]): List[A] =
		l match {
			case Nil => sys.error("empty list!")
			case Cons(_, t) => t
		}

	//func : List의 첫 요소를 대체
	def setHead[A](l: List[A], h: A): List[A] =
		l match {
			case Nil => sys.error("empty list!")
			case Cons(_, t) => Cons(h, t)
		}

	//func : n 개의 요소 제거
	def drop[A](l: List[A], n: Int): List[A] =
		if (n <= 0) l
		else l match {
			case Nil => Nil
			case Cons(_, t) => drop(t, n - 1)
		}

	//func : 주어진 프리케이트 만족하면 삭제
	def dropWhile[A](l: List[A], f: A => Boolean): List[A] =
		l match {
			case Cons(h, t) if f(h) => dropWhile(t, f)
			case _ => l
		}

	//func : tail에 요소 추가하기
	def append[A](a1: List[A], a2: List[A]): List[A] =
		a1 match {
			case Nil => a2
			case Cons(h, t) => Cons(h, append(t, a2))
		}

	//fnc : 마지막 요소만 빼기
	def init[A](l: List[A]): List[A] =
		l match {
			case Nil => sys.error("empty list!")
			case Cons(_, Nil) => Nil
			case Cons(h, t) => Cons(h, init(t))
		}


	def foldRight[A, B](as: List[A], z: B)(f: (A, B) => B): B =
		as  match {
			case Nil => z
			case Cons(x, xs) => f(x, foldRight(xs, z)(f))
		}

	def sum(ns: List[Int]) =
		foldRight(ns,0)(_+_)

	def product(ns: List[Int]) =
		foldRight(ns, 1.0)(_*_)

	//func : list 길이 계산
	def length[A](l: List[A]): Int =
		foldRight(l,0)((_,acc) => acc +1)

	@annotation.tailrec
	def foldLeft[A,B](l: List[A], z: B)(f: (B, A) => B): B = l match {
		case Nil => z
		case Cons(h,t) => foldLeft(t, f(z,h))(f)
	}

	//func : list reverse
	def reverse[A](l: List[A]): List[A] = foldLeft(l, List[A]())((a,h) => Cons(h,a))

	//func : list 두개 합성
	def appendViaFoldRight[A](l: List[A], r: List[A]): List[A] =
		foldRight(l, r)(Cons(_,_))

	//func : list zip함수
	def zipWith[A,B,C](a: List[A], b: List[B])(f: (A,B) => C): List[C] = (a,b) match {
		case (Nil,_) => Nil
		case (_,Nil) => Nil
		case (Cons(h1,t1), Cons(h2,t2)) => Cons(f(h1,h2), zipWith(t1,t2)(f))
	}

}
