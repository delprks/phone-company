package com.phone.file

import java.io.IOException

import scala.collection.parallel.mutable.ParArray
import scala.io.Source.fromResource

class FileHandler {

  def file(name: String): ParArray[String] = {
    try {
      fromResource(name).getLines().toIndexedSeq.toParArray
    } catch {
      case _: Exception => throw new IOException(s"Could not load $name")
    }
  }

  def load(lines: ParArray[String]): ParArray[CallRecord] = {
    lines.map { record =>
      val recordArr = record.split(" ")

      CallRecord(recordArr(0), recordArr(1), recordArr(2))
    }
  }
}
