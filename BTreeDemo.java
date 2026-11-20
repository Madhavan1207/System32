
import java.util.Scanner;

class BTreeNode {
    int[] keys;
    int t;
    BTreeNode[] child;
    int n;
    boolean leaf;

    BTreeNode(int t, boolean leaf) {
        this.t = t;
        this.leaf = leaf;
        keys = new int[2 * t - 1];
        child = new BTreeNode[2 * t];
        n = 0;
    }

    void traverse() {
        int i;
        for (i = 0; i < n; i++) {
            if (!leaf)
                child[i].traverse();
            System.out.print(keys[i] + " ");
        }
        if (!leaf)
            child[i].traverse();
    }

    BTreeNode search(int key) {
        int i = 0;
        while (i < n && key > keys[i])
            i++;

        if (i < n && keys[i] == key)
            return this;

        if (leaf)
            return null;

        return child[i].search(key);
    }

    void insertNonFull(int key) {
        int i = n - 1;

        if (leaf) {
            while (i >= 0 && keys[i] > key) {
                keys[i + 1] = keys[i];
                i--;
            }
            keys[i + 1] = key;
            n++;
        } else {
            while (i >= 0 && keys[i] > key)
                i--;
            i++;

            if (child[i].n == 2 * t - 1) {
                splitChild(i, child[i]);
                if (keys[i] < key)
                    i++;
            }
            child[i].insertNonFull(key);
        }
    }

    void splitChild(int i, BTreeNode y) {
        BTreeNode z = new BTreeNode(t, y.leaf);
        z.n = t - 1;

        for (int j = 0; j < t - 1; j++)
            z.keys[j] = y.keys[j + t];

        if (!y.leaf) {
            for (int j = 0; j < t; j++)
                z.child[j] = y.child[j + t];
        }

        y.n = t - 1;

        for (int j = n; j >= i + 1; j--)
            child[j + 1] = child[j];

        child[i + 1] = z;

        for (int j = n - 1; j >= i; j--)
            keys[j + 1] = keys[j];

        keys[i] = y.keys[t - 1];
        n++;
    }
}

class BTree {
    BTreeNode root;
    int t;

    BTree(int t) {
        this.t = t;
        root = null;
    }

    void insert(int key) {
        if (root == null) {
            root = new BTreeNode(t, true);
            root.keys[0] = key;
            root.n = 1;
        } else {
            if (root.n == 2 * t - 1) {
                BTreeNode s = new BTreeNode(t, false);
                s.child[0] = root;
                s.splitChild(0, root);

                int i = 0;
                if (s.keys[0] < key)
                    i++;

                s.child[i].insertNonFull(key);
                root = s;
            } else {
                root.insertNonFull(key);
            }
        }
    }

    void search(int key) {
        if (root == null || root.search(key) == null)
            System.out.println(key + " not found");
        else
            System.out.println(key + " found");
    }

    void traverse() {
        if (root != null)
            root.traverse();
        System.out.println();
    }
}

public class BTreeDemo {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter minimum degree: ");
        int t = sc.nextInt();

        if (t < 2) {
            System.out.println("Minimum degree must be at least 2.");
            sc.close();
            return;
        }

        BTree tree = new BTree(t);

        long startTime = System.nanoTime();

        while (true) {
            System.out.println("\n1. Insert");
            System.out.println("2. Search");
            System.out.println("3. Display");
            System.out.println("4. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter key: ");
                    int key = sc.nextInt();
                    tree.insert(key);
                    break;

                case 2:
                    System.out.print("Enter key to search: ");
                    int searchKey = sc.nextInt();
                    tree.search(searchKey);
                    break;

                case 3:
                    System.out.print("B-Tree elements: ");
                    tree.traverse();
                    break;

                case 4:
                    long endTime = System.nanoTime();

                    System.out.println("\nProgram terminated.");
                    System.out.println("Execution Time: " +
                            (endTime - startTime) + " nanoseconds");
                    System.out.println("Execution Time: " +
                            (endTime - startTime) / 1000000.0 + " milliseconds");

                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
// TIME COMPLEXITY:
// Insertion: O(log n)
// Searching: O(log n)
// Traversal: O(n)

// SPACE COMPLEXITY:
// Insertion: O(log n) - Recursive stack space
// Searching: O(log n) - Recursive stack space
// Traversal: O(log n) - Recursive stack space
// Overall Tree Storage: O(n)