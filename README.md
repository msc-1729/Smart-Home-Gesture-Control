<h2 align ="center"> Smart-Home-Gesture-Control </h2> </br>

# Introduction
This is an Android application that allows the user to control the IoT devices in a home with hand gestures. Initially, the application requires the user to upload a recording of the videos that are then received by the server that has been hosted using the Python Flask library. These practice videos can be recorded by referring to the expert videos that are provided on the first screen of the application. Later after confirming that the video is clear and accurate enough, it can be labeled as one of the listed gestures and then uploaded into the database. A pre-trained CNN for processing and recognizing the gesture in the videos are used for recognizing the gesture and performing the appropriate action.

# First Page
The application basically contains three pages and the first page contains the drop-down of 17 different gestures. Upon selection of a single gesture, the user will be directed to the second page. The list of gestures is as follows: {Turn on lights, Turn off lights, Turn on the fan, Turn off the fan, Increase fan
speed, decrease fan speed, Set Thermostat to the specified temperature, gestures one for each digit 0,1,2,3,4,5,6,7,8,9}
<img src="https://github.com/msc-1729/Smart-Home-Gesture-Control/blob/main/assets/ListOfGestures.png"/>

# Second Page

The second screen contains two buttons where one is for viewing the expert gesture recording for the particular gesture and then the record button can be used to record the video gesture, after confirming that the gesture video is clear enough and accurate, it can be uploaded onto the database through the flask server.
The videos in the application are played using the android.provider.MediaStore library for accessing the Media file and then by using the android.media.MediaPlayer the video is played for the reference of the user. 
A screenshot of how the first page looks is as follows: </br>
<img src = "https://github.com/msc-1729/Smart-Home-Gesture-Control/blob/main/assets/First%20Page.png" />

# Third Page: 
On this page, the camera interface will be opened for the user to record the practice gesture. The video will be captured for 5 seconds, and the video will be saved with the following filename format: [GESTURE NAME]_PRACTICE_[practice number].mp4
Screen 3 will have another button that says “UPLOAD”. Once this button is pressed, the user will be able to upload the gesture to a local server. Moreover, clicking this button will take the user back to Screen 1.

<img src = "https://github.com/msc-1729/Smart-Home-Gesture-Control/blob/main/assets/SecondPage_for%20recording.png" />

# Local server

The server that hosts the application is developed using the Python Flask library. It is hosted on IP 192.168.0.136 and in port 5000. Through this port, the server accepts the recorded gesture videos, and then an appropriate response is provided through the same port. A python file named server is used for hosting the server which also saves the uploaded videos into the folders of the local machine in which the program is running. The data is transferred between the user interface and the database through the server using CRUD operations GET and POST. 

The whole demonstration of the application can be found in this video: 
https://youtu.be/i0CVlT2A9gQ




