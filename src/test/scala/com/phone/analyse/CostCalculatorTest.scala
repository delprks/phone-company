package com.phone.analyse

import com.phone.file.CallRecord
import org.scalatest.FunSuite

import scala.collection.parallel.mutable.ParArray

class CostCalculatorTest extends FunSuite {

  test("cost calculator method returns the correct cost of calls without applying the promotion") {
    val costCalculator = new CostCalculator(moreThanBoundaryCostPerSec = 0.03, lessThanBoundaryCostPerSec = 0.05)

    val customerA = CustomerRecord("A", ParArray(
      CallRecord("A", "555-333-212", 123),
      CallRecord("A", "555-433-242", 401),
      CallRecord("A", "555-333-212", 70)
    ))

    val customerB = CustomerRecord("B", ParArray(
      CallRecord("B", "555-333-212", 80)
    ))

    assert(costCalculator.calculate(customerA) == CustomerCallsCost("A", 25.28)) //(123 * 0.05) + (180 * 0.05 + 221 * 0.03) + (70 * 0.05)
    assert(costCalculator.calculate(customerB) == CustomerCallsCost("B", 4)) //(80 * 0.05)
  }

  test("cost calculator method returns the correct cost of calls and applies the promotion") {
    val costCalculator = new CostCalculator(moreThanBoundaryCostPerSec = 0.03, lessThanBoundaryCostPerSec = 0.05)

    val customerA = CustomerRecord("A", ParArray(
      CallRecord("A", "555-333-212", 123),
      CallRecord("A", "555-433-242", 401),
      CallRecord("A", "555-333-212", 70)
    ))

    val customerB = CustomerRecord("B", ParArray(
      CallRecord("B", "555-333-212", 80)
    ))

    assert(costCalculator.calculate(customerA, promotion = true) == CustomerCallsCost("A", 9.65)) //(123 * 0.05) + (70 * 0.05)
    assert(costCalculator.calculate(customerB, promotion = true) == CustomerCallsCost("B", 0))
  }
}
