# This program calculates the taxi fare based on distance traveled

def fare_calculator(distance_km):
    """This function calculates the total taxi fare based on the distance traveled"""
    base_fare = 4.00
    fare_per_meter = 0.25 / 140
    total_fare = base_fare + distance_km * 1000 * fare_per_meter
    return total_fare

distance = float(input("Enter the distance traveled in kilometers: "))
fare = fare_calculator(distance)
print(f"The total fare for your trip is ${fare:.2f}")
