__author__ = 'Phil'

import enchant
import sys

# Prompts the user to enter the name of the text file and then saves it.
try:
    file_name = sys.argv[1]
except IndexError:
    print("No argument given, usage: 'check_review.py [filepath]'")
    exit(0)

# Count of total words in file.
word_count = 0

# Try to open file, quit if you cannot.
try:
    file = open(file_name, 'r')
    open_flag = 1
except IOError:
    print("File not found!")
    exit(0)

# Initialize an empty dictionary to store words used in the file.
word_bank = {}

# For each line of text in the file do the following:
# First, split it by white-space, storing it into an array.
# Second, for each word stored in the array, attempt to increment it count in the dictionary 'word_bank'.
# Last, if the word does not yet exist in the word bank, initialize it with count 1.
for line in file:
    line_list = line.split()
    for word in line_list:
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

print("Total Words: %d" % word_count)
print("Correct, English words: %d" % correct_words)
print("Incorrect words: %d" % incorrect_words)
print("All Caps words: %d" % all_caps_words)
