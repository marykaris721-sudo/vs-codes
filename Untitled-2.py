import random

def start_game():
    secret=random.randint(0,10)
    attempts = 0
    guessed = False

    print("--WELCOME TO THE GUESSING GAME--")
    print("--AM SELECTING A RANDOM NUMBER 0<X>10 GUESS IT RIGHT--")
    
    while not guessed:
        try:
            guess=int(input("\n ENTER YOUR GUESS: "))
            attempts+=1

            if guess<secret:
                print("too low! try again!")
            elif guess>secret:
                print("too high! try again!")
            else:
                print(f"🎉 CONGRATULATION AFTER {attempts} NUMBER OF ATTEMPTS!🎉")
                break
        except ValueError:
            print("Value Error!")
start_game()