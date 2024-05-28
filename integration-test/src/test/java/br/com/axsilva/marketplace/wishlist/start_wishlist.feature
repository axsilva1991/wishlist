Feature: Get test for Wishlist
  Background:
    * url baseUrl

  Scenario: Get nonexistent bank product
    Given path '/selected?clientId=clientId1&productReferenceCode=productNotCreated'
    And header Accept = 'application/json'
    When method GET
    Then status 404

  Scenario: Add a product to the customer's wish list
    Given path '/clientId1'
    And header Accept = 'application/json'
    And request {'referenceCode': 'product1hash','referenceStore': 'referenceStore1',  'selectedPrice': 10.0  }
    When method POST
    Then status 201

  Scenario: Add a duplicated product to the customer's wish list
    Given path '/clientId1'
    And header Accept = 'application/json'
    And request {'referenceCode': 'product1hash','referenceStore': 'referenceStore1',  'selectedPrice': 10.0  }
    When method POST
    Then status 422

  Scenario: Add a product with not send referenceCode wish list
    Given path '/clientId1'
    And header Accept = 'application/json'
    And request {'referenceCode': 'product1hash','referenceStore': 'referenceStore1',  'selectedPrice': 10.0  }
    When method POST
    Then status 422

  Scenario: Get existent product
    Given path '/selected?clientId=clientId1&productReferenceCode=product1hash'
    And header Accept = 'application/json'
    When method GET
    Then status 200

  Scenario: List products of wishlist
    Given path '/clientId1'
    And header Accept = 'application/json'
    When method GET
    Then status 200
    And match response == {  'clientId': 'clientId1', 'products': [  {  'referenceCode': 'product1hash',  'referenceStore': 'referenceStore1',  'selectedPrice': 10.0  }  ]  }

  Scenario: List products of wishlist when the wishlist was not found
    Given path '/clientIdNotCreated'
    And header Accept = 'application/json'
    When method GET
    Then status 404
    And match response == {  'code': 'WISHLIST_NOT_FOUND_ERROR', 'message': 'This wishlist was not found or empty.'}

  Scenario: Delete a product created
    Given path '/clientId1?productReferenceCode=product1hash'
    And header Accept = 'application/json'
    When method DELETE
    Then status 204

  Scenario: Validate Delete a product
    Given path '/selected?clientId=clientId1&productReferenceCode=product1hash'
    And header Accept = 'application/json'
    When method GET
    Then status 404