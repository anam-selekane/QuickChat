This project is a Java base messaging application called Quickchat.
it contains 3parts where Part1 consists of login and registration of the user.
Part2 consists of messages between people.
Part3 consists of storing and sending messages from one person to another.
USER MANAGEMENT
User registration with:
Username validation
Password complexity check
South African cellphone format validation
Secure login system
Login status message:
Welcome, <username>, it is great to see you again.

Messaging System
Send messages to recipients
Validate recipient number (must start with +)
Message length limit: 250 characters
Automatically generates:
Message ID (10 digits)
Message Hash
Message Options
Users can choose:
Send Message
Disregard Message

Store Message (JSON file)
JSON Storage
Messages are stored in a file:
messages.json
Each message includes:
messageID
messageNumber
recipient
messageText
messageHash
