package org.example.desine;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PeekingIterator284 {

    public class PeekingIterator<E> implements Iterator<E> {

        private final Iterator<E> iterator;

        private E peekedElement;
        private boolean hasPeeked;

        public PeekingIterator(Iterator<E> iterator) {
            this.iterator = iterator;
        }

        public E peek() {
            if (!hasPeeked) {
                if (!iterator.hasNext()) {
                    throw new NoSuchElementException();
                }

                peekedElement = iterator.next();
                hasPeeked = true;
            }

            return peekedElement;
        }

        @Override
        public E next() {
            if (!hasPeeked) {
                return iterator.next();
            }

            E result = peekedElement;

            hasPeeked = false;
            peekedElement = null;

            return result;
        }

        @Override
        public boolean hasNext() {
            return hasPeeked || iterator.hasNext();
        }
    }

    //не дженерик для оригинальной задачи
    class PeekingIterator1 implements Iterator<Integer> {
        private Integer peeked = null;
        //тут надо делать с флагом, а не нулом. Потому что если в коллекции будет нул ето приведет к не корректному поведению
        private final Iterator<Integer> iterator;

        public PeekingIterator1(Iterator<Integer> iterator) {
            // initialize any member here.
            this.iterator = iterator;

        }

        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {
            if (peeked == null) {//проверка на хез некст?
                peeked = iterator.next();
            }
            return peeked;

        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {
            if (peeked == null) {
                return iterator.next();
            }
            Integer toReturn = peeked;
            peeked = null;
            return toReturn;
        }

        @Override
        public boolean hasNext() {
            if (peeked == null) {
                return iterator.hasNext();
            }
            return true;

        }
    }
}
