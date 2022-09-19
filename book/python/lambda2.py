
from dataclasses import dataclass

@dataclass
class Person:
    age: int
    name: str

people = []
personA = Person(age=1, name='A')
personB = Person(age=3, name='B')
personC = Person(age=2, name='C')
people.append(personA)
people.append(personB)
people.append(personC)

print(people)
print(sorted(people, key=lambda person: person.age))
