class Day16(path: String = "day16/input") {
    private val inputData: List<String> = path.fromResource().readLines()


    fun compute(): Int {
        println(hexToBin())
        return PacketParser(hexToBin()).count()
    }

    fun compute2(): Int {
        return 0
    }

    fun hexToBin(): String {
        return inputData[0].toCharArray().map { hexCharToBinary(it.toString()) }.joinToString("")
    }

    fun hexCharToBinary(hex: String): String {
        val i = hex.toInt(16)
        return Integer.toBinaryString(i).padStart(4, '0')
    }

    data class Packet(val version: Int, val typeId: Int, val binaryMessage: String)
    data class PacketParser(val binaryMessage: String) {
        fun String.binToInt() = this.toInt(2)
        fun parse(): Packet {
            val version: Int = packetVesion()
            val typeId: Int = typeId()
            val packet: Packet = if (typeId == 4) {
                parseLiteralPacket(version, typeId, binaryMessage)
            } else {
                parseOperatorPacket(version, typeId, binaryMessage)
            }
            return packet
        }

        fun count(): Int {

            val packet = parse()
            println("${packet.version} ${packet.typeId}")
            return 0
        }

        private fun parseLiteralPacket(version: Int, typeId: Int, binaryMessage: String): Packet {
            TODO("Not yet implemented")
        }

        private fun parseOperatorPacket(version: Int, typeId: Int, binaryMessage: String): Packet {
            TODO("Not yet implemented")
        }


        private fun typeId(): Int {
            val substring = binaryMessage.substring(3, 6)
            return substring.binToInt()
        }

        private fun packetVesion(): Int {
            val substring = binaryMessage.substring(0, 3)
            return substring.binToInt()
        }


    }
}
