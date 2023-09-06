
import requests

API_KEY = "ttww4G3WvCOAvryfa7di0ebavOHfbwmU"
base_url_messi = "https://api.nytimes.com/svc/search/v2/articlesearch.json?q=Messi&api-key=ttww4G3WvCOAvryfa7di0ebavOHfbwmU"
base_url_ronaldo = "https://api.nytimes.com/svc/search/v2/articlesearch.json?q=Ronaldo&api-key=ttww4G3WvCOAvryfa7di0ebavOHfbwmU"

clicks_ronaldo = 0
clicks_messi = 0


# Set the query parameter to search for articles related to Messi since 2010
parameters = {
    'api-key': API_KEY,
    'q': 'Messi',
    'begin_date': '20100101',
    'end_date': '20230101'  
}


# Send a GET request to the API and extract the response
response = requests.get(base_url_messi, params=parameters)
content = response.json()


# Count the number of articles that mention Messi
for article in content["response"]["docs"]:
    headline_and_snippet = article["headline"]["main"] + " " + article["snippet"]
    if "messi" in headline_and_snippet.lower():
        clicks_messi += 1


# Set the query parameter to search for articles related to Ronaldo
parameters = {
    'api-key': API_KEY,
    'q': 'Ronaldo',
    'begin_date': '20100101',
    'end_date': '20230101'  
}


# Send a GET request to the API and extract the response
response = requests.get(base_url_ronaldo, params=parameters)
content = response.json()

# Count the number of articles that mention Ronaldo
for article in content["response"]["docs"]:
    headline_and_snippet = article["headline"]["main"] + " " + article["snippet"]
    if "ronaldo" in headline_and_snippet.lower():
        clicks_ronaldo += 1


# Compare the number of hits Ronaldo and Messi get on the New York Times to make sure code is actually working
print("Number of hits for Messi:", clicks_messi)
print("Number of hits for Ronaldo:", clicks_ronaldo)


with open('messi_ronaldo.txt', 'w') as f:
    f.write("The more popular soccer player ")
    if clicks_ronaldo > clicks_messi:
        f.write("is Cristiano Ronaldo.")
    elif clicks_ronaldo < clicks_messi:
        f.write("is Lionel Messi.")
    else:
        f.write("is neither. Cristiano Ronaldo and Lionel Messi are equal.")


