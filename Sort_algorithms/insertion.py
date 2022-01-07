

def sort(A):

    Insertion_Sort(A)

    # Do quicksort here. Use the Sorter's comparison- and swap
    # methods for automatically counting the swaps and comparisons.

    # Use A.swap(i, j) to swap the values at two indices i and j. The swap vis
    # counted, when using this method. Comparisons are counted automatically.

    return A


def Insertion_Sort(List): 
 
    for i in range(1, len(List)): 
        j = i
        while (j > 0) and (List[j-1] > List[j]): 
            List.swap((j-1), j)
            """"temp_element = List[j-1]
            List[j-1] = List[j]
            List[j] = temp_element """
            j -= 1 
    return List