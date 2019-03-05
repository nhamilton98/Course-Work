Nathan Hamilton
CSCD 437-01
Homework 5 - Regex

The only part of my solution I would consider a shortcoming is that I used a helper method to help validate in three of the problems:

Date - validate() method used to ensure date entered is a real date (i.e. day<=31/<=30/<=29/<=28 depending on the month and year)
Time - validate() method used to ensure hour>=0/<=23 and minutes>=0/<=59
Password - validate() method used to ensure password does not contain three consecutive characters