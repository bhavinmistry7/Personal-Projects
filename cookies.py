# Define the list of ingredients
ingredients = ("flour", "butter", "sugar", "eggs", "chocolate chips")

# Create an empty list to store user inputs
user_ingredients = []

# Ask the user to enter ingredients until they enter "stop"
while True:
    user_input = input("Please enter your ingredients. Once you are done, enter 'stop'")
    if user_input.lower() == "stop":
        break
    else:
        user_ingredients.append(user_input.lower())

# Check if the user has all the necessary ingredients
if set(ingredients).issubset(set(user_ingredients)):
    print("You are ready to start making cookies!")
else:
    print("You are still missing some ingredients.")
