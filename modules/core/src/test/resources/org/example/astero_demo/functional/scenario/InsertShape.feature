Feature: Create Shape
  Scenario: Create Shape
    When Click on create Rectangular button
    Then Application have shifted into insert mode
    When Cursor are moved on a Canvas
    When Drag and drop by x: 100, y: 100
    Then Shape was created with width: 100, height: 100
    Then Application have shifted into single selection mode