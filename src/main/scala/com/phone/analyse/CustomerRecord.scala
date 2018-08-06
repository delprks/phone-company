package com.phone.analyse

import com.phone.file.CallRecord

import scala.collection.parallel.mutable.ParArray

case class CustomerRecord(id: String, callRecords: ParArray[CallRecord])
