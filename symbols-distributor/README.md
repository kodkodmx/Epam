# Symbol Distributor

The purpose of this task is to practice using `FileInputStream` and `FileOutputStream`.

Duration: _1 hour_

## Description

In this task, your goal is to implement `SymbolsDistributor` interface and made `SymbolsDistributorFactory` returns it.
`SymbolsDistributor` reads a passed `inputFile` and distribute it symbols through multiple files which are described
in `outputMapping`. `outputMapping` is a map of `Predicate<Integer>` against output `File`. Please use keys of this map
to determine should a symbol goes to a specified output file or not. Be aware, that output file must be created before
writing to it. Input file must be also checked, because it might not be readable or doesn't even exist.
Moreover, `distribute` method must not throw any checked exception.

## Requirements

The input parameters are never `null` or empty.

* `SymbolsDistributionFactory.getInstance` must return an instance of `SymbolsDistributor`
* `distribute` method must throw `IllegalArgumentException` when:
  * `inputFile` does not exist
  * `inputFile` is not readable
  * `inputFile` is not a regular (normal) file, e.g. it's a directory
* `distribute` method must not throw any checked exception
* `distribute` must read an `inputFile` and distribute its symbols through multiple output files using the corresponding
predicates
* unsupported symbols must be ignored
* letters case must be preserved
* order of symbols must be preserved
* no additional symbols must be present in output files (no new-line is required at the end of a file)

## Examples

```java
distributor.distribute(inputFile, Map.of(
    Character::isLetter, lettersFile,
    Character::isDigit, digitsFile
))
```

Input file:
```
Hello 42 world!
```

Letters file:
```
Helloworld
```

Digits file:
```
42
```
