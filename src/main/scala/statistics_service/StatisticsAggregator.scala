package statistics_service

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import scala.concurrent.{ExecutionContext, Future}
import scala.util.Try

class StatisticsAggregator (private val dataReader: MongoDataReader)
                           (implicit executionContext: ExecutionContext) {

  def collectStatistics(): Future[StatisticsModel] =
    dataReader.read
      .map(list => StatisticsDetails(
        list.map(_.processingStart).min,
        list.map(_.processingEnd).max,
        list.map(_.messageSizeBytes / 1024.0 / 1024.0).sum,
        list.map(r => datesDiffSec(r.processingStart, r.processingEnd))))
      .map(details => StatisticsModel(
        Try(details.dataSizeMb / datesDiffSec(details.startTime, details.endTime)).getOrElse(0),
        details.processingTimesMs.max))

  private def datesDiffSec(x: LocalDateTime, y: LocalDateTime): Long = x.until(y, ChronoUnit.SECONDS)
}
