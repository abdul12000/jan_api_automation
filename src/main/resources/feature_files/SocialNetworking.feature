Feature: Imagine you are building a social network. Starting from simple functionality. Users are now able to make posts and comment on them.
#  You are working in the backend team that exposes the service: https://jsonplaceholder.typicode.com/ which has the following endpoints:
#
#  1. Make posts: https://jsonplaceholder.typicode.com/posts
#  2. Comment on posts: https://jsonplaceholder.typicode.com/comments
#  3. List of users: https://jsonplaceholder.typicode.com/users


  @regression
  Scenario Outline: Test that existing comments can be retreived with a GET request
    Given service is up and running
    When i search for "<id>" of a comment with a GET method
    Then i should get the correct "<id>", "<name>", "<email>" and "<body>" returned with status code of 200
    Examples:
      | id | name|email                                                                      | body                                                                                                                                                              |
      | 2  |quo vero reiciendis velit similique earum |Jayne_Kuhic@sydney.com | est natus enim nihil est dolore omnis voluptatem numquam\net omnis occaecati quod ullam at\nvoluptatem error expedita pariatur\nnihil sint nostrum voluptatem reiciendis et|
      | 5  |vero eaque aliquid doloribus et culpa|Hayden@althea.biz | harum non quasi et ratione\ntempore iure ex voluptates in ratione\nharum architecto fugit inventore cupiditate\nvoluptates magni quo et|



  @regression
  Scenario Outline: Test that new comments can be created with the POST request
    Given service is up and running
    When I create a new comment with the following details "<postId>","<name>", "<email>" and "<body>",
    Then i should get the correct "<postId>","<name>", "<email>" and "<body>" returned and with status code of 201
    Examples:
      | postId | name           | email            | body                   |
      | 2      | my new comment | lateef@gmail.com | I am writing a comment |


  Scenario Outline: Test that existing posts can be retreived with a GET request
    Given service is up and running
    When i search for "<id>" of a post with a GET method
    Then i should get the correct "<id>", "<title>" and "<body>" returned with status code of 200
    Examples:
      | id | title                                                                      | body                                                                                                                                                              |
      | 1  | sunt aut facere repellat provident occaecati excepturi optio reprehenderit | quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto |
      | 5  | nesciunt quas odio                                                         | repudiandae veniam quaerat sunt sed\nalias aut fugiat sit autem sed est\nvoluptatem omnis possimus esse voluptatibus quis\nest aut tenetur dolor neque            |


  Scenario Outline: Test that new posts can be created with the POST request
    Given service is up and running
    When I create a new post with the following details "<uId>","<title>" and "<body>",
    Then i should get the correct "<uId>","<title>" and "<body>" returned and with status code of 201
    Examples:
      | uId   | title                | body                                                     |
      | 10008 | My best Holiday ever | I went to Singapore and I had a nice time with my family |
