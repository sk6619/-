# java集合

### 一：ArrayList

##### 1.构造函数

```java
public ArrayList();
public ArrayList(Collection<? extends E> c)
public ArrayList(int initialCapacity)    
```

##### 2.初始化时，数组长度为0.

```java
this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;都是空数组
```
##### 3.添加数据，数组长度变化

```java
public boolean add(E e) {
       //最小容量为size+1
        ensureCapacityInternal(size + 1);  
        elementData[size++] = e;
        return true;
 }
```

##### 4.添加数据第一条数据

```java
private void ensureCapacityInternal(int minCapacity) {
    	//数组是空数组
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA{
            //第一次添加数据后将最小容量变为10
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        ensureExplicitCapacity(minCapacity);
 }
```

##### 4.判断是否扩容，需要最小容量为10，大于数组长度，需要扩容

```java
private void ensureExplicitCapacity(int minCapacity) {
    modCount++;
    if (minCapacity - elementData.length > 0)
        grow(minCapacity);
}
```

##### 5.扩容方法

```java
private void grow(int minCapacity) {
          // 获取到ArrayList中elementData数组的内存空间长度
          int oldCapacity = elementData.length;
         // 扩容至原来的1.5倍
         int newCapacity = oldCapacity + (oldCapacity >> 1);
         // 再判断一下新数组的容量够不够，够了就直接使用这个长度创建新数组，
          // 不够就将数组长度设置为需要的长度
         if (newCapacity - minCapacity < 0)
             newCapacity = minCapacity;
         //若预设值大于默认的最大值检查是否溢出
         if (newCapacity - MAX_ARRAY_SIZE > 0)
             newCapacity = hugeCapacity(minCapacity);
         // 调用Arrays.copyOf方法将elementData数组指向新的内存空间时newCapacity的连续空间
         // 并将elementData的数据复制到新的内存空间
         elementData = Arrays.copyOf(elementData, newCapacity);
     }
```

##### 6.数组复制

```java
public static <T,U> T[] copyOf(U[] original, int newLength, Class<? extends T[]> newType) {
    @SuppressWarnings("unchecked")
    T[] copy = ((Object)newType == (Object)Object[].class)
        ? (T[]) new Object[newLength]
        : (T[]) Array.newInstance(newType.getComponentType(), newLength);
    System.arraycopy(original, 0, copy, 0,
                     Math.min(original.length, newLength));
    return copy;
}
```

##### 7.实现一个集合添加和获取元素

```java
public boolean add(T t) {
    if(elementData == DEFAULT_EMPTY_ELEMENT) {
        elementData = new Object[DEFAULT_CAPACITY];
    }
    if(size+1 > elementData.length) {
        Object[] newArray = new Object[DEFAULT_CAPACITY+(DEFAULT_CAPACITY>>1)];
        elementData = Arrays.copyOf(elementData, newArray.length);
        /*System.arraycopy(elementData, 0, newArray, 0, elementData.length);
        elementData = newArray;*/
    }
    elementData[size++] = t;
    return true;
//获取元素
public T get(int index) {
        if(index+1 > size) {
            throw new IndexOutOfBoundsException("越界");
        }
        return (T) elementData[index];
    }
```

##### 8.删除一个元素

```java
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
```

##### 9.修改一个元素

```java
public T update(int index,T t){
    if(index > size-1) {
        throw new IndexOutOfBoundsException("越界");
    }
    T oldValue = (T) elementData[index];
    elementData[index] = t;
    return oldValue;
}
```

### 二：LinkedList（双向链表 ，线程不安全）

##### 1.节点：实际是一个linkedList的内部类

```java
private static class Node<E> {
    E item;
    Node<E> next;
    Node<E> prev;
    Node(Node<E> prev, E element, Node<E> next) {
        this.item = element;
        this.next = next;
        this.prev = prev;
    }
}
```

##### 2.核心属性：

```java
//节点个数，即集合的大小
transient int size = 0;
//表头
transient Node<E> first;
//表尾
transient Node<E> last;
```

##### 3.构造函数

```java
public LinkedList(Collection<? extends E> c) {
    this();
    addAll(c);
}
public LinkedList() {
    }
```

##### 4.add方法

```java
public boolean add(E e) {
    linkLast(e);
    return true;
}
```

```java
void linkLast(E e) {
    final Node<E> l = last;
    final Node<E> newNode = new Node<>(l, e, null);
    last = newNode;
    if (l == null)
        //如果末尾元素为空，说明链表没有元素
        first = newNode;
    else
        l.next = newNode;
    size++;
    modCount++;
}
```

##### 5.get方法，下标获取节点

```java
public E get(int index) {
    checkElementIndex(index);
    return node(index).item;
}
```

```java
Node<E> node(int index) {
    // assert isElementIndex(index);

    if (index < (size >> 1)) {
        Node<E> x = first;
        for (int i = 0; i < index; i++)
            x = x.next;
        return x;
    } else {
        //从后往前遍历，直到找到位置，效率低下
        Node<E> x = last;
        for (int i = size - 1; i > index; i--)
            x = x.prev;
        return x;
    }
}
```

##### 6.remove方法

```java
public E remove(int index) {
    checkElementIndex(index);
    return unlink(node(index));
}
```

```java
E unlink(Node<E> x) {
    // assert x != null;
    final E element = x.item;
    final Node<E> next = x.next;
    final Node<E> prev = x.prev;

    if (prev == null) {
        first = next;
    } else {
        prev.next = next;
        x.prev = null;
    }

    if (next == null) {
        last = prev;
    } else {
        next.prev = prev;
        x.next = null;
    }

    x.item = null;
    size--;
    modCount++;
    return element;
}
```

##### 7.修改元素

```java
public E set(int index, E element) {
    checkElementIndex(index);
    Node<E> x = node(index);
    E oldVal = x.item;
    x.item = element;
    return oldVal;
}
```

##### 实现简单的linkedlist

```java
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
```

```java
//get方法
public T get(int index) {//通过下标获取节点
    checkIndexout(index);//检查是否越界
    //判断下标在哪个位置选择从前或者从后面遍历
    if(index < (size >> 1)) {
        Node<T> temp = first;
        for(int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.item;
    }else {
        Node<T> temp = last;
        for(int i = size-1; i > index; i--) {
            temp = temp.prev;
        }
        return temp.item;
    }
}
```

```java
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
```

```java
//修改
public T update(int index,T t) {
    checkIndexout(index);
    T item = findByIndex(index).item;
    findByIndex(index).item = t;
    return item;
}
```