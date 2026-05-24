
/*: * Variables and Types
 Kotlin is a statically typed language, but it's also flexible and support type inference
*/

// Mutable Variables use the keyword `var`
var data = 3
data = 5
data = 7

// Immutable Variables use the keyword `val` (value)
// We want to use val as much as possible
val notMutableObject = String()

// Constants use `const val` and they can only be applied to top-level structures
object TaxCalculator {
    const val SALES_TAX = 8.2
}

// Types are defined with colon after name, but they can be inferred by its initial value
var price: Double
var otherPrice = 325.99
// Sometimes we want to explicitly define the data type
var aThirdPrice = 23  // is it a Double?

if (price==aThirdPrice) {}  // Structural equality
if (price===aThirdPrice) {} // Referencial equality


// Core Data Types
val string: String
var char: Char
val integer: Int      // also Byte, Short, Long
val double: Double    // also Float
val boolean: Boolean  // values are true and false
var what: Any         // avoid Any as much as possible

// String Literals, double quotes
print("Hello World")
// Multi-line literal strings


// Every string can have template expressions using $ or ${}
// expression result will be converted to string
val message = "The $price price is ${otherPrice * 1.1}"

