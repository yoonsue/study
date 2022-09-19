class Duck:
    def sound(self):
        print("꽥꽥")

class Dog:
    def sound(self):
        print("멍멍")

class Rock:
    pass

def get_sound(animal):
    animal.sound()

def main():
    bird = Duck()
    dog = Dog()
    rock = Rock()
    
    get_sound(bird)
    get_sound(dog)
    get_sound(rock)

main()