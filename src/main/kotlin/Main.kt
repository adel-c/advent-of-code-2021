fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    val day1 = Day1()
    println("day1 count: ${day1.count1()}")
    println("day1 count3: ${day1.count3()}")
}
