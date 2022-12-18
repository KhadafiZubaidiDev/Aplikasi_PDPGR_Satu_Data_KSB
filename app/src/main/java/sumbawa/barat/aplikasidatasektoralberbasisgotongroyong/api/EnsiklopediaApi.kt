package sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.api

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.util.*
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.model.Ensiklopedia

class EnsiklopediaApi {

    @OptIn(KtorExperimentalAPI::class)
    private val client = HttpClient(CIO) {
        install(DefaultRequest) {
            headers.append("Content-Type", "application/json")
        }

        install(JsonFeature) {
            serializer = GsonSerializer()
        }
    }

    @KtorExperimentalAPI
    suspend fun tampilDataEnsklopediaSeluruhnya(): List<Ensiklopedia> {
        try {
            return client.get {
                url("https://khadafizubaidi.xyz/api/tampilDataEnsiklopediaSeluruhnya")
            }
        } catch (e : java.lang.Exception) {
            return emptyList()
        }

    }
}