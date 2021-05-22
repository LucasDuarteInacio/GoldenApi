package com.goldenburguer.app.exceptions;

public class NotFoundException extends ApiException {

  public NotFoundException(String message, String detail, Integer status) {
    super(message, detail, status);
  }

  public NotFoundException(final Issue issue) {
    super(issue);
  }

  public static NotFoundException customerNotFound() {
    return new NotFoundException(new Issue(IssueEnum.CUSTOMER_NOT_FOUND));
  }

  public static NotFoundException categoryNotFound() {
    return new NotFoundException(new Issue(IssueEnum.CATEGORY_NOT_FOUND));
  }

  public static NotFoundException productNotFound() {
    return new NotFoundException(new Issue(IssueEnum.PRODUCT_NOT_FOUND));
  }

  public static NotFoundException orderNotFound() {
    return new NotFoundException(new Issue(IssueEnum.ORDER_NOT_FOUND));
  }

  public static NotFoundException optionNotFound() {
    return new NotFoundException(new Issue(IssueEnum.OPTION_NOT_FOUND));
  }

  public static NotFoundException checkoutNotFound() {
    return new NotFoundException(new Issue(IssueEnum.CHECKOUT_NOT_FOUND));
  }

  public static NotFoundException addressNotFound() {
    return new NotFoundException(new Issue(IssueEnum.ADDRESS_NOT_FOUND));
  }

  public static NotFoundException NeighborhoodNotFound() {
    return new NotFoundException(new Issue(IssueEnum.NEIGHBORHOOD_NOT_FOUND));
  }
}
