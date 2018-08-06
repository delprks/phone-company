package com.phone.analyse

import com.phone.file.CallRecord

import scala.collection.parallel.mutable.ParArray
import scala.util.matching.Regex

object Analyser {
  private final val DURATION_FORMAT: Regex = "(^\\d+:\\d{2}:\\d{2}$)".r

  def duration(isoDuration: String): Int = {
    if (!DURATION_FORMAT.pattern.matcher(isoDuration).matches) throw new IllegalArgumentException("Invalid duration format")

    val durationArr = isoDuration.split(":")

    3600 * durationArr(0).toInt + 60 * durationArr(1).toInt + durationArr(2).toInt
  }

  def customerRecord(callRecords: ParArray[CallRecord]): ParArray[CustomerRecord] = {
    callRecords.groupBy(_.customerId).toParArray.par.map {
      case (id: String, callRecords: ParArray[CallRecord]) => CustomerRecord(id, callRecords)
    }
  }
}
