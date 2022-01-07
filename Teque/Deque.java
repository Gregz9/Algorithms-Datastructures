

class Deque {
    
    int low, high; 
    int size = 0; 
    int[] queue; 

    Deque() {
        queue = new int[1000000];
        low = queue.length/2; 
        high = low; 
    }

    void addFirst(int i) {
        if(low == high) {
            high++; 
        }

        queue[low] = i; 
        low--;
        size++; 
    }

    void addLast(int i) {
        if(low == high) {
            low--;
        }

        queue[high] = i; 
        high++;
        size++;
    }
    

    int eraseFirst() {
        int data = queue[low -1]; 
        queue[low-1] = 0;
        low++;
        size--;
        return data;
    }

    int eraseLast() {
        int data = queue[high-1];
        queue[high-1] = 0;  
        high--; 
        size--;
        return data;
    }

    int get(int ind) {

        return (low+ind);
    }



}