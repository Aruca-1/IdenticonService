# Identicon Service
EndPoint to generate random svg animations using the IdenticonGenerator! I use this in my github readme, check it out!

![Derez Image for Git hub](./derezTri.svg)

```
![Derez Image for the browser](icon)
```

## How to use
Host yourself: Just deploy this to Heroku!
Use mine: Try not to swap it with requests but, feel free to use it.

### URL
Create a link to /icon  using markdown like below or html and your good to go!

``` ![Derez Image for the browser](icon) ```


### Deploy To Heroku
Assuming you have the Heroku CLI installed. Pushing out the fat jar is pretty easy.

build
``` ./gradlew deployableShadow ```

* Setup Java
``` heroku plugins:install java ```

* Create a new space
``` heroku create --no-remote ```

* Deploy to Heroku
 ``` heroku deploy:jar build/libs/IdenticonService.jar --app <APP-Name-returned-from-create-above> ```


##License
Code licensed under MIT Licenses.

Generated images releases under [CC BY-SA 4.0](https://creativecommons.org/licenses/by-sa/4.0/legalcode) ![CC BY-SA 4.0](https://archive.org/images/cc/cc.png)
