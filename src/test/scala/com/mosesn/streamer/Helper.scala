package com.mosesn.streamer

object Helper {
  def counterIterator: Iterator[Int] = new Iterator[Int] {
    def hasNext: Boolean = true
    var cur = -1
    def next(): Int = {
      cur += 1
      cur
    }
  }

  def counterIterable: Iterable[Int] = new Iterable[Int] {
    override def iterator: Iterator[Int] = counterIterator
  }
}
