package org.example;

public class NumberOf1Bits191 {
    public int hammingWeight(int n) {
        int count = 0;

        while (n != 0) {
            //операция и с числом умешеным на единицу сбрасывает
            n &= (n - 1);// последнюю установленный бит
            count++;
        }
        return count;
    }
}
