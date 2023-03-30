# This program uses the requests package to make an HTTP GET request to the page, and then uses BeautifulSoup to parse the HTML content of the response.
# In this instance, It demonstrates three examples of how to extract information from the HTML content:

# 1) Extracting the page title
# 2) Extracting all the links on the page
# 3) Extracting all the headings on the page

# Importing required packages
import requests
from bs4 import BeautifulSoup

# Defining the URL to extract information from
url = 'https://en.wikipedia.org/wiki/Real_Madrid_CF'

# Making a GET request to the URL and storing the response in a variable
response = requests.get(url)

# Parsing the HTML content of the response using BeautifulSoup
soup = BeautifulSoup(response.content, 'html.parser')

# Example 1: Extracting the page title
title = soup.title.string
print(f'Page Title: {title}')

# Example 2: Extracting all the links on the page
links = soup.find_all('a')
print(f'Number of Links: {len(links)}')
for link in links:
    href = link.get('href')
    if href and href.startswith('http'):
        print(f'{link.text.strip()}: {href}')

# Example 3: Extracting all the headings on the page
headings = soup.find_all(['h1', 'h2', 'h3', 'h4', 'h5', 'h6'])
print(f'Number of Headings: {len(headings)}')
for heading in headings:
    print(f'{heading.name}: {heading.text.strip()}')

# Storing information about the page 
page_info = {
    'Title': title,
    'Number of Links': len(links),
    'Number of Headings': len(headings)
}

# Using a loop to display the page information
for key, value in page_info.items():
    print(f'{key}: {value}')
    
# Using a conditional statement to check if the page has a table of contents
if soup.find(id='toc'):
    print('This page has a Table of Contents')
else:
    print('This page does not have a Table of Contents')
