package com.shaokui.list;

/**
 * <p>文档描述：实现一个简单的linkedList</p>
 *
 * @Author MylinkedList
 * @Date 2020/5/8 0008 上午 10:27
 * @Version 1.0
 */
public class MylinkedList<T> {

    //集合大小，也是节点到个数
    int size;

    //定义头节点
    Node<T> first;

    //定义尾结点
    Node<T> last;

    //之定义一个默认的构造器
    MylinkedList() {

    }
    //修改
    public T update(int index,T t) {
        checkIndexout(index);
        T item = findByIndex(index).item;
        findByIndex(index).item = t;
        return item;
    }
    //add方法
    public boolean add(T t) {
        //暂时存放last节点
        Node<T> node = last;
        Node<T> newNode = new Node(last,null,t);
        last = newNode;
        //1.判断是否有尾结点
        if(node == null) {//尾结点为空说明该集合没有元素，插入的节点为头节点
            first = newNode;
        }else {//不为空说明该节点的上一个节点为last
            node.next = last;
        }
        size++;
        return true;
    }
    //get方法
    public T get(int index) {//通过下标获取节点
        checkIndexout(index);//检查是否越界
        //判断下标在哪个位置选择从前或者从后面遍历
        return findByIndex(index).item;
    }
    public T remove(int index) {
        checkIndexout(index);
        Node<T> node = findByIndex(index);
        Node<T> prev = node.prev;
        Node<T> next = node.next;
        //当前的前节点为空
        if(prev == null) {
            first = next;
        }else {
            prev.next = next;
        }
        //当前的后节点为空
        if(next == null) {
            prev.next = null;
            last = node.prev;
        }else {
            next.prev = prev;
        }
        size--;
        return node.item;
    }

    public void checkIndexout(int index) {
        if(index > size-1) {
            throw new IndexOutOfBoundsException("下标越界");
        }
    }
    Node<T> findByIndex(int index) {
        System.out.println("二分法");
        Node<T> temp;
        if(index < (size >> 1)) {
            System.out.println("靠左");
            temp = first;
            for(int i = 0; i < index; i++) {
                temp = temp.next;
            }
        }else {
            System.out.println("靠右");
            temp = last;
            for(int i = size-1; i > index; i--) {
                temp = temp.prev;
            }
        }
        return temp;
    }




    //内部定义一个节点类，双向链表
    private static class Node<T> {
         T item;
         Node<T> prev;
         Node<T> next;
         //节点的构造函数
         Node(Node<T> prev,Node<T> next,T item) {
             this.prev = prev;
             this.next = next;
             this.item = item;
         }
     }
}
