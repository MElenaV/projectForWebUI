Feature: Отправка обратной связи

  Background:
    Given User Authorized

    @hooks
      @close
  Scenario Outline:
    When click link contact us
    And select subject heading
    And fill message
    And fill input email '<email>'
    And click button send message

    Then check success send message
    Examples:
      | email |
      | 123@mail.ru |
      | 321@mail.ru |
