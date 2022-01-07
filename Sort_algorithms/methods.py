
from countcompares import CountCompares
from countswaps import CountSwaps
import sys 

def ChoosePivot(array, low, high):
    pivot = array[(high+low)//2]

    return pivot

def Partition(array, low, high): 
    p = ChoosePivot(array, low, high)
    array.swap(p, high)
    pivot = array[high]
    left = low  
    right = high +1 

    while left <= right: 
        while left <= right and array[left] <= pivot: 
            left += 1
        while right >= left and array[right] >= pivot: 
            right -= 1
        if left < right: 
            array.swap(left, right)
    
    array.swap(left, high)
    return left


