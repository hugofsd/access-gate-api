# 🔄 Fluxo de Funcionamento - Access Gate API

## 📖 Visão Geral do Sistema

O **Access Gate API** é um sistema de controle de acesso que gerencia o fluxo de entrada e saída de pessoas em condomínios, edifícios comerciais ou áreas restritas. O sistema permite o cadastro de moradores, visitantes e usuários do sistema (porteiros/administradores).

---

## 🏗️ Arquitetura e Fluxo de Dados

### Camada de Apresentação → Núcleo → Persistência

```
┌─────────────────┐
│   HTTP Client   │ (Postman, Frontend, Mobile App)
└────────┬────────┘
         │
         ▼
┌─────────────────────────────────────────────┐
│         ADAPTER LAYER (Controllers)          │
│  ┌──────────────────────────────────────┐   │
│  │  MoradorController                    │   │
│  │  UsuarioController                    │   │
│  │  VisitanteController                  │   │
│  └──────────────┬───────────────────────┘   │
│                 │ DTOs                       │
│                 ▼                            │
│  ┌──────────────────────────────────────┐   │
│  │  Converters                           │   │
│  │  (DTO ↔ Domain)                       │   │
│  └──────────────┬───────────────────────┘   │
└─────────────────┼───────────────────────────┘
                  │
                  ▼
┌─────────────────────────────────────────────┐
│         CORE LAYER (Business Logic)          │
│  ┌──────────────────────────────────────┐   │
│  │  Service Ports (Interfaces)           │   │
│  │  - MoradorServicePort                 │   │
│  │  - UsuarioServicePort                 │   │
│  │  - VisitanteServicePort               │   │
│  └──────────────┬───────────────────────┘   │
│                 │                            │
│                 ▼                            │
│  ┌──────────────────────────────────────┐   │
│  │  Services (Implementation)            │   │
│  │  - Validações de Negócio              │   │
│  │  - Regras de Domínio                  │   │
│  └──────────────┬───────────────────────┘   │
│                 │                            │
│                 ▼                            │
│  ┌──────────────────────────────────────┐   │
│  │  Repository Ports (Interfaces)        │   │
│  └──────────────┬───────────────────────┘   │
└─────────────────┼───────────────────────────┘
                  │
                  ▼
┌─────────────────────────────────────────────┐
│      ADAPTER LAYER (Persistence)             │
│  ┌──────────────────────────────────────┐   │
│  │  Repository Adapters                  │   │
│  │  (Domain ↔ Entity)                    │   │
│  └──────────────┬───────────────────────┘   │
│                 │                            │
│                 ▼                            │
│  ┌──────────────────────────────────────┐   │
│  │  JPA Repositories                     │   │
│  └──────────────┬───────────────────────┘   │
└─────────────────┼───────────────────────────┘
                  │
                  ▼
         ┌────────────────┐
         │  H2 Database   │
         └────────────────┘
```

---

## 🔄 Fluxos Principais

### 1️⃣ Fluxo de Cadastro de Morador

```
[Cliente] 
   │
   ├──► POST /api/moradores
   │    Body: {nome, cpf, endereco, celular}
   │
   ▼
[MoradorController]
   │
   ├──► Recebe MoradorDto
   │
   ▼
[MoradorConverter]
   │
   ├──► Converte MoradorDto → Morador (Domain)
   │
   ▼
[MoradorService]
   │
   ├──► Valida regras de negócio
   │    • CPF único
   │    • Campos obrigatórios
   │    • Formato de dados
   │
   ▼
[MoradorRepositoryAdapter]
   │
   ├──► Converte Morador (Domain) → MoradorEntity
   │
   ▼
[MoradorRepository (JPA)]
   │
   ├──► Persiste no banco de dados
   │
   ▼
[Retorno]
   │
   ├──► MoradorEntity → Morador → MoradorDto
   │
   └──► Response: 201 Created + MoradorDto
```

**Exemplo de Request:**
```json
POST /api/moradores
{
  "nome": "João da Silva",
  "cpf": "12345678901",
  "endereco": "Apto 101",
  "celular": "11987654321"
}
```

**Response:**
```json
201 Created
{
  "id": 1,
  "nome": "João da Silva",
  "cpf": "12345678901",
  "endereco": "Apto 101",
  "celular": "11987654321"
}
```

---

### 2️⃣ Fluxo de Cadastro de Visitante

```
[Cliente] 
   │
   ├──► POST /api/visitantes
   │    Body: {nome, rg}
   │
   ▼
[VisitanteController]
   │
   ├──► Recebe VisitanteDto
   │
   ▼
[VisitanteConverter]
   │
   ├──► Converte VisitanteDto → Visitante (Domain)
   │
   ▼
[VisitanteService]
   │
   ├──► Valida regras de negócio
   │    • RG único
   │    • Campos obrigatórios
   │
   ▼
[VisitanteRepositoryAdapter]
   │
   ├──► Converte Visitante → VisitanteEntity
   │
   ▼
[VisitanteRepository (JPA)]
   │
   ├──► Persiste no banco de dados
   │
   ▼
[Retorno]
   │
   ├──► VisitanteEntity → Visitante → VisitanteDto
   │
   └──► Response: 201 Created + VisitanteDto
```

---

### 3️⃣ Fluxo de Consulta de Visitante por RG

```
[Cliente] 
   │
   ├──► GET /api/visitantes/{rg}
   │
   ▼
[VisitanteController]
   │
   ├──► Extrai PathVariable {rg}
   │
   ▼
[VisitanteService]
   │
   ├──► Busca visitante por RG
   │
   ▼
[VisitanteRepositoryAdapter]
   │
   ├──► Consulta VisitanteRepository
   │
   ▼
[VisitanteRepository (JPA)]
   │
   ├──► Executa: SELECT * FROM visitante WHERE rg = ?
   │
   ▼
[Retorno]
   │
   ├──► VisitanteEntity → Visitante → VisitanteDto
   │
   └──► Response: 200 OK + VisitanteDto
        OU
        404 Not Found (se não encontrado)
```

---

### 4️⃣ Fluxo de Listagem de Moradores

```
[Cliente] 
   │
   ├──► GET /api/moradores
   │
   ▼
[MoradorController]
   │
   ▼
[MoradorService]
   │
   ├──► Busca todos os moradores
   │
   ▼
[MoradorRepositoryAdapter]
   │
   ├──► Consulta MoradorRepository
   │
   ▼
[MoradorRepository (JPA)]
   │
   ├──► Executa: SELECT * FROM morador
   │
   ▼
[Retorno]
   │
   ├──► List<MoradorEntity> → List<Morador> → List<MoradorDto>
   │
   └──► Response: 200 OK + List<MoradorDto>
```

---

### 5️⃣ Fluxo de Cadastro de Usuário

```
[Cliente] 
   │
   ├──► POST /api/usuarios
   │    Body: {nome, email, senha, administrador}
   │
   ▼
[UsuarioController]
   │
   ├──► Recebe UsuarioDto
   │
   ▼
[UsuarioConverter]
   │
   ├──► Converte UsuarioDto → Usuario (Domain)
   │
   ▼
[UsuarioService]
   │
   ├──► Valida regras de negócio
   │    • Email único
   │    • Senha forte (futuro)
   │    • Campos obrigatórios
   │
   ▼
[UsuarioRepositoryAdapter]
   │
   ├──► Converte Usuario → UsuarioEntity
   │
   ▼
[UsuarioRepository (JPA)]
   │
   ├──► Persiste no banco de dados
   │
   ▼
[Retorno]
   │
   ├──► UsuarioEntity → Usuario → UsuarioDto
   │
   └──► Response: 201 Created + UsuarioDto
```

---

## 🎯 Casos de Uso Principais

### Cenário 1: Chegada de um Novo Morador
1. **Admin/Porteiro** acessa o sistema
2. Cadastra o novo morador via `POST /api/moradores`
3. Sistema valida CPF e dados obrigatórios
4. Morador é registrado no banco de dados
5. Sistema retorna confirmação com ID gerado

### Cenário 2: Visitante na Portaria
1. **Porteiro** recebe visitante
2. Verifica se já está cadastrado via `GET /api/visitantes/{rg}`
3. Se não cadastrado, registra via `POST /api/visitantes`
4. Sistema cria registro da visita (funcionalidade futura)
5. Porteiro libera acesso

### Cenário 3: Consulta de Moradores
1. **Administrador** precisa visualizar todos os moradores
2. Consulta via `GET /api/moradores`
3. Sistema retorna lista completa
4. Admin pode realizar análises ou relatórios

### Cenário 4: Cadastro de Funcionário
1. **Administrador** cadastra novo porteiro/funcionário
2. Cria usuário via `POST /api/usuarios`
3. Define permissões (administrador: true/false)
4. Sistema armazena credenciais
5. Funcionário pode acessar o sistema

---

## 🔐 Tratamento de Erros

### Fluxo de Exceções

```
[Erro no Service]
   │
   ├──► Lança BusinessException
   │
   ▼
[ApplicationResourceAdvice]
   │
   ├──► Captura exceção
   │
   ├──► Cria ApiError {timestamp, status, message}
   │
   └──► Response: 400/404/500 + ApiError
```

**Tipos de Erros Tratados:**
- ❌ **400 Bad Request**: Dados inválidos
- ❌ **404 Not Found**: Recurso não encontrado
- ❌ **409 Conflict**: Violação de unicidade (CPF/RG/Email duplicado)
- ❌ **500 Internal Server Error**: Erro inesperado

---

## 🔄 Ciclo de Vida da Requisição

```
1. Request HTTP → 2. Controller → 3. Converter (DTO → Domain) 
                                           ↓
                                    4. Service (Business Logic)
                                           ↓
                                    5. Repository Port
                                           ↓
                                    6. Repository Adapter
                                           ↓
                                    7. JPA Repository
                                           ↓
                                    8. Database
                                           ↓
9. Response HTTP ← 10. Converter (Domain → DTO) ← Database Result
```

---

## 📊 Diagrama de Relacionamentos

```
┌─────────────┐
│   Pessoa    │ (Entidade Base)
│             │
│ - id        │
│ - nome      │
└──────┬──────┘
       │
       ├──────────────┬──────────────┐
       │              │              │
┌──────▼──────┐ ┌────▼─────┐ ┌──────▼──────┐
│   Morador   │ │ Visitante│ │   Usuario   │
│             │ │          │ │             │
│ - cpf       │ │ - rg     │ │ - email     │
│ - endereco  │ └──────────┘ │ - senha     │
│ - celular   │              │ - admin     │
└─────────────┘              └─────────────┘
```

---

## 🚀 Próximos Passos

### Fluxos Futuros a Implementar:

1. **Registro de Visitas**
   - Associar visitante a morador
   - Registrar horário de entrada/saída
   - Gerar histórico de visitas

2. **Autenticação e Autorização**
   - Login com JWT
   - Controle de acesso por perfil
   - Refresh token

3. **Notificações**
   - Avisar morador quando visitante chegar
   - Notificar sobre entregas
   - Alertas de segurança

4. **Relatórios e Dashboard**
   - Total de visitas por período
   - Moradores mais visitados
   - Horários de maior movimento

---

## 📝 Notas Técnicas

### Benefícios da Arquitetura Hexagonal no Fluxo:

✅ **Isolamento do Core**: Regras de negócio independentes de frameworks  
✅ **Testabilidade**: Cada camada pode ser testada isoladamente  
✅ **Manutenibilidade**: Mudanças em uma camada não afetam outras  
✅ **Flexibilidade**: Fácil substituição de adaptadores (ex: trocar H2 por PostgreSQL)  
✅ **Clareza**: Fluxo de dados bem definido e previsível

---

**Autor**: Hugo França da Silva Dia  
**Projeto**: Access Gate API  
**Arquitetura**: Hexagonal (Ports and Adapters)  
**Versão**: 0.0.1-SNAPSHOT
