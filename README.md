# cmp1242-2020-final-assignment-Mariam-maghraby
cmp1242-2020-final-assignment-Mariam-maghraby created by GitHub Classroom

Team Members:
Mariam Ashraf Fathi Ali , Seat No:51080 , Section:2 , BN:26
Laila Hamdy abdelrauof , Seat No:51062 , Section:2 , BN:8
Ghada Ashraf Gomaa , Seat No:51060 , Secttion:2 , BN:6

Description of the program of the library mangement system:
We have made a program of a library for an admin to manage it, the admin can:
1. Add books (By adding the book title, the name of the author, the date of publishing and the number of copies he is adding to the library).
2. Add library members by assigning them usernames (A username of a library member can only be 5-30 characters long, and can only contain alphanumeric characters, periods and underscore. Spaces and other special characters are not allowed).
3. Searching for a book either by book title or by author. 
4. Allow library members to borrow a copy of a book, members can borrow a copy of multiple books at a time each for <14 days. (Borrowing a book decreases the number of available copies of the book by 1 until it’s returned. A member can borrow only 1 copy of the same book at a time, if a book has zero copies in the library the book is not available for borrowing).
5. Retrieve a list of the books borrowed by any library member.
6. Return the books borrowed by any member. (Returning the books removes it from the list of borrowed books of the member and increases the number of its available copies in the library by 1).
7. Delete a copy of a book.
8. Deleting a library member. (Deleting a member deletes all his data along with him).
9. Searching for the existence of a certain library member and retrieving a list of his borrowed books.
We have managed to put some restrictions, like not allowing entering a blank input or entering a letter/special character when asked for a number, etc.

Using the program is simple, a list of numbered options are shown to the user, he can choose any of the operations (from 1-9) or press -1 to exit the program. Whenever the admin has chosen an option (1-9) and completed the steps of it, the list of operations will appear again until he presses -1 to exit.
