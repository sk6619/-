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

### 三HashSet（线程不安全）（元素无序，不可重复）

##### 1.hashset的父类

```java
public class HashSet<E>
    extends AbstractSet<E>
    implements Set<E>, Cloneable, java.io.Serializable
```

##### 2.构造函数（内部实例化了一个map）

```java
private transient HashMap<E,Object> map;
public HashSet() {
    map = new HashMap<>();
}
//set集合的大小，即内部hashMap的大小
public int size() {
        return map.size();
    }
默认大小16
default initial capacity (16) and load factor (0.75).
```

##### 3.加载因子

加载因子是表示Hsah表中元素的填满的程度.若:加载因子越大,填满的元素越多,好处是,空间利用率高了,但:冲突的机会加大了.反之,加载因子越小,填满的元素越少,好处是:冲突的机会减小了,但:空间浪费多了.

冲突的机会越大,则查找的成本越高.反之,查找的成本越小.因而,查找时间就越小.

因此,必须在 "冲突的机会"与"空间利用率"之间寻找一种平衡与折衷. 这种平衡与折衷本质上是数据结构中有名的"时-空"矛盾的平衡与折衷.

##### 4.阈值=加载因子*容量

存储的元素达到这个临界值机会扩容

##### 5.遍历

![image-20200509104001451](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200509104001451.png)

```java
Iterator iterator = set.iterator();
while (iterator.hasNext()) {
    System.out.println(iterator.next());
}
```

![image-20200509104313569](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200509104313569.png)

##### HashSet具体用法（排重）

没有重写对象的equals和hashcode方法，两个对象肯定不等

```java
Set<User> set = new HashSet<>();

User user1 = new User(10,"张三");
User user2 = new User(10, "张三");

System.out.println(user1.equals(user2));//false
System.out.println(set.add(user1));//true
System.out.println(set.add(user2));//true

set.forEach(user->System.out.println(user));
```

```java
重写hashcode和equals进行排重，obj表示要插入的对象
@Override
public int hashCode() {
    return age+name.hashCode();
}

@Override
public boolean equals(Object obj) {
    User user = (User) obj;
    return this.name == user.name && this.age == user.age;
}
```

### 四;TreeSet(线程不安全)

##### 方式一自然排序：treeSet里面的元素必须实现Comparable接口，不然报错

```java
public class User implements Comparable 
@Override
    public int compareTo(Object o) {
        User user = (User)o;
    //升序降序，返回0一样大不会添加数据，返回1大于，返回-1小于
        return user.age-this.age;
    }
```

##### 方式二：定制排序

**定制排序**。上面例子通过修改user类中的CompareTo方法就可以实现不同的排序方法。如果在特定情况下，不能够修改user类的代码，就需要用到定制排序

```java
//创建comparator接口的实例
Comparator comparator = new Comparator() {
    @Override
    public int compare(Object o1, Object o2) {
        if(o1 instanceof User && o2 instanceof User) {
            User user1 = (User) o1;
            User user2 = (User) o2;
            return user1.age-user2.age;
        }
        return 0;
    }
};
//将实例传给set构造函数
Set set = new TreeSet(comparator);
User user1 = new User(9, "李四");
User user2 = new User(10, "李四");
User user3 = new User(11, "李四");
User user4 = new User(11, "李四");

treeset构造函数
public TreeSet(Comparator<? super E> comparator) {
        this(new TreeMap<>(comparator));
    }
```

