Feature: Order component
  Scenario: list orders
    When I GET "/orders"
    Then the response status is 200
