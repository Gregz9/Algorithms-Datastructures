import java.util.ArrayList; 


class BSTArray {

    static void printBBST(int[] array, int low, int high) {

        if(low > high) {
            return; 
        }

        int newMid = (low+high)/2 + (low+high)%2;

        
        System.out.print(array[newMid] + " ");

            printBBST(array, newMid+1, high);
            printBBST(array, low, newMid-1);   
    }

    public static void main(String[] args) {

        int[] preTree = { 0,1,2,3,4,5,6,7,8,9,10};

        printBBST( preTree, 0, preTree.length-1);

        
    }

}