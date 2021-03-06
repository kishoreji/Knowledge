package com.knowledge.tree

import java.util.*
import kotlin.math.abs

data class Node(val data: Int, var left: Node? = null, var right: Node? = null)

fun main() {
    var root: Node? = null
    root = addNode(null, 1)
    root = addNode(root, 2)
    root = addNode(root, 3)
    root = addNode(root, 4)
    root = addNode(root, 5)
    root = addNode(root, 6)
    root = addNode(root, 7)
    root = addNode(root, 8)
    root = addNode(root, 9)
    root = addNode(root, 0)
    root = addNode(root, 10)

    println("IS this tree BST = ${isBST(root)}")
    println("IS this tree balanced = ${isBalance(root)}")
    println("balance this tree")
    root = balance(root)
    println("IS this tree balanced = ${isBalance(root)}")
    println("traverse in-order ")
    printInOrder(root)
    println()
    println("traverse pre-order ")
    printPreOrder(root)
    println()
    println("----------------")
//    printZigZagBST(root)
}

fun printInOrder(root: Node?): Unit {
    if (root == null) return
    printInOrder(root.left)
    print("${root.data} ")
    printInOrder(root.right)
}

fun printPreOrder(root: Node?): Unit {
    if (root == null) return
    print("${root.data} ")
    printPreOrder(root.left)
    printPreOrder(root.right)
}

fun balance(root: Node?): Node? {
    val elements = LinkedList<Node>()
    inOrderTraverse(root, elements)
    return constructBalancedBST(elements, 0, elements.size - 1)
}

fun constructBalancedBST(elements: LinkedList<Node>, low: Int, high: Int): Node? {
    if (low > high) return null
    val mid = low + (high - low) / 2
    val temp = elements[mid]
    temp.left = constructBalancedBST(elements, low, mid - 1)
    temp.right = constructBalancedBST(elements, mid + 1, high)
    return temp
}

fun inOrderTraverse(root: Node?, elements: LinkedList<Node>) {
    if (root == null) return
    inOrderTraverse(root.left, elements)
    elements.add(root)
    inOrderTraverse(root.right, elements)
}

fun isBalance(root: Node?): Boolean {
    if (root == null) return true
    return if (heightOfNode(root) <= 1) {
        isBalance(root.left) && isBalance(root.right)
    } else {
        false
    }
}

fun heightOfNode(root: Node?): Int {
    if (root == null) return -1
    val left = heightOfNode(root.left) + 1
    val right = heightOfNode(root.right) + 1
    return abs(left - right)
}

fun addNode(root: Node?, data: Int): Node {
    if (root == null) {
        return Node(data, null, null)
    }

    if (root.data > data) {
        root.left = addNode(root.left, data)
    } else {
        root.right = addNode(root.right, data)
    }
    return root
}
