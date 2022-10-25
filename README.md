# SortedLinkedList minimal solution (doubly linked)

### Thoughts:
- As the description wasn't specific about the app (whether it should be jar or something else) - I decided to use gradle (even tho it might be a little overkill).
- The input/output wasn't specified, so I kept the example arrays in the code instead of making it usable from main function arguments as I find it more approachable and testable than to change the arguments every time. Also I simply print the result to std output.
- I wasn't quite sure whether it should be sorted right away, but as stated in comments in the code it seemed to me that SLL should be sorted at any time (thus right after addition), hope it wasn't a wrong assumption. That affected the implementation.
- I decided to use doubly linked list, but it wasn't necessary for the sake of this task
- I added some thoughts in the comments near the implementation, so please read that as well

## How to run
In order to run the app simply run `gradle build run` or import into your favourite IDE and run `main` function from `Main.kt` file