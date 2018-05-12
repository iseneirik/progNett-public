## The algorithm

At first i set the max shift distance to the length of the needle, after that 
I iterate through the needle and set shift distance to each character, for 
each wildcard character i find, i set the max shift distance to that index.
Then the loop starts, it compares the needle with the text at index,
if a match is found, print it out, then continue looping, increasing
index by the amount specified in badCharacterShift

## Compilation

javac *.java

## Run

java Oblig3 <needle-filename> <haystack-filename>

## assumptions

The search is case sensitive, since nothing else is specified
in the task, however, a quick fix to setBadCharShift() and matchesPattern()
can make the search case insensitive

## Other

A few shortcuts where taken, eg. main() throws Exception. This is because
I had some fun seeing how short i could make the code and still have it
readable and understandable. This might also affect the effectiveness of
the code (although i believe it is quite ok).