class Day16(path: String = "day16/input") {
    private val inputData: List<String> = path.fromResource().readLines()


    fun compute(): Int {
        println(hexToBin(inputData[0]))
        return PacketParser(hexToBin(inputData[0])).count()
    }

    fun compute2(): Int {
        return 0
    }

    companion object{
        fun hexToBin(input:String): String {
            return input.toCharArray().map { hexCharToBinary(it.toString()) }.joinToString("")
        }

        fun hexCharToBinary(hex: String): String {
            val i = hex.toInt(16)
            return Integer.toBinaryString(i).padStart(4, '0')
        }
    }


    open class Packet(open val version: Int, open val typeId: Int)
    data class LiteralPacket(override val version: Int, override val typeId: Int, val value: String) :
        Packet(version, typeId)

    data class OperatorPacket(
        override val version: Int,
        override val typeId: Int,
        val subPacket: List<Packet>
    ) : Packet(version, typeId)

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
            val packets = binaryMessage.substring(6)
            val chunked = packets.chunked(5)
            val count = chunked.takeWhile { it[0] != '0' }.size + 1
            val value = chunked.subList(0, count).joinToString("") { it.substring(1) }.binToInt().toString()
           return LiteralPacket(version,typeId,value)
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
