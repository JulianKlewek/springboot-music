# springboot-music
Manage your playlists on different applications.

Whole idea is to let users convert their favourite playlists from one one app to another. For example for youtube to spotify. 
To do this you just need to paste link to playlist.


### Currently delivered features:
- Registration – Activating a New Account by Email (token)
- Registration – Password Encoding
- Registration – Roles
- Custom Validators 
- Youtube API - Getting videos titles from given playlist


### Set up Email
You need to configure the email by providing your own username and password in application.properties.

### Build and deploy
```
mvn clean install
```

To deploy just use main class: ```SpringbootMusicApplication```

Once deployed, you can access the app at:
http://localhost:8080/
