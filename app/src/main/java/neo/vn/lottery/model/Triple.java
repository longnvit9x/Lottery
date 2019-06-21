package neo.vn.lottery.model;

public class Triple<A, B, C> {
    public A first;
    public B second;
    public C third;

    public Triple(A i, B i1, C i2) {
        this.first = i;
        this.second = i1;
        this.third = i2;
    }
}
