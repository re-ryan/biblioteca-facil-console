# Biblioteca Fácil Console

Cliente console do projeto Biblioteca Fácil, desenvolvido em Java com Spring Boot.

Este projeto é uma aplicação independente que consome a API HTTP do backend `biblioteca-facil`.

## Relação com o backend

O projeto `biblioteca-facil-console` não faz parte do backend principal.

Ele não acessa diretamente:

- services do backend;
- repositories do backend;
- entidades JPA do backend;
- banco de dados.

A comunicação acontece exclusivamente por HTTP.

```text
biblioteca-facil-console -> HTTP -> biblioteca-facil
```

## Arquitetura atual

```text
biblioteca-facil-console
├── src
│   └── main
│       ├── java
│       │   └── br.com.bibliotecafacil.console
│       │       ├── api
│       │       ├── biblioteca
│       │       ├── usuario
│       │       └── BibliotecaFacilConsoleApplication.java
│       └── resources
│           └── application.properties
├── pom.xml
└── README.md
```

## Responsabilidades

O console é responsável por:

- exibir menus no terminal;
- ler dados digitados pelo usuário;
- validar entradas básicas de tela;
- chamar a API do backend;
- exibir mensagens de sucesso, erro e consulta.

As regras principais da aplicação continuam no backend `biblioteca-facil`.

## Tecnologias

- Java 21
- Spring Boot
- Spring Web
- Maven
- RestClient

## Pré-requisitos

Antes de executar o console, o backend precisa estar rodando.

## Funcionalidades atuais

O console permite acessar os fluxos administrativos iniciais do Biblioteca Fácil.

### Bibliotecas

- listar bibliotecas;
- cadastrar bibliotecas.

### Usuários

- listar usuários;
- cadastrar usuários.

Essas operações são feitas consumindo os endpoints REST do backend.

## Endpoints consumidos

### Bibliotecas

Listar bibliotecas:

```http
GET /api/bibliotecas
```

Cadastrar biblioteca:

```http
POST /api/bibliotecas
```

### Usuários

Listar usuários:

```http
GET /api/usuarios
```

Cadastrar usuário:

```http
POST /api/usuarios
```