package com.mosesn.streamer

object Streamer {
  def convert[A](iterator: Iterator[A]): Stream[A] =
    if (iterator.hasNext) iterator.next() #:: convert(iterator) else Stream.Empty

  def apply[A](iterator: Iterator[A]): Stream[A] = convert(iterator)

  def apply[A](iterable: Iterable[A]): Stream[A] = convert(iterable.iterator)
}
