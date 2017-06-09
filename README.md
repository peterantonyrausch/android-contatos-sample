# android-contatos-sample

# Por que estamos fazendo o teste?
Nós queremos montar um time de desenvolvedores Android, e para isso
precisamos saber como você programa. Queremos ver seu potencial, então avalie
muito bem como irá criar a arquitetura do seu código.
# Como você será avaliado
Vamos avaliar tudo seu seu código desde a indentação até como você criou a
regra de negócio.
# Prazo e entrega
O prazo para entregar a solução é até as 8:00 AM da próxima segunda-feira. Você
deve nos enviar por email o endereço do repositório com seu código e o APK
gerado.
# O problema
Seu objetivo é desenvolver um aplicativo para cadastrar de forma fictícia um
endereço de contato.

O usuário deverá preencher um formulário com os seguintes campos:
- Nome;
- Email;
- Cep;
- Logradouro;
- Número;
- Complemento;
- Bairro;
- Cidade;
- UF;
- Telefone;

As regras dos campos são:
- Nome:
  - Campo obrigatório
- Email:
  - Campo obrigatório;
  - Verificar se o email é valido
 - Cep:
  - Campo obrigatório
  - Ao completar de digitar o cep, efetuar uma consulta no web service para buscar o endereço
  - Se não for localizado nenhum endereço, exibir uma mensagem informando ao usuário
  - Os campos logradouro, bairro, cidade e uf ficam desabilitados quando localizar o endereço
- Número:
  - Campo obrigatório;
- Complemento
  - Campo opcional;
- Bairro:
  - Campo obrigatório;
- Cidade:
  - Campo obrigatório;
- UF:
  - Campo obrigatório;
- Telefone:
  - Campo obrigatório
  
Se a validação dos campos estiver corretos, abrir uma outra tela com uma
mensagem de sucesso.

Para buscar o endereço pode utilizar qualquer um dos web services abaixo:
- https://viacep.com.br/ws/01001000/json
- http://correiosapi.apphb.com/cep/76873274
