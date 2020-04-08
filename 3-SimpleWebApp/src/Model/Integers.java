package Model;

import java.math.BigInteger;

public class Integers {

    private BigInteger a;
    private BigInteger b;

    public BigInteger getA() {
        return a;
    }

    public void setA(BigInteger a) {
        this.a = a;
    }

    public BigInteger getB() {
        return b;
    }

    public void setB(BigInteger b) {
        this.b = b;
    }

    public Integers(BigInteger a, BigInteger b) {
        this.a = a;
        this.b = b;
    }

    public BigInteger addValues(){
        return this.a.add(this.b);
    }
}
