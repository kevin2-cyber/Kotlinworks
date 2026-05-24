/*: * Null Safety
 A String must have a string value, but a String? accepts null
*/
var myName: String        // What's the value? Null? IMPOSSIBLE
var myLastname: String?   // It can handle a null value

// Null operators
// **************
print(myLastname.length)   // it doesn't work!

// not-null assertion operator
val lengthForSure = myLastname!!.length

// Safe calls
val length = myLastname?.length

// Default values
val lengthOrDefault = name?.count ?: 0   // What's the name of the ?: operator?




