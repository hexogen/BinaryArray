# Binary Array
Binary array data structure

[![Build Status](https://travis-ci.org/hexogen/binaryArray.svg?branch=master)](https://travis-ci.org/hexogen/binaryArray)

Binary array data structure. 


## Data structure features

|                            |  Linked list       |  Array    | Dynamic array | Binary array |
|----------------------------|--------------------|-----------|---------------|--------------|
| Get at index               |        Θ(n)        |	   Θ(1)   | 	Θ(1)	  |   Θ(log n)   |
| Insert/delete at beginning |	      Θ(1)        |    N/A	  |     Θ(n)	  |   Θ(log n)   |
| Insert/delete at end	     |        Θ(1)        |    N/A	  |     Θ(1)      |   Θ(log n)   |
| Insert/delete in middle	 | search time + Θ(1) |	   N/A    | 	Θ(n)	  |   Θ(log n)   |
| Wasted space (average)	 |        Θ(n)        | 	0     |     Θ(n)      |   Θ(n)       |

## License

The Laravel framework is open-sourced software licensed under the [MIT license](http://opensource.org/licenses/MIT).
