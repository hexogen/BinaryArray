# Binary Array
Binary array data structure

[![Build Status](https://travis-ci.org/hexogen/binaryArray.svg?branch=master)](https://travis-ci.org/hexogen/binaryArray)

## API

* __BinaryArray:get(int index)__  : Get item with the given index position.
* __BinaryArray:set(int index, T value)__  : Set a value to cell with the given index position.
* __BinaryArray:push(T value)__  : Push value to the end of array.
* __BinaryArray:delete(int index)__  : Delete item with the given relative position.
* __BinaryArray:iterator()__  : Get new iterator with zero start position.
* __BinaryArray:iterator(int start)__  : Get new iterator with the given start position

## Data structure features

|                            |  Linked list       |  Array    | Dynamic array | Binary array |
|----------------------------|--------------------|-----------|---------------|--------------|
| Get at index               |        Θ(n)        |	   Θ(1)   | 	Θ(1)	  |   Θ(log n)   |
| Insert at beginning        |	      Θ(1)        |    N/A	  |     Θ(n)	  |   Θ(n)       |
| Delete at beginning        |	      Θ(1)        |    N/A	  |     Θ(n)	  |   Θ(log n)   |
| Insert at end              |        Θ(1)        |    N/A	  |     Θ(1)      |   Θ(1)       |
| Delete at end	             |        Θ(1)        |    N/A	  |     Θ(1)      |   Θ(log n)   |
| Insert in middle	         | search time + Θ(1) |	   N/A    | 	Θ(n)	  |   Θ(n)       |
| Delete in middle	         | search time + Θ(1) |	   N/A    | 	Θ(n)	  |   Θ(log n)   |
| Wasted space (average)	 |        Θ(n)        | 	0     |     Θ(n)      |   Θ(n)       |

## License

The Binary array data structure is open-sourced software licensed under the [MIT license](http://opensource.org/licenses/MIT).
