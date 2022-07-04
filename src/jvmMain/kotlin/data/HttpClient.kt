package data

import data.dto.ExamDto
import data.dto.RulesDto
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

/*
    Http client for request on server
 */
class HttpClient {
    /*
    Instance server
     */
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = false
                }
            )
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
        defaultRequest {
            url("http://localhost:8085")
        }
    }

    /*
    Get rules from server
     */
    suspend fun getRules(): RulesDto {
        return client.get("rules").body()
    }

    /*
    Get Exam from server
     */
    suspend fun getExam(): ExamDto {
        return client.get("exam").body()
    }
}
