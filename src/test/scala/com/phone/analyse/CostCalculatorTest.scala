package com.phone.analyse

import com.phone.file.CallRecord
import org.scalatest.FunSuite

import scala.collection.parallel.mutable.ParArray

class CostCalculatorTest extends FunSuite {

  test("cost calculator method returns the correct cost of calls without applying the promotion") {
    val costCalculator = new CostCalculator(costBoundaryInSec = 180, moreThanBoundaryCostPerSec = 0.03, lessThanBoundaryCostPerSec = 0.05)

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
    val costCalculator = new CostCalculator(costBoundaryInSec = 180, moreThanBoundaryCostPerSec = 0.03, lessThanBoundaryCostPerSec = 0.05)

    val customerA = CustomerRecord("A", ParArray(
      CallRecord("A", "555-333-212", 123),
      CallRecord("A", "555-433-242", 402),
      CallRecord("A", "555-333-212", 70),
      CallRecord("A", "555-333-212", 110),
      CallRecord("A", "555-333-212", 30),
      CallRecord("A", "555-333-212", 240)
    ))

    val customerB = CustomerRecord("B", ParArray(
      CallRecord("B", "555-333-212", 30),
      CallRecord("B", "555-433-242", 80),
      CallRecord("B", "555-333-212", 70)
    ))

    assert(costCalculator.calculate(customerA, promotion = true) == CustomerCallsCost("A", 15.66)) //drops cost of calling 555-333-212
    assert(costCalculator.calculate(customerB, promotion = true) == CustomerCallsCost("B", 4)) //drops the cost of calling 555-333-212
  }
}
