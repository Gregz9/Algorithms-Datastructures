import java.util.PriorityQueue; 
import java.io.File; 
import java.io.FileNotFoundException;
import java.util.Scanner; 
import java.util.ArrayList; 
import java.util.Comparator;
import java.lang.Character;


/* Some small fixes needed, cause the code isn't working properly*/
class Huffman {

    static class Node {
        Node left, right; 
        char key;
        int value; 

    } 

    static ArrayList<Node> insertToQueue() throws FileNotFoundException{
        
        ArrayList<Node> firstStep = new ArrayList<>(); 
        Scanner userInput = new Scanner(System.in); 
        System.out.println("Enter filename with data for Huffman-tree: ");
        String filename = userInput.nextLine(); 

        Scanner reader = new Scanner(new File(filename));
        String notNeeded = reader.nextLine();

        while(reader.hasNextLine()) {
            
            String[] wordAndFreq = reader.nextLine().strip().replace("(", "").replace(")", "").split(" ");
            //System.out.println(wordAndFreq[0] + " " +wordAndFreq[1]);

            Node node = new Node();
            char c = wordAndFreq[0].charAt(0);
            node.key = c;
            node.value = Integer.parseInt(wordAndFreq[1]);
            node.left = null; 
            node.right = null;
            firstStep.add(node);
        }

        reader.close();

        ArrayList<Node> preHuff = new ArrayList<>();
        

        while(firstStep.size()>0) {
            Node smallestEl = firstStep.get(0);
            for(int i = 0; i < firstStep.size(); i++) {
                if(firstStep.get(i).value < smallestEl.value) {
                    smallestEl = firstStep.get(i);
                }
            }
            int index = firstStep.indexOf(smallestEl);
            preHuff.add(firstStep.remove(index));
        }

        return preHuff;
    }



            /* Algorithm Huffman(C):
                Input: A set, C, of d characters, each with a given weight, f(c)
                Output: A coding tree, T, for C, with minimum total path weight
                
                Initialize a priority queue Q.
                for each character c in C do
                    Create a single-node binary tree T storing c.
                    Insert T into Q with key f(c).
                while Q.size() > 1 do
                    f1 <= Q.minKey()
                    T1 <= Q.removeMin()
                    f2 <= Q.minKey()
                    T2 <= Q.removeMin()
                    Create a new binary tree T with left subtree T1 and right subtree T2.
                    Insert T into Q with key f1 + f2.
                return tree Q.removeMin()*/
       
    static void printHuff(Node root, String s) {

        if(root.left == null && root.right == null && Character.isLetter(root.key)) {
            System.out.println(root.key + ":" + s);
            return;
        }
        printHuff(root.left, s+"0");
        printHuff(root.right, s+"1");
    }

    public static void main(String[] args)throws FileNotFoundException {

        ArrayList<Node> preHuff = insertToQueue(); 
        /*This arrayList is sorted from char with lowest weight, up to the 
        char with highest weight*/
        Node root = null;
        while(preHuff.size() > 1) {

            Node T1 = preHuff.remove(0); 
            Node T2 = preHuff.remove(0);
            Node huff = new Node();
            huff.value = (T1.value + T2.value);
            huff.key = '-';
            huff.left = T1;
            huff.right = T2;
            root = huff;


            preHuff.add(0, root);
        }
        printHuff(root, "");
    }
}