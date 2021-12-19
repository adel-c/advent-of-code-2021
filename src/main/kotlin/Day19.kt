class Day19(path: String = "day19/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun compute(): Long {

        val parse = parse()
        println(parse)
        return 0
    }
    fun compute2(): Long {
        return 0
    }

    fun parse():List<Scanner>{
        val scans= mutableListOf<Scanner>()
        val currentListPoint= mutableListOf<Point3d>()
        inputData.filter { it.isNotBlank() }.drop(1).forEach { l->
            if(l.startsWith("---")){
                scans.add(Scanner(currentListPoint.toList()))
                currentListPoint.clear()
            }else{
                val (x,y,z)=l.split(",").map { it.toInt() }
                currentListPoint.add(Point3d(x,y,z))
            }

        }
        scans.add(Scanner(currentListPoint.toList()))
        return scans
    }
    data class Scanner(val points:List<Point3d>)
    data class Point3d(val x:Int,val y:Int,val z:Int)
}
/*
--- scanner 0 ---
-809,-750,623
-853,-746,517
-136,-29,-84
 */
