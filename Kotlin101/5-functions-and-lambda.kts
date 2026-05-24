/*: * Functions
 */

fun aFunction() {
    print("I'm a function")
}

fun aFunctionReturning(): String {
    return "I'm a function"
}

fun aFunctionReturning2(): String = "I'm a function"

// Arguments
fun sum(a: Int, b: Int): Int {
    return a+b
}

fun sum3(a: Int, b: Int, c: Int): Int {
    return a+b+c
}

sum3(4,c=4,b=2)

// How do you call sum?
sum(4,1)
sum(a=4, b=1)

/*: * Extension functions
    You can add functions to any Type! Careful OOP extremists!
*/
fun Int.isEven() : Boolean {
    return this % 2 == 0
}


var lambda: (Int, Int) -> Int = {a,b -> a+b}
/*: * Lambda functions, Type is (arguments) -> ReturnType
   if no return is expected, we use Unit
 */
val myFunction: (Int) -> Unit = {
    print(it)
}
// implicit `it` argument when there is only one argument
// you can still name it if you want to
val greet: (String) -> String
greet = {
    return "Hello $it"
}

val myCalc: (Int, Int) -> Int = a,b -> a+b


