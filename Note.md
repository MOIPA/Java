### 1. 拆装箱

1. 装箱：Java里头每种数据类型都有对应的包装器类型

   `在Java SE5之前，如果要生成一个数值为10的Integer对象，必须这样进行：`

   ​    `Integer i = new Integer(10);`

   ​	自动装箱：`Integer i = 10;`

2. 拆箱：自动奖基本类型转为包装器类型

   ​	`int n = 10;`

3. 基本数据类型对应的包装器类型：

   ​	`int : Integer` 	`byte : Byte`	`short : Short`

   ​	`long : Long`	`float : Float`	`double : Double`

   ​	`char : Character`	`boolean : Boolean`

4. 实现原理：

  ```java
    	public class Main(){
  		public static void main(String[] args){
  			Integer i = 10;   //integer i = Integer.valueOf(100);  自动调用Integer.valueOf(int); 返回Integer对象
  			int t = i; //拆箱，实际上执行了 int t = i.intValue();自动调用Integer.intValue(); 返回int基本类型
  		}	
    	 }
  ```

  ```java
  public static Integer valueOf(int i) {
          if(i >= -128 && i <= IntegerCache.high)
              return IntegerCache.cache[i + 128];
          else
              return new Integer(i);
      }
  ```

5. 面试题：

   1. ```java
      public` `class` `Main {
          ``public` `static` `void` `main(String[] args) {
              
              ``Integer i1 = ``100``;
              ``Integer i2 = ``100``;
              ``Integer i3 = ``200``;
              ``Integer i4 = ``200``;
              
              ``System.out.print(i1==i2);
              ``System.out.println(i3==i4);
          ``}
      }
      ```

      结果：true   false

      原因：通过valueOf方法创建Integer对象的时候，如果数值在[-128,127]之间，便返回指向IntegerCache.cache中已经存在的对象的引用；否则创建一个新的Integer对象。

   2. 

      ```java
      public` `class` `Main {
          ``public` `static` `void` `main(String[] args) {
              
              ``Double i1 = ``100.0``;
              ``Double i2 = ``100.0``;
              ``Double i3 = ``200.0``;
              ``Double i4 = ``200.0``;
              
              ``System.out.print(i1==i2);
              ``System.out.println(i3==i4);
          ``}
      }
      ```

      输出：false false

      原因：为什么Double类的valueOf方法会采用与Integer类的valueOf方法不同的实现。很简单：在某个范围内的整型数值的个数是有限的，而浮点数却不是。

      

   3. ```java
      public` `class` `Main {
          ``public` `static` `void` `main(String[] args) {
              
              ``Boolean i1 = ``false``;
              ``Boolean i2 = ``false``;
              ``Boolean i3 = ``true``;
              ``Boolean i4 = ``true``;
              
              ``System.out.println(i1==i2);
              ``System.out.println(i3==i4);
          ``}
      }
      ```

      输出：true true

      原因：

      ```java
      public static Boolean valueOf(boolean b) {
              return (b ? TRUE : FALSE);
          }
       public static final Boolean TRUE = new Boolean(true);
      
          /** 
           * The <code>Boolean</code> object corresponding to the primitive 
           * value <code>false</code>. 
           */
          public static final Boolean FALSE = new Boolean(false);
      ```

   4. 谈谈Integer i = new Integer(xxx)和Integer i =xxx;这两种方式的区别。

      　　当然，这个题目属于比较宽泛类型的。但是要点一定要答上，我总结一下主要有以下这两点区别：

         　　1）第一种方式不会触发自动装箱的过程；而第二种方式会触发；

         　　2）在执行效率和资源占用上的区别。第二种方式的执行效率和资源占用在一般性情况下要优于第一种情况（注意这并不是绝对的）。

   5. ```java
      public class Main {
          public static void main(String[] args) {
              
              Integer a = 1;
              Integer b = 2;
              Integer c = 3;
              Integer d = 3;
              Integer e = 321;
              Integer f = 321;
              Long g = 3L;
              Long h = 2L;
              
              System.out.println(c==d); //true
              System.out.println(e==f); //false
              System.out.println(c==(a+b)); //? true
              System.out.println(c.equals(a+b)); //true
              System.out.println(g==(a+b)); //? true 因为对比时a+b的值都自动装箱为Float
              System.out.println(g.equals(a+b)); //? false
              System.out.println(g.equals(a+h)); //? true
          }
      }
      ```

      原因：当 "=="运算符的两个操作数都是 包装器类型的引用，则是比较指向的是否是同一个对象，而如果其中有一个操作数是表达式（即包含算术运算）则比较的是数值（即会触发自动拆箱的过程）。

      ​	举例 ： 最后部分 a+h触发自动拆箱，一个int一个long 两个加法升级为long类型，触发装箱Long，使用equal都触发Long的longValueOf() 类型都为long比较成功

### 2. 为什么说 Java 中只有值传递

​	我们都知道JVM内存模型中有，stack和heap的存在，但是更准确的说，是每个线程都分配一个独	享的stack，所有线程共享一个heap。对于每个方法的局部变量来说，是绝对无法被其他方法，甚	至其他线程的同一方法所访问到的，更遑论修改。

​	当我们在方法中声明一个 int i = 0，或者 Object obj = null 时，仅仅涉及stack，不影响到heap，	当我们 new Object() 时，会在heap中开辟一段内存并初始化Object对象。当我们将这个对象赋予	obj变量时，仅仅是stack中代表obj的那4个字节变更为这个对象的地址。

### 3. 什么是面向对象

​	把一组数据结构和处理他们的方法组成对象，把相同行为的对象归为类，通过类的封装隐藏内部细节，通过继承实现类的特化/泛化，通过多态实现基于对象类型的动态分配

### 4. Java 如何实现的平台无关

​	a) Java程序只需要与Java平台打交道，而不用管具体的操作系统。

​	b) Java语言保证了基本数据类型的值域和行为都是由语言自己定义的。而C/C++中，基本数据类是	由它的占位宽度决定的，占位宽度由所在平台决定的。不同平台编译同一个C++程序会出现不同的	行为。

​	c) Java class文件。Java程序最终会被编译成二进制class文件。class文件可以在任何平台创建，也	可以被任何平台的Java虚拟机装载运行。它的格式有着严格的定义，是平台无关的。

​	JVM 还支持哪些语言（Kotlin、Groovy、JRuby、Jython、Scala）

### 5.  java基本数据类型



Java语言提供了八种基本类型。六种数字类型（四个整数型，两个浮点型），一种字符类型，还有一种布尔型。

**byte：**

- byte 数据类型是8位、有符号的，以二进制补码表示的整数；
- 最小值是 **-128（-2^7）**；
- 最大值是 **127（2^7-1）**；
- 默认值是 **0**；
- byte 类型用在大型数组中节约空间，主要代替整数，因为 byte 变量占用的空间只有 int 类型的四分之一；
- 例子：byte a = 100，byte b = -50。

**short：**

- short 数据类型是 16 位、有符号的以二进制补码表示的整数
- 最小值是 **-32768（-2^15）**；
- 最大值是 **32767（2^15 - 1）**；
- Short 数据类型也可以像 byte 那样节省空间。一个short变量是int型变量所占空间的二分之一；
- 默认值是 **0**；
- 例子：short s = 1000，short r = -20000。

**int：**

- int 数据类型是32位、有符号的以二进制补码表示的整数；
- 最小值是 **-2,147,483,648（-2^31）**；
- 最大值是 **2,147,483,647（2^31 - 1）**；
- 一般地整型变量默认为 int 类型；
- 默认值是 **0** ；
- 例子：int a = 100000, int b = -200000。

**long：**

- long 数据类型是 64 位、有符号的以二进制补码表示的整数；
- 最小值是 **-9,223,372,036,854,775,808（-2^63）**；
- 最大值是 **9,223,372,036,854,775,807（2^63 -1）**；
- 这种类型主要使用在需要比较大整数的系统上；
- 默认值是 **0L**；
- 例子： long a = 100000L，Long b = -200000L。
  "L"理论上不分大小写，但是若写成"l"容易与数字"1"混淆，不容易分辩。所以最好大写。

**float：**

- float 数据类型是单精度、32位、符合IEEE 754标准的浮点数；
- float 在储存大型浮点数组的时候可节省内存空间；
- 默认值是 **0.0f**；
- 浮点数不能用来表示精确的值，如货币；
- 例子：float f1 = 234.5f。

**double：**

- double 数据类型是双精度、64 位、符合IEEE 754标准的浮点数；
- 浮点数的默认类型为double类型；
- double类型同样不能表示精确的值，如货币；
- 默认值是 **0.0d**；
- 例子：double d1 = 123.4。

**boolean：**

- boolean数据类型表示一位的信息；
- 只有两个取值：true 和 false；
- 这种类型只作为一种标志来记录 true/false 情况；
- 默认值是 **false**；
- 例子：boolean one = true。

**char：**

- char类型是一个单一的 16 位 Unicode 字符；
- 最小值是 **\u0000**（即为0）；
- 最大值是 **\uffff**（即为65,535）；
- char 数据类型可以储存任何字符；
- 例子：char letter = 'A';

### 7.String 问题综合

**1. 字符串比较,使用 "==" 还是 equals() ?**

简单来说, "==" 判断两个引用的是不是同一个内存地址(同一个物理对象).

而 equals 判断两个字符串的值是否相等.

除非你想判断两个string引用是否同一个对象,否则应该总是使用 equals()方法.

如果你了解 

字符串的驻留

 ( 

String Interning

 ) 则会更好地理解这个问题

**2. 对于敏感信息,为何使用char[]要比String更好?**

String是不可变对象

, 意思是一旦创建,那么整个对象就不可改变. 即使新手觉得String引用变了,实际上只是(指针)引用指向了另一个(新的)对象.

而程序员可以明确地对字符数组进行修改,因此敏感信息(如密码)不容易在其他地方暴露(只要你用完后对char[]置0).

**3. 在switch语句中使用String作为case条件?**

从 JDK7 开始,这是可以的,啰嗦一句,Java 6 及以前的版本都不支持这样做.

```java
// 只在java 7及更高版本有效!
switch (str.toLowerCase()) {
      case "a":
           value = 1;
           break;
      case "b":
           value = 2;
           break;
}
```
**4. 转换String为数字**

对于非常大的数字请使用Long,代码如下

```java
int age = Integer.parseInt("10");
long id = Long.parseLong("190"); // 假如值可能很大.
```

**5. 如何通过空白字符拆分字符串**

String 的 split()方法接收的字符串会被当做正则表达式解析,

"\s"代表空白字符,如空格" ",tab制表符"\t", 换行"\n",回车"\r".

而编译器在对源代码解析时,也会进行一次字面量转码,所以需要"\\s".

```java
String[] strArray = aString.split("\\s+");
```
**6. substring()  方法内部是如何处理的?**

在JDK6中,substring()方法还是共用原来的char[]数组,通过偏移和长度构造了一个"新"的String。

想要substring()取得一个全新创建的对象,使用如下这种方式:

```java
String sub = str.substring(start, end) + "";
```
当然 Java 7 中,substring()创建了一个新的char[] 数组,而不是共用.

又一个Java面试的好问题，你应该答出“substring方法通过原字符串创建了一个新的对象”，否则你的回答肯定是不能令人满意的。这个问题也经常被拿来测试应聘者对于substring()可能带来的内存泄漏风险是否有所了解。直到Java 1.7版本之前，substring会保存一份原字符串的字符数组的引用，这意味着，如果你从1GB大小的字符串里截取了5个字符，而这5个字符也会阻止那1GB内存被回收，因为这个引用是强引用。

到了Java 1.7，这个问题被解决了，原字符串的字符数组已经不再被引用，但是这个改变也使得substring()创建字符串的操作更加耗时，以前的开销是O(1)，现在最坏情况是O(n)

**7. String vs StringBuilder vs StringBuffer**

StringBuilder 是可变的,因此可以在创建以后修改内部的值.

StringBuffer 是同步的,因此是线程安全的,但效率相对更低.

**8. 如何重复拼接同一字符串?**

方案1: 使用Apache Commons Lang 库的 StringUtils 工具类.

```java
String str = "abcd";
String repeated = StringUtils.repeat(str,3);//abcdabcdabcd
```

方案2:

使用 StringBuilder 构造. 更灵活.

```java
String src = "name";
int len = src.length();
int repeat = 5;
StringBuilder builder = new StringBuilder(len * repeat);
for(int i=0; i<repeat; i++){
  builder.append(src);
}
String dst = builder.toString();
```

**9. 如何将String转换为日期?**

```java
SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
String str = "2013-11-07";
Date date = format.parse(str);
System.out.println(format.format(date));//2013-11-07
```

**10. 如何统计某个字符出现的次数?**

同样使用Apache Commons Lang 库 StringUtils  类:

```java
int n = StringUtils.countMatches("11112222", "1");
System.out.println(n)
```
**11.String 对“+”的重载、字符串拼接的几种方式和区别**

​	编译器自动引用java.lang.StringBuilder类,用以构造最终的String,为每个”+”调用一次append方法,最后调用toString方法生成最终的String并保存

   ### 8. 关键字

###### 	1. transient: 

 	在实际开发过程中，我们常常会遇到这样的问题，这个类的有些属性需要	序列化，而其他属性不需要被序列化，打个比方，如果一个用户有一些敏	感信息（如密码，银行卡号等），为了安全起见，不希望在网络操作（主	要涉及到序列化操作，本地序列化缓存也适用）中被传输，这些信息对应	的变量就可以加上transient关键字。换句话说，这个字段的生命周期仅存	于调用者的内存中而不会写到磁盘里持久化。

​      	总之，java 的transient关键字为我们提供了便利，你只需要实现     Serilizable接口，将不需要序列化的属性前添加关键字transient，序列化对象	的时候，这个属性就不会序列化到指定的目的地中。	

###### 	2.instanceof

```java
Object a = null;
a instanceof Object; //true or false
```

###### 3.volatile

​	保证指定的值改动后立刻更新到主存中，因为当两个线程操控的时候，同一值，a线程改变了存放在工作内存（高速缓存中），b线程从内存里读不到

这就是缓存不一致问题，java提供了volatile和synchronized还有lock保证。

但是volatile只保证了这个变量改动后立刻写入内存，没保证语句执行是原子的

```java
//使用 lock
public class Test {
    public  int inc = 0;
    Lock lock = new ReentrantLock();
    
    public  void increase() {
        lock.lock();
        try {
            inc++;
        } finally{
            lock.unlock();
        }
    }
    
    public static void main(String[] args) {
        final Test test = new Test();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<1000;j++)
                        test.increase();
                };
            }.start();
        }
        
        while(Thread.activeCount()>1)  //保证前面的线程都执行完
            Thread.yield();
        System.out.println(test.inc);
    }
}

//采用synchronized
public class Test {
    public  int inc = 0;
    
    public synchronized void increase() {
        inc++;
    }
    
    public static void main(String[] args) {
        final Test test = new Test();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<1000;j++)
                        test.increase();
                };
            }.start();
        }
        
        while(Thread.activeCount()>1)  //保证前面的线程都执行完
            Thread.yield();
        System.out.println(test.inc);
    }
}
```

### 9.集合类

###### 1. Java ArrayList、LinkedList、Vector的区别

都实现了List的接口，ArrayList实现了可变大小的数组。Vector和ArrayList几乎是一样的，区别在于Vector是线程安全的。SynchronizedList和Vector最主要的区别： 

1.SynchronizedList有很好的扩展和兼容功能。他可以将所有的List的子类转成线程安全的类。 2.使用SynchronizedList的时候，进行遍历时要手动进行同步处理。 3.SynchronizedList可以指定锁定的对象。

###### 2. Set 和 List 区别？Set 如何保证元素不重复？

List有序，允许重复，Set无序，不允许重复。HashSet根据传入元素的哈希码不同判断是否同一个元素，还有treeSet等只是了解

###### 3. Collection 和 Collections 区别

collection是一个集合接口，collections是有关集合操作的静态方法，类似一个工具类

###### 4.Enumeration 和 Iterator 区别

都是接口， Enumeration**只有2个函数接口。**通过Enumeration，我们只能读取集合的数据，而不能对数据进行修改。
        Iterator**只有3个函数接口。**Iterator除了能读取集合的数据之外，也能数据进行删除操作。

Enumeration 是JDK 1.0添加的接口。使用到它的函数包括Vector、Hashtable等类，这些类都是JDK 1.0中加入的，Enumeration存在的目的就是为它们提供遍历接口。Enumeration本身并没有支持同步，而在Vector、Hashtable实现Enumeration时，添加了同步。
        而Iterator 是JDK 1.2才添加的接口，它也是为了HashMap、ArrayList等集合提供遍历接口。Iterator是支持fail-fast机制的：当多个线程对同一个集合的内容进行操作时，就可能会产生fail-fast事件。

###### 5.fail-fast 和 fail-safe

fail-fash : 在用迭代器遍历一个集合对象时，如果遍历过程中对集合对象的内容进行了修改（增加、删除、修改），则会抛出Concurrent Modification Exception。

fail-safe : 采用安全失败机制的集合容器，在遍历时不是直接在集合内容上访问的，而是先复制原有集合内容，在拷贝的集合上进行遍历。

### 10 枚举

在JDK1.5 之前，我们定义常量都是： public static final.... 。现在好了，有了枚举，可以把相关的常量分组到一个枚举类型里，而且枚举提供了比常量更多的方法。 

```java
public enum Color {  
 RED, GREEN, BLANK, YELLOW  
}
Color c = Color.RED;
...
    switch (color) {  
        case RED:  
            color = Signal.GREEN;  
            break;  
        case YELLOW:  
            color = Signal.RED;  
            break;  
        case GREEN:  
            color = Signal.YELLOW;  
            break;  
        } 

```



实际枚举也是实现单例的最佳方法：可以不用去担心线程安全问题，Enum本身就是线程安全的

```
public enum SinletonA{
    INSTANCE;
    public void method(){}
}
SinletonA a = SinletonA.INSTANCE;
        a.method();
```

