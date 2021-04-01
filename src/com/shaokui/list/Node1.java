package com.shaokui.list;

import com.sun.xml.internal.ws.api.pipe.NextAction;
import jdk.nashorn.internal.ir.Node;
import jdk.nashorn.internal.ir.ReturnNode;

/**
 * <p>文档描述：自定义一个单项链表--尾插</p>
 *
 * @Author Node
 * @Date 2020/5/10 0010 上午 11:17
 * @Version 1.0
 */
public class Node1<T> {

    int size;

    T item;

    Node1<T> first;

    Node1<T> last;

    Node1<T> next;

    Node1(Node1<T> next,T item) {
        this.item = item;
        this.next = next;
    }
    Node1() {}

    //随便添加一个数据
    void add(T t) {
        Node1<T> node = last;
        Node1<T> temp = new Node1(null,t);
        if(last == null) {
            first = temp;
        }else {
            node.next = temp;
        }
        last = temp;
        size++;
    }

    T get(int index) {
        if(index > size-1) {
            throw  new IndexOutOfBoundsException("越界");
        }
        Node1<T> temp = first;
        for(int i = 0;i<index; i++) {
            temp = temp.next;
        }
        return (T) temp.item;
    }

}
