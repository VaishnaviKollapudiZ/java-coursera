package course4.linkedlist1;

import java.util.AbstractList;

public class MyLinkedList<E> extends AbstractList<E> {

    private LLNode<E> head;
    private LLNode<E> tail;
    private int size=0;

    private class LLNode<E>
    {
        private LLNode<E> next;
        LLNode<E> prev;
        E data;

        LLNode(LLNode<E> prev, E data, LLNode<E> next) {
            this.data= data;
            this.next = next;
            this.prev = prev;
        }

    }

    private void addLast(E element){

        LLNode<E> newNode = new LLNode<E>(tail,element,null);
        LLNode<E> last = tail;
        if(last == null)
            head = newNode;
        else
            last.next = newNode;
        tail = newNode;
        size++;
    }
    private void addBefore(E element, LLNode<E> nextNode){

        LLNode<E> previousNode = nextNode.prev;
        LLNode<E> newNode = new LLNode<>(previousNode,element,nextNode);
        nextNode.prev = newNode;

        if(previousNode == null)
            head = newNode;
        else previousNode.next = newNode;
        size++;

    }
    @Override
    public boolean add(E element )
    {
        addLast(element);
        return true;
    }

    /** Get the element at position index
     * @throws IndexOutOfBoundsException if the index is out of bounds. */

    private boolean checkIndex(int index){
        if (index<0 || index>size)
            throw new IndexOutOfBoundsException(index+" out of bounds");
        return true;
    }
    private LLNode<E> getNode(int index){

        LLNode<E> pointer;
        if(index < (size/2)) {

            pointer = head;
            for (int position = 0; position < index; position++)
                pointer = pointer.next;
        }
        else {
            pointer = tail;
            for (int position = size - 1; position > index; position--)
                pointer = pointer.prev;
        }
        return pointer;
    }
    public E get(int index)
    {
        checkIndex(index);
        return getNode(index).data;
    }

    @Override
    public void add(int index, E element )
    {
        if(index >= 0 && index<=size){
            if(index == size)
                addLast(element);
            else
                addBefore(element, getNode(index));
        }else
            throw new IndexOutOfBoundsException(index+" out of bounds for a linkedList of size "+size);

    }

    public int size()
    {
        return size;
    }
    private void removeNode(LLNode<E> currentNode){

        LLNode<E> previousNode = currentNode.prev;
        LLNode<E> nextNode = currentNode.next;

        if(previousNode == null){
            //implies currentNode is the firstNode, since we delete currentNode, head should point to nextNode
            head = nextNode;
        }else{
            previousNode.next = nextNode;
            currentNode.prev = null;
        }


        if(nextNode == null){
            //currentNode to be deleted is the last node
            tail = previousNode;
        }else{
            nextNode.prev = previousNode;
            currentNode.next = null;
        }

        currentNode.data = null;
        size--;
    }
    @Override
    public E remove(int index)
    {
        if(index>=0 && index<=size)
            removeNode(getNode(index));
        else
            throw new IndexOutOfBoundsException(index +" out of bounds for a linked list of size "+size);

        return null;
    }

    @Override
    public E set(int index, E element)
    {
        checkIndex(index);
        LLNode<E> node = getNode(index);
        E oldData = node.data;
        node.data = element;
        return oldData;
    }
}


