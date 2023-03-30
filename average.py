#This program will take as many numbers as inputted and return the average

#Create an empty list to store the inputed numbers in
numbers = []

#Creating a while loop to take in inputted numbers until user says stop
while True:
    user_input = input(f"Please enter as many numbers as you would like. When you are done, enter 'stop' to stop): ")
    if user_input == "stop":
        break

    #Create a try method in order to account for any incorrect inputs. Ex: inputting "nah" will result in "Invalid input. Please enter a number or 'stop'."
    try:
        number = float(user_input)
        numbers.append(number)
    except ValueError:
        print(f"Invalid input. Please enter a number or 'stop'.")

#Take the length of the list (basically the number of inputs)
if len(numbers) == 0:
    print(f"No numbers entered. Please enter a number.")

#Take the sum of the numbers inputted and divide it by the number of values in the list (number of inputs)
else:
    average = sum(numbers) / len(numbers)
    print(f"The average of the entered numbers is:", average)
