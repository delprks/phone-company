package com.phone.analyse

class CostCalculator(costBoundaryInSec: Int, moreThanBoundaryCostPerSec: Double, lessThanBoundaryCostPerSec: Double) {

  def calculate(customerRecord: CustomerRecord, promotion: Boolean = false): CustomerCallsCost = {
    val cost = if (promotion) {
      val groupedCalls = customerRecord.callRecords.groupBy(_.phoneNumber)

      groupedCalls.par.map {
        case (_, callRecords) => callRecords.par.map(record => calculateCallCost(record.duration)).sum
      }.toIndexedSeq.sortBy(- _).drop(1).sum
    } else {
      customerRecord.callRecords.par.map(record => calculateCallCost(record.duration)).sum
    }

    CustomerCallsCost(customerRecord.id, cost)
  }

  private def calculateCallCost(duration: Int): Double = duration match {
    case callDuration if callDuration <= costBoundaryInSec => callDuration * lessThanBoundaryCostPerSec
    case _ =>
      val moreThanBoundaryDuration = duration - costBoundaryInSec

      (costBoundaryInSec * lessThanBoundaryCostPerSec) + (moreThanBoundaryDuration * moreThanBoundaryCostPerSec)
  }

}
