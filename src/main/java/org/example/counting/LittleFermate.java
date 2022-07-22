package org.example.counting;

public class LittleFermate implements Combination{
    final int MOD;
    // Maximum size of pascal triangle
    final int hightOfTriangle;

    final int[] fact;
    final int[] invMod;

    private void findFact(int n) {
        fact[0] = 1;
        for(int i = 1; i <= n; i ++)
            fact[i] = i * fact[i - 1];
    }

    LittleFermate(int mod, int maxN){
        this.MOD = mod;
        hightOfTriangle = maxN;
        fact = new int[hightOfTriangle + 1];
        invMod = new int[hightOfTriangle + 1];


    }

    @Override
    public int getNCR(int n, int r) {
        return 0;
    }
}