class STNode {
    int from, to, mid;
    long incVal, sumVal;
    STNode left, right;
    STNode(int from, int to) {
        this.from = from;
        this.to = to;
        mid = from + (to - from) / 2;
        incVal = 0;
        sumVal = 0;
        left = null;
        right = null;
    }
    void inc(int p, int q, long val) {
        sumVal += val * (q - p + 1);
        if (p == from && q == to) {
            incVal += val;
            return;
        }
        if (p <= mid && left == null)
            left = new STNode(from, mid);
        if (q > mid && right == null)
            right = new STNode(mid + 1, to);
        if (q <= mid)
            left.inc(p, q, val);
        else if (p > mid)
            right.inc(p, q, val);
        else {
            left.inc(p, mid, val);
            right.inc(mid + 1, q, val);
        }
    }
    long sum(int p, int q) {
        if (p == from && q == to)
            return sumVal;
        pushDown();
        if (q <= mid)
            return left.sum(p, q);
        else if (p > mid)
            return right.sum(p, q);
        return left.sum(p, mid) + right.sum(mid + 1, q);
    }
    void pushDown() {
        if (left == null)
            left = new STNode(from, mid);
        if (right == null)
            right = new STNode(mid + 1, to);
        left.incVal += incVal;
        right.incVal += incVal;
        left.sumVal += incVal * (left.to - left.from + 1);
        right.sumVal += incVal * (right.to - right.from + 1);
        incVal = 0;
    }
}
