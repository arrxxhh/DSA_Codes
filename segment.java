public class segment {
    static int tree[];

    public static void init(int n) {
        tree = new int[4 * n];

    }

    public static int buildST(int arr[], int sti, int st, int ei) {
        if (st == ei) {
            tree[sti] = arr[st];
            return arr[st];
        }

        int mid = (st + ei) / 2;
        int l = buildST(arr, 2 * sti + 1, st, mid);
        int r = buildST(arr, 2 * sti + 2, mid + 1, ei);
        tree[sti] = l + r;
        return tree[sti];

    }

    public static int getSum(int arr[], int qi, int qj) {
        int n = arr.length;
        return getSumUtil(0, 0, n - 1, qi, qj);
    }

    public static int getSumUtil(int i, int si, int sj, int qi, int qj) {
        if (qj <= si || qi >= sj)
            return 0;
        else if (si >= qi && sj <= qj)
            return tree[i];
        else {
            int mid = (si + sj) / 2;
            int l = getSumUtil(2 * i + 1, si, mid, qi, qj);
            int r = getSumUtil(2 * i + 2, mid + 1, sj, qi, qj);
            return l + r;
        }

    }

    public static void main(String[] args) {
        int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8 };
        int n = arr.length;
        init(n);
        buildST(arr, 0, 0, n - 1);

        // for (int i = 0; i < tree.length; i++) {
        // System.out.print(tree[i] + " ");
        // }

        System.out.println(getSum(arr, 2, 5));
    }
}