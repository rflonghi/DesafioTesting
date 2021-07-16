# Desafio Testing

### US0005 - Cadastro de Districts

Descrição: Permitir criação de bairros.

Sign:

| Method | Sign |
|---|---|
| POST | /api/district/create |

### Parâmetros:

| Parâmetros | Tipo | Descrição/Evento |
|:---|:---:|:---|
| name | String | Nome do bairro. |
| value | BigDecimal | Valor de metro² dos imóveis no bairro. |

### Request:
<pre>
{
    "name": "Osasco",
    "value": 10
}
</pre>

### Response:
<pre>
{
    "id": 1
    "name": "Osasco",
    "value": 10
}
</pre>
