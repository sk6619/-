

# Java反射

### 一：Java反射是什么？（反射是框架设计的灵魂）

##### 1.在运行状态中

对于任意一个类------都能知道该类的所有属性和方法

对于任意一个对象---都能调用它任意方法和属性

这种动态获取信息以及动态调用对象的方法和属性的功能称之为反射机制

##### 2.反射机制提供的功能

a.在运行时判断一个对象所属的类

b.在运行时构造属于类的任意对象

c.在运行时判断任意一个类所具有的所有属性和方法

d.在运行时调用任意个对象的方法

​       剖析一个类首先要获取字节码文件对象class，获取方法：Class类中的方法

##### 3.Class类

1.用于获取与类相关的各种信息， 提供了获取类信息的相关方法

2.Class类继承自Object类

3.Class类是所有类的共同的图纸

4.存放各种类的信息

5.Class 类的实例表示正在运行的 Java 应用程序中的类和接口。也就是jvm中有N多的实例每个类都有该Class对象。（包括基本数据类型）

6.Class 没有公共构造方法。Class 对象是在加载类时由 Java 虚拟机以及通过调用类加载器中的defineClass 方法自动构造的。也就是这不需要我们自己去处理创建，JVM已经帮我们创建好了

##### 4.反射的应用场景

1.知道类和对象的具体信息，此时直接对类和对象进行操作即可，无需反射

2.不知道类或者对象的具体信息，此时应该使用反射来实现，例如配置文件

### 二：获取反射入口Class对象的3种方法

##### 1.通过Class.forName("包名+类名");

```java
//抛出找不到该类的异常
Class clazz = Class.forName("com.shaokui.reflex.People");
```

##### 2.类名.class

```java
Class clazz = People.class;
```

##### 3.对象.getClass();

```java
People people = new People();
Class clazz = people.getClass();
```

##### 4.三种方式获取的Class对象都是一个对象

```java
assert clazz1 == clazz2; true
assert clazz2 == clazz3; true
```

### 三：根据Class对象获取所有对象的方法和信息

##### 1.获取方法（public修饰）

```java
//根据方法名获取不是private的单个方法
Method method  = clazz.getMethod("方法名", String.class);
//获取所有不是private的方法
Method[] methods = clazz.getMethods();
//获取private修饰的方法
Method[] methods = clazz1.getDeclaredMethods();
/////////////////////////////////////////////
//操作方法，参数为方法名和方法参数
Method method = clazz.getMethod("eat", null);
method.invoke(p, null);

```

##### 2.获取方法上的注解

```
//获取private方法上的所有注解
method.getDeclaredAnnotations();
//获取方法上的所有注解
method.getAnnotations();
```

##### 3.获取所有字段---属性

```java
//获取不是private的
clazz.getField("name");
//获取所有不是private的属性
clazz.getFields();
//获取所有属性
clazz.getDeclaredFields();
//获取私有属性
clazz.getDeclaredField("name");
/////////////////////////////////////////////////////////////
//操作字段
Class clazz = People.class;
People p = new People();
Field name = clazz.getField("name");
//给哪个对象的name字段设置哪个值
name.set(p, "张三");
System.out.println(p.name);
```

##### 4.获取类的构造方法

```java
clazz.getConstructor(int.class);
clazz.getConstructors();
```

##### 5.获取父类

```java
clazz.getSuperclass();
```

##### 6.获取对象的实例

```java
clazz.newInstance();
```