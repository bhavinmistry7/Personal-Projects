import random

# Creating a list of 100 random integers between 0 and 9
random_hundred = [random.randint(0, 9) for _ in range(100)]

# Creating a dictionary object to store the count of the number of times each of the numbers between 0 and 9 appeared in the list
count_dictionary = {num: random_hundred.count(num) for num in range(10)}

# Printing the dictionary information using an f-string to the user
print(f"Count dictionary: {count_dictionary}")

# Creating a set object called "unique_set" that contains non-duplicate random numbers from the list
unique_set = set(random_hundred)

# Printing to the user the length of unique_set to confirm that it is 10
print(f"Length of unique_set: {len(unique_set)}")

