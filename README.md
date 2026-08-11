Previsão do Tempo — Java

Aplicação desktop desenvolvida em Java para consultar condições meteorológicas de cidades brasileiras por meio da API Open-Meteo.
O projeto foi desenvolvido com foco em estudos de integração com APIs REST, consumo de dados JSON, tratamento de respostas HTTP e construção de interfaces gráficas com Java Swing.

---Objetivo
O objetivo do projeto é permitir que o usuário informe uma cidade e visualize as condições meteorológicas correspondentes de forma simples e visual.

A aplicação realiza duas etapas principais:
Consulta a API de geocodificação da Open-Meteo para obter latitude e longitude da cidade pesquisada.
Utiliza essas coordenadas para consultar os dados meteorológicos e exibir as informações na interface.
Atualmente são apresentados:
Temperatura;
Condição climática;
Umidade;
Velocidade do vento;
Ícone correspondente à condição do tempo.
Tecnologias utilizadas
Java
Java Swing
HTTP / REST APIs
HttpURLConnection
JSON
JSON Simple 1.1.1
Open-Meteo Geocoding API
Open-Meteo Weather Forecast API
Git / GitHub
IntelliJ IDEA

Funcionalidades:
Pesquisa de cidades brasileiras;
Conversão do nome da cidade em latitude e longitude;
Consumo de API meteorológica;
Processamento de respostas JSON;
Identificação da condição climática através do código meteorológico;
Exibição de temperatura, umidade e velocidade do vento;
Alteração do ícone conforme a condição climática;
Interface gráfica desktop desenvolvida com Swing.

Como funciona:
O fluxo principal da aplicação é:
Usuário informa uma cidade
        ↓
WeatherAppGui
        ↓
WeatherApp.getWeatherData()
        ↓
Open-Meteo Geocoding API
        ↓
Latitude + Longitude
        ↓
Open-Meteo Weather Forecast API
        ↓
Resposta JSON
        ↓
Tratamento dos dados
        ↓
Atualização da interface

Estrutura do projeto:
Previsao-do-tempo---java/
│
├── README.md
├── .gitignore
└── Tempo/
    ├── lib/
    │   └── json-simple-1.1.1.jar
    │
    └── src/
        ├── AppLauncher.java
        ├── WeatherApp.java
        ├── WeatherAppGui.java
        │
        └── assets/
            ├── clear.png
            ├── cloudy.png
            ├── humidity.png
            ├── rain.png
            ├── search.png
            ├── snow.png
            └── windspeed.png

Principais classes:
AppLauncher.java
Responsável por inicializar a aplicação e abrir a interface gráfica.

WeatherApp.java
Contém a lógica principal da aplicação, incluindo:
comunicação HTTP com a Open-Meteo;
busca das coordenadas da cidade;
consulta dos dados meteorológicos;
leitura e processamento do JSON;
conversão dos códigos meteorológicos em descrições compreensíveis.

WeatherAppGui.java
Responsável pela interface gráfica construída com Java Swing. Recebe a cidade informada pelo usuário, solicita os dados ao WeatherApp e atualiza os componentes da tela.

assets/
Contém os recursos visuais utilizados pela interface, como ícones de chuva, céu limpo, tempo nublado, neve, umidade e vento.

Como executar:
É necessário possuir:
JDK instalado e configurado;
IntelliJ IDEA ou outra IDE compatível com projetos Java;
acesso à internet para realizar as consultas à API.

O projeto utiliza a biblioteca json-simple-1.1.1, disponível na pasta:

Tempo/lib/json-simple-1.1.1.jar

1. Clonar o repositório

git clone https://github.com/carlossantos-debuger/Previsao-do-tempo---java.git

2. Abrir o projeto

Abra o repositório na sua IDE e configure a pasta Tempo/src como diretório de código-fonte, caso isso não seja identificado automaticamente.

3. Configurar a dependência

Caso a IDE não carregue a dependência automaticamente, adicione o arquivo abaixo ao classpath do projeto:

Tempo/lib/json-simple-1.1.1.jar

No IntelliJ IDEA isso pode ser feito em:

File → Project Structure → Modules → Dependencies

4. Executar

Execute a classe:

AppLauncher.java

A aplicação abrirá a interface gráfica. Digite o nome de uma cidade brasileira e clique no botão de pesquisa.

API utilizada:
O projeto utiliza a Open-Meteo em dois momentos:

Geocodificação
Converte o nome da cidade pesquisada em latitude e longitude.
https://geocoding-api.open-meteo.com/v1/search

Dados meteorológicos
Consulta informações meteorológicas utilizando as coordenadas encontradas.
https://api.open-meteo.com/v1/forecast

Conceitos praticados
Este projeto foi utilizado para praticar conceitos importantes de desenvolvimento Java, como:
integração com APIs externas;
requisições HTTP;
manipulação de JSON;
separação entre interface e lógica da aplicação;
tratamento de erros;
manipulação de datas e horários;
desenvolvimento de interfaces desktop;
versionamento de código com Git.

Melhorias futuras
Algumas evoluções possíveis para o projeto:
adicionar previsão para os próximos dias;
melhorar o tratamento de erros de conexão;
adicionar testes automatizados;
migrar o gerenciamento de dependências para Maven ou Gradle;
separar a integração com a API em uma camada de serviço;
criar objetos de domínio para representar os dados meteorológicos;
melhorar a responsividade e o layout da interface.

Autor

Carlos Santos

GitHub: carlossantos-debuger

Projeto desenvolvido para estudos de Java e integração com APIs.
