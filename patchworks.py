#!/usr/bin/env python3
# -*- coding: utf-8 -*-


import turtle
import random

# Function to draw a square patch with a given color and design
def draw_patch(color, design):
    turtle.fillcolor(color)
    turtle.begin_fill()

    if design == 1:
        # Replace this with your own design for patch 1
        turtle.circle(50)

    elif design == 2:
        # Replace this with your own design for patch 2
        turtle.forward(100)
        turtle.right(90)
        turtle.forward(100)
        turtle.right(90)
        turtle.forward(100)
        turtle.right(90)
        turtle.forward(100)

    turtle.end_fill()

# Function to draw a patchwork based on the user's inputs
def draw_patchwork(size, colors, student_number):
    turtle.speed(2)
    turtle.bgcolor("white")

    # Determine patch designs based on the student number
    design1 = int(student_number[-2])
    design2 = int(student_number[-1])

    # Determine patch arrangement based on the antepenultimate digit
    arrangement = int(student_number[-3])

    # Draw the patchwork
    for row in range(size):
        for col in range(size):
            x = col * 100
            y = row * 100

            # Determine color based on user input
            color = colors[col % len(colors)]

            # Draw the patch based on the arrangement
            if arrangement == 1:
                draw_patch(color, design1)
            elif arrangement == 2:
                draw_patch(color, design2)
            else:
                # Plain patch (no design)
                turtle.fillcolor("white")
                turtle.begin_fill()
                turtle.forward(100)
                turtle.right(90)
                turtle.forward(100)
                turtle.right(90)
                turtle.forward(100)
                turtle.right(90)
                turtle.forward(100)
                turtle.end_fill()

            # Move to the next patch position
            turtle.penup()
            turtle.goto(x, y)
            turtle.pendown()

    turtle.done()

# Main program
def main():
    # Get user inputs
    size = int(input("Enter the patchwork size (5, 7, or 9): "))
    colors = []

    while len(colors) < 3:
        color = input(f"Enter color {len(colors) + 1} (red, green, blue, magenta, orange, yellow, cyan): ").lower()
        if color not in colors:
            colors.append(color)
        else:
            print("Color must be different from the previous ones. Try again.")

    student_number = input("Enter your student number: ")

    # Draw the patchwork
    draw_patchwork(size, colors, student_number)

if __name__ == "__main__":
    main()


