/*: * Basic Expressions
 Kotlin code looks in its first view as many other languages
*/

// We can have global expressions without the need of a function or class.
// However, if you are targeting Android, you will always need a class
// (Java VM requirement)
// Semi-colons are optional, not recommended unless two expressions share a line
// We use (mandatory) curly braces for code blocks following C-syntax.
// Spaces and new lines are, most of the time, ignored
var x = 1; var y = 2

// Boolean conditions need parenthesis
if ((x > 1) && (y < 2)) {

}

while (x < 10) {
    x++
}

print(x)      // println sends a message to the console. Android console buffers it
println(x)    // println sends a message to the console with a new line after

/*: ** Name style guidelines */

// Variable, function and package names use camelCase
var name: String = ""
val tax = 7.8
fun printMessage() { }

// Constant names are using UPPER_SNAKE_CASE
object List {
    const val MAX_LENGTH = 4
}

// Data types use TitleCase.
// TitleClase also applies to Singleton variables and Objects!!
class CustomerOrder { }
object MyObject { }
interface MyInterface { }
val SingletonInstance: Any = TODO()

// Valid identifiers: as most languages, but with steroids - Careful on Android
var español = "hola"
var 愛 = "love"


// Conditionals are expressions; replaces the ternary operator from other languages
val result = if (tax>8) "high tax" else "low tax"

// When, replaces the `switch` from other languages
var price: Any = 2
when (price) {
    0 -> println("No price")
    1 -> {
        print("That's ")
        print("cheap!")
    }
    in 2..10 -> println("Still cheap")
    11, 12, 13 -> println("Getting expensive")
    is String -> println("What's that!?")
    else -> println("Expensive!")
}

// Loops: standard while and for-in
for (i in 0..10) {
    println(i)
}








