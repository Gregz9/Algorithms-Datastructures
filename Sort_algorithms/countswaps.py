

class CountSwaps(list):
    swaps = 0

    def swap(self, i, j):
        self.swaps += 1
        self[i], self[j] = self[j], self[i]
    
    def swap2(self, i, temp): 
        self.swaps += 1
        self[i] = temp

    def swap3(self, other, i, j): 
        self.swaps += 1 
        self[i] = other[j]
    
    def swap4(self, i, j): 
        self.swaps += 1
        self[i] = self[j]
    
    def swap5(self): 
        self.swaps += 1 
       