__author__ = 'Phil'

import enchant

# Prompts the user to enter the name of the text file and then saves it.
file_name = input("Please enter the name of the text file: ")

# Flag used to ensure file is opened
open_flag = 0

# Loop through trying to open the file and re-ask user for input if it does not work.
while not open_flag:
    try:
        file = open(file_name, 'r')
        open_flag = 1
    except IOError:
        file_name = input("File does not exist! Please re-enter filename: ")

# Initialize an empty dictionary to store words used in the file.
word_bank = {}

# For each line of text in the file do the following:
# First, split it by white-space, storing it into an array.
# Second, for each word stored in the array, attempt to increment it count in the dictionary 'word_bank'.
# Last, if the word does not yet exist in the word bank, initialize it with count 1.
for line in file:
    line_list = line.split()
    for word in line_list:
        try:
            word_bank[word] += 1
        except KeyError:
            word_bank[word] = 1

correct_words = 0
incorrect_words = 0

d = enchant.Dict("en_US")

# For every word in the word bank, check to see if it is an English word.
for key in word_bank:
    if d.check(key):
        correct_words += 1
    else:
        incorrect_words += 1

print("Correct, English words: %d" % correct_words)
print("Incorrect words: %d" % incorrect_words)


