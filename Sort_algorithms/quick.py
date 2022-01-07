
import random

def sort(A):  
    QuickSort(A, 0, len(A)-1)

    return A

def ChoosePivot(lista, low, high):
 
    pivot = random.choice(lista[low:high])

    return pivot

def Partition(lista, low, high): 

    p = ChoosePivot(lista, low, high)
    pivot = lista[high]
    left = low 
    right = high -1  
    while left <= right: 
        while (left <= right) and (lista[left] <= pivot): 
            left = left +1
        while (right >= left) and (lista[right] >= pivot): 
            right = right -1 
        if left < right: 
            lista.swap(left, right)
           # lista[left], lista[right] = lista[right], lista[left]
    
    lista.swap(left, high)
   # lista[left], lista[high] = lista[high], lista[left]

    return left

def QuickSort(lista, low, high): 
    while low < high: 
        p = Partition(lista, low, high)
        if p-low < high -p: 
            QuickSort(lista, low, p-1)
            low = p+1
        else:
            QuickSort(lista, p+1, high)
            high = p-1
    return lista