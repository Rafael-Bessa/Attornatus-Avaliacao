# Attornatus Avaliação Back End

![aaaaaaaa](https://user-images.githubusercontent.com/104053775/212801646-10ca7d50-5016-4378-a2d8-221288770e10.png)

# 💻 Como eu fiz o desenvolvimento

- ``Criei as camadas de Controller para Pessoas e Enderecos (Endpoints /pessoas /enderecos)`` 
- ``As classes de serviço realizar as operações com maior quantidade de código, com o intuito de deixar as classes do controller mais limpas``
- ``Criei interfaces para Pessoas e Enderecos, afim de gerar facilidade a escrita e idenficação de métodos``
- ``Uma pessoa pode ter vários endereços, e pode ter um endereço principal``
- ``O endereço principal, eu resolvi criar uma classe a parte e anotá-la como @Embeddable (sem tabela específica no database)``
- ``Os métodos http possíveis são: Para /pessoas (GET, GET/ID, POST, PUT/ID, DELETE/ID, PATCH/ID), o patch eu coloquei com a funcionalidade atualizar o endereço principal.``
- ``Os métodos http possíveis para /endereco são: (GET/idPessoa), onde ele mostra todos endereços de uma determinada pessoa, (POST/idPessoa), onde ele insere mais endereços para determinada pessoa.`` 
- ``Todas as funcionalidades pedidos foram implementadas``

# Utilização com o Insomnia 🌑 🌔

## Métodos POST

![post](https://user-images.githubusercontent.com/104053775/212803094-fefbbe35-ad45-4c12-ac2d-cc3554f8790d.png)
![post1](https://user-images.githubusercontent.com/104053775/212803222-b0635888-51f9-41c1-832c-195b7c508389.png)

## Método Get com paginação e ordenado pelo nome das pessoas

![getaall](https://user-images.githubusercontent.com/104053775/212803421-4e45e760-d545-4ae9-807e-ee6158d28196.png)

## Método PUT

![put](https://user-images.githubusercontent.com/104053775/212803619-1fb13114-1723-4648-bc9b-b79cd9fa5fc8.png)

## Banco de dados H2 após essas operações

Os campos onde está NULL, é por que o endereço principal não foi informado para essa pessoa.

![h22](https://user-images.githubusercontent.com/104053775/212803803-f65aea8c-3044-4684-8a0e-eebe5ab45ef7.png)

![h222](https://user-images.githubusercontent.com/104053775/212803964-dfa8ccff-7a64-4f56-a17d-f6293b4af4c9.png)


# Testes automatizados

## Fiz alguns testes com os controllers, outros com os repositorys e uns com a classe Pessoa

![teste](https://user-images.githubusercontent.com/104053775/212804236-55c5d04f-ed2e-481d-a069-0b04ef28b45f.png)

