fun main() {
    val list = IntList(1)
    list.add(1)
    list.addWithIndex(1,2)
    list.remove()
    list.remove()
}


class IntList(maxItems: Int) {
    private var arr = arrayOfNulls<Int>(maxItems)

    fun size(): Int {
        var size = 0

        var currentItem: Int?
        do {
            currentItem = arr[size]
            if (currentItem != null) size++
        } while (currentItem != null && size != arr.size)

        return size
    }

    fun get(index: Int): Int {

        return arr[index]!!
    }

    fun add(item: Int) {

        if (size() == arr.size) {
            val newArr = arr.copyOf(arr.size + 1)
            arr = newArr
        }
        arr[size()] = item
        println(arr.joinToString(separator = ", ", prefix = "[", postfix = "]") { it.toString() })
    }

    fun addWithIndex(index: Int, item: Int) {
        val s = size()
        if (index > s) {
            throw IllegalArgumentException("Неверный индекс списка")
        }
        if (s == arr.size) {
            val newArr = arr.copyOf(arr.size + 1)
            arr = newArr
        }
        for (j in s downTo index + 1) {
            arr[j] = arr[j - 1]
        }
        if (s < arr.size) {
            arr[index] = item
        }
        println(arr.joinToString(separator = ", ", prefix = "[", postfix = "]") { it.toString() })
    }

    fun remove() {
        val newArr = arr.copyOf(arr.size - 1)
        arr = newArr
        println(arr.joinToString(separator = ", ", prefix = "[", postfix = "]") { it.toString() })
    }

    fun removeWithIndex(index:Int){
        for (j in index + 1 until size()) {
            arr[j - 1] = arr[j]
        }
        val newArr = arr.copyOf(arr.size - 1)
        arr = newArr
        println(arr.joinToString(separator = ", ", prefix = "[", postfix = "]") { it.toString() })
    }
}
// [10, 9, 8, 7, null, null] (1, 5) - [1, 5, 2, 3, 4, null]

//      25
// [10, *9, 8, 7, 13, 15, 16, 18, null, null] (1, 5) - [1, 5, 2, 3, 4, null]
// s = 8 downTo 2
// [1, 2, 3, 4, null, null] (5, 5) net
// [1, 2, 3, 4, null, null] (4, 5) [1, 2, 3, 4, 5, null]