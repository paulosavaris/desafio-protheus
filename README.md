# Desafio_Programador_Protheus_Unoesc

Esse e o codigo para o desafio da [Unoesc](https://www.unoesc.edu.br/). para a vaga de programador Protheus

Link para repostiorio com os commits [PauloSavaris](https://github.com/paulosavaris/Desafio_Programador_Protheus_Unoesc).

Utilizado a IDE Intellij e visual Studio Code

# Requisitos
- JDK 17
- PostgreSQL 13
- Maven

# Configuracao do Banco de Dados

Antes de executar a aplicação, é necessário configurar o banco de dados. Para isso, execute os seguintes passos:

- Crie um banco de dados no PostgreSQL. de preferencia com o nome apiunoesc
  - Configure a conexado do banco no arquivo src\main\java\org\example\Migracao e no arquivo \src\main\java\org\example\postgres\ConexaoFactory
  - Caso de algum erro com a criacao das tabelas via migration, tem um arquivo unoesAPI.sql com os comandos para criar as tabelas, function e trigger necessarias.

# Executando a Aplicação
Para executar a aplicação, siga os seguintes passos:

- Clone o repositório.
- Configure o banco de dados de acordo com as instruções acima.

- Se estiver tudo certo so Executar o arquivo main

Em seguida, execute a classe Main para exibir o menu de opções.

Escolha uma das opções do menu digitando o número correspondente e pressionando ENTER. Aqui estão as opções disponíveis:

1 - Consultar dados das acoes, individual ou em grupo. Essa opção permite que você consulte informações sobre uma ou várias ações especificadas.

2 - Consulta todas as acoes (recomendável rodar a cada inicialização do programa, pois baixa todas as informações das acoes no banco). Essa opção permite que você baixe todas as informações disponíveis sobre todas as ações listadas na API e as armazene em um banco de dados.

3 - Consulta grupos de acoes com as mesma letras informadas. Essa opção permite que você consulte informações sobre várias ações cujos símbolos começam com as mesmas letras. Por exemplo, se você digitar "AAPL", a aplicação retornará informações sobre todas as ações que começam com "AAPL", como "AAPL34" e "AAPL3S".

4 - Exportar Arquivo CSV. Essa opção permite que você exporte as informações das ações consultadas para um arquivo CSV.

7 - Sair / finalizar aplicação. Essa opção permite que você saia da aplicação.

Selecione uma opção e siga as instruções apresentadas na tela para prosseguir com a consulta das informações das ações ou a exportação dos dados.

Após concluir a ação desejada, você será redirecionado para o menu principal. Para sair da aplicação, selecione a opção 7.

