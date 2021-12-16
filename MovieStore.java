package codingProJohnTeam;


import java.util.ArrayList;
import java.util.Iterator;

class Customer{
private String name;
private int age;
private String phone;
private String address;
private ArrayList<Movie> borrowedMovies =new ArrayList<>();;
private int numberOfMoviesBorrowed;



//Creates a new Customer
public Customer(String name, int age, String phone, String address){
	this.name = name;
	this.age = age;
	this.phone = phone;
	this.address = address;
}
public Customer() {
	// TODO Auto-generated constructor stub
}
//Returns the name of a customer
public String getName(){
	return this.name;
}
//Returns the age of a customer
public int getAge(){
	return this.age;
}
//Returns the phone number of a customer
public String getPhone(){
	return this.phone;
}
//Returns the address of a customer
public String getAddress(){
	return this.address;
}
//Returns a list of borrowed movies
public ArrayList<Movie> getBorrowedMovies(){

	return this.borrowedMovies;
	
}
//Returns number of movies borrowed
public int getNumberOfMoviesBorrowed(){
	return this.numberOfMoviesBorrowed;
}


//Adds borrowed movie
public void addBorrowedMovie(Movie movie){
	this.borrowedMovies.add(movie);

}
//Remove returned from a collection of borrowed movies
public void removeReturnedMovie(Movie movie){
	this.borrowedMovies.remove(movie);
}
//Return customer details
public String toString(){
	return "Customer name: "+getName()+". Age: "+getAge()+". Phone: "+getPhone()+". Address: "+getAddress()+
			". Borrowed movies: "+getBorrowedMovies()+". Number of movies borrowed: "+getNumberOfMoviesBorrowed();
}
}

//Class movie
class Movie  {
private String title;
private String director;
private int ageLimit;
private int year;
private int numberOfCopies;




// Creates a new Movie
public Movie(String title, String director, int ageLimit, int year, int numberOfCopies) {
	super();
	this.title = title;
	this.director = director;
	this.ageLimit = ageLimit;
	this.year = year;
	this.numberOfCopies = numberOfCopies;
}

//Returns title of a movie
public String getTitle(){
	return this.title;
}
//Returns the number of copies
public int getNumberOfCopies() {
	return this.numberOfCopies;
}
//Returns the director of a movie
public String director(){
return this.director;
}
//Returns the age limit of a movie
public int getAgeLimit(){
return this.ageLimit;
}
//Returns the year of a movie
public int getYear(){
return this.year;
}
//Returns true or false to indicate whether all copies of the movie are rented or not
public boolean isBorrowed() {
	if (this.numberOfCopies==0) {
		return true;
	}
	else {
		return false;
	}
}
//Adds new copy(ies) of a movie
public void addCopies(int copies){
this.numberOfCopies+=copies;
}
//Removes a copy of a movie
public void removeCopy(){
this.numberOfCopies=numberOfCopies-1;
}
//Return movie details

public String toString(){
	return "Movie title: "+getTitle()+". Age Limit: "+getAgeLimit()+". Director: "+director()+". Year: "+getYear()
+". Number of copies: "+this.numberOfCopies;			}
}

//public class MovieStore
public class MovieStore {
	
	private String storeName;
	private String address;
	private String openingHours;
	private ArrayList<Movie> movieCollection=new ArrayList<Movie>();
	private ArrayList<Customer> listOfCustomers =new ArrayList<>();
	private final int RENTLIMIT = 5;
	
	

//Creates a new MovieStore
public MovieStore(String storeName, String address, String openingHours){
	this.storeName = storeName;
	this.address = address;
	this.openingHours = openingHours;
	
}
//Prints the details of the movie store (name, address, and opening hours)
public void printStoreDetails(){
	System.out.println("**********************************************************************************");
	System.out.println("*Movie store : "+this.storeName+"\n*Address: "+this.address+"\n*Openning Hours: "+this.openingHours+".");
	System.out.println("**********************************************************************************\n");

}
//Adds a new a customer to the list of customers
/*array  
 * adding*/
public void addCustomer(Customer newCustomer){
	listOfCustomers.add(newCustomer);
}
//Adds a new movie to the movie collection
public void addMovie(Movie newMovie){
	movieCollection.add(newMovie);
}
//prints a list of available movies
public void printAvailableMovies(){
	System.out.println("\nThe following movies are available in the movies collection");
	System.out.println("**********************************************************************************");
	int count=0;
	for(Movie movieWatch: movieCollection) {
		count+=1;
		System.out.println(count+"."+movieWatch.getTitle()+" "+movieWatch.director()+" - "+movieWatch.getNumberOfCopies()+" in stock.");
	}
	System.out.println("\n");
}
//prints a list of rented movies
public void printRentedMovies() {
    System.out.println("\nThe following movies have been rented");
    System.out.println("**********************************************************");
    for (int i = 0, j = 0; i < this.movieCollection.size(); i++) {
        if (!(this.movieCollection.get(i).isBorrowed())) {
            System.out.println((j + 1) + ". " + this.movieCollection.get(i).getTitle()+" - "+this.movieCollection.get(i).director()+" - "+this.movieCollection.get(i).getNumberOfCopies()+" in stock.");
            j += 1;
        }
    }

}

	

//prints a list of registered customers
public void printRegisteredCustomers(){
	System.out.println("\nRegistered number of customers");
	System.out.println("**********************************************************************************");
	int count=0;
	for(Customer customer: listOfCustomers) {
		count+=1;
		
		System.out.println(count+"."+customer.getName()+" "+customer.getAddress() +"(Phone: "+customer.getPhone()+")");
	}
	
}
//prints a list of movies currently rented to a certain customer
public void printMoviesRentedToCustomer(String customerName) {
    
    System.out.println("\n" + customerName + " has borrowed the following movies:");
    System.out.println("*********************************************************");
    for (int i = 0; i < this.listOfCustomers.size(); i++) {
        if (this.listOfCustomers.get(i).getName().equals(customerName)) {
            for (int j = 0; j < this.listOfCustomers.get(i).getBorrowedMovies().size(); j++) {
                System.out.println("* " + (j + 1) + ". " + this.listOfCustomers.get(i).getBorrowedMovies().get(j).toString());
            }
        }
    }

}



//Rents a movie to a customer if a movie is available and the customer has not rented more than 5 movies already
//Also, a customer must not be allowed to rent a movie that is outside his/her age range

public void rentMovie(String movieTitle, String customerName) {
    for (int i = 0; i < this.listOfCustomers.size(); i++) {
        if (this.listOfCustomers.get(i).getName().equals(customerName)) {
            if (this.listOfCustomers.get(i).getNumberOfMoviesBorrowed() < this.RENTLIMIT) {
                for (int j = 0; j < this.movieCollection.size(); j++) {
                    if (this.movieCollection.get(j).getTitle().equals(movieTitle)) {
                        if (this.movieCollection.get(j).getAgeLimit() <= this.listOfCustomers.get(i).getAge()) {
                            if (this.movieCollection.get(j).isBorrowed()) {
                                this.listOfCustomers.get(i).addBorrowedMovie(this.movieCollection.get(j));
                                /*this.movieCollection.get(j).removeCopy();*/
                            } else if (!(this.movieCollection.get(j).isBorrowed())) {
                                System.out.println("\n**!!The movie \"" + this.movieCollection.get(j).getTitle() + "\" is out of stock!!**");
                            }
                        } else if (this.movieCollection.get(j).getAgeLimit() > this.listOfCustomers.get(i).getAge()) {
                            System.out.println("\n**!!Customer out of age limit for this movie!!**");
                            System.out.println("\nCustomer is " + this.listOfCustomers.get(i).getAge() + " years old and minimum age limit for this movie is " + this.movieCollection.get(j).getAgeLimit());
                        }
                    }
                }
            } else if (this.listOfCustomers.get(i).getNumberOfMoviesBorrowed() >= this.RENTLIMIT) {
                System.out.println("You already borrowed 5 movies");
            }
        }
    }

}

	
//Returns a rented movie to collection
public void returnMovie(String movieTitle, String customerName) {
    for (int i = 0; i < this.movieCollection.size(); i++) {
        if (this.movieCollection.get(i).getTitle().equals(movieTitle)) {
            this.movieCollection.get(i).addCopies(1);

            for (int j = 0; j < this.listOfCustomers.size(); j++) {
                if (this.listOfCustomers.get(j).getName().equals(customerName)) {
                    this.listOfCustomers.get(j).removeReturnedMovie(this.movieCollection.get(i));
                }
            }
        }
    }
}


public static void main(String[] args){
MovieStore QuickMartStore = new MovieStore("Movie Madness (U) Ltd", "QuickMart, Kisii","Monday-Saturday 10AM – 11PM");
//	Adding sample movies
QuickMartStore.addMovie(new Movie("Sound City", "Dave Grohl", 17, 2013, 2));
QuickMartStore.addMovie(new Movie("The Invisible War", "Kirby Dick", 21, 2012, 4));

QuickMartStore.addMovie(new Movie("Man on Wire", "James Marsh", 18, 2008 , 1));

QuickMartStore.addMovie(new Movie("The Interrupters", "Steve James", 14, 2011, 1));


QuickMartStore.addMovie(new Movie("Waste Land", "Lucy Walker", 20, 2012, 1));


QuickMartStore.addMovie(new Movie("Deliver Us From Evil", " Amy J. Berg", 10, 2006,8));

QuickMartStore.addMovie(new Movie("Taxi to the Dark Side", "Alex Gibney", 14, 2007, 1));
QuickMartStore.addMovie(new Movie("My Voyage to Italy", "Martin Scorsese", 12, 2001,
2));
QuickMartStore.addMovie(new Movie("Last Train Home", "Lixin Fan", 14, 2009, 2));

//Adding sample customers
QuickMartStore.addCustomer(new Customer("Grace", 16, " 07577009", "Kisii"));

QuickMartStore.addCustomer(new Customer("James", 20, " 0789193", " Rongo "));

QuickMartStore.addCustomer(new Customer("Sarah", 27, " 0716367", " Kisumu"));

QuickMartStore.addCustomer(new Customer("Ruth", 19, " 0718384", " Nairobi"));

QuickMartStore.addCustomer(new Customer("Brian", 29, " 0718288", " Mombasa"));

QuickMartStore.addCustomer(new Customer("Robert", 26, "0771833", " Kisii"));

QuickMartStore.addCustomer(new Customer("Joseph", 19, "0708817", " Oyugis"));

QuickMartStore.addCustomer(new Customer("Lisa", 21, " 0798182", " Kisii"));

//Printing store details
QuickMartStore.printStoreDetails();
//Try to print the list of all registered customers
QuickMartStore.printRegisteredCustomers();
//Try to print the list of all movies in the collection
QuickMartStore.printAvailableMovies();
//Try to borrow a movie
QuickMartStore.rentMovie("Last Train Home", "James");
QuickMartStore.rentMovie("The Interrupters", "James");
QuickMartStore.rentMovie("Taxi to the Dark Side",
"Joseph");
//Try print the list of all movies currently rented
QuickMartStore.printRentedMovies();
//Try to borrow a movie that is out of stock
QuickMartStore.rentMovie("Taxi to the Dark Side",
"Grace");
//Try to borrow a movie that is out of your age range
QuickMartStore.rentMovie("Waste Land", "Joseph");
//Try to return a movie
QuickMartStore.returnMovie("The Interrupters", "James");
//Try to print the list of all movies in the collection
QuickMartStore.printAvailableMovies();
//Try to print the list of movies currently rented to a certain customer
QuickMartStore.printMoviesRentedToCustomer("James");
}




}

