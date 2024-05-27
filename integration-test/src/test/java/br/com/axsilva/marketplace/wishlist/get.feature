Feature: Get test for Wishlist
  Background:
    * url baseUrl

  Scenario: Get existent bank product
    Given path '/v1/wishlist/products/selected?clientId=clientId&productId=ewrwerwer23'
    And header Accept = 'application/json'
    When method GET
    Then status 200

  Scenario: Get unexistent bank product
    Given path '/v1/wishlist/products/selected?clientId=clientId&productId=ewrwerwer23s'
    And header Accept = 'application/json'
    When method GET
    Then status 404