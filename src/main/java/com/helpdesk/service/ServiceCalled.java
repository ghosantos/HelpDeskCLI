package com.helpdesk.service;

import com.helpdesk.model.Called;
import com.helpdesk.model.Client;
import com.helpdesk.model.Technical;
import com.helpdesk.model.User;
import com.helpdesk.model.enums.AssignCallResult;
import com.helpdesk.model.enums.StatusCalled;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceCalled {
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private final List<User> listUsers;
    private final List<Called> listCalleds;

    public ServiceCalled(List<User> listUsers, List<Called> listCalleds) {
        this.listCalleds = listCalleds;
        this.listUsers = listUsers;
    }

    public void addClient(Client client){
        listUsers.add(client);
    }

    public void addTechnical(Technical technical){
        listUsers.add(technical);
    }

    public void addCalled(Called called){
        listCalleds.add(called);
    }

    public AssignCallResult assignCall(long id, Technical technical) {
        Called called = getCallById(id);

        if (called == null){
            return AssignCallResult.CALL_NOT_FOUND;
        }
        if (called.getTechnical() != null) {
            return AssignCallResult.CALL_ALREADY_ASSIGNED;
        }
        if (technical.getCountCall() >= 5){
            return AssignCallResult.TECHNICIAN_AT_LIMIT;
        }else {
            called.assignTechnician(technical);
            return AssignCallResult.SUCCESS;
        }
    }

    public boolean closeCall(long id){
        Called called = getCallById(id);

        if (called != null && called.getTechnical() != null){
            called.closeTicket();
            return true;
        }else {
           return false;
        }
    }

    public List<Called> getOpenAndProgressCalls() {
        return listCalleds.stream()
                .filter(c -> c.getStatus().equals(StatusCalled.OPEN) || c.getStatus().equals(StatusCalled.PROGRESS))
                .collect(Collectors.toList());
    }

    public List<Called> getOpenCalls() {
        return listCalleds.stream()
                .filter(c -> c.getStatus().equals(StatusCalled.OPEN))
                .collect(Collectors.toList());
    }

    public List<Called> getProgressCalls(){
        return listCalleds.stream().filter(c -> c.getStatus().equals(StatusCalled.PROGRESS)).collect(Collectors.toList());
    }

    public List<Called> closedCalls (){
        return listCalleds.stream().filter(c -> c.getStatus().equals(StatusCalled.CLOSED)).collect(Collectors.toList());
    }

    public Called getCallById (long id){
        return listCalleds.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    private List<Called> filterByTechnician(Technical technical){
        return listCalleds.stream().filter(c -> technical.equals(c.getTechnical())).collect(Collectors.toList());
    }


}