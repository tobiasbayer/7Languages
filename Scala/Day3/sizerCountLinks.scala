import scala.io._
import scala.actors._
import Actor._

object PageLoader {
  def getPageSize(url: String) = Source.fromURL(url).mkString.length 

  def getLinkCount(url: String) = {
    val content = Source.fromURL(url).mkString
    val linkRegEx = "<a".r
    linkRegEx.findAllIn(content).length
  }
}

val urls = List("http://www.amazon.com/",
                "http://www.twitter.com/",
		"http://www.google.com/",
		"http://www.cnn.com/")

def timeMethod(method: () => Unit) = {
  val start = System.nanoTime
  method()
  val end = System.nanoTime
  println("Method took " + (end - start)/1000000000.0 + " seconds.")
}

def getPageSizeSequentially() = {
  for(url <- urls) {
    println("Size for " + url + ": " + PageLoader.getPageSize(url))
    println("Number of links for " + url + ": " + PageLoader.getLinkCount(url))
    println
  }
}

def getPageSizeConcurrently() = {
  val caller = self

  for(url <- urls) {
    actor { caller ! (url, PageLoader.getPageSize(url), PageLoader.getLinkCount(url)) }
  }

  for(i <- 1  to urls.size) {
    receive {
      case (url, size, links) => 
        println("Size for " + url + ": " + size)
	println("Number of links for " + url + ": " + links)
	println
    }
  }
}

println("Sequential run:")
timeMethod { getPageSizeSequentially }

for(i <- 1 to 2) println

println("Concurrent run:")
timeMethod { getPageSizeConcurrently }
