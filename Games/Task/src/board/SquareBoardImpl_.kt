package board

import com.sun.xml.internal.bind.v2.TODO

open class SquareBoardImpl_(override val width: Int) : SquareBoard {

    private val allCells = mutableListOf<Cell>()

    init {
        for (i in 1..width){
            for (j in 1..width){
                allCells.add(Cell(i,j))
            }
        }
    }

    private fun getPosition(a: Int, b: Int): Int{

        var pos = 0
        for (i in 1..width){
            if (a == i) {
                for (j in 1..width) {
                    if (b == j){
                        //println("getCellorNull  : ${allCells[pos]}")
                        return pos
                    }
                    pos += 1
                }
            }else{
                pos += width
            }
        }
        return  -1
    }


    override fun getCellOrNull(a: Int, b: Int): Cell? {

        if ((a in 1..width) && (b in 1..width)){

            return allCells[getPosition(a,b)]

            /*var pos = 0
            for (i in 1..width){
                if (a == i) {
                    for (j in 1..width) {
                        if (b == j){
                            //println("getCellorNull  : ${allCells[pos]}")
                            return allCells[pos]
                        }
                        pos += 1
                    }
                }else{
                    pos += width
                }
            }*/

            /* for (i in 1..width){
                 for (j in 1..width) {
                     if (a == i && b == j){
                         break
                     }
                     pos += 1
                 }
             }
 */
            /*println("getCellorNull  : ${allCells[pos]}")
            return allCells[pos]*/
        }

        return null
    }

    override fun getCell(a: Int, b: Int): Cell {

        if ((a !in 1..width) || (b !in 1..width)){
            throw IllegalArgumentException()
        }

        //println("\nparams: ( $a, $b)")
        //println("allCells: $allCells")
        /*   println("getPosition: ${getPosition(a, b)}")
           val pos = getPosition(a,b)
           println("Pos: $pos")
           println("Cell: ${allCells[pos]}")*/

        /*var pos = 0
        for (i in 1..width){
            if (a == i) {
                for (j in 1..width) {
                    if (b == j){
                        //println("getCell  : ${allCells[pos]}")
                        return allCells[pos]
                    }
                    pos += 1
                }
            }else{
                pos += width
            }
        }
       *//* for (i in 1..width){
            for (j in 1..width) {
                if (a == i && b == j){
                     break
                }
                pos += 1
            }
        }*//*

        //println("getCell  : ${allCells[pos]}")
        return allCells[pos]*/
        return allCells[getPosition(a,b)]
    }

    override fun getAllCells(): Collection<Cell> {
        return allCells
    }

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {

        val list = mutableListOf<Cell>()

        /* println("jRange: $jRange")
         println("jRange First: ${jRange.first}")
         println("jRange Last: ${jRange.last}")*/

        if (jRange.first > jRange.last){
            /* for (j in jRange.first downTo i) {
                 list.add(Cell(i, j))
             }*/

            if (jRange.first < width){
                for (j in jRange) {
                    list.add(allCells[getPosition(i, j)])
                }
            }else{
                for (j in jRange.first downTo i) {
                    list.add(allCells[getPosition(i, j)])
                }
            }

        }else {

            if (jRange.last < width){
                for (j in jRange) {
                    list.add(allCells[getPosition(i, j)])
                }
            }else{
                for (j in jRange.first..width) {
                    list.add(allCells[getPosition(i, j)])
                }
            }
        }

        return list
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        val list = mutableListOf<Cell>()

        /*println("iRange: $iRange")
        println("iRange First: ${iRange.first}")
        println("iRange Last: ${iRange.last}")*/

        if (iRange.first > iRange.last){

            if (iRange.last < width){
                for (i in iRange) {
                    list.add(allCells[getPosition(i, j)])
                }
            }else{
                for (i in iRange.first downTo j) {
                    list.add(allCells[getPosition(i, j)])
                }
            }

        }else {

            if (iRange.last < width){
                for (i in iRange) {
                    list.add(allCells[getPosition(i, j)])
                }
            }else{
                for (i in iRange.first..width) {
                    list.add(allCells[getPosition(i, j)])
                }
            }
        }

        return list
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {

        println("Cell  : $this")

        return when(direction){

            Direction.UP -> if (i > 1) allCells[getPosition(i,j)-width]
            else null
            Direction.DOWN -> if (i < width) allCells[getPosition(i,j)+width]
            else null
            Direction.RIGHT -> if (j < width) allCells[getPosition(i,j)+1]
            else null
            Direction.LEFT -> if (j > 1) allCells[getPosition(i,j)-1]
            else null
        }
    }
}