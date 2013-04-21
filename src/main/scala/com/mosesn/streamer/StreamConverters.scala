package com.mosesn.streamer

object StreamConverters {
  implicit def asStreambleConverter[A](iterator: Iterator[A]): Streamable[A] =
    Streamable(iterator)

  implicit def asStreamableConverter[A](iterable: Iterable[A]): Streamable[A] =
    Streamable(iterable)
}

trait Streamable[A] {
  def streamableIterator: Iterator[A]
  def toLazyStream: Stream[A] = Streamer(streamableIterator)
}

object Streamable {
  def apply[A](iterable: Iterable[A]): Streamable[A] = new IterableStreamable[A](iterable)
  def apply[A](iterator: Iterator[A]): Streamable[A] = new IteratorStreamable[A](iterator)
}

class IterableStreamable[A](iterable: Iterable[A]) extends Streamable[A] {
  override def streamableIterator: Iterator[A] = iterable.iterator
}

class IteratorStreamable[A](iterator: Iterator[A]) extends Streamable[A] {
  private[this] var unused: Boolean = true
  override def streamableIterator: Iterator[A] = {
    require(unused, "You can't turn an iterator into a stream twice")
    unused = false
    iterator
  }
}
