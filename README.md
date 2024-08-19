# Projeto Final do Módulo de Lógica de Programação
### Agenda de Contatos


Este projeto foi desenvolvido como projeto final para conclusão do módulo de lógica de programação do curso Santander Coders Back-End ministrado pela Ada Tech.
A aplicação requerida no projeto consiste em ser uma agenda de contatos desenvolvida em Java, onde é possível registrar inúmeros contatos e seus dados.
O projeto em questão foi realizado em uma equipe, cujo os membros Priscila Silva dos Santos, Antonia Débora Dos Santos Pontes Rocha,
Lucas Alecsander Ataide Gomes Da Silva, Matheus Gomes e Lucas Salvador Do Carmo, desenvolveram juntos o projeto de forma colaborativa, com comunicação clara e eficaz, dividindo bem as tarefas e nos ajudando mutuamente nas dúvidas que surgiram ao longo do processo.




## Funcionalidades da Aplicação


1. **Adicionar Contato**: Permite adicionar um novo contato à agenda.
2. **Detalhar Contato**: Exibe os detalhes de um contato específico.
3. **Editar Contato**: Permite editar as informações de um contato existente.
4. **Remover Contato**: Remove um contato da agenda.
5. **Sair**: Encerra a aplicação.
6. **Tratamento de Erros**: Trata adequadamente as possíveis incompatibilidades na utilização da aplicação.


## Colaboração no Git


Para garantir que todos os membros da equipe pudessem colaborar de forma eficiente, seguimos os seguintes passos no Git:


1. **Criação do Repositório**: Um repositório foi criado no GitHub para o projeto.
2. **Criação de uma Branch**: Uma branch chamada release foi criada para que a equipe colocasse o código em uma “branch de trabalho” antes de passar o projeto completo para a branch main
3. **Clonagem do Repositório**: Cada membro da equipe clonou o repositório para suas máquinas locais.

```plaintext
fork (feito no github)
git clone https://github.com/usuario/Agenda-Contatos.git   
git checkout release
git add .
git commit -m "Descrição do commit"
git push origin release
Sync fork (feito no github)
git pull origin release
```
4. **Adição, Commit e Push**: Comandos feitos para enviar as modificações feitas para o repositório remoto
5. **Sync fork e git pull**: Para atualizar o repositório do github e para atualizar os códigos na máquina local de cada membro da equipe
6. **Revisão e Merge**: As pull requests eram revisadas por outros membros da equipe antes de serem mescladas na branch principal.


## Estrutura da Aplicação


` \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\`


```plaintext
                      ##################
                      ##### AGENDA #####
                      ##################


                    >>>> Contatos <<<<
                 
Id | Nome            | Telefone    | E-mail
1  | Priscila Santos | 11977775555 | priscila@gmail.com
2  | Débora Rocha    | 11982227777 | debora@gmail.com
3  | Lucas Silva     | 11977775555 | lucas_silva@gmail.com
4  | Matheus Gomes   | 25988844777 | matheus@gmail.com
5  | Lucas Carmo     | 13982287777 | lucas_carmo@gmail.com


>>>> Menu Contato <<<<


1 - Adicionar Contato
2 - Detalhar Contato
3 - Editar Contato
4 - Remover Contato
5 - Sair
```


## Reflexões sobre o projeto
### Quais foram os desafios no projeto?
Trabalhar com arrays estáticos (Contato[]) para armazenar os contatos. Arrays em Java têm um tamanho fixo, o que complicou a adição e remoção de contatos, pois cada operação exigia a criação de um novo array e a cópia dos elementos existentes. A necessidade de manter IDs únicos e consistentes para cada contato, especialmente ao adicionar ou remover contatos. Criar uma interface de linha de comando que fosse intuitiva e amigável para o usuário final. Gerenciar o ciclo de vida do programa, garantindo que ele pudesse ser iniciado, executado e finalizado de maneira controlada, sem comportamentos inesperados.

### O que foi mais interessante?
Ajudar e ser ajudado pelos membros da equipe, receber orientações específicas para o projeto do professor e usar habitualmente comandos do git para mesclar e sincronizar os códigos com todos os membros da equipe. Implementar exceções personalizadas aprimorando a robustez do sistema, garantindo uma abordagem mais controlada e específica para lidar com os erros. Projetar a interface de linha de comando para interação com o usuário, garantindo que seja intuitiva e responsiva, oferecendo uma visão sobre como tornar o software mais acessível e fácil de usar.


### O que pode ser melhorado?
Substituir arrays por coleções dinâmicas (ArrayList), implementação de persistência de dados usando um banco de dados (SQL), garantidos que os contatos sejam salvos. Implementar testes unitários para garantir que as funcionalidades do sistema operem corretamente. E adicionar pesquisa de contatos por nome ou email, ao invés de ser somente por telefone.




