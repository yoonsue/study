import math

def circle_area(radius):
    return math.pi * radius ** 2

circle_area_lambda = lambda radius: math.pi * radius ** 2

print(circle_area(22))
print(circle_area.__class__)
print(circle_area.__name__)

print(circle_area_lambda(22))
print(circle_area_lambda.__class__)
print(circle_area_lambda.__name__)