package com.helpdesk.entities;

import com.helpdesk.enums.Priority;
import com.helpdesk.enums.StatusCalled;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Called {
    private static int nextId = 1;
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private Integer id;

    private Client client;
    private Technical technical;

    //Informações necessárias para abertura do chamado
    private String description;
    private StatusCalled statusCalled;
    private Priority priority;

    private LocalDateTime openingDate;
    private LocalDateTime closedDate; //Pode ser nulo

    public Called(){}

    public Called(Client client, String description, Priority priority) {
        this.id = nextId++;
        this.client = client;
        this.description = description;
        this.priority = priority;
        this.statusCalled = StatusCalled.OPEN;
        this.openingDate = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public String getDescription() {
        return description;
    }

    public Priority getPriority() {
        return priority;
    }

    public StatusCalled getStatus() {
        return statusCalled;
    }

    public LocalDateTime getOpeningDate() {
        return openingDate;
    }

    public LocalDateTime getClosedDate() {
        return closedDate;
    }

    //Métodos

    public void assignTechnician(Technical technical) {
        this.technical = technical;
        this.statusCalled = StatusCalled.PROGRESS;
    }

    public void closeTicket(){
        this.closedDate = LocalDateTime.now();
        this.statusCalled = StatusCalled.CLOSED;
    }

    public String toString() {
        return String.format("#%d | %s | Prioridade: %s | Status: %s", id, description, priority, statusCalled);
    }
}
