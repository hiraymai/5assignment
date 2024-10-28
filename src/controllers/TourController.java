package controllers;

import factories.TourFactory;
import models.Tour;
import services.TourService;
import services.PaymentService;
import payment.HalkBankPayment;
import payment.KaspiGoldPayment;
import payment.PaypalPayment;

import java.util.List;
import java.util.Scanner;

public class TourController {
    private TourService tourService = new TourService();

    public void initializeTours() {
        tourService.addTour(TourFactory.createTour("adventure", "Mountain Hike", 300, "Almaty"));
        tourService.addTour(TourFactory.createTour("luxury", "City Tour", 700, "Astana"));
        tourService.addTour(TourFactory.createTour("adventure", "Desert Safari", 400, "Shymkent"));
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Tour Agency!");
        System.out.print("Choose tour type (Adventure/Luxury): ");
        String tourType = scanner.nextLine();

        System.out.print("Enter your budget: ");
        double budget = scanner.nextDouble();
        scanner.nextLine();

        List<Tour> tours = tourService.findToursWithinBudget(budget);
        if (tours.isEmpty()) {
            System.out.println("No tours available within your budget.");
            return;
        }

        System.out.println("Available tours:");
        for (int i = 0; i < tours.size(); i++) {
            System.out.println((i + 1) + ". " + tours.get(i).getName() + " - " + tours.get(i).getCity() + " - $" + tours.get(i).getPrice());
        }

        System.out.print("Choose a tour by number: ");
        int tourChoice = scanner.nextInt();
        scanner.nextLine();
        Tour selectedTour = tours.get(tourChoice - 1);

        System.out.print("Choose payment method (HalkBank/KaspiGold/PayPal): ");
        String paymentMethod = scanner.nextLine();
        PaymentService paymentService;
        switch (paymentMethod.toLowerCase()) {
            case "halkbank":
                paymentService = new PaymentService(new HalkBankPayment());
                break;
            case "kaspigold":
                paymentService = new PaymentService(new KaspiGoldPayment());
                break;
            case "paypal":
                paymentService = new PaymentService(new PaypalPayment());
                break;
            default:
                System.out.println("Invalid payment method.");
                return;
        }

        boolean success = paymentService.processPayment(selectedTour.getPrice());
        if (success) {
            System.out.println("Your tour is successfully booked. Enjoy your trip!");
        } else {
            System.out.println("Sorry, something went wrong. Please try again.");
        }
    }
}
