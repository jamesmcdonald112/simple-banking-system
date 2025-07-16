Components:
•	Account: stores card, pin, balance, and optional ID
•	AccountStore: central static storage and lookup
•	AccountGenerator: builds accounts using credit card methods
•	LoginManager: handles login and account validation
•	Utility: random number formatting, Luhn logic placeholder
•	Menu: main user interface controller

PHASE 1: FOUNDATION CLASSES + UTILITY

1. Create a Utility class
   •	Method: generateRandomNumber(int digits)
   •	Uses String.format("%0" + digits + "d", random.nextInt((int)Math.pow(10, digits)))
   •	Later: add calculateLuhnChecksum(String partialCardNumber)

2. Write tests for:
•	Random numbers are the correct length
•	Only digits

3. Create Account class

4. Fields:
•	String cardNumber
•	String pin
•	int balance = 0
•	String id (optional, for database later)

5. Write the constructor and getter methods.

6. Write tests for:
•	Creating an account sets the correct card and PIN
•	Balance starts at 0

⸻

1. Create AccountStore class
   •	Field: static List<Account> accounts = new ArrayList<>();

2. Methods:
•	add(Account account)
•	Account findByCardAndPin(String card, String pin)

3. Write tests for:
•	Adding an account stores it
•	Searching with correct card+pin returns the account
•	Wrong pin or card returns null

⸻

PHASE 2: CARD + PIN GENERATION

⸻

1. Create AccountGenerator class

Methods:
•	generateCardNumber()
•	Prefix: “400000”
•	Append: 9-digit random number
•	Append: dummy checksum (e.g. “1” for now)
•	generatePin()
•	Use Utility.generateRandomNumber(4)
•	generateAccount()
•	Calls the above methods
•	Returns a new Account

Write tests for:
•	Card starts with “400000”
•	Card length = 16
•	Pin is 4 digits
•	Account object has correct fields set

⸻

PHASE 3: LOGIN SYSTEM

⸻

1. Create LoginManager class

Methods:
•	boolean isValidLogin(String card, String pin)
•	Calls AccountStore.findByCardAndPin()
•	Returns true if found

Write tests for:
•	Valid login returns true
•	Invalid login returns false

⸻

PHASE 4: USER INTERFACE + FLOW

⸻

1. Create Menu class

Method:
•	static void showMainMenu()
•	While loop:
•	1: create account
•	2: login
•	0: exit

Handle “Create Account”
   •	Calls AccountGenerator.generateAccount()
   •	Saves to AccountStore
   •	Prints card and pin to user

You can now write integration-style tests here or just use manual console testing.

⸻

1. Handle “Login to Account”
   •	Prompt for card + pin
   •	Use LoginManager.isValidLogin(card, pin)
   •	If correct, show account menu:
   •	1: Show balance
   •	2: Log out (goes back to main menu)
   •	0: Exit

⸻
1. Later: Implement Luhn algorithm properly
- Checksum verifies that the card number is valid
- The total modulus 10 should equal zero to be valid
- Take the original card number
- Drop the last digit (16th digit)
- Multiply odd digits (including the first digit) by 2
- Subtract 9 to numbers over 9
- Add all numbers together
- Whatever is left over (moduls 10) is the check sum

- The card number is stored as a string, i need to break each character into an array and 
  convert it to an int for this to work. This will allow e to multiply by two and to add 
  together. At the end, i can convert it back to a string


  
-

1. Balance from database 
Test:
- Add an account to the database with a known balance
- Simulate the user logging in and selecting balance
- Assert that the correct balance is shown in the console output.