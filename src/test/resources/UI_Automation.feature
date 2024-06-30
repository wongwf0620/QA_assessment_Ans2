@RunAll
Feature: Convert Module

Background:
  Given I am on the xe landing page

@VerifiedUI
Scenario: Verified Convert Module UI
  When Verified the URL page is "https://www.xe.com/"
  When I enter "abc" in the "Amount" field
  Then Verified convert module UI of the page

@ConvertCurrency
Scenario Outline: Convert currency with different currency pairs
  When Verified the URL page is "https://www.xe.com/"
  And I select "FromCurrency" and type "<FromCurrency>" to the field
  And I select "ToCurrency" and type "<ToCurrency>" to the field
  When I enter "100" in the "Amount" field
  And I click on the "Convert" button
  Then I should see the converted amount in the "Result" field

Examples:
  | FromCurrency | ToCurrency |
  | EUR          | USD        |
  | USD          | GBP        |
  | SGD          | MYR        |


@ConvertTestInvalidAmount
Scenario Outline: Test invalid amount input on amount field
  When Verified the URL page is "https://www.xe.com/"
  And I select "FromCurrency" and type "<FromCurrency>" to the field
  And I select "ToCurrency" and type "<ToCurrency>" to the field
  When I enter "<ConvertAmount>" in the "Amount" field
  And I check "Convert" button is available or not
  Then I check the "error" message

  Examples:
    | FromCurrency | ToCurrency | ConvertAmount |
    | SGD          | MYR        | abc           |
    | USD          | EUR        | -100          |

@ConvertTestLargeAmount
Scenario Outline: Test Large amount of convert currency
  When Verified the URL page is "https://www.xe.com/"
  And I select "FromCurrency" and type "<FromCurrency>" to the field
  And I select "ToCurrency" and type "<ToCurrency>" to the field
  When I enter "<ConvertAmount>" in the "Amount" field
  And I click on the "Convert" button
  Then I should see the converted amount in the "Result" field

  Examples:
    | FromCurrency | ToCurrency | ConvertAmount |
    | SGD          | MYR        | 100000000000  |
    | USD          | EUR        | 100000000000  |

  @Convert
  Scenario Outline: Test input behavior if enter more than two decimal number in amount field after dot Example: 12345.5678
    When Verified the URL page is "https://www.xe.com/"
    And I select "FromCurrency" and type "<FromCurrency>" to the field
    And I select "ToCurrency" and type "<ToCurrency>" to the field
    When I enter "<ConvertAmount>" in the "Amount" field
    And I click on the "Convert" button
    And I should see the converted amount in the "Result" field
    And I check back the "Amount" field value for correction value

    Examples:
      | FromCurrency | ToCurrency | ConvertAmount |
      | USD          | MYR        | 123.450000    |
      | USD          | MYR        | 123.456789    |

  @Convert
  Scenario Outline: Convert currency with same currency pairs
    When Verified the URL page is "https://www.xe.com/"
    And I select "FromCurrency" and type "<FromCurrency>" to the field
    And I select "ToCurrency" and type "<ToCurrency>" to the field
    When I enter "100" in the "Amount" field
    And I click on the "Convert" button
    Then I should see the converted amount in the "Result" field

    Examples:
      | FromCurrency | ToCurrency |
      | USD          | USD        |
      | EUR          | EUR        |
      | CNY          | CNY        |
