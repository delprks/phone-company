package com.phone.analyse

import scala.collection.parallel.mutable.ParArray

class CostCalculator(moreThanBoundaryCostPerSec: Double, lessThanBoundaryCostPerSec: Double) {
  private final val COST_BOUNDARY_IN_SECONDS = 180

  def calculate(customerRecords: ParArray[CustomerRecord]): ParArray[CustomerCallsCost] = {
    customerRecords.map { customerRecord =>
        val cost = customerRecord.callRecords.map(record => calculateCallCost(record.duration)).sum

        CustomerCallsCost(customerRecord.id, cost)
    }
  }

  private def calculateCallCost(duration: Int): Double = duration match {
    case callDuration if callDuration <= COST_BOUNDARY_IN_SECONDS => callDuration * lessThanBoundaryCostPerSec
    case _ =>
      val moreThanBoundaryDuration = duration - COST_BOUNDARY_IN_SECONDS

      (COST_BOUNDARY_IN_SECONDS * lessThanBoundaryCostPerSec) + (moreThanBoundaryDuration * moreThanBoundaryCostPerSec)
  }

}
