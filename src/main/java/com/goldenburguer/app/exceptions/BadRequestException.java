package com.goldenburguer.app.exceptions;

public class BadRequestException extends ApiException {

  public BadRequestException(String message, String detail, Integer status) {
    super(message, detail, status);
  }

  public BadRequestException(final Issue issue) {
    super(issue);
  }

  public static BadRequestException invalidCategory() {
    return new BadRequestException(new Issue(IssueEnum.INVALID_CATEGORY));
  }

  public static BadRequestException existingCategory() {
    return new BadRequestException(new Issue(IssueEnum.EXISTING_CATEGORY));
  }

  public static BadRequestException existingProduct() {
    return new BadRequestException(new Issue(IssueEnum.EXISTING_PRODUCT));
  }

  public static BadRequestException existingCustomer() {
    return new BadRequestException(new Issue(IssueEnum.EXISTING_CUSTOMER));
  }

  public static BadRequestException checkoutOpened() {
    return new BadRequestException(new Issue(IssueEnum.CHECKOUT_OPENED));
  }

  public static BadRequestException checkoutClosed() {
    return new BadRequestException(new Issue(IssueEnum.CHECKOUT_CLOSED));
  }

  public static BadRequestException notExistingcheckoutActive() {
    return new BadRequestException(new Issue(IssueEnum.CHECKOUT_ACTIVE_NOT_EXISTING));
  }

  public static BadRequestException invalidOption() {
    return new BadRequestException(new Issue(IssueEnum.INVALID_OPTION));
  }

  public static BadRequestException existingOption() {
    return new BadRequestException(new Issue(IssueEnum.EXISTING_OPTION));
  }

  public static BadRequestException existingNeighborhood() {
    return new BadRequestException(new Issue(IssueEnum.EXISTING_NEIGHBORHOOD));
  }
}
