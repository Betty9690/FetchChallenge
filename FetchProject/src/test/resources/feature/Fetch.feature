Feature: Validating Fake Gold Bar
  @fetch
  Scenario: Positive Validation
    Given User navigates to Fetch Application
    When User input gold bar numbers
    Then User find the fake gold bar weight
