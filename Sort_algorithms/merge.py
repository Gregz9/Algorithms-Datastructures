from countswaps import CountSwaps

def sort(A):
    MergeSort(A)
    return A 

def merge(array, array1, array2): 
    i = 0
    j = 0
    k = 0

    while (i < len(array1)) and (j < len(array2)):
        if array1[i] < array2[j]: 
          #  array[k] = array1[i]
            array.swap3(array1,k,i)
            i += 1 
        else: 
          #  array[k] = array2[j]
            array.swap3(array2,k,j)
            j += 1
        k += 1 
    
    while i < len(array1):
       # array[k] = array1[i]
        array.swap3(array1,k,i)
        i += 1 
        k += 1
    
    while j < len(array2): 
      #  array[k] = array2[j]
        array.swap3(array2,k,j)
        j += 1
        k += 1 
    
    return array

def MergeSort(array): 

    if len(array) > 1: 
        mid = len(array) // 2
        first_list = CountSwaps(array[:mid])
        second_list = CountSwaps(array[mid:])
    
        MergeSort(first_list)
        MergeSort(second_list)
        merge(array, first_list, second_list)
    return array