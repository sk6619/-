# JVM调优相关知识

一计算对象的大小

对象布局：

对象头：markWord，classPointe类型指针，数组长度

实例数据

对齐填充 ：被8整除

![image-20200508204648087](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200508204648087.png)

##### 指针压缩默认开启jdk1.6  未开启的情况内存地址8b  开启为4b

![image-20200508204829450](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200508204829450.png)

64位机的最大内存  2的48次方  16位的保留位

##### 空对象：没有普通属性的类生成的对象

开启指正压缩：8+4+4  对齐填充

关闭指针压缩：8+8

![image-20200508211208952](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200508211208952.png)

为什么开启指针压缩：提升效率，节省空间



##### 对象内存布局合适出现两段填充：

1.数组对象在指针关闭压缩的情况

![image-20200508214025460](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200508214025460.png)

虚拟机栈默认大小1024k

查看栈大小

![image-20200508214325984](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200508214325984.png)

64位最小160k

160 * 1024/栈深度=每个炸大小