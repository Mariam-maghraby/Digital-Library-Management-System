package CMPproject;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome, mr. Admin!");
        Scanner sc=new Scanner(System.in);
        int options = 1;
        int bookcounter=0;
        int membercounter=0;
        book bookarray[]=new book[100];
        member memberarray[]=new member[100];
        while(options!=-1){

            System.out.println("Which one of the following operations would you like to do, please press:");
            System.out.println("1 for book insertion.");
            System.out.println("2 for adding a library member.");
            System.out.println("3 for searching for a book.");
            System.out.println("4 for borrowing a book.");
            System.out.println("5 for retrieving the list of books borrowed by a member.");
            System.out.println("6 for returning a borrowed book.");
            System.out.println("7 for deleting a copy of a book.");
            System.out.println("8 for deleting a library member.");
            System.out.println("9 for searching for a library member.");
            System.out.println("-1 to exit the program.");
            options=check();
            if (options<-1||options>9||options==0) {
                System.out.println("Invalid input, please try again.");
            }
            else{
                if (options==1) {
                    boolean flag=false;
                    int temp=0;
                    System.out.println("Please enter the book title");
                    book insertedbook=new book();
                    insertedbook.title=input();
                    System.out.println("Please enter the author name");
                    insertedbook.author=input();
                    System.out.println("Please enter date of publishing");
                    insertedbook.date=input();
                    System.out.println("Please enter No of copies");
                    insertedbook.copies=check();
                    if(insertedbook.copies>0) {
                        for(int i=0;i<bookcounter;i++) {
                            if (insertedbook.title.equals(bookarray[i].title)){
                                temp=bookarray[i].copies;
                                flag=true;
                                break;
                            }
                        }
                        if(flag) {
                            insertedbook.copies+=temp;
                            System.out.println("New copies of this book has been added now!");
                            System.out.println("The book now has "+insertedbook.copies+" copies available.");
                        }
                        else{
                            bookarray[bookcounter]=insertedbook;
                            System.out.println("The book " + insertedbook.title + " now added!");
                            bookcounter++;
                        }
                    }
                    else {
                        System.out.println("To add a book, number of copies must be 1 or more.");
                    }
                }

                if (options==2) {
                    boolean flag=false;
                    member newmember=new member();
                    System.out.println("Please enter the username.");
                    newmember.username=input();
                    for(int i=0;i<membercounter;i++) {
                        if (newmember.username.equals(memberarray[i].username)){
                            flag=true;
                            break;
                        }
                    }
                    if(flag) {
                        System.out.println("Username already exists, please try again.");
                    }
                    else{
                        if(usernamecheck(newmember.username)){
                            memberarray[membercounter]=newmember;
                            System.out.println("Member of username " + newmember.username + " now added!");
                            membercounter++;
                        }
                    }
                }
                if (options==3) {
                    boolean flag=false;
                    boolean bookexists=false;
                    boolean authorexists=false;
                    if(bookcounter==0) {
                        flag=true;}
                    if(flag) {
                        System.out.println("Library is empty, please add some books first.");
                    }
                    if(!flag) {
                        System.out.println("Press 1 for search by title \nPress 2 for search by author");
                        int choice;
                        String searchtitle;
                        String searchauthor;
                        choice=check();
                        if(choice==1) {
                            System.out.println("Please enter the title you are searching for.");
                            searchtitle=input();
                            for(int i=0;i<bookcounter;i++) {
                                if(searchtitle.equals(bookarray[i].title)){
                                    bookexists=true;
                                    System.out.println("The book "+bookarray[i].title+" by "+bookarray[i].author+ " published in "+bookarray[i].date+ " is avaliable in "+bookarray[i].copies+" copies");
                                }
                            }
                            if (bookexists==false) {
                                System.out.println("Book is not found.");
                            }
                        }

                        else if(choice==2) {
                            System.out.println("Please enter the author you are searching for.");
                            searchauthor=input();
                            for(int i=0;i<bookcounter;i++) {
                                if(searchauthor.equals(bookarray[i].author)){authorexists=true;
                                    System.out.println("The author wrote the following book: "+bookarray[i].title+" avaliable in "+bookarray[i].copies+" copies");
                                }
                            }
                            if(authorexists==false){
                                System.out.println("Author is not found.");
                            }
                        }
                        else{
                            System.out.println("Invalid input, please try again.");
                        }
                    }
                }
                if (options==4) {
                    String borrowedbook;
                    String userborrowing;
                    boolean flag1=false;
                    boolean flag2=false;
                    boolean flag3=true;
                    int temp1=0;
                    int temp2=0;
                    int days;
                    if(bookcounter>0 && membercounter>0){
                        System.out.println("Please enter the username borrowing the book");
                        userborrowing=input();
                        System.out.println("Please enter the title of the book");
                        borrowedbook=input();
                        for(int i=0;i<membercounter;i++) {
                            if(userborrowing.equals(memberarray[i].username)){
                                flag1=true;
                                temp1=i;
                                break;
                            }
                        }
                        if(flag1){
                            for(int j=0;j<bookcounter;j++) {
                                if(borrowedbook.equals(bookarray[j].title)) {
                                    for(int k=0;k<memberarray[temp1].borrowcounter;k++) {
                                        if(borrowedbook.equals(memberarray[temp1].borrowedbooks[k])){
                                            flag3=false;
                                            break;
                                        }
                                    }
                                    flag2=true;
                                    temp2=j;
                                    break;
                                }
                            }
                        }
                        if(flag1 && flag2 && flag3) {
                            System.out.println("Please enter the number of days of borrowing in digits. Max days is <14 days.");
                            days=check();
                            if (days<14) {
                                if(bookarray[temp2].copies>1) {
                                    bookarray[temp2].copies--;
                                    memberarray[temp1].borrowedbooks[memberarray[temp1].borrowcounter]=bookarray[temp2].title;
                                    memberarray[temp1].borrowcounter++;
                                    System.out.println("Book borrowed succesfully.");
                                }
                                else if(bookarray[temp2].copies==1) {
                                    memberarray[temp1].borrowedbooks[memberarray[temp1].borrowcounter]=bookarray[temp2].title;
                                    memberarray[temp1].borrowcounter++;
                                    System.out.println("Book borrowed successfully.");
                                    bookarray[temp2].copies=0;
                                }
                                else {System.out.println("The book is not available for borrowing. (Zero copies available)");

                                }

                            }

                            else {System.out.println("Books can only be borrowed for less than 14 days, please try again.");

                            }
                        }
                        else if(!flag1 || !flag2){
                            System.out.println("Username and/or book not found, please try again.");
                        }
                        if(!flag3){
                            System.out.println("User already borrowed this book, a user can only borrow one copy of a book at a time.");
                        }
                    }
                    else {
                        System.out.println("No users and/or books, add some then try again.");
                    }
                }

                if(options==5){
                    String userborrowing;
                    boolean flag=false;
                    int temp=0;
                    if(bookcounter>0 && membercounter>0){
                        System.out.println("Please enter the username.");
                        userborrowing=input();
                        for(int i=0;i<membercounter;i++) {
                            if(userborrowing.equals(memberarray[i].username)){
                                flag=true;
                                temp=i;
                                break;
                            }
                        }
                        if (flag) {
                            System.out.println("The user has borrowed the following books: ");
                            for(int i=0;i<memberarray[temp].borrowcounter;i++) {
                                if(memberarray[temp].borrowedbooks[i]!=""){
                                    System.out.println(memberarray[temp].borrowedbooks[i]);
                                }
                            }
                        }
                        else {
                            System.out.println("Username doesn't exist, please try again.");
                        }
                    }
                    else{
                        System.out.println("No users and/or books, add some then try again.");
                    }



                }
                if (options == 6) {
                    String userreturning;
                    String bookreturn;
                    boolean userflag=false;
                    boolean borrowflag=true;
                    int temp1=0;
                    int temp2=0;
                    int temp3=0;
                    if (bookcounter > 0 && membercounter > 0) {
                        System.out.println("Please enter the username of the user that has borrowed the book.");
                        userreturning = input();
                        for (int i = 0; i < membercounter; i++) {
                            if (userreturning.equals(memberarray[i].username)) {
                                userflag = true;
                                temp1 = i;
                                break;
                            }
                        }
                        if(userflag){
                            System.out.println("Which book to be returned? (Enter the title.) The user borrowed the following book(s): ");
                            for (int i = 0; i < memberarray[temp1].borrowcounter; i++) {
                                if(memberarray[temp1].borrowedbooks[i]!=""){
                                    System.out.println(memberarray[temp1].borrowedbooks[i]);
                                }
                            }
                            bookreturn= input();
                            for(int j=0; j<memberarray[temp1].borrowcounter;j++) {
                                if (bookreturn.equals(memberarray[temp1].borrowedbooks[j])) {
                                    borrowflag=false;
                                    temp2=j;
                                    break;

                                }
                            }
                            if(!borrowflag) {
                                for(int i=0;i<bookcounter;i++) {
                                    if (bookreturn.equals(bookarray[i].title)) {
                                        temp3=i;
                                        break;
                                    }
                                }
                                memberarray[temp1].borrowedbooks[temp2]="";
                                bookarray[temp3].copies++;


                                System.out.println("Book has been returned.");

                            }
                            else {
                                System.out.println("This user hasn't borrowed this book.");

                            }

                        }
                        else{
                            System.out.println("User doesn't exist.");
                        }

                    }
                    else {
                        System.out.println("No users and/or books, please add some then try again.");
                    }

                }
                if(options==7) {
                    String deletebook;
                    boolean flag=false;
                    int temp=0;
                    System.out.print("Please enter the name of the book you want to delete a copy of.");
                    deletebook=input();
                    for(int i=0;i<bookcounter;i++){
                        if(deletebook.equals(bookarray[i].title)) {
                            flag=true;
                            temp=i;
                            break;
                        }
                    }
                    if(flag){

                        if(bookarray[temp].copies>0){
                            bookarray[temp].copies--;
                            System.out.println("1 copy deleted. Book now has "+bookarray[temp].copies+" copies available.");
                        }
                        else {
                            System.out.println("Book already has no copies in the library.");
                        }

                    }
                    else {
                        System.out.println("Book doesn't exist in the library. You can only delete a copy from available books.");
                    }
                }
                if(options==8){

                    String deleteuser;
                    boolean flag=false;
                    int temp=0;
                    if(membercounter!=0){
                        System.out.print("Please enter the username of the user you want to delete.");
                        deleteuser=input();
                        for(int i=0;i<membercounter;i++){
                            if(deleteuser.equals(memberarray[i].username)) {
                                flag=true;
                                temp=i;
                                break;
                            }
                        }
                        if(flag){
                            for(int i=0;i<memberarray[temp].borrowcounter;i++) {
                                memberarray[temp].borrowedbooks[i]="";
                            }
                            memberarray[temp].username="";
                            memberarray[temp].borrowcounter=0;
                            System.out.println("Member deleted.");
                        }
                        else {
                            System.out.println("Member doesn't exist. You can only delete members that are already in the library.");
                        }
                    }
                    else {
                        System.out.println("Library has no members. Please add some then try again.");
                    }
                }
                if (options==9) {
                    boolean flag=false;
                    boolean userexists=false;
                    String searchuser;
                    int temp=0;
                    if(membercounter==0) {
                        flag=true;}
                    if(flag) {
                        System.out.println("Library is empty, please add users first.");
                    }
                    if(!flag) {
                        System.out.println("Please enter the username of the member you are searching for.");
                        searchuser=input();
                        for(int i=0;i<membercounter;i++){
                            if(searchuser.equals(memberarray[i].username)){
                                userexists=true;
                                temp=i;
                                break;
                            }
                        }
                        if(userexists){
                            System.out.println("User "+searchuser+" exists.");
                            System.out.println("This user has borrowed the following books: ");
                            for(int i=0;i<memberarray[temp].borrowcounter;i++) {
                                if(memberarray[temp].borrowedbooks[i]!=""){
                                    System.out.println(memberarray[temp].borrowedbooks[i]);
                                }
                            }


                        }
                        else{
                            System.out.println("The user you have searched for is not registered in the library.");
                        }
                    }
                }
            }
        }
    }
    private static int check(){
        Scanner sc=new Scanner(System.in);
        int option=0;
        String optionstr= sc.nextLine();
        boolean isNum=true;
        if(optionstr.isEmpty()){
            System.out.println("Only numbers are allowed for this option.");
            return check();
        }
        for(int i=0; i<optionstr.length();i++){
            char c=optionstr.charAt(i);
            if(!(c>47&&c<58||c==45)) {
                isNum=false;
                break;
            }
        }
        if(isNum){
            option=Integer.parseInt(optionstr);
        }
        else {
            System.out.println("Only numbers are allowed for this option.");
            return check();
        }
        return option;
    }
    private static String input(){
        Scanner sc=new Scanner(System.in);
        String input= sc.nextLine().trim();
        if(input.isEmpty()){
            System.out.println("A blank input is not allowed.");
            return input();
        }
        return input;

    }
    private static boolean usernamecheck(String username){
        boolean check=true;
        if(username.length()>30|username.length()<5){
            check=false;
            System.out.println("Please choose a username 5–30 characters long.");
        }
        else{
            for(int i=0;i<username.length();i++){
                char c=username.charAt(i);
                if( (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c>='0' && c<='9')
                        || c=='.' || c=='_') {
                    continue;
                }
                else {check=false;
                    System.out.println("A username should only contain alphanumeric characters, periods (.) and underscore (_).");
                    break;
                }
            }
        }

        return check;


    }
}