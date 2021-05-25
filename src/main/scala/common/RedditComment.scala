package common

import java.time.LocalDateTime

case class RedditComment(id: String, author: String, comment: String, processingStartedAt: LocalDateTime)
case class CommentStatistics(id: String, processingStart: LocalDateTime, processingEnd: LocalDateTime, messageSizeBytes: Int)
