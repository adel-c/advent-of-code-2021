fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    day1()
    day2()
    day3()
}


private fun day1() {
    val day1 = Day1()
    println("day1 count: ${day1.count1()} /1791")
    println("day1 count3: ${day1.count3()} /1822")
}

private fun day2() {
    val day2 = Day2()
    val position = day2.position()
    println("day2 count: $position /Position(horizontal=1895, depth=894)")
    println("day2 count: ${position.factor()} /1694130")

    val position2 = day2.position2()
    println("day2 2 count: $position2 / Position2(horizontal=1895, aim=894, depth=896491)")
    println("day2 2 count: ${position2.factor()} /1698850445")
}

fun day3() {
    val day3 = Day3()
    val diag = day3.diag()
    println("day3 gamma: ${diag.gamma()} / 3529")
    println("day3 epsilon: ${diag.epsilon()} / 566")
    println("day3 factor: ${diag.gamma() * diag.epsilon()} / 1997414")
    println("day3 oxygen: ${diag.oxygen()} / 3573")
    println("day3 co2: ${diag.co2()} / 289")
    println("day3 factor2: ${diag.oxygen() * diag.co2()} / 1032597")
}

