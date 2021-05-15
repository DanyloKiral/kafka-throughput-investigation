package processor_service

import java.io.FileWriter
import java.nio.file.{Files, Paths}
import java.time.LocalDateTime
import au.com.bytecode.opencsv.CSVWriter

class DataWriter (fileName: String) {
  def initOutputFile(): Unit = {
    if (!Files.exists(Paths.get(fileName))) {
      writeCsvLineToFile(Array(
        "id", "author", "comment", "created_at", "processing_started_at", "processing_finished_at", "message_size"))
    }
  }

  def writeRecord(id: String, author: String, comment: String,
                  created_at: String, processing_started_at: LocalDateTime,
                  processing_finished_at: LocalDateTime, messageSize: Long): Unit = {
    writeCsvLineToFile(Array(id, author, comment, created_at, processing_started_at, processing_finished_at, messageSize))
  }

  private def writeCsvLineToFile(line: Array[String]): Unit = {
    val fileWriter = new FileWriter(fileName, true)
    val csvWriter = new CSVWriter(fileWriter)
    csvWriter.writeNext(line:_*)
    fileWriter.close
  }
}
