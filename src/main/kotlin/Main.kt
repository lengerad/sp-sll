/**
 * Simple node representation that needs to have comparable class in order to enable sorting
 */
data class Node<T: Comparable<T>>(
    val data: T,
    var nextNode: Node<T>? = null,
    var previousNode: Node<T>? = null,
)

/**
 * According to the sentence from the assignment `(linked list that keeps values sorted).` I assumed that list should be sorted after each addition.
 * I used doubly linked list, so it can be traversed both ways and removal operation already has pointer to previous node. Extra pointer needs extra maintenance, but I decide for this implementation.
 * I decided to create `addSorted` that puts the element on the sorted position right away and `remove` function that enables to remove value form SLL and simple search ( I wasn't sure whether I should implement other LL functions).
 * Overall it seems that task leads to question - should it be sorted right away or simply add node without sorting and then sort before `traversing/printing`?
 * It's up for a discussion in terms of usage of operations made on the list - if there would be a lot of additions and traversing/printing I guess current solution is fine (even tho not much efficient),
 * but if there would be thousands of additions and single print/traverse once in a while then it would lead to "sort once per request" via quick/merge sort.
 */
class SortedLinkedList<T : Comparable<T>> {
    private var head: Node<T>? = null

    constructor (array: Array<T>) {
        array.forEach { value -> appendSorted(value) }
    }

    fun appendSorted(data: T): Node<T>? {
        val node = Node(data)
        if (head == null || head!!.data >= data) {
            node.nextNode = head
            head?.previousNode = node
            head = node
            return head
        }
        var currentNode = head!!
        while (currentNode.nextNode != null && currentNode.nextNode!!.data < data) {
            currentNode = currentNode.nextNode!!
        }
        node.nextNode = currentNode.nextNode
        currentNode.nextNode?.previousNode = node
        currentNode.nextNode = node
        node.previousNode = currentNode
        return head
    }

    fun remove(data: T): Boolean {
        if (head == null) return false
        var currentNode = head
        do {
            if (currentNode!!.data == data ) {
                if (currentNode == head) head = currentNode.nextNode
                currentNode.previousNode?.nextNode = currentNode.nextNode
                currentNode.nextNode?.previousNode = currentNode.previousNode
                // remove links
                currentNode.nextNode = null
                currentNode.previousNode = null
                return true
            }
            currentNode = currentNode.nextNode
        } while (currentNode?.nextNode != null)
        return false
    }

    fun search(data: T): Boolean {
        var currentNode = head
        while (currentNode != null) {
            if (currentNode.data == data) return true
            currentNode = currentNode.nextNode
        }
        return false
    }

    fun printList() {
        var current = head
        while (current != null) {
            print("${current.data} ")
            current = current.nextNode
        }
        println()
    }

    fun printWithPointers() {
        var current = head
        while (current != null) {
            print("|${current.previousNode?.data} <- ${current.data} -> ${current.nextNode?.data}|")
            current = current.nextNode
        }
        println()
    }
}

fun main(args: Array<String>) {
    val unsortedIntArray = arrayOf(92, 11, 2, 3, 555, 12, 44, 31, 1000, -12, 87, 43, 12)
    // integer example
    val intList = SortedLinkedList(unsortedIntArray)
    intList.printList()
    // let's remove some data
    intList.remove(43)
    // and verify result
    intList.printList()
    intList.printWithPointers()
    val searchResult = intList.search(0)

    val unsortedStringArray = arrayOf("aurora", "design", "meets", "yellow", "alfa", "before", "detox", "b", "zero", "wow")
    // same goes for strings
    val stringList = SortedLinkedList(unsortedStringArray)
    stringList.printList()
    stringList.remove("yellow")
    stringList.printList()
    // simply to preview pointers of the linked list (also to preview that after removal all relations are properly set)
    stringList.printWithPointers()
}
