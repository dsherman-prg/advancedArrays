fun main(){
    // Base arrays for hotdogStand
    val supply = arrayOf("Hotdog","Hotdog Buns","Ketchup","Celery Salt","Mustard")
    val quantity = arrayOf("0","0","0","0","0")
    var hotdogStand = arrayOf<Array<String>>()

    // Combine both arrays into 2d array
    hotdogStand += supply
    hotdogStand += quantity

    do {
        // Print first menu
        println("\nPlease enter the number of your selection:")
        println("1 Display Items and quantity")
        println("2 Edit item quantity")
        println("3 Exit")

        // Get user menu selection
        val selection = readLine()!!.toInt()
        when(selection){
            1 -> displayQuantity(hotdogStand)
            2 -> selectionMenu(hotdogStand)
            !in 1..3 -> println("Invalid Entry. Please enter a valid selection.")
        }

    // Repeat menu if invalid selection is entered
    }while (selection != 3)
}

fun displayQuantity(hotdogStand: Array<Array<String>>){
    val rowcount = hotdogStand[0].size-1

    // Print everything in the hotdogStand 2d array
    for (entry in 0..rowcount){
        val arrayItem = hotdogStand[0][entry]
        val arrayQuantity = hotdogStand[1][entry]
        val count = entry+1
        println("$count $arrayItem: $arrayQuantity in stock")
    }
}

// Second menu to select product to edit values of
fun selectionMenu(hotdogStand: Array<Array<String>>){
    do {

        println("\nPlease select which product you want to add or subtract from:")
        // Call functions to list possible options
        displayQuantity(hotdogStand)
        // Calculate final entry and print exit menu option
        val total = hotdogStand[0].size+1
        println("$total Exit")

        // Get user selection
        var selection = readLine()!!.toInt()
        // Subtract one to get actual array value
        selection -= 1
        when(selection){
            // Allow any input that is within the range of the total number of entries in the array
            // So that the arrays can be increased in the future if needed
            in 0 until hotdogStand[0].size -> editQuantity(selection, hotdogStand)
            !in 0..hotdogStand[0].size+1 -> println("Please enter a valid selection")
        }
    // Loop until valid input is entered
    }while (selection != hotdogStand[0].size)
}

fun editQuantity(item: Int, hotdogStand: Array<Array<String>>){
    do {
        // Print menu
        println("\nDo you want to add or subtract?")
        println("1 Add")
        println("2 Subtract")
        println("3 Exit\n")

        // Get user input
        val userInput = readLine()!!.toInt()
        when(userInput){
            // Add value to selected product
            1 -> {
                println("How many do you want to add?")
                val userNumber = readLine()!!.toInt()
                var quantity = hotdogStand[1][item].toInt()
                println("\nOriginal Value: $quantity")
                hotdogStand[1][item] = (quantity + userNumber).toString()
                println("Amount added: $userNumber")
                quantity = hotdogStand[1][item].toInt()
                println("New value: $quantity\n")

            }
            // Subtract from selected value
            2 ->{
                println("How many do you want to subtract product quantities?")
                val userNumber = readLine()!!.toInt()
                var quantity = hotdogStand[1][item].toInt()
                println("\nOriginal Value: $quantity")

                // If the value was going to be set below zero print an error and set it to zero
                if (quantity - userNumber < 0){
                    println("Error: Value would be below zero")
                    println("Setting value to 0")
                    hotdogStand[1][item] = "0"
                    quantity = 0
                }
                else{
                    hotdogStand[1][item] = (quantity - userNumber).toString()
                    println("Amount subtracted: $userNumber")
                    quantity = hotdogStand[1][item].toInt()
                }

                println("New value: $quantity")
            }

            !in 1..3 -> println("Error: Please enter a valid selection")
        }
    }while (userInput !in 1..3)
}