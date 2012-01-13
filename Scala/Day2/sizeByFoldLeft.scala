val strings = List("i", "am", "the", "god", "of", "hellfire")
println("Size of list is " + strings.foldLeft(0)((total, elem) => total + 1))
