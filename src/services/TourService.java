package services;

import models.Tour;

import java.util.ArrayList;
import java.util.List;

public class TourService {
    private List<Tour> availableTours = new ArrayList<>();

    public void addTour(Tour tour) {
        availableTours.add(tour);
    }

    public List<Tour> findToursWithinBudget(double budget) {
        List<Tour> matchingTours = new ArrayList<>();
        for (Tour tour : availableTours) {
            if (tour.getPrice() <= budget) {
                matchingTours.add(tour);
            }
        }
        return matchingTours;
    }
}
