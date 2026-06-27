package org.example.bits;

public class CountingBits338 {

    //Решение перебором в лоб. н(лог(н))
    public int[] countBits(int n) {
        int[] res = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            res[i] = countOnes(i);
        }

        return res;
    }

    private int countOnes(int x) {
        int count = 0;
        while (x > 0) {
            count += (x & 1);
            x >>= 1;
        }
        return count;
    }


    //Количество единиц в числе равно количеству единиц в числе которое соответствует всем битам кроме последнего (деление на два, сдвиг)
    // и возможно еще + 1 если последний бит единица. Выясняем значение последнего бита операцией И.
    public int[] countBitsDP(int n) {
        int[] res = new int[n + 1];

        res[0] = 0;

        for (int i = 1; i <= n; i++) {
            res[i] = res[i >> 1] + (i & 1);
        }

        return res;
    }
}
