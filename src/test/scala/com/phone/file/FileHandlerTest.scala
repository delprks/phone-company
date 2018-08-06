package com.phone.file

import org.scalatest.FunSuite

import scala.collection.parallel.mutable.ParArray

class FileHandlerTest extends FunSuite {

  test("load method returns list of call records from the input") {
    val fileHandler = new FileHandler()

    val input = ParArray(
      "A 555-333-212 00:02:03",
      "A 555-433-242 00:06:41",
      "B 555-333-212 00:01:20",
      "A 555-333-212 00:01:10"
    )

    val expected = ParArray(
      CallRecord("A", "555-333-212", 123),
      CallRecord("A", "555-433-242", 401),
      CallRecord("B", "555-333-212", 80),
      CallRecord("A", "555-333-212", 70)
    )

    assert(fileHandler.load(input) == expected)
  }

}
