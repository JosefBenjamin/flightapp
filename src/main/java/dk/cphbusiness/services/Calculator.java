package dk.cphbusiness.services;

import dk.cphbusiness.flightdemo.FlightReader;
import dk.cphbusiness.flightdemo.dtos.FlightDTO;
import dk.cphbusiness.flightdemo.dtos.FlightInfoDTO;
import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.channels.FileLockInterruptionException;
import java.util.List;

public class Calculator {

    private static final String FLIGHTS_LIST = "flights.json";
    public static final FlightReader reader = new FlightReader();


    //TODO: Add a new feature (e.g. calculate the total flight time for a specifc airline.
    // For example, calculate the total flight time for all flights operated by Lufthansa)


    public static void totalFlightTimeAirline(String airline) {
            try {
                List<FlightDTO> flights = reader.getFlightsFromFile(FLIGHTS_LIST);
                List<FlightInfoDTO> infoFlights = reader.getFlightInfoDetails(flights);

                // Sum the total minutes as a 'long' to avoid losing precision
                long totalMinutes = infoFlights.stream()
                        .filter((x) -> {
                           return x.getAirline() != null && x.getAirline().equalsIgnoreCase(airline);
                        }).mapToLong((x) -> {
                            return x.getDuration().toMinutes();
                        }).sum();

                if (totalMinutes == 0) {
                    System.out.println("No flights found for airline: " + airline);
                    return;
                }

                // Convert total minutes to hours and remaining minutes for a nice output
                long hours = totalMinutes / 60;
                long minutes = totalMinutes % 60;

                System.out.println("Total flight time for " + airline + ": " + hours + " hours and " + minutes + " minutes.");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }







    //TODO: Add a new feature (e.g. calculate the average flight time for a specific airline.
    // For example, calculate the average flight time for all flights operated by Lufthansa)

    public static void averageFlightTimeAirline(String airline)  {


        try {
            List<FlightDTO> flights = reader.getFlightsFromFile(FLIGHTS_LIST);
            List<FlightInfoDTO> allInfo = reader.getFlightInfoDetails(flights);

            double averageFlightTime = allInfo.stream()
                    .filter((x) -> {
                        return (x.getAirline() != null) && (x.getAirline().equalsIgnoreCase(airline));
                    }).mapToLong( (x) -> {
                        return  x.getDuration().toMinutes();
                    }).average().orElse(0);

            if(averageFlightTime == 0) {
                System.out.println("Something went wrong with finding an airline!");
            }

            double hours = averageFlightTime / 60;
            double minutes = averageFlightTime % 60;



            System.out.println("Airline: " + airline + " Hours: " + String.format("%.2f",hours) + " Minutes: " + String.format("%.2f",minutes));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
