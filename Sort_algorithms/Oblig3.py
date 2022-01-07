import math 
import io
from typing import List
import random
import sys 

def Insertion_Sort(List): 
    for i in range(1, len(List)): 
        j = i
        while (j > 0) and (List[j-1] > List[j]): 
            temp_element = List[j-1]
            List[j-1] = List[j]
            List[j] = temp_element 
            j -= 1 
    return List

def ChoosePivot(lista, low, high):
 
    """left = lista[low]; 
    right = lista[high]
    mid = lista[(low+high)//2]
    

    if(left <= mid <= right) or (left >= mid >= right): 
        pivot = mid
    elif(mid <= left <= right) or (mid >= left >= right): 
        pivot = low 
    elif(left <= right <= mid) or (left >= right >= mid): 
        pivot = high"""
    list_copy = lista[low:high]
    
    pivot = random.choice(list_copy)
    print(pivot)

    return pivot

def Partition(array, low, high): 
    """ p = ChoosePivot(lista, low, high)
    print(p)
    pivot = lista[high]
    left = low 
    right = high -1  
    while left <= right: 
        while (left <= right) and (lista[left] <= pivot): 
            left = left +1
        while (right >= left) and (lista[right] >= pivot): 
            right = right -1 
        if left < right: 
            lista[left], lista[right] = lista[right], lista[left]
    
    lista[left], lista[high] = lista[high], lista[left]"""
 
    r = ChoosePivot(array, low, high)
    #array.swap(p, high)
    #array[r], array[high] = array[high], array[r]
    pivot = array[high]
    left = low  
    right = high  -1

    while left <= right: 
        while left <= right and array[left] <= pivot: 
            left += 1
        while right >= left and array[right] >= pivot: 
            right -= 1
        if left < right: 
            array[left], array[right] = array[right], array[left]
           # array.swap(left, right)
    
  #  array.swap(left, high)
    array[left], array[high] = array[high], array[left]
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

def merge(lista, lista1, lista2): 
    i = 0
    j = 0 
    k = 0

    while (i < len(lista1)) and (j < len(lista2)): 
        if lista1[i] < lista2[j]: 
            lista[k] = lista1[i]
            i += 1 
        else: 
            lista[k] = lista2[j]
            j += 1 
        k += 1
    
    while i < len(lista1):
        lista[k] = lista1[i]
        i += 1 
        k += 1
    
    while j < len(lista2): 
        lista[k] = lista2[j]
        j += 1
        k += 1 
    
    return lista


def MergeSort(lista): 
    
    if len(lista) > 1: 
        mid = len(lista) //2
        lista1 = lista[:mid] 
        lista2 = lista[mid:]
        MergeSort(lista1)
        MergeSort(lista2)
        merge(lista, lista1, lista2)
    return lista

def BubbleSort(lista): 
    for i in range(0, len(lista)-1): 
        for j in range(0, len(lista)-i-1):
            if lista[j] > lista[j +1]: 
                temp = lista[j]
                lista[j] = lista[j+1]
                lista[j+1] = temp
    return lista

def ShellSort( lista): 
    dist = len(lista) // 2
    while dist > 0: 
        for i in range(dist, len(lista)): 
            temp = lista[i]
            j = i 
            while (j >= dist) and (lista[j-dist] > temp): 
                lista[j] = lista[j-dist]
                j = j - dist
            lista[j] = temp
        dist = dist//2
    return lista     


def BubbleDown(array, index, num): 
    largest = index
    left = 2*index + 1
    right = 2*index + 2

    if (left < num) and (array[largest] < array[left]): 
        largest, left = left, largest

    if (right < num) and (array[largest] < array[right]): 
        largest, right = right, largest

    if (index != largest): 
        array[index], array[largest] = array[largest], array[index]
        BubbleDown(array, largest, num)
    

def BuildMaxHeap(array, num): 
    for i in range(num//2, -1, -1): 
        BubbleDown(array, i, num)

def HeapSort(array): 
    BuildMaxHeap(array, len(array))
    for i in range(len(array)-1, -1, -1): 
        array[0], array[i] = array[i], array[0]
        BubbleDown(array, 0, i)
    return array

    # The student can adjust these parameters to conduct their experiments

if __name__ == '__main__':
 
    sort = [4,2,5,7,3,9,4,13,32,12,61,64,91,8, 12, 17, 22,1,6]

    low = 4
    high = 10
    print(sort[low:high])
    sorted = QuickSort(sort, 0, len(sort)-1)

    print(sorted)
        



        

    