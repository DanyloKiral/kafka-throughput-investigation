package statistics_service

import java.time.LocalDateTime

case class StatisticsDetails(startTime: LocalDateTime, endTime: LocalDateTime, dataSizeMb: Double, processingTimesMs: List[Long])
case class StatisticsModel(avgThroughputMbps: Double, maxLatencyMs: Long)
