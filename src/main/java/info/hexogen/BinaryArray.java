package info.hexogen;

import java.util.Iterator;

public class BinaryArray <T> implements Iterable<T>{
    private boolean[] exists; //item is added and not removed
    private int[] offsets; //array offset
    private T[] items; //items buffer
    private int size; //array buffer size
    private int length; //num of elements in array
    private int last; //last element id
    
    public BinaryArray () {
        init(1);
    }
    
    public BinaryArray (int size) {
        init(size);
    }
    
    private void init(int size) {
        this.size = size;
        length = 0;
        last = -1;
        exists = new boolean[size];
        offsets = new int[size];
        items = (T[]) new Object[size];
        
        for (int i = 0; i < size; i++) {
            offsets[i] = 0;
        }
    }
    
    public T get(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException();
        }
        
        int lo = 0;
        int hi = size - 1;
        int mid = lo + (hi - lo) / 2;
        int offset = offsets[mid];
        int k = mid + offset;

        while (lo <= hi) {
            if (k > index) {
                hi = mid - 1;
            } else if (k < index) {
                lo = mid + 1;
            } else if (!exists[mid]) {
                hi = mid - 1;
            } else {
                return items[mid];
            }
            mid = lo + (hi - lo) / 2;
            offset += offsets[mid];
            k = mid + offset;
        }
        
        throw new RuntimeException("Index not found");
    }
    
    public void set(int index, T value) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index shuld be positive integer");
        }
        
        if (index >= size) {
            resize(index);
        }
        
        if (index >= length) {
            insert(index, value);
        } else {
            update(index, value);
        }
        
    }
    
    public void push(T value) {
        int next = last + 1;
        if (next >= size) {
            resize(size + 1);
        }
        items[next] = value;
        exists[next] = true;
        length++;
        last++;
    }
    
    public void delete(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException();
        }
        
        int lo = 0;
        int hi = size - 1;
        int mid = lo + (hi - lo) / 2;
        int offset = offsets[mid];
        int k = mid + offset;
        
        boolean deleted = false;
       
        while (lo <= hi) {
            if (index < k) {
                if (deleted == false) {
                    offsets[mid]--;
                    deleted = true;
                }
                hi = mid - 1;
            } else if (index > k) {
                if (deleted) {
                    offsets[mid]++;
                    deleted = false;
                }
                lo = mid + 1;
            } else {
                exists[mid] = false;
                items[mid] = null;
                if (deleted == false) {
                    offsets[mid]--;
                }
                if (mid > lo) {
                    int left = lo + (mid - lo - 1) / 2;
                    offsets[left]++;
                }
                return;
            }
            mid = lo + (hi - lo) / 2;
            offset += offsets[mid];
            k = mid + offset;
        }
        
        throw new RuntimeException("Index not found");
    }
    
    private void update(int index, T value) {
        int hi = 0;
        int lo = length - 1;
        int mid = lo + (hi - lo) / 2;
        int offset = offsets[mid];
        int k = mid + offset;

        while (lo <= hi && (k != index || exists[mid] == false)) {
            if (k < index) {
                hi = mid - 1;
            } else if (k > index) {
                lo = mid + 1;
            } else if (!exists[mid]) {
                hi = mid - 1;
            } else {
                items[mid] = value;
                return;
            }
            mid = lo + (hi - lo) / 2;
            k += offsets[mid];
        }
        
        throw new RuntimeException("Index not found");
    }
    
    private void insert(int index, T value) {
        for (int i = last + 1; i < index; i++) {
            offsets[i] = 0;
            items[i] = null;
            exists[i] = true;
        }
        length += index - last + 1; 
        last = index;
        items[last] = value;
        exists[last] = true;
    }

    private void resize(int index) {
        int pow = (int) Math.round(Math.log(index) / Math.log(2));
        
        if (Math.pow(2, pow) <= index) {
            pow++;
        }
        
        int newSize = (int) Math.pow(2, pow);
        
        boolean[] newExists = new boolean[newSize];
        int[] newOffset = new int[newSize];
        T[] newItems = (T[]) new Object[newSize];
        
        for (int i = 0, j = 0; i <= last; i++) {
            if (exists[i]) {
                newExists[j] = true;
                newItems[j++] = items[i];
            }
        }
        
        for (int i = 0; i < newSize; i++) {
            newOffset[i] = 0;
        }
        
        size = newSize;
        exists = newExists;
        offsets = newOffset;
        items = newItems;
        last = length - 1;
    }
    
    private class BinaryArrayIterator implements Iterator<T> {
        
        private int current;
        
        private BinaryArrayIterator () {
            init(0);
        }
        
        private BinaryArrayIterator(int start) {
            init(start);
        }
        
        private void init (int start) {
            if (start < 0 || start >= length) {
                throw new IndexOutOfBoundsException();
            }

            current = -1;
            
            if(start == 0 && length == 0) {
                return;
            }
            
            int lo = 0;
            int hi = size - 1;
            int mid = lo + (hi - lo) / 2;
            int offset = offsets[mid];
            int k = mid + offset;

            while (lo <= hi) {
                if (k > start) {
                    hi = mid - 1;
                } else if (k < start) {
                    lo = mid + 1;
                } else if (!exists[mid]) {
                    hi = mid - 1;
                } else {
                    current = mid;
                    break;
                }
                mid = lo + (hi - lo) / 2;
                offset += offsets[mid];
                k = mid + offset;
            }

            if (current == -1) {
                throw new RuntimeException("Index not found");
            }
        }

        @Override
        public boolean hasNext() {
            return current != -1;
        }

        @Override
        public T next() {
            if (current == -1) {
                throw new IndexOutOfBoundsException();
            }
            
            T value = items[current];
            
            if (current == last) {
                current = -1;
            } else {
                current++;
                while (!exists[current]) {
                    current++;
                }
            }
            
            return value;
        }
    
    }

    @Override
    public Iterator<T> iterator() {
        return new BinaryArrayIterator();
    }
    
    public Iterator<T> iterator(int start) {
        return new BinaryArrayIterator(start);
    }
}
/*
* The MIT License (MIT)
* Copyright (c) 2016 Volodymyr Basarab
* 
* Permission is hereby granted, free of charge, to any person obtaining
* a copy of this software and associated documentation files (the "Software"),
* to deal in the Software without restriction, including without limitation
* the rights to use, copy, modify, merge, publish, distribute, sublicense,
* and/or sell copies of the Software, and to permit persons to whom
* the Software is furnished to do so, subject to the following conditions:
* 
* The above copyright notice and this permission notice shall be included
* in all copies or substantial portions of the Software.
* 
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
* EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
* OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
* IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
* DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
* TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
* OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
