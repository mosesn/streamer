package com.mosesn.streamer

import org.scalatest.FunSpec
import org.scalatest.time.SpanSugar._
import org.scalatest.concurrent.Timeouts
import org.scalatest.matchers.ShouldMatchers
import scala.util.Random

class StreamerSpec extends FunSpec with ShouldMatchers with Timeouts {
  val rand = new Random()

  describe("Streamer") {
    it("should convert iterables") {
      val stream = failAfter(10 millis) {
        Streamer(Helper.counterIterable)
      }
      assert(stream.isInstanceOf[Stream[Int]])
    }

    it("should convert short iterators to streams") {
      val num = rand.nextInt(50)
      val list = (0 until num).toList
      val iter = list.iterator
      val stream = Streamer(iter)
      stream should have size (num)
      assert(stream.isInstanceOf[Stream[Int]])
      stream.toList should be (list)
    }

    it("should convert empty iterators to streams") {
      val stream = Streamer(Nil.iterator)
      stream should be ('empty)
      assert(stream.isInstanceOf[Stream[Int]])
      stream.toList should be (Nil)
    }

    it("should convert infinite iterators to streams") {
      val stream = failAfter(10 millis) {
        Streamer(Helper.counterIterator)
      }
      assert(stream.isInstanceOf[Stream[Int]])
    }

    it("should convert infinite iterators to streams that can be mapped over") {
      val stream = failAfter(10 millis) {
        Streamer(Helper.counterIterator)
      }
      assert(stream.isInstanceOf[Stream[Int]])
      val factor = rand.nextInt(20)
      val num = rand.nextInt(10)
      (stream map (_ * factor) take num).toSeq should be ((0 until (factor * num) by factor).toSeq)
    }

    // This test should never complete.  Feel free to run it, but know it won't complete
    ignore("should convert infinite iterators to streams that cannot be reduced on") {
      val stream = failAfter(10 millis) {
        Streamer(Helper.counterIterator)
      }

      assert(stream.isInstanceOf[Stream[Int]])
      val num = rand.nextInt(10)
      (stream take num).toSeq should be ((0 until num).toSeq)
      stream reduce (_ + _)
    }
  }
}
