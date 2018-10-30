package com.techverito.bookmycinema;

import com.techverito.bookmycinema.model.Audi;
import com.techverito.bookmycinema.model.Revenue;
import com.techverito.bookmycinema.util.ApplicationConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

/*Main java class, please execute this class in order to execute the project*/
@SpringBootApplication
public class BookmycinemaApplication {

	public static void main(String[] args) {
		Revenue revenue = new Revenue();

		//SpringApplication.run(BookmycinemaApplication.class, args);
		//Declare 3 Audis/screens
		Set<String> setList1 = new HashSet<>();
		Set<String> setList2 = new HashSet<>();
		Set<String> setList3 = new HashSet<>();
		Audi audi1 = new Audi(1, "Audi 1", "show 1", setList1);
		Audi audi2 = new Audi(2, "Audi 2", "show 2", setList2);
		Audi audi3 = new Audi(3, "Audi 3", "show 3", setList3);

		Map<Integer, Audi> integerAudiMap = new HashMap<>();
		integerAudiMap.put(audi1.getId(), audi1);
		integerAudiMap.put(audi2.getId(), audi2);
		integerAudiMap.put(audi3.getId(), audi3);

		List<Audi> audis = new ArrayList<>();
		audis.add(audi1);
		audis.add(audi2);
		audis.add(audi3);
		printScreenList(audis);

		String inputChar = "Y";

		while(inputChar.equals("Y")) {
			System.out.println("Input:");
			System.out.println("Enter Show Number:");
			Scanner sc = new Scanner(System.in);
			// String input
			String showNumber = sc.nextLine();
			Integer intShowNum = Integer.valueOf(showNumber);

			//check for the seat availability
			boolean isValidAudi = checkAudi(intShowNum, integerAudiMap);

			if(!isValidAudi) {
				System.out.println("Audi is invalid, Do you want to continue? Y/N");
				inputChar = sc.nextLine();
				if(inputChar.equals("Y")){
					continue;
				} else {
					break;
				}
			}

			System.out.println("Enter Seats:");
			String seats = sc.nextLine();
			String[] seatArray = seats.split(",");
			List<String> stringList = Arrays.asList(seatArray);

			//check fo rthe seats
			boolean validSeats = isSeatValid(stringList);
			if(!validSeats) {
				System.out.println("Seat/s are invalid, Do you want to continue? Y/N");
				inputChar = sc.nextLine();
				if(inputChar.equals("Y")){
					continue;
				} else {
					break;
				}
			}

			boolean isSeatAvailable = checkSeats(intShowNum, integerAudiMap, stringList);
			if(!isSeatAvailable) {
				System.out.println("Seat/s are invalid, Do you want to continue? Y/N");
				inputChar = sc.nextLine();
				if(inputChar.equals("Y")){
					continue;
				} else {
					break;
				}
			}

			//If everything is okay then book the seats and print calculations
			bookTicketAndCalculate(intShowNum, stringList, integerAudiMap, revenue);
			System.out.println("Do you want to print TOTAL SALES? Y/N");
			String saleChar = sc.nextLine();
			if(saleChar.equals("Y")){
				printTotalSale(revenue);
			}
		}
	}

	public static void printScreenList(List<Audi> audis){
		audis.forEach(audi -> {
			System.out.println(audi.getShowName() + " is Running in " + audi.getAudiName() + ":");
			System.out.println("All Seats");
			System.out.println(ApplicationConstants.platiNumSeats.toString());
			System.out.println(ApplicationConstants.goldSeats.toString());
			System.out.println(ApplicationConstants.silverSeats.toString());
		});
	}

	public static boolean checkAudi(Integer intShowNum, Map<Integer, Audi> integerAudiMap){
		Audi audi = integerAudiMap.get(intShowNum);
		if(Objects.isNull(audi)) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean checkSeats(Integer intShowNum, Map<Integer, Audi> integerAudiMap, List<String> stringList){
		Audi audi = integerAudiMap.get(intShowNum);
	    Set<String> selectedSet = audi.getSelectedSeatsList();
		boolean seatAvailableFlag = true;
		//null or empty check ie initial stage
	    if(Objects.isNull(selectedSet) || selectedSet.isEmpty()) {
	    	return true;
		}
		for (String seat : stringList) {
			if(selectedSet.contains(seat)) {
				System.out.println(seat + " is Not available, Please select different seats");
				seatAvailableFlag = false;
			}
		}
		return seatAvailableFlag;
	}

	public static boolean isSeatValid(List<String> stringList) {
		for (String s : stringList) {
			if(!ApplicationConstants.totalSeats.contains(s))
				return false;
		}
		return true;
	}

	public static void bookTicketAndCalculate(Integer intShowNum, List<String> strings, Map<Integer, Audi> integerAudiMap, Revenue revenue) {
		double totalAmount = 0;
		double serviceTax = 0;
		double swachhBharatCess = 0;
		double krishiKalyanCess = 0;
		Audi audi = integerAudiMap.get(intShowNum);
		Set<String> selectedSeatSet = audi.getSelectedSeatsList();

		for (String s : strings) {
			selectedSeatSet.add(s);
			if(ApplicationConstants.platiNumSeats.contains(s)){
				totalAmount = totalAmount + ApplicationConstants.platinumTicketCost;
			}else if(ApplicationConstants.goldSeats.contains(s)){
				totalAmount = totalAmount + ApplicationConstants.goldTicketCost;
			}else if(ApplicationConstants.silverSeats.contains(s)){
				totalAmount = totalAmount + ApplicationConstants.silverTicketCost;
			}
		}

		serviceTax = (totalAmount * ApplicationConstants.serviceTax) / 100;
		swachhBharatCess = (totalAmount * ApplicationConstants.swachhBharatCess) / 100;
		krishiKalyanCess = (totalAmount * ApplicationConstants.krishiKalyanCess) / 100;

		System.out.println(" Successfully Booked - Show " +intShowNum);
		System.out.println("Sub Total:" + totalAmount);
		revenue.setTotalRevenue(revenue.getTotalRevenue() + totalAmount);
		totalAmount = serviceTax + swachhBharatCess + krishiKalyanCess;
		System.out.println("Service Tax @" + ApplicationConstants.serviceTax + ": Rs." + serviceTax );
		System.out.println("Swachh Bharat Ces @" + ApplicationConstants.swachhBharatCess + ": Rs." + swachhBharatCess );
		System.out.println("Krishi Kalyan Ces @" + ApplicationConstants.krishiKalyanCess + ": Rs." + krishiKalyanCess );
		revenue.setServiceTax(revenue.getServiceTax() + serviceTax);
		revenue.setSwachhBharatCess(revenue.getSwachhBharatCess() + swachhBharatCess);
		revenue.setKrishiKalyanCess(revenue.getKrishiKalyanCess() + krishiKalyanCess);
	}

	public static void printTotalSale(Revenue revenue) {
		System.out.println("Total Sales:");
		System.out.println("Revenue: Rs. " + revenue.getTotalRevenue());
		System.out.println("Service Tax: Rs. " + revenue.getServiceTax());
		System.out.println("Swachh Bharat Cess: Rs. " + revenue.getSwachhBharatCess());
		System.out.println("Krishi Kalyan Cess: Rs. " + revenue.getKrishiKalyanCess());
	}
}
