import java.util.*;

public class max_min_segment {
    static int tree[];

    // Max type problem ST
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
        tree[sti] = Math.max(l, r);
        return tree[sti];
    }

    // Queries
    public static int getMax(int arr[], int qi, int qj) {
        int n = arr.length;
        return getMaxUtil(0, 0, n - 1, qi, qj);
    }

    public static int getMaxUtil(int i, int si, int sj, int qi, int qj) {// O(logn)
        if (qj < si || qi > sj)
            return Integer.MIN_VALUE;
        else if (si >= qi && sj <= qj)
            return tree[i];
        else {
            int mid = (si + sj) / 2;
            int l = getMaxUtil(2 * i + 1, si, mid, qi, qj);
            int r = getMaxUtil(2 * i + 2, mid + 1, sj, qi, qj);
            return Math.max(l, r);
        }

    }

    // Update
    public static void update(int arr[], int idx, int val) {
        int n = arr.length;
        arr[idx] = val;
        updateUtil(0, 0, n - 1, idx, val);
    }

    public static void updateUtil(int i, int si, int sj, int idx, int val) {// O(logn)
        if (idx > sj || idx < si)
            return;
        tree[i] = Math.max(tree[i], val);
        if (si != sj) {
            int mid = (si + sj) / 2;
            updateUtil(2 * i + 1, si, mid, idx, val);
            updateUtil(2 * i + 2, mid + 1, sj, idx, val);
        }
    }

    public static void main(String[] args) {
        int arr[] = { 6, 8, -1, 2, 17, 1, 3, 2, 4 };
        int n = arr.length;
        init(n);
        buildST(arr, 0, 0, n - 1);

        for (int i = 0; i < tree.length; i++) {
            System.out.print(tree[i] + " ");
        }
        System.out.println(getMax(arr, 2, 5));// 17
        update(arr, 2, 20);
        System.out.println(getMax(arr, 2, 5));// 20
    }
}