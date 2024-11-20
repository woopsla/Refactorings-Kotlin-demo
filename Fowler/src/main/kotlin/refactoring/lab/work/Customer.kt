package refactoring.lab.work

import java.time.LocalDate

class Customer(
    val name: String,
    val dateOfBirth: String
) {
    private val rentals = mutableListOf<Rental>()
    private val returnedRentals = mutableListOf<Rental>()

    val rentedVideos: List<Video> get() = rentals.map { it.video }

    fun rentVideo(video: Video, rentedOn: LocalDate = LocalDate.now()) {
        rentals.add(Rental(video, rentedOn))
    }

    fun returnVideo(title: String, returnedOn: LocalDate = LocalDate.now()) {
        rentals.find { it.video.title == title }?.also {
            it.returnVideo(returnedOn)
            returnedRentals.add(it)
            rentals.remove(it)
        }
    }

    fun statement(): String {
        var totalAmount = 0.0;
        var frequentRenterPoints = 0;
        var result = "Rental Record for $name\n";
        for (each in returnedRentals) {
            var thisAmount = 0.0;

            //determine amounts for each line
            when (each.video.priceCode) {
                Video.REGULAR -> {
                    thisAmount += 2;
                    if (each.daysRented > 2)
                        thisAmount += (each.daysRented - 2) * 1.5;
                }

                Video.NEW_RELEASE -> {
                    thisAmount += each.daysRented * 3;
                }

                Video.CHILDREN -> {
                    thisAmount += 1.5;
                    if (each.daysRented > 3)
                        thisAmount += (each.daysRented - 3) * 1.5;
                }
            }

            // add frequent renter points
            frequentRenterPoints++;

            // add bonus for a two day new release rental
            if ((each.video.priceCode == Video.NEW_RELEASE) && each.daysRented > 1)
                frequentRenterPoints++;

            //show figures for this rental
            result += "\t${each.video.title}\t$thisAmount\n";
            totalAmount += thisAmount;
        }

        //add footer lines
        result += "Amount owed is $totalAmount\n";
        result += "You earned $frequentRenterPoints frequent renter points";
        return result;
    }

//    fun htmlStatement(): String {
//        var result = "<H1>Rentals for <EM>$name</EM></H1><P>\n";
//
//        for (rental in returnedRentals) {
//            //show figures for each rental
//            result += "${rental.video.title}: ${rental.getCharge()}<BR>\n";
//        }
//
//        //add footer lines
//        result += "<P>You owe <EM>${getTotalCharge()}</EM><P>\n";
//        result += "On this rental you earned <EM>${getTotalPoints()}</EM> frequent renter points<P>";
//        return result;
//    }
}
