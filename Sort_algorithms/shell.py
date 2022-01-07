

def sort(A): 

    ShellSort(A)

    return A 

def ShellSort( lista): 
    dist = len(lista) // 2
    while dist > 0: 
        for i in range(dist, len(lista)): 
            temp = lista[i]
            j = i 
            while (j >= dist) and (lista[j-dist] > temp): 
                lista.swap4(j, j- dist)
                #lista[j] = lista[j-dist]
                j = j - dist
            lista.swap2(j, temp)
           # lista[j] = temp
        dist = dist//2
    return lista  