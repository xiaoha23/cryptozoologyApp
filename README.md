Resource Summary

URI	                            |   HTTP Method |   HTTP    |Status     | Description
galvanize.com/api/animal	    |   POST	    |   201     |CREATED	| Form submission to add a new animal.
galvanize.com/api/animal/all    |   GET         |   200     |OK         | Get All animals

sappyllc.com/api/customers	GET	200 OK	Return a list of customers.
sappyllc.com/api/customers/	GET	200 OK	Return an existing customer.
sappyllc.com/api/customers/	PUT	200 OK	Update and return an existing customer.
sappyllc.com/api/customers/	PATCH	200 OK	Partially update an existing customer.
sappyllc.com/api/customers/


As zookeeper, I want to add animals to my zoo.

Rule: Animal should have a name and a type (flying, swimming, walking)

When I add an animal
Then it is in my zoo
___________________

As zookeeper, I want to view animals of my zoo.
Given I have added animals to my zoo
When I check my zoo
Then I see all the animals

___________________

As a zookeper, I want to feed my animals.

Rule: Animal moods are unhappy or happy. They are unhappy by default.

Given an animal is unhappy
When I give it a treat
Then the animal is happy

Given an animal is happy
When I give it a treat
Then the animal is still happy
As a zookeeper, I want to maintain different types of habitats so that I can put different types of animals in them.

Given I have an empty <habitat>
When I put animal of <type> into a compatible habitat
Then the animal is in the habitat

Given I have an empty <habitat>
When I put animal of <type> into an incompatible habitat
Then the animal habitat should not change
And the animal becomes unhappy

Given I have an occuppied habitat
When I put an animal into the occupied habitat
Then the animal habitat should not change

|   type  |  habitat  |
| --------- | --------- | 
| flying     |   nest    | 
| swimming  |   ocean   | 
| walking   |   forest  | 

As a zookeeper, I want to search zoo data so that I can make reports on my zoo.

Given I have animals in my zoo
When I search for <mood> and <type>
Then I see a list of animals matching only <mood> and <type>

Given I have habitats in my zoo
When I search for empty habitats
Then I see a list of empty habitats