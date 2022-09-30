package cinema

import java.lang.Exception
import java.math.BigDecimal
import java.math.RoundingMode

fun main() {
    println("Enter the number of rows:")
    val numberOfRows = readln().toInt()
    println("Enter the number of seats in each row:")
    val numberOfSeats = readln().toInt()

    var numberOfPurchasedTickets = 0
    var percentage = BigDecimal.valueOf(0)
    var currentIncome = 0
    val totalIncome = if (numberOfRows * numberOfSeats < 60)  numberOfRows * numberOfSeats * 10 else
            (numberOfRows / 2 + numberOfRows % 2) * numberOfSeats * 8 + (numberOfRows - (numberOfRows / 2 + numberOfRows % 2)) * numberOfSeats * 10

    val ofRows = mutableListOf<Any>(" ")
    for (i in 1..numberOfSeats) ofRows.add(i)
    val ofSeats = MutableList(numberOfRows) { mutableListOf<Any>() }
    for (i in ofSeats.indices) {
        ofSeats[i].add(i+1)
        for (j in 1..numberOfSeats) {
            ofSeats[i].add('S')
        }
    }

    do {
        println("\n1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit")
        val choice = readln().toInt()
        if (choice == 1) {
            println("\nCinema:")
            println(ofRows.joinToString(" "))
            for (i in ofSeats.indices) println(ofSeats[i].joinToString(" "))
        } else if (choice == 2) {

            var notify = "Wrong input!"
            while (notify == "Wrong input!" || notify == "That ticket has already been purchased!") {
                println("\nEnter a row number:")
                val rowNumber = readln().toInt()
                println("Enter a seat number in that row:")
                val seatNumber = readln().toInt()
                try {
                    if (ofSeats[rowNumber - 1][seatNumber] != 'B') {
                        ofSeats[rowNumber - 1][seatNumber] = 'B'
                        val price: Int
                        if (numberOfRows * numberOfSeats < 60 || rowNumber < numberOfRows / 2 + numberOfRows % 2) price = 10 else price = 8
                        println("\nTicket price: $$price")
                        currentIncome += price
                        numberOfPurchasedTickets ++

                        notify = ""
                    } else {
                        notify = "That ticket has already been purchased!"
                        println("\n$notify")
                    }
                } catch (e: Exception) {
                    notify = "Wrong input!"
                    println("\n$notify")
                }
            }
        } else if (choice == 3) {
            percentage = (BigDecimal(numberOfPurchasedTickets).setScale(4, RoundingMode.CEILING) / BigDecimal(numberOfRows * numberOfSeats) * BigDecimal("100")).setScale(2, RoundingMode.DOWN)
            println("\nNumber of purchased tickets: $numberOfPurchasedTickets")
            println("Percentage: $percentage%")
            println("Current income: $$currentIncome")
            println("Total income: $$totalIncome")
        }
    } while (choice != 0)
}