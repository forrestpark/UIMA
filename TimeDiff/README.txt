Customizations

In the EasyResult screen, our team used different icons for displaying correct and incorrect answers. Instead of the magenta correct/incorrect signs proposed by the design document, our implementation uses a green circle and a red ‘X’ for correct and incorrect answers, respectively. We also placed the signs under the hourglass images instead of over them. We reasoned that said variation would be the simplest way to implement while maintaining the functionality proposed by the design document.

In the LearnHard screen, our team used EditText boxes instead of sliders for receiving user inputs, as allowed by the instructions of the assignment.


Testing

For Calculate, we logged the answer from our time difference calculation function and checked that this was what was output on the UI. Similarly, our team tested the LearnEasy activity by putting in the program “Log.d” statements that would print either the actual time difference (between the start and end time) or the size of the hourglass image selected (small, medium, large), and compared the printed answers--the time differences--and the guesses--the size of the selected hourglass images--using Logcat.


Design Suggestions

Instead of blocking direct access to the LearnHard screen and making the users navigate through the EasyResult screen to get to it, creating a button on the main activity screen that navigates directly to the LearnHard screen may be a better design choice, as it provides greater flexibility to the users with relatively low development cost.

An option to retry the LearnHard activity like the LearnEasy activity may be a better design choice for the same reason mentioned above.

It does not make sense for this app to contain six activities. In fact, just three will suffice with the design that we were given: a calculate activity, a learn activity that contains both easy and hard modes since they share many of the same objects, and a main activity from which the user could navigate to the calculate or learn activities.


Issues

One issue that we faced was that when testing our application on the Nexus One API 18, our app could not fit in the small screen. We tried switching to a LinearLayout in hopes that we could play with relative sizing of the objects with respect to the screen size, but we could not get it to work satisfactorily. In addition, we tried to chain objects together, but for some reason, the objects did not spawn as we expected them to; we could not give relative weights to manipulate the positions of the objects. Despite this issue with the older phone/API version, we decided to stick with our original design because it seemed to work fine with most phone models and APIs in the emulator.

We failed to implement highlighting the selected hourglass image. Our team searched for help from online resources and tried adjusting attributes of the three hourglass images like the margins but ended up failing to make it work. Users are still able to see a slight color change in the boundaries of the selected image and a sound change when an image is newly selected.

Partner

Ray Lee -- jlee664
