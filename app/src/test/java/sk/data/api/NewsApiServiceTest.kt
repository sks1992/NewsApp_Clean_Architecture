package sk.data.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sk.sandeep.newsapp.data.api.NewsApiService

class NewsApiServiceTest {

    private lateinit var service: NewsApiService
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApiService::class.java)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }


    private fun enqueueMockResponse(
        fileName: String
    ) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()

        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)
    }

    @Test
    fun getTopHeadlines_sendRequest_receivedExpected() {
        runBlocking {
            enqueueMockResponse("newsresponse.json")
            val responseBody = service.getTopHeadlines("us", 1).body()
            val request = server.takeRequest()

            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/v2/top-headlines?country=us&page=1&apiKey=e004331040d8464892c01b7f4b70641f")
        }
    }

    @Test
    fun getTopHeadlines_receivedResponse_correctPageSize() {
        runBlocking {
            enqueueMockResponse("newsresponse.json")
            val responseBody = service.getTopHeadlines("us", 1).body()

            val articlesList = responseBody!!.articles
            assertThat(articlesList.size).isEqualTo(20)
        }
    }

    @Test
    fun getTopHeadlines_receivedResponse_correctContent() {
        runBlocking {
            enqueueMockResponse("newsresponse.json")
            val responseBody = service.getTopHeadlines("us", 1).body()

            val articlesList = responseBody!!.articles
            val article = articlesList[0]

            assertThat(article.author).isEqualTo("Julie Bosman, Amy Harmon, Albert Sun, Chloe Reynolds, Sarah Cahalan")

            assertThat(article.url).isEqualTo("https://www.nytimes.com/live/2021/12/15/world/covid-omicron-vaccines")
        }
    }

}