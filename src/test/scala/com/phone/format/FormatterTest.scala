package com.phone.format

import com.phone.analyse.CustomerCallsCost
import org.scalatest.FunSuite

class FormatterTest extends FunSuite {

  test("format method should format the output correctly when the total cost is more than 0") {
    val actual = Formatter.format(CustomerCallsCost("A", 32.00))

    assert(actual == "Customer ID: A, total cost of calls: £0.32")
  }

  test("format method should format the output correctly when the total cost is 0") {
    val actual = Formatter.format(CustomerCallsCost("A", 0))

    assert(actual == "Customer ID: A, total cost of calls: £0.0")
  }

}
