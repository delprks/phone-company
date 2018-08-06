package com.phone.file

import java.io.File

import scala.collection.parallel.mutable.ParArray
import scala.io.Source.fromFile

class FileHandler {

  final val SUPPORTED_FILES = List("log")

  def file(name: String): ParArray[String] = {
    val file = new File(name)

    if (file.exists && SUPPORTED_FILES.exists(file.getName.endsWith(_))) {
      fromFile(file).getLines().toIndexedSeq.toParArray
    } else {
      throw new IllegalArgumentException("Invalid file type provided. Only .log files are supported")
    }
  }

  def load(lines: ParArray[String]): ParArray[CallRecord] = {
    lines.map { record =>
      val recordArr = record.split(" ")

      CallRecord(recordArr(0), recordArr(1), recordArr(2))
    }
  }
}
