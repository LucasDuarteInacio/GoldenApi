package com.goldenburguer.app.exceptions;

import java.util.IllegalFormatException;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Getter
public enum IssueEnum {
  CUSTOMER_NOT_FOUND(1, "Cliente não encontrado."),
  CATEGORY_NOT_FOUND(2, "Categoria não encontrada."),
  NEIGHBORHOOD_NOT_FOUND(2, "Bairro não encontrada."),
  ORDER_NOT_FOUND(3, "Pedido não encontrado"),
  PRODUCT_NOT_FOUND(4, "Produto não encontrado."),
  OPTION_NOT_FOUND(5, "Acrescimo não encontrado."),
  INVALID_OPTION(7, "Forneça um nome de acréscimo válido."),
  EXISTING_OPTION(8, "já existe um acréscimo cadastrado com esse nome"),
  EXISTING_NEIGHBORHOOD(8, "já existe um Bairro cadastrado com esse nome"),
  CHECKOUT_NOT_FOUND(6, "Caixa não encontrado."),
  INVALID_CATEGORY(7, "Forneça um nome de categoria válido."),
  EXISTING_CATEGORY(8, "Categoria já cadastrada"),
  EXISTING_PRODUCT(9, "Produto já cadastrado"),
  EXISTING_CUSTOMER(10, "Já existe um usuario cadastrado com esse nome."),
  CHECKOUT_CLOSED(11, "Este caixa ja foi fechado"),
  CHECKOUT_OPENED(12, "É necessário fechar o caixa antes de abrir um novo."),
  CHECKOUT_ACTIVE_NOT_EXISTING(13, "Não foi encontrado nenhum caixa aberto no momento"),
  ADDRESS_NOT_FOUND(14, "Endereço não encontrado.");

  private final int code;
  private final String message;
  private final Logger logger = LogManager.getLogger(IssueEnum.class);

  IssueEnum(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public String getFormattedMessage(final Object... args) {
    if (message == null) {
      return "";
    }
    try {
      return String.format(message, args);
    } catch (final IllegalFormatException e) {
      logger.warn(e.getMessage(), e);
      logger.warn(String.format("message: %s and code: %d ", message, code));
      return message.replace("%s", "");
    }
  }
}
