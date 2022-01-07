
 
from functools import total_ordering
import sys
#import resource



@total_ordering
class CountCompares:
    def __init__(self, elem):
        self.elem = elem
        self.compares = 0

    def reset(self):
        self.compares = 0

    def __eq__(self, other):
        return self.elem == other

    def __lt__(self, other):
        self.compares += 1
        return self.elem < other

    def __repr__(self):
        return self.elem.__repr__()