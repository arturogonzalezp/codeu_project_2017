
# CODEU CHAT SERVER | README

## Group 17 Getting Started

1. To build the project:
    ```
    python build/all.py rebuild
    ```
2. To run the server: 
    ```
    python build/all.py run codeu.chat.ServerMain <team_id> <team_secret> <port> <persistent-dir>
    ```
    Note: `<persistent-dir>` must be "./persistance/". Example:
    ```
    python build/all.py run codeu.chat.ServerMain 100 ABABAB 2007 ./persistance/
    ```
3. To run the client:
    ```
   python build/all.py run codeu.chat.MainGuiClient <host> <port>
    ```
    Examples:
    ```
    python build/all.py run codeu.chat.MainGuiClient 192.168.0.1 2007
    python build/all.py run codeu.chat.MainGuiClient localhost@2007
    ```

## DISCLAIMER

CODEU is a program created by Google to develop the skills of future software
engineers. This project is not an offical Google Product. This project is a
playground for those looking to develop their coding and software engineering
skills.


## ENVIRONMENT

All instructions here are relative to a LINUX environment. There will be some
differences if you are working on a non-LINUX system. We will not support any
other development environment.

Note: This has been fixed with the new python script, this project will run in Windows, too.

This project was built using JAVA 7. It is recommended that you install
JAVA&nbsp;7 when working with this project.

Table of Contents
-----------------

[**Getting Started**](#getting-started)

[**Finding Your Way Around the Project**](#your-way)

[**Source Directories**](#directories)

[**Feature Description**](#features)

[**Refactor Documentation**](#refactors)

[**Bug Documentation**](#bugs)

<a name="getting-started"></a>
## GETTING STARTED

  1. To build the project:
       ```
       python build/all.py rebuild
       ```

  1. To test the project:
       ```
       python build/all.py run codeu.chat.TestRunner
       ```

  1. To run the project you will need to run both the client and the server. Run
     the following two commands in separate terminals:

       ```
       python build/all.py run codeu.chat.ServerMain <team_id> <team_secret> <port> <persistent-dir>
       python build/all.py run codeu.chat.MainGuiClient <host> <port>
       ```

     You must specify the following startup arguments for `run codeu.chat.ServerMain':
     + `<team_id>` and `<team_secret>`: a numeric id for your team, and a secret
       code, which are used to authenticate your server with the Relay server.
       You can specify any integer value for `<team_id>`, and a value expressed
       in hexadecimal format (using numbers `0-9` and letters in the range
       `A-F`) for `<team_secret>` when you launch the server in your local setup
       since it will not connect to the Relay server.
     + `<port>`: the TCP port that your Server will listen on for connections
       from the Client. You can use any value between 1024 and 65535, as long as
       there is no other service currently listening on that port in your
       system. The server will return an error:

         ```
         java.net.BindException: Address already in use (Bind failed)
         ```

       if the port is already in use.
     + `<persistent-dir>`: the path where you want the server to save data between
       runs.

     The startup arguments for `python build/all.py run codeu.chat.MainGuiClient` are the following:
     + `<host>`: the hostname or IP address of the computer on which the server
       is listening. If you are running server and client on the same computer,
       you can use `localhost` here.
     + `<port>`: the port on which your server is listening. Must be the same
       port number you have specified when you launched `python build/all.py run codeu.chat.ServerMain`.

All running images write informational and exceptional events to log files.
The default setting for log messages is "INFO". You may change this to get
more or fewer messages, and you are encouraged to add more LOG statements
to the code. The logging is implemented in `codeu.chat.util.Logger.java`,
which is built on top of `java.util.logging.Logger`, which you can refer to
for more information.

In addition to your team's client and server, the project also includes a
Relay Server and a script that runs it (`python build/all.py run codeu.chat.RelayMain`).
This is not needed to get started with the project.

<a name="your-way"></a>
## Finding your way around the project

All the source files (except test-related source files) are in
`./src/codeu/chat`.  The test source files are in `./test/codeu/chat`. If you
use the supplied scripts to build the project, the `.class` files will be placed
in `./bin`. There is a `./third_party` directory that holds the jar files for
JUnit (a Java testing framework). Your environment may or may not already have
this installed. The supplied scripts use the version in `./third_party`.

Finally, there are some high-level design documents in the project Wiki. Please
review them as they can help you find your way around the sources.

<a name="directories"></a>
## Source Directories

The major project components have been separated into their own packages. The
main packages/directories under `src/codeu/chat` are:

### codeu.chat.client

Classes for building the three clients (`codeu.chat.ClientMain`,
`codeu.chat.SimpleGuiClientMain`, and `codeu.chat.MainGuiClient`).

### codeu.chat.server

Classes for building the server (`codeu.chat.ServerMain`).

### codeu.chat.relay

Classes for building the Relay Server (`codeu.chat.RelayMain`). The Relay Server
is not needed to get started.

### codeu.chat.common

Classes that are shared by the clients and servers.

### codeu.chat.util

Some basic infrastructure classes used throughout the project.

<a name="features"></a>
## FEATURES

### New UI

A brand new UI was developed to implement file sharing and interface callbacks.
It is the one you'll see if you run the client with the instructions in [**Getting Started**](#getting-started)

### File Sharing

You are now able to share files through the chat. Simply click on the "File" button in the bottom-right corner, select a file from your computer and share it with everyone in the conversation.

### Secure Login

To make sure only you can read your conversations, we've implemented a secure login that will prompt you for your username and password before you can access the messages. It'll appear right when you run the client as in the [**Getting Started**](#getting-started) instructions

### Message Encryption

To add even more security to the chat, messages are encrypted when going through the Server and database so none but the users in your conversation can read them. This is done automatically and not apparent on the graphical interface, but you can see how the message is stored and sent through the server in the terminal where you run the client.

<a name="refactors"></a>
## REFACTOR DOCUMENTATION

### New UI
We created a brand new UI which enabled us to implement the secure login and file sharing features. However, using Swing limited the design we could apply to it and not making the project a webapp limited the features we could implement. We chose it because that's the library we were more comfortable with and could build faster.

### Changed Conversation, Message and User classes
This allowed us to add files and encryption as well as persistence.

### Changed the methods that add conversations and messages
This sometimes conflicted with the interfaces implemented, but allowed encryption, persistence and file-sharing to be added.

<a name="bugs"></a>
## BUG DOCUMENTATION

### 1. Key Storage in Firebase
Symptoms: Keys won't store in database
Cause: Firebase cannot parse BigIntegers include in EncryptionKeys
Cure: Casting BigIntegers to String and save them as such

### 2: Message Decryption Because of BIgInteger Casting
Symptoms: Messages were displayed as gibberish
Cause: Keys and messages weren't being parsed correctly from BigIntegers to Strings and viceversa
Cure: Had to create two different methods for purely numerical values and text

### 3: Last Message Decryption
Symptoms: Last message sent is displayed as gibberish
Cause: Las message is not encrypted but still goes through the decryption process so it's turned into gibberish
Cure: Verify if the message is encrypted before decrypting it

### 4: UI Repaint Bug
Symptoms: When updating automatically, the UI sometimes won't repaint itself correctly
Cause: Unknown
Cure: Bug not fixed yet, added a manual update button meanwhile
