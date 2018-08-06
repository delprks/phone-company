package com.phone

import com.phone.analyse.{Analyser, CostCalculator, CustomerCallsCost, CustomerRecord}
import com.phone.file.{CallRecord, FileHandler}
import com.phone.format.Formatter

import scala.collection.parallel.mutable.ParArray

object Main extends App {
  private final val CALL_LOG = "calls.log"
  private final val COST_BOUNDARY_IN_SEC = 180
  private final val MORE_THAN_BOUNDARY_COST_PER_SEC = 0.03
  private final val LESS_THAN_BOUNDARY_COST_PER_SEC = 0.05
  private final val APPLY_PROMOTION = true

  val fileHandler = new FileHandler
  val costCalculator = new CostCalculator(COST_BOUNDARY_IN_SEC, MORE_THAN_BOUNDARY_COST_PER_SEC, LESS_THAN_BOUNDARY_COST_PER_SEC)

  val callLogFile: ParArray[String] = fileHandler.file(CALL_LOG)
  val callLogs: ParArray[CallRecord] = fileHandler.load(callLogFile)

  val customerCallRecords: ParArray[CustomerRecord] = Analyser.customerRecord(callLogs)
  val customerCallsCost: ParArray[CustomerCallsCost] = customerCallRecords.par.map(callRecord => costCalculator.calculate(callRecord, APPLY_PROMOTION))
  val formattedCost = customerCallsCost.map(cost => Formatter.format(cost))

  formattedCost.foreach(println)
}
