import sympy as sp

# Defining the variables
x, y = sp.symbols('x y')

# Defining the function
f = 4*x**2 + x*y + 4*y**2

# Calculating the gradient (partial derivatives)
df_dx = sp.diff(f, x)
df_dy = sp.diff(f, y)

# Finding the critical points where both partial derivatives are zero
critical_points = sp.solve([df_dx, df_dy], (x, y), dict=True)

#Printing of critical points in a for loop
print("Critical Points:")
for point in critical_points:
    x_val = point[x]
    y_val = point[y]
    print(f"x = {x_val}, y = {y_val}")
    

