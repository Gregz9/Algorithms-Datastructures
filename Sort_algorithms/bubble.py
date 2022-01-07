

def sort(A): 

    BubbleSort(A)

    return A 

def BubbleSort(lista): 
    for i in range(0, len(lista)-1): 
        for j in range(0, len(lista)-i-1):
            if lista[j] > lista[j +1]: 
                lista.swap(j, j+1)
                """  temp = lista[j]
                lista[j] = lista[j+1]
                lista[j+1] = temp"""
    return lista