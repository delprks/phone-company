package com.phone.analyse

class CostCalculator(moreThanBoundaryCostPerSec: Double, lessThanBoundaryCostPerSec: Double) {
  private final val COST_BOUNDARY_IN_SECONDS = 180

  def calculate(customerRecord: CustomerRecord, promotion: Boolean = false): CustomerCallsCost = {
    val records = if (promotion) {
      customerRecord.callRecords.toIndexedSeq.sortBy(- _.duration).drop(1)
    } else customerRecord.callRecords

    val cost = records.map(record => calculateCallCost(record.duration)).sum

    CustomerCallsCost(customerRecord.id, cost)
  }

  private def calculateCallCost(duration: Int): Double = duration match {
    case callDuration if callDuration <= COST_BOUNDARY_IN_SECONDS => callDuration * lessThanBoundaryCostPerSec
    case _ =>
      val moreThanBoundaryDuration = duration - COST_BOUNDARY_IN_SECONDS

      (COST_BOUNDARY_IN_SECONDS * lessThanBoundaryCostPerSec) + (moreThanBoundaryDuration * moreThanBoundaryCostPerSec)
  }

}
