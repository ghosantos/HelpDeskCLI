# HelpDesk System 📞🛠️

Este projeto simula um sistema de chamados de suporte técnico, desenvolvido em **Java puro**, com foco em **orientação a objetos** e boas práticas.

## ✅ Funcionalidades já implementadas

- [x] Cadastro e listagem de chamados
- [x] Atribuição de chamados a técnicos
- [x] Validação para impedir que um técnico assuma mais de 5 chamados simultâneos
- [x] Fechamento de chamados com registro da data de encerramento
- [x] Contador de chamados por técnico (incremento e decremento automático)
- [x] Filtro de chamados por status (abertos, em andamento, fechados)
- [x] Filtro de chamados por técnico responsável
- [x] Validação para impedir que chamados sejam finalizados sem técnico atribuído

## 🧠 Conceitos e práticas utilizadas

- Encapsulamento e orientação a objetos
- Responsabilidade única (SRP) aplicada em parte das classes
- Uso de enums para status dos chamados e perfis de usuários
- Validação de regras de negócio
- Uso de listas e `Stream API` para filtros e buscas
- Separação em camadas: model, service, view
- Menu interativo via console (CLI)
- Enum para prioridade dos chamados

## 📁 Estrutura atual do projeto (Java puro)

```
com.helpdesk
├── model/
│   ├── enums/
│   │   ├── Priority.java
│   │   ├── StatusCalled.java
│   │   └── TypeUser.java
|       └── AssignCallResult.java
│   ├── User.java      (abstract class)
│   ├── Client.java
│   ├── Technical.java
│   └── Called.java
├── service/
│   └── ServiceCalled.java  (menu and main logic)
└── view/
    ├── Menu.java
    ├── ClientMenu.java
    └── TechnicalMenu.java
├── Main.java
```

## 💡 Exemplos de uso

### Cliente

```text
--- MENU CLIENTE ---
1 - Abrir novo chamado
2 - Ver chamados em andamento/abertos
3 - Chamados finalizados
0 - Sair
```

### Técnico

```text
--- MENU TÉCNICO ---
1 - Ver chamados abertos
2 - Atribuir-se a um chamado
3 - Meus chamados em andamento
4 - Buscar chamado por ID
5 - Fechar um chamado
0 - Sair
```

## 🧭 Próximos passos e melhorias

- [ ] Migração para Spring Boot
- [ ] Criar endpoints REST (API) para operações
- [ ] Implementar autenticação/autorização com Spring Security
- [ ] Persistência dos chamados e técnicos com Spring Data JPA e banco relacional (PostgresSQL)
- [ ] Implementar DTOs para comunicação entre camadas
- [ ] Criar testes unitários e de integração

## ✅ Requisitos

- Java 17 ou superior
- IDE compatível com projetos Java

## ✍️ Autor

- Gustavo Oliveira — [LinkedIn](https://www.linkedin.com/in/gustavo-oliveira-1477922b3/)

---

> Projeto de aprendizado com Java: orientação a objetos, listas, enums, scanner, controle de fluxo, estruturação em camadas e boas práticas!
