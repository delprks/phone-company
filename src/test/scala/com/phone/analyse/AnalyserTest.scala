package com.phone.analyse

import com.phone.file.CallRecord

import scala.collection.parallel.mutable.ParArray

class AnalyserTest extends org.scalatest.FunSuite {

  test("duration method returns correct value for valid input") {
    assert(Analyser.duration("00:02:03") == 123)
  }

  test("duration method throws exception for invalid input") {
    val thrown = intercept[IllegalArgumentException] {
      Analyser.duration("2018-02-14T00:02:03")
    }

    assert(thrown.getMessage == "Invalid duration format")
  }

  test("customerRecord method should group customer call records by customer ID") {
    val input = ParArray(
      CallRecord("A", "555-333-212", 123),
      CallRecord("A", "555-433-242", 401),
      CallRecord("B", "555-333-212", 80),
      CallRecord("A", "555-333-212", 70)
    )

    val expected = ParArray[CustomerRecord](
      CustomerRecord("A", ParArray(
        CallRecord("A", "555-333-212", 123),
        CallRecord("A", "555-433-242", 401),
        CallRecord("A", "555-333-212", 70)
      )),
      CustomerRecord("B", ParArray(
        CallRecord("B", "555-333-212", 80)
      ))
    )

    assert(Analyser.customerRecord(input) == expected)
  }
}
