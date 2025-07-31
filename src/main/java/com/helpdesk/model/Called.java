package com.helpdesk.model;

import com.helpdesk.model.enums.Priority;
import com.helpdesk.model.enums.StatusCalled;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Called {
    private static int nextId = 1;
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private Integer id;

    private Client client;
    private Technical technical;

    //Information required to open a ticket
    private String description;
    private StatusCalled statusCalled;
    private Priority priority;

    private LocalDateTime openingDate;
    private LocalDateTime closedDate; //Starts at zero until a call is closed


    //Constructors
    public Called(){}

    public Called(Client client, String description, Priority priority) {
        this.id = nextId++;
        this.client = client;
        this.description = description;
        this.priority = priority;
        this.statusCalled = StatusCalled.OPEN;
        this.openingDate = LocalDateTime.now();
    }

    //Methods
    public void assignTechnician(Technical technical) {
        this.technical = technical;
        this.statusCalled = StatusCalled.PROGRESS;
        this.technical.incrementCallCount();
    }

    public void closeTicket(){
        this.closedDate = LocalDateTime.now();
        this.statusCalled = StatusCalled.CLOSED;
        this.technical.decrementCallCount();
    }

    //Getters e Setters
    public int getId() {
        return id;
    }
    public Client getClient() {
        return client;
    }
    public Technical getTechnical(){return technical;}
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

    public String toString() {
        String nameTechnical = (technical != null) ? technical.getName() : "Não atribuído";
        return String.format("#%d | %s | Prioridade: %s | Status: %s | Técnico: %s ", id, description, priority, statusCalled, nameTechnical);
    }
}
