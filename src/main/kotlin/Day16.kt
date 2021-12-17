class Day16(path: String = "day16/input") {
    private val inputData: List<String> = path.fromResource().readLines()


    fun compute(): Int {
        return PacketParser(hexToBin(inputData[0])).count()
    }

    fun compute2(): Long {
        return PacketParser(hexToBin(inputData[0])).count2()
    }

    companion object {
        fun hexToBin(input: String): String {
            val joinToString = input.toCharArray().map { hexCharToBinary(it.toString()) }.joinToString("")
            println(joinToString)
            return joinToString
        }

        fun hexCharToBinary(hex: String): String {
            val i = hex.toInt(16)
            return Integer.toBinaryString(i).padStart(4, '0')
        }
    }


    open class Packet(open val version: Int, open val typeId: Int, open val length: Int) {
        open fun versionCount(): Int = version
        open fun typedCount(): Long {
            TODO("Not yet implemented")
        }
    }

    data class LiteralPacket(
        override val version: Int,
        override val typeId: Int,
        override val length: Int,
        val value: String
    ) :
        Packet(version, typeId, length) {
        override fun typedCount(): Long {
            return value.toLong()
        }
    }

    data class OperatorPacket(
        override val version: Int,
        override val typeId: Int,
        override val length: Int,
        val subPacket: List<Packet>
    ) : Packet(version, typeId, length) {
        override fun versionCount(): Int {
            return version + subPacket.sumOf { it.versionCount() }
        }

        override fun typedCount(): Long {
            return when (typeId) {
                0 -> subPacket.sumOf { it.typedCount() }
                1 -> subPacket.fold(1) { acc, next -> acc * next.typedCount() }
                2 -> subPacket.minOf { it.typedCount() }
                3 -> subPacket.maxOf { it.typedCount() }
                5 -> if (subPacket.first().typedCount() > subPacket.last().typedCount()) 1 else 0
                6 -> if (subPacket.first().typedCount() < subPacket.last().typedCount()) 1 else 0
                7 -> if (subPacket.first().typedCount() == subPacket.last().typedCount()) 1 else 0
                else -> error("Invalid Operator type")
            }
        }
    }

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
            return packet.versionCount()
        }

        fun count2(): Long {

            val packet = parse()
            return packet.typedCount()
        }

        private fun parseLiteralPacket(version: Int, typeId: Int, binaryMessage: String): Packet {
            val packets = binaryMessage.substring(6)
            val chunked = packets.chunked(5)
            val count = chunked.takeWhile { it[0] != '0' }.size + 1
            val literral = chunked.subList(0, count).joinToString("") { it.substring(1) }
            val value = literral.toLong(2).toString()
            return LiteralPacket(version, typeId, (count * 5) + 6, value)
        }

        private fun parseOperatorPacket(version: Int, typeId: Int, binaryMessage: String): Packet {
            val packetsCount = binaryMessage[6] == '1'
            val messageLengthBitCount = if (packetsCount) 11 else 15
            val headerPlusOperatorLength = 8 + messageLengthBitCount - 1
            val packetsSize = binaryMessage.substring(8, headerPlusOperatorLength).binToInt()
            val subPacketsBinary = binaryMessage.substring(headerPlusOperatorLength)
            val decrement: (p: Packet) -> Int = if (packetsCount) { _ -> 1 } else { p -> p.length }
            val subPacket = parseSubPackets(subPacketsBinary, packetsSize, decrement)
            return OperatorPacket(
                version,
                typeId,
                8 + messageLengthBitCount + subPacket.sumOf { it.length } - 1,
                subPacket
            )

        }

        private fun parseSubPackets(
            binaryMessage: String,
            initialPacketsSize: Int,
            packetSizeDec: (p: Packet) -> Int
        ): MutableList<Packet> {
            var packetsSize = initialPacketsSize
            var subBinary = binaryMessage
            val subPacket = mutableListOf<Packet>()
            var packetShift = 0
            while (packetsSize > 0) {
                subBinary = subBinary.substring(packetShift)
                val packet = PacketParser(
                    subBinary
                ).parse()
                packetShift = packet.length
                packetsSize -= packetSizeDec(packet)
                subPacket.add(packet)


            }
            return subPacket
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
