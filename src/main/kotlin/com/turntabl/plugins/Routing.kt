package com.turntabl.plugins

import com.apptastic.rssreader.Item
import com.apptastic.rssreader.RssReader
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import java.util.stream.Collectors
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.Duration


fun Application.configureRouting() {
    // Starting point for a Ktor app:
    routing {
        get("/") {
            val url = "https://technovagh.com/feed/"
            displayRssFeedInConsole(url)
            val news = getRssFeedAsString(url)
            call.respondText(news.toString())
        }
    }

}

fun getRssFeedAsString(url: String): String? {
    val req: HttpRequest = HttpRequest.newBuilder(URI.create(url))
        .timeout(Duration.ofSeconds(25))
        .GET()
        .build()
    val client: HttpClient = HttpClient.newBuilder()
        .followRedirects(HttpClient.Redirect.NORMAL)
        .build()
    val response: HttpResponse<String> = client.send(req, HttpResponse.BodyHandlers.ofString())
    return response.body()
}

fun displayRssFeedInConsole(url: String): Unit {
    val reader = RssReader()
    val rssFeed : List<Item> = reader.read(url).collect(Collectors.toList())

    val strbul = StringBuilder()
    val channel = rssFeed.get(0).channel
    //Channel Details
    strbul.append("\n")
    strbul.append("Channel Details")
    strbul.append("\n")
    strbul.append("(")
    strbul.append(" Channel Title: ")
    strbul.append(channel.title.toString())
    strbul.append(", ")
    strbul.append("Channel Description: ")
    strbul.append(channel.description.toString())
    strbul.append(", ")
    strbul.append("Channel Link: ")
    strbul.append(channel.link.toString())
    strbul.append(")")
    strbul.append("\n\n")
    strbul.append("Item Details")

    rssFeed.forEach { item ->
        //Item Details
        strbul.append("\n")
        strbul.append("(")
        strbul.append(" Item Title: ")
        strbul.append(item.title.get())
        strbul.append(", ")
        strbul.append("Item Description: ")
        strbul.append(item.description.get())
        strbul.append(", ")
        strbul.append("Item Link: ")
        strbul.append(item.link.get())
        strbul.append(")")
        strbul.append("\n")
    }

    val rssDataToStr = strbul.toString()
    println(rssDataToStr)
}
