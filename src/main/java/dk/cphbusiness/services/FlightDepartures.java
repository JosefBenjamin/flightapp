package dk.cphbusiness.services;

import dk.cphbusiness.flightdemo.FlightReader;
import dk.cphbusiness.flightdemo.dtos.FlightDTO;
import dk.cphbusiness.flightdemo.dtos.FlightInfoDTO;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class FlightDepartures {

    private static final FlightReader reader = new FlightReader();
    private static final String FLIGHTS_LIST = "flights.json";



    public  void fromAToB (String destinationA, String destinationB) {

       try {
           List<FlightDTO> flights = reader.getFlightsFromFile(FLIGHTS_LIST);
           List<FlightInfoDTO> airTravelInformation = reader.getFlightInfoDetails(flights);

           //TODO: Add a new feature (make a list of flights that are operated between two specific airports.
           // For example, all flights between Fukuoka and Haneda Airport)

           // List<FlightInfoDTO> flightDepartures =
                   airTravelInformation.stream()
                           .filter((x) -> {
                             return x.getDestination() != null;
                           })
                           .filter((x) -> {
                               return x.getDestination().equalsIgnoreCase(destinationA) || x.getDestination().equalsIgnoreCase(destinationB);
                           }).forEach((x) -> {
                               System.out.println(
                                    "Airline:" +  x.getAirline() + " \n" +
                                     "Departure:" + x.getDeparture() + " \n" +
                                     "Duration:" + x.getDuration());
                           });

       } catch (IOException e) {
           e.printStackTrace();
       }


    }

    public  void departureTime (LocalDateTime localDateTime) {

        try {
            List<FlightDTO> flights = reader.getFlightsFromFile(FLIGHTS_LIST);
            List<FlightInfoDTO> airTravelInformation = reader.getFlightInfoDetails(flights);

            //TODO: Add a new feature (make a list of flights that leaves before a specific time in the day/night.
            // For example, all flights that leave before 01:00)

            airTravelInformation.stream()
                    .filter((x) -> {
                        return x.getDeparture() != null;
                    })
                    .filter((x) -> {
                        return x.getDeparture().isAfter(localDateTime);
                    }).forEach((x) -> {
                        System.out.println(
                               "<----------------------> \n" + "Flights that depart before: \n" +  x.getAirline() + " \n" +
                                        "Departure:" + x.getDeparture() + " \n" +
                                        "Duration:" + x.getDuration());
                    });

        } catch (IOException e) {
            e.printStackTrace();
        }


    }




}
