package com.shaokui.list;

import java.lang.annotation.ElementType;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * <p>文档描述：自定义集合Arraylist不规范</p>
 *
 * @Author MyArraylist
 * @Date 2020/5/7 0007 下午 12:45
 * @Version 1.0
 */

public class MyArraylist<T> {

    private int DEFAULT_CAPACITY = 10;//第一次添加数据后默认容量

    private int size;//数组的实际存放大小

    public Object[] elementData;//实际存放数据的数组

    public static final Object[] DEFAULT_EMPTY_ELEMENT = {};

    public MyArraylist() {
        this.elementData = DEFAULT_EMPTY_ELEMENT;
    }
    //添加一个数据
    public boolean add(T t) {
        //判断添加之前数组是不是空数组
        if(elementData == DEFAULT_EMPTY_ELEMENT) {
            //如果是，将数组长度设为默认长度10
            elementData = new Object[DEFAULT_CAPACITY];
        }
        //如果添加数据之后集合长度大于数组长度，扩容
        if(size+1 > elementData.length) {
            DEFAULT_CAPACITY = DEFAULT_CAPACITY+(DEFAULT_CAPACITY>>1);
            Object[] newArray = new Object[DEFAULT_CAPACITY];
            elementData = Arrays.copyOf(elementData, newArray.length);
            /*System.arraycopy(elementData, 0, newArray, 0, elementData.length);
            elementData = newArray;*/
        }
        elementData[size++] = t;
        return true;
    }
    public T get(int index) {
        if(index+1 > size) {
            throw new IndexOutOfBoundsException("越界");
        }
        return (T) elementData[index];
    }
    public T remove(int index) {
        if(index > size-1) {
            throw new IndexOutOfBoundsException("越界");
        }
        T oldValue = (T) elementData[index];
        //index之后是需要替换的
        int length = size-index-1;
        System.arraycopy(elementData, index+1, elementData, index, length);
        size--;
        return oldValue;
    }

    public T update(int index,T t){
        if(index > size-1) {
            throw new IndexOutOfBoundsException("越界");
        }
        T oldValue = (T) elementData[index];
        elementData[index] = t;
        return oldValue;
    }

    int getSize() {
        return size;
    }
}
