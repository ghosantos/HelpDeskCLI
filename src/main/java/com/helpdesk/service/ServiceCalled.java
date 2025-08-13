package com.helpdesk.service;

import com.helpdesk.model.Called;
import com.helpdesk.model.Client;
import com.helpdesk.model.Technical;
import com.helpdesk.model.User;
import com.helpdesk.model.enums.AssignCallResult;
import com.helpdesk.model.enums.StatusCalled;
import com.helpdesk.model.exceptions.DomainException;

import java.util.List;
import java.util.stream.Collectors;

public class ServiceCalled {

    private final List<User> listUsers;
    private final List<Called> listCalleds;

    public ServiceCalled(List<User> listUsers, List<Called> listCalleds) {
        this.listCalleds = listCalleds;
        this.listUsers = listUsers;
    }

    public void addClient(Client client) {
        if (isValidName(client.getName())) {
            throw new DomainException("Nome inválido! Digite apenas letras e espaços.");
        }
        if (isValidEmail(client.getEmail())) {
            throw new DomainException("E-mail inválido! Verifique o formato.");
        }
        listUsers.add(client);
    }

    public void addTechnical(Technical technical) {
        if (isValidName(technical.getName())) {
            throw new DomainException("Nome inválido! Digite apenas letras e espaços.");
        }
        if (isValidEmail(technical.getEmail())) {
            throw new DomainException("E-mail inválido! Verifique o formato.");
        }
        listUsers.add(technical);
    }


    public void addCalled(Called called) {
        listCalleds.add(called);
    }

    public AssignCallResult assignCall(long id, Technical technical) {
        Called called = getCallById(id);

        if (called == null) {
            return AssignCallResult.CALL_NOT_FOUND;
        }
        if (called.getTechnical() != null) {
            return AssignCallResult.CALL_ALREADY_ASSIGNED;
        }
        if (technical.getCountCall() >= 5) {
            return AssignCallResult.TECHNICIAN_AT_LIMIT;
        }

        called.assignTechnician(technical);
        return AssignCallResult.SUCCESS;
    }

    public boolean closeCall(long id) {
        Called called = getCallById(id);

        if (called == null) {
            throw new IllegalArgumentException("Chamado não encontrado para o ID informado.\n");
        }
        if (called.getTechnical() == null){
            throw new IllegalArgumentException("Chamado não possuí técnico atríbuido");
        }
        if (called.isClosed()){
            throw new IllegalArgumentException("O chamado já está fechado.");
        }

        called.closeTicket();
        return true;
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

    public List<Called> getProgressCalls() {
        return listCalleds.stream().filter(c -> c.getStatus().equals(StatusCalled.PROGRESS)).collect(Collectors.toList());
    }

    public List<Called> closedCalls() {
        return listCalleds.stream().filter(c -> c.getStatus().equals(StatusCalled.CLOSED)).collect(Collectors.toList());
    }

    public Called getCallById(long id) {
        return listCalleds.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    public boolean isValidName(String nome) {
        return nome == null || !nome.matches("^[\\p{L} ]+$");
    }

    public boolean isValidEmail(String email) {
        return email == null || !email.matches("^[\\w.%+-]+@[\\w.-]+\\.[A-Za-z]{2,6}$");
    }

//    private List<Called> filterByTechnician(Technical technical){
//        return listCalleds.stream().filter(c -> technical.equals(c.getTechnical())).collect(Collectors.toList());
//    }


}