package com.phone.analyse

import com.phone.file.CallRecord
import org.scalatest.FunSuite

import scala.collection.parallel.mutable.ParArray

class CostCalculatorTest extends FunSuite {

  test("cost calculator method returns the correct cost of calls") {
    val costCalculator = new CostCalculator(moreThanBoundaryCostPerSec = 0.03, lessThanBoundaryCostPerSec = 0.05)

    val input = ParArray[CustomerRecord](
      CustomerRecord("A", ParArray(
        CallRecord("A", "555-333-212", 123),
        CallRecord("A", "555-433-242", 401),
        CallRecord("A", "555-333-212", 70)
      )),
      CustomerRecord("B", ParArray(
        CallRecord("B", "555-333-212", 80)
      ))
    )

    val expected = ParArray(
      CustomerCallsCost("A", 25.28), //6.15 + (180 * 0.05 + 221 * 0.03) + (70 * 0.05)
      CustomerCallsCost("B", 4) //80 * 0.05
    )

    assert(costCalculator.calculate(input) == expected)
  }
}
