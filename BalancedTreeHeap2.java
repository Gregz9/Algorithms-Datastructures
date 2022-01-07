import java.util.Scanner;
import java.util.PriorityQueue;

public class BalancedTreeHeap2 {

    public static void skrivTre(PriorityQueue<Integer> tree, int low, int high) {
       
        if (low > high) {
            return;
        }

        int mid = (low+high)/2 + (low+high)%2;

        PriorityQueue<Integer> a2 = new PriorityQueue<Integer>(tree); 
        for (int i=0; i<mid; i++) {
            a2.poll(); 
        }

        System.out.print(a2.poll() + " "); 
        skrivTre(tree, mid+1, high);
        skrivTre(tree, low, mid-1);

    }


    public static void main(String[] args) {

        PriorityQueue<Integer> tree = new PriorityQueue<>();

        for(int i = 0; i < 11; i++) {
            tree.offer(i);
        }

        skrivTre(tree, 0, tree.size()-1);
    }
}
