# PhotoMVP
Photo application based on Android MVP Architecture

Introduction
------------
### Android MVP Architecture
This repository contains a Photo application implementing Android MVP Architecture.

The application illustrates how to implement [Model-View-Presenter][10] (MVP) architecture based on Android.

[10]: https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter

Please refer to [Arndroid Architecture Blueprints][11] for detailed information about Android Model-View-Presenter Architecture

[11]: https://github.com/googlesamples/android-architecture

### flickr RESTful API
This application uses RESTful API of [flickr][0] which is a popular image/video hosting service in order to get photos.

It receives photo data formatted in JSON and converts it to Java Objects including photo url, title, owner's name, date, description, viewer's count, comment's count and so on.

Please visit [https://www.flickr.com/services/api/](https://www.flickr.com/services/api/) for more detailed information.

[0]: https://www.flickr.com/services/api/

### Android development skills
This repository is able to help understand how to use the following skills.
* How to call RESTful API with Retrofit
* How to use OkHttp Logging Intercepter in order to debug HTTP request/response data  
* How to convert JSON to Java Objects with Moshi
* How to load images from a remote server with Glide
* How to reduce boilerplate codes with ButterKnife
* How to implement a Bottom Sheet component on Google Material Design guidelines for more fancy User Interface


Screenshots
-----------
![PhotoMVP screenshot](https://softpian.github.io/gifs/PhotoMVP_2.gif)


Getting Stared
--------------
In your local.properties file, put your own key given from [flickr][0]:

```
flickrApiKey="yourOwnAPIKey"
```
For example, you should write it as below. 
(The following API Key is not real one. Never use it in your application.)
```
flickrApiKey="788a5fnd5r134id6a792ff39pp68dcs3"
```


Libraries Used
---------------
* [Retrofit][1] - Type-safe HTTP client for Android and Java which makes it easier to consume RESTful API services.
* [OkHttp Logging Intercepter][2] - Logs HTTP request and response data with different logging levels in order to debug HTTP error 
* [Moshi][3] - JSON library for Android and Java which makes it easy to parse JSON into Java objects. Used with Retrofit Moshi converter
* [Glide][4] - A fast and efficient image loading library for Android focused on smooth scrolling which offers an easy to use
* [ButterKnife][5] - Binds field and method for Android views with annotation processing and it reduces boilerplate codes
* [BottomSheet][6] - Surface containing supplementary content that are anchored to the bottom of the screen

[1]: http://square.github.io/retrofit/
[2]: https://github.com/square/okhttp/wiki/Interceptors
[3]: https://github.com/square/moshi
[4]: https://bumptech.github.io/glide/
[5]: http://jakewharton.github.io/butterknife/
[6]: https://material.io/design/components/sheets-bottom.html

Reference
---------
* [Arndroid Architecture Blueprints][11]
* [todo-mvp of Google][12]

[12]: https://github.com/googlesamples/android-architecture/tree/todo-mvp/

License
-------

    Copyright Jaemoon Hwang <jaemoon.hwang@gmail.com>

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
