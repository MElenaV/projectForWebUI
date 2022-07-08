Feature: Добавление товара в корзину

  Background:
    Given User Authorized

    @hooks
      @close
  Scenario Outline:
    When click tshirts button in women submenu
    And select size '<size>'
    And select price
    And click tshirt cart by name
    Then check total sum
    Examples:
      | size |
      | S    |
      | M    |