# Amogh's Super Stock App

#### Made By Amogh Joshi

## The Practical aspects of Amogh's Super Stock App

In the current day, where job loss seems to be a certainty for many people, rather than just a hypothetical situation;
many people are looking for alternative forms of making ends meet; and this is where the stock market comes into play.
With the situation caused by *COVID-19*, the markets are very disjointed from the actual dire situation of the everyday
economy. Stock trading has been up by an astounding **267%** year-over-year growth from the same time last year 
(https://www.youtube.com/watch?v=KN5AqXmYn5E). As said earlier, this is caused by a few reasons:
- Record amount of joblosses seen due to the collapse of the retail sector.
- The prevalence of commission free trading platforms mostly aimed at millennials (Robinhood, Acorns)
- The volatilty of the stock market, allowing for rapid growths or devastating losses
- Or, in some cases, as a method of spending quarantine time doing something "productive" (the case for me)   

## The personal aspect

While this may seem like an embellished scenario, this app is something I intend to continue using and updating. Stock
trading tips has been something that my father, and I relinquish together. Watching the stocks soar, in the more likely 
scenario, tumble, is a collective experience that my father, and I relinquish in together, in all of this sadness or 
glory. We have been having trouble tracking all the investments and their statistics. One of the aspects I intend to
integrate into this app once the course has finished are API calls to track the stocks prices and their profits/losses.

##User Stories Phase One
- ###First User Story:
As a user, I want to be able to create the exact specifics of a stock as I please. I want to be able to input the 
stock's name, code, the stock's purchase price, current price, and the day that the current price was recorded (so that 
in the future I can see my gains and how long it took to get there), purchase date (to see the stock's maturity over a
given time period), the amount of stocks that I have, and to see the changes in the stocks price as a whole as the 
stock matures. As such, a basic implementation of the stock trading app should be able to make all of that happen.

- ### Second User Story
As a user, I want to keep track of all of my stocks as a whole, rather than the app only being focused around one 
specific stock, but rather a large variety of stocks as a whole. I want to be able to add of the stocks that I made and 
combine them into a massive list that keeps track of all the stocks I have added.

- ## Third User Story
As a user, I want to be able to keep track of a specific stock that I have added to the list, and see all the 
information associated with the stock. I want to be able to see all the stocks that I have added to the app, and if
need be (could be due to a sale of a stock), get rid of the stock from my portfolio.

- ##Fourth User Story
As a user, I want my application to keep track of all the changes happening to a stock, and perhaps, even create a graph
showing the growth of the stock as time associated. As such, I want the app to be able to keep track of all the changes
I make to the price over a given period.


##User Stories Phase Two
- ###First User Story:
As a user, I want to have the ability to choose my stock list. Rather than it being a requirement, If I feel that the 
stock portfolio that I made is exactly how I wanted to be, I want to be able to save it. If I choose not to save the 
wishlist, then I also want the ability to not do so. The choice that the app provides to me is of utmost priority.

- ###Second User Story:
As a user, I want the ability to load my stock portfolio that I have made earlier. As an additional feature, I want to 
be able to choose between previously saved portfolios, such that multiple people can use my app without have multiple 
instances of the app open. This will allow greater flexibility when it comes to being able to use the app.


##Instructions for Grader Phase 3
- You can generate the loading event by simply starting the program. A gui should appear asking to load a stock. 
The GUI has a custom-made logo, and this is one part of my audio visual experience.
- You can generate the "adding Xs to Ys" event by pressing "1" on the main menu (the "add new stock option"), and a new
gui should appear asking to fill out some information about the stock you want to add. When you click submit, assuming 
you have no errors, you shall hear a cash register sound effect. This is the second part of my audio visual effect, and 
adds to the effect of the GUI.
- You can locate my visual component on the load gui option, and that is the logo of my application.
- You can trigger my audio component by simply pressing submit on the "add a new stock" gui. It is the sound of the cash 
register.
- You can save the state of my application by simply pressing save when exiting the application gui. 
- You can reload the state of my application by simply pressing load when opening the application.

##Phase 4
###Phase 4: Task 2
I decided to add robustness to my classes through the addition of exception handling within the "Stock" class. The way 
that this is implemented is through ensuring that the amount field within the stock object is never below 1. If it
set to an amount below 1, it will throw an exception that I have made called IncorrectTypeException. This will then be 
caught and dealt with. The IncorrectTypeException is actually thrown mainly when the user enters an incompatible type
for a field and can be reminded to change the given input. The other robustness exception I have added is the
MassiveStockFindError, which is thrown when the user tries to find a stock that is not within the stock portfolio. Both
of the exceptions are checked exceptions, but I had initially created these before this phase to ensure that the user
has a great experience that does not end the program everytime en error is made.
###Phase 4: Task 3
An area where I improved cohesion and reduced coupling is when the "AddStock" tries to set the fields within the stock
class to certain fields, I felt that rather than making it so that the program 


The fist thing I did to properly refactor my code is removing any excess fields within the class that do not need to be
there. I instead created fields within the methods to reduce the scopes of the fields. In addition to this, I also 
refactored a field that I called "data" of the type SaveAndLoad. However, SaveAndLoad is a class with only static 
methods, thus I changed the methods to use the SaveAndLoad class directly (going from data.loadData to 
SaveAndLoad.loadData) and so on. 