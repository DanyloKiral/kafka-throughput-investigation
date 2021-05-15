package processor_service

import java.io.FileWriter
import java.nio.file.{Paths, Files}
import au.com.bytecode.opencsv.CSVWriter

class DataWriter (fileName: String) {
  def initOutputFile(): Unit = {
    if (!Files.exists(Paths.get(fileName))) {
      writeCsvLineToFile(Array("id", "author", "comment", "created_at"))
    }
  }

  def writeRecord(record: Map[String, Any]): Unit = {
    val ticks = record("created_utc")

    writeCsvLineToFile(Array(record("id").toString, record("author").toString, record("body").toString, ticks.toString))
  }

  private def writeCsvLineToFile(line: Array[String]): Unit = {
    val fileWriter = new FileWriter(fileName, true)
    val csvWriter = new CSVWriter(fileWriter)
    csvWriter.writeNext(line:_*)
    fileWriter.close()
  }
}
