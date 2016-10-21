# AI-Assignment-2

Question 1:
Run the GameNim.java

Question 2 (See PDF for alternate form):
Variables: The set of courses C where C = {CSC 110, CSC 115, …. CSC 421, …} such that completion of every course inside C is required to graduate with a degree in computer science.
Domains: The set of domains D such that each domain in D = {term, termSlot, timeslot} defines a course that is offered, in the form {spring2017, 2, 8:30-9:20 TWF}.
Constraints: The set of constraints {C1, C2, C3, C4} such that:
-	C1: Let X and Y be two courses where Y is a prerequisite for X. Suppose that an assignment A has {termX, termSlotX, timeX} for X and {termY, termSlotY, timeY} for Y, then A is consistent if termX > termY, assuming “>” means “later in time”.
-	C2: Let X be a course. Suppose that an assignment A has {termX, termSlotX, timeX} for X. A is consistent if A has termX != null (representing the idea that a course is valid even if it’s not offered every term by saying it only needs to be offered in 1 term)
-	C3: Assignment A has (termX, termSlotX, timeX) for X and (termY, termSlotY, timeY) for Y. Then, A is consistent if (termX, termSlotX) != (termY, termSlotY)
-	C4: Let X and Y be two courses X and Y. Suppose that an assignment A has {termX, termSlotX, timeX} for X and {termY, termSlotY, timeY} for Y, then A is consistent if (termSlotX != termSlotY and timeX != timeY).

Question 3:
Run CSPZebra.java