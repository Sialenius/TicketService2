package com.project.jfb.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.jfb.io.entity.BusTicket;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDataReader {

    public static List<BusTicket> readTicketsData() {
        Resource resource = new ClassPathResource("ticketData.txt");
        List<BusTicket> tickets = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try (
                FileReader reader = new FileReader(resource.getFile());
                BufferedReader bufferedReader = new BufferedReader(reader);
        ) {
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                BusTicket ticket = objectMapper.readValue(currentLine, BusTicket.class);

                tickets.add(ticket);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return tickets;

    }
}
