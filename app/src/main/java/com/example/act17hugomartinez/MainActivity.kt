package com.example.act17hugomartinez

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var player = "X"  // Cambié "jugador" a "player"
    private var board = Array(3) { Array(3) { "" } }  // Cambié "tablero" a "board"
    private lateinit var gameStatus: TextView  // Cambié "estadoJuego" a "gameStatus"
    private lateinit var resetButton: Button  // Cambié "botonReset" a "resetButton"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gameStatus = findViewById(R.id.statusMessage)

        val gameButtons = arrayOf(
            findViewById<Button>(R.id.button1),
            findViewById<Button>(R.id.button2),
            findViewById<Button>(R.id.button3),
            findViewById<Button>(R.id.button4),
            findViewById<Button>(R.id.button5),
            findViewById<Button>(R.id.button6),
            findViewById<Button>(R.id.button7),
            findViewById<Button>(R.id.button8),
            findViewById<Button>(R.id.button9)
        )

        for (i in gameButtons.indices) {
            gameButtons[i].setOnClickListener { onButtonClick(i) }
        }

        resetButton = findViewById(R.id.botonReiniciar)
        resetButton.setOnClickListener {
            resetGame()
        }
    }

    private fun onButtonClick(index: Int) {
        val row = index / 3
        val column = index % 3

        if (board[row][column] != "") return

        board[row][column] = player
        val button = findViewById<Button>(resources.getIdentifier("button${index + 1}", "id", packageName))
        button.text = player

        if (checkVictory()) {
            gameStatus.text = "Player $player wins!"
        } else if (isDraw()) {
            gameStatus.text = "It's a draw!"
        } else {
            player = if (player == "X") "O" else "X"
            gameStatus.text = "Player $player's turn"
        }
    }

    private fun checkVictory(): Boolean {
        for (i in 0..2) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) return true
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) return true
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) return true
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) return true

        return false
    }

    private fun isDraw(): Boolean {
        for (row in board) {
            if (row.contains("")) return false
        }
        return true
    }

    private fun resetGame() {
        board = Array(3) { Array(3) { "" } }

        val gameButtons = arrayOf(
            findViewById<Button>(R.id.button1),
            findViewById<Button>(R.id.button2),
            findViewById<Button>(R.id.button3),
            findViewById<Button>(R.id.button4),
            findViewById<Button>(R.id.button5),
            findViewById<Button>(R.id.button6),
            findViewById<Button>(R.id.button7),
            findViewById<Button>(R.id.button8),
            findViewById<Button>(R.id.button9)
        )

        gameButtons.forEach { it.text = "" }

        player = "X"
        gameStatus.text = "Player $player's turn"
    }
}
