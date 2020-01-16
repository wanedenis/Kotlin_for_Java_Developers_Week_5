package board

class GameBoardImpl<T>(width: Int) : SquareBoardImpl(width), GameBoard<T> {

    private val map =  mutableMapOf<Cell, T?>()
    private val board = SquareBoardImpl(width)

    init {

        //println("Init START")
        //println("Init Board: {${board.getAllCells()}}")

        for (i in 1..width){
            //println("Init i = $i")
            for (j in 1..width){
                //println("Init j = $j")
                map[board.getCell(i,j)] = null
            }
        }

        //println("Init END")
    }

    override fun get(cell: Cell): T? {
        //println("Test GetCell: {${cell}}")
        return map[cell]
    }

    override fun set(cell: Cell, value: T?) {
        //println("Test SetCell: {${cell}}")
        map[cell] = value
    }

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> {

        val list = mutableListOf<Cell>()

        map.forEach { it ->
            if(predicate(it.value)) {
                list.add(getCell(it.key.i, it.key.j))
                //return getCell(it.key.i, it.key.j)
                //return map.get(getCell(it.key.i, it.key.j))
            }
        }

        return list
    }

    override fun find(predicate: (T?) -> Boolean): Cell? {

        map.forEach { it ->
            if(predicate(it.value)) {
                return getCell(it.key.i, it.key.j)
                //return map.get(getCell(it.key.i, it.key.j))
            }
        }

        return null
    }

    override fun any(predicate: (T?) -> Boolean): Boolean {
        return map.values.any(predicate)
    }

    override fun all(predicate: (T?) -> Boolean): Boolean {
        map.forEach { it ->
            if(!predicate(it.value)) {
                return false
            }
        }
        return true
    }
}