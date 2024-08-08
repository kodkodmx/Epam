# Ranged Ops Integer Set

The purpose of this exercise is for you to practice using collections, namely Set.

Duration: **45 minutes**


## Description

In this task, you need to demonstrate how to work with a set of integers encapsulated in the `RangedOpsIntegerSet` class.  

Please proceed to the RangedOpsIntegerSet class and provide implementations of the following methods:  

*	`boolean add(final Integer integer)`  
   Adds a single value to the set

*	`boolean remove(final Object o)`  
   Removes a single value from the set  

*	`boolean add(int fromInclusive, int toExclusive)`  
   Adds a range of values to the set (first argument—inclusive; last argument—exclusive); returns true if range values were actually added  

*	`boolean remove(int fromInclusive, int toExclusive)`  
   Removes a range of values from the list (first argument—inclusive; last argument—exclusive); returns true if range values were actually removed  

*	`Iterator<Integer> iterator()`   
   Returns an iterator to iterate over elements of the set in natural order  

*	`int size()`  
   Returns the number of current elements

## Restrictions
You may not use lambdas   or streams when implementing this task.

## Examples
1. Adding one range:
```java
RangedOpsIntegerSet set = new RangedOpsIntegerSet();
set.add(1, 5);
for(Integer el : set){
    System.out.println(el);
}
```
Output:
```
1
2
3
4
```

---
2. Adding two ranges:  
```java
RangedOpsIntegerSet set = new RangedOpsIntegerSet();
set.add(1, 5);
set.add(9, 11);
for(Integer el : set){
    System.out.println(el);
}
```

```
1
2
3
4
9
10
```

---
3. Adding and removing ranges:  
```java
RangedOpsIntegerSet set = new RangedOpsIntegerSet();
set.add(1, 15);
set.remove(3, 12);
for(Integer el : set){
    System.out.println(el);
}
```

```
1
2
12
13
14
```

---
