
import java.util.Scanner;

class BPlusNode {
    int[] keys;
    BPlusNode[] children;
    BPlusNode next;
    int n;
    boolean leaf;

    BPlusNode(int order, boolean leaf) {
        keys = new int[order];
        children = new BPlusNode[order + 1];
        this.leaf = leaf;
        n = 0;
    }
}

class BPlusTree {
    BPlusNode root;
    int order;

    BPlusTree(int order) {
        this.order = order;
        root = new BPlusNode(order, true);
    }

    void insert(int key) {
        if (root.n == order - 1) {
            BPlusNode newRoot = new BPlusNode(order, false);
            newRoot.children[0] = root;
            splitChild(newRoot, 0);
            root = newRoot;
        }
        insertNonFull(root, key);
    }

    void insertNonFull(BPlusNode node, int key) {
        if (node.leaf) {
            int i = node.n - 1;

            while (i >= 0 && node.keys[i] > key) {
                node.keys[i + 1] = node.keys[i];
                i--;
            }

            if (i >= 0 && node.keys[i] == key) {
                System.out.println("Duplicate key not allowed!");
                return;
            }

            node.keys[i + 1] = key;
            node.n++;
        } else {
            int i = 0;

            while (i < node.n && key >= node.keys[i])
                i++;

            if (node.children[i].n == order - 1) {
                splitChild(node, i);

                if (key >= node.keys[i])
                    i++;
            }

            insertNonFull(node.children[i], key);
        }
    }

    void splitChild(BPlusNode parent, int index) {
        BPlusNode left = parent.children[index];
        BPlusNode right = new BPlusNode(order, left.leaf);

        int mid = order / 2;

        if (left.leaf) {
            right.n = left.n - mid;

            for (int j = 0; j < right.n; j++)
                right.keys[j] = left.keys[mid + j];

            left.n = mid;

            right.next = left.next;
            left.next = right;

            for (int j = parent.n; j > index; j--) {
                parent.keys[j] = parent.keys[j - 1];
                parent.children[j + 1] = parent.children[j];
            }

            parent.keys[index] = right.keys[0];
        } else {
            int promoted = left.keys[mid];

            right.n = left.n - mid - 1;

            for (int j = 0; j < right.n; j++)
                right.keys[j] = left.keys[mid + 1 + j];

            for (int j = 0; j <= right.n; j++)
                right.children[j] = left.children[mid + 1 + j];

            left.n = mid;

            for (int j = parent.n; j > index; j--) {
                parent.keys[j] = parent.keys[j - 1];
                parent.children[j + 1] = parent.children[j];
            }

            parent.keys[index] = promoted;
        }

        parent.children[index + 1] = right;
        parent.n++;
    }

    boolean search(int key) {
        BPlusNode current = root;

        while (!current.leaf) {
            int i = 0;

            while (i < current.n && key >= current.keys[i])
                i++;

            current = current.children[i];
        }

        for (int i = 0; i < current.n; i++) {
            if (current.keys[i] == key)
                return true;
        }

        return false;
    }

    void display() {
        BPlusNode current = root;

        while (!current.leaf)
            current = current.children[0];

        System.out.print("B+ Tree elements: ");

        while (current != null) {
            for (int i = 0; i < current.n; i++)
                System.out.print(current.keys[i] + " ");

            current = current.next;
        }

        System.out.println();
    }
}

public class BPlusTreeDemo {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter order of B+ Tree (minimum 3): ");
        int order = sc.nextInt();

        if (order < 3) {
            System.out.println("Order must be at least 3.");
            sc.close();
            return;
        }

        BPlusTree tree = new BPlusTree(order);

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

                    if (tree.search(searchKey))
                        System.out.println(searchKey + " found");
                    else
                        System.out.println(searchKey + " not found");
                    break;

                case 3:
                    tree.display();
                    break;

                case 4:
                    long endTime = System.nanoTime();

                    System.out.println("\nProgram terminated.");
                    System.out.println("Execution Time: " +
                            (endTime - startTime) + " nanoseconds");
                    System.out.println("Execution Time: " +
                            (endTime - startTime) / 1000000.0 +
                            " milliseconds");

                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
