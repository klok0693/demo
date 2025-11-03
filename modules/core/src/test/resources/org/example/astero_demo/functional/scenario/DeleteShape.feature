Feature: Delete Shape
  Scenario: Delete Shape
    Given Shape with id: 1
    When Shape with id: 1 are selected on canvas
    Then Application have shifted into single selection mode
    When Click on Delete button
    Then Shape with id: 1 no longer exist
    Then Application have shifted into single selection mode