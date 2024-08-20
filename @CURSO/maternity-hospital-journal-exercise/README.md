# Maternity Hospital Journal

The purpose of this exercise is to apply various algorithms to collections using the methods of the `Collections` class.

Duration: **1 hour**


## Description

In this task, you will simulate the work of a weekly maternity hospital journal. The internal structure of the journal is a collection of the `Map` type, where the key is the weekday (an enum of the `WeekDay` type) and the value is a list of babies born during the week (objects of the `Baby` type).  
The `Baby` class describes a baby with the following characteristics: `name`, `weight`, `height`, `gender`, and `birth time`. All characteristics are passed to the constructor to create an object. **You must not change this class.**  
Please note that once a birth journal has been created and filled in, it cannot be changed. This needs to be guaranteed.  
Now, please proceed to the `BirthJournalManagementImpl` class, which implements the `BirthJournalManagement` interface, and provide implementations of the following methods:
* `boolean addEntryOfBaby(WeekDay day, Baby baby)`  
   Adds an entry for the specified baby to the journal; returns true if the addition was successful and false if not
* `void commit()`  
   Makes the journal immutable
* `int amountBabies()`   
   Returns the number of babies born in a given week
* `List<Baby> findBabyWithHighestWeight(String gender)`  
   Finds the baby of the specified gender with the highest weight  
   If there are multiple babies, it sorts them alphabetically by name.
* `List<Baby> findBabyWithSmallestHeight(String gender)`  
   Finds the baby of the specified gender with the smallest height  
   If there are multiple babies, it sorts them in order of increasing weight  
* `Set<Baby> findBabiesByBirthTime(String from, String to)`  
   Finds babies born in the specified time period

### Details:
* Time of birth is given as a `String` in the form `"hour:minute"`.
* For implementations of all methods, the parameters passed to them are guaranteed to be correct.
* You can add any private methods to the `BirthJournalManagementImpl` class.

## Restrictions:

You may not use lambdas or streams to complete this task.
