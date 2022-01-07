import java.util.Scanner;
import java.util.ArrayList;  
import java.util.HashMap; 



class KittenOnATree<T> { 

    Node first = null;

    class Node {
        Node parent, next;  
        int value; 

        Node(int V) {
            value = V; 
        }

        HashMap<int, Node> n = new HashMap<>(); 

    void add(int data){
        Node gren = new Node(data); 

        if(first == null) {
            first = gren; 
        }
        else {
            gren.next = first.next; 
            gren.next = first; 
            first = gren; 
        }
    }

    ArrayList<Integer> getAncestors(Node T) {
        Node temp = T; 
        ArrayList<Integer> ancestors = new ArrayList<>(); 
        while(temp.parent != null) {
            ancestors.add(temp.parent.value);
            temp.parent = temp.parent.parent;
        }
        return ancestors; 
    }  

    }
}

class KattTest{
    public static void main(String[] args) {

        Scanner inn = new Scanner(System.in);

        String grenMedKatt = inn.nextLine(); 

        while(inn.nextLine() != "-1") {
            
            String[] linje = inn.nextLine().split(" "); 
            
        }
    }
}