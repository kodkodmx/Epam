# License Reader

The purpose of this task is to practice with reading, processing and writing to a file using Java *Reader/*Writer
classes and their buffered variants, and using `try-with-resources`.

Duration: _1.5 hours_

## Description

In this task you will implement `LicenseReader#collectLicenses(File, File)` method. It must read all license files
from `root`, format these licenses to a specific format and write all of them into `outputFile`.
`root` might be a directory or just a regular file. Please note that you need to collect files not only from the
root directory, but also from its subdirectories and their subdirectories, etc. All non-license files must be ignored.
Be aware, that this method must not throw any checked exception.

Take a look at the license header format:

```
---
License: <license name>
Issued by: <issuer>
Issued on: <date>
Expires on: <date>
---
```

The order of these properties is not restricted, so you may expect that `Expires on` might occur earlier than
`License`. All of them are mandatory except for `Expires on`. The date format is ISO date (`yyyy-mm-dd`). The license
is wrapped between lines containing 3 dashes (`---`). The file always starts with license header, otherwise it's just
a regular file.

If license header is invalid, then `IllegalArgumentException` must be thrown. If either `root` or `outputFile` is null,
then `IllegalArgumentException` must be thrown. If `root` does not exist, or it's not readable, then the same
exception must be thrown. And if `root` is a directory, but is not executable (means that it could not be entered),
then `IllegalArgumentException` must be thrown.

The output format for just one license looks like:

```
License for <file name> is <license name> issued by <issuer> [<issuedOn date> - <expiresOn date>]
```

Each line must contain the information about license in this format. If license does not have an expiration date,
then instead of `<expiresOn date>` - `unlimited` must be used.

If `outputFile` exists, then it must be cleared. So no appending is allowed.

## Requirements

* `collectLicenses` method must verify that `root` is:
    * not null
    * exists
    * readable
    * if it's a directory, then that it's executable
* `collectLicenses` method must verify that `outputFile` is not null
* if `outputFile` is not empty, then it must be cleared before writing to it
* all files and directories of `root` must be processed
* non-license files must be ignored
* license files must be processed and info about it must be stored in `outputFile` (see formats in `Description`
  section)
* if license format is invalid, or any verification is failed, then `IllegalArgumentException` must be thrown
* all license properties are mandatory, except for `Expires on` (it's optional)
* the order of license properties are undefined
* the order in the resulting file does not matter
* if `Expires on` is omitted, then in `unlimited` must be used
* `collectLicenses` does not throw any checked exception
* `BufferedWriter`/`BufferedReader` must be used for IO operations
* All IO buffered streams must be defined using `try-with-resources` syntax

## Examples

libA/libraryA.lic
```
---
License: GNU
Issued by: GNU Commitet
Issued on: 2020-01-01
Expires on: 2025-01-01
---

Library description...
```

libraryB.lic
```
---
License: Custom
Issued by: Goverment
Issued on: 1984-04-13
---

Top secret info...
```

output
```
License for libA/libraryA.lic is GNU issued by GNU Commitet [2020-01-01 - 2025-01-01]
License for libraryB.lic is Custom issued by Goverment [1984-04-13 - unlimited]
```