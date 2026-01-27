package org.example;

public class ReverseBits190 {

    //короткое
    public int reverseBits1(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result = (result << 1) | (n & 1);
            n >>>= 1; // ВАЖНО: беззнаковый сдвиг
        }
        return result;
    }

    //пошаговое решение что б было понятно
    public int reverseBits(int n) {
        int toReturn = 0;
        for (int i = 0; i < 32; i++) {
            toReturn <<= 1; //двигаем на 1 бит массив результата
            int bitToAdd = n & 1; //получаем последний бит
            toReturn |= bitToAdd; //добавляем его к результату
            n >>>= 1;//двигаем дано на следующий бит без сохранения знака
        }
        return toReturn;
    }
    
    //супер оптимизированное. Разворачиваем по частям масками
    public int reverseBits2(int n) {
        n = (n >>> 16) | (n << 16);

        n = ((n & 0xff00ff00) >>> 8)  | ((n & 0x00ff00ff) << 8);
        n = ((n & 0xf0f0f0f0) >>> 4)  | ((n & 0x0f0f0f0f) << 4);
        n = ((n & 0xcccccccc) >>> 2) | ((n & 0x33333333) << 2);
        n = ((n & 0xaaaaaaaa) >>> 1) | ((n & 0x55555555) << 1);

        return n;
    }
}
