
def sort(A): 

    HeapSort(A)

    return A 

def BubbleDown(array, index, num): 
    largest = index
    left = 2*index + 1
    right = 2*index + 2

    if (left < num) and (array[largest] < array[left]): 
        largest, left = left, largest
        array.swap5()

    if (right < num) and (array[largest] < array[right]): 
        largest, right = right, largest
        array.swap5()

    if (index != largest): 
      #  array[index], array[largest] = array[largest], array[index]
        array.swap(index, largest)
        BubbleDown(array, largest, num)
    

def BuildMaxHeap(array, num): 
    for i in range(num//2, -1, -1): 
        BubbleDown(array, i, num)

def HeapSort(array): 
    BuildMaxHeap(array, len(array))
    for i in range(len(array)-1, -1, -1): 
        array.swap(0,i)
        #array[0], array[i] = array[i], array[0]
        BubbleDown(array, 0, i)
    return array