# 遇到的一些问题

## 1、判断int类型是空

```java
int c = 10;

// 错误
c == null

// 正确
String.valueOf(c).equals("")  // 返回true或false
```

## 2、常见包所在的目录

 - import java.util.*;
    - Map
    - HashMap

 - import java.lang.*;
    - String
    - Object

## 3、HashMap API

```java
/**
   map在用get(key)之前，需要先用containsKey(key)判断存不存在

   map.get(key)
      如果没有找到，函数将会抛出一个NullPointException，空指针异常。
      要么catch，要么在get之前用containsKeys判断。
 */
Map<Integer,Integer> map = new HashMap<Integer,Integer>();

if(map.containsKey("key"){
   int index = map.get("key");
}
```
