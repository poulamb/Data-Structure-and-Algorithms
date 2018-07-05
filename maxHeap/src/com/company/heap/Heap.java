package com.company.heap;

public class Heap {

    private int[] heap;
    private int size;

    public Heap(int capacity) {
        heap = new int[capacity];
    }

    public void insert(int value) {   // time complexity: O(logn)
        if (isFull()) {
            throw new IndexOutOfBoundsException("Heap is full");
        }

        heap[size] = value;
        fixHeapAbove(size);
        size++;
    }

    public int delete(int index) { // O(n)(find the deleted value)
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Heap is Empty");
        }

        int parent = getParent(index);
        int deletedValue = heap[index];

        //replacement
        heap[index] = heap[size-1];//the rightmost node in the heap is heap[size-1]

        if (index == 0|| heap[index] < heap[parent]) {
            fixHeapBelow(index, size-1);
        }
        else {
            fixHeapAbove(index);
        }

        size --;
        return deletedValue;

    }

    public void sort() {//  time complexity: O(nlogn)
        int lastHeapIndex = size-1;
        for (int i =0; i< lastHeapIndex; i++) {
            int tmp = heap[0];
            heap[0] = heap[lastHeapIndex - i];
            heap[lastHeapIndex - i] = tmp;

            fixHeapBelow(0,lastHeapIndex - i - 1);
        }
    }


    public int peek() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        return heap[0];
    }

    private void fixHeapAbove(int index) {
        int newValue = heap[index];
        while (index > 0 && newValue > heap[getParent(index)]) {
            heap[index] = heap[getParent(index)];
            index = getParent(index);
        }
        heap[index] = newValue;
    }

    private void fixHeapBelow(int index, int lastHeapIndex) {

        int childToSwap;

        while (index <= lastHeapIndex) {
            int leftChild = getChild(index, true);
            int rightChild = getChild(index, false);
            if (leftChild <= lastHeapIndex) {//complete tree: left child/ left& right child/ no child
                if (rightChild > lastHeapIndex) {// no right child
                    childToSwap = leftChild;
                } else {// has right child, swap with the largest child
                    childToSwap = (heap[leftChild] > heap[rightChild] ? leftChild : rightChild);
                }

                //compare if greater, swap, done
                if (heap[index] < heap[childToSwap]) {
                    int tmp = heap[index];
                    heap[index] = heap[childToSwap];
                    heap[childToSwap] = tmp;
                }
                else {
                    break;
                }
                index = childToSwap; //compare the new replacement value(parent) with new children
            }
            else {
                break;
            }
        }
    }

    public void printHeap() {
        for (int i =0;i<size; i++) {
            System.out.print(heap[i]);
            System.out.print(", ");
        }
        System.out.println();

    }

    public boolean isFull () {
        return size == heap.length;
    }

    public int getParent (int index) {
        return (index - 1) / 2;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getChild(int index, boolean left) {
        return 2 * index + (left?1:2);
    }

}
