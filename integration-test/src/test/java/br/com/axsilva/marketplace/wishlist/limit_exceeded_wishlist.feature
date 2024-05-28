Feature: Get test for Wishlist
  Background:
    * url baseUrl

  Scenario: Add a product1hashLimit  for clientIdLimited
    Given path '/clientIdLimited'
    And header Accept = 'application/json'
    And request {'referenceCode': 'product1hashLimit','referenceStore': 'referenceStore1',  'selectedPrice': 10.0  }
    When method POST
    Then status 201

  Scenario: Add a product2hashLimit for clientIdLimited
    Given path '/clientIdLimited'
    And header Accept = 'application/json'
    And request {'referenceCode': 'product2hashLimit','referenceStore': 'referenceStore1',  'selectedPrice': 10.0  }
    When method POST
    Then status 201

  Scenario: Add a product3hashLimit for clientIdLimited
    Given path '/clientIdLimited'
    And header Accept = 'application/json'
    And request {'referenceCode': 'product3hashLimit','referenceStore': 'referenceStore1',  'selectedPrice': 10.0  }
    When method POST
    Then status 201

  Scenario: Add a product4hashLimit for clientIdLimited
    Given path '/clientIdLimited'
    And header Accept = 'application/json'
    And request {'referenceCode': 'product4hashLimit','referenceStore': 'referenceStore1',  'selectedPrice': 10.0  }
    When method POST
    Then status 201

  Scenario: Add a product5hashLimit for clientIdLimited
    Given path '/clientIdLimited'
    And header Accept = 'application/json'
    And request {'referenceCode': 'product5hashLimit','referenceStore': 'referenceStore1',  'selectedPrice': 10.0  }
    When method POST
    Then status 201

  Scenario: Add a product6hashLimit for clientIdLimited
    Given path '/clientIdLimited'
    And header Accept = 'application/json'
    And request {'referenceCode': 'product6hashLimit','referenceStore': 'referenceStore1',  'selectedPrice': 10.0  }
    When method POST
    Then status 201

  Scenario: Add a product7hashLimit for clientIdLimited
    Given path '/clientIdLimited'
    And header Accept = 'application/json'
    And request {'referenceCode': 'product7hashLimit','referenceStore': 'referenceStore1',  'selectedPrice': 10.0  }
    When method POST
    Then status 201

  Scenario: Add a product8hashLimit for clientIdLimited
    Given path '/clientIdLimited'
    And header Accept = 'application/json'
    And request {'referenceCode': 'product8hashLimit','referenceStore': 'referenceStore1',  'selectedPrice': 10.0  }
    When method POST
    Then status 201

  Scenario: Add a product9hashLimit for clientIdLimited
    Given path '/clientIdLimited'
    And header Accept = 'application/json'
    And request {'referenceCode': 'product9hashLimit','referenceStore': 'referenceStore1',  'selectedPrice': 10.0  }
    When method POST
    Then status 201

  Scenario: Add a product10hashLimit for clientIdLimited
    Given path '/clientIdLimited'
    And header Accept = 'application/json'
    And request {'referenceCode': 'product10hashLimit','referenceStore': 'referenceStore1',  'selectedPrice': 10.0  }
    When method POST
    Then status 201

  Scenario: Add a product11hashLimit  for clientIdLimited
    Given path '/clientIdLimited'
    And header Accept = 'application/json'
    And request {'referenceCode': 'product11hashLimit','referenceStore': 'referenceStore1',  'selectedPrice': 10.0  }
    When method POST
    Then status 201

  Scenario: Add a product12hashLimit for clientIdLimited
    Given path '/clientIdLimited'
    And header Accept = 'application/json'
    And request {'referenceCode': 'product12hashLimit','referenceStore': 'referenceStore1',  'selectedPrice': 10.0  }
    When method POST
    Then status 201

  Scenario: Add a product13hashLimit for clientIdLimited
    Given path '/clientIdLimited'
    And header Accept = 'application/json'
    And request {'referenceCode': 'product13hashLimit','referenceStore': 'referenceStore1',  'selectedPrice': 10.0  }
    When method POST
    Then status 201

  Scenario: Add a product14hashLimit for clientIdLimited
    Given path '/clientIdLimited'
    And header Accept = 'application/json'
    And request {'referenceCode': 'product14hashLimit','referenceStore': 'referenceStore1',  'selectedPrice': 10.0  }
    When method POST
    Then status 201

  Scenario: Add a product15hashLimit for clientIdLimited
    Given path '/clientIdLimited'
    And header Accept = 'application/json'
    And request {'referenceCode': 'product15hashLimit','referenceStore': 'referenceStore1',  'selectedPrice': 10.0  }
    When method POST
    Then status 201

  Scenario: Add a product16hashLimit for clientIdLimited
    Given path '/clientIdLimited'
    And header Accept = 'application/json'
    And request {'referenceCode': 'product16hashLimit','referenceStore': 'referenceStore1',  'selectedPrice': 10.0  }
    When method POST
    Then status 201

  Scenario: Add a product17hashLimit for clientIdLimited
    Given path '/clientIdLimited'
    And header Accept = 'application/json'
    And request {'referenceCode': 'product17hashLimit','referenceStore': 'referenceStore1',  'selectedPrice': 10.0  }
    When method POST
    Then status 201

  Scenario: Add a product18hashLimit for clientIdLimited
    Given path '/clientIdLimited'
    And header Accept = 'application/json'
    And request {'referenceCode': 'product18hashLimit','referenceStore': 'referenceStore1',  'selectedPrice': 10.0  }
    When method POST
    Then status 201

  Scenario: Add a product19hashLimit for clientIdLimited
    Given path '/clientIdLimited'
    And header Accept = 'application/json'
    And request {'referenceCode': 'product19hashLimit','referenceStore': 'referenceStore1',  'selectedPrice': 10.0  }
    When method POST
    Then status 201

  Scenario: Add a product20hashLimit for clientIdLimited
    Given path '/clientIdLimited'
    And header Accept = 'application/json'
    And request {'referenceCode': 'product20hashLimit','referenceStore': 'referenceStore1',  'selectedPrice': 10.0  }
    When method POST
    Then status 201

  Scenario: Add a product21hashLimit for clientIdLimited
    Given path '/clientIdLimited'
    And header Accept = 'application/json'
    And request {'referenceCode': 'product21hashLimit','referenceStore': 'referenceStore1',  'selectedPrice': 10.0  }
    When method POST
    Then status 422

  Scenario: Remove a product1hashLimit  for clientIdLimited
    Given path '/clientIdLimited?productReferenceCode=product1hashLimit'
    And header Accept = 'application/json'
    When method DELETE
    Then status 204

  Scenario: Remove a product2hashLimit for clientIdLimited
    Given path '/clientIdLimited?productReferenceCode=product2hashLimit'
    And header Accept = 'application/json'
    When method DELETE
    Then status 204

  Scenario: Remove a product3hashLimit for clientIdLimited
    Given path '/clientIdLimited?productReferenceCode=product3hashLimit'
    And header Accept = 'application/json'
    When method DELETE
    Then status 204

  Scenario: Remove a product4hashLimit for clientIdLimited
    Given path '/clientIdLimited?productReferenceCode=product4hashLimit'
    And header Accept = 'application/json'
    When method DELETE
    Then status 204

  Scenario: Remove a product5hashLimit for clientIdLimited
    Given path '/clientIdLimited?productReferenceCode=product5hashLimit'
    And header Accept = 'application/json'
    When method DELETE
    Then status 204

  Scenario: Remove a product6hashLimit for clientIdLimited
    Given path '/clientIdLimited?productReferenceCode=product6hashLimit'
    And header Accept = 'application/json'
    When method DELETE
    Then status 204

  Scenario: Remove a product7hashLimit for clientIdLimited
    Given path '/clientIdLimited?productReferenceCode=product7hashLimit'
    And header Accept = 'application/json'
    When method DELETE
    Then status 204

  Scenario: Remove a product8hashLimit for clientIdLimited
    Given path '/clientIdLimited?productReferenceCode=product8hashLimit'
    And header Accept = 'application/json'
    When method DELETE
    Then status 204

  Scenario: Remove a product9hashLimit for clientIdLimited
    Given path '/clientIdLimited?productReferenceCode=product9hashLimit'
    And header Accept = 'application/json'
    When method DELETE
    Then status 204

  Scenario: Remove a product10hashLimit for clientIdLimited
    Given path '/clientIdLimited?productReferenceCode=product10hashLimit'
    And header Accept = 'application/json'
    When method DELETE
    Then status 204

  Scenario: Remove a product11hashLimit  for clientIdLimited
    Given path '/clientIdLimited?productReferenceCode=product11hashLimit'
    And header Accept = 'application/json'
    When method DELETE
    Then status 204

  Scenario: Remove a product12hashLimit for clientIdLimited
    Given path '/clientIdLimited?productReferenceCode=product12hashLimit'
    And header Accept = 'application/json'
    When method DELETE
    Then status 204

  Scenario: Remove a product13hashLimit for clientIdLimited
    Given path '/clientIdLimited?productReferenceCode=product13hashLimit'
    And header Accept = 'application/json'
    When method DELETE
    Then status 204

  Scenario: Remove a product14hashLimit for clientIdLimited
    Given path '/clientIdLimited?productReferenceCode=product14hashLimit'
    And header Accept = 'application/json'
    When method DELETE
    Then status 204

  Scenario: Remove a product15hashLimit for clientIdLimited
    Given path '/clientIdLimited?productReferenceCode=product15hashLimit'
    And header Accept = 'application/json'
    When method DELETE
    Then status 204

  Scenario: Remove a product16hashLimit for clientIdLimited
    Given path '/clientIdLimited?productReferenceCode=product16hashLimit'
    And header Accept = 'application/json'
    When method DELETE
    Then status 204

  Scenario: Remove a product17hashLimit for clientIdLimited
    Given path '/clientIdLimited?productReferenceCode=product17hashLimit'
    And header Accept = 'application/json'
    When method DELETE
    Then status 204

  Scenario: Remove a product18hashLimit for clientIdLimited
    Given path '/clientIdLimited?productReferenceCode=product18hashLimit'
    And header Accept = 'application/json'
    When method DELETE
    Then status 204

  Scenario: Remove a product19hashLimit for clientIdLimited
    Given path '/clientIdLimited?productReferenceCode=product19hashLimit'
    And header Accept = 'application/json'
    When method DELETE
    Then status 204

  Scenario: Remove a product20hashLimit for clientIdLimited
    Given path '/clientIdLimited?productReferenceCode=product20hashLimit'
    And header Accept = 'application/json'
    When method DELETE
    Then status 204

  Scenario: Verify if wishlist of clientIdLimited is empty
    Given path '/clientIdLimited'
    And header Accept = 'application/json'
    When method GET
    Then status 404
    And match response == {  'code': 'WISHLIST_NOT_FOUND_ERROR', 'message': 'This wishlist was not found or empty.'  }
