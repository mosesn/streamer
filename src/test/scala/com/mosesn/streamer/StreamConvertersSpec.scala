package com.mosesn.streamer

import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers
import scala.util.Random
import StreamConverters._

class StreamConvertersSpec extends FunSpec with ShouldMatchers {
  describe("StreamConverters") {
    it("should convert iterators to streams") {
      val stream = Helper.counterIterator.toLazyStream
      assert(stream.isInstanceOf[Stream[Int]])
    }

    it("should convert iterables to streams") {
      val stream = Helper.counterIterable.toLazyStream
      assert(stream.isInstanceOf[Stream[Int]])
    }

    it("should not allow iterators to be converted to streams twice") {
      val streamable: Streamable[Int] = Helper.counterIterator
      streamable.toLazyStream
      val throwable = evaluating { streamable.toLazyStream } should produce [Exception]
      throwable.getMessage should endWith ("You can't turn an iterator into a stream twice")
    }
  }
}
