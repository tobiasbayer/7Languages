trait Censor {

  val toCensor = readCensorMap
  
  def censor(message: String) = {
    val words = message.split(" ")
    // return either the substitution or the word itself if there's no substitution (getOrElse)
    val censored = words.map(word => toCensor.getOrElse(word, word))
    // something like list.join() using foldLeft
    censored.foldLeft("")((joined, elem) => joined + (if(joined == "") "" else " ") + elem)
  }

  def readCensorMap = {
    var map = Map[String, String]()
    scala.io.Source.fromFile("subst.txt", "UTF8").getLines.foreach {
      line => 
      val key = line.split(" ")(0)
      val value = line.split(" ")(1)
      map += (key -> value)
    }

    map
  }
}

class Censee extends Object with Censor {
  
  def output(message: String) {
    println(censor(message))
  }
}

val c = new Censee

c.output("Darn Shoot Code Scala Fun")
