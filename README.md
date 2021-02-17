
1. Run `SocialApplication`
1. Visit http://localhost:8080/h2-console 
  * See `application.yml` for the name of the database: `jdbc:h2:mem:social`
1. Visit http://localhost:8080/api/users/1 to see the JSON for User id=1
1. Visit http://localhost:8080/api/users 
  * See the nice JSON output below

```
[
  {"id":1,"name":"Bert","handle":"BertMan","friendships":[3,2]},
  {"id":2,"name":"Ernie","handle":"EpicErnie","friendships":[1]},
  {"id":3,"name":"Big Bird","handle":"TallBird","friendships":[]}
]
```
