# SimpleSound
The java SimpleSound API has a very simple purpose: to make audio recording and playback more accessible to beginner and intermediate java programmers. If you have looked at the javax.sound API it is a pretty advanced library and might be hard for those just starting to learn java. The javax.sound API is not the only barrier for begginger programmers. For any practical audio playback or recording programmers must understand concurrent programming techiniques. [SimpleSound utilizes multithreading] Essentially, what SimpleSound does is it breaks the javax.sound API into two fundemental parts: recording and playback. 

What is in this repository:
• Raw code for the SimpleRecorder and SimplePlayback classes<br />
• The binary for using the SimpleSound API<br />
• The binary for the version of jlayer used for .mp3 file support<br />
• A folder of HTML javadocs for both of the classes<br />

Recording with the SimpleRecorder:

For specific documentation see the javadocs for the SimpleRecorder Class. The following are the constructors for the SimpleRecorder:

SimpleRecorder a;
a = new SimpleRecorder();&nbsp;&nbsp;&nbsp;&nbsp;//Inits a new SimpleRecorder object with path: "audio.wav", duration: 10 seconds, format: wave<br />
a = new SimpleRecorder(30);&nbsp;&nbsp;&nbsp;&nbsp;//Inits a new SimpleRecorder object with path: "audio.wav", duration: 30 seconds, format: wave<br />
a = new SimpleRecorder(30, "path/audio.wav");&nbsp;//Inits a new SimpleRecorder object with path: "path/audio.wav", duration: 30 seconds, format: wave<br />
//There is one other but these three are the ones that beginners will use. 

Play back with SimplePlayback

For specific documentation see the javadocs for the SimplePlayback Class. The following are the constructors for the SimplePlayback:

SimplePlayback b;
b = new SimplePlayback();&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;//Inits a new SimplePlayback object with path: null<br />
b = new SimplePlayback("path/file.mp3");&nbsp;&nbsp;&nbsp;&nbsp;//Inits a new SimplePlayback object with path: path/file.mp3<br />
