# Desafio Spring Boot - Votação Cooperativa

## Objetivo

No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias por meio de votação. Este projeto consiste no desenvolvimento de uma API REST para gerenciar e participar dessas sessões de votação. A solução será executada na nuvem e deve fornecer as seguintes funcionalidades:

- Cadastrar uma nova pauta
- Abrir uma sessão de votação em uma pauta, permitindo definir um tempo específico ou utilizar o tempo padrão de 1 minuto
- Receber votos dos associados, sendo as opções apenas "Sim" ou "Não"
- Garantir que cada associado vote apenas uma vez por pauta
- Contabilizar os votos e exibir o resultado da votação

## Funcionalidades

- Cadastrar Associados
- Associar um associado como votante de uma pauta
- Criar pautas a serem votadas
- Listar todas as pautas cadastradas
- Atualizar informações de uma pauta
- Buscar uma pauta específica
- Excluir uma pauta
- Registrar votos em uma pauta aberta
- Exibir o resultado final da votação de uma pauta

## Tecnologias Utilizadas

| Uso                         | Tecnologia        |
| --------------------------- | ----------------- |
| Linguagem de Programação    | Java 17           |
| Banco de Dados              | PostgreSQL        |
| Framework                   | Spring Boot       |
| Interface para consultar BD | DBeaver           |
| Documentação                | Swagger           |
| Testes Unitários            | JUnit 5 e Mockito |

## Como Rodar o Projeto

Antes de iniciar a aplicação, é necessário ter instalado e configurado o Banco de Dados PostgreSQL. Como alternativa, pode-se utilizar o Docker para criar um container com o banco de dados.

### Configuração do Banco de Dados

O `application.properties` está configurado da seguinte forma:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/votacaodb
spring.datasource.username=postgres
spring.datasource.password=root
```

Caso prefira, crie um banco de dados com nome, usuário e senha personalizados e altere essas configurações no arquivo `application.properties`.

### Utilizando Docker para o Banco de Dados

O repositório contém um arquivo `docker-compose.yml` para facilitar a criação do banco de dados PostgreSQL em um container. Para iniciar o banco utilizando Docker, execute:

```sh
docker-compose up -d
```

Isso criará e inicializará um container com o PostgreSQL configurado corretamente.

### Executando a Aplicação

Para rodar a aplicação, utilize os seguintes comandos:

```sh
# Clonar o repositório
git clone https://github.com/jean-cassio/votacao
cd votacao

# Compilar o projeto
mvn clean install

# Executar a aplicação
mvn spring-boot:run
```

A API será iniciada na porta **8080**.

## Testes

Para executar os testes unitários, utilize o comando:

```sh
mvn test
```

## Documentação da API

A documentação da API está disponível via Swagger nos seguintes endpoints:

- [Swagger UI](http://localhost:8080/swagger-ui/index.html)
- [OpenAPI JSON](http://localhost:8080/v3/api-docs)
