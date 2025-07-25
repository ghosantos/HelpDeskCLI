# HelpDesk System 📞🛠️

Sistema simples de HelpDesk em Java, com foco no fluxo de abertura e gerenciamento de chamados por clientes e técnicos.

## 📋 Funcionalidades

### 👤 Cliente
- Cadastro de cliente (nome e e-mail)
- **Abrir novo chamado**, definindo descrição e prioridade (baixa, média, alta)
- **Visualizar chamados abertos ou em andamento**
- **Visualizar chamados finalizados (encerrados)**

### 🧑‍💻 Técnico
- Cadastro de técnico (nome e e-mail)
- **Visualizar todos os chamados abertos**
- **Atribuir-se a um chamado** por ID (muda o status para PROGRESS)
- **Visualizar seus chamados em andamento**
- **Buscar chamado por ID**
- **Finalizar chamado** (muda o status para CLOSED)

## 🧱 Estrutura do Projeto

```
com.helpdesk
├── entities
│   ├── User.java (classe abstrata)
│   ├── Client.java
│   ├── Technical.java
│   └── Called.java
│
├── enums
│   ├── Priority.java (LOW, MEDIUM, HIGH)
│   └── StatusCalled.java (OPEN, PROGRESS, CLOSED)
│
├── service
│   └── ServiceCalled.java (menu e lógica principal)
```

## ▶️ Como executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/helpdesk-system.git
   ```
2. Abra em sua IDE Java favorita (Eclipse, IntelliJ, VS Code, etc).
3. Crie uma classe `Main` com `main(String[] args)` e chame:
   ```java
   Scanner sc = new Scanner(System.in);
   ServiceCalled service = new ServiceCalled();
   service.clientMenu(sc); // ou service.technicalMenu(sc);
   ```
4. Execute o projeto.

## 💡 Exemplos de uso

```text
--- MENU CLIENTE ---
1 - Abrir novo chamado
2 - Ver chamados em andamento/abertos
3 - Chamados finalizados
0 - Sair
```

```text
--- MENU TÉCNICO ---
1 - Ver chamados abertos
2 - Atribuir-se a um chamado
3 - Meus chamados em andamento
4 - Buscar chamado por ID
5 - Fechar um chamado
0 - Sair
```

## ✅ Requisitos
- Java 17 ou superior
- IDE compatível com projetos Java

## 🔮 Melhorias Futuras

- Implementar persistência de login (lembrar usuário entre sessões)
- Armazenar dados do usuário em arquivos `.txt` ou `.json`
- Utilizar banco de dados para armazenar chamados e usuários
- Criar sistema de autenticação com senha
- Adicionar interface gráfica (GUI) com JavaFX ou Swing
- Implementar filtros por data, status e cliente no histórico de chamados
- Gerar relatórios de chamados em PDF ou CSV

## ✍️ Autor

- Gustavo — [LinkedIn](https://www.linkedin.com/in/gustavo-oliveira-1477922b3/)

---

> Este projeto é voltado ao aprendizado de Java com orientação a objetos, uso de listas, enums, scanner, controle de fluxo e estruturação em camadas simples.

