__author__ = 'Phil'

import enchant
import sys
import string

# Prompts the user to enter the name of the text file and then saves it.
try:
    file_name = sys.argv[1]
except IndexError:
    print("No argument given, usage: 'check_review.py [filepath]'")
    exit(0)

# Count of total words in file.
word_count = 0

# List of words that would indicate a real one.
good_words = ["very", "great", "service", "staff", "lobby"]

# Try to open file, quit if you cannot.
try:
    file = open(file_name, 'r')
    open_flag = 1
except IOError:
    print("File not found!")
    exit(0)

# Initialize an empty dictionary to store words used in the file.
word_bank = {}

# List to contain length of all the words.
word_lengths = []

# For each line of text in the file do the following:
# First, split it by white-space, storing it into an array.
# Second, for each word stored in the array, attempt to increment it count in the dictionary 'word_bank'.
# Last, if the word does not yet exist in the word bank, initialize it with count 1.
for line in file:
    line_list = line.split()
    for word in line_list:
        word = word.translate(str.maketrans('', '', string.punctuation))
        word_lengths.append(len(word))
        word_count += 1
        try:
            word_bank[word] += 1
        except KeyError:
            word_bank[word] = 1

correct_words = 0
incorrect_words = 0
all_caps_words = 0

d = enchant.Dict("en_US")

# For every word in the word bank, check to see if it is an English word.
for key in word_bank:
    if d.check(key):
        correct_words += 1
    else:
        incorrect_words += 1
    # Check if the word is ALL-CAPS
    if key.isupper():
        all_caps_words += 1

misspelled_percent = incorrect_words/word_count
all_caps_percent = all_caps_words/word_count

# Calculate average word length.
length_sum = 0
for length in word_lengths:
    length_sum += length
length_average = length_sum/word_count

good_words_total = 0

for key in word_bank:
    if key.lower() in good_words:
        good_words_total += word_bank[key]
good_words_percent = good_words_total/word_count

print(misspelled_percent)
print(all_caps_percent)
print(word_count)
print(length_average)
print(good_words_percent)

with open('connection.txt', 'w') as out:
    out.write(str(misspelled_percent) + ' ' + str(all_caps_percent) + ' ' + str(word_count) + ' ' + str(length_average) + ' ' + str(good_words_percent))

open('completed.txt', 'w')