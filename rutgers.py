import random

#List of all names being used
full_names = ["Alex Johnson", "Samantha Lee", "Juan Rodriguez", "Aaliyah Patel", "Daniel Kim", "Fatima Ali", "Adam Smith"]

#Make an empty list for the emails
rutgers_emails = []

#Create method for creating the email
for name in full_names:
    split_name = name.split(" ")
    first_initial = split_name[0][0]
    last_initial = split_name[1][0]
    random_num = random.randint(100, 999)
    email = first_initial + last_initial + str(random_num) + "@rutgers.edu"
    rutgers_emails.append(email)

#Printing rutgers emails
print(rutgers_emails)
