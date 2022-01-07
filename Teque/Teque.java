import java.util.Scanner;
import java.io.File; 
import java.io.FileNotFoundException;

class Teque {

    static Deque left_queue, right_queue;

    static void balance() {
        //Case where left_queue holds more elements than right_queue
        if (left_queue.size - right_queue.size >= 2) {
            right_queue.addFirst(left_queue.eraseLast());
        }
        
        //If the opposite occurs 
        else if (right_queue.size - left_queue.size >= 2) {
            left_queue.addLast(right_queue.eraseFirst());
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        
        left_queue = new Deque();
        right_queue = new Deque();
        Scanner input = new Scanner(System.in); 
        System.out.println("Enter filname: ");
        String filname = input.nextLine();
        Scanner scan = new Scanner(new File(filname));
        
        int numOfOper = Integer.valueOf(scan.nextLine());

        for (int i = 0; i<numOfOper; i++) {
            String[] line = scan.nextLine().strip().split(" "); 
            String instruction = line[0]; 
            
            switch (instruction) {
                case ("push_front"):
                    left_queue.addFirst(Integer.valueOf(line[1]));
                    balance();
                    break;
            
                case ("push_back"): 
                    right_queue.addLast(Integer.valueOf(line[1]));
                    balance();
                    break;
                
                case ("push_middle"):
                    if (left_queue.size >= right_queue.size) {
                        left_queue.addLast(Integer.parseInt(line[1]));
                    }
                    else{
                        right_queue.addFirst(Integer.parseInt(line[1]));
                    }
                    balance();
                    break;

                case ("get"):
                    if(Integer.parseInt(line[1]) < left_queue.size) {
                        System.out.println(left_queue.get(Integer.parseInt(line[1])));
                    } 
                    else{
                        System.out.println(right_queue.get(Integer.parseInt(line[1])-left_queue.size));
                        }
                    break;
                default: 
                    scan.close();
                    break;
        }
        }
    }
}