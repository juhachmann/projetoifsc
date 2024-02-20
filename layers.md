# Camadas

## API
 - RestControllers
 - Documentação OPEN API (Swagger)
 - HATEOAS
 - Http Responses
 - Validação dos Inputs
 - DTOs específicos para a API
 - Proteção SQL injection
 - Se liga a uma das "portas" do domínio
 - Controllers, DTOs, Services, Helpers


## Infraestrutura
 - JPA
 - Records / Entidades JPA (não são o mesmo que as Entidades do Domínio, é só o jeito de criar e manipular as tabelas usando o JPA)
 - Proteção contra SQL Injection?
 - Autenticação e autorização fica aqui?
 - Queries de busca com filtro
 - Implementa interface do Repositório para se comunicar com o domínio
 - JPAEntities, Repositories, Helpers


## Core
 - Entidades (com todas as regras de negócio)
 - Aplicação / casos de uso - aplica as regras de negócio 
### Interfaces / APIs : 
 - DTOs específicos (no caso, vou fazer igual às entidades, mas vou fazer para simplificar a minha vida)
 - Interfaces dos Repositórios
 - Entry Points / Portas (precisa estar separado dos Casos de Uso? Acho que vou fazer junto)
 - Entities, Repositories Interfaces, Ports


## Fluxo dos dados
 - Request do usuário via serviço web por meio de um Http Request
 - O serviço web solicita algo ao domínio por uma das portas
 - O domínio se comunica com a camada da base de dados que implementa a interface que ele impõe
 - A camada da base de dados devolve uma resposta ao domínio, que continua trabalhando os dados
 - O domínio devolve uma resposta ao serviço web
 - O serviço web devolve uma resposta ao usuário por meio de uma Http Response

### Observações
 - No caso, domínio, serviço web e infraestrutura vão estar todos no mesmo servidor, a divisão é só por clareza e para eu aprender
 - POJOs diferentes que vão ficar tendo que se comunicar: na camada da API temos os requests e responses do serviço web; na camada do domínio, tem os DTOs que devem estar no formato de entrada / saída nas "portas", para que o serviço web consiga se comunicar com o domínio; e, por fim, na camada de infraestrutura, há as entidades que irão representar as tabelas do banco de dados, onde estarão as anotações do JPA. Elas também vão ter que mapear para as entidades do domínio, mas não pode expor as entidades do domínio, então, vai ter que usar outros DTOs apenas para isso. No total, há: os DTOs que comunicam com o serviço web, e os DTOs que comunicam com a infraestrutura. Além disto, as entidades de domínio e as entidades JPA (sem lógica). 
 - User: UserJPAEntity, User, UserDTO, UserEntityDTO
 - Config: ConfigJPAEntity, Config, ConfigDTO, ConfigEntityDTO
 - Vaga: VagaJPAEntity, Vaga, VagaDTO, VagaEntityDTO
 - Para eu não me perder, vou ficar com o básico nesse momento, que são "User" e "Vaga"


