/*: * Other Data Types
 Kotlin has much more to offer, including the ability to create
 Interfaces - Objects - Data Classes - Enum Classes - Sealed Classes
*/

// INTERFACES
interface Listener {
    fun listen()
}
class Human: Listener {
    override fun listen() {
        print("I'm listening!")
    }
}

// OBJECTS
object KotlinWorkshop {
    val name = "Introduction to Kotlin and Android app development"
    val url = "https://frontendmasters.com"
}
// Classes don't have static members, but they contain a companion object
class User(var name: String) {
    fun print() {
        print("Printing $name at $FONT_SIZE")
    }

    companion object {
        const val collection = listOf(User("A"), User("B"))
        const val FONT_SIZE = 12
        fun printAll() {
            for (user in collection) {
                user.print()
            }
        }
    }
}

// SEALED CLASS: A class that can have no instances; only an implicit companion object
sealed class Utilities {
    fun plusTax(price: Double): Double = price * 1.10
}

// ENUM CLASS
enum class Direction {
    NORTH, SOUTH, WEST, EAST
}
enum class Color(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}


// DATA CLASS: basic behavior for classes holding data (such as toString, equals, copy)
data class Product(val id: Int, var name: String, var price: Double)


