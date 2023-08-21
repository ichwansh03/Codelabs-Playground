class Calculator {

    fun add(value1: Int, value2: Int): Int {
        return value1 + value2
    }

    fun divide(value1: Int, value2: Int): Int {
        if (value2 == 0){
            throw IllegalArgumentException("cannot divide with 0")
        } else {
            return value1/value2
        }
    }
}