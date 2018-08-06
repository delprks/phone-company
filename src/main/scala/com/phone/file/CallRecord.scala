package com.phone.file

import com.phone.analyse.Analyser

case class CallRecord(customerId: String, phoneNumber: String, duration: Int)

object CallRecord {
  def apply(customerId: String, phoneNumber: String, duration: String): CallRecord = CallRecord(customerId, phoneNumber, Analyser.duration(duration))
}
