# Access Gate API

## 📋 Descrição

API de controle de acesso para gerenciamento de moradores, visitantes e usuários em condomínios e portarias. Sistema desenvolvido seguindo os princípios da **Arquitetura Hexagonal (Ports and Adapters)**, garantindo baixo acoplamento, alta coesão e facilidade de manutenção.

---

## 🏗️ Arquitetura Hexagonal

Este projeto foi estruturado seguindo a **Arquitetura Hexagonal** (também conhecida como Ports and Adapters), que organiza o código em camadas bem definidas:

### 📦 Estrutura de Camadas

```
access-gate-api/
│
├── 🎯 core/                          # Núcleo da aplicação (Domain + Business Logic)
│   ├── domain/                       # Entidades de domínio (POJO puros)
│   │   ├── Morador.java
│   │   ├── Visitante.java
│   │   ├── Usuario.java
│   │   ├── Pessoa.java
│   │   └── Visita.java
│   │
│   ├── ports/                        # Interfaces (contratos)
│   │   ├── MoradorServicePort.java
│   │   ├── MoradorRepositoryPort.java
│   │   ├── UsuarioServicePort.java
│   │   ├── UsuarioRepositoryPort.java
│   │   ├── VisitanteServicePort.java
│   │   └── VisitanteRepositoryPort.java
│   │
│   ├── service/                      # Implementação das regras de negócio
│   │   ├── MoradorService.java
│   │   ├── UsuarioService.java
│   │   └── VisitanteService.java
│   │
│   └── exceptions/                   # Exceções de negócio
│       └── BusinessException.java
│
├── 🔌 adapter/                       # Adaptadores (Infraestrutura)
│   ├── controller/                   # Adaptadores de entrada (REST)
│   │   ├── MoradorController.java
│   │   ├── UsuarioController.java
│   │   └── VisitanteController.java
│   │
│   ├── dto/                          # Objetos de transferência
│   │   ├── MoradorDto.java
│   │   ├── UsuarioDto.java
│   │   └── VisitanteDto.java
│   │
│   ├── entities/                     # Entidades JPA (persistência)
│   │   ├── MoradorEntity.java
│   │   ├── UsuarioEntity.java
│   │   ├── VisitanteEntity.java
│   │   └── PessoaEntity.java
│   │
│   ├── repository/                   # Adaptadores de saída (persistência)
│   │   ├── MoradorRepository.java
│   │   ├── MoradorRepositoryAdapter.java
│   │   ├── UsuarioRepository.java
│   │   ├── UsuarioRepositoryAdapter.java
│   │   ├── VisitanteRepository.java
│   │   ├── VisitanteRepositoryAdapter.java
│   │   ├── PessoaRepository.java
│   │   └── PessoaRepositoryAdapter.java
│   │
│   └── converters/                   # Conversores entre camadas
│       ├── MoradorConverter.java
│       ├── UsuarioConverter.java
│       └── VisitanteConverter.java
│
└── 🔧 infra/                         # Configurações de infraestrutura
    ├── BeansConfig.java
    └── handlers/
        ├── ApiError.java
        └── ApplicationResourceAdvice.java
```

### 🎯 Princípios Aplicados

- **Inversão de Dependência**: O núcleo não depende de nada externo
- **Separação de Responsabilidades**: Cada camada tem sua função bem definida
- **Testabilidade**: Facilita testes unitários do core sem dependências externas
- **Independência de Framework**: O domínio não conhece Spring, JPA ou qualquer framework
- **Substituibilidade**: Adapters podem ser substituídos sem afetar o core

---

## 🚀 Tecnologias Utilizadas

### Framework e Linguagem
- **Java 17** - Linguagem de programação
- **Spring Boot 4.0.2** - Framework principal
- **Spring Web MVC** - Camada web RESTful
- **Spring Data JPA** - Persistência de dados

### Banco de Dados
- **H2 Database** (em memória) - Banco de dados para desenvolvimento e testes

### Ferramentas e Bibliotecas
- **Lombok** - Redução de boilerplate
- **ModelMapper 3.1.1** - Mapeamento de objetos
- **Maven** - Gerenciamento de dependências e build

---

## 📚 Endpoints da API

### 🏠 Moradores

#### Criar Morador
```http
POST /api/moradores
Content-Type: application/json

{
  "nome": "João da Silva",
  "cpf": "12345678901",
  "endereco": "Apto 101",
  "celular": "11987654321"
}
```

#### Listar Todos os Moradores
```http
GET /api/moradores
```

---

### 👥 Usuários

#### Criar Usuário
```http
POST /api/usuarios
Content-Type: application/json

{
  "nome": "Admin Silva",
  "email": "admin@example.com",
  "senha": "senha123",
  "administrador": true
}
```

---

### 👤 Visitantes

#### Criar Visitante
```http
POST /api/visitantes
Content-Type: application/json

{
  "nome": "Maria Santos",
  "rg": "123456789"
}
```

#### Listar Todos os Visitantes
```http
GET /api/visitantes
```

#### Buscar Visitante por RG
```http
GET /api/visitantes/{rg}
```

---

## ⚙️ Configuração e Execução

### Pré-requisitos
- Java 17 ou superior
- Maven 3.6+

### Executar a Aplicação

#### Windows
```bash
mvnw.cmd spring-boot:run
```

#### Linux/Mac
```bash
./mvnw spring-boot:run
```

### Acessar o Console H2
```
URL: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb
Username: admin
Password: admin
```

---

## 🧪 Testes

### Executar Testes
```bash
mvnw test
```

---

## 📊 Modelo de Dados

### Entidades Principais

**Morador**
- id (Long)
- nome (String)
- cpf (String)
- endereco (String)
- celular (String)

**Visitante**
- id (Long)
- nome (String)
- rg (String)

**Usuario**
- id (Long)
- nome (String)
- email (String)
- senha (String)
- administrador (Boolean)

**Pessoa** (Entidade base compartilhada)
- id (Long)
- nome (String)

---

## 🛠️ Build e Deploy

### Gerar JAR
```bash
mvnw clean package
```

### Executar JAR
```bash
java -jar target/access-gate-api-0.0.1-SNAPSHOT.jar
```

---

## 📝 Padrões de Projeto Utilizados

- **Hexagonal Architecture / Ports and Adapters**
- **Dependency Injection** (via Spring)
- **Repository Pattern**
- **DTO Pattern**
- **Service Layer Pattern**
- **Converter Pattern**
- **Exception Handler** (Global exception handling)

---

## 👨‍💻 Autor

**Hugo França da Silva Dia**
- GitHub: [@devfranca](https://github.com/devfranca)

---

## 📄 Licença

Este projeto é de código aberto e está disponível para fins educacionais e de portfólio.
