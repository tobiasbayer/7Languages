trait Censor {

  val toCensor = Map("Shoot" -> "Pucky", "Darn" -> "Beans")
  
  def censor(message: String) = {
    val words = message.split(" ")
    // return either the substitution or the word itself if there's no substitution (getOrElse)
    val censored = words.map(word => toCensor.getOrElse(word, word))
    // something like list.join() using foldLeft
    censored.foldLeft("")((joined, elem) => joined + (if(joined == "") "" else " ") + elem)
  }
}

class Censee extends Object with Censor {
  
  def output(message: String) {
    println(censor(message))
  }
}

val c = new Censee

c.output("Darn Shoot Code Scala Fun")
