package games.gameOfFifteen

import board.Direction
import board.GameBoard
import board.createGameBoard
import games.game.Game

/*
 * Implement the Game of Fifteen (https://en.wikipedia.org/wiki/15_puzzle).
 * When you finish, you can play the game by executing 'PlayGameOfFifteen'.
 */
fun newGameOfFifteen(initializer: GameOfFifteenInitializer = RandomGameInitializer()): Game =
        GameOfFifteen(initializer)

class GameOfFifteen(private val initializer: GameOfFifteenInitializer): Game {
    private val board = createGameBoard<Int?>(4)

    override fun initialize() {
        board.setNewVals(initializer)
    }

    override fun canMove(): Boolean = board.any { it == null }

    override fun hasWon(): Boolean {
        val allSet = board.getAllCells()
        var fg = 0
        for (i in 1 until 16) {
            if(board.get(allSet.elementAt(i-1)) != i)
                fg++
        }
        return (fg == 0)
    }

    override fun processMove(direction: Direction) {
        val nCell = board.filter { it == null }.first()
        when(direction) {
            Direction.UP -> {
                if (board.getCellOrNull(nCell.i+1, nCell.j) != null) {
                    val t = board.get(board.getCell(nCell.i+1, nCell.j))
                    board.set(nCell, t)
                    board.set(board.getCell(nCell.i+1, nCell.j), null)
                }
            }
            Direction.DOWN -> {
                if (board.getCellOrNull(nCell.i-1, nCell.j) != null) {
                    val t = board.get(board.getCell(nCell.i-1, nCell.j))
                    board.set(nCell, t)
                    board.set(board.getCell(nCell.i-1, nCell.j), null)
                }
            }
            Direction.RIGHT -> {
                if (board.getCellOrNull(nCell.i,nCell.j-1) != null) {
                    val t = board.get(board.getCell(nCell.i, nCell.j-1))
                    board.set(nCell, t)
                    board.set(board.getCell(nCell.i, nCell.j-1), null)
                }
            }
            Direction.LEFT -> {
                if (board.getCellOrNull(nCell.i,nCell.j+1) != null) {
                    val t = board.get(board.getCell(nCell.i, nCell.j+1))
                    board.set(nCell, t)
                    board.set(board.getCell(nCell.i, nCell.j+1), null)
                }
            }
        }
    }

    override fun get(i: Int, j: Int): Int? = board.get(board.getCell(i,j))

    fun GameBoard<Int?>.setNewVals(initializer: GameOfFifteenInitializer) {
        val allSet = this.getAllCells()
        val perm = initializer.initialPermutation
        for (i in 0 until allSet.size) {
            try {
                this.set(allSet.elementAt(i), perm[i])
            } catch (e: Exception) {
                this.set(allSet.elementAt(i), null)
            }
        }
    }

}

