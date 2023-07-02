import numpy as np 
a = np.arange(1,17).reshape(4,4)
b = np.arange(1,17).reshape(4,4)
c = np.dot(a,b)
print(a)
print(b)
print(c)