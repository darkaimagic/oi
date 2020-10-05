class STNode {
    int from, to, sum, cnt;
    Node left, right;
    Node(int from, int to) {
        this.from = from;
        this.to = to;
        sum = 0;
        cnt = 0;
        left = null;
        right = null;
    }
    void add(int x) {
        sum += x;
        cnt++;
        if (from < to) {
            int mid = from + (to - from) / 2;
            if (x <= mid) {
                if (left == null)
                    left = new Node(from, mid);
                left.add(x);
            } else {
                if (right == null)
                    right = new Node(mid + 1, to);
                right.add(x);
            }
        }
    }
    int get(int idx) {
        if (from == to)
            return from;
        if (left != null && idx < left.cnt)
            return left.get(idx);
        if (left != null)
            idx -= left.cnt;
        return right.get(idx);
    }
}
